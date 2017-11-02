package de.dreiecke_zaehlen.java;

import java.awt.Point;

public class Function {

	private double m, b;
	private double a, z;
	private boolean senkrecht = false;

	private Function(double m, double b, double a, double z) {
		// m*x + b
		// Intervall: a bis z
		this.m = m;
		this.b = b;
		this.a = a;
		this.z = z;
		this.senkrecht = false;
	}
	private Function(double at, double a, double z) {
		// at (X); y1 ; y2
		this.m = 0;
		this.b = at;
		this.a = a;
		this.z = z;
		this.senkrecht = true;
	}
	

	public static Function createFunction(Point p1, Point p2) {

		if (p1.getX() == p2.getX()) {
			// hier gilt: m = unendliche Steigung; b = X-Wert der Senkrechten;
			// und a und z geben die Y-Begrenzung an

			System.out.println("senkrecht from: " + p1.getY() + " bis: " + p2.getY() + " @x= " + p1.getX());
			return new Function(p1.getX(), p1.getY(), p2.getY());
		} else {
			
			double m = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX()); // else oder nicht?
			double b = p1.getY() - m * p1.getX();
			// 1= a1 + b
			System.out.println(m + "*x+" + b + "von:" + p1.getX() + "bis:" + p2.getX());
			return new Function(m, b, p1.getX(), p2.getX());
		}

	}

	

	public Point calculateIntersection(Function func) {
		// berechnet den Schnittpunkt zweier Functions

		if ((!this.isSenkrecht()) && (!func.isSenkrecht())) {
			// wenn zwei normale Funktionen
			if (func.getM() - this.getM() != 0) {
				float schnittpunktX = (float) ((this.getB() - func.getB()) / (func.getM() - this.getM()));
				float schnittpunktY = (float) this.getY(schnittpunktX);
				if (schnittpunktX >= this.getA() && schnittpunktX <= this.getZ() && schnittpunktX >= func.getA()
						&& schnittpunktX <= func.getZ()) {
					// liegt der schnittpunkt auf beiden Func
					return new Point((int) schnittpunktX, (int) schnittpunktY);
				}
			}
		}
		System.err.println("achtung senkrecht!");
		if (this.isSenkrecht() && !func.isSenkrecht()) {
			// wenn diese Func senkrecht
			
		}

		return null;
	}

	public float getY(float x) {

		return (float) (this.m * x + this.b);

	}

	// settters und getters
	public double getM() {
		return m;
	}

	public double getB() {
		return b;
	}

	public double getA() {
		return a;
	}

	public double getZ() {
		return z;
	}

	public boolean isSenkrecht() {
		return senkrecht;
	}

}
