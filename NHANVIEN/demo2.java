package NHANVIEN;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class demo2 extends JFrame {

    public demo2() {
        setTitle("My Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        String[] columnNames = {"Mã NV", "Họ tên", "Địa chỉ", "Giới tính"};

        Object[][] data = {
                {"NV001", "Nguyễn Văn A", "Hà Nội", "Nam"},
                {"NV002", "Trần Thị B", "TP.HCM", "Nữ"},
                {"NV003", "Lê Văn C", "Đà Nẵng", "Nam"},
                {"NV004", "Phạm Thị D", "Hải Phòng", "Nữ"}
        };

        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new demo2();
    }
}

