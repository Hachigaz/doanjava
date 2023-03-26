package Panel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;

public class Test extends JFrame implements MouseListener{
    JPanel panelLeft,panelRight,panelTop,panelIcon,panelUser;
    public String[] str = {"Danh sách sản phẩm","Danh mục sản phẩm","Khách hàng","Nhà cung cấp","Nhập/Xuất kho"};
    public String[] img = {"danhMuc.png","danhSach.png","users.png","nhaCungCap.png","kho.png"};
    JLabel[] labels = new JLabel[5];
    JLabel label1,labelIcon1,labelIcon2,labelUserName,labelTitle;
    Test(){
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
                    0, 0, new Color(0,171,255),
                    0, getHeight(), new Color(100,100,100)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelLeft.setPreferredSize(new Dimension(190,0));
        panelLeft.setBackground(new Color(7, 140, 217));
        panelLeft.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        for(int i=0;i<5;i++){
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
                    0, 0, new Color(102,0,255),
                    0, getHeight(), new Color(0,171,255)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelTop.setLayout(new BorderLayout());
        panelTop.setPreferredSize(new Dimension(0,130));

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
