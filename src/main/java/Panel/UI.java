package Panel;

import SQL.*;
import misc.TitleFrame;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import BLL.NhanVienBLL;
import DAL.DataAccessLayer;
import DTO.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;

import GUI.CongTy2UI;
import GUI.DonNhapUI;
import GUI.DonXuatUI;
import GUI.NhanVienUI2;
import GUI.ThongKeUI;
import GUI.ThongTinKhoUI;
import GUI.TraCuuHangUI;
import Panel.NhanVien.ChangePass;
import Program.Program;
public class UI extends TitleFrame implements MouseListener{
    JPanel panelLeft,panelTop,panelIcon,panelUser,panelUI;
    public JPanel panelRight;
    JLabel label1,labelIcon1,labelIcon2,labelUserName,labelTitle,labelTitleBar;
    JPopupMenu popupMenu;
    JLabel labelSetting;
    ChangePass formChange;
    //tao thay Label[] thành Arraylist<Label>
    private ArrayList<JLabel> btnChucNang = new ArrayList<JLabel>();
    //mảng chứa panel chức năng
    private HashMap<JLabel,JPanel> pnlChucNang = new HashMap<JLabel,JPanel>();

    public static SQLUser master;
    public static Taikhoan_nhanvienMD tenTKDangNhap;
    public static NhanvienMD nvDangNhap;
    public static KhoMD khoNVDangNhap;
    
    public static String manv,tenDN,matkhau,manhomquyen;
    public static String maKho;

    private DataAccessLayer<KhoMD> khoDAL;
    private DataAccessLayer<NhanvienMD> nvDAL;
    private DataAccessLayer<ChitietnhomquyenMD> ctNhomQuyenDAL;
    private NhanVienBLL nhanVienBLL;


    public UI(SQLUser master,Taikhoan_nhanvienMD tkDangNhap){
        UI.master= master;
        UI.tenTKDangNhap=tkDangNhap;

        nhanVienBLL = new NhanVienBLL();

        manv = tkDangNhap.getMaNV();
        tenDN = tkDangNhap.getTenTaiKhoan();
        matkhau = tkDangNhap.getMatKhau();
        manhomquyen = tkDangNhap.getMaNhomQuyen();

        khoDAL=  new DataAccessLayer<>(master, KhoMD.class);
        nvDAL = new DataAccessLayer<>(master, NhanvienMD.class);
        ctNhomQuyenDAL = new DataAccessLayer<>(master, ChitietnhomquyenMD.class);

        //lấy kho đăng nhập

        UI.khoNVDangNhap =  khoDAL.getFirst("MaKho = "+nvDAL.getFirst("MaNV="+tkDangNhap.getMaNV()).getKho_lam_viec());
        UI.nvDangNhap = nvDAL.getFirst("MaNV = "+tkDangNhap.getMaNV());

        maKho = nvDangNhap.getKho_lam_viec();

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
        panelUser.setPreferredSize(new Dimension(400,170));
        panelUser.setOpaque(false);
        panelUser.setLayout(new BorderLayout());
        panelUser.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        NhanvienMD nvDangNhap = nvDAL.getTable("MaNV="+tkDangNhap.getMaNV()).get(0);
        
        labelUserName = new JLabel("Xin chào "+nvDangNhap.getTenNV());
        labelUserName.setFont(new Font("Monospace",Font.PLAIN,18));
        labelUserName.setHorizontalAlignment(JLabel.RIGHT);
        labelUserName.setForeground(Color.WHITE);
        labelUserName.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));

        labelTitle = new JLabel("PHẦN MỀM QUẢN LÝ KHO");
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setFont(new Font("Monospace",Font.BOLD,25));

        labelSetting = new JLabel("Đăng xuất/Đổi mật khẩu");
        ImageIcon iconST = new ImageIcon("res/img/logout.png");
        Image imgST = iconST.getImage();
        Image newImgST = imgST.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconST = new ImageIcon(newImgST);
        labelSetting.setIcon(newIconST);
        labelSetting.setBackground(new Color(4,155,254));
        labelSetting.setFocusable(false);
        labelSetting.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelSetting.setHorizontalAlignment(JLabel.RIGHT);
        labelSetting.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        labelSetting.setForeground(Color.white);

        JMenuItem logoutItem = new JMenuItem("Đăng xuất");
        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn là muốn đăng xuất không", "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    Program.program.dangNhap();
                    dispose();
                }else{

                }
            }
            
        });

        JMenuItem changePasswordItem = new JMenuItem("Đổi mật khẩu");
        changePasswordItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Thực hiện các thao tác khi nhấn "Đổi mật khẩu" ở đây
                // JOptionPane.showMessageDialog(null, "Bạn muốn đổi mật khẩu");
                createForm();
            }
        });

        popupMenu = new JPopupMenu();
        popupMenu.add(logoutItem);
        popupMenu.add(changePasswordItem);

        labelSetting.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                // int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn là muốn đăng xuất không", "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
                // if (dialogResult == JOptionPane.YES_OPTION) {
                //     Program.program.dangNhap();
                //     dispose();
                // }else{

                // }
                popupMenu.show(labelSetting, 250, labelSetting.getHeight());
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

        panelUser.add(labelUserName,BorderLayout.NORTH);
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
        
        

        //do lon panel chuc nang
        Dimension panelRightSize = new Dimension(panelRight.getSize().width-14,panelRight.getSize().height-16);
        //them quyen
        for(ChitietnhomquyenMD quyenTK : ctNhomQuyenDAL.getTable("MaNhomQuyen = "+tkDangNhap.getMaNhomQuyen())){
            switch(quyenTK.getMaQuyen()){
                case "Q1":
                    themQuyen(new JLabel("Nhân viên"), "res/img/username.png", new NhanVienUI2(panelRightSize));
                    break;
                case "Q2":
                    themQuyen(new JLabel("Công Ty"), "res/img/company.png", new CongTy2UI(panelRightSize));
                    break;
                case "Q3":
                    themQuyen(new JLabel("Thông tin kho"), "res/img/kho.png", new ThongTinKhoUI());
                    break;
                case "Q4":
                    themQuyen(new JLabel("Hàng trong kho"),"res/img/product.png", new TraCuuHangUI(panelRightSize));
                    break;
                case "Q5":
                    themQuyen(new JLabel("Đơn nhập "),"res/img/danhSach.png", new DonNhapUI(panelRightSize));
                    break;
                case "Q6":
                    themQuyen(new JLabel("Đơn Xuất"),"res/img/donXuat.png", new DonXuatUI(panelRightSize));
                    break;
                case "Q7":
                    themQuyen(new JLabel("Thống kê"), "res/img/chart.png",  new ThongKeUI(panelRightSize));
            }
        }
    }
    public void createForm(){
        Window window = SwingUtilities.getWindowAncestor(this);
        formChange = new ChangePass((JFrame) window, changeButtonAction);
        formChange.setVisible(true);
    }

    ActionListener changeButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] dataTK = formChange.getUserPass();
            if(formChange.check()==false){
                JOptionPane.showMessageDialog(formChange, "Mời bạn nhập đầy đủ thông tin");
            }else if(dataTK[0].equals(UI.matkhau)==false){
                JOptionPane.showMessageDialog(formChange, "Bạn nhập sai mật khẩu cũ");
            }else if(dataTK[3] == "False"){
                JOptionPane.showMessageDialog(formChange, "Bạn nhập lại mật khẩu không hợp lệ!");
            }else{
                nhanVienBLL.suaTK("MaNV = "+UI.manv,"TenTaiKhoan = " + UI.tenDN,"MatKhau = " + dataTK[1],"MaNhomQuyen = " + UI.manhomquyen);
                JOptionPane.showMessageDialog(formChange, "Đổi mật khẩu thành công");
                formChange.dispose();
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
