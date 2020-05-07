package project.species;

import java.util.Random;
import java.util.Timer;
import project.Cordinates;
import project.Simulation;

public class Dermathogides extends AMite implements IMite {
	
	Random rnd = new Random();
	
	public Dermathogides (Cordinates cordinates) {
		this.cordinates = new Cordinates(cordinates);
		this.type = 8;
		setHealth(rnd.nextInt(10) + 40);
		setDirection(rnd.nextInt(10));
		
		this.fertility = 6; // MAX 10
		this.strength = 9;
		this.speed = 2;
		
		ovulation = new OvulationCycle();
		Timer timer = new Timer();
		timer.schedule(ovulation, (10 - fertility)*6*1000, (10 - fertility)*6*1000);
		
	}

	@Override
	public Cordinates move() {
		
		Cordinates crd = new Cordinates (cordinates);
		
		for (int i = 0; i < 100; i++) { // looking food mood
			int x = rnd.nextInt(3) - 1;
			int y = rnd.nextInt(3) - 1;
			crd.modCordinates(x,y);
			
			if (crd.getX() >= 0 && crd.getX() < Simulation.getMap().getLength() && crd.getY() >= 0 && crd.getY() < Simulation.getMap().getWidth() && Simulation.getMap().getStatus(crd) == 1)
				return crd;	
			
			crd = new Cordinates (cordinates);
		} 	
		
		if (rnd.nextInt(5)%4 == 0) {
			setDirection(rnd.nextInt(10));
		}
		
		int k = rnd.nextInt(speed) + 1;
	
		switch (getDirection() ) {
			case 1: {
				crd.modCordinates(-1*k, -1*k);
				break;
			}	
			case 2:
				crd.modCordinates(-1*k, 0*k);
				break;
			case 3:
				crd.modCordinates(-1*k, 1*k);
				break;
			case 4:
				crd.modCordinates(0*k, -1*k);
				break;
			case 5:
				crd.modCordinates(0*k, 0*k);
				break;
			case 6:
				crd.modCordinates(0*k, 1*k);
				break;
			case 7:
				crd.modCordinates(1*k, -1*k);
				break;
			case 8:
				crd.modCordinates(1*k, 0*k);
				break;
			case 9:
				crd.modCordinates(1*k, 1*k);
				break;
				
			default: {
				crd.modCordinates(0*k, 0*k);
				break;
				}
		}
		this.setHealth(-1);
		return crd;
	}

	public void attack(IMite enemy) {
		enemy.setHealth(-this.strength);
	}

}
