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
<<<<<<< HEAD
	public int getHealth();
	public void setHealth(int vital);
	public void attack(IMite enemy);
	
}
=======
	public void setHealth(int value);
	public int getHealth();
	public void attack(IMite enemy);
}
>>>>>>> 7a8f3afb51b90ce516e814b1208c915a2081418c
