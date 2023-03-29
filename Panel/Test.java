package Panel;

import SQL.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import Function.*;

public class Test extends JFrame implements MouseListener{
    JPanel panelLeft,panelRight,panelTop,panelIcon,panelUser;
<<<<<<< Updated upstream
    public String[] str = {"Danh sách sản phẩm","Danh mục sản phẩm","Khách hàng","Nhà cung cấp","Nhập/Xuất kho"};
    public String[] img = {"danhMuc.png","danhSach.png","users.png","nhaCungCap.png","kho.png"};
    JLabel[] labels = new JLabel[5];
<<<<<<< Updated upstream
    JLabel label1,labelIcon1,labelIcon2,labelUserName,labelTitle;
=======
    JLabel label1,labelIcon1,labelIcon2,labelUserName,labelTitle,labelHide,labelClose;
>>>>>>> Stashed changes
    public Test(){
=======
    public String[] str = {"Danh sách sản phẩm","Danh mục sản phẩm","Khách hàng","Nhà cung cấp","Nhập/Xuất kho","Đơn nhập"};
    public String[] img = {"danhMuc.png","danhSach.png","users.png","nhaCungCap.png","kho.png","kho.png"};
    JLabel[] labels = new JLabel[6];
    JLabel label1,labelIcon1,labelIcon2,labelUserName,labelTitle;
    SQLHandler hanndler;
    public Test(SQLHandler handler){
        this.hanndler= handler;
>>>>>>> Stashed changes
        this.setSize(1100,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        panelLeft = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(255,197,70),
                    0, getHeight(), new Color(255,145,83)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelLeft.setPreferredSize(new Dimension(190,0));
        panelLeft.setBackground(new Color(7, 140, 217));
        panelLeft.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        for(int i=0;i<6;i++){
            ImageIcon icon = new ImageIcon("res/img/"+img[i]);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newImg);

            labels[i] = new JLabel(str[i]);
            labels[i].setOpaque(false);
            labels[i].setBorder(null);
            labels[i].setForeground(Color.white);
            labels[i].setPreferredSize(new Dimension(190,40));
            labels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            labels[i].setFont(new Font("Monospace",Font.PLAIN,15));
            labels[i].addMouseListener(this);
            panelLeft.add(labels[i]);

            labels[i].setIcon(newIcon);
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
        labelIcon1.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        panelTop = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(255,209,67),
                    0, getHeight(), new Color(255,197,70)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelTop.setLayout(new BorderLayout());
        panelTop.setPreferredSize(new Dimension(0,160));
        panelTop.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));

        panelUser = new JPanel();
        panelUser.setPreferredSize(new Dimension(250,170));
        panelUser.setOpaque(false);

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

        labelTitle = new JLabel("Phần mềm quản lý kho");
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setFont(new Font("Monospace",Font.BOLD,25));
        // panelUser.setLayout(new FlowLayout(FlowLayout.CENTER,15,0));

        //Chuc nang
        DataSet ds = handler.query("select * from mat_hang");
        TaoDonNhap dn = new TaoDonNhap(ds);
        panelRight.add(dn);

        panelUser.add(labelIcon2);
        panelUser.add(labelUserName);

        panelTop.add(labelIcon1,BorderLayout.WEST);
        panelTop.add(panelUser,BorderLayout.EAST);
        panelTop.add(labelTitle,BorderLayout.CENTER);
        

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
        }else if (e.getSource()==labels[5]){
            panelRight.setVisible(true);
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
                labels[i].setBackground(new Color(223,18,133));
                labels[i].setForeground(Color.white);
                labels[i].setOpaque(true);
            }
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        for(int i=0;i<str.length;i++){
            if(e.getSource()==labels[i]){
                labels[i].setOpaque(false);
                labels[i].setBackground(null);
            }
        }
    }
}
