package Panel.ThongTinSP;



import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DTO.Mat_hangMD;
import DTO.Model;
import DTO.Custom.ThongTinSPMD;
import Panel.SubPanel.*;
import misc.TitleFrame;
//của tao làm
public class ThongTinSPUI extends TitleFrame{
    private JPanel contentPanel = new JPanel();
    private ThongTinSPBLL thongTinSPBLL;

    private TablePanel matHangTable = new TablePanel();
    private JPanel optionPanel = new JPanel();
    private JPanel locPanel = new JPanel();

    private TableModel currentTableModel;
    public ThongTinSPUI(String MaCtyXem){
        thongTinSPBLL = new ThongTinSPBLL(MaCtyXem);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(matHangTable,BorderLayout.CENTER);
        contentPanel.add(optionPanel,BorderLayout.NORTH);
        contentPanel.add(locPanel,BorderLayout.WEST);
        this.setVisible(true);
        this.setSize(900,700);
        
        this.setContentPane(contentPanel);
        
        setupPanel();

        locPanel.add(new LocPanel());
    }
    public void setupPanel(){
        updateTable();
    }
    public void updateTable(){
        ArrayList<ThongTinSPMD> dsMatHang = thongTinSPBLL.getDanhSachTTSP();
        String[] columnNames = {"Mã mặt hàng","Tên mặt hàng","Mã loại"};
        currentTableModel = new DefaultTableModel(Model.to2DArray(dsMatHang,"MaMH","TenMH","TenLoai"), columnNames);
        matHangTable.SetTable(currentTableModel,null);
    }
    public static void main(String[] args) {
        new ThongTinSPUI("Cty_ABC");
    }
}
