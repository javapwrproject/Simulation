package project.species;

import java.util.Timer;
import project.Cordinates;

public class Dermathogides extends AMite implements IMite {
	
	
	public Dermathogides (Cordinates cordinates) {
		this.type = 0;
		this.fertility = 8; // species differentiation factor
		this.health = 20;
		this.speed = 10;
		this.strength = 3;
		this.cordinates = cordinates;
		
		ovulation = new OvulationCycle();
		Timer timer = new Timer();
		timer.schedule(ovulation,5*1000, fertility*5*1000);
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}


}
