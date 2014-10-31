package firespread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FireSpread extends JFrame{
  
    public static void main(String[] args) throws InterruptedException {

        JButton autoPlay, step;

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
        autoPlay = new JButton("Auto Play");
        autoPlay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                newGrid.run();
            }
        });
        
        step = new JButton("Step");
        step.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                newGrid.start();
            }
        });
        
        JPanel jpButton = new JPanel();
        jpButton.setLayout(new GridLayout(2, 1));
        jpButton.add(autoPlay);
        jpButton.add(step);
        

        // ใน frame มีไรบ้าง
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(newGrid, BorderLayout.CENTER);
        frame.add(jpButton, BorderLayout.EAST);

    }
 
}
