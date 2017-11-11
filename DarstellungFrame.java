import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DarstellungFrame extends Frame {
	private static final long serialVersionUID = 186708090502948842L;
	private static final int SCALE = 5;
	private static final int OFFSET = 30;

	private List<Punkt> punkte = new ArrayList<Punkt>();
	private List<Dreieck> dreiecke = new ArrayList<Dreieck>();

	public DarstellungFrame(List<Punkt> punkte, List<Dreieck> dreiecke, int fileNR) {
		super();
		this.punkte = punkte;
		this.dreiecke = dreiecke;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		setTitle("Visualisierung: " + fileNR);
		setBounds(100, 100, 250*SCALE, 250*SCALE);
		setVisible(true);
		run(this);
		setVisible(true);
	}

	private void run(Frame frame) {
		Graphics g = frame.getGraphics();

		paint(g);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i <= 6; i++) {

			g.drawString(Integer.toString(i * 50), i * 50 * SCALE + OFFSET, this.getHeight() - OFFSET);
			g.drawString(Integer.toString(i * 50), OFFSET, this.getHeight() - i * 50 * SCALE - OFFSET);
		}
		g.setColor(Color.RED);
		for (int i = 0; i < punkte.size(); i = i + 2) {
			g.drawLine(punkte.get(i).toAWTPoint().x * SCALE + OFFSET,
					this.getHeight() - punkte.get(i).toAWTPoint().y * SCALE - OFFSET,
					punkte.get(i + 1).toAWTPoint().x * SCALE + OFFSET,
					this.getHeight() - punkte.get(i + 1).toAWTPoint().y * SCALE - OFFSET);
		}
		g.setColor(Color.BLUE);
		for (int i = 0; i < dreiecke.size(); i++) {
			g.drawOval(dreiecke.get(i).getP1().getXint() * SCALE + OFFSET - SCALE,
					this.getHeight() - dreiecke.get(i).getP1().getYint() * SCALE - OFFSET - SCALE, 2 * SCALE,
					2 * SCALE);
			g.drawOval(dreiecke.get(i).getP2().getXint() * SCALE + OFFSET - SCALE,
					this.getHeight() - dreiecke.get(i).getP2().getYint() * SCALE - OFFSET - SCALE, 2 * SCALE,
					2 * SCALE);
			g.drawOval(dreiecke.get(i).getP3().getXint() * SCALE + OFFSET - SCALE,
					this.getHeight() - dreiecke.get(i).getP3().getYint() * SCALE - OFFSET - SCALE, 2 * SCALE,
					2 * SCALE);
		}
	}
}
