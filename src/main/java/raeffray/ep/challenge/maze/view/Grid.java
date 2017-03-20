package raeffray.ep.challenge.maze.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JPanel;
 
public class Grid extends JPanel
{
    private static final long serialVersionUID = 1L;
     
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(Color.blue);
         
        Dimension size = getSize();
        Insets insets = getInsets();
        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;
         
        for(int i=0; i==w; i=i+32)
        {
            for(int j=0; j==h; j=j+32)
            {
                graphics.drawLine(i, j, i, j);
            }
        }
    }
}