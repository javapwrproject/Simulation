package project;

import project.map.IMap;
/**
 * Responsible for printing an actual situation of simulation in the console
 */
public class Printer {
	private final IMap map;
	
	public Printer (IMap map) {
		this.map = map;
		
		System.out.println("INITIAL CONDITIONS: "); 
		System.out.println();
		/*System.out.println("HEIGTH OF EXPANSE: " + map.getHeigth());
		System.out.println("WIDTH OF EXPANSE: " + map.getWidth());*/
		System.out.println("FOOD PER DAY: " + map.getFoodPerDay()); 
		System.out.println("NUMBER OF DERMATHOGOIDES: " + map.getDermathoidesesNumber()); 
		System.out.println("NUMBER OF EUROGLYPHUS: " + map.getEuroglyphusesNumber()); 
	}
	
	public void print() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("Dermathoideses: " + map.getDermathoidesesNumber() + "    Euroglyphuses: " +  map.getEuroglyphusesNumber() );
		for (int j = 0; j < map.getHeigth(); j++)  {
			System.out.println();
			for (int k = 0; k < map.getWidth(); k++) {
				switch(map.getStatus(new Cordinates(j,k))) {
				
				case EMPTY:
					System.out.print("   ");	
					break;
				
				case FOOD:
					System.out.print(" * ");	
					break;
					
				case EUROGLYPHUS:
					System.out.print(" E ");	
					break;
					
				case DERMATHOIDES:
					System.out.print(" D ");	
					break;
					
				case DERMATHOIDES_EGG:
					System.out.print(" d ");	
					break;
				
				case EUROGLYPHUS_EGG:
					System.out.print(" e ");	
					break;	
					
				}       
			}    
		}	
		
		System.out.println();
		System.out.println();
	}
	
	public static void clrscr(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	
	public void summary() {
		
		if (map.getDermathoidesesNumber() == 0 && map.getEuroglyphusesNumber() == 0) {
			System.out.println();
			System.out.print("END OF SIMULATION BOTH SPIECES EXTINCT");
		} else if (map.getDermathoidesesNumber() == 0) {
			System.out.println();
			System.out.print("END OF SIMULATION EUROGLYPHUS IS ONLY SPIECE ON THE MAP");	
		} else {
			System.out.println();
			System.out.print("END OF SIMULATION DERMATHOIDES IS ONLY SPIECE ON THE MAP");		
		}	
	}
	
	public void messegeDeathDefender(Type type) {
		System.out.println(type + " WAS ATTACKED AND KILLED");
	}
	
	public void messegeDeathAttacker(Type type) {
		System.out.println(type + " ATTACKED AND LOST");
		
	}

}
