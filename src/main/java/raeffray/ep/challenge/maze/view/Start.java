package raeffray.ep.challenge.maze.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Start extends JPanel {
  private static final long serialVersionUID = 1L;
  public static final int GRID_COUNT = 50;
 
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics = (Graphics2D) g;
    graphics.setColor(Color.BLACK);
 
    Dimension size = getSize();
    Insets insets = getInsets();
    int w = size.width - insets.left - insets.right;
    int h = size.height - insets.top - insets.bottom;
     
    int sqrWidth = (int)((double)w / GRID_COUNT);
    int sqrHeight = (int)((double)h / GRID_COUNT);
    for (int row = 0; row < GRID_COUNT; row++) {
      for (int col = 0; col < GRID_COUNT; col++) {
        if ((row + col) % 2 == 0) {
          int x = (int) (row * (double) w / GRID_COUNT);
          int y = (int) (col * (double) h / GRID_COUNT);
           graphics.fillRect(x, y, sqrWidth, sqrHeight);
        }
      }
    }
  }
 
  public static void main(String[] args) {
    Start grid = new Start();
    grid.setPreferredSize(new Dimension(800, 800));
    JFrame frame = new JFrame("Grid");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(grid);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
 
}