package project;
import package project.species; // issues with packages
import package project.eggs;
import package project.map;
import java.util.LinkedList;
//import javafx.util;

public class Simulation {
	
	IMap map;
	LinkedList<IMite> mitelist = new LinkedList<>();
	LinkedList<IEgg> egglist = new LinkedList<>();
	
	public static void runSimulation() {
		
		while (true) { // in the future MAXTIME
			for (int i = 0; i < mitelist.size; i++) {  // alternative (IMites m : mitelist)
				IMite m;
				m = mitelist[i];
			
				if(m.isStarved) {
					map.setStatus();   // information to map that since now this place is empty
					mitelist.remove(i); // if map would have list by reference it might be good because map could remove object from list in case of "setPosition" 
					i--;
					}
				if (m.getLayEggAbillity()) {
					egglist.add(new Egg(m.getType()), m.getCordinates);
					m.layEgg();	
					map.setStatus(); // or mite should tell it to map
				}
				
				m.move();
			}
			
			
			for (int i = 0; i < egglist.size; i++) {
				IEgg e;
				e = egglist[i];
				
				if(e.eggReady) {
					(e.getType == 0) ? mitelist.add(new Derm(e.cordinates) ) : mitelist.add(new Euct(e.cordinates) ) // convenntionaly type 0/1 is Dermathogigus/Eucthogius
					egglist.remove(i);
					i--;
					map.setPosition();
				}
			}
	    }
	}	
	
	

	public static void main(String[] args) {
		
		Simulation.runSimulation();
		

	}

}
