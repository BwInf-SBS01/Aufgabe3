public class Dreieck {
	
	private Punkt p1, p2, p3;
	public boolean aufeinemPunkt;
	
	public Dreieck(Funktion f1, Funktion f2, Funktion f3) {
		p1 = f1.calculateIntersection(f2);
		p2 = f1.calculateIntersection(f3);
		p3 = f2.calculateIntersection(f3);
		aufeinemPunkt = false;
		if(p1.equalsPunkt(p2) || p2.equalsPunkt(p3) || p1.equalsPunkt(p3)) {
			aufeinemPunkt = true;
		}
		
	}

	@Override
	public String toString() {
		return "Dreieck[" + this.p1 + "," + this.p2 + "," + this.p3 + "]";
	}

}
