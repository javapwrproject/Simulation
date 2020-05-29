package project.species;

import java.util.TimerTask;
/**
 * Responsible of updating the ability of laying egg
 */
public class OvulationCycle  extends TimerTask {
	
	private boolean canLayEgg;
	
	@Override
	public void run() {
		canLayEgg = true;
	}
	
	public boolean layEggAbility() {
		return canLayEgg;
	}
	
	public void resetCycle() {
		canLayEgg = false;
	}

}
