package project.species;

import project.Cordinates;

public interface IMite {
	public void move();
	public Cordinates getCordinates();
	public boolean layEggAbility();
	public boolean isStarved();
	public void layEgg();
	public int getType();
	public void eat();
}
