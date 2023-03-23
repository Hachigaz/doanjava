package Panel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;

public class Test extends JFrame implements MouseListener{
    JPanel panel1,panel2,panel3;
    public String[] str = {"Home","About us","Contact"};
    JLabel[] labels = new JLabel[3];
    JLabel label1;
    Test(){

        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(300,200));
        panel1.setBackground(Color.red);
        panel1.setLayout(new FlowLayout());

        // label1 = new JLabel("Home");
        // label1.setBackground(Color.cyan);
        // label1.setPreferredSize(new Dimension(250,30));
        // label1.setHorizontalAlignment(JLabel.CENTER);
        // label1.setOpaque(true);
        // label1.addMouseListener(this);

        // label2 = new JLabel("About us");
        // label2.setBackground(Color.cyan);
        // label2.setPreferredSize(new Dimension(250,30));
        // label2.setHorizontalAlignment(JLabel.CENTER);
        // label2.setOpaque(true);

        // label3 = new JLabel("Contact");
        // label3.setBackground(Color.cyan);
        // label3.setPreferredSize(new Dimension(250,30));
        // label3.setHorizontalAlignment(JLabel.CENTER);
        // label3.setOpaque(true);

        for(int i=0;i<3;i++){
            labels[i] = new JLabel(str[i]);
            labels[i].setBackground(Color.cyan);
            labels[i].setPreferredSize(new Dimension(250,30));
            labels[i].setOpaque(true);
            labels[i].addMouseListener(this);
            panel1.add(labels[i]);
        }

        panel2 = new JPanel();
        panel2.setBackground(Color.blue);

        panel3 = new  JPanel();
        panel3.setBackground(Color.green);
        panel3.setPreferredSize(new Dimension(0,100));

        this.add(panel1,BorderLayout.WEST);
        this.add(panel2);
        this.add(panel3,BorderLayout.NORTH);
        this.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        for(int i=0;i<str.length;i++){
            if(e.getSource()==labels[i]){
                labels[i].setBackground(Color.red);
                labels[i].setForeground(Color.white);
            }
        }

    }
    @Override
    public void mouseExited(MouseEvent e) {
        for(int i=0;i<str.length;i++){
            if(e.getSource()==labels[i]){
                labels[i].setBackground(Color.cyan);
                labels[i].setForeground(Color.black);
            }
        }
    }
}
