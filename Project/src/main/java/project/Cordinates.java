package project;

public class Cordinates {
	private int x;
	private int y;
	
	Cordinates (int x, int y) {
		this.x  = x;
		this.y = y;
	}
	
	public Cordinates (Cordinates cordinates) {
		this.x  = cordinates.getX();
		this.y = cordinates.getY();
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Cordinates getCordinates() {
		return this;
	}
	
	public void setCordinates(int x, int y) {
		this.x  = x;
		this.y = y;
	}
}
