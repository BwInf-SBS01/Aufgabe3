import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DreieckZaehlerMain {

  private static List<Punkt> punkte = new ArrayList<Punkt>();
  private static List<Funktion> funktionen = new ArrayList<Funktion>();
  private static List<List<Funktion>> schnittFunktionen = new ArrayList<List<Funktion>>();
  private static List<Dreieck> dreiecke = new ArrayList<Dreieck>();

  public static void main(String[] args) {
    new DarstellungFrame();
  }

  public static void zaehleUndStelleDar(int fileNR) {
    // wird von dem Frame aufgerufen, damit die Listen von Punkten und Dreiecken aktualisiert werden.
    punkte.clear();
    funktionen.clear();
    schnittFunktionen.clear();
    dreiecke.clear();
    loadPoints("dreiecke" + fileNR + ".txt");
    makeFunc();
    calcIntersec();
    calcTriang();
    System.out.println(dreiecke.size() + " Dreiecke in dreiecke" + fileNR + ".txt");
    writeDreiecke(fileNR);
  }

  private static void calcTriang() {
    //berechnet mit Hilfe der Listen der Funktionen und Schnittfunktionen die Dreicke und speichert diese in der Dreiecks Liste. 
    for (int i = 0; i < funktionen.size(); i++) {
      Funktion func1 = funktionen.get(i);
      List<Funktion> list = getSchnittFunc(func1);
      for (int j = 0; j < list.size(); j++) {
        Funktion func2 = list.get(j);
        List<Funktion> list2 = getSchnittFunc(func2);
        list2 = removeFuncFromList(func1, list2);
        for (int k = 0; k < list2.size(); k++) {
          Funktion func3 = list2.get(k);
          List<Funktion> list3 = getSchnittFunc(func3);
          for (int l = 0; l < list3.size(); l++) {
            if (list3.get(l).equals(func1)) {
              Dreieck dreieck = new Dreieck(func1, func2, func3);
              if (!dreieck.isAufeinemPunkt()) {
                dreiecke.add(dreieck);
              }
            }
          }
        }
      }
    }
  }

  private static List<Funktion> getSchnittFunc(Funktion f) {
    //Hilfsmethode der calcTrinag() Methode: Sucht zu einer bestimmten Funktion die Schnittfunktionslise heraus.
    for (int i = 0; i < funktionen.size(); i++) {
      if (funktionen.get(i).equals(f)) {
        return schnittFunktionen.get(i);
      }
    }
    return null;
  }

  private static List<Funktion> removeFuncFromList(Funktion rev, List<Funktion> list) {
    //Hilfsmethode der calcTrinag() Methode: Entfernt eine Funktion aus einer übergebenen Liste.
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).equals(rev)) {
        list.remove(i);
      }
    }
    return list;
  }

  private static void calcIntersec() {
    //Erstellt für jede Funktion eine Liste von Schnittfunktionen
    for (Funktion func : funktionen) {
      List<Funktion> schnitte = new ArrayList<Funktion>();
      for (Funktion func2 : funktionen) {
        if (func.calculateIntersection(func2) != null && !func.equals(func2)) {
          schnitte.add(func2);
        }
      }
      schnittFunktionen.add(schnitte);
    }
  }

  private static void makeFunc() {
    //Erstellt aus der Liste von Punkten eine Liste aus Funktionen
    for (int i = 0; i < punkte.size() / 2; i++) {
      funktionen.add(Funktion.createFunction(punkte.get(2 * i), punkte.get(2 * i + 1)));
    }
  }

  private static int loadPoints(String file) {
    //Lädt die Punkte aus der passenden Datei.
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(file)));
      String line;
      int anzahl = Integer.parseInt(reader.readLine());
      while (((line = reader.readLine()) != null) && (!line.isEmpty())) {
        String[] zahlen;
        zahlen = line.split(" ");
        punkte.add(new Punkt(Float.parseFloat(zahlen[0]), Float.parseFloat(zahlen[1])));
        punkte.add(new Punkt(Float.parseFloat(zahlen[2]), Float.parseFloat(zahlen[3])));
      }
      reader.close();
      return anzahl;
    } catch (IOException e) {
      System.err.println("Couldn't load file!");
      return 0;
    }
  }

  private static void writeDreiecke(int loadedFile) {
    //Scheibt eine Ergebnis zu jeder aufgerufenen Darstellung.
    BufferedWriter writer = null;
    try {
      File out = new File("dreiecke" + loadedFile + "-ergebnis.txt");
      out.delete();
      writer = new BufferedWriter(new FileWriter(out));
      writer.write(dreiecke.size() + " Dreiecke gefunden");
      for (Dreieck dreieck : dreiecke) {
        writer.newLine();
        writer.write(dreieck.toString());
      }
      writer.close();
    } catch (IOException e) {
      System.err.println("Error writing File!");
    }
  }

  public static List<Punkt> getPunkte() {
    //Getter
    return punkte;
  }

  public static List<Dreieck> getDreiecke() {
    //Getter
    return dreiecke;
  }
}
