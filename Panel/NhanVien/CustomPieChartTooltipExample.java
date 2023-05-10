package Panel.NhanVien;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;
import org.jfree.ui.*;

public class CustomPieChartTooltipExample {
    public static void main(String[] args) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Nhóm 0 - 14", 25.0);
        dataset.setValue("Nhóm 15 - 59", 66.0);
        dataset.setValue("Nhóm trên 60", 9.0);

        JFreeChart chart = ChartFactory.createPieChart(
                "CƠ CẤU DÂN SỐ THEO NHÓM TUỔI NĂM 2010",
                dataset,
                true,
                true,
                false // Tắt hiển thị tooltip mặc định
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setToolTipGenerator(new StandardPieToolTipGenerator("{0}: ({2})")); // Đặt tooltip tùy chỉnh

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));

        ApplicationFrame frame = new ApplicationFrame("Custom Pie Chart Tooltip Example");
        frame.setContentPane(chartPanel);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }
}