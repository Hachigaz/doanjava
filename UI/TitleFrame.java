package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class TitleFrame extends JFrame {
    private static final int RIGHT = 2;
    JPanel p1,p2;
    JLabel lb1;
    public TitleFrame(){
        this.setUndecorated(true);

        p1  = new JPanel();
        lb1 = new JLabel();
        lb1.setPreferredSize(new Dimension(45,30));
        lb1.setText("      X");
        lb1.setOpaque(true);
        lb1.setBackground(new Color(250,250,250));
        p1.setBackground(new Color(224, 72, 72));
        p1.add(lb1);
        p1.setLayout(new FlowLayout(RIGHT));
        this.add(p1,BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lb1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==lb1){
                    dispose();
                    System.exit(0);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if ( e.getSource()==lb1){
                    lb1.setBackground(new Color(224, 72, 72));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if ( e.getSource()==lb1){
                    lb1.setBackground(new Color(250,250,250));
                }
            }
            
        });
        this.pack();
        this.setVisible(true);
    }
}
