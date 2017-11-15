import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class DarstellungFrame extends JFrame {
  private static final long serialVersionUID = 186708090502948842L;
  private static final int SCALE = 3;
  private static final int OFFSET = 20;
  private JButton button_next = new JButton("next");
  private JButton button_last = new JButton("last");
  private JLabel label = new JLabel();
  private List<Punkt> punkte = new ArrayList<Punkt>();
  private List<Dreieck> dreiecke = new ArrayList<Dreieck>();
  private int fileNR;

  public DarstellungFrame() {
    super();
    this.punkte = DreieckZaehlerMain.getPunkte();
    this.dreiecke = DreieckZaehlerMain.getDreiecke();
    this.fileNR = 0;
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setBounds(100, 100, 200 * SCALE, 250 * SCALE);
    setVisible(true);
    Container cp = this.getContentPane();
    cp.setLayout(null);
    button_next.setBounds(OFFSET + 26 * SCALE, OFFSET, 25 * SCALE, 10 * SCALE);
    button_next.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        next();
      }
    });
    button_last.setBounds(OFFSET, OFFSET, 25 * SCALE, 10 * SCALE);
    button_last.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        last();
      }
    });
    label.setBounds(OFFSET + 26 * SCALE*2, OFFSET, 200 * SCALE, 10 * SCALE);
    cp.add(button_next);
    cp.add(button_last);
    cp.add(label);
    next();
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.ORANGE);
    g.setColor(Color.BLACK);
    for (int i = 0; i <= 5; i++) {
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
    button_next.updateUI();
    button_last.updateUI();
    label.updateUI();
  }
  private void next() {
    if (fileNR+1 <= 6) {     
      this.fileNR++;
      DreieckZaehlerMain.zaehleUndStelleDar(fileNR);
      this.punkte = DreieckZaehlerMain.getPunkte();
      this.dreiecke = DreieckZaehlerMain.getDreiecke();
      setTitle("Visualisierung: " + fileNR);
      label.setText(dreiecke.size()+" Dreiecke gefunden: als 'deiecke"+fileNR+"-ergebnis.txt' gespeichert"); 
      paintAll(getGraphics());    
    }
  }
  private void last() {
    if (fileNR-1 >= 1) {     
      this.fileNR--;
      DreieckZaehlerMain.zaehleUndStelleDar(fileNR);
      this.punkte = DreieckZaehlerMain.getPunkte();
      this.dreiecke = DreieckZaehlerMain.getDreiecke();
      setTitle("Visualisierung: " + fileNR);
      label.setText(dreiecke.size()+" Dreiecke gefunden: als 'deiecke"+fileNR+"-ergebnis.txt' gespeichert"); 
      paintAll(getGraphics());
    }
  }
  

}
