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
    private NhanVienUI ui;
    private SQLUser master;
    private Taikhoan_nhanvienMD tkDangNhap;
    private DataAccessLayer<DSNhanVienMD> NhanVienDAL;
    private FormNhanVien form;
    public NhanVienCTR(SQLUser user,Taikhoan_nhanvienMD tkdn, Dimension d){
        this.master = user;
        this.tkDangNhap = tkdn;
        NhanVienDAL = new DataAccessLayer<DSNhanVienMD>(user, DSNhanVienMD.class);
        ui = new NhanVienUI(d);

        ActionListener searchAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ui.timKiem();
            }
        };

        ActionListener infoAction = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.displayInfo();
            }
        };

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

        ActionListener addAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                DataAccessLayer<NhanvienMD> NhanVienDAO = new DataAccessLayer<>(user,NhanvienMD.class);
                ArrayList<NhanvienMD> DSNV = new ArrayList<NhanvienMD>();
                DSNV.add(new NhanvienMD(form.getMaNV(), form.getTenNV(), form.getMaCV(), form.getGioiTinh(), form.getNgaySinh(), form.getDiaChi(), form.getMaKho(), form.getSoGio(), form.getLuongCoBan()));
                NhanVienDAO.add(DSNV);
                ui.updateTable(new DefaultTableModel(Model.to2DArray(DSNV),NhanVienDAO.getReturnedColumnName()));
                // panelDanhSach.setPreferredSize(new Dimension(830, 700));
            }
        };
        ActionListener displayForm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form = new FormNhanVien();
                form.setUpForm(addAction);
            }
        };
        ui.setUpSearchPanel(searchAction, displayForm, infoAction, Buttons);

        ArrayList<DSNhanVienMD> DSNVMD2 = NhanVienDAL.getTable();
        ui.updateTable(new DefaultTableModel(Model.to2DArray(DSNVMD2),NhanVienDAL.getReturnedColumnName()));
    }
    public NhanVienUI getUI(){
        return ui;
    }
    
}
