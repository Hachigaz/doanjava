package DangNhap;
import javax.swing.*;

import misc.ThongBaoDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Enumeration;

public class ChonURL extends JFrame implements ActionListener{
    JRadioButton luachon1;
    JRadioButton luachon2;
    ButtonGroup group;
    JPanel panel;
    String luachon;

    JButton button;
    public ChonURL(){
        super("Lựa chọn kết nối");
        panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(500, 300));

        luachon = null;
        luachon1 = new JRadioButton("localhost");
        luachon2 = new JRadioButton("IP của Huy gay");
        luachon1.setActionCommand("localhost1");
        luachon2.setActionCommand("ip");

        group = new ButtonGroup(); 
        group.add(luachon1);
        group.add(luachon2);

        panel.add(luachon1);
        layout.putConstraint(SpringLayout.NORTH, luachon1, 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, luachon1, 10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.SOUTH, luachon2, 40, SpringLayout.SOUTH, luachon1);
        layout.putConstraint(SpringLayout.WEST, luachon2, 10, SpringLayout.WEST, this);
        panel.add(luachon2);

        button = new JButton("Tiếp");
        button.addActionListener(this);

        
        layout.putConstraint(SpringLayout.NORTH, button, -100, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, button, 50, SpringLayout.WEST, this);
        panel.add(button);

        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        Enumeration<AbstractButton> buttons = group.getElements();
        while (buttons.hasMoreElements()) {
            JRadioButton button = (JRadioButton) buttons.nextElement();
            if (button.isSelected()) {
                this.luachon = button.getActionCommand();
                this.dispose();
                break;
            }
        }
        if(luachon == null){
            new ThongBaoDialog("Vui lòng nhập lựa chọn đi thằng ngu",this);
        }
    }
    public String getLuachon() {
        return luachon;
    }
}
