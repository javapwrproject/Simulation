package project.species;
import project.Cordinates;
import project.Type;

public abstract class Mite {
	Type type;
	Type eggType;
	private int direction; 
	private int health;
    int fertility;
	int strength;
	int speed;

	OvulationCycle ovulation;
	Cordinates cordinates;
	
	public Cordinates getCordinates() {
		return cordinates;
	}
	
	public boolean layEggAbility() {
		return ovulation.layEggAbility();
	}
	
	public boolean isStarved() {
		return (health <=0);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public Type getType() {
		return type;
	}
	
	public Type getEggType() {
		return eggType;
	}
	
	public void damage(int vital) {
		health -= vital; 
	}
	
	public void layEgg() {
		ovulation.resetCycle();
	}
	
	public void eat() {
		health += 10;
	}
	int getDirection() {
		return direction;
	}
	
	void setDirection(int direction) {
		this.direction = direction;
	}
	
}	
