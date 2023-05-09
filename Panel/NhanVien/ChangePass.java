package Panel.NhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import Panel.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ChangePass extends JDialog{
    private JPanel panelContainer,panelInput,panelBottom;
    private JLabel labelTitle,labelUser,labelPass,labelRetypePass,labelHienMK,labelPassCu;
    private JTextField textUser;
    JButton button;
    private JPasswordField password,retypePass,passwordCu;
    public ChangePass(JFrame parent,ActionListener change){
        this.setPreferredSize(new Dimension(700,500));

        labelTitle = new JLabel("Đổi mật khẩu");
        labelTitle.setFont(new Font("Poppins",Font.BOLD,18));
        labelTitle.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        labelTitle.setHorizontalAlignment(JLabel.CENTER);

        panelInput = new JPanel();
        panelInput.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        labelPassCu = new JLabel("Mật khẩu cũ");
        panelInput.add(labelPassCu,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        passwordCu = new JPasswordField();
        passwordCu.setPreferredSize(new Dimension(300,35));
        passwordCu.setBorder(null);
        panelInput.add(passwordCu,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        labelPass = new JLabel("Mật khẩu");
        panelInput.add(labelPass,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        password = new JPasswordField();
        password.setPreferredSize(new Dimension(300,35));
        password.setBorder(null);
        panelInput.add(password,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        labelRetypePass = new JLabel("Nhập lại mật khẩu");
        panelInput.add(labelRetypePass,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        retypePass = new JPasswordField();
        retypePass.setPreferredSize(new Dimension(300,35));
        retypePass.setBorder(null);
        panelInput.add(retypePass,gbc);

        button = new JButton("Xác nhận");
        button.setPreferredSize(new Dimension(250, 40));
        button.setBorder(null);
        button.setFocusable(false);
        button.setBackground(new Color(0,255,119));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(change);

        panelBottom = new JPanel();
        panelBottom.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
        panelBottom.setLayout(new FlowLayout());
        panelBottom.add(button);

        panelContainer = new JPanel();
        panelContainer.setLayout(new BorderLayout());
        panelContainer.add(labelTitle,BorderLayout.NORTH);
        panelContainer.add(panelInput);
        panelContainer.add(panelBottom,BorderLayout.SOUTH);

        this.add(panelContainer);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    public boolean check(){
        char[] passCharCu = passwordCu.getPassword();
        String passwordCu = new String(passCharCu);
        char[] passChar = password.getPassword();
        String pass = new String(passChar);
        char[] reTypeChar = retypePass.getPassword();
        String retype = new String(reTypeChar);
        if(pass.trim().equals("")){
            return false;
        }
        if(retype.trim().equals("")){
            return false;
        }
        if(passwordCu.trim().equals("")){
            return false;
        }
        return true;
    }
    public String[] getUserPass(){
        char[] passCharCu = passwordCu.getPassword();
        String passCu = new String(passCharCu);
        char[] passChar = password.getPassword();
        String pass = new String(passChar);
        char[] reTypeChar = retypePass.getPassword();
        String retype = new String(reTypeChar); 
        String check = "False";
        if(pass.equals(retype)){
            check = "True";
        }
        String maNhomQuyen = "";
        if(UI.manv.substring(0, 2).equals("NV")){
            maNhomQuyen = "NQ_NV";
        }else if(UI.manv.substring(0, 3).equals("QLK")){
            maNhomQuyen = "NQ_QLK";
        }else if(UI.manv.substring(0, 3).equals("QTV")){
            maNhomQuyen = "NQ_ADMIN";
        }
        return new String[] {passCu,pass,maNhomQuyen,retype,check};
    }
}
