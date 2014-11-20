package firespread;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FireSpread extends JFrame {

    private static JPanel panel1, panel2, panel3;
    private static JLabel label1, label2, label3, label4, label5, label6;
    private static JButton autoPlay, reset, step, pause;
    private static JComboBox wd, hd;
    private static JSlider tree, fire, burn;
    private static String width[] = {"10", "20", "30", "40", "50"};
    private static String hight[] = {"10", "20", "30", "40", "50"};
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int INIT1 = 100;
    private static final int INIT2 = 50;
    private static final int INIT3 = 0;
    private static Grid newGrid;
    private static int w, h, p, t, x, y, b, count;
    private static Timer timer;
    static boolean nice = true; // Variable for checking button
    static Thread pa;

    public static void main(String[] args) throws InterruptedException {
        initialDefaultValue();
        initialButton();
        buttonFunction();
        frameCreate();
        settingFunction();
    }

    // running method of autorun including timer.
    private static void letStart() {
        nice = true;// set to true every time user press button
        ActionListener actListner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (nice == true) {
                    newGrid.start();
                    count++;
                    label6.setText("STEP : " + count);
                }
            }
        };

        Timer timer = new Timer(500, actListner);
        timer.start();
    }

    // Create Frame and re arranged.
    public static void frameCreate() {
        final JFrame frame = new JFrame();
        frame.setTitle("Fire_Spread");
        frame.setSize(850, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add panel into the frame
        frame.add(panel1, BorderLayout.SOUTH);
        frame.add(newGrid, BorderLayout.CENTER);

        // add panel for setting.
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(11, 1, 30, 10));
        // initial setting command
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();

        label1.setText("Width size");
        label2.setText("Hight size");
        label3.setText("Catch probabaility ");
        label4.setText("Tree probabaility");
        label5.setText("Burn probability");
        label6.setText("STEP : " + count);

        wd = new JComboBox(width);
        wd.setSelectedIndex(4);
        hd = new JComboBox(hight);
        hd.setSelectedIndex(4);

        tree = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT1);
        tree.setMajorTickSpacing(20);
        tree.setMinorTickSpacing(1);
        tree.setPaintTicks(true);
        tree.setPaintLabels(true);

        fire = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT2);
        fire.setMajorTickSpacing(20);
        fire.setMinorTickSpacing(1);
        fire.setPaintTicks(true);
        fire.setPaintLabels(true);

        burn = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT3);
        burn.setMajorTickSpacing(20);
        burn.setMinorTickSpacing(1);
        burn.setPaintTicks(true);
        burn.setPaintLabels(true);

        panel3.add(label1);
        panel3.add(wd);
        panel3.add(label2);
        panel3.add(hd);
        panel3.add(label3);
        panel3.add(fire);
        panel3.add(label4);
        panel3.add(tree);
        panel3.add(label5);
        panel3.add(burn);
        panel3.add(label6);

        panel2.add(panel3);
        frame.add(panel2, BorderLayout.EAST);
        frame.setVisible(true);
    }

    // Create buttons into frame.
    public static void initialButton() {
        // create panel1
        panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        // autoplay button
        autoPlay = new JButton("Auto Play");
        panel1.add(autoPlay);
        // step button
        step = new JButton("Step");
        panel1.add(step);
        // stop button
        pause = new JButton("Pause");
        panel1.add(pause);
        // reset button
        reset = new JButton("Reset");
        panel1.add(reset);

    }

    // Create the default value for variable.
    public static void initialDefaultValue() {
        w = 50;
        h = 50;
        p = 70;
        t = 100;
        b = 0;
        count = 0;
        x = (int) (Math.random() * w);
        y = (int) (Math.random() * h);
        newGrid = new Grid(w, h, p, t, x, y, b, count);
    }

    // Create buttons function when clicked.
    public static void buttonFunction() {
        // Auto play animation.
        autoPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//		        newGrid.reset(w, h, p, t);
                letStart(); // method for autorun
            }
        });
        // ไฟลามเป็น Step
        step.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                newGrid.start();
                nice = false;

                if (newGrid.start()) {
                    count++;
                    label6.setText("STEP : " + count);
                }
            }
        });
        // Reset
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                nice = false;
                newGrid.reset(w, h, p, t, b, count);
                count = 0;
                label6.setText("STEP : " + count);
            }
        });

        // stop
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                nice = false;
                pa.stop();
            }
        });
    }

    public static void settingFunction() {
        // width get value
        wd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                w = Integer.parseInt((String) cb.getSelectedItem());
            }
        });
        // high get value
        hd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                h = Integer.parseInt((String) cb.getSelectedItem());
            }
        });
        tree.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    t = (int) source.getValue();
                }

            }

        });

        fire.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    p = (int) source.getValue();
                }

            }

        });

        burn.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    b = (int) source.getValue();
                }

            }

        });
    }
}
