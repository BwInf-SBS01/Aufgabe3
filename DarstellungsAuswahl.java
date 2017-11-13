import java.awt.Button;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class DarstellungsAuswahl extends JFrame {

  private static final long serialVersionUID = 1L;
  private static final int SCALE = 5;
  private static final int OFFSET = 30;
  private Button button_next = new Button("next");
  private Button button_last = new Button("last");
  
  public DarstellungsAuswahl() {
    super();
    addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            dispose();
          System.exit(0);
          }
        });
    setTitle("Super");
    setBounds(100, 100, 100, 150);
    Container cp = getContentPane();
    cp.setLayout(null);
    button_next.setBounds(OFFSET, OFFSET, 20, 15);
    button_next.setVisible(true);
    button_last.setBounds(OFFSET*2+SCALE, OFFSET, 20, 15);
    button_last.setVisible(true);
    cp.add(button_next);
    cp.add(button_last);
    this.add(cp);
    setVisible(true);
  }
}
