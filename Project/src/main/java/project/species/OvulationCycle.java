package project.species;

import java.util.TimerTask;

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
