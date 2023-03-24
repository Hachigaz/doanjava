package Panel;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Flow;
import java.awt.*;

public class Test extends JFrame implements MouseListener{
    JPanel panelLeft,panelRight,panelTop,panelIcon,panelUser;
    public String[] str = {"Danh sách sản phẩm","Danh mục sản phẩm","Khách hàng","Nhà cung cấp","Nhập/Xuất kho"};
    JLabel[] labels = new JLabel[5];
    JLabel label1,labelIcon1,labelIcon2,labelUserName;
    Test(){
        this.setSize(1100,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(170,0));
        panelLeft.setBackground(new Color(7, 140, 217));
        panelLeft.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

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

        for(int i=0;i<5;i++){
            labels[i] = new JLabel(str[i]);
            labels[i].setBackground(new Color(7, 140, 217));
            labels[i].setForeground(Color.white);
            labels[i].setPreferredSize(new Dimension(170,40));
            labels[i].setHorizontalAlignment(JLabel.CENTER);
            labels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            labels[i].setFont(new Font("Monospace",Font.PLAIN,15));
            labels[i].setOpaque(true);
            labels[i].addMouseListener(this);
            panelLeft.add(labels[i]);
        }
        panelRight = new JPanel();
        panelRight.setBackground(Color.white);
        // Giảm kích thước icon
        ImageIcon icon = new ImageIcon("res/img/logo_kho.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(130,130,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        labelIcon1 = new JLabel();
        labelIcon1.setIcon(newIcon);

        panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.setBackground(new Color(7, 140, 217));
        panelTop.setPreferredSize(new Dimension(0,130));

        panelUser = new JPanel();
        panelUser.setPreferredSize(new Dimension(250,170));
        panelUser.setBackground(new Color(7, 140, 217));

        ImageIcon icon2 = new ImageIcon("res/img/user.png");
        Image img2 = icon2.getImage();
        Image newImg2 = img2.getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon2 = new ImageIcon(newImg2);
        labelIcon2 = new JLabel();
        labelIcon2.setIcon(newIcon2);
        labelIcon2.setVerticalAlignment(JLabel.CENTER);

        labelUserName = new JLabel("Xin chào user name");
        labelUserName.setFont(new Font("Monospace",Font.PLAIN,18));
        labelUserName.setForeground(Color.WHITE);
        labelUserName.setVerticalAlignment(JLabel.CENTER);

        // panelUser.setLayout(new FlowLayout(FlowLayout.CENTER,15,0));

        panelUser.add(labelIcon2);
        panelUser.add(labelUserName);

        panelTop.add(labelIcon1,BorderLayout.WEST);
        panelTop.add(panelUser,BorderLayout.EAST);

        this.add(panelLeft,BorderLayout.WEST);
        this.add(panelRight);
        this.add(panelTop,BorderLayout.NORTH);

        this.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==labels[0]){
            panelRight.setBackground(Color.BLACK);
        }else if(e.getSource()==labels[1]){
            panelRight.setBackground(Color.WHITE);
        }else if(e.getSource()==labels[2]){
            panelRight.setBackground(Color.ORANGE);
        }else if(e.getSource()==labels[3]){
            panelRight.setBackground(Color.LIGHT_GRAY);
        }else if(e.getSource()==labels[4]){
            panelRight.setBackground(Color.yellow);
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
                labels[i].setBackground(new Color(7, 140, 217));
                labels[i].setForeground(Color.white);
            }
        }
    }
}
