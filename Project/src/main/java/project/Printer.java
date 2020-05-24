package project;

import project.map.IMap;

public class Printer {
	IMap map;
	int euroglyphuses;
	int dermathoideses;
	
	public Printer (int euroglyphuses, int dermathoideses, IMap map) {
		this.euroglyphuses = euroglyphuses;
		this.dermathoideses = dermathoideses;
		this.map = map;
		
		System.out.println("SET UP SIMULATION: "); 
		System.out.println(); 
		System.out.println("NUMBER OF DERMATHOGOIDES: " + dermathoideses); 
		System.out.println("NUMBER OF EUROGLYPHUS: " + euroglyphuses); 
		System.out.println("FOOD PER DAY " + map.getFoodPerDay()); 
	}
	
	public void print() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("Dermathoideses: " + dermathoideses + "    Euroglyphuses: " + euroglyphuses);
		for (int j = 0; j < map.getLength(); j++)  {
			System.out.println();
			for (int k = 0; k < map.getWidth(); k++) {
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
	}
	
	public static void clrscr(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public void setDermathoideses(int x) {
		dermathoideses += x;
	}
	
	public void setEuroglyphuses(int x) {
		euroglyphuses += x;
	}
	
	public void summary() {
		
		if (dermathoideses == 0 && euroglyphuses == 0) {
			System.out.println();
			System.out.println("END OF SIMULATION BOTH SPIECES EXTINCT");
		} else if (dermathoideses == 0) {
			System.out.println();
			System.out.println("END OF SIMULATION EUROGLYPHUS IS ONLY SPIECE ON THE MAP");	
		} else {
			System.out.println();
			System.out.println("END OF SIMULATION DERMATHOIDES IS ONLY SPIECE ON THE MAP");		
		}	
	}

}
