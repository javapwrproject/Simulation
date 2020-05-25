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

public class Simulation {
	
	private static IMap map;
	private Printer printer;
	private LinkedList<IMite> mitelist = new LinkedList<>();
	private List<IEgg> egglist = new LinkedList<>();	
	
	public Simulation(int euroglyphuses, int dermathoideses, int length, int width) {
		
		map = new Map(length, width);
		printer = new Printer(euroglyphuses, dermathoideses, map);
		
		Random rnd = new Random();
		for (int i = 0; i < dermathoideses; i++) {
			Cordinates crd = new Cordinates(rnd.nextInt(length), rnd.nextInt(width));
			
			if (map.getStatus(crd) == Type.EMPTY) {
				map.setStatus(crd, Type.DERMATH);
				mitelist.add(new Dermathogides(crd));
			}	else i--;	
		}
		
		for (int i = 0; i < euroglyphuses; i++) {
			Cordinates crd = new Cordinates(rnd.nextInt(length), rnd.nextInt(width));
			
			if (map.getStatus(crd) == Type.EMPTY) {
				map.setStatus(crd, Type.EUROGLYPH);
				mitelist.add(new Euroglyphus(crd));
			} else i--;	
		}		
	}
	
	
	public void runSimulation() {
		
		for (int i = 0; i < mitelist.size(); i++) { 
			IMite m = mitelist.get(i);
				
			if(m.isStarved()) {
				map.setStatus(m.getCordinates(), Type.EMPTY); 
				mitelist.remove(m);
				i -= 1;
				if (m.getType() == Type.EUROGLYPH) printer.setEuroglyphuses(-1);
				if (m.getType() == Type.DERMATH) printer.setDermathoideses(-1);
				continue;
				}
				
			if (m.layEggAbility()) {
				egglist.add(new Egg(m.getEggType(), m.getCordinates()));
				m.layEgg();	
				map.setStatus(m.getCordinates(), m.getEggType() );
			}
				
			boolean bool = true;
			while(bool) {
				if (map.getStatus(m.getCordinates()) == m.getType()) // other case it mean there is his egg
					map.setStatus(m.getCordinates(), Type.EMPTY);
					
				Cordinates crd = new Cordinates (m.move());
					
				if (map.getStatus(crd ) == Type.DERMATHEGG || map.getStatus(crd ) == Type.EUROGLYPHEGG || map.getStatus(crd ) == m.getType()) 
					continue;
				
				if (map.getStatus(crd ) == Type.EMPTY) {
					map.setStatus(crd, m.getType() );
					m.getCordinates().set(crd.getX(), crd.getY() );
					break;
				}
					
				if (map.getStatus(crd ) == Type.FOOD) {
					m.eat();
					map.setStatus(crd, m.getType() );
					m.getCordinates().set(crd.getX(), crd.getY() );
					break;
						
				} else {
					for (int j = 0; j < mitelist.size(); j++) {
						IMite enemy = mitelist.get(j);
							
						if (enemy.getCordinates().equals (crd)) {
							while(enemy.getHealth() > 0 && m.getHealth() > 0) {
								m.attack(enemy);
								if (enemy.getHealth() > 0) enemy.attack(m);
							}
								
							if (m.getHealth() > enemy.getHealth()) {
								map.setStatus(crd, m.getType() );
								m.getCordinates().set(crd.getX(), crd.getY() );
								mitelist.remove(enemy);
								if (m.getType() == Type.DERMATH) {
									printer.setEuroglyphuses(-1); 
									System.out.println("EUROGLYPHUS WAS ATTACKED AND KILLED");
								}
								else {
									printer.setDermathoideses(-1); 
									System.out.println("DERMATHOIDES IS ATTACK AND LOSE");
								}
								if (i > j) i -= 1;
								bool = false;
								
							 }else {
								mitelist.remove(m);
								if (m.getType() == Type.DERMATH) {
									printer.setDermathoideses(-1);
									System.out.println("DERMATHOIDES WAS ATTACKED AND KILLED");
								}
								else {
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
			
		for (int i = 0; i < egglist.size(); i++) {
			IEgg e;
			e = egglist.get(i);
				
			if (e.timeToHatch()) {
				if (e.getType() == Type.DERMATHEGG) {
					mitelist.add( new Dermathogides(e.getCordinates() ) );
					printer.setDermathoideses(1); 
				}
				else { 
					mitelist.add( new Euroglyphus(e.getCordinates() ) );
					printer.setEuroglyphuses(1);
				}
				map.setStatus(e.getCordinates(), mitelist.getLast().getType());
				egglist.remove(e);
				i -= 1;
			}
		}
	}
	
	
	public boolean notOver() {
		boolean over = true;
		
		for (IMite m : mitelist) {
			if (m.getType() == Type.DERMATH) over = false;
		
			for (IEgg e : egglist) 
				if (e.getType() == Type.DERMATHEGG) over = false;	
		}	
		if (over == true) return false;
		
		over = true;
		for (IMite m : mitelist) {
			if (m.getType() == Type.EUROGLYPH) over = false;
		
			for (IEgg e : egglist) 
				if (e.getType() == Type.EUROGLYPHEGG) over = false;	
		}	
		
		return !over;
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
