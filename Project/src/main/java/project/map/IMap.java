package project.map;

import project.Cordinates;
import project.Type;

public interface IMap {
	void setStatus(Cordinates cordinates, Type status); 
	public Type getStatus(Cordinates cordinates);
	public int getFoodPerDay();
	public int getLength();
	public int getWidth();

}