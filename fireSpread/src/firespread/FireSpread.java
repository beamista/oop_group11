package firespread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FireSpread extends JFrame{
  
//    JButton autoPlay = new JButton("Auto Play");
//    JButton step = new JButton("Step");
//    
//    int w, h, p, x, y;
//    String width, height, prob;
//    Grid newGrid;
    
    public static void main(String[] args) throws InterruptedException {

        JButton autoPlay = new JButton("Auto Play");
        JButton step = new JButton("Step");
        
        JFrame frame = new JFrame();
        frame.setTitle("Fire_Spread");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
 
        String width = JOptionPane.showInputDialog("Please input width: ");
        String height = JOptionPane.showInputDialog("Please input height: ");
        String prob = JOptionPane.showInputDialog("Please input probability: ");
        
//         รับ input width & height จาก user
        int w = Integer.parseInt(width);
        int h = Integer.parseInt(height);
        int p = Integer.parseInt(prob);
        int x = (int) (Math.random() * w);
        int y = (int) (Math.random() * h);
        Grid newGrid = new Grid(w, h, p, x, y);
        JPanel jbutton = new JPanel();
        jbutton.add(autoPlay);
        jbutton.add(step);
        frame.add(newGrid, BorderLayout.CENTER);
        frame.add(jbutton, BorderLayout.SOUTH);
        autoPlay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                newGrid.run();
            }
        });
        step.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                newGrid.start();
            }
        });
        
    }
    
//    public FireSpread() {
//        
//        JPanel jbutton = new JPanel();
//        jbutton.add(autoPlay);
//        jbutton.add(step);
//        
//        setLayout(new BorderLayout());
//        add(jbutton, BorderLayout.CENTER);
//        
//        autoPlay.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                newGrid.run();
//            }
//        });
//        
//        step.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                newGrid.start();
//            }
//        });
//    }
//    
//    public void setInput(String width, String height, String prob){
//
//        width = JOptionPane.showInputDialog("Please input width: ");
//        w = Integer.parseInt(width);
//        
//        height = JOptionPane.showInputDialog("Please input height: ");
//        h = Integer.parseInt(height);
//        
//        prob = JOptionPane.showInputDialog("Please input probability: ");
//        p = Integer.parseInt(prob);
//        
//        x = (int)(Math.random() * w);
//        y = (int)(Math.random() * h);
//        
//        newGrid = new Grid(w, h, p, x, y);
//    }
//    
//    public Grid getInput(){
//        return this.newGrid;
//    }
}
