package de.dreiecke_zaehlen.java;

public class Function {

	/**
	 * Steigung bei senkrecht = false
	 */
	private double m;
	/**
	 * y-Achsenabschnitt bei senkrecht = false x-Stelle bei senkrecht = true
	 */
	private double b;
	/**
	 * X Wert "von" bei senkrecht = false Y Wert "von" bei senkrecht = true
	 */
	private double a;
	/**
	 * X Wert "bis" bei senkrecht = false Y Wert "bis" bei senkrecht = true
	 */
	private double z;
	private boolean senkrecht = false;

	private Function(double m, double b, double x1, double x2) {
		// m*x + b
		// Intervall: a bis z
		this.m = m;
		this.b = b;
		this.a = Math.min(x1, x2);
		this.z = Math.max(x1, x2);
		this.senkrecht = false;
	}

	private Function(double at, double y1, double y2) {
		// at (X); y1 ; y2
		// Senkrecht [x]= at von y1 bis y2
		this.m = 0; // ! wird nicht gebraucht
		this.b = at; // ! dient als X Stellen Speicher
		this.a = Math.min(y1, y2); // ! dient als y Stellen Speicher
		this.z = Math.max(y1, y2); // ! dient als y Stellen Speicher
		this.senkrecht = true;
	}

	public static Function createFunction(Punkt p1, Punkt p2) {

		if (p1.getX() == p2.getX()) {
			// hier gilt: m = unendliche Steigung; b = X-Wert der Senkrechten;
			// und a und z geben die Y-Begrenzung an

			System.out.println("SENKRECHTE" + " @x= " + p1.getX() + "  von:" + p1.getY() + " bis:" + p2.getY());
			return new Function(p1.getX(), p1.getY(), p2.getY());
		} else {

			double m = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX()); // else oder nicht?
			double b = p1.getY() - m * p1.getX();
			// 1= a1 + b
			System.out.println("STRECKE "+ m + "*x+" + b + "  von:" + p1.getX() + "bis:" + p2.getX());
			return new Function(m, b, p1.getX(), p2.getX());
		}

	}

	public Punkt calculateIntersection(Function func) {
		// berechnet den Schnittpunkt zweier Functions

		if ((!this.isSenkrecht()) && (!func.isSenkrecht())) {
			// wenn zwei normale Funktionen
			if (func.getM() - this.getM() != 0) {
				float schnittpunktX = (float) ((this.getB() - func.getB()) / (func.getM() - this.getM()));
				float schnittpunktY = (float) this.getY(schnittpunktX);
				if (schnittpunktX >= this.getA() && schnittpunktX <= this.getZ() && schnittpunktX >= func.getA()
						&& schnittpunktX <= func.getZ()) {
					// liegt der schnittpunkt auf beiden Func
					return new Punkt(schnittpunktX, schnittpunktY);
				}
			}
		}

		if (this.isSenkrecht() && !func.isSenkrecht()) {
			// wenn nur diese Func senkrecht
			System.out.println("achtung senkrecht!");
			if (func.getA() < this.getB() && func.getZ() > this.getB()) {
				float schnittpunktX = (float) this.getB();
				float schnittpunktY = (float) func.getY((schnittpunktX));
				if (func.getY(this.getB()) > this.getA() && func.getY(this.getB()) < this.getZ()) {
					return new Punkt(schnittpunktX, schnittpunktY);
				}
			}
		} else if (!this.isSenkrecht() && func.isSenkrecht()) {
			// wenn nur andere Func senkrecht
			System.out.println("achtung senkrecht!");
			if (this.getA() < func.getB() && this.getZ() > func.getB()) {
				float schnittpunktX = (float) func.getB();
				float schnittpunktY = (float) this.getY((schnittpunktX));
				if (this.getY(func.getB()) > func.getA() && this.getY(func.getB()) < func.getZ()) {
					return new Punkt(schnittpunktX, schnittpunktY);
				}
			}
		}

		return null;
	}

	public float getY(double d) {

		return (float) (this.m * d + this.b);

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
