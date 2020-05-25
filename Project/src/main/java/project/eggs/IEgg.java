package project.eggs;

import project.Cordinates;
import project.Type;

public interface IEgg {
	boolean timeToHatch();
	Type getType();
	Cordinates getCordinates();

}
