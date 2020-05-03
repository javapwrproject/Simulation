package project.species;

import java.util.Random;
import java.util.Timer;
import project.Cordinates;

public class Dermathogides extends AMite implements IMite {
	
	Random rnd = new Random();
	
	public Dermathogides (Cordinates cordinates) {
		this.type = 0;
		setHealth(20);
		this.fertility = 8; // species differentiation factor
		this.speed = 10;
		this.strength = 3;
		this.cordinates = new Cordinates(cordinates);
		
		ovulation = new OvulationCycle();
		Timer timer = new Timer();
		timer.schedule(ovulation,5*1000, fertility*5*1000);
		
	}

	@Override
	public void move() {
		
		if (rnd.nextInt(5)%4 == 8) {
			setDirection(rnd.nextInt(10));
		}
		
		int k = rnd.nextInt(4);
	
		switch (getDirection() ) {
			case 1: {
				cordinates.setCordinates(-1*k, -1*k);
			}	
			case 2:
				cordinates.setCordinates(-1*k, 0*k);
			case 3:
				cordinates.setCordinates(-1*k, 1*k);
			case 4:
				cordinates.setCordinates(0*k, -1*k);
			case 5:
				cordinates.setCordinates(0*k, 0*k);
			case 6:
				cordinates.setCordinates(0*k, 1*k);
			case 7:
				cordinates.setCordinates(1*k, -1*k);
			case 8:
				cordinates.setCordinates(1*k, 0*k);
			case 9:
				cordinates.setCordinates(1*k, 1*k);
				
			default: {
				cordinates.setCordinates(0*k, 0*k);	
				}
		}
		this.setHealth(-1);
	}


}
