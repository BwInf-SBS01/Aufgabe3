package de.dreiecke_zaehlen.java;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DreieckZaehlerMain {

	private static List<Point> punkte = new ArrayList<Point>();
	private static List<Function> funktionen = new ArrayList<Function>();
	
	private static int anzahl;

	public static void main(String[] args) {
		new DarstellungFrame("dreiecke1.txt");
		loadPoints("dreiecke1.txt");
		// testeDreiecke();
		maker();
		analyser();
	}

	public static void testeDreiecke() {
		punkte.add(new Point(1, 1));
		punkte.add(new Point(2, 2));
		punkte.add(new Point(1, 2));
		punkte.add(new Point(2, 1));

	}

	private static void maker() {
//		for (int i = 0; i < punkte.size() / 2; i++) {
//			strecken.add(Strecke.machStrecke(punkte.get(2 * i), punkte.get(2 * i + 1)));
//		}
		for (int i = 0; i < punkte.size(); i++) {
			nullVecs.add(new Vector2D(punkte.get(i).getX(), punkte.get(i).getY()));
		}

	}

	private static void analyser() {
//		for (int i = 0; i < strecken.size(); i++) {
//			for (int j = 0; j < strecken.size(); j++) {
//				// berechneSP(strecken.get(i), strecken.get(j));
//			}
//		}
		
		for (int i = 0; i < (nullVecs.size()/2)-2; i++) {
			for (int j = i+1; j < nullVecs.size()/2; j++) {
				// berechneSP(nullVecs.get(2*i), nullVecs.get(2*i+1), nullVecs.get(2*j), nullVecs.get(2*j+1));
				System.out.println(Double.toString(nullVecs.get(2*i).getX()) + " " + Double.toString(nullVecs.get(2*i+1).getX()) + " "  + Double.toString(nullVecs.get(2*j).getX()) + " "  + Double.toString(nullVecs.get(2*j+1).getX()));
			}
		}

	}

	private static int loadPoints(String file) {
		FileReader fr = null;
		try {
			fr = new FileReader(new File("rsc/" + file));
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(fr);

			String line;
			int anzahl = Integer.parseInt(reader.readLine());
			while ((line = reader.readLine()) != null) {
				String[] zahlen;
				zahlen = line.split(" ");
				punkte.add(new Point((int) Float.parseFloat(zahlen[0]), (int) Float.parseFloat(zahlen[1])));
				punkte.add(new Point((int) Float.parseFloat(zahlen[2]), (int) Float.parseFloat(zahlen[3])));
			}
			return anzahl;
		} catch (IOException e) {
			System.err.println("Couldn't load file!");
			return 0;
		}
	}
}
