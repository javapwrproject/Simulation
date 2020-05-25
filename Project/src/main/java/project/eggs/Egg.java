package project.eggs;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import project.Cordinates;
import project.Type;

public class Egg extends TimerTask implements IEgg{
	
	private Type type;
	private boolean hatch;
	private Cordinates cordinates;	
	
	Random rnd = new Random();
	int time = rnd.nextInt(5000);
	
	public Egg(Type type, Cordinates cordinates) {
		
	Timer timer = new Timer();
	timer.schedule(this, (10*1000 + time));
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
	
	public Type getType() {
		return this.type;
	}
	
	public Cordinates getCordinates() {
		return cordinates;
	}
	
	
}
