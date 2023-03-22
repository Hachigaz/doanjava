package temp;
import java.awt.*;

import javax.swing.*;

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
    
    // public static void connectToSQLServer(){
    //     String connectionUrl =
    //     "jdbc:sqlserver://localhost;"
    //             + "database=QuanLyKho;"
    //             + "user=sa;"
    //             + "password=sa;"
    //             + "encrypt=true;"
    //             + "trustServerCertificate=false;"
    //             + "loginTimeout=30;";
                
    //     try (Connection connection = DriverManager.getConnection(connectionUrl);) {

    //     }
    //     // Handle any errors that may have occurred.
    //     catch (SQLException e) {
    //         e.printStackTrace();
    //         System.out.println(e.getLocalizedMessage());
    //     }
    // }
    public static JFrame taoFormDN(){
        JFrame formDN = new JFrame("Đăng nhập");
        formDN.setBounds(300,150,600,400);
        formDN.setVisible(true);
        formDN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(193, 183, 49));
        inputPanel.setBounds(0,0,100,100);
        inputPanel.setPreferredSize(new Dimension(900,100));
    
        JPanel submitPanel = new JPanel();
        submitPanel.setBackground(new Color(131, 193, 49));
        submitPanel.setBounds(0,0,300,300);
        submitPanel.setPreferredSize(new Dimension(200,100));

        formDN.setLayout(new FlowLayout());

        formDN.add(inputPanel);
        formDN.add(submitPanel);
        return formDN;
    }
    public static void start(){
        javax.swing.JFrame frame = new JFrame();

        frame.setBounds(300,150,1000,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
