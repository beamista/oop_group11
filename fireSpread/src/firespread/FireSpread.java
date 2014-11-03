package firespread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FireSpread extends JFrame{
  
    public static void main(String[] args) throws InterruptedException {
        
        //create panel1
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        //autoplay button
        JButton autoPlay = new JButton("Auto Play");
        panel1.add(autoPlay);
        //step button
        JButton step = new JButton("Step");
        panel1.add(step);
        //reset button
        JButton reset = new JButton("Reset");
        panel1.add(reset);

        String width = JOptionPane.showInputDialog("Please input width: ");
        String height = JOptionPane.showInputDialog("Please input height: ");
        String prob = JOptionPane.showInputDialog("Please input probability: ");
        
        //รับ input width & height จาก user
        int w = Integer.parseInt(width);
        int h = Integer.parseInt(height);
        int p = Integer.parseInt(prob);
        int x = (int) (Math.random() * w);
        int y = (int) (Math.random() * h);
        
        Grid newGrid = new Grid(w, h, p, x, y);
        //ไฟลามอัตโนมัติ
        autoPlay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                newGrid.run();
            }
        });
        //ไฟลามเป็น Step
        step.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                newGrid.start();
            }
        });
        //Reset
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGrid.reset(w, h, p);
            }
        });
        //สร้างเฟรม
        JFrame frame = new JFrame();
        frame.setTitle("Fire_Spread");
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //add panel into the frame
        frame.add(panel1, BorderLayout.SOUTH);
        frame.add(newGrid, BorderLayout.CENTER);    
    }
}

