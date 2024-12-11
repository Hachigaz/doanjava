package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import BLL.DonNhapBLL;
import BLL.NhanVienBLL;
import BLL.ThongKeBLL;
import DAL.DonNhapDAL;
import DTO.DonNhapMD;
import DTO.DonXuatMD;
import DTO.Model;
import DTO.Custom.DSNhanVienMD;
import Panel.UI;
import Panel.SubPanel.TablePanel;
import Program.Program;

public class ThongKeUI extends JPanel implements MouseListener{
    NhanVienBLL nhanVienBLL = new NhanVienBLL();
    ThongKeBLL thongKeBLL = new ThongKeBLL();
    private JPanel panelButton,panelChart,panelNhanVien,panelKho,panelDonNhap,panelDonXuat;
    private JButton btnNhanVien,btnKho,btnDonNhap,btnDonXuat;
    private TablePanel panelDanhSachNhanVien = new TablePanel();
    private TablePanel panelDanhSachKho = new TablePanel();
    private TablePanel panelDanhSachDonNhap = new TablePanel();
    private TablePanel panelDanhSachDonXuat = new TablePanel();
    private TableModel tableDanhSach;
    private DefaultTableModel tableDanhSachKho;
    private TableModel tableDanhSachDN,tableDanhSachDX;
    private JComboBox comboBox,comboBoxXuat;
    public int namLon;
    public int namLonXuat;
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

        panelDanhSachDonNhap.setPreferredSize(new Dimension(830, 270));
        panelDanhSachDonNhap.setOpaque(true);

        btnDonNhap = new JButton("Đơn nhập");
        btnDonNhap.addMouseListener(this);
        btnDonNhap.setBorder(null);
        btnDonNhap.setPreferredSize(new Dimension(100,40));
        btnDonNhap.setBackground(new Color(0,255,119));
        btnDonNhap.setForeground(Color.BLACK);
        btnDonNhap.setFocusable(false);
        btnDonNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelDanhSachDonXuat.setPreferredSize(new Dimension(830, 270));
        panelDanhSachDonXuat.setOpaque(true);

        btnDonXuat = new JButton("Đơn xuất");
        btnDonXuat.addMouseListener(this);
        btnDonXuat.setBorder(null);
        btnDonXuat.setPreferredSize(new Dimension(100,40));
        btnDonXuat.setBackground(new Color(0,255,119));
        btnDonXuat.setForeground(Color.BLACK);
        btnDonXuat.setFocusable(false);
        btnDonXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelButton.add(btnNhanVien);
        panelButton.add(btnKho);
        panelButton.add(btnDonNhap);
        panelButton.add(btnDonXuat);
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
        barChartPanel.setPreferredSize(new java.awt.Dimension(900,250));

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






        panelDonNhap = new JPanel();
        panelDonNhap.setBackground(Color.white);
        panelDonNhap.setPreferredSize(new Dimension(1200,555));
        panelDonNhap.setVisible(false);

        DefaultCategoryDataset datasetBarChartDN = new DefaultCategoryDataset();
        for(int i=1;i<=12;i++){
            datasetBarChartDN.addValue(thongKeBLL.arrCacThang("2023")[i-1], "Số đơn", "Tháng "+ i);
        }

        JFreeChart barChartDN = ChartFactory.createBarChart(
                "THỐNG KÊ SỐ ĐƠN NHẬP QUA CÁC NĂM",
                "Năm 2023",
                "Số đơn",
                datasetBarChartDN
        );

        ChartPanel barChartPanelDN = new ChartPanel(barChartDN);
        barChartPanelDN.setPreferredSize(new java.awt.Dimension(1100,250));

        comboBox = new JComboBox<>(thongKeBLL.layCacNamNhap());
        comboBox.addActionListener(changeYear);
        // namLon = 0;
        // for(int i=0;i<thongKeBLL.layCacNamNhap().length;i++){
        //     if(Integer.parseInt(thongKeBLL.layCacNamNhap()[i])>namLon){
        //         namLon = Integer.parseInt(thongKeBLL.layCacNamNhap()[i]);
        //     }
        // }
        // comboBox.setSelectedItem(namLon);

        panelDonNhap.add(comboBox);
        panelDonNhap.add(barChartPanelDN);


        panelNhanVien.add(panelDanhSachNhanVien);

        String[] columnNamesDN = {"Mã đơn nhập","Mã kho","Mã công ty","Mã nhân viên","Ngày nhập"};
        ArrayList<DonNhapMD> DanhSachDonNhap = thongKeBLL.getDanhSachDonNhap();
        tableDanhSachDN = new DefaultTableModel(Model.to2DArray(DanhSachDonNhap),columnNamesDN){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        panelDanhSachDonNhap.SetTable(tableDanhSachDN, null);

        panelDonNhap.add(panelDanhSachDonNhap);
        // System.out.println(panelDanhSachDonNhap);



        // DefaultCategoryDataset datasetBarChartDN = new DefaultCategoryDataset();
        // for(int i=1;i<13;i++){
        //     datasetBarChartDN.addValue(, "Số đơn", "Tháng "+i);
        // }
        panelDonXuat = new JPanel();
        panelDonXuat.setBackground(Color.white);
        panelDonXuat.setPreferredSize(new Dimension(1200,555));
        panelDonXuat.setVisible(false);

        DefaultCategoryDataset datasetBarChartDX = new DefaultCategoryDataset();
        for(int i=1;i<=12;i++){
            datasetBarChartDX.addValue(thongKeBLL.arrCacThangXuat("2023")[i-1], "Số đơn", "Tháng "+ i);
        }

        JFreeChart barChartDX = ChartFactory.createBarChart(
                "THỐNG KÊ SỐ ĐƠN XUẤT QUA CÁC NĂM",
                "Năm 2023",
                "Số đơn",
                datasetBarChartDX
        );

        ChartPanel barChartPanelDX = new ChartPanel(barChartDX);
        barChartPanelDX.setPreferredSize(new java.awt.Dimension(1100,250));

        comboBoxXuat = new JComboBox<>(thongKeBLL.layCacNamXuat());
        comboBoxXuat.addActionListener(changeYearDX);
        // namLonXuat = 0;
        // for(int i=0;i<thongKeBLL.layCacNamXuat().length;i++){
        //     if(Integer.parseInt(thongKeBLL.layCacNamNhap()[i])>namLonXuat){
        //         namLonXuat = Integer.parseInt(thongKeBLL.layCacNamNhap()[i]);
        //     }
        // }
        // comboBox.setSelectedItem(namLon);

        panelDonXuat.add(comboBoxXuat);
        panelDonXuat.add(barChartPanelDX);

        String[] columnNamesDX = {"Mã đơn nhập","Mã kho","Mã công ty","Mã nhân viên","Ngày xuất"};
        ArrayList<DonXuatMD> DanhSachDonXuat = thongKeBLL.getDanhSachDonXuat();
        tableDanhSachDX = new DefaultTableModel(Model.to2DArray(DanhSachDonXuat),columnNamesDX){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        panelDanhSachDonXuat.SetTable(tableDanhSachDX, null);

        panelDonXuat.add(panelDanhSachDonXuat);
        

        panelChart = new JPanel();
        panelChart.setBackground(Color.WHITE);
        panelChart.add(panelNhanVien);
        panelChart.add(panelKho);
        panelChart.add(panelDonNhap);
        panelChart.add(panelDonXuat);

        this.add(panelButton,BorderLayout.NORTH);
        this.add(panelChart,BorderLayout.CENTER);
        
    }
    ActionListener changeYear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelDonNhap.removeAll();
            DefaultCategoryDataset datasetBarChartDN = new DefaultCategoryDataset();
            for(int i=1;i<=12;i++){
                datasetBarChartDN.addValue(thongKeBLL.arrCacThang(getSelectedNamKey())[i-1], "Số đơn", "Tháng "+ i);
            }
            JFreeChart barChartDN = ChartFactory.createBarChart(
                    "THỐNG KÊ SỐ ĐƠN NHẬP QUA CÁC NĂM",
                    "Năm "+getSelectedNamKey(),
                    "Số đơn",
                    datasetBarChartDN
            );
    
            ChartPanel barChartPanelDN = new ChartPanel(barChartDN);
            barChartPanelDN.setPreferredSize(new java.awt.Dimension(1100,250));
            // comboBox = new JComboBox<>(thongKeBLL.layCacNamNhap());
            // comboBox.addActionListener(changeYear);

            panelDonNhap.add(comboBox);
            comboBox.setSelectedItem(namLon);
            panelDonNhap.add(barChartPanelDN);

            String[] columnNamesDN = {"Mã đơn nhập","Mã kho","Mã công ty","Mã nhân viên","Ngày nhập"};
            ArrayList<DonNhapMD> DanhSachDonNhap = thongKeBLL.getDanhSachDonNhap();
            tableDanhSachDN = new DefaultTableModel(Model.to2DArray(DanhSachDonNhap),columnNamesDN){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            panelDanhSachDonNhap.SetTable(tableDanhSachDN, null);

            panelDonNhap.add(panelDanhSachDonNhap);
            panelDonNhap.revalidate();
            panelDonNhap.repaint(); 
        }
        
    };
    ActionListener changeYearDX = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelDonXuat.removeAll();
            DefaultCategoryDataset datasetBarChartDX = new DefaultCategoryDataset();
            for(int i=1;i<=12;i++){
                datasetBarChartDX.addValue(thongKeBLL.arrCacThangXuat(getSelectedNamKey())[i-1], "Số đơn", "Tháng "+ i);
            }
            JFreeChart barChartDX = ChartFactory.createBarChart(
                    "THỐNG KÊ SỐ ĐƠN XUẤT QUA CÁC NĂM",
                    "Năm "+getSelectedNamKey(),
                    "Số đơn",
                    datasetBarChartDX
            );
    
            ChartPanel barChartPanelDX = new ChartPanel(barChartDX);
            barChartPanelDX.setPreferredSize(new java.awt.Dimension(1100,250));
            // comboBox = new JComboBox<>(thongKeBLL.layCacNamNhap());
            // comboBox.addActionListener(changeYear);

            panelDonXuat.add(comboBoxXuat);
            panelDonXuat.add(barChartPanelDX);

            String[] columnNamesDX = {"Mã đơn nhập","Mã kho","Mã công ty","Mã nhân viên","Ngày nhập"};
            ArrayList<DonXuatMD> DanhSachDonXuat = thongKeBLL.getDanhSachDonXuat();
            tableDanhSachDX = new DefaultTableModel(Model.to2DArray(DanhSachDonXuat),columnNamesDX){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            panelDanhSachDonXuat.SetTable(tableDanhSachDX, null);

            panelDonXuat.add(panelDanhSachDonXuat);
            panelDonXuat.revalidate();
            panelDonXuat.repaint(); 
        }
        
    };
    String[] dsNam = thongKeBLL.layCacNamNhap();
    public String getSelectedNamKey(){
        String selected = comboBox.getSelectedItem().toString();
        for(int i = 0; i < dsNam.length;i++){
            if(selected.equals(dsNam[i])){
                return dsNam[i];
            }
        }
        return null;
    }
    String[] dsNamXuat = thongKeBLL.layCacNamXuat();
    public String getSelectedNamKeyXuat(){
        String selected = comboBoxXuat.getSelectedItem().toString();
        for(int i = 0; i < dsNamXuat.length;i++){
            if(selected.equals(dsNamXuat[i])){
                return dsNamXuat[i];
            }
        }
        return null;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == btnNhanVien){
            panelNhanVien.setVisible(true);
            panelKho.setVisible(false);
            panelDonNhap.setVisible(false);
            panelDonXuat.setVisible(false);
        }else if(e.getSource() == btnKho){
            panelNhanVien.setVisible(false);
            panelKho.setVisible(true);
            panelDonNhap.setVisible(false);
            panelDonXuat.setVisible(false);
        }else if(e.getSource() == btnDonNhap){
            panelNhanVien.setVisible(false);
            panelKho.setVisible(false);
            panelDonNhap.setVisible(true);
            panelDonXuat.setVisible(false);
        }else if(e.getSource() == btnDonXuat){
            panelNhanVien.setVisible(false);
            panelKho.setVisible(false);
            panelDonNhap.setVisible(false);
            panelDonXuat.setVisible(true);
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
        }else if(e.getSource() == btnDonNhap){
            btnDonNhap.setBackground(new Color(223,18,133));
            btnDonNhap.setForeground(Color.white);
        }else if(e.getSource() == btnDonXuat){
            btnDonXuat.setBackground(new Color(223,18,133));
            btnDonXuat.setForeground(Color.white);
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
        }else if(e.getSource() == btnDonNhap){
            btnDonNhap.setBackground(new Color(0,255,119));
            btnDonNhap.setForeground(Color.BLACK);
        }else if(e.getSource() == btnDonXuat){
            btnDonXuat.setBackground(new Color(0,255,119));
            btnDonXuat.setForeground(Color.BLACK);
        }
    }


}
