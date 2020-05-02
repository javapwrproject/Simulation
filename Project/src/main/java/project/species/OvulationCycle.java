package project.species;

import java.util.TimerTask;
import java.util.Timer;

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
