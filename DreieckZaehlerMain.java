package de.dreiecke_zaehlen.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DreieckZaehlerMain {

	private static List<Punkt> punkte = new ArrayList<Punkt>();
	private static List<Function> funktionen = new ArrayList<Function>();

	private static int anzahl;

	public static void main(String[] args) {
		// new DarstellungFrame("test.txt");
		loadPoints("test.txt");
		// loadPointsTEST();
		makeFunc();

		System.out.println("calculateIntersection=" + funktionen.get(0).calculateIntersection(funktionen.get(1)));

	}

	private static void makeFunc() {
		for (int i = 0; i < punkte.size() / 2; i++) {
			funktionen.add(Function.createFunction(punkte.get(2 * i), punkte.get(2 * i + 1)));
		}
	}

	private static void loadPointsTEST() {
		punkte.add(new Punkt(2, 3));
		punkte.add(new Punkt(2, 0));
		punkte.add(new Punkt(1, 2));
		punkte.add(new Punkt(3, 2));

	}

	private static int loadPoints(String file) {
		FileReader fr = null;
		try {
			fr = new FileReader(new File("src/" + file));
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(fr);

			String line;
			int anzahl = Integer.parseInt(reader.readLine());
			while ((line = reader.readLine()) != null) {
				String[] zahlen;
				zahlen = line.split(" ");
				punkte.add(new Punkt(Float.parseFloat(zahlen[0]), Float.parseFloat(zahlen[1])));
				punkte.add(new Punkt(Float.parseFloat(zahlen[2]), Float.parseFloat(zahlen[3])));
			}
			return anzahl;
		} catch (IOException e) {
			System.err.println("Couldn't load file!");
			return 0;
		}
	}
}
