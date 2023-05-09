package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BLL.DonNhapBLL;
import Panel.Form.FormDon;
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
        
        // xem  chi tiết đơn
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
                new FormDon("FormNhap");
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

