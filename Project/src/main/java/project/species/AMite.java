package project.species;
import project.Cordinates;

public abstract class AMite {
	int type; // char type ?
	// can be mood by user maybe but not now
	int health;
	int strength;
	int speed;
	int fertility;
	//
	OvulationCycle ovulation;
	Cordinates cordinates;
	
	public Cordinates getCordinates() {
		return cordinates;
	}
	
	public boolean layEggAbility() {
		return ovulation.canLayEgg;
	}
	
	public boolean isStarved() {
		return (health <=0);
	}
	
	public int getType() {
		return type;
	}
	
	public void layEgg() {
		ovulation.resetCycle();
	}
	
	public void eat() {
		health++; // in the future colaboration with map
	}
	
}	
