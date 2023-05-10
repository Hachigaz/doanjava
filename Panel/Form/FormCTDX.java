package Panel.Form;



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

import DTO.ChitietdonnhapMD;
import DTO.ChitietdonxuatMD;
import DTO.Loai_hangMD;
import DTO.Mat_hangMD;
import DTO.Model;
import DTO.Custom.ThongTinSPMD;
import Panel.Form.FormInput;
import Panel.Form.FormThem;
import Panel.SubPanel.*;
import misc.ThongBaoDialog;
import misc.TitleFrame;
import BLL.*;
//của tao làm
public class FormCTDX extends TitleFrame{
    private JPanel contentPanel = new JPanel();
    private FormCTDXBLL FormCTDXBLL;

    private TablePanel matHangTable = new TablePanel();

    // private JPanel locPanel = new JPanel();
    // private LocPanel panelLocLoaiSP = new LocPanel();


    private JPanel optionPanel = new JPanel();

    private JPanel optionSPPanel = new JPanel();
    // private JButton themSPBtn = new JButton("Thêm sản phẩm mới");
    // private JButton suaSPBtn = new JButton("Sửa thông tin sản phẩm đã chọn");
    // private JButton xoaSPBtn =new JButton("Xoá sản phẩm đã chọn");

    private JPanel optionInfoPanel = new JPanel();



    private TableModel currentTableModel;
    public FormCTDX(String MaDXXem){
        FormCTDXBLL = new FormCTDXBLL(MaDXXem);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(matHangTable,BorderLayout.CENTER);
        contentPanel.add(optionPanel,BorderLayout.NORTH);

        optionPanel.add(optionInfoPanel);
        
        optionPanel.add(optionSPPanel);

        this.setVisible(true);
        this.setSize(1200,800);
        this.setLocationRelativeTo(null);
        
        this.setContentPane(contentPanel);

        setupPanel();
    }
    public void setupPanel(){
        ArrayList<Loai_hangMD> dsLoaiHang = FormCTDXBLL.getDanhSachLoaiHang();
        ArrayList<String> dsTenLoai = new ArrayList<String>();
        for(Loai_hangMD loaiHang : dsLoaiHang){
            dsTenLoai.add(loaiHang.getTenloai());
        }
        updateTable();
    }
    public void updateTable(){
        ArrayList<ChitietdonxuatMD> dsMatHang = FormCTDXBLL.getDanhSachTTSP();
        String[] columnNames = {"Mã DN","Mã DX","Mã KV","Ma MH","Số lượng "};
        currentTableModel = new DefaultTableModel(Model.to2DArray(dsMatHang,"MaDonNhap","MaDonXuat","MaKV","MaMH","SoLuong"), columnNames);
        matHangTable.SetTable(currentTableModel,null);
    }
    //form inputs
    private ArrayList<FormInput> inputs;
    private JTextField tenSPField;
    private CustomComboBox loaiSPCB;
    private JTextField soLuongField;
    
    WindowAdapter formAdapter = new WindowAdapter() {
        public void windowClosed(WindowEvent e){
            setEnabled(true);
            setAlwaysOnTop(true);
            setAlwaysOnTop(false);
        }
    };
    public static void main(String[] args) {
        new FormCTDN("DX0001");
    }
}
