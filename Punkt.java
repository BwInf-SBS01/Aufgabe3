package de.dreiecke_zaehlen.java;

public class Punkt {
	private float x;
	private float y;

	public Punkt(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Punkt@[x=" + x + ",y=" + y + "]";
	}
	
	public boolean equalsPunkt(Punkt p) {		
		return(this.getX() == p.getX() && this.getY() == p.getY()) ;
	}

	public java.awt.Point toAWTPoint() {
		return new java.awt.Point((int) Math.round(x), (int) Math.round(y));
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
