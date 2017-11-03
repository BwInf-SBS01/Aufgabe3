package de.dreiecke_zaehlen.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DreieckZaehlerMain {

	private static List<Punkt> punkte = new ArrayList<Punkt>();
	private static List<Funktion> funktionen = new ArrayList<Funktion>();
	private static List<List<Funktion>> schnittFunktionen = new ArrayList<List<Funktion>>();


	public static void main(String[] args) {
		// new DarstellungFrame("test.txt");
		loadPoints("test.txt");

		makeFunc();
		calcIntersec();
		calcTriang();
		// System.out.println("calculateIntersection=" +
		// funktionen.get(0).calculateIntersection(funktionen.get(1)));

	}


	private static void calcTriang() {
		for (int i = 0; i < funktionen.size(); i++) {
			Funktion func = funktionen.get(i);
			List<Funktion>  list = schnittFunktionen.get(i); 
			for (int j = 0; j < list.size(); j++) {
				List<Funktion> list2 = schnittFunktionen.get(j);
				for (int k = 0; k < list2.size(); k++) {
					if(list.contains(list2.get(k))) {
						System.out.println("dreick");
						list2.remove(func);
						list.remove(funktionen.get(j));
					}
				}
				schnittFunktionen.add(j, list2);
			}
			schnittFunktionen.add(i,list);
		}
	}

	private static void calcIntersec() {
		for (Funktion func : funktionen) {
			List<Funktion> schnitte = new ArrayList<Funktion>();
			for (Funktion func2 : funktionen) {
				if (func.calculateIntersection(func2) != null && !func.equals(func2)) {
					schnitte.add(func2);
				}
			}
			schnittFunktionen.add(schnitte);
		}
		System.out.println(schnittFunktionen);

	}

	private static void makeFunc() {
		for (int i = 0; i < punkte.size() / 2; i++) {
			funktionen.add(Funktion.createFunction(punkte.get(2 * i), punkte.get(2 * i + 1)));
		}
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
