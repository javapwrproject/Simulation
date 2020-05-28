package project.species;

import java.util.Random;

import java.util.Timer;
import project.Cordinates;
import project.Type;
import project.map.IMap;

public class Euroglyphus extends Mite implements IMite {
		
		Random rnd = new Random();
		
		public Euroglyphus (Cordinates cordinates) {
			this.cordinates = new Cordinates(cordinates);
			this.type = Type.EUROGLYPHUS;
			this.eggType = Type.EUROGLYPHUS_EGG;
			setHealth(rnd.nextInt(10) + 33);
			setDirection(rnd.nextInt(10));
			
			this.fertility = 7; 
			this.strength = 5;
			this.speed = 3;
			
			ovulation = new OvulationCycle();
			Timer timer = new Timer();
			timer.schedule(ovulation, (10 - fertility)*6*1000, (10 - fertility)*6*1000);
			
		}

		@Override
		public Cordinates move(IMap map) {
			
			Cordinates crd = new Cordinates (cordinates);
			
			for (int i = 0; i < 100; i++) {
				int x = rnd.nextInt(3) - 1;
				int y = rnd.nextInt(3) - 1;
				crd.modCordinates(x,y);
				
				if (map.isOnMap(crd) && map.getStatus(crd) == Type.FOOD)
					return crd;	
				
				crd = new Cordinates (cordinates);
			} 	
			
			if (rnd.nextInt(5)%4 == 0) {
				setDirection(rnd.nextInt(10));
			}
			
			int k = rnd.nextInt(speed);
		
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
			this.damage(1);
			
			map.adjustCordinates(crd);
			return crd;
		}
		
		public void attack(IMite enemy) {
			enemy.damage(this.strength);
		}
}
