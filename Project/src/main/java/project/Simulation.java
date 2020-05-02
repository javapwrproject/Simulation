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

public class Simulation {
	
	static IMap map;
	static LinkedList<IMite> mitelist = new LinkedList<>();
	static LinkedList<IEgg> egglist = new LinkedList<>();
	
	public static void runSimulation() {
		
		while (true) { // MAXTIME
			for (IMite m : mitelist) { // alternative (int i = 0; i < mitelist.size(); i++) { (when remove object then i--)
				
				if(m.isStarved()) {
					map.setStatus(m.getCordinates(), 0);   // information to map that now this place is empty
					mitelist.remove(m); // if map would have mitelist by reference it might be good because map could remove object from list in case of "setPosition" 
					}
				
				if (m.layEggAbility()) {
					egglist.add(new Egg(m.getType(), m.getCordinates()));
					m.layEgg();	
					map.setStatus(m.getCordinates(), 0); // or mite should tell it to map
				}
				
				while(true) {
					m.move();
					switch (map.getStatus(m.getCordinates() ) ) {
						case 0: { // empty
							map.setStatus(m.getCordinates(), 8);
							break;
						}
						case 1: { // food
							m.eat();
							map.setStatus(m.getCordinates(), 8);
							break;
						}
						case 2: { // egg
							m.move();
						}
						case 3: { // egg other specimen this case to modife in future
							m.move();
						}
						case 4: { // other speciemen
							m.move(); //in future some inkrementator to work on "surrended state"
						}		
						case 5: { // same speciemen
							m.move(); //in future some inkrementator to work on "surrended state"
						}		
					}
				}
				
			}
			
			
			for (IEgg e : egglist) { 
				
				if (e.timeToHatch()) {
					//mitelist.add( (e.getType() == 0) ? new Dermathogides(e.cordinates)  : new Dermathogides(e.cordinates) ) // altegnative
					//(e.getType() == 0) ? mitelist.add( new Dermathogides(e.getCordinates() ) ) : mitelist.add(new Dermathogides(e.getCordinates() ) ); // convenntionaly type 0/1 is Dermathogigus/Eucthogius
					if (e.getType() == 8) mitelist.add( new Dermathogides(e.getCordinates() ) );
					else mitelist.add( new Euroglyphus(e.getCordinates() ) );
					egglist.remove(e);
					map.setStatus(e.getCordinates(), 0);
				}
			}
	    }
	}
	
	public static void setSimulation(int d, int e, int x, int y) {
		map = new Map();
		
		Random rnd = new Random();
		for (int i = 0; i < d; i++) {
			Cordinates crd = new Cordinates(rnd.nextInt(x), rnd.nextInt(y));
			
			if (map.getStatus(crd) ==0)
				map.setStatus(crd, 8);
			else i--;	
		}
		
		for (int i = 0; i < e; i++) {
			Cordinates crd = new Cordinates(rnd.nextInt(x), rnd.nextInt(y));
			
			if (map.getStatus(crd) ==0)
				map.setStatus(crd, 7);
			else i--;	
		}
		
	}
	
	

	public static void main(String[] args) {
		
		Simulation.runSimulation();
		

	}

}
