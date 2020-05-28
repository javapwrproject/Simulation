package project.map;

import project.Cordinates;
import project.Type;

public interface IMap {
	void setStatus(Cordinates cordinates, Type status); 
	public Type getStatus(Cordinates cordinates);
	public int getFoodPerDay();
	public int getHeigth();
	public int getWidth();
	public int getEuroglyphusesNumber();
	public int getDermathoidesesNumber();
	public boolean isOnMap(Cordinates crd);
	public void adjustCordinates(Cordinates crd);

}