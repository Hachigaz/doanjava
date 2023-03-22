package UI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class DangNhapUI extends TitleFrame{
    public final int F_Width = 600;
    public final int F_Height = 350;

    private JPanel formPanel;
    private JPanel sidePanel;

    private JLabel titleLabel;

    private JLabel logoLabel;
    private JLabel usernameLabel;
    private JTextField usernameInput;
    private JLabel passwordLabel;
    private JPasswordField passwordInput;
    
    private JButton submitButton;

    public DangNhapUI(){
        super();


        this.setBounds(600,50,F_Width,F_Height);


        int sidePanelWidth = 200;
        SpringLayout springlayout = new SpringLayout();

        formPanel = new JPanel();
        formPanel.setBackground(new Color(200,200,200));
        formPanel.setPreferredSize(new Dimension(this.getBounds().width-sidePanelWidth,this.getBounds().height));
        formPanel.setLayout(springlayout);

        titleLabel = new JLabel("Phần mềm quản lý kho");
        titleLabel.setFont(new Font("",Font.PLAIN,20));

        logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon(new ImageIcon("res/img/hammr.jpg").getImage().getScaledInstance(200, 200 , Image.SCALE_DEFAULT)));

        usernameLabel = new JLabel();
        usernameLabel.setText("Mã tài khoản");
        
        passwordLabel = new JLabel();
        passwordLabel.setText("Mật khẩu");

        usernameInput = new JTextField("",15);
        passwordInput = new JPasswordField("",15);

        springlayout.putConstraint(SpringLayout.NORTH,usernameLabel,100,SpringLayout.NORTH,formPanel);
        springlayout.putConstraint(SpringLayout.WEST,usernameLabel,30,SpringLayout.WEST,formPanel);
        springlayout.putConstraint(SpringLayout.NORTH,usernameInput,100,SpringLayout.NORTH,formPanel);
        springlayout.putConstraint(SpringLayout.WEST,usernameInput,30,SpringLayout.EAST,usernameLabel);

        springlayout.putConstraint(SpringLayout.NORTH,passwordLabel,20,SpringLayout.SOUTH,usernameLabel);
        springlayout.putConstraint(SpringLayout.WEST,passwordLabel,30,SpringLayout.WEST,formPanel);
        springlayout.putConstraint(SpringLayout.NORTH,passwordInput,15,SpringLayout.SOUTH,usernameInput);
        springlayout.putConstraint(SpringLayout.WEST,passwordInput,50,SpringLayout.EAST,passwordLabel);

        springlayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, formPanel);
        springlayout.putConstraint(SpringLayout.NORTH, titleLabel, 20, SpringLayout.NORTH, formPanel);

        formPanel.add(titleLabel);

        formPanel.add(usernameLabel);
        formPanel.add(usernameInput);
        formPanel.add(passwordLabel);
        formPanel.add(passwordInput);


        submitButton = new JButton("Đăng nhập");
        submitButton.setPreferredSize(new Dimension(100,30));

        springlayout.putConstraint(SpringLayout.WEST, submitButton, 60, SpringLayout.WEST, formPanel);
        springlayout.putConstraint(SpringLayout.SOUTH, submitButton, -20, SpringLayout.SOUTH, formPanel);

        formPanel.add(submitButton);

        //side panel
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(70,70,70));
        sidePanel.setPreferredSize(new Dimension(sidePanelWidth,this.getBounds().height));

        sidePanel.add(logoLabel);


        springlayout = new SpringLayout();

        springlayout.putConstraint(SpringLayout.VERTICAL_CENTER, logoLabel, 0, SpringLayout.VERTICAL_CENTER, sidePanel);
        springlayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logoLabel, 0, SpringLayout.HORIZONTAL_CENTER, sidePanel);

        sidePanel.setLayout(springlayout);


        //Frame
        this.add(formPanel,BorderLayout.CENTER);
        this.add(sidePanel,BorderLayout.EAST);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void disableAll(){
        usernameInput.setEnabled(false);
        passwordInput.setEnabled(false);
    
        usernameInput.setBackground(new Color(150, 150, 150));
        passwordInput.setBackground(new Color(150, 150, 150));

        submitButton.setEnabled(false);
    }

    public void enableAll(){
        usernameInput.setEnabled(true);
        passwordInput.setEnabled(true);
    
        usernameInput.setBackground(new Color(255, 255, 255));
        passwordInput.setBackground(new Color(255, 255, 255));

        submitButton.setEnabled(true);
    }

    public String getUsernameInput(){
        return this.usernameInput.getText();
    }

    public char[] getPasswordInput(){
        return this.passwordInput.getPassword();
    }

    public void setSubmitAction(ActionListener a){
        this.submitButton.addActionListener(a);
    }
}