package project.species;

import project.Cordinates;
import project.Type;
import project.map.IMap;
/** 
 * Have declarations of general mite behaviors
 */
public interface IMite {
	
	public Cordinates move(IMap map);
	public Cordinates getCordinates();
	public boolean layEggAbility();
	public boolean isStarved();
	public void layEgg();
	public Type getType();
	public Type getEggType();
	public void eat();
	public int getHealth();
	public void damage(int vital);
	public void attack(IMite enemy);
	
}


