public class Funktion {

	/**
	 * ist die Steigung bei senkrecht=false
	 */
	private float m;
	/**
	 * y-Achsenabschnitt bei senkrecht = false , x-Stelle bei senkrecht = true
	 */
	private float b;
	/**
	 * X Wert "von" bei senkrecht = false Y Wert "von" bei senkrecht = true
	 */
	private float a;
	/**
	 * X Wert "bis" bei senkrecht = false Y Wert "bis" bei senkrecht = true
	 */
	private float z;
	private boolean senkrecht = false;

	/**
	 * m*x + b Intervall: a bis z
	 */
	private Funktion(float m, float b, float x1, float x2) {
		this.m = m;
		this.b = b;
		this.a = Math.min(x1, x2);
		this.z = Math.max(x1, x2);
		this.senkrecht = false;
	}

	private Funktion(float xs, float y1, float y2) {
		// at (X); y1 ; y2
		// Senkrecht [x]= at von y1 bis y2
		this.m = 0; // ! wird nicht gebraucht
		this.b = xs; // ! dient als X Stellen Speicher
		this.a = Math.min(y1, y2); // ! dient als y Stellen Speicher
		this.z = Math.max(y1, y2); // ! dient als y Stellen Speicher
		this.senkrecht = true;
	}

	public static Funktion createFunction(Punkt p1, Punkt p2) {

		if (p1.getX() == p2.getX()) {
			// hier gilt: m = unendliche Steigung; b = X-Wert der Senkrechten;
			// und a und z geben die Y-Begrenzung an

			return new Funktion(p1.getX(), p1.getY(), p2.getY());
		} else {

			float m = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
			float b = p1.getY() - m * p1.getX();

			return new Funktion(m, b, p1.getX(), p2.getX());
		}

	}

	public Punkt calculateIntersection(Funktion func) {
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
			if (func.getA() <= this.getB() && func.getZ() >= this.getB()) {
				float schnittpunktX = this.getB();
				float schnittpunktY = func.getY((schnittpunktX));
				if (func.getY(this.getB()) >= this.getA() && func.getY(this.getB()) <= this.getZ()) {
					return new Punkt(schnittpunktX, schnittpunktY);
				}
			}
		} else if (!this.isSenkrecht() && func.isSenkrecht()) {
			// wenn nur andere Func senkrecht
			if (this.getA() <= func.getB() && this.getZ() >= func.getB()) {
				float schnittpunktX = func.getB();
				float schnittpunktY = this.getY((schnittpunktX));
				if (this.getY(func.getB()) >= func.getA() && this.getY(func.getB()) <= func.getZ()) {
					return new Punkt(schnittpunktX, schnittpunktY);
				}
			}
		}
		// sonst keine ‹berschneidung
		return null;
	}

	public float getY(double d) {

		return (float) (this.m * d + this.b);

	}

	// settters und getters
	public float getM() {
		return m;
	}

	public float getB() {
		return b;
	}

	public float getA() {
		return a;
	}

	public float getZ() {
		return z;
	}

	public boolean isSenkrecht() {
		return senkrecht;
	}
}
