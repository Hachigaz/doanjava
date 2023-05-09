package Panel.Account;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import Panel.NhanVien.NhanVienBLL;

public class TaiKhoanUI extends JPanel implements MouseListener{
    NhanVienBLL nhanVienBLL = new NhanVienBLL();
    private JPanel panelButton,panelChart,panelNhanVien,panelKho;
    private JButton btnNhanVien,btnKho;
    public TaiKhoanUI(Dimension d){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(d);

        btnNhanVien = new JButton("Nhân viên");
        btnNhanVien.addMouseListener(this);
        btnKho = new JButton("Kho");
        btnKho.addMouseListener(this);

        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        panelButton.add(btnNhanVien);
        panelButton.add(btnKho);
        panelButton.setBackground(Color.BLUE);

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
        chartPanel.setPreferredSize(new java.awt.Dimension(1000,530));

        panelNhanVien.add(chartPanel);

        panelKho = new JPanel();
        panelKho.setBackground(Color.green);
        panelKho.setPreferredSize(new Dimension(1200,555));
        panelKho.setVisible(false);

        // JFreeChart barChart = ChartFactory.createBarChart(
        //         "THỐNG KÊ HÀNG TRONG KHO",
        //         "Kho"
        // );

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

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }


}
