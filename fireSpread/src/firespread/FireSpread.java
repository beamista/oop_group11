package firespread;

import javax.swing.*;
import java.awt.*;

public class FireSpread {

    public static void main(String[] args) {

        JButton reset, enter;

        String width = JOptionPane.showInputDialog("Please input width: ");
        String height = JOptionPane.showInputDialog("Please input height: ");
        String prob = JOptionPane.showInputDialog("Please input probability: ");

        // รับ input width & height จาก user
        int w = Integer.parseInt(width);
        int h = Integer.parseInt(height);
        int p = Integer.parseInt(prob);
        int x = (int) (Math.random() * w);
        int y = (int) (Math.random() * h);
        Grid newGrid = new Grid(w, h, p, x, y);

        // Create a frame
        JFrame frame = new JFrame("Spreading_of_Fire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // สร้างปุ่ม
        JPanel Button = new JPanel();
        reset = new JButton("Reset");
        Button.add(reset);

        enter = new JButton("Enter");
        Button.add(enter);

        // ใน frame มีไรบ้าง
        frame.setSize(600, 500);
        frame.add(newGrid);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(Button);
        frame.add(Button, BorderLayout.EAST);
        frame.add(newGrid, BorderLayout.CENTER);
        x = 0;
        while (x < 5) {
            newGrid.start();
            x++;
            newGrid.Update();
        }
        
    }
}
