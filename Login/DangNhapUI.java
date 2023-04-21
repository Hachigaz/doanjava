package Login;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DangNhapUI extends JFrame implements MouseListener{
    public JPanel panelTitleBar,centerPanel,panelLoginContainer,panelLogin,panelSubmit;
    public JLabel labelHide,labelClose,labelUser,labelPass,labelTitle,labelForget;
    public static JTextField usernameInput;
    public JPasswordField passwordInput;
    public JButton submitButton;
    public DangNhapUI() {
        this.setTitle("Gradient Frame");
        this.setSize(1000, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
    
        labelHide = new JLabel();
        labelClose = new JLabel();

        ImageIcon iconHide = new ImageIcon("res/img/minimum.png");
        Image imgHide = iconHide.getImage();
        Image newImgHide = imgHide.getScaledInstance(13,13,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconHide = new ImageIcon(newImgHide);

        ImageIcon iconClose = new ImageIcon("res/img/close.png");
        Image imgClose = iconClose.getImage();
        Image newImgClose = imgClose.getScaledInstance(13,13,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconClose = new ImageIcon(newImgClose);

        labelHide.setIcon(newIconHide);
        labelHide.setHorizontalAlignment(JLabel.CENTER);
        labelHide.setPreferredSize(new Dimension(30,20));
        labelHide.setBackground(new Color(255, 255, 153));
        labelHide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelHide.setOpaque(true);
        labelHide.addMouseListener(this);

        labelClose.setIcon(newIconClose);
        labelClose.setHorizontalAlignment(JLabel.CENTER);
        labelClose.setPreferredSize(new Dimension(30,20));
        labelClose.setBackground(new Color(255, 255, 153));
        labelClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelClose.setOpaque(true);
        labelClose.addMouseListener(this);

        panelTitleBar = new JPanel();
        panelTitleBar.setPreferredSize(new Dimension(0,30));
        panelTitleBar.setBackground(new Color(255, 255, 153));
        panelTitleBar.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));

        panelTitleBar.add(labelHide);
        panelTitleBar.add(labelClose);

        labelTitle = new JLabel("ĐĂNG NHẬP");
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        labelTitle.setFont(new Font("Monospace",Font.BOLD,18));
        labelTitle.setOpaque(false);
        
        panelLogin = new JPanel();
        panelLogin.setLayout(new GridBagLayout());
        panelLogin.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 20, 20);
            
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelLogin.add(createLabel("username.png"),gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelLogin.add(createTextField(),gbc);     
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelLogin.add(createLabel("lock.png"),gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelLogin.add(createPasswordField(),gbc); 

        submitButton = new JButton("Đăng nhập");
        submitButton.setBorder(null);
        submitButton.setBackground(new Color(255, 255, 153));
        submitButton.setForeground(Color.BLACK);
        submitButton.setFont(new Font("Monosapce",Font.PLAIN,15));
        submitButton.setPreferredSize(new Dimension(240,40));
        submitButton.setFocusable(false);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelSubmit = new JPanel();
        panelSubmit.setBorder(BorderFactory.createEmptyBorder(0,0,100,0));
        panelSubmit.setOpaque(false);
        panelSubmit.add(submitButton);
        


        panelLoginContainer = new JPanel();
        panelLoginContainer.setOpaque(false);
        // panelLoginContainer.setBackground(Color.WHITE);
        panelLoginContainer.setPreferredSize(new Dimension(400,500));
        panelLoginContainer.setLayout(new BorderLayout());

        panelLoginContainer.add(labelTitle,BorderLayout.NORTH);
        panelLoginContainer.add(panelLogin,BorderLayout.CENTER);
        panelLoginContainer.add(panelSubmit,BorderLayout.SOUTH);

        // panelLoginContainer.add(username);
        centerPanel = new JPanel(){
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color color1 = new Color(255, 255, 153);
                Color color2 = new Color(255, 102, 0);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0,getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(),getHeight());
             }
        };
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,45));
        centerPanel.add(panelLoginContainer);

        this.add(panelTitleBar,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.setVisible(true);
    }

    public JLabel createLabel(String link){
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(20, 20)); // đặt kích thước ưu tiên cho nhãn
        label.setFont(new Font("Monospace",Font.BOLD,13));
        ImageIcon icon = new ImageIcon("res/img/"+link);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        label.setIcon(newIcon);
        return label;
    }
    public JTextField createTextField() {
        usernameInput = new JTextField("Admin");
        usernameInput.setPreferredSize(new Dimension(240, 30)); // đặt kích thước ưu tiên cho trường văn bản
        usernameInput.setFont(new Font("Monospace",Font.PLAIN,18));
        usernameInput.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        usernameInput.setOpaque(false);
        return usernameInput;
    } 
    public JPasswordField createPasswordField() {
        passwordInput = new JPasswordField("123");
        passwordInput.setPreferredSize(new Dimension(240, 30)); // đặt kích thước ưu tiên cho trường văn bản
        passwordInput.setFont(new Font("Monospace",Font.PLAIN,18));
        passwordInput.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        passwordInput.setOpaque(false);
        return passwordInput;
    } 

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==labelHide){
            this.setExtendedState(JFrame.ICONIFIED); 
        }else if(e.getSource()==labelClose){
            this.dispose();
        }
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
        // TODO Auto-generated method stub
        if(e.getSource()==labelClose){
            labelClose.setBackground(Color.red);
        }else if(e.getSource()==labelHide){
            labelHide.setBackground(Color.gray);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==labelClose){
            labelClose.setBackground(new Color(255, 255, 153));
        }else if(e.getSource()==labelHide){
            labelHide.setBackground(new Color(255, 255, 153));
        }
    }
    public void disableAll(){
        usernameInput.setEnabled(false);
        passwordInput.setEnabled(false);
    
        usernameInput.setBackground(new Color(150, 150, 150));
        passwordInput.setBackground(new Color(150, 150, 150));

        submitButton.setEnabled(false);
    }

    public void enableAll(){//??
        usernameInput.setEnabled(true);
        passwordInput.setEnabled(true);
    
        usernameInput.setBackground(new Color(255, 255, 255));
        passwordInput.setBackground(new Color(255, 255, 255));

        submitButton.setEnabled(true);
    }

    public static String getUsernameInput(){
        return usernameInput.getText();
    }

    public String getPasswordInput(){
        String s = "";
        char[] p = this.passwordInput.getPassword();
        for(int i = 0 ; i < p.length;i++){
            s+=p[i];
        }
        return s;
    }
    public void setSubmitAction(ActionListener a){
        this.submitButton.addActionListener(a);
    }
}
