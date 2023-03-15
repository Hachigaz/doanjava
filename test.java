import java.awt.Color;

import javax.swing.JFrame;

import java.awt.BorderLayout;

public class test {
    public static void main(String[] args) {
        createFrame();
        createFrame();
        createFrame();
    }

    public static javax.swing.JFrame createFrame(){        
    javax.swing.JFrame frame = new javax.swing.JFrame("cho hien");
    javax.swing.JWindow window = new javax.swing.JWindow();
    window.setVisible(true);

    frame.setBounds(200,200,800,600);

    javax.swing.JButton button = new javax.swing.JButton("xin chao");
    javax.swing.JLabel label = new javax.swing.JLabel("chao ban");

    button.setBounds(10,10,100,20);
    label.setBounds(20,20,100,100);
    label.setOpaque(true);
    label.setBackground(Color.BLACK);
    frame.add(button);
    frame.add(label);


    frame.setVisible(true);

    javax.swing.JPanel panel = new javax.swing.JPanel();
    panel.add(label,BorderLayout.CENTER);
    return frame;
    }
}
