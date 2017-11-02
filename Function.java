package de.dreiecke_zaehlen.java;

import java.awt.Point;

public class Function {

	private double m, b;
	private double a, z;
	private boolean senkrecht = false;

	public Function(double m, double b, double a, double z, boolean senkrecht) {
		// m*x + b
		// Intervall: a bis z
		this.m = m;
		this.b = b;
		this.a = a;
		this.z = z;
		this.senkrecht = senkrecht;
	}

	public static Function createFunction(Point p1, Point p2) {
		boolean senkrecht = false;
		double m = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
		if (m >= Double.MAX_VALUE) {
			// hier gilt: m = unendliche Steigung; b = X-Wert der Senkrechten;
			// und a und z geben die Y-Begrenzung an
			senkrecht = true;
			m = Double.MAX_VALUE; // dummy wert
			double b = p1.getX();
			return new Function(m, b, p1.getY(), p2.getY(), senkrecht);
		} // else oder nicht?
		double b = p1.getY() - m * p1.getX();
		// 1= a1 + b
		System.out.println(m + "*x+" + b + "von:" + p1.getX() + "bis:" + p2.getX());
		return new Function(m, b, p1.getX(), p2.getX(), senkrecht);
	}

	public boolean hasIntersection(Function func) {
		// this = func (gleichsetzen)
		// m1x+b1 = m2x+b2 --> x = (b1-b2)/(m2-m1)
		if (func.getM() - this.getM() != 0) {
			float schnittpunkt = (float) ((this.getB() - func.getB()) / (func.getM() - this.getM()));
			if (schnittpunkt >= this.getA() && schnittpunkt <= this.getZ() && schnittpunkt >= func.getA()
					&& schnittpunkt <= func.getZ()) {
				// liegt der schnittpunkt auf beiden Func
				return true;
			}
		}
		return false;

	}

	public Point calculateIntersection(Function func) {
		// berechnet den Schnittpunkt zweier Functions
		if(hasIntersection(func)) {
			float schnittpunktX = (float) ((this.getB() - func.getB()) / (func.getM() - this.getM()));
			float schnittpunktY = (float) this.getY(schnittpunktX);
			if(schnittpunktY == func.getY(schnittpunktX)) {
				return new Point((int)schnittpunktX, (int) schnittpunktY);
			}
			System.err.println("liegen doch nicht aufeinander ?");
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
