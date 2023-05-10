package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import BLL.NhanVienBLL;
import BLL.ThongKeBLL;
import DTO.Model;
import DTO.Custom.DSNhanVienMD;
import Panel.UI;
import Panel.SubPanel.TablePanel;
import Program.Program;

public class ThongKeUI extends JPanel implements MouseListener{
    NhanVienBLL nhanVienBLL = new NhanVienBLL();
    ThongKeBLL thongKeBLL = new ThongKeBLL();
    private JPanel panelButton,panelChart,panelNhanVien,panelKho;
    private JButton btnNhanVien,btnKho;
    private TablePanel panelDanhSachNhanVien = new TablePanel();
    private TablePanel panelDanhSachKho = new TablePanel();
    private TableModel tableDanhSach;
    private DefaultTableModel tableDanhSachKho;
    public ThongKeUI(Dimension d){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(d);

        panelDanhSachNhanVien.setPreferredSize(new Dimension(830, 270));
        panelDanhSachNhanVien.setOpaque(true);


        btnNhanVien = new JButton("Nhân viên");
        btnNhanVien.addMouseListener(this);
        btnNhanVien.setBorder(null);
        btnNhanVien.setPreferredSize(new Dimension(100,40));
        btnNhanVien.setBackground(new Color(0,255,119));
        btnNhanVien.setForeground(Color.BLACK);
        btnNhanVien.setFocusable(false);
        btnNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelDanhSachKho.setPreferredSize(new Dimension(830, 270));
        panelDanhSachKho.setOpaque(true);

        btnKho = new JButton("Kho");
        btnKho.addMouseListener(this);
        btnKho.setBorder(null);
        btnKho.setPreferredSize(new Dimension(100,40));
        btnKho.setBackground(new Color(0,255,119));
        btnKho.setForeground(Color.BLACK);
        btnKho.setFocusable(false);
        btnKho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelButton.add(btnNhanVien);
        panelButton.add(btnKho);
        panelButton.setBackground(Color.gray);

        panelNhanVien = new JPanel();
        panelNhanVien.setBackground(Color.WHITE);
        panelNhanVien.setPreferredSize(new Dimension(1200,555));
        panelNhanVien.setVisible(true);

        int soQuanTri = nhanVienBLL.layPhanTramChucVu()[0];
        int soQuanLyKho = nhanVienBLL.layPhanTramChucVu()[1];
        int soNhanVienKho = nhanVienBLL.layPhanTramChucVu()[2];
        int soLuongNhanVien = nhanVienBLL.laySoLuongNhanVien();


        double tyLeQuanTri = (double) soQuanTri / soLuongNhanVien;
        double tyLeQuanLyKho = (double) soQuanLyKho / soLuongNhanVien;

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Nhóm quản trị", (int) Math.round(tyLeQuanTri*100));
        dataset.setValue("Nhóm quản lý kho", (int) Math.round(tyLeQuanLyKho*100));
        dataset.setValue("Nhóm nhân viên kho", 100 - (int) Math.round(tyLeQuanTri*100) - (int) Math.round(tyLeQuanLyKho*100));

        JFreeChart chart = ChartFactory.createPieChart(
                "TỶ LỆ NHÂN VIÊN THEO CHỨC VỤ TRONG CÔNG TY",
                dataset,
                true,
                true,
                false // Tắt hiển thị tooltip mặc định
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setToolTipGenerator(new StandardPieToolTipGenerator("{0}: ({2})")); // Đặt tooltip tùy chỉnh

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(700,250));

        panelNhanVien.add(chartPanel);

        panelNhanVien.add(panelDanhSachNhanVien);

        String[] columnNames = {"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày sinh","Địa chỉ","Kho làm việc"};
        ArrayList<DSNhanVienMD> DanhSachNhanVien = nhanVienBLL.getDanhSachNhanVien();
        tableDanhSach = new DefaultTableModel(Model.to2DArray(DanhSachNhanVien),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        panelDanhSachNhanVien.SetTable(tableDanhSach, null);

        panelKho = new JPanel();
        panelKho.setBackground(Color.white);
        panelKho.setPreferredSize(new Dimension(1200,555));
        panelKho.setVisible(false);

        String maKhoHT = UI.maKho;
        // String[] dsKhuVuc = thongKeBLL.layDSKV(maKhoHT);
        // float[] dsSucChua = thongKeBLL.laySucChua(maKhoHT);
        Object[][] dsTenKhuVuc = thongKeBLL.dsMHTrongKho(maKhoHT);

        DefaultCategoryDataset datasetBarChart = new DefaultCategoryDataset();
        for(int i=0;i<dsTenKhuVuc[0].length;i++){
            datasetBarChart.addValue((float)dsTenKhuVuc[1][i], "Số thùng", dsTenKhuVuc[0][i].toString());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "THỐNG KÊ HÀNG TRONG KHO THEO KHU VỰC",
                UI.khoNVDangNhap.getTenKho(),
                "Số thùng",
                datasetBarChart
        );

        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setPreferredSize(new java.awt.Dimension(700,250));

        panelKho.add(barChartPanel);

        panelKho.add(panelDanhSachKho);

        String[] columnName = {"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày sinh","Địa chỉ","Kho làm việc"};

        
        ArrayList<Object[]> danhSachThung_Loai = thongKeBLL.getDanhSachMH_KV(maKhoHT);
        tableDanhSachKho = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableDanhSachKho.addColumn("Khu vực");
        tableDanhSachKho.addColumn("Loại hàng");
        tableDanhSachKho.addColumn("Số lượng thùng");
        for(Object[] data : danhSachThung_Loai){
            tableDanhSachKho.addRow(data);
        }
        panelDanhSachKho.SetTable(tableDanhSachKho, null);

        panelChart = new JPanel();
        panelChart.setBackground(Color.WHITE);
        panelChart.add(panelNhanVien);
        panelChart.add(panelKho);

        this.add(panelButton,BorderLayout.NORTH);
        this.add(panelChart);
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == btnNhanVien){
            panelNhanVien.setVisible(true);
            panelKho.setVisible(false);
        }else if(e.getSource() == btnKho){
            panelNhanVien.setVisible(false);
            panelKho.setVisible(true);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == btnNhanVien){
            btnNhanVien.setBackground(new Color(223,18,133));
            btnNhanVien.setForeground(Color.white);
        }else if(e.getSource() == btnKho){
            btnKho.setBackground(new Color(223,18,133));
            btnKho.setForeground(Color.white);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == btnNhanVien){
            btnNhanVien.setBackground(new Color(0,255,119));
            btnNhanVien.setForeground(Color.BLACK);
        }else if(e.getSource() == btnKho){
            btnKho.setBackground(new Color(0,255,119));
            btnKho.setForeground(Color.BLACK);
        }
    }


}
