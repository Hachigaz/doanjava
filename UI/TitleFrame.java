package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class TitleFrame extends JFrame {
    private static final int RIGHT = 2;
    JPanel p1,p2;
    JLabel lb1,lb2,lb3;
    public TitleFrame(){
        this.setUndecorated(true);

        p1  = new JPanel();
        lb1 = new JLabel();
        lb2 = new JLabel();
        //lb3 = new JLabel();
        lb1.setPreferredSize(new Dimension(45,30));
        lb1.setText("      X");
        lb1.setOpaque(true);
        lb1.setBackground(new Color(250,250,250));
        lb2.setPreferredSize(new Dimension(45,30));
        lb2.setText("     -");
        lb2.setOpaque(true);
        lb2.setBackground(new Color(250,250,250));
        //lb3.setPreferredSize(new Dimension(45,30));
        // lb3.setText("     PHONG TO");
        // lb3.setOpaque(true);
        // lb3.setBackground(new Color(250,250,250));

        p1.setBackground(new Color(224, 72, 72));
        
        p1.add(lb2);
        //p1.add(lb3);
        p1.add(lb1);

        p1.setLayout(new FlowLayout(RIGHT));
        this.add(p1,BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==lb1){
                    dispose();
                    System.exit(0);
                }
                if ( e.getSource()==lb2){
                    TitleFrame.this.setExtendedState(JFrame.ICONIFIED);    }
                // if ( e.getSource()==lb3){
                //     TitleFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);    }
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
                if ( e.getSource()==lb2){
                    lb2.setBackground(new Color(224, 72, 72));
                }
                // if ( e.getSource()==lb3){
                //     lb3.setBackground(new Color(224, 72, 72));
                // }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if ( e.getSource()==lb2){
                    lb2.setBackground(new Color(250,250,250));
                }
                if ( e.getSource()==lb1){
                    lb1.setBackground(new Color(250,250,250));
                }
                // if ( e.getSource()==lb3){
                //     lb3.setBackground(new Color(250,250,250));
                // }
            }
            
        };
        lb1.addMouseListener(ml);
        lb2.addMouseListener(ml);
        //lb3.addMouseListener(ml);
        this.pack();
        this.setVisible(true);
    }
}
