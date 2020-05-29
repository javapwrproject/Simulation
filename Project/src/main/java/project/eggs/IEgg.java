package project.eggs;

import project.Cordinates;
import project.Type;
/**
 * Allow to get information about hatching's state and type of hatched mite
 */
public interface IEgg {
	boolean timeToHatch();
	Type getType();
	Cordinates getCordinates();

}
