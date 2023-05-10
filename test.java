
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test extends JFrame {
    private JTable table;
    private JButton exportButton;

    public test() {
        // Khởi tạo bảng dữ liệu mẫu
        String[][] data = {
            {"Name", "Age", "City"},
            {"John Doe", "30", "New York"},
            {"Jane Smith", "25", "London"},
            {"Tom Johnson", "35", "Paris"}
        };

        String[] columnNames = {"Name", "Age", "City"};

        table = new JTable(data, columnNames);
        exportButton = new JButton("Export");

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportTableToExcel();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(table));
        panel.add(exportButton);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void exportTableToExcel() {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet 1");

            // Xuất tiêu đề cột
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < table.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(table.getColumnName(i));
            }

            // Xuất dữ liệu từ bảng
            for (int i = 0; i < table.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(String.valueOf(table.getValueAt(i, j)));
                }
            }

            // Lưu tệp Excel
            String filePath = "D:/Java/BT_javaa/src/doanjava/Excel/Donnhap.xlsx";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            System.out.println("Exported table to Excel successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new test();
            }
        });
    }
}