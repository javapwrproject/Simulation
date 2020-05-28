package project;

import project.eggs.Egg;
import project.eggs.IEgg;
import project.map.IMap;
import project.map.Map;
import project.species.Dermathogides;
import project.species.Euroglyphus;
import project.species.IMite;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Simulation {

	private static IMap map;
	private Printer printer;
	private LinkedList<IMite> mites = new LinkedList<>();
	private List<IEgg> eggs = new LinkedList<>();

	private final Random randomSeed = new Random();
	// opakować parametry w "SimulatinoProperties"
	public Simulation(int euroglyphuses, int dermathoideses, int length, int width) {

		map = new Map(length, width);
		printer = new Printer(euroglyphuses, dermathoideses, map);

		placeDermathoidesesAtRandomEmptyCoordinates(dermathoideses, length, width);
		placeEuroglyphusesAtRandomEmptyCoordinates(euroglyphuses, length, width);
	}

	private void placeEuroglyphusesAtRandomEmptyCoordinates(int euroglyphusesToPlace) {
		for (int i = 0; i < euroglyphusesToPlace; i++) {
			Cordinates coordinate = map.getRandomEmptyCoordinates();

			// lepiej, żeby mapa/plansza trzymała informacje o pozycjach i roztoczach
			// możemy jej powiedzieć "umieść mi takie roztocze na takiej pozycji
			// lub zapytać o to, czy istnieją roztocza, ich liczbę itp
			map.place(coordinate, new Euroglyphus());
		}
	}

	private void placeDermathoidesesAtRandomEmptyCoordinates(int dermathoideses, int length, int width) {
		for (int i = 0; i < dermathoideses; i++) {
			Cordinates crd = new Cordinates(randomSeed.nextInt(length), randomSeed.nextInt(width));

			if (map.getStatus(crd) == Type.EMPTY) {
				map.setStatus(crd, Type.DERMATH);
				mites.add(new Dermathogides(crd));
			} else i--;
		}
	}


	public void runSimulation() {

		for (int i = 0; i < mites.size(); i++) {
			IMite m = mites.get(i);

			if (m.isStarved()) {
				map.setStatus(m.getCordinates(), Type.EMPTY);
				mites.remove(m);
				i -= 1;
				if (m.getType() == Type.EUROGLYPH) printer.setEuroglyphuses(-1);
				if (m.getType() == Type.DERMATH) printer.setDermathoideses(-1);
				continue;
			}

			if (m.layEggAbility()) {
				eggs.add(new Egg(m.getEggType(), m.getCordinates()));
				m.layEgg();
				map.setStatus(m.getCordinates(), m.getEggType());
			}

			boolean bool = true;
			while (bool) {
				if (map.getStatus(m.getCordinates()) == m.getType()) // other case it mean there is his egg
					map.setStatus(m.getCordinates(), Type.EMPTY);

				Cordinates crd = new Cordinates(m.move());

				if (map.getStatus(crd) == Type.DERMATHEGG || map.getStatus(crd) == Type.EUROGLYPHEGG || map.getStatus(crd) == m.getType())
					continue;

				if (map.getStatus(crd) == Type.EMPTY) {
					map.setStatus(crd, m.getType());
					m.getCordinates().set(crd.getX(), crd.getY());
					break;
				}

				if (map.getStatus(crd) == Type.FOOD) {
					m.eat();
					map.setStatus(crd, m.getType());
					m.getCordinates().set(crd.getX(), crd.getY());
					break;

				} else {
					for (int j = 0; j < mites.size(); j++) {
						IMite enemy = mites.get(j);

						if (enemy.getCordinates().equals(crd)) {
							while (enemy.getHealth() > 0 && m.getHealth() > 0) {
								m.attack(enemy);
								if (enemy.getHealth() > 0) enemy.attack(m);
							}

							if (m.getHealth() > enemy.getHealth()) {
								map.setStatus(crd, m.getType());
								m.getCordinates().set(crd.getX(), crd.getY());
								mites.remove(enemy);
								if (m.getType() == Type.DERMATH) {
									printer.setEuroglyphuses(-1);
									System.out.println("EUROGLYPHUS WAS ATTACKED AND KILLED");
								} else {
									printer.setDermathoideses(-1);
									System.out.println("DERMATHOIDES IS ATTACK AND LOSE");
								}
								if (i > j) i -= 1;
								bool = false;

							} else {
								mites.remove(m);
								if (m.getType() == Type.DERMATH) {
									printer.setDermathoideses(-1);
									System.out.println("DERMATHOIDES WAS ATTACKED AND KILLED");
								} else {
									printer.setEuroglyphuses(-1);
									System.out.println("EUROGLYPHUS IS ATTACK AND LOSE");
								}
								i -= 1;
								bool = false;
							}
							break;
						}
					}
				}
			}
		}

		for (IEgg egg : eggs) {
			hatchIfItsTime(egg);
		}
	}

	private void hatchIfItsTime(IEgg egg) {
		if (egg.timeToHatch()) {
			IMite hatchedMite = egg.hatch();
			mites.add(hatchedMite);
			map.setStatus(egg.getCordinates(), hatchedMite.getType());
			eggs.remove(egg);
		}
	}


	public boolean notOver() {
		boolean dermathsStillPersist = thereAreAnyDermaths() || thereAreAnyUnhatchedDermathEggs();
		boolean mitesStillPersist = thereAreAnyMites() || thereAreAnyUnhatchedMiteEggs();

		// mam nadzieję, że nic nie pomieszałem
		boolean mitesWon = !dermathsStillPersist && mitesStillPersist;
		boolean dermathsWon = !mitesStillPersist && dermathsStillPersist;
		return !mitesWon && !dermathsWon;
	}

	private boolean thereAreAnyUnhatchedMiteEggs() {
		return eggs.stream().anyMatch(egg -> egg.getType() == Type.EUROGLYPHEGG);
	}

	private boolean thereAreAnyMites() {
		return mites.stream().anyMatch(mite -> mite.getType() == Type.EUROGLYPH);
	}

	private boolean thereAreAnyUnhatchedDermathEggs() {
		return eggs.stream().anyMatch(egg -> egg.getType() == Type.DERMATHEGG);
	}

	private boolean thereAreAnyDermaths() {
		return mites.stream().anyMatch(mite -> mite.getType() == Type.DERMATH);
	}


	public static IMap getMap() {
		return Simulation.map;
	}

	public Printer getPrinter() {
		return this.printer;
	}

}
/*	map.setStatus(crd, enemy.getType() );
enemy.getCordinates().setX(crd.getX() );
enemy.getCordinates().setY(crd.getY() ); // optional mood: attacked (if win) go to place object who attack first */
