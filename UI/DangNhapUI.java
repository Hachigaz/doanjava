package UI;
import javax.swing.*;

import java.awt.*;

public class DangNhapUI extends JFrame{
    public final int F_Width = 600;
    public final int F_Height = 450;

    private JPanel formPanel;
    private JPanel submitPanel;

    private JLabel logoLabel;
    private JLabel usernameLabel;
    private JTextField usernameInput;
    private JLabel passwordLabel;
    private JPasswordField passwordInput;
    
    public DangNhapUI(){
        super("Đăng nhập");
        this.setBounds(500,200,F_Width,F_Height);
        this.setLayout(new FlowLayout());

        formPanel = new JPanel();
        formPanel.setBackground(new Color(200,200,200));
        formPanel.setPreferredSize(new Dimension(this.getBounds().width,200));

        logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon(new ImageIcon("res/img/hammr.jpg").getImage().getScaledInstance(200, 200 , Image.SCALE_DEFAULT)));

        usernameLabel = new JLabel();
        usernameLabel.setText("Mã tài khoản");
        
        passwordLabel = new JLabel();
        passwordLabel.setText("Mật khẩu");

        usernameInput = new JTextField("",15);
        passwordInput = new JPasswordField("",15);

        SpringLayout springlayout = new SpringLayout();
        springlayout.putConstraint(SpringLayout.WEST, usernameLabel, 5, SpringLayout.WEST, formPanel);
        springlayout.putConstraint(SpringLayout.NORTH, usernameLabel, 5, SpringLayout.NORTH, formPanel);
        springlayout.putConstraint(SpringLayout.WEST, usernameInput, 5, SpringLayout.EAST, usernameLabel);
        springlayout.putConstraint(SpringLayout.NORTH, usernameInput, 5, SpringLayout.NORTH, formPanel);

        springlayout.putConstraint(SpringLayout.WEST, passwordLabel, 5, SpringLayout.WEST, formPanel);
        springlayout.putConstraint(SpringLayout.NORTH, passwordLabel, 20, SpringLayout.SOUTH, usernameLabel);
        springlayout.putConstraint(SpringLayout.WEST, passwordInput, 5, SpringLayout.EAST, usernameLabel);
        springlayout.putConstraint(SpringLayout.NORTH, passwordInput, 20, SpringLayout.SOUTH, usernameInput);

        springlayout.putConstraint(SpringLayout.VERTICAL_CENTER, logoLabel, 0, SpringLayout.VERTICAL_CENTER, formPanel);
        springlayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logoLabel, 0, SpringLayout.HORIZONTAL_CENTER, formPanel);

        formPanel.add(usernameLabel);
        formPanel.add(usernameInput);
        formPanel.add(passwordLabel);
        formPanel.add(passwordInput);
        formPanel.add(logoLabel);

        formPanel.setLayout(springlayout);


        submitPanel = new JPanel();
        submitPanel.setBackground(new Color(70,70,70));
        submitPanel.setPreferredSize(new Dimension(50,100));


        this.add(formPanel);
        this.add(submitPanel);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}