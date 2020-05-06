package project;
import project.species.Dermathogides;
import project.species.Euroglyphus;
import project.species.IMite; 
import project.eggs.Egg;
import project.eggs.IEgg;
import project.map.IMap;
import project.map.Map;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;

public class Simulation {
	
	static int dermathoideses = 16;
	static int euroglyphuses = 16;
	static int length = 49;
	static int width = 75;
	
	static Timer time = new Timer();
	static IMap map;
	static LinkedList<IMite> mitelist = new LinkedList<>();
	static LinkedList<IEgg> egglist = new LinkedList<>();
	
	
	public static void runSimulation() {
		
		while (true) { // MAXTIME
			for (int i = 0; i < mitelist.size(); i++) { 
				IMite m = mitelist.get(i);
				
				if(m.isStarved()) {
					map.setStatus(m.getCordinates(), 0); // information to map that now this place is empty
					mitelist.remove(m); // if map would have mitelist by reference, map could remove object from list in case of "setPosition" 
					i--;
					if (m.getType() == 7) euroglyphuses--;
					else dermathoideses--;
					continue;
					
					}
				
				if (m.layEggAbility()) {
					egglist.add(new Egg(m.getType(), m.getCordinates()));
					m.layEgg();	
					map.setStatus(m.getCordinates(), m.getType()*10 ); // or mite should tell it to map
				}
				
				boolean bool = true;
				while(bool) {
					if (mitelist.size() == 0) break; 
					
					if (map.getStatus(m.getCordinates()) == m.getType()) // other case it mean that there is his egg
						map.setStatus(m.getCordinates(), 0);
					
					Cordinates crd = new Cordinates (m.move());
					if (crd.getX() < 0) crd.setX(0);
					if (crd.getX() >= length)  crd.setX(length-1);
					if (crd.getY() < 0)  crd.setY(0);
					if (crd.getY() >= width)  crd.setY(width-1);
					
					if (m.getType() == 8) {
						switch (map.getStatus(crd ) ) {
	
							case 0: // empty
								map.setStatus(crd, m.getType() );
								// System.out.println(" case 0");
								m.getCordinates().setX(crd.getX() );
								m.getCordinates().setY(crd.getY() );
								bool = false;
								break;
								
							case 1: // food
								m.eat();
								map.setStatus(crd, m.getType());
								// System.out.println("case 1");
								m.getCordinates().setX(crd.getX() );
								m.getCordinates().setY(crd.getY() );
								bool = false;
								break;
								
							case 80: // egg
								break;
								
							case 70: // egg other specimen
								break;
								
							case 7: // other speciemen
				
								for (int j = 0; j < mitelist.size(); j++) {
									IMite enemy = mitelist.get(j);
									if (enemy.getCordinates().equalss(crd)) {
										while(enemy.getHealth() > 0 && m.getHealth() > 0) {
											m.attack(enemy);
											if (enemy.getHealth() > 0) enemy.attack(m);
										}
										
										if (m.getHealth() > enemy.getHealth()) {
											map.setStatus(crd, m.getType() );
											
											m.getCordinates().setX(crd.getX() );
											m.getCordinates().setY(crd.getY() );
											
											mitelist.remove(enemy);
											if (i > j) i--;
											bool = false;
											System.out.println("Euroglyphus is killed");
											euroglyphuses--;
										} else {
											mitelist.remove(m);
											i--;
											bool = false;
											System.out.println("Dermathoides is killed");
											dermathoideses--;
											
										}
										break;
									}
								}
							break;
								 
							case 8:  // same speciemen
								break;			
						}
						
					} if (m.getType() == 7) { 
						switch (map.getStatus(crd ) ) {
		
							case 0:
								map.setStatus(crd, m.getType() );
								// System.out.println(" case 0");
								m.getCordinates().setX(crd.getX() );
								m.getCordinates().setY(crd.getY() );
								bool = false;
								break;
								
							case 1:
								m.eat();
								map.setStatus(crd, m.getType());
								// System.out.println("case 1");
								m.getCordinates().setX(crd.getX() );
								m.getCordinates().setY(crd.getY() );
								bool = false;
								break;
								
							case 80:
								break;
								
							case 70:
								break;
								
							case 7: 
								break;	
								 
							case 8:
								
								for (int j = 0; j < mitelist.size(); j++) {
									IMite enemy = mitelist.get(j);
									if (enemy.getCordinates().equalss(crd)) {
										while(enemy.getHealth() > 0 && m.getHealth() > 0) {
											m.attack(enemy);
											if (enemy.getHealth() > 0) enemy.attack(m);
										}
										
										if (m.getHealth() > enemy.getHealth()) {
											map.setStatus(crd, m.getType() );
											m.getCordinates().setX(crd.getX() );
											m.getCordinates().setY(crd.getY() );
											mitelist.remove(enemy);
											if (i > j) i--;
											bool = false;
											System.out.println("Dermathoides is killed");
											dermathoideses--;
											
										} else {
											mitelist.remove(m);
											i--;
											bool = false;
											System.out.println("Euroglyphus is killed");
											euroglyphuses--;
											
										}
										break;
									}
								}
							break;
						}
					}						
				}	
			}	
			
			for (int i = 0; i < egglist.size(); i++) {
				IEgg e;
				e = egglist.get(i);
				
				if (e.timeToHatch()) {

					if (e.getType() == 8) {
						mitelist.add( new Dermathogides(e.getCordinates() ) );
						dermathoideses++;
					}
					else { 
						mitelist.add( new Euroglyphus(e.getCordinates() ) );
						euroglyphuses++;
					}
					map.setStatus(e.getCordinates(), mitelist.getLast().getType());
					egglist.remove(e);
					i--;
				}
			}
			
			// visualisation
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("Dermathoideses: " + dermathoideses + "    Euroglyphuses: " + euroglyphuses);
			for (int j = 0; j < length; j++)  {
				System.out.println();
				for (int k = 0; k < width; k++) {
					switch(map.getStatus(new Cordinates(j,k))) {
					
					case 0:
						System.out.print("   ");	
						break;
					
					case 1:
						System.out.print(" * ");	
						break;
						
					case 7:
						System.out.print(" E ");	
						break;
						
					case 8:
						System.out.print(" D ");	
						break;
						
					case 80:
						System.out.print(" d ");	
						break;
					
					case 70:
						System.out.print(" e ");	
						break;	
						
					}       
				}    
			}	
			
			System.out.println();
			System.out.println();
			
			if (dermathoideses == 0 || euroglyphuses == 0) break;
	    }
	}
	
	public static void setSimulation() {
		map = new Map(length, width);
		
		Random rnd = new Random();
		for (int i = 0; i < dermathoideses; i++) {
			Cordinates crd = new Cordinates(rnd.nextInt(length), rnd.nextInt(width));
			
			if (map.getStatus(crd) == 0) {
				map.setStatus(crd, 8);
				mitelist.add(new Dermathogides(crd));
			}	else i--;	
		}
		
		for (int i = 0; i < euroglyphuses; i++) {
			Cordinates crd = new Cordinates(rnd.nextInt(length), rnd.nextInt(width));
			
			if (map.getStatus(crd) == 0) {
				map.setStatus(crd, 7);
				mitelist.add(new Euroglyphus(crd));
			} else i--;	
		}
		
		System.out.println("SET UP SIMULATION: "); 
		System.out.println(); 
		System.out.println("NUMBER OF DERMATHOGOIDES: " + dermathoideses); 
		System.out.println("NUMBER OF EUROGLYPHUS: " + euroglyphuses); 
		System.out.println("FOOD PER DAY " + map.getFoodPerDay()); 
	}
	
	

	public static void main(String[] args) {
		
		Simulation.setSimulation();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Simulation.runSimulation();
		
		if (dermathoideses == 0) {
			//for (int i = 0; i < length-2; i++)
				System.out.println();
			System.out.println("END OF SIMULATION EUROGLYPHUS IS ONLY SPIECE ON THE MAP");	
		} else {
			//for (int i = 0; i < length-2; i++)
			System.out.println();
		System.out.println("END OF SIMULATION DERMATHOIDES IS ONLY SPIECE ON THE MAP");	
			
		}
		

	}

}
