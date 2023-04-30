package Panel.NhanVien;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAL.DataAccessLayer;
import DTO.Model;
import DTO.NhanvienMD;
import DTO.Taikhoan_nhanvienMD;
import DTO.Custom.DSNhanVienMD;
import SQL.SQLUser;

public class NhanVienCTR {
    private Object atributeNV[];
    private NhanVienUI2 ui;
    private SQLUser master;
    private Taikhoan_nhanvienMD tkDangNhap;
    private DataAccessLayer<DSNhanVienMD> NhanVienDAL;
    public NhanVienCTR(SQLUser user,Taikhoan_nhanvienMD tkdn, Dimension d){
        this.master = user;
        this.tkDangNhap = tkdn;
        NhanVienDAL = new DataAccessLayer<DSNhanVienMD>(user, DSNhanVienMD.class);
        ui = new NhanVienUI2(d);

        MouseListener Buttons = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ui.enter(e.getSource());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ui.exit(e.getSource());
            }
        };

        ui.setUpSearchPanel(searchAction, displayForm, infoAction, Buttons);

        ArrayList<DSNhanVienMD> DSNVMD2 = NhanVienDAL.getTable();
        ui.updateTable(new DefaultTableModel(Model.to2DArray(DSNVMD2),NhanVienDAL.getReturnedColumnName()));
    }
    public NhanVienUI2 getUI(){
        return ui;
    }
    
}
