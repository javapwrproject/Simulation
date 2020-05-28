package project.map;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import project.Cordinates;
import project.Type;

public class Map extends TimerTask implements IMap{
	
	private final int Heigth; 
	private final int Width;
	private final int foodPerDay;
	
	 Type[][] expanse = new Type[100][100]; 
	
	Map (int foodPerDay, int x, int y) {
		for (int i = 0; i < 100; i++)
			 for (int j = 0; j < 100; j++)
				 expanse[i][j] = Type.EMPTY;
		
		this.foodPerDay = foodPerDay;
		Heigth = x;
		Width = y;
		Timer timer = new Timer();
		timer.schedule(this, 100, 15*1000);
	}
	
	public Map (int x, int y) {
		for (int i = 0; i < 100; i++)
			 for (int j = 0; j < 100; j++)
				 expanse[i][j] = Type.EMPTY;
		
		Heigth = x;
		Width = y;
		foodPerDay = 100;
		Timer timer = new Timer();
		timer.schedule(this, 100, 15*1000);
	}
	 
	private void foodGenerate(int N) {
		Random rnd = new Random();
		
		int controler = 0;
		for (int i = 0; i < N; i++) {
			
			int x = rnd.nextInt(Heigth);
			int y = rnd.nextInt(Width);
			
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
		return this.Heigth;
	}
	
	public int getWidth() {
		return this.Width;
	}
	
	public int getFoodPerDay() {
		return foodPerDay;
	}
	
}
