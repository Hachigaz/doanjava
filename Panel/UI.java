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

public class UI extends JFrame implements MouseListener{
    JPanel panelLeft,panelRight,panelTop,panelIcon,panelUser,panelUI,panelTitleBar;
    JLabel label1,labelIcon1,labelIcon2,labelUserName,labelTitle,labelHide,labelClose,labelTitleBar;
    public String[] str = {"Danh sách sản phẩm","Nhà cung cấp","Xuất kho","Đơn nhập","Nhân viên"};
    public String[] img = {"danhMuc.png","nhaCungCap.png","kho.png","kho.png","users.png"};
    JLabel[] labels = new JLabel[5];
    SQLUser hanndler;
    TaoDonNhap tdn;
    DanhMucSP dmsp;
    NhanVien dsnv;
    public UI(SQLUser handler){
        this.hanndler= handler;
        this.setSize(1300,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
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
        for(int i=0;i<5;i++){
            ImageIcon icon = new ImageIcon("res/img/"+img[i]);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newImg);

            labels[i] = new JLabel(str[i]);
            labels[i].setOpaque(false);
            labels[i].setBorder(null);
            labels[i].setForeground(Color.black);
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
        panelTop.setPreferredSize(new Dimension(0,140));

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

        labelTitle = new JLabel("PHẦN MỀM QUẢN LÝ KHO");
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setFont(new Font("Monospace",Font.BOLD,25));

        DataSet danhMucSanPham = handler.getDataQuery("select * from loai_hang");
        dmsp = new DanhMucSP(danhMucSanPham);
        panelRight.add(dmsp);

        DataSet danhSachNhanVien = handler.getDataQuery("select * from nhanvien");
        dsnv = new NhanVien(danhSachNhanVien);
        panelRight.add(dsnv);

        DataSet taoDonNhap = handler.getDataQuery("select * from  donnhap");
        tdn = new TaoDonNhap(taoDonNhap);
        tdn.setVisible(false);
        panelRight.add(tdn);

        panelUser.add(labelIcon2);
        panelUser.add(labelUserName);

        panelTop.add(labelIcon1,BorderLayout.WEST);
        panelTop.add(panelUser,BorderLayout.EAST);
        panelTop.add(labelTitle,BorderLayout.CENTER);
        
        panelUI = new JPanel();
        panelUI.setLayout(new BorderLayout());

        panelUI.add(panelLeft,BorderLayout.WEST);
        panelUI.add(panelRight);
        panelUI.add(panelTop,BorderLayout.NORTH);

        // Thanh title bar phía trên
        panelTitleBar = new JPanel();
        panelTitleBar.setPreferredSize(new Dimension(0,30));
        panelTitleBar.setBackground(new Color(255,209,67));
        panelTitleBar.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));

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
        labelHide.setBackground(new Color(255,209,67));
        labelHide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelHide.setOpaque(true);
        labelHide.addMouseListener(this);

        labelClose.setIcon(newIconClose);
        labelClose.setHorizontalAlignment(JLabel.CENTER);
        labelClose.setPreferredSize(new Dimension(30,20));
        labelClose.setBackground(new Color(255,209,67));
        labelClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelClose.setOpaque(true);
        labelClose.addMouseListener(this);

        panelTitleBar.add(labelHide);
        panelTitleBar.add(labelClose);

        this.add(panelUI,BorderLayout.CENTER);
        this.add(panelTitleBar,BorderLayout.NORTH);

        this.setVisible(true);
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
        if(e.getSource()==labels[0]){
            dmsp.setVisible(true);
            dsnv.setVisible(false);
            tdn.setVisible(false);
        }else if(e.getSource()==labels[1]){
            dmsp.setVisible(false);
            dsnv.setVisible(false);
            tdn.setVisible(false);
        }else if(e.getSource()==labels[2]){
            dmsp.setVisible(false);
            dsnv.setVisible(false);
            tdn.setVisible(false);
        }else if(e.getSource()==labels[3]){
            dmsp.setVisible(false);
            dsnv.setVisible(false);
            tdn.setVisible(true);
        }else if(e.getSource()==labels[4]){
            dmsp.setVisible(false);
            dsnv.setVisible(true);
            tdn.setVisible(false);
        }
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
        if(e.getSource()==labelHide){
            labelHide.setBackground(Color.gray);
        }else if(e.getSource()==labelClose){
            labelClose.setBackground(Color.red);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        for(int i=0;i<str.length;i++){
            if(e.getSource()==labels[i]){
                labels[i].setOpaque(false);
                labels[i].setBackground(null);
                labels[i].setForeground(Color.BLACK);
            }
        }
        if(e.getSource()==labelHide){
            labelHide.setBackground(new Color(255,209,67));
        }else if(e.getSource()==labelClose){
            labelClose.setBackground(new Color(255,209,67));
        }
    }
}