package Function;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import SQL.DataSet;

public class TaoDonNhap extends JPanel implements MouseListener{
    JTable donnhap;
    JLabel lChuaDN;
    JButton add,look;
    public TaoDonNhap(DataSet ds)
    {
        // donnhap = new JTable(ds.getData(),ds.getColumnName());
        // this.add(donnhap);

        // tạo đơn nhập mới
        add = new JButton("Thêm đơn nhập");
        add.setBorder(null);
        add.setPreferredSize(new Dimension(100,30));
        add.setBackground(new Color(100, 150, 30));

        look = new JButton("Xem đơn nhập");
        look.setPreferredSize(new Dimension(100, 40));
        look.setBackground(new Color(100, 150, 30));
        look.setBorder(null);


        this.add(add);
        this.add(look);
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
