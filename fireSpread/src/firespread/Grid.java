package firespread;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class Grid extends JPanel {

    int w, h, p, t, b, count;

    private Cell cell[][];
    int cellSize;

    public Grid(int w, int h, int p, int t, int x, int y, int b, int count) {
        this.w = w;
        this.h = h;
        this.p = p;
        this.t = t;
        this.b = b;
        this.count = count;

        cell = new Cell[w][h];
        this.reset(w, h, p, t, b, count);
//        cell[x][y].setStatus(2);
        //10*10
        if (w == 10 && h == 10) {
            cellSize = 35;
            cell[5][5].setStatus(2);
            //20*20
        }
        if (w == 20 && h == 20) {
            cellSize = 25;
            cell[10][10].setStatus(2);
            //30*30
        }
        if (w == 30 && h == 30) {
            cellSize = 19;
            cell[15][15].setStatus(2);
            //40*40
        }
        if (w == 40 && h == 40) {
            cellSize = 14;
            cell[20][20].setStatus(2);
            //50*50
        }
        if (w == 50 && h == 50) {
            cellSize = 12;
            cell[25][25].setStatus(2);
        }
        Update();
    }

    public void Update() {
        validate();
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

    public boolean randomBurn(int b) {
        int rand = (int) (Math.random() * 100);
        System.out.println(rand);

        return rand < (b);
    }

    public boolean randomFire(int p) {
        int rand = (int) (Math.random() * 100);
        System.out.println(rand);

        return rand < (p);
    }

    public boolean randomTree(int t) {
        int rand = (int) (Math.random() * 100);
        System.out.println(rand);

        return rand > (t);
    }

    public boolean start() {
        HashSet<Cell> localFire = new HashSet();

        boolean stillBurn = false;;
        for (int i = 1; i < cell.length - 1; i++) {
            for (int j = 1; j < cell[0].length - 1; j++) {

                if (cell[i][j].getStatus() == Cell.BURNING) {

                    if (cell[i - 1][j].getStatus() == Cell.TREE && randomFire(p)) {
                        localFire.add(cell[i - 1][j]);
                        stillBurn = true;
                    }

                    if (cell[i][j + 1].getStatus() == Cell.TREE && randomFire(p)) {
                        localFire.add(cell[i][j + 1]);
                        stillBurn = true;
                    }

                    if (cell[i + 1][j].getStatus() == Cell.TREE && randomFire(p)) {
                        localFire.add(cell[i + 1][j]);
                        stillBurn = true;
                    }

                    if (cell[i][j - 1].getStatus() == Cell.TREE && randomFire(p)) {
                        localFire.add(cell[i][j - 1]);
                        stillBurn = true;
                    }

                    cell[i][j].setStatus(Cell.EMPTY);
                }
            }
        }

        Object temp[] = localFire.toArray();

        for (int i = 0; i < temp.length; i++) {
            ((Cell) temp[i]).setStatus(Cell.BURNING);
        }

        this.repaint();
        return stillBurn;
    }

    public void reset(int w, int h, int p, int t, int b, int count) {

        int x = (int) (Math.random() * w);
        int y = (int) (Math.random() * h);
        this.w = w;
        this.h = h;
        this.p = p;
        cell = new Cell[w][h];
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if ((i == 0 || j == 0) || (i == w - 1 || j == h - 1)) {
                    cell[i][j] = new Cell(Cell.EMPTY);
                } else if (randomTree(t)) {
                    cell[i][j] = new Cell(Cell.EMPTY);
                } else {
                    if(randomBurn(b)) {
                        cell[i][j] = new Cell(Cell.BURNING);
                    } else {
                        cell[i][j] = new Cell(Cell.TREE);
                    }
                }
            }
        }

//        cell[x][y].setStatus(2);
        //10*10
        if (w == 10 && h == 10) {
            cellSize = 35;
            cell[5][5].setStatus(2);
            //20*20
        }
        if (w == 20 && h == 20) {
            cellSize = 25;
            cell[10][10].setStatus(2);
            //30*30
        }
        if (w == 30 && h == 30) {
            cellSize = 19;
            cell[15][15].setStatus(2);
            //40*40
        }
        if (w == 40 && h == 40) {
            cellSize = 14;
            cell[20][20].setStatus(2);
            //50*50
        }
        if (w == 50 && h == 50) {
            cellSize = 12;
            cell[25][25].setStatus(2);
        }
        repaint();
    }
}
