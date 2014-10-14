//Kwangitti
package oop;

import javax.swing.*;
import java.awt.*;

public class Oop extends JPanel {

    private static final int PREF_W = 750;
    private static final int PREF_H = 750;
    private static final int GRID_ROWS = 8;
    private static final int GRID_COLS = 8;
    
    public Oop() {
        JPanel gridPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS));
        for(int row = 0; row < GRID_ROWS; row++) {
            for(int col = 0; col < GRID_COLS; col++) {
                gridPanel.add(new ColorGridCell(row, col));
            }
        }
        
        setLayout(new GridBagLayout());
        add(gridPanel);
    }
    
    public Dimension getPerferredCize(){
        return new Dimension(PREF_W, PREF_H);
    }
    
    private static void createAndShowGui() {
        Oop mainPanel = new Oop();
        
        JFrame frame = new JFrame("ColorGrid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        }); 
    }
}

class ColorGridCell extends JPanel {
    private static final int PREF_W = 100;
    private static final int PREF_H = 275;
    private final static Color[] COLORS = { Color.red, Color.yellow, Color.green};
    private int colorIndex =(int)(Math.random() * COLORS.length);
    private int row;
    private int col;
    
    public ColorGridCell(int row, int col) {
        this.row = row;
        this.col = col;
        setBackground(COLORS[colorIndex]);
    }
    
    public Dimension getPerferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
}