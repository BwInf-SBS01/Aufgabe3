package de.dreiecke_zaehlen.java;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class DarstellungFrame extends Frame {

	private static final long serialVersionUID = 186708090502948842L;
	private List<Point> punkte = new ArrayList<Point>();
	int anzahl;

	public DarstellungFrame(String file) {
		super();
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);

			}
		});
		setTitle("Visualisierung:" + file);
		setBounds(100, 100, 400, 400);
		setVisible(true);
		run(this, file);
		
		setVisible(true);
	}

	private void run(Frame frame, String file) {

		Graphics g = frame.getGraphics();
		anzahl = loadPoints(file);
		paint(g);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < anzahl * 2; i = i + 2) {
			System.out.println(punkte.get(i) + " to " + punkte.get(i + 1));
			g.drawLine(punkte.get(i).x + 50, this.getHeight() - punkte.get(i).y - 50, punkte.get(i + 1).x + 50,
					this.getHeight() - punkte.get(i + 1).y - 50);
		}
	}

	private int loadPoints(String file) {
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
