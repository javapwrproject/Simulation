package project.species;

import java.util.Random;
import java.util.Timer;

import project.Cordinates;

public class Euroglyphus extends AMite implements IMite {
		
		Random rnd = new Random();
		
		public Euroglyphus (Cordinates cordinates) {
			this.cordinates = new Cordinates(cordinates);
			this.type = 7;
			setHealth(rnd.nextInt(10) + 10);
			setDirection(rnd.nextInt(10));
			
			this.fertility = 30; 
			this.speed = 7;
			this.strength = 5;
			
			ovulation = new OvulationCycle();
			Timer timer = new Timer();
			timer.schedule(ovulation, fertility*4*1000, fertility*4*1000);
			
		}

		@Override
		public Cordinates move() {
			
			Cordinates crd = new Cordinates (cordinates);
			
			if (rnd.nextInt(5)%4 == 0) {
				setDirection(rnd.nextInt(10));
			}
			
			int k = rnd.nextInt(4);
		
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
			//this.setHealth(-1);
			return crd;
		}
<<<<<<< HEAD
		
=======
>>>>>>> 7a8f3afb51b90ce516e814b1208c915a2081418c
		public void attack(IMite enemy) {
			enemy.setHealth(-this.strength);
		}
}
