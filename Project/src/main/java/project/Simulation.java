package project;
import project.species.IMite; 
import project.species.Dermathogides;
import project.species.Euroglyphus; 
import project.eggs.IEgg;
import project.eggs.Egg;
import project.map.IMap;
import project.map.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/**
 * Responsible for creating and destroying objects 
 * Iterating through objects to actualize their positions, carry out processes of fights and updating the map
 */
public class Simulation {
	
	private IMap map;
	private Printer printer;
	private LinkedList<IMite> mites = new LinkedList<>();
	private List<IEgg> eggs = new LinkedList<>();	
	
	public Simulation(Properties properties, int heigth, int width) {
		
		map = new Map(heigth, width);
		
		Random rnd = new Random();
		for (int i = 0; i < properties.dermathoideses; i++) {
			Cordinates crd = new Cordinates(rnd.nextInt(heigth), rnd.nextInt(width));
			
			if (map.getStatus(crd) == Type.EMPTY) {
				map.setStatus(crd, Type.DERMATHOIDES);
				mites.add(new Dermathogides(crd));
			}	else i--;	
		}
		
		for (int i = 0; i < properties.euroglyphuses; i++) {
			Cordinates crd = new Cordinates(rnd.nextInt(heigth), rnd.nextInt(width));
			
			if (map.getStatus(crd) == Type.EMPTY) {
				map.setStatus(crd, Type.EUROGLYPHUS);
				mites.add(new Euroglyphus(crd));
			} else i--;	
		}	
		
		printer = new Printer(map);
	}
	
	
	public void runSimulation() {
		
		for (int i = 0; i < mites.size(); i++) { 
			IMite m = mites.get(i);
				
			if(m.isStarved()) {
				map.setStatus(m.getCordinates(), Type.EMPTY); 
				mites.remove(m);
				i -= 1;
				continue;
				}
				
			if (m.layEggAbility()) {
				eggs.add(new Egg(m.getEggType(), m.getCordinates()));
				m.layEgg();	
				map.setStatus(m.getCordinates(), m.getEggType() );
			}
				
			boolean bool = true;
			while(bool) {
				if (map.getStatus(m.getCordinates()) == m.getType()) // other case it mean there is his egg
					map.setStatus(m.getCordinates(), Type.EMPTY);
					
				Cordinates crd = new Cordinates (m.move(map));
					
				if (map.getStatus(crd ) == Type.DERMATHOIDES_EGG || map.getStatus(crd ) == Type.EUROGLYPHUS_EGG || map.getStatus(crd ) == m.getType()) 
					continue;
				
				if (map.getStatus(crd ) == Type.EMPTY) {
					map.setStatus(crd, m.getType() );
					m.getCordinates().set(crd);
					break;
				}
					
				if (map.getStatus(crd ) == Type.FOOD) {
					m.eat();
					map.setStatus(crd, m.getType() );
					m.getCordinates().set(crd);
					break;
						
				} else {
					for (int j = 0; j < mites.size(); j++) {
						IMite enemy = mites.get(j);
							
						if (enemy.getCordinates().equals (crd)) {
							while(enemy.getHealth() > 0 && m.getHealth() > 0) {
								m.attack(enemy);
								if (enemy.getHealth() > 0) enemy.attack(m);
							}
								
							if (m.getHealth() > enemy.getHealth()) {
								map.setStatus(crd, m.getType() );
								m.getCordinates().set(crd);
								printer.messegeDeathDefender(enemy.getType());
								mites.remove(enemy);
								if (i > j) i -= 1;
								bool = false;	
							 }else {
								printer.messegeDeathAttacker(m.getType());
								mites.remove(m);
								i -= 1;
								bool = false;
							}
							break;
						}
					}	
				}						
			}	
		}	
			
		for (int i = 0; i < eggs.size(); i++) {
			IEgg e;
			e = eggs.get(i);
				
			if (e.timeToHatch()) {
				if (e.getType() == Type.DERMATHOIDES_EGG)
					mites.add( new Dermathogides(e.getCordinates() ) ); 
				else 
					mites.add( new Euroglyphus(e.getCordinates() ) );
				
				map.setStatus(e.getCordinates(), mites.getLast().getType());
				eggs.remove(e);
				i -= 1;
			}
		}
	}
	
	
	public boolean notOver() {
		boolean over = true;
		
		for (IMite m : mites) {
			if (m.getType() == Type.DERMATHOIDES) over = false;
		
			for (IEgg e : eggs) 
				if (e.getType() == Type.DERMATHOIDES_EGG) over = false;	
		}	
		if (over == true) return false;
		
		over = true;
		for (IMite m : mites) {
			if (m.getType() == Type.EUROGLYPHUS) over = false;
		
			for (IEgg e : eggs) 
				if (e.getType() == Type.EUROGLYPHUS_EGG) over = false;	
		}	
		
		return !over;
	}
	
	
	public IMap getMap() {
		return map;	
	}
	
	public Printer getPrinter() {
		return this.printer;
	}
}
/*	map.setStatus(crd, enemy.getType() );
enemy.getCordinates().setX(crd.getX() );
enemy.getCordinates().setY(crd.getY() ); // optional mood: attacked (if win) go to place object who attack first */
