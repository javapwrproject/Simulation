package project.map;

import java.util.ArrayList;
import java.util.HashMap;
import project.Cordinates;

public class Map implements IMap{
	
	// HashMap<Cordinates, Integer> expanse = new HashMap<>(); Alternative forms might be better
	// ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	
	 Integer[][] expanse = new Integer[100][100]; // dimensions
	
	void foodGenerate() {
		
	}

	@Override
	public void setStatus(Cordinates cordinates, int status) {
		expanse[cordinates.getX()][cordinates.getY()] = status;
		
	}

	@Override
	public int getStatus(Cordinates cordinates) {
		return expanse[cordinates.getX()][cordinates.getY()];
	}
	

}
