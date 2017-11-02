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
		// new DarstellungFrame("dreiecke1.txt");
		// loadPoints("dreiecke1.txt");
		loadPointsTEST();
		makeFunc();
		
		System.out.println("calculateIntersection=" + funktionen.get(0).calculateIntersection(funktionen.get(1)));

	}

	private static void makeFunc() {
		for (int i = 0; i < punkte.size(); i += 2) {
			funktionen.add(Function.createFunction(punkte.get(i), punkte.get(i + 1)));
		}
	}

	private static void loadPointsTEST() {
		punkte.add(new Point(2, 3));
		punkte.add(new Point(2, 0));
		punkte.add(new Point(1, 2));
		punkte.add(new Point(3, 2));

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
