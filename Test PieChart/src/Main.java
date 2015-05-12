import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	
   public static void main(String[] argv) {
      JFrame frame = new JFrame();
      frame.getContentPane().setBackground(Color.GREEN);
      frame.getContentPane().setLayout(null);
      PieChart pieChart = new PieChart();
      pieChart.setBounds(0, 0, 509, 521);
      frame.getContentPane().add(pieChart);
      frame.setBackground(Color.WHITE);
      frame.setSize(776, 568);
      frame.setVisible(true);
   }
}
