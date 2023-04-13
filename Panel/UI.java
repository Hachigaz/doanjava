package Panel;

import SQL.*;
import misc.TaiKhoanDangNhap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;

import Panel.DonNhap.DonNhapUI;
import Panel.TraCuuHang.TraCuuHangCTR;
import Panel.TraCuuHang.TraCuuHangUI;
import Panel.NhaCungCap;

public class UI extends JFrame implements MouseListener{
    JPanel panelLeft,panelTop,panelIcon,panelUser,panelUI,panelTitleBar;
    public JPanel panelRight;
    JLabel label1,labelIcon1,labelIcon2,labelUserName,labelTitle,labelHide,labelClose,labelTitleBar;
    //tao thay Label[] thành Arraylist<Label>
    //thay tên labels thành btnChucNang (đặt tên ngu)
    private ArrayList<JLabel> btnChucNang = new ArrayList<JLabel>();
    //mảng chứa panel chức năng
    private HashMap<JLabel,JPanel> pnlChucNang = new HashMap<JLabel,JPanel>();

    private SQLUser master;
    private TaiKhoanDangNhap tenTKDangNhap;
    public UI(SQLUser master,TaiKhoanDangNhap tkDangNhap){
        this.master= master;
        this.tenTKDangNhap=tkDangNhap;

        this.setSize(1400,750);
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
        
        panelRight = new JPanel();
        panelRight.setBackground(Color.white);
        // Giảm kích thước icon
        ImageIcon icon = new ImageIcon("res/img/logo_kho.png");
        Image imgIcon = icon.getImage();
        Image newImg = imgIcon.getScaledInstance(130,130,java.awt.Image.SCALE_SMOOTH);
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
        panelTop.setPreferredSize(new Dimension(0,120));

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

        labelUserName = new JLabel("Xin chào ");
        labelUserName.setFont(new Font("Monospace",Font.PLAIN,18));
        labelUserName.setForeground(Color.WHITE);
        labelUserName.setVerticalAlignment(JLabel.CENTER);

        labelTitle = new JLabel("PHẦN MỀM QUẢN LÝ KHO");
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setFont(new Font("Monospace",Font.BOLD,25));


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
        
        
        String[] str = {"Danh sách sản phẩm","Nhà cung cấp","Xuất kho","Đơn nhập","Nhân viên"};
        String[] img = {"danhMuc.png","nhaCungCap.png","kho.png","kho.png","users.png"};
        
        DataSet ds = master.getDataQuery("SELECT * FROM khuvuc");
        DataSet ds1 = master.getDataQuery("SELECT * FROM cong_ty");
        themQuyen(new JLabel(str[0]), "res/img/"+img[0], new ThongTinSP(ds));
        themQuyen(new JLabel(str[1]), "res/img/"+img[1], new NhaCungCap(ds));
        themQuyen(new JLabel(str[3]), "res/img/"+img[3], new DonNhapUI(ds));
        themQuyen(new JLabel(str[4]), "res/img/"+img[4], new NhanVien(master,tkDangNhap));
        TraCuuHangCTR cnTraCuuHang = new TraCuuHangCTR(master, tkDangNhap);
        themQuyen(new JLabel("Hàng trong kho"),"res/img/danhSach.png", cnTraCuuHang.getUI());
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==labelHide){
            this.setExtendedState(JFrame.ICONIFIED); 
        }else if(e.getSource()==labelClose){
            this.dispose();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //duyệt mảng panel coi cái nút nào đc bấm thì hiện lên
        for(int i = 0 ; i < btnChucNang.size();i++){
            JPanel panel = pnlChucNang.get(btnChucNang.get(i));
            if(panel!=null){
                if(e.getSource()==btnChucNang.get(i)){
                    panel.setVisible(true);
                }
                else{
                    panel.setVisible(false);
                }
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        for(int i=0;i<btnChucNang.size();i++){
            if(e.getSource()==btnChucNang.get(i)){
                btnChucNang.get(i).setBackground(new Color(223,18,133));
                btnChucNang.get(i).setForeground(Color.white);
                btnChucNang.get(i).setOpaque(true);
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
        for(int i=0;i<btnChucNang.size();i++){
            if(e.getSource()==btnChucNang.get(i)){
                btnChucNang.get(i).setOpaque(false);
                btnChucNang.get(i).setBackground(null);
                btnChucNang.get(i).setForeground(Color.BLACK);
            }
        }
        if(e.getSource()==labelHide){
            labelHide.setBackground(new Color(255,209,67));
        }else if(e.getSource()==labelClose){
            labelClose.setBackground(new Color(255,209,67));
        }
    }
    public void themQuyen(JLabel label,String duongDanIcon,JPanel panel){
        //chỉnh độ lớn của panel
        panel.setPreferredSize(new Dimension(panelRight.getSize().width-10,panelRight.getSize().height-10));

        ImageIcon icon = new ImageIcon(duongDanIcon);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);

        label.setBackground(Color.red);
        label.setOpaque(false);
        label.setBorder(null);
        label.setForeground(Color.black);
        label.setPreferredSize(new Dimension(190,40));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setFont(new Font("Monospace",Font.PLAIN,15));
        label.addMouseListener(this);
        label.setIcon(newIcon);

        btnChucNang.add(label);
        panelLeft.add(label);

        pnlChucNang.put(label,panel);
        panelRight.add(panel);
        panel.setVisible(false);
    }
}
