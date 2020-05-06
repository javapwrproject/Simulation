package project.eggs;

import java.util.Timer;
import java.util.TimerTask;
import project.Cordinates;

public class Egg extends TimerTask implements IEgg{
	
	private int type;
	private boolean hatch;
	private Cordinates cordinates;	
	
	public Egg(int type, Cordinates cordinates) {
	Timer timer = new Timer();
	timer.schedule(this, 10*1000);
	this.type = type;
	this.cordinates = new Cordinates(cordinates); 
	}
	
	@Override
	public void run() {
		hatch = true;
	}
	
	public boolean timeToHatch() {
		return hatch;
	}
	
	public int getType() {
		return this.type;
	}
	
	public Cordinates getCordinates() {
		return cordinates;
	}
	
	
}
