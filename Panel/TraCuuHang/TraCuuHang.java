package Panel.TraCuuHang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

import javax.swing.*;
import javax.swing.table.*;

import SQL.DataSet;
import SQL.SQLUser;
import misc.TaiKhoanDangNhap;
import misc.util;

public class TraCuuHang extends JPanel{

    private JPanel panelChucNang;
    private JPanel panelLoc;
    private JPanel panelDanhSach;
    
    private TableRowSorter<TableModel> rowSorter;
    private JScrollPane bangDanhSach;

    private final String sqlDSMH = 
    "SELECT kv.TenKV as 'Khu vực', mh.TenMH as 'Tên mặt hàng', ctdn.SLConLai as 'Số lượng', loai.TenLoai as 'Loại sản phẩm', DATE(dn.NgayNhap) as 'Ngày nhập'\n"+
    "FROM mat_hang mh, khuvuc kv,chitiet_donnhap ctdn, donnhap dn, loai_hang loai\n"+
    "where kv.MaKV = ctdn.MaKV AND mh.MaMH = ctdn.MaMH AND dn.MaDonNhap = ctdn.MaDonNhap AND loai.MaLoai = mh.MaLoai";
    
    private SQLUser master;
    private TaiKhoanDangNhap tkDangNhap;

    public TraCuuHang(SQLUser master,TaiKhoanDangNhap tkdn){
        this.master = master;
        this.tkDangNhap = tkdn;

        this.panelChucNang = new JPanel();
        this.panelLoc = new JPanel();
        this.panelDanhSach = new JPanel();

        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(600,500));

        panelChucNang.setPreferredSize(new Dimension(this.getSize().width,60));
        panelLoc.setPreferredSize(new Dimension(240,this.getSize().height-60));
        panelDanhSach.setPreferredSize(new Dimension(this.getSize().width-240,this.getSize().height-60));

        this.add(panelChucNang,BorderLayout.NORTH);
        this.add(panelLoc,BorderLayout.WEST);
        this.add(panelDanhSach,BorderLayout.CENTER);


        panelChucNang.setBackground(Color.DARK_GRAY);
        panelChucNang.setOpaque(true);
        panelLoc.setBackground(Color.LIGHT_GRAY);
        panelLoc.setOpaque(true);
        panelDanhSach.setBackground(Color.GREEN);
        panelDanhSach.setOpaque(true);

        SetupPanelLoc();
        SetupPanelChucNang();

        
        String sql = sqlDSMH+" AND dn.MaKho = '" + util.objToString(master.getDataQuery("SELECT Kho_Lam_Viec FROM nhanvien WHERE MaNV = '"+ tkdn.getMaNV()+"'").getRow(0))[0] +"'";
        this.SetTable(master.getDataQuery(sql));
    }
    //lọc theo loại sp và khu vực
    private ArrayList<JLabel> locArrLabel = new ArrayList<JLabel>();
    private HashMap<JLabel,JScrollPane> locArrPanel = new HashMap<JLabel,JScrollPane>();
    
    private void SetupPanelLoc(){
        this.themChucNangLoc("Lọc theo khu vực","SELECT MaKV,TenKV FROM khuvuc");
        this.themChucNangLoc("Lọc theo loại sản phẩm","SELECT MaLoai,TenLoai FROM loai_hang");
        panelLoc.revalidate();
        panelLoc.repaint();
    }

    private void themChucNangLoc(String title,String sql){
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for(JLabel label : locArrLabel){
                    if(e.getSource()==label){
                        JScrollPane panel = locArrPanel.get(label);
                        panel.setVisible(!panel.isVisible());
                        panelLoc.revalidate();
                        panelLoc.repaint();
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
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
        };

        JLabel label = new JLabel(title);
        panelLoc.add(label);
        label.addMouseListener(listener);
        locArrLabel.add(label);


        DataSet dsLoaiSP = master.getDataQuery(sql);
        LocPanel panel = new LocPanel(util.objToString(dsLoaiSP.getColumn(1)),util.objToString(dsLoaiSP.getColumn(0)));
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(panelLoc.getPreferredSize().width,250));
        panelLoc.add(scrollPane);
        locArrPanel.put(label,scrollPane);

        panel.setActionForCheckBoxes(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JCheckBox checkbox = (JCheckBox)e.getSource();
                if(checkbox.isSelected()){

                }
                else{

                }
            }
        });
    }

    private String[] optionName; 
    private String[] optionKey;
    private JComboBox<String> cbChonKho;

    private void SetupPanelChucNang(){
        JLabel labelChonKho = new JLabel("Chọn kho");
        DataSet dsKho = master.getDataQuery("SELECT MaKho,TenKho FROM kho");

        optionName = util.objToString(dsKho.getColumn(1));
        optionKey = util.objToString(dsKho.getColumn(0));
        
        cbChonKho = new JComboBox<String>(optionName);

        panelChucNang.add(labelChonKho);
        panelChucNang.add(cbChonKho);

        cbChonKho.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String selected = cbChonKho.getSelectedItem().toString();   
                for(int i = 0; i < optionKey.length;i++){
                    if(selected.equals(optionName[i])){
                        String sql = sqlDSMH + " AND dn.MaKho = '" + optionKey[i] +"'";
                        DataSet ds = master.getDataQuery(sql);
                        SetTable(ds);
                    }
                }
            }
        });

        JTextField searchBar = new JTextField(20);
        searchBar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String searchText = searchBar.getText();
                if (searchText.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    try {
                        rowSorter.setRowFilter(RowFilter.regexFilter(searchText));
                    } catch (PatternSyntaxException ex) {
                        System.err.println("Invalid regex pattern: " + ex.getMessage());
                    }
                }
            }
        });
        panelChucNang.add(searchBar);
    }

    // private String getMaKhoHienTai(){
    //     String maKho = null;
    //     String selected = cbChonKho.getSelectedItem().toString();   
    //     for(int i = 0; i < optionKey.length;i++){
    //         if(selected.equals(optionName[i])){
    //             maKho = optionKey[i];
    //         }
    //     }
    //     return maKho;
    // }

    private void SetTable(DataSet ds){
        if(ds!=null){
            if(this.bangDanhSach!=null){
                this.remove(bangDanhSach);
            }
            TableModel tbModel = new DefaultTableModel(ds.getData(),ds.getColumnLabel());

            JTable tableDS = new JTable(tbModel);
            tableDS.getTableHeader().setReorderingAllowed(false);
            tableDS.setRowHeight(30);
            
            this.rowSorter = new TableRowSorter<>(tbModel);
            tableDS.setRowSorter(rowSorter);

            this.bangDanhSach = new JScrollPane(tableDS);
            
            this.add(bangDanhSach);
            
            // Revalidate and repaint the frame
            this.revalidate();
            this.repaint();
        }
    }
}