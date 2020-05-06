package project.map;

import project.Cordinates;

public interface IMap {
	void setStatus(Cordinates cordinates, int status); // Cordinaates is type ~Pair
	public int getStatus(Cordinates cordinates);
	public void getDimensions();
	public int getFoodPerDay();

}