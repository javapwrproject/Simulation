package project.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import project.Cordinates;

public class Map extends TimerTask implements IMap{
	
	// HashMap<Cordinates, Integer> expanse = new HashMap<>(); Alternative forms might be better
	// ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	Map (int foodPerDay, int x, int y) {
		this.foodPerDay = foodPerDay;
		X = x;
		Y = y;
		Timer timer = new Timer();
		timer.schedule(this, 100, 3*10000);
	}
	
	public Map (int x, int y) {
		X = x;
		Y = y;
		foodPerDay = 5;
		Timer timer = new Timer();
		timer.schedule(this, 100, 15*1000);
	}
	
	private int X; // dimensions
	private int Y;
	final private int foodPerDay;
	
	 int[][] expanse = new int[100][100];
	 
	 
	private void foodGenerate(int N) {
		Random rnd = new Random();
		
		int controler = 0;
		for (int i = 0; i < N; i++) {
			
			int x = rnd.nextInt(X);
			int y = rnd.nextInt(Y);
			
			if (expanse[x][y] == 0) {
				expanse[x][y] = 1;
			} else { 
				i--;
				controler++;
			}
			if (controler > 10*N) break;
		}
		
	}

	@Override
	public void setStatus(Cordinates cordinates, int status) {
		expanse[cordinates.getX()][cordinates.getY()] = status;
		
	}

	@Override
	public int getStatus(Cordinates cordinates) {
		return expanse[cordinates.getX()][cordinates.getY()];
	}

	@Override
	public void run() {
		foodGenerate(foodPerDay);
		
	}
	
	public void getDimensions() {
		System.out.println(X + " " + Y);
	}
	
	public int getFoodPerDay() {
		return foodPerDay;
	}
	
}
