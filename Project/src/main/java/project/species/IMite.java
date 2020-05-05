package project.species;

import project.Cordinates;

public interface IMite {
	public Cordinates move();
	public Cordinates getCordinates();
	public boolean layEggAbility();
	public boolean isStarved();
	public void layEgg();
	public int getType();
	public void eat();
	public int getHealth();
	public void setHealth(int vital);
	public void attack(IMite enemy);
	
}
