package Panel;

import SQL.*;
import misc.DataSet;
import misc.TitleFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import DAL.DataAccessLayer;
import DTO.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;

import GUI.DonNhapUI;

import Panel.Donxuat.DonXuatCTR;
import Panel.NhanVien.ChangePass;
import Panel.NhanVien.NhanVienBLL;
import Panel.NhanVien.NhanVienUI2;
import Panel.ThongTinKho.ThongTinKhoUI;
import Panel.TraCuuHang.TraCuuHangUI;
import Program.Program;
import Panel.Account.ThongKeUI;
import Panel.CongTy2.CongTy2UI;
import Panel.DonNhap.DonNhap2Ui;
public class UI extends TitleFrame implements MouseListener{
    NhanVienBLL nhanVienBLL = new NhanVienBLL();
    JPanel panelLeft,panelTop,panelIcon,panelUser,panelUI;
    public JPanel panelRight;
    JLabel label1,labelIcon1,labelIcon2,labelUserName,labelTitle,labelTitleBar;
    JPopupMenu popupMenu;
    JLabel labelSetting;
    private ChangePass formChange;
    //tao thay Label[] thành Arraylist<Label>
    private ArrayList<JLabel> btnChucNang = new ArrayList<JLabel>();
    //mảng chứa panel chức năng
    private HashMap<JLabel,JPanel> pnlChucNang = new HashMap<JLabel,JPanel>();

    public static SQLUser master;
    public static Taikhoan_nhanvienMD tenTKDangNhap;
    public static NhanvienMD nvDangNhap;
    public static KhoMD khoNVDangNhap;

    private DataAccessLayer<KhoMD> khoDAL;
    private DataAccessLayer<NhanvienMD> nvDAL;
    private DataAccessLayer<ChitietnhomquyenMD> ctNhomQuyenDAL;
    public static String manv,tenDN,matkhaucu;
    public UI(SQLUser master,Taikhoan_nhanvienMD tkDangNhap){
        UI.master= master;
        UI.tenTKDangNhap=tkDangNhap;

        khoDAL=  new DataAccessLayer<>(master, KhoMD.class);
        nvDAL = new DataAccessLayer<>(master, NhanvienMD.class);
        ctNhomQuyenDAL = new DataAccessLayer<>(master, ChitietnhomquyenMD.class);

        //lấy kho đăng nhập
        UI.khoNVDangNhap =  khoDAL.getFirst("MaKho = "+nvDAL.getFirst("MaNV="+tkDangNhap.getMaNV()).getKho_lam_viec());
        UI.nvDangNhap = nvDAL.getFirst("MaNV = "+tkDangNhap.getMaNV());
        // manv = nhanVienBLL.layMaNV(nvDangNhap.getTenNV());
        // tenDN = nhanVienBLL.layTaiKhoan(manv)[1];
        // matkhaucu = nhanVienBLL.layTaiKhoan(manv)[2];
        this.setSize(1400,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);

        panelLeft = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(4,155,254),
                    0, getHeight(), new Color(37,83,111)
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
        panelRight.setLayout(new CardLayout());
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
                    0, 0, new Color(0,155,254),
                    0, getHeight(), new Color(4,156,255)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelTop.setLayout(new BorderLayout());
        panelTop.setPreferredSize(new Dimension(0,120));
 
        panelUser = new JPanel();
        panelUser.setPreferredSize(new Dimension(200,170));
        panelUser.setOpaque(false);
        panelUser.setLayout(new FlowLayout());
        panelUser.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        ImageIcon icon2 = new ImageIcon("res/img/user.png");
        Image img2 = icon2.getImage();
        Image newImg2 = img2.getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon2 = new ImageIcon(newImg2);
        labelIcon2 = new JLabel();
        labelIcon2.setIcon(newIcon2);
        labelIcon2.setVerticalAlignment(JLabel.CENTER);

        NhanvienMD nvDangNhap = nvDAL.getTable("MaNV="+tkDangNhap.getMaNV()).get(0);
        
        labelUserName = new JLabel("Xin chào "+nvDangNhap.getTenNV());
        labelUserName.setFont(new Font("Monospace",Font.PLAIN,18));
        labelUserName.setForeground(Color.WHITE);

        labelTitle = new JLabel("PHẦN MỀM QUẢN LÝ KHO");
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setFont(new Font("Monospace",Font.BOLD,25));

        labelSetting = new JLabel("Đăng xuất/Đổi mật khẩu");
        ImageIcon iconST = new ImageIcon("res/img/TaiKhoan.png");
        Image imgST = iconST.getImage();
        Image newImgST = imgST.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconST = new ImageIcon(newImgST);
        labelSetting.setIcon(newIconST);
        labelSetting.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        labelSetting.setBackground(new Color(4,155,254));
        labelSetting.setFocusable(false);
        labelSetting.setVisible(false);
        labelSetting.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        labelSetting.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                popupMenu.show(labelSetting, 0, labelSetting.getHeight());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        JMenuItem logoutItem = new JMenuItem("Đăng xuất");
        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc rằng bạn muốn đăng xuất không?", "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    Program.program.dangNhap();
                    dispose();
                }else{

                }
            }
        });
        JMenuItem changePasswordItem = new JMenuItem("Đổi mật khẩu");
        changePasswordItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createForm();
            }
        });
        popupMenu = new JPopupMenu();
        popupMenu.add(logoutItem);
        popupMenu.add(changePasswordItem);

        panelUser.add(labelIcon2);
        panelUser.add(labelUserName);
        panelUser.add(labelSetting);

        panelTop.add(labelIcon1,BorderLayout.WEST);
        panelTop.add(panelUser,BorderLayout.EAST);
        panelTop.add(labelTitle,BorderLayout.CENTER);
        
        panelUI = new JPanel();
        panelUI.setLayout(new BorderLayout());

        panelUI.add(panelLeft,BorderLayout.WEST);
        panelUI.add(panelRight);
        panelUI.add(panelTop,BorderLayout.NORTH);

        // set content panel cho titleframe
        this.setContentPane(panelUI);
        this.setVisible(true);
        
        
        String[] str = {"Nhà cung cấp","Xuất kho","Đơn nhập","Nhân viên"};
        String[] img = {"nhaCungCap.png","kho.png","kho.png","users.png"};
        

        DataSet dsdonnhap = master.getDataQuery("SELECT * FROM donnhap");
        //do lon panel chuc nang
        Dimension panelRightSize = new Dimension(panelRight.getSize().width-14,panelRight.getSize().height-16);
        DonXuatCTR cnDonXuat = new DonXuatCTR(master, tkDangNhap,panelRightSize);
        //them quyen
        for(ChitietnhomquyenMD quyenTK : ctNhomQuyenDAL.getTable("MaNhomQuyen = "+tkDangNhap.getMaNhomQuyen())){
            switch(quyenTK.getMaQuyen()){
                case "Q1":
                    themQuyen(new JLabel("Nhân viên"), "res/img/username.png", new NhanVienUI2(panelRightSize));
                    break;
                case "Q2":
                    themQuyen(new JLabel("Công Ty"), "res/img/username.png", new CongTy2UI(panelRightSize));
                    break;
                case "Q3":
                    themQuyen(new JLabel("Thông tin kho"), "res/img/kho.png", new ThongTinKhoUI());
                    break;
                case "Q4":
                    themQuyen(new JLabel("Hàng trong kho"),"res/img/danhSach.png", new TraCuuHangUI(panelRightSize));
                    break;
                case "Q5":
                    themQuyen(new JLabel(str[2]), "res/img/"+img[2], new DonNhapUI(dsdonnhap));
                    break;
                case "Q6":
                    themQuyen(new JLabel("Đơn xuất"),"res/img/danhSach.png", cnDonXuat.getUI());
                    break;
            }
        }
        // themQuyen(new JLabel(str[1]), "res/img/"+img[1], new NhaCungCap(master,tkDangNhap));
        themQuyen(new JLabel(str[2]), "res/img/"+img[2], new DonNhapUI(dsdonnhap));
        //DonXuatCTR cnDonXuat = new DonXuatCTR(master, tkDangNhap,panelRightSize);
        themQuyen(new JLabel("Đơn xuất"),"res/img/danhSach.png", cnDonXuat.getUI());
        //themQuyen(new JLabel(str[4]), "res/img/"+img[4], new NhanVien(master,tkDangNhap,panelRightSize));
        // NhanVienCTR cnNhanVien = new NhanVienCTR(master, tkDangNhap, panelRightSize);
        themQuyen(new JLabel("Nhân viên"), "res/img/username.png", new NhanVienUI2(panelRightSize));
        themQuyen(new JLabel("Công Ty"), "res/img/username.png", new CongTy2UI(panelRightSize));
        themQuyen(new JLabel("Hàng trong kho"),"res/img/danhSach.png", new TraCuuHangUI(panelRightSize));
        themQuyen(new JLabel("Đơn nhập 2"),"res/img/danhSach.png", new DonNhap2Ui(panelRightSize));
        //themQuyen(new JLabel("Công Ty"),"res/img/danhSach.png", new CongTyUI(panelRightSize));
        themQuyen(new JLabel("Thông tin kho"), "res/img/kho.png", new ThongTinKhoUI());
    }
    public void createForm(){
        Window window = SwingUtilities.getWindowAncestor(this);
        formChange = new ChangePass((JFrame) window, addButtonAction);
        formChange.setVisible(true);
    }
    final String manvTemp = manv;
    final String tenDNTemp = tenDN;
    final String matkhaucuTemp = matkhaucu; 
    ActionListener addButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] data = formChange.getUserPass();
            if(formChange.check()==false){
                JOptionPane.showMessageDialog(formChange, "Mời bạn nhập đầy đủ thông tin");
            }else{
                if(data[0].equals(matkhaucuTemp) == false){
                    JOptionPane.showMessageDialog(formChange, "Bạn nhập sai mật khẩu cũ");
                }else if(data[4] == "False"){
                    JOptionPane.showMessageDialog(formChange, "Bạn nhập mật khẩu không hợp lệ!");
                }else{
                    // nhanVienBLL.suaTK("MaNV = "+manvTemp, "TenTaiKhoan = "+tenDNTemp," MatKhau = "+data[1],"MaNhomQuyen = "+data[2]);
                    JOptionPane.showMessageDialog(formChange, "Đổi mật khẩu thành công");
                    formChange.dispose();
                }
            }
        }
    };
    @Override
    public void mouseClicked(MouseEvent e) {
        
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
    }
    @Override
    public void mouseExited(MouseEvent e) {
        for(int i=0;i<btnChucNang.size();i++){
            if(e.getSource()==btnChucNang.get(i)){
                btnChucNang.get(i).setOpaque(false);
                btnChucNang.get(i).setBackground(null);
                btnChucNang.get(i).setForeground(Color.white);
            }
        }
    }
    public void themQuyen(JLabel label,String duongDanIcon,JPanel panel){
        //chỉnh độ lớn của panel

        ImageIcon icon = new ImageIcon(duongDanIcon);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);

        label.setBackground(Color.red);
        label.setOpaque(false);
        label.setBorder(null);
        label.setForeground(Color.white);
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
