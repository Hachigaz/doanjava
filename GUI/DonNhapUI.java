package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BLL.DonNhapBLL;
import DTO.DonNhapMD;
import Panel.SubPanel.TablePanel;
import misc.DataSet;
public class DonNhapUI extends JPanel implements MouseListener{

    private final String sqlDonNhap = "select MaDonNhap as 'Mã đơn nhập', MaKho as 'Mã kho', MaCty as 'Mã công ty',MaNV as 'Mã nhân viên','NgayNhap' as 'Ngày nhập' from donnhap";
    private DonNhapBLL donnhapBLL;
    private JButton btadd,btlook;
    private JPanel pNorth;
    public DonNhapUI(DataSet dsdonnhap)

    {
        setLayout(new BorderLayout());
        TableModel model = new DefaultTableModel(dsdonnhap.getData(), dsdonnhap.getColumnName());
        pNorth = new JPanel();
        TablePanel tablePanel = new TablePanel();
        tablePanel.SetTable(model, null);

        // tạo đơn nhập mới
        btadd = new JButton("Thêm đơn nhập");
        btadd.setBorder(null);
        btadd.setPreferredSize(new Dimension(100,40));
        btadd.setBackground(new Color(0, 255, 119));
        btadd.setForeground(new Color(0, 0, 0));
        btadd.setOpaque(true);
        btadd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btlook = new JButton("Xem đơn nhập");
        btlook.setPreferredSize(new Dimension(100, 40));
        btlook.setBackground(new Color(255, 197, 70));
        btlook.setForeground(new Color(0, 0, 0));
        btlook.setBorder(null);
        btlook.setOpaque(true);
        btlook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame("New Window");

                JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

                JLabel label1 = new JLabel("Mã đơn nhập:");
                JTextField field1 = new JTextField(10);
                panel.add(label1);
                panel.add(field1);

                JLabel label2 = new JLabel("Mã kho:");
                JComboBox<String> comboBox1 = new JComboBox<>(new String[]{"Kho 1", "Kho 2", "Kho 3"});
                panel.add(label2);
                panel.add(comboBox1);

                JLabel label3 = new JLabel("Mã công ty:");
                JComboBox<String> comboBox2 = new JComboBox<>(new String[]{"Công ty A", "Công ty B", "Công ty C"});
                panel.add(label3);
                panel.add(comboBox2);

                JLabel label4 = new JLabel("Mã nhân viên:");
                JTextField field2 = new JTextField(10);
                panel.add(label4);
                panel.add(field2);

                JLabel label5 = new JLabel("Ngày nhập:");
                JTextField field3 = new JTextField(10);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                field3.setText(dateFormat.format(new java.util.Date()));
                panel.add(label5);
                panel.add(field3);

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Mã đơn nhập");
                model.addColumn("Mã kho");
                model.addColumn("Mã công ty");
                model.addColumn("Mã nhân viên");
                model.addColumn("Ngày nhập");

                JTable table = new JTable(model);

                JButton addButton = new JButton("Add Row");
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.addRow(new Object[]{"", "", ""});
                    }
                });

                JButton saveButton = new JButton("Lưu");
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String maDonNhap = field1.getText();
                        String maKho = comboBox1.getSelectedItem().toString();
                        String maCongTy = comboBox2.getSelectedItem().toString();
                        String maNhanVien = field2.getText();
                        String ngayNhap = field3.getText();

                        DonNhapMD donnhapnew= new DonNhapMD(maDonNhap, maKho, maCongTy, maNhanVien, ngayNhap);

                        donnhapBLL.addDonnhap(donnhapnew);
                        // Lưu dữ liệu vào cơ sở dữ liệu hoặc làm gì đó khác ở đây

                        newFrame.dispose();
                    }
                });

                JButton cancelButton = new JButton("Hủy");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newFrame.dispose();
                    }
                });

                JPanel panelmid = new JPanel(new BorderLayout());
                panelmid.add(new JScrollPane(table), BorderLayout.CENTER);
                panelmid.add(addButton, BorderLayout.SOUTH);

                JPanel panelSouth = new JPanel();
                panelSouth.add(saveButton);
                panelSouth.add(cancelButton);

                JPanel panel2 = new JPanel(new BorderLayout());
                panel2.add(panel,BorderLayout.NORTH);
                panel2.add(panelmid,BorderLayout.CENTER);
                panel2.add(panelSouth, BorderLayout.SOUTH);

                newFrame.setLocation(600, 100);
                newFrame.getContentPane().add(panel2);
                newFrame.pack();
                newFrame.setVisible(true);
            }
        });
        pNorth.add(btadd);
        pNorth.add(btlook);
        add(tablePanel,BorderLayout.CENTER);
        add(pNorth,BorderLayout.NORTH);
        setPreferredSize(new Dimension(500,500));

        this.revalidate();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}

