import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;

class Slice {
	
   double value;
   Color color;
   public Slice(double value, Color color) {  
      this.value = value;
      this.color = color;
   }
}
class PieChart extends JComponent {

   Slice[] slices = { 
		   
new Slice(20, new Color(148, 104, 169)), 
new Slice(20, new Color(250, 185, 54)),
new Slice(20, new Color(50, 180, 228)), 
new Slice(20, new Color(232, 71, 73)), 
new Slice(20, new Color(152, 194, 28)) };
   
   
  PieChart() {
	  
  	setBackground(Color.WHITE);
}
   public void paint(Graphics g) {
      drawPie((Graphics2D) g, getBounds(), slices);
     
   }
   
   
   void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
      double total = 0.0D;
      for (int i = 0; i < slices.length; i++) {
         total += slices[i].value;
      }
      
      
      double curValue = 0.0D;
      int startAngle = 0;
      for (int i = 0; i < slices.length; i++) {
         startAngle = (int) (curValue * 360 / total);
         int arcAngle = (int) (slices[i].value * 360 / total);
         g.setColor(slices[i].color);
         g.fillArc(area.x, area.y, area.width, area.height, 
         startAngle, arcAngle);
         curValue += slices[i].value;
      }
   }
}