package project.map;

import project.Cordinates;

public interface IMap {
	void setStatus(Cordinates cordinates, int status); 
	public int getStatus(Cordinates cordinates);
	public void getDimensions();
	public int getFoodPerDay();
	public int getLength();
	public int getWidth();

}