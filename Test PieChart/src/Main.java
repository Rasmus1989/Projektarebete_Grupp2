import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	
   public static void main(String[] argv) {
      JFrame frame = new JFrame();
      frame.getContentPane().add(new PieChart());
      frame.setBackground(Color.WHITE);
      frame.setSize(500, 500);
      frame.setVisible(true);
   }
}
