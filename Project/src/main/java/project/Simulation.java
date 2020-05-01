package project;
import project.species.Dermathogides;
import project.species.Euroglyphus;
import project.species.IMite; 
import project.eggs.Egg;
import project.eggs.IEgg;
import project.map.IMap;
import java.util.LinkedList;

public class Simulation {
	
	static IMap map;
	static LinkedList<IMite> mitelist = new LinkedList<>();
	static LinkedList<IEgg> egglist = new LinkedList<>();
	
	public static void runSimulation() {
		
		while (true) { // in the future MAXTIME
			for (IMite m : mitelist) { // alternative (int i = 0; i < mitelist.size(); i++) { (when remove object then i--)
				
				if(m.isStarved()) {
					map.setPosition(m.getCordinates(), 0);   // information to map that now this place is empty
					mitelist.remove(m); // if map would have mitelist by reference it might be good because map could remove object from list in case of "setPosition" 
					}
				
				if (m.layEggAbility()) {
					egglist.add(new Egg(m.getType(), m.getCordinates()));
					m.layEgg();	
					map.setPosition(m.getCordinates(), 0); // or mite should tell it to map
				}
				
				m.move();
			}
			
			
			for (IEgg e : egglist) { 
				
				if (e.timeToHatch()) {
					
					if (e.getType() == 0) mitelist.add( new Dermathogides(e.getCordinates() ) );
					else mitelist.add( new Euroglyphus(e.getCordinates() ) );
					//mitelist.add( (e.getType() == 0) ? new Dermathogides(e.cordinates)  : new Dermathogides(e.cordinates) )
					//(e.getType() == 0) ? mitelist.add( new Dermathogides(e.getCordinates() ) ) : mitelist.add(new Dermathogides(e.getCordinates() ) ); // convenntionaly type 0/1 is Dermathogigus/Eucthogius
					egglist.remove(e);
					map.setPosition(e.getCordinates(), 0);
				}
			}
	    }
	}	
	
	

	public static void main(String[] args) {
		
		Simulation.runSimulation();
		

	}

}
