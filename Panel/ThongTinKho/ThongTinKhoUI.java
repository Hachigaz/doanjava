package Panel.ThongTinKho;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DTO.*;
import DTO.Custom.DSChiTietKhuVucLoaiMD;
import Panel.UI;
import Panel.Form.FormInput;
import Panel.Form.FormThem;
import Panel.SubPanel.TablePanel;
import misc.ThongBaoDialog;

public class ThongTinKhoUI extends JPanel{
    //danh sách có nút sửa thong tin và xoá khu vực
    private TablePanel danhSachPanel = new TablePanel();
    //BLL
    private ThongTinKhoBLL thongTinKhoBLL;

    private Font buttonFont = new Font("Helvetica",Font.BOLD,14);
    private Font displayFont = new Font("Helvetica",Font.BOLD,16);
    //===========================================HeaderPanel - sửa thông tin kho(tên, địa chỉ),thêm khu vực mới
    private JPanel headerPanel = new JPanel();

    private JLabel khoHienTaiLabel;
    private JLabel tongSucChuaLabel;
    private JButton themKhuVucBtn;
    private JButton xoaKhuVucBtn;
    private JPanel sidePanel = new JPanel();
    //panel hiện lên khi vừa bấm vào chức năng
    public JPanel startPanel = new JPanel();
    //panel hiện chi tiết khu vực và option
    public JPanel infoPanel = new JPanel(new BorderLayout());
    
    //==========================================OptionPanel
    public JPanel optionPanel = new JPanel();
    private JButton themLoaiBtn = new JButton("Thêm loại hàng vào khu vực");
    private JButton xoaLoaiBtn = new JButton("Xoá loại hàng đã chọn");

    public TablePanel chiTietKhuVucPanel = new TablePanel();
    //panel hiện khi bảng trống
    public JPanel tableNullMessagePanel = new JPanel();
    //panel form nhập ctkv
    public JPanel themCTKVPanel = new JPanel();
    private JComboBox<String> loaiHangCB = new JComboBox<>();
    private JPanel themCTKVOptionPanel = new JPanel();
    private JButton submitCTKVBtn = new JButton("Thêm");
    private JButton cancelCTKVBtn = new JButton("Huỷ");

    public ThongTinKhoUI(){
        thongTinKhoBLL = new ThongTinKhoBLL();


        this.setLayout(new BorderLayout());

        //Dimension panelSize = this.getPreferredSize();
        //this.setPreferredSize(panelSize);

        // headerPanel.setPreferredSize(new Dimension(panelSize.width,70));
        // sidePanel.setPreferredSize(new Dimension(700, panelSize.height-70));
        // danhSachPanel.setPreferredSize(new Dimension(panelSize.width-700,panelSize.height-70));
        
        sidePanel.setOpaque(true);
        sidePanel.setBackground(Color.darkGray);

        
        this.add(headerPanel,BorderLayout.NORTH);
        this.add(sidePanel,BorderLayout.EAST);
        this.add(danhSachPanel,BorderLayout.CENTER);

        //setup header panel
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.setOpaque(true);


        this.khoHienTaiLabel=new JLabel("Kho hiện tại");
        this.khoHienTaiLabel.setFont(displayFont);
        this.tongSucChuaLabel = new JLabel("Tổng sức chứa");
        this.tongSucChuaLabel.setFont(displayFont);
        this.themKhuVucBtn=new JButton("Thêm khu vực mới");
        this.themKhuVucBtn.setFont(buttonFont);
        this.xoaKhuVucBtn=new JButton("Xoá khu vực đã chọn");
        this.xoaKhuVucBtn.setFont(buttonFont);
        

        headerPanel.add(this.khoHienTaiLabel);
        headerPanel.add(this.tongSucChuaLabel);
        headerPanel.add(themKhuVucBtn);
        headerPanel.add(xoaKhuVucBtn);

        
        this.themKhuVucBtn.addActionListener(themKVBtnListener);
        this.xoaKhuVucBtn.addActionListener(xoaKVBtnListener);



        //setup side panel
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        JLabel startLabel =new JLabel("Chọn vào một khu vực để xem chi tiết");
        startLabel.setFont(displayFont);
        startPanel.add(startLabel);


        tableNullMessagePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        JLabel tableNullMsgLabel =new JLabel("Khu vực chưa thêm loại hàng");
        tableNullMsgLabel.setFont(displayFont);
        tableNullMessagePanel.add(tableNullMsgLabel);
        tableNullMessagePanel.setBackground(Color.orange);
        tableNullMessagePanel.setOpaque(true);


        optionPanel.setBackground(Color.orange);
        optionPanel.setOpaque(true);
        optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        themLoaiBtn.setFont(buttonFont);
        optionPanel.add(themLoaiBtn);
        xoaLoaiBtn.setFont(buttonFont);
        optionPanel.add(xoaLoaiBtn);

        infoPanel.add(optionPanel,BorderLayout.NORTH);
        JPanel infoSubPanel = new JPanel(new CardLayout());
        infoPanel.add(infoSubPanel,BorderLayout.CENTER);
        infoSubPanel.add(chiTietKhuVucPanel);
        infoSubPanel.add(tableNullMessagePanel);
        
        themCTKVPanel.setLayout(new BoxLayout(themCTKVPanel,BoxLayout.Y_AXIS));

        JPanel headerCTKVOptionPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        JLabel themLoaiLabel = new JLabel("Thêm loại mới vào khu vực");
        themLoaiLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        headerCTKVOptionPanel.add(themLoaiLabel);
        themCTKVPanel.add(headerCTKVOptionPanel);
        JPanel inputOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        
        JLabel loaiHangCBLabel = new JLabel("Chọn loại hàng");
        loaiHangCBLabel.setFont(displayFont);
        inputOptionPanel.add(loaiHangCBLabel);
        loaiHangCB.setFont(buttonFont); 
        inputOptionPanel.add(loaiHangCB);
        
        themCTKVPanel.add(inputOptionPanel);

        submitCTKVBtn.setFont(buttonFont);
        cancelCTKVBtn.setFont(buttonFont);
        themCTKVOptionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20, 0));
        themCTKVOptionPanel.add(submitCTKVBtn);
        themCTKVOptionPanel.add(cancelCTKVBtn);

        themCTKVPanel.add(themCTKVOptionPanel);
        

        infoPanel.setVisible(false);
        tableNullMessagePanel.setVisible(false);
        themCTKVPanel.setVisible(false);

        sidePanel.setLayout(new CardLayout());
        sidePanel.add(startPanel);
        sidePanel.add(infoPanel);
        sidePanel.add(themCTKVPanel);

        setupXoaButton(null);

         
        setupPanel();
    }
    //reset panel
    private TableModel currentKVTableModel;
    public void setupPanel(){
        //Header Panel
        String tenKhoHienTai = UI.khoNVDangNhap.getTenKho();
        String tongSucChua = thongTinKhoBLL.getTongSucChuaKho(UI.khoNVDangNhap.getMaKho()).toString();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20,20));
        this.khoHienTaiLabel.setText("Kho hiện tại: "+tenKhoHienTai);
        this.tongSucChuaLabel.setText("Tổng sức chứa: "+tongSucChua);
        headerPanel.repaint();
        headerPanel.revalidate();
        //side panel
        
        String[] columnNames = {"Mã khu vực","Tên khu vực", "Sức chứa"};
        currentKVTableModel = new DefaultTableModel(Model.to2DArray(thongTinKhoBLL.getDanhSachKV(),"MaKV","TenKV","SucChua"),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.danhSachPanel.SetTable(currentKVTableModel, selectedCTKVRowListener);
    }
    //action listener cua danh sachpanel
    private ListSelectionListener selectedCTKVRowListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            UpdateCTKVLTable();
        }
    };

    //action listener cua option panel
    private ActionListener themKVBtnListener = new ActionListener() {
            
        public void actionPerformed(ActionEvent e){
            Window mainWindow = SwingUtilities.getWindowAncestor((Component)e.getSource());
            mainWindow.setEnabled(false);

            ArrayList<FormInput> inputFields = new ArrayList<FormInput>();
            inputFields.add(new FormInput("Tên khu vực",new JTextField("",20)));
            inputFields.add(new FormInput("Sức chứa",new JTextField("",20)));
            
            ActionListener themKVSubmitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String maKhoDN = UI.khoNVDangNhap.getMaKho();
                    String maKVMoi = thongTinKhoBLL.taoMaKVMoi(maKhoDN);
                    JTextField tenKVField = (JTextField)(inputFields.get(0).getInputComponent());
                    String tenKV = tenKVField.getText();
                    Float soLuong = 0.0f;
                        JTextField soLuongField = (JTextField)(inputFields.get(1).getInputComponent());
                        if(tenKVField.getText().equals(""))
                        {
                            new ThongBaoDialog("Tên khu vực không được để trống",null);
                            return;
                        }
                        if(soLuongField.getText().equals("")){
                            new ThongBaoDialog("Số lượng không được để trống", null);
                            return;
                        }
                        try{
                            soLuong = Float.parseFloat(soLuongField.getText());
                        }
                        catch(NumberFormatException exception){
                            new ThongBaoDialog("Số lượng nhập vào không phải là số",null);
                            return;
                        }
                        if(soLuong <= 0){
                            new ThongBaoDialog("Số lượng nhập vào phải lớn hơn 0", null);
                            return;
                        }
                        thongTinKhoBLL.themKVMoi(new KhuvucMD(maKhoDN, maKVMoi, tenKV, soLuong));
                        new ThongBaoDialog("Thêm khu vực thành công", null);
                        setupPanel();
                        Window formThemDialog = SwingUtilities.getWindowAncestor((JComponent)e.getSource());
                        formThemDialog.dispose();
                }
            };
            WindowAdapter themKVFormAdapter = new WindowAdapter() {
                public void windowClosed(WindowEvent e){
                    mainWindow.setEnabled(true);
                    mainWindow.setAlwaysOnTop(true);
                    mainWindow.setAlwaysOnTop(false);
                }
            };
            FormThem formThemKV =  new FormThem("Thêm khu vực mới",inputFields,themKVSubmitListener);
            formThemKV.addWindowListener(themKVFormAdapter);
        }
    
    };
    private ActionListener xoaKVBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //kiểm tra khu vực còn loại hàng nào ko
            int selectedRow = danhSachPanel.getSelectedRow();
            if(selectedRow==-1){
                new ThongBaoDialog("Chưa chọn khu vực để xoá", null);
                return;
            }
            String maKVXoa = currentKVTableModel.getValueAt(selectedRow,0).toString();
            
            if(thongTinKhoBLL.getDanhSachKhuVucLoai("MaKV = "+maKVXoa)==null){
                thongTinKhoBLL.removeKhuVucTrongKho(maKVXoa);
                new ThongBaoDialog("Đã xoá khu vực "+maKVXoa +" ra khỏi kho", null);
                setupPanel();
            }
            else{
                new ThongBaoDialog("Không thể xoá khu vực còn loại hàng", null);
            }
            
        }
        
    };


    //phuong thuc cua side panel
    public void setupThemLoaiButton(ActionListener themKVAction){
        removeButtonListeners(themLoaiBtn);
        themLoaiBtn.addActionListener(themKVAction);
    }
    public void setupXoaButton(ActionListener xoaKVAction){
        if(xoaKVAction!=null){
            xoaLoaiBtn.setEnabled(true);
            removeButtonListeners(xoaLoaiBtn);
            xoaLoaiBtn.addActionListener(xoaKVAction);
        }
        else{
            xoaLoaiBtn.setEnabled(false);
        }
    }
    public void removeButtonListeners(JButton button){
        for(ActionListener listener : button.getActionListeners()){
            button.removeActionListener(listener);
        }
    }
    public void setupThemCTKVForm(List<String> tenLoai,ActionListener submitAction,ActionListener cancelAction){
        for(int i = 0 ; i < tenLoai.size();i++){
            loaiHangCB.addItem(tenLoai.get(i));
        }
        removeButtonListeners(submitCTKVBtn);
        submitCTKVBtn.addActionListener(submitAction);
        removeButtonListeners(cancelCTKVBtn);
        cancelCTKVBtn.addActionListener(cancelAction);

    }
    public String getSelectedItemInComboBox(){
        return loaiHangCB.getSelectedItem().toString();
    }
    public void setDisplayNullMessage(){
        hideAllPanels();
        infoPanel.setVisible(true);
        tableNullMessagePanel.setVisible(true);
    }
    public void setDisplayTable(){
        hideAllPanels();
        infoPanel.setVisible(true);
        chiTietKhuVucPanel.setVisible(true);
    }
    public void setDisplayThemCTKVPanel(){
        hideAllPanels();
        themCTKVPanel.setVisible(true);
    }
    private void hideAllPanels(){
        if(startPanel.isVisible()){
            startPanel.setVisible(false);
        }
        chiTietKhuVucPanel.setVisible(false);
        tableNullMessagePanel.setVisible(false);
        infoPanel.setVisible(false);
        themCTKVPanel.setVisible(false);
    }
    public int getSelectedTableRow(){
        return chiTietKhuVucPanel.getSelectedRow();
    }
    //phuong thuc cua side panel
    private TableModel currentCTKVTable;
    private void UpdateCTKVLTable(){
        //lay ma khu vuc duoc chon
        String selectedMaKVRow = currentKVTableModel.getValueAt(danhSachPanel.getSelectedRow(),0).toString();
        //lay ma kho cua tai khoan dang nhap
        String maKhoDN = UI.khoNVDangNhap.getMaKho();
        
        //lay du lieu de tao bang
        //action cho nút thêm loại vào khu vực
        ActionListener themCTKVLoaiAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //hiện form thêm ctkv
                setDisplayThemCTKVPanel();
                ArrayList<Loai_hangMD> dsLoaiHang = thongTinKhoBLL.getDanhSachLoaiHang();
                ActionListener submitAction = new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        String TenLoai = getSelectedItemInComboBox();
                        for(Loai_hangMD loaiHang : dsLoaiHang){
                            if(TenLoai.equals(loaiHang.getTenloai())){
                                //duyệt trong dsctkv hiện tại coi nó có tồn tại trong csdl chưa
                                boolean timThay = false;
                                ArrayList<Khuvuc_loaihangMD> dsKhuVucLoai = thongTinKhoBLL.getDanhSachKhuVucLoai("MaKV="+selectedMaKVRow);
                                if(dsKhuVucLoai!=null){
                                    for(Khuvuc_loaihangMD kvLoai : dsKhuVucLoai){
                                        if(kvLoai.getMaLoai().equals(loaiHang.getMaLoai())){
                                            timThay=true;
                                        }
                                    }
                                }
                                if(!timThay){
                                    thongTinKhoBLL.addNewKhuVucLoai(new Khuvuc_loaihangMD(selectedMaKVRow, loaiHang.getMaLoai()));
                                    new ThongBaoDialog("Thêm loại hàng thành công", null);
                                    UpdateCTKVLTable();
                                }
                                else{
                                    new ThongBaoDialog("Loại hàng muốn thêm đã có trong khu vực", null);
                                    UpdateCTKVLTable();
                                }
                            }
                        }
                    }
                };
                ActionListener cancelAction = new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        UpdateCTKVLTable();
                    }
                };
                ArrayList<String> dsTenLoaiHang = new ArrayList<String>(); 
                for(Loai_hangMD loaiHang : dsLoaiHang){
                    dsTenLoaiHang.add(loaiHang.getTenloai());
                }
                setupThemCTKVForm(dsTenLoaiHang,submitAction,cancelAction);
            }
        };
        ArrayList<DSChiTietKhuVucLoaiMD> dsChiTietKVL = thongTinKhoBLL.getDanhSachCTKV("kho.MaKho = "+maKhoDN,"khuvuc.MaKV = " + selectedMaKVRow);
        if(dsChiTietKVL!=null){
            //action cho chọn một dòng trong chi tiet kv
            ListSelectionListener selectedCTKVAction = new ListSelectionListener(){
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    //xoá một ctkv trong khu vực
                    ActionListener xoaCTKVAction = new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            thongTinKhoBLL.getDanhSachKhuVucLoai();
                            int selectedRowIndex = chiTietKhuVucPanel.getSelectedRow();
                            float soLuong = Float.parseFloat(dsChiTietKVL.get(selectedRowIndex).getSoLuongChua().toString());
                            if(soLuong>0){
                                new ThongBaoDialog("Không thể xoá chi tiết khu vực còn hàng",null);
                            }
                            else{
                                String maKVDelete = dsChiTietKVL.get(selectedRowIndex).getMaKV().toString();
                                String maLoaiDelete = dsChiTietKVL.get(selectedRowIndex).getMaLoai();
                                thongTinKhoBLL.removeKhuVucLoai("MaKV = "+maKVDelete,"MaLoai="+maLoaiDelete);
                                new ThongBaoDialog("Xoá thành công",null);
                                UpdateCTKVLTable();
                            }
                            
                        }
                        
                    };
                    setupXoaButton(xoaCTKVAction);
                }
            };
            // float tongSLHang = 0;
            // for(DSChiTietKhuVucLoaiMD chiTietKVL : dsChiTietKVLDAL.getTable("kho.MaKho = "+maKhoHT)){
            //     tongSLHang+=chiTietKVL.getSoLuongChua();
            // };
            
            //hiện panel table
            setDisplayTable();
            //thêm dữ liệu vào cho table
            String[] columnNames = {"Khu vực","Tên loại hàng","Mức chứa hiện tại"};
            Object[][] data = Model.to2DArray(dsChiTietKVL,"TenKhuVuc","TenLoai","SoLuongChua");
            currentCTKVTable = new DefaultTableModel(data,columnNames){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            //set table
            chiTietKhuVucPanel.SetTable(currentCTKVTable,selectedCTKVAction);

            //thêm listener cho nút thêm khu vực
            setupThemLoaiButton(themCTKVLoaiAction);
            //reset nút xoá
            setupXoaButton(null);
        }
        else{
            //hiện thông báo bảng trống
            setDisplayNullMessage();
            setupThemLoaiButton(themCTKVLoaiAction);
            //reset nút xoá
            setupXoaButton(null);
        }
    }
    //listener cua side panel
}