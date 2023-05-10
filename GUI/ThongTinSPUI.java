package GUI;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BLL.ThongTinSPBLL;
import DTO.Loai_hangMD;
import DTO.Mat_hangMD;
import DTO.Model;
import DTO.Custom.ThongTinSPMD;
import Panel.Form.FormInput;
import Panel.Form.FormThem;
import Panel.SubPanel.*;
import misc.ThongBaoDialog;
import misc.TitleFrame;
//của tao làm
public class ThongTinSPUI extends TitleFrame{
    private JPanel contentPanel = new JPanel();
    private ThongTinSPBLL thongTinSPBLL;

    private TablePanel matHangTable = new TablePanel();

    private JPanel locPanel = new JPanel();
    private LocPanel panelLocLoaiSP = new LocPanel();


    private JPanel optionPanel = new JPanel();

    private JPanel optionSPPanel = new JPanel();
    private JButton themSPBtn = new JButton("Thêm sản phẩm mới");
    private JButton suaSPBtn = new JButton("Sửa thông tin sản phẩm đã chọn");
    private JButton xoaSPBtn =new JButton("Xoá sản phẩm đã chọn");

    private JPanel optionInfoPanel = new JPanel();



    private TableModel currentTableModel;
    public ThongTinSPUI(String MaCtyXem){
        thongTinSPBLL = new ThongTinSPBLL(MaCtyXem);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(matHangTable,BorderLayout.CENTER);
        contentPanel.add(optionPanel,BorderLayout.NORTH);
        contentPanel.add(locPanel,BorderLayout.WEST);

        optionPanel.add(optionInfoPanel);
        
        optionPanel.add(optionSPPanel);
        optionSPPanel.add(themSPBtn);
        themSPBtn.addActionListener(themSPListener);
        optionSPPanel.add(suaSPBtn);
        suaSPBtn.addActionListener(suaSPListener);
        optionSPPanel.add(xoaSPBtn);
        xoaSPBtn.addActionListener(xoaSPListener);

        this.setVisible(true);
        this.setSize(1200,800);
        this.setLocationRelativeTo(null);
        
        this.setContentPane(contentPanel);

        locPanel.add(panelLocLoaiSP);
        
        setupPanel();
    }
    public void setupPanel(){
        ArrayList<Loai_hangMD> dsLoaiHang = thongTinSPBLL.getDanhSachLoaiHang();
        ArrayList<String> dsTenLoai = new ArrayList<String>();
        for(Loai_hangMD loaiHang : dsLoaiHang){
            dsTenLoai.add(loaiHang.getTenloai());
        }
        panelLocLoaiSP.setupPanel(dsTenLoai,2);
        panelLocLoaiSP.setActionForCheckBoxes(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JCheckBox cb = (JCheckBox)e.getSource();
                if(cb.isSelected()){
                    matHangTable.themDieuKienLoc(panelLocLoaiSP.getColumnIndex(),cb.getName());
                }
                else{
                    matHangTable.xoaDieuKienLoc(panelLocLoaiSP.getColumnIndex(), cb.getName());
                }
                matHangTable.locCacDieuKien();
            }
        });

        updateTable();
    }
    public void updateTable(){
        ArrayList<ThongTinSPMD> dsMatHang = thongTinSPBLL.getDanhSachTTSP();
        String[] columnNames = {"Mã mặt hàng","Tên mặt hàng","Loại sản phẩm","Số lượng mỗi thùng"};
        currentTableModel = new DefaultTableModel(Model.to2DArray(dsMatHang,"MaMH","TenMH","TenLoai","SLMoiThung"), columnNames);
        matHangTable.SetTable(currentTableModel,null);
    }
    //form inputs
    private ArrayList<FormInput> inputs;
    private JTextField tenSPField;
    private CustomComboBox loaiSPCB;
    private JTextField soLuongField;
    //action listeners
    private ActionListener themSPListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputs = new ArrayList<FormInput>();
            tenSPField = new JTextField(20);
            inputs.add(new FormInput("Tên sản phẩm",tenSPField));
            loaiSPCB = new CustomComboBox();

            ArrayList<Loai_hangMD> dsLoai = thongTinSPBLL.getDanhSachLoaiHang();
            for(Loai_hangMD loaiHang : dsLoai){
                loaiSPCB.addItem(loaiHang.getTenloai(),loaiHang.getMaLoai());
            }
            inputs.add(new FormInput("Loại sản phẩm", loaiSPCB));

            soLuongField = new JTextField(20);
            inputs.add(new FormInput("Số lượng mỗi thùng", soLuongField));


            ActionListener formThemSubmitListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String tenSPMoi = tenSPField.getText();
                    if(tenSPMoi.equals("")){
                        new ThongBaoDialog("Tển sản phẩm không được để trống", null);
                        return;
                    }
                    int soLuongMT;
                    try{
                        soLuongMT = Integer.parseInt(soLuongField.getText());    
                        if(soLuongMT<=0){
                            new ThongBaoDialog("Số lượng nhập vào phải lớn hơn 0", null);
                            return;
                        }
                    }
                    catch(NumberFormatException exception){
                        new ThongBaoDialog("Số lượng nhập vào không phải là số",null);
                        return;
                    }
                    Mat_hangMD mhMoi = new Mat_hangMD(thongTinSPBLL.taoMaMatHangMoi(), thongTinSPBLL.getMaCtyChon(),loaiSPCB.getSelectedKey(),tenSPMoi,soLuongMT);
                    thongTinSPBLL.themMatHangMoi(mhMoi);
                    new ThongBaoDialog("Thêm sản phẩm mới thành công", null);
                    Window formThemDialog = SwingUtilities.getWindowAncestor((JComponent)e.getSource());
                    formThemDialog.dispose();
                    updateTable();
                }
            };

            setEnabled(false);
            FormThem formThemNV = new FormThem("Thêm sản phẩm mới",inputs,formThemSubmitListener);
            formThemNV.addWindowListener(formAdapter);
        }
    };
    private ActionListener suaSPListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(matHangTable.getSelectedRow()<0){
                new ThongBaoDialog("Chưa chọn mặt hàng muốn sửa",null);
                return;
            }
            String maMHSelected = currentTableModel.getValueAt(matHangTable.getSelectedRow(), 0).toString();
            Mat_hangMD mh = thongTinSPBLL.getFirstMatHangCty("MaMH = "+maMHSelected);

            inputs = new ArrayList<FormInput>();
            
            tenSPField = new JTextField(mh.getTenMH(),20);
            inputs.add(new FormInput("Tên sản phẩm",tenSPField));

            loaiSPCB = new CustomComboBox();
            ArrayList<Loai_hangMD> dsLoai = thongTinSPBLL.getDanhSachLoaiHang();
            for(Loai_hangMD loaiHang : dsLoai){
                loaiSPCB.addItem(loaiHang.getTenloai(),loaiHang.getMaLoai());
                if(loaiHang.getMaLoai().equals(mh.getMaLoai())){
                    loaiSPCB.setSelectedItem(loaiHang.getTenloai());
                }
            }
            inputs.add(new FormInput("Loại sản phẩm", loaiSPCB));

            soLuongField=new JTextField(mh.getSoLuongMoiThung().toString(),20);
            inputs.add(new FormInput("Số lượng mỗi thùng", soLuongField));
            
            ActionListener formSuaSubmitListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String tenSPMoi = tenSPField.getText();
                    if(tenSPMoi.equals("")){
                        new ThongBaoDialog("Tển sản phẩm không được để trống", null);
                        return;
                    }
                    int soLuongMT;
                    try{
                        soLuongMT = Integer.parseInt(soLuongField.getText());    
                        if(soLuongMT<=0){
                            new ThongBaoDialog("Số lượng nhập vào phải lớn hơn 0", null);
                            return;
                        }
                    }
                    catch(NumberFormatException exception){
                        new ThongBaoDialog("Số lượng nhập vào không phải là số",null);
                        return;
                    }
                    Mat_hangMD mhMoi = new Mat_hangMD(mh.getMaMH(), thongTinSPBLL.getMaCtyChon(),loaiSPCB.getSelectedKey(),tenSPMoi,soLuongMT);
                    thongTinSPBLL.updateMatHang("MaMH ="+mhMoi.getMaMH(),"MaCty="+mhMoi.getMaCty(),"MaLoai="+mhMoi.getMaLoai(),"TenMH="+mhMoi.getTenMH());
                    new ThongBaoDialog("Sửa thành công", null);
                    Window formThemDialog = SwingUtilities.getWindowAncestor((JComponent)e.getSource());
                    formThemDialog.dispose();
                    updateTable();
                }
            };

            setEnabled(false);
            FormThem formThemNV = new FormThem("Sửa thông tin sản phẩm",inputs,formSuaSubmitListener);
            formThemNV.addWindowListener(formAdapter);
        }
    };
    private ActionListener xoaSPListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(matHangTable.getSelectedRow()<0){
                new ThongBaoDialog("Chưa chọn sản phẩm để xoá", null);
                return;
            }
            if(thongTinSPBLL.removeMatHang("MaMH="+currentTableModel.getValueAt(matHangTable.getSelectedRow(), 0))){
                new ThongBaoDialog("Xoá sản phẩm thành công", null);
            }
            else{
                new ThongBaoDialog("Không thể xoá mặt hàng còn tồn tại trong kho", null);
            }
            updateTable();
        }
    };
    WindowAdapter formAdapter = new WindowAdapter() {
        public void windowClosed(WindowEvent e){
            setEnabled(true);
            setAlwaysOnTop(true);
            setAlwaysOnTop(false);
        }
    };
    public static void main(String[] args) {
        new ThongTinSPUI("Cty_ABC");
    }
}
