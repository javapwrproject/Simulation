package project.map;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import project.Cordinates;
import project.Type;
/**
 * Stores information about each field and periodically generate the food 
 */
public class Map extends TimerTask implements IMap{
	
	private final int height; 
	private final int width;
	private final int foodPerDay;
	
	 Type[][] expanse = new Type[100][100]; 
	
	public Map (int x, int y) {
		for (int i = 0; i < 100; i++)
			 for (int j = 0; j < 100; j++)
				 expanse[i][j] = Type.EMPTY;
		
		height = x;
		width = y;
		foodPerDay = 100;
		Timer timer = new Timer();
		timer.schedule(this, 100, 15*1000);
	}
	 
	private void foodGenerate(int N) {
		Random rnd = new Random();
		
		int controler = 0;
		for (int i = 0; i < N; i++) {
			
			int x = rnd.nextInt(height);
			int y = rnd.nextInt(width);
			
			if (expanse[x][y] == Type.EMPTY) {
				expanse[x][y] = Type.FOOD;
			} else { 
				i--;
				controler++;
			}
			if (controler > 10*N) break;
		}
		
	}

	@Override
	public void setStatus(Cordinates cordinates, Type status) {
		expanse[cordinates.getX()][cordinates.getY()] = status;
		
	}

	@Override
	public Type getStatus(Cordinates cordinates) {
		return expanse[cordinates.getX()][cordinates.getY()];
	}

	@Override
	public void run() {
		foodGenerate(foodPerDay);
	}
	
	public int getHeigth() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getFoodPerDay() {
		return foodPerDay;
	}
	
	public int getEuroglyphusesNumber() {
		int number = 0;
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (expanse[i][j] == Type.EUROGLYPHUS) number++;
		return number;
	}
	
	public int getDermathoidesesNumber() {
		int number = 0;
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (expanse[i][j] == Type.DERMATHOIDES) number++;
		return number;
	}
	
	public boolean isOnMap(Cordinates crd) {
		return crd.getX() > 0 && crd.getX() < height && crd.getY() > 0 && crd.getY() < width;
	}
	
	public void adjustCordinates(Cordinates crd) {
		if (crd.getX() < 0) crd.setX(0);
		if (crd.getX() >= height) crd.setX(height - 1);
		if (crd.getY() < 0) crd.setY(0);
		if (crd.getY() >= width) crd.setY(width - 1);
		
	}
}
