package project.map;

import project.Cordinates;

public interface IMap {
	void setPosition(Cordinates cordinates, int status); // Cordinaates is type ~Pair
	public int getStatus(Cordinates cordinates);

}