package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import BLL.*;
import DTO.ChitietdonnhapMD;
import DTO.ChitietdonxuatMD;
import DTO.CongtyMD;
import DTO.DonXuatMD;
import DTO.DonNhapMD;
import DTO.KhoMD;
import DTO.KhuvucMD;
import DTO.Loai_hangMD;
import DTO.Model;
import DTO.Custom.DSDonNhapMD;
import DTO.Custom.DSDonXuatMD;
import DTO.Custom.DSTraCuuHangMD;
import Panel.Form.FormCTDN;
import Panel.Form.FormCTDX;
import Panel.Form.FormDon;
import Panel.SubPanel.LocPanel;
import Panel.SubPanel.TablePanel;
import misc.ThongBaoDialog;
import misc.util;
public class DonXuatUI extends JPanel{
    //BLL
    private DonXuatBLL DonXuatBLL = new DonXuatBLL();
    private JTable tableTemp;
    private JPanel panelChucNang;
    private JPanel panelLoc;
    private TablePanel panelDanhSach;
    public static JButton btloc,btlook,btexport;
    private JDateChooser date1,date2;


    private JTextField searchBar;



    public DonXuatUI(Dimension d){

        this.panelChucNang = new JPanel();
        this.panelLoc = new JPanel();
        this.panelDanhSach = new TablePanel();

        this.setLayout(new BorderLayout());

        this.setPreferredSize(d);

        panelChucNang.setPreferredSize(new Dimension(this.getPreferredSize().width,60));
        panelLoc.setPreferredSize(new Dimension(300,this.getPreferredSize().height-60));
        panelDanhSach.setPreferredSize(new Dimension(this.getPreferredSize().width-240,this.getPreferredSize().height-60));

        this.add(panelChucNang,BorderLayout.NORTH);
        this.add(panelLoc,BorderLayout.WEST);
        this.add(panelDanhSach,BorderLayout.CENTER);
        updateTable();
        tableTemp = panelDanhSach.getTableDS();
        tableTemp.addMouseListener(actionInfo);
        //panelChucNang.setBackground(new Color(27,101,147));
        //panelChucNang.setOpaque(true);
        //panelChucNang.setLayout(new BorderLayout());
        panelLoc.setBackground(Color.LIGHT_GRAY);
        panelLoc.setOpaque(true);
        panelLoc.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        panelDanhSach.setBackground(new Color(255, 182, 87,255));
        panelDanhSach.setOpaque(true);



        btexport = new JButton("Export");
        btexport.setPreferredSize(new Dimension(100, 40));
        btexport.setBackground(new Color(255, 197, 70));
        btexport.setForeground(new Color(0, 0, 0));
        btexport.setEnabled(false);
        btexport.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){                
                int selectedRow = panelDanhSach.getSelectedRow();
                String maDonChon = panelDanhSach.getTableDS().getModel().getValueAt(selectedRow, 0).toString();
                DonXuatMD donChon = DonXuatBLL.getFirstDonXuat(maDonChon);
                ArrayList<ChitietdonxuatMD> dsCT = DonXuatBLL.getDanhSachCTDX("MaDonXuat="+maDonChon);
                exportTableToExcel(donChon,dsCT);
            }
        });





        JButton btreload = new JButton("Refresh");
        btreload.setPreferredSize(new Dimension(100, 40));
        btreload.setBackground(new Color(255, 197, 70));
        btreload.setForeground(new Color(0, 0, 0));

        btreload.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
                btlook.setEnabled(false);
            }
            
        });
        //Object[][] dsDN = Model.to2DArray(DonNhap2BLL.getDanhSachDonNhap());

        btlook = new JButton("Xem đơn xuất");
        btlook.setPreferredSize(new Dimension(100, 40));
        btlook.setBackground(new Color(255, 197, 70));
        btlook.setForeground(new Color(0, 0, 0));
        btlook.setBorder(null);
        btlook.setOpaque(true);
        btlook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btlook.setEnabled(false);
        btloc = new JButton("Lọc");
        btloc.setPreferredSize(new Dimension(100, 40));
        btloc.setBackground(new Color(255, 197, 70));
        btloc.setForeground(new Color(0, 0, 0));
        btloc.setBorder(null);
        btloc.setOpaque(true);
        btloc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        date1 = new JDateChooser();
        date2 = new JDateChooser();
        btloc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               loc();
            }

            private void loc() {
                Date startDate = date1.getDate();
                Date endDate = date2.getDate();
                
                // Convert the dates to string format
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDateString = dateFormat.format(startDate);
                String endDateString = dateFormat.format(endDate);
                // Retrieve the data from the database and filter it based on the date range
                ArrayList<DSDonXuatMD> dsDN = DonXuatBLL.getDanhSachDX("NgayNhap >= " + startDateString , "NgayNhap <="+ endDateString );
                // Update the table with the filtered data
                String[] columnNames = {"Mã Đơn ", "Mã kho", "Mã Cty", "Tên Cty", "Mã NV", "Ngày nhập"};
                
                TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsDN), columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                panelDanhSach.SetTable(tableDanhSach, null);
            }
            
        }
        );
        panelLoc.add(btloc);
        panelLoc.add(date1);
        panelLoc.add(date2);
        SetupPanelChucNang();
        panelChucNang.add(btlook);
        panelChucNang.add(btexport);
        panelChucNang.add(btreload);
        setupPanel();
    }
    public void setupPanel(){
        String[] locPanelTitle = {"Lọc theo kho","Lọc theo công ty","Lọc theo sản phẩm của công ty"};
        int[] columnIndexes = {1,3,4};
        ArrayList<ArrayList<String>> tenLoc = new ArrayList<ArrayList<String>>();



        //Lấy danh sách kho và thêm vào bảng lộc
        ArrayList<KhoMD> danhSachKho = DonXuatBLL.getDanhSachKho();

        tenLoc.add(new ArrayList<String>());
        for(KhoMD kho : danhSachKho){          
            tenLoc.get(0).add(kho.getMaKho());
        }

        //Lấy danh sách khu vực và thêm vào bảng lộc
         ArrayList<CongtyMD> danhSachCongtyMD = DonXuatBLL.getDanhSachCongTy();
         tenLoc.add(new ArrayList<String>());
        for(CongtyMD congty : danhSachCongtyMD){          
            tenLoc.get(1).add(congty.getTenCty());
        }


        //Lấy danh sách khu vực và thêm vào bảng lộc
        // ArrayList<CongtyMD> danhSachCT = DonNhap2BLL.getDanhSachCongTy(); 

        // tenLoc.add(new ArrayList<String>());
        // for(CongtyMD cty : danhSachCT){
        //     tenLoc.get(2).add(cty.getTenCty());
        // }
        

        SetupPanelLoc(locPanelTitle, columnIndexes, tenLoc);

        //setup bảng
        // String[] columnNames = {"Mã Đơn ","Mã kho","Mã Cty","Tên Cty","Mã NV","Ngày nhập"};
        // ArrayList<DSDonNhapMD> dsDN = DonNhap2BLL.getDanhSachDN();
        // TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsDN),columnNames){
        //     @Override
        //     public boolean isCellEditable(int row, int column) {
        //         return false;
        //     }
        // };
        // UpdateTable(tableDanhSach);
        
    }
    //lọc theo loại sp và khu vực
    private ArrayList<JPanel> arrLocLabel = new ArrayList<JPanel>();
    private HashMap<JPanel,JScrollPane> arrLocPanel = new HashMap<JPanel,JScrollPane>();
    private ArrayList<JPanel> arrLocPanelWrapper = new ArrayList<JPanel>();

    private void xoaChucNangLocCu(){
        for(JPanel panelWrapper : arrLocPanelWrapper){
            panelLoc.remove(panelWrapper);
        }
        arrLocPanel.clear();
        arrLocLabel.clear();
        arrLocPanelWrapper.clear();
    }

    public void SetupPanelLoc(String[] panelTitles,int[] columnIndexes,ArrayList<ArrayList<String>> tenLoc){
        xoaChucNangLocCu();
        for(int i = 0 ; i < tenLoc.size();i++){
            themChucNangLoc(panelTitles[i],columnIndexes[i],tenLoc.get(i));
        }
        panelLoc.revalidate();
        panelLoc.repaint();
    }

    public void collapseClickedLocPanel(Object component){
        for(JPanel panel : arrLocLabel){
            if(component==panel){
                JScrollPane paneLoc = arrLocPanel.get(panel);
                paneLoc.setVisible(!paneLoc.isVisible());
                panelLoc.revalidate();
                panelLoc.repaint();
            }
        }
    }

    public void sortSelectedCheckbox(Object checkbox){
        JCheckBox cb = (JCheckBox)checkbox;
        LocPanel panel = (LocPanel)cb.getParent();
        
        if(cb.isSelected()){
            String key = cb.getName();
            this.panelDanhSach.themDieuKienLoc(panel.getColumnIndex(),key);
            this.panelDanhSach.locCacDieuKien();
        }
        else{
            String key = cb.getName();
            this.panelDanhSach.xoaDieuKienLoc(panel.getColumnIndex(),key);
            this.panelDanhSach.locCacDieuKien();
        }
    }
    MouseListener panelCollapseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {            
            collapseClickedLocPanel(e.getSource());
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
    String[] arr = new String[5];
    MouseListener actionInfo = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            int rowIndex = tableTemp.getSelectedRow();
            for(int i=0;i<arr.length;i++){
                arr[i] = tableTemp.getValueAt(rowIndex, i).toString();
            }

            btlook.setEnabled(true);
            btexport.setEnabled(true);
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

    ItemListener locCheckboxAction = new ItemListener() {

        @Override
        public void itemStateChanged(ItemEvent e) {
            sortSelectedCheckbox(e.getSource());
        }
        
    };
    private void themChucNangLoc(String title,int columnIndex,ArrayList<String> locLabelName){
        

        JLabel label = new JLabel(title);
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));
        labelPanel.add(label);
        labelPanel.setPreferredSize(new Dimension(panelLoc.getPreferredSize().width,30));
        labelPanel.addMouseListener(panelCollapseListener);
        arrLocLabel.add(labelPanel);
        LocPanel panel = new LocPanel();
        panel.setupPanel(locLabelName,columnIndex);
        JScrollPane scrollPane = new JScrollPane(panel);

        JPanel panelWrapper = new JPanel();
        arrLocPanelWrapper.add(panelWrapper);
        
        panelWrapper.setLayout(new BoxLayout(panelWrapper, BoxLayout.Y_AXIS));
        panelWrapper.add(labelPanel);
        panelWrapper.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(panelLoc.getPreferredSize().width,250));
        panelLoc.add(panelWrapper);
        arrLocPanel.put(labelPanel,scrollPane);

        panel.setActionForCheckBoxes(locCheckboxAction);
    }

    private String[] optionName;
    private String[] optionKey;
    private JComboBox<String> cbChonKho;

    ActionListener onChangeMaKho = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            setupPanel();
        }

    };

    ActionListener onSubmitSearch = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            timTheoGiaTri();
        }
    };
    
    public void SetupPanelChucNang(){
        JButton btadd = new JButton("Thêm đơn Xuất");
        btadd.setBorder(null);
        btadd.setPreferredSize(new Dimension(100,40));
        btadd.setBackground(new Color(0, 255, 119));
        btadd.setForeground(new Color(0, 0, 0));
        btadd.setOpaque(true);
        btadd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // xem  chi tiết đơn

        btadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormDon formTao = new FormDon("FormXuat");
                Window mainWindow = SwingUtilities.getWindowAncestor(panelChucNang);
                mainWindow.setEnabled(false);
                formTao.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e){
                        mainWindow.setEnabled(true);
                        mainWindow.setAlwaysOnTop(true);
                        mainWindow.setAlwaysOnTop(false);
                    }
                });
            }
        });                                                                                         
        btlook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormCTDX(arr[0]);
            }
        });
        panelChucNang.add(btadd);
        //panelChucNang.add(btlook);
    }

    //lấy mã kho đang chọn trong combobox
    // public String getSelectedMaKhoKey(){
    //     String selected = cbChonKho.getSelectedItem().toString();
    //     for(int i = 0; i < optionKey.length;i++){
    //         if(selected.equals(optionName[i])){
    //             return optionKey[i];
    //         }
    //     }
    //     return null;
    // }
    //tìm kiếm theo giá trị nhập
    String searchedText="";
    public void timTheoGiaTri(){
        String searchText = searchBar.getText();
        if (searchText.length() == 0) {
            searchedText="";
            panelDanhSach.themDieuKienLoc(1, searchText);
            this.panelDanhSach.locCacDieuKien();
        } else {
            try {
                this.panelDanhSach.xoaDieuKienLoc(1,searchedText);
                this.panelDanhSach.themDieuKienLoc(1,searchText);//1 là cột tên sản phẩm
                searchedText=searchText;
                this.panelDanhSach.locCacDieuKien();
            } catch (PatternSyntaxException ex) {
                System.err.println("Invalid regex pattern: " + ex.getMessage());
            }
        }
    }
    public void UpdateTable(TableModel table){
        this.panelDanhSach.SetTable(table,null);
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

    //tạo bảng và khởi tạo lại mảng chứa các đối tượng lọc
    private void exportTableToExcel(DonXuatMD dx,ArrayList<ChitietdonxuatMD> dsCT) {
        try {
            Workbook workbook = new XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Đơn xuất");
            org.apache.poi.ss.usermodel.Row sheetname = sheet.createRow(0);

            sheet.setColumnWidth(0, 20* 256);
            sheet.setColumnWidth(1, 20* 256);
            sheet.setColumnWidth(2, 20* 256);
            sheet.setColumnWidth(3, 20* 256);
            sheet.setColumnWidth(4, 20* 256);

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            
            Cell sheeCell=sheetname.createCell(0);
            sheeCell.setCellValue("Đơn xuất "+dx.getMaDonXuat());
            org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(1);
            Cell headerCell1=headerRow.createCell(0);
            headerCell1.setCellValue("Mã đơn xuất");
            headerCell1.setCellStyle(cellStyle);
            Cell headerCell2=headerRow.createCell(1);
            headerCell2.setCellValue("Mã kho");
            headerCell2.setCellStyle(cellStyle);
            Cell headerCell3=headerRow.createCell(2);
            headerCell3.setCellValue("Mã công ty");
            headerCell3.setCellStyle(cellStyle);
            Cell headerCell5=headerRow.createCell(3);
            headerCell5.setCellValue("Mã nhân viên");
            headerCell5.setCellStyle(cellStyle);
            Cell headerCell6=headerRow.createCell(4);
            headerCell6.setCellValue("Ngày xuất");
            headerCell6.setCellStyle(cellStyle);

            org.apache.poi.ss.usermodel.Row dataheaderRow = sheet.createRow(2);
            Cell dataheaderCell1=dataheaderRow.createCell(0);
            dataheaderCell1.setCellValue(dx.getMaDonXuat());
            dataheaderCell1.setCellStyle(cellStyle);
            Cell dataheaderCell2=dataheaderRow.createCell(1);
            dataheaderCell2.setCellValue(dx.getMaKho());
            dataheaderCell2.setCellStyle(cellStyle);
            Cell dataheaderCell3=dataheaderRow.createCell(2);
            dataheaderCell3.setCellValue(dx.getMaCty());
            dataheaderCell3.setCellStyle(cellStyle);
            Cell dataheaderCell4=dataheaderRow.createCell(3);
            dataheaderCell4.setCellValue(dx.getMaNV());
            dataheaderCell4.setCellStyle(cellStyle);
            Cell dataheaderCell5=dataheaderRow.createCell(4);
            dataheaderCell5.setCellValue(dx.getNgayXuat());
            dataheaderCell5.setCellStyle(cellStyle);

            org.apache.poi.ss.usermodel.Row CTDNRow = sheet.createRow(3);
            Cell sheeCell2=CTDNRow.createCell(0);
            sheeCell2.setCellValue("Chi tiết đơn xuất");

            org.apache.poi.ss.usermodel.Row dataCTDNRow = sheet.createRow(4);
            Cell mdn = dataCTDNRow.createCell(0);
            mdn.setCellValue("Mã đơn xuất");
            mdn.setCellStyle(cellStyle);
            Cell mmh = dataCTDNRow.createCell(1);
            mmh.setCellValue("Mã đơn nhập");
            mmh.setCellStyle(cellStyle);
            Cell mkv = dataCTDNRow.createCell(2);
            mkv.setCellValue("Mã mặt hàng");
            mkv.setCellStyle(cellStyle);
            Cell slnhap = dataCTDNRow.createCell(3);
            slnhap.setCellValue("Mã khu vực");
            slnhap.setCellStyle(cellStyle);
            Cell slconlai = dataCTDNRow.createCell(4);
            slconlai.setCellValue("Số lượng");
            slconlai.setCellStyle(cellStyle);

            for (int i=4;i<dsCT.size()+4;i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(i+1);
                ChitietdonxuatMD ct= dsCT.get(i-4);
                Cell madxCTDX=row.createCell(0);
                madxCTDX.setCellValue(ct.getMaDonXuat());
                madxCTDX.setCellStyle(cellStyle);
                Cell madnCTDX=row.createCell(1);
                madnCTDX.setCellValue(ct.getMaDonNhap());
                madnCTDX.setCellStyle(cellStyle);
                Cell mamhCTDX=row.createCell(2);
                mamhCTDX.setCellValue(ct.getMaMH());
                mamhCTDX.setCellStyle(cellStyle);
                Cell makvCTDX=row.createCell(3);
                makvCTDX.setCellValue(ct.getMaKV());
                makvCTDX.setCellStyle(cellStyle);
                Cell slCTDN=row.createCell(4);
                slCTDN.setCellValue(ct.getSoLuong());
                slCTDN.setCellStyle(cellStyle);
                
            }
            JFileChooser xuatFileChooser = new JFileChooser();
            xuatFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = xuatFileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // The user selected a file
                String selectedFilePath = xuatFileChooser.getSelectedFile().getPath()+"\\"+dx.getMaDonXuat()+".xlsx";
                FileOutputStream fileOutputStream = new FileOutputStream(selectedFilePath);
                workbook.write(fileOutputStream);
                fileOutputStream.close();
                new ThongBaoDialog("Đã xuất ra file "+dx.getMaDonXuat()+".xlsx", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateTable(){
        
        String[] columnNames = {"Mã Đơn ","Mã kho","Mã Cty","Tên Cty","Mã NV","Ngày nhập"};
        ArrayList<DSDonXuatMD> dsDX = DonXuatBLL.getDanhSachDX();
        TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsDX),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        panelDanhSach.SetTable(tableDanhSach, null);
        tableTemp = panelDanhSach.getTableDS();
        tableTemp.addMouseListener(actionInfo);
    }



}