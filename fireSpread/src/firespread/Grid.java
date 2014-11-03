package firespread;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class Grid extends JPanel {

    int w, h, p;

    private Cell cell[][];
    private final int cellSize = 13;

    public Grid(int w, int h, int p, int x, int y) {
        this.w = w;
        this.h = h;
        this.p = p;
        cell = new Cell[w][h];
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if ( (i == 0 || j == 0) || (i == w-1 || j == h-1)) {
                    cell[i][j] = new Cell(Cell.EMPTY);
                } else {
                    cell[i][j] = new Cell(Cell.TREE);
                }
            }
        }
        cell[x][y].setStatus(2);

    }

    public void Update() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (cell[i][j] != null) {
                    int x = (i + 1) * cellSize;
                    int y = (j + 1) * cellSize;
                    if (cell[i][j].getStatus() == Cell.EMPTY) {
                        g.setColor(Color.YELLOW);
                    } else if (cell[i][j].getStatus() == Cell.TREE) {
                        g.setColor(Color.GREEN);
                    } else {
                        g.setColor(Color.RED);
                    }

                    g.fillRect(x + 2, y + 2, cellSize - 2, cellSize - 2);
                }
            }
        }
    }

    public boolean random(int p) {
        int rand = (int) (Math.random() * 100);
        System.out.println(rand);

        return rand < (p);
    }

    public boolean start() {
        HashSet<Cell> localFire = new HashSet();
       
        boolean stillBurn = false;;
        for (int i = 1; i < cell.length - 1; i++) {  
            for (int j = 1; j < cell[0].length - 1; j++) {
                
                if (cell[i][j].getStatus() == Cell.BURNING) {
                    
                    if (cell[i - 1][j].getStatus() == Cell.TREE && random(p)) {
                        localFire.add(cell[i - 1][j]);
                        stillBurn = true;
                    }

                    if (cell[i][j + 1].getStatus() == Cell.TREE && random(p)) {
                        localFire.add(cell[i][j + 1]);
                        stillBurn = true;
                    }

                    if (cell[i + 1][j].getStatus() == Cell.TREE && random(p)) {
                        localFire.add(cell[i + 1][j]);
                        stillBurn = true;
                    }

                    if (cell[i][j - 1].getStatus() == Cell.TREE && random(p)) {
                        localFire.add(cell[i][j - 1]);
                        stillBurn = true;
                    }
                    
                    cell[i][j].setStatus(Cell.EMPTY);
                }
            }
        }
        
           Object temp[] = localFire.toArray();
           
           for(int i = 0; i < temp.length; i++ ){
               ((Cell)temp[i]).setStatus(Cell.BURNING);
           }
           
                      
        this.repaint();
        return stillBurn;
    }
    
    public void run() {
        while (this.start()) {
            try {
                Thread.sleep(200);  
            } catch (InterruptedException ex) {

            }
        }
    }
    
    public void reset(int w, int h, int p) {
        
        int x = (int) (Math.random() * w);
        int y = (int) (Math.random() * h);
        this.w = w;
        this.h = h;
        this.p = p;
        cell = new Cell[w][h];
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if ( (i == 0 || j == 0) || (i == w-1 || j == h-1)) {
                    cell[i][j] = new Cell(Cell.EMPTY);
                } else {
                    cell[i][j] = new Cell(Cell.TREE);
                }
            }
        }
        cell[x][y].setStatus(2);
        repaint();
    }
}
