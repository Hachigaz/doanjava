package Panel;

import SQL.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import Function.*;
import Login.FormDN;
import Quyen.*;
import Quyen.XemDSMH.DSMHPanel;

public class UI extends JFrame implements MouseListener{
    JPanel panelLeft,panelTop,panelIcon,panelUser,panelUI,panelTitleBar;
    public static JPanel panelRight;
    JLabel label1,labelIcon1,labelIcon2,labelUserName,labelTitle,labelHide,labelClose,labelTitleBar;
    public String[] str = {"Danh sách sản phẩm","Nhà cung cấp","Xuất kho","Đơn nhập","Nhân viên"};
    public String[] img = {"danhMuc.png","nhaCungCap.png","kho.png","kho.png","users.png"};
    //tao thay Label[] thành Arraylist<Label>
    //thay tên labels thành btnChucNang (đặt tên ngu)
    ArrayList<JLabel> btnChucNang = new ArrayList<JLabel>();
    //mảng chứa panel chức năng
    HashMap<JLabel,JPanel> pnlChucNang = new HashMap<JLabel,JPanel>();
    SQLUser hanndler;

    public UI(SQLUser handler){
        this.hanndler= handler;
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
        //code j đây
        for(int i=0;i<5;i++){
            ImageIcon icon = new ImageIcon("res/img/"+img[i]);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newImg);

            btnChucNang.add(new JLabel(str[i]));
            btnChucNang.get(i).setOpaque(false);
            btnChucNang.get(i).setBorder(null);
            btnChucNang.get(i).setForeground(Color.black);
            btnChucNang.get(i).setPreferredSize(new Dimension(190,40));
            btnChucNang.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnChucNang.get(i).setFont(new Font("Monospace",Font.PLAIN,15));
            btnChucNang.get(i).addMouseListener(this);
            panelLeft.add(btnChucNang.get(i));

            btnChucNang.get(i).setIcon(newIcon);
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

        labelUserName = new JLabel("Xin chào "+FormDN.getUsernameInput());
        labelUserName.setFont(new Font("Monospace",Font.PLAIN,18));
        labelUserName.setForeground(Color.WHITE);
        labelUserName.setVerticalAlignment(JLabel.CENTER);

        labelTitle = new JLabel("PHẦN MỀM QUẢN LÝ KHO");
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setFont(new Font("Monospace",Font.BOLD,25));


        //thêm panel chức năng vào mảng
        DataSet danhMucSanPham = handler.getDataQuery("select * from mat_hang");
        pnlChucNang.put(btnChucNang.get(0),new DanhMucSP(danhMucSanPham));

        DataSet danhSachNhanVien = handler.getDataQuery("select MaNV as 'Mã nhân viên', TenNV as 'Tên nhân viên', MaCV as 'Mã chức vụ', GioiTinh as 'Giới tính', NgaySinh as 'Ngày sinh', DiaChi as 'Địa chỉ', Kho_lam_viec as 'Kho làm việc' from nhanvien");
        pnlChucNang.put(btnChucNang.get(4),new NhanVien(danhSachNhanVien));
        
        DataSet danhSachNhaCungCap = handler.getDataQuery("select MaCty as 'Mã công ty', TenCty as 'Tên công ty', DiaChi as 'Địa chỉ', SDT as 'SDT' from cong_ty");
        pnlChucNang.put(btnChucNang.get(1),new NhaCungCap(danhSachNhaCungCap));

        DataSet taoDonNhap = handler.getDataQuery("select * from  donnhap");
        pnlChucNang.put(btnChucNang.get(3),new TaoDonNhap(taoDonNhap));

        //thêm mấy cái panel zo right panel
        //duyệt bảng băm
        for(int i = 0 ; i<btnChucNang.size();i++){
            //lấy panel có khoá là nút chức năng của panel đó
            JPanel panel = pnlChucNang.get(btnChucNang.get(i));
            //nếu kết quả trả về không phải là rỗng
            if(panel != null){
                //thì thêm cái panel zo rightpanel xong setvisible false
                panelRight.add(panel);
                panel.setVisible(false);
            }
        }

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

        //của tao
        themBtnChucNang(new QuyenDSMH());

        JLabel cho = new JLabel("Cho hien");
        JPanel hien = new JPanel();
        cho.setBackground(Color.red);
        cho.setOpaque(false);
        cho.setBorder(null);
        cho.setForeground(Color.black);
        cho.setPreferredSize(new Dimension(190,40));
        cho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cho.setFont(new Font("Monospace",Font.PLAIN,15));
        cho.addMouseListener(this);

        hien.setBackground(Color.red);
        hien.setPreferredSize(new Dimension(100,200));
        themQuyen(cho, new DanhMucSP(danhMucSanPham));
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
    public void themQuyen(JLabel label,JPanel panel){
        btnChucNang.add(label);
        panelLeft.add(label);

        pnlChucNang.put(label,panel);
        panelRight.add(panel);
        panel.setVisible(false);
    }
    //thêm nút cho quyền
    public void themBtnChucNang(Quyen quyen){
        //tạo nút
        JLabel label = new JLabel(quyen.title);
        label.setOpaque(false);
        label.setBorder(null);
        label.setForeground(Color.black);
        label.setPreferredSize(new Dimension(190,40));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setFont(new Font("Monospace",Font.PLAIN,15));
        label.addMouseListener(this);
        //thêm vào mãng nút chức năng
        btnChucNang.add(label);
        //thêm vào left panel
        panelLeft.add(label);
        
        panelLeft.revalidate();
        panelLeft.repaint();
    }
}
