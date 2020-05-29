package project;
/**
 * Represents the location on the Cartesian plane
 */
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void set(Cordinates crd) {
		this.x = crd.getX();
		this.y = crd.getY();
	}
	
	public Cordinates getCordinates() {
		return this;
	}
	
	public void modCordinates(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public boolean equals (Cordinates c) {
		return (c.getX() == this.x && c.getY() == this.y);
	}
	
}
