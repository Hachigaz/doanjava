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
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BLL.*;
import DTO.ChitietdonnhapMD;
import DTO.CongtyMD;
import DTO.DonNhapMD;
import DTO.KhoMD;
import DTO.Model;
import DTO.Custom.DSDonNhapMD;
import Panel.Form.FormCTDN;
import Panel.Form.FormDon;
import Panel.SubPanel.LocPanel;
import Panel.SubPanel.TablePanel;
import misc.ThongBaoDialog;


import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.options.convert.PdfConvertOptions;
import java.io.File;

import com.toedter.calendar.JDateChooser;

public class DonNhapUI extends JPanel{
    //BLL
    private DonNhapBLL donNhapBLL = new DonNhapBLL();
    private JTable tableTemp;
    private JPanel panelChucNang;
    private JPanel panelLoc;
    private TablePanel panelDanhSach;
    public static JButton btloc,btlook, btexport,btpdf;
    //private JCalendar date1,date2;
    public static JDateChooser date1,date2;
    private JTextField searchBar;



    public DonNhapUI(Dimension d){

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

        //Object[][] dsDN = Model.to2DArray(donNhapBLL.getDanhSachDonNhap());

        btlook = new JButton("Xem đơn nhập");
        btlook.setPreferredSize(new Dimension(100, 40));
        btlook.setBackground(new Color(255, 197, 70));
        btlook.setForeground(new Color(0, 0, 0));
        btlook.setBorder(null);
        btlook.setOpaque(true);
        btlook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btlook.setEnabled(false);

       
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
                DonNhapMD donChon = donNhapBLL.getFirstDonNhap(maDonChon);
                ArrayList<ChitietdonnhapMD> dsCT = donNhapBLL.getDanhSachCTDN("MaDonNhap="+maDonChon);
                JFileChooser xuatFileChooser = new JFileChooser();
                xuatFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = xuatFileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // The user selected a file
                    String selectedFilePath = xuatFileChooser.getSelectedFile().getPath()+"\\"+donChon.getMaDonNhap()+".xlsx";
                    if(exportTableToExcel(donChon,dsCT,selectedFilePath))
                    {
                        new ThongBaoDialog("Đã xuất ra file "+donChon.getMaDonNhap()+".xlsx", null);
                    }
                }
            }
        });

        JButton btreload = new JButton("Refresh");
        btreload.setPreferredSize(new Dimension(100, 40));
        btreload.setBackground(new Color(255, 197, 70));
        btreload.setForeground(new Color(0, 0, 0));
        btreload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelLoc.remove(btloc);
                panelLoc.remove(date1);
                panelLoc.remove(date2);
                setupPanel();
                updateTable();
                btlook.setEnabled(false);
            }
            
        });


        JButton btimport = new JButton("Import");
        btimport.setPreferredSize(new Dimension(100, 40));
        btimport.setBackground(new Color(255, 197, 70));
        btimport.setForeground(new Color(0, 0, 0));

        btimport.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx", "xlsx");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // The user selected a file
                    String selectedFilePath = fileChooser.getSelectedFile().getPath();
                    importExceltoTable(selectedFilePath);
                }
            }
        });

        btpdf = new JButton("In đơn nhập");
        btpdf.setPreferredSize(new Dimension(100, 40));
        btpdf.setBackground(new Color(255, 197, 70));
        btpdf.setForeground(new Color(0, 0, 0));

        btpdf.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){                
                int selectedRow = panelDanhSach.getSelectedRow();
                String maDonChon = panelDanhSach.getTableDS().getModel().getValueAt(selectedRow, 0).toString();
                DonNhapMD donChon = donNhapBLL.getFirstDonNhap(maDonChon);
                ArrayList<ChitietdonnhapMD> dsCT = donNhapBLL.getDanhSachCTDN("MaDonNhap="+maDonChon);
                JFileChooser xuatFileChooser = new JFileChooser();
                xuatFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = xuatFileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // The user selected a file
                    String selectedFilePath = xuatFileChooser.getSelectedFile().getPath()+"\\"+donChon.getMaDonNhap()+".xlsx";
                    if(exportTableToExcel(donChon, dsCT, selectedFilePath)){
                        exportTableToPdf(selectedFilePath);
                    }
                }
            }
        });
        SetupPanelChucNang();
        panelChucNang.add(btlook);
        panelChucNang.add(btexport);
        panelChucNang.add(btimport);
        panelChucNang.add(btreload);
        panelChucNang.add(btpdf);
        setupPanel();
        
    }
    public void setupPanel(){
        String[] locPanelTitle = {"Lọc theo kho","Lọc theo công ty","Lọc theo sản phẩm của công ty"};
        int[] columnIndexes = {1,3,4};
        ArrayList<ArrayList<String>> tenLoc = new ArrayList<ArrayList<String>>();



        //Lấy danh sách kho và thêm vào bảng lộc
        ArrayList<KhoMD> danhSachKho = donNhapBLL.getDanhSachKho();

        tenLoc.add(new ArrayList<String>());
        for(KhoMD kho : danhSachKho){          
            tenLoc.get(0).add(kho.getMaKho());
        }

        //Lấy danh sách khu vực và thêm vào bảng lộc
         ArrayList<CongtyMD> danhSachCongtyMD = donNhapBLL.getDanhSachCongTy();
         tenLoc.add(new ArrayList<String>());
        for(CongtyMD congty : danhSachCongtyMD){          
            tenLoc.get(1).add(congty.getTenCty());
        }

        //ArrayList<ChitietdonnhapMD> ngaynhap = donNhapBLL.get
        //Lấy danh sách khu vực và thêm vào bảng lộc
        // ArrayList<CongtyMD> danhSachCT = donNhapBLL.getDanhSachCongTy(); 

        // tenLoc.add(new ArrayList<String>());
        // for(CongtyMD cty : danhSachCT){
        //     tenLoc.get(2).add(cty.getTenCty());
        // }
        btloc = new JButton("Lọc");
        btloc.setPreferredSize(new Dimension(300, 40));
        btloc.setBackground(new Color(255, 197, 70));
        btloc.setForeground(new Color(0, 0, 0));
        btloc.setBorder(null);
        btloc.setOpaque(true);
        btloc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        date1 = new JDateChooser();
        date2 = new JDateChooser();
        date1.setPreferredSize(new Dimension(200, 30));
        date2.setPreferredSize(new Dimension(200, 30));
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
                if (startDate==null && endDate == null){
                    new ThongBaoDialog("Chọn ngày đi ", null);
                }
                else if (startDate==null){
                String endDateString = dateFormat.format(endDate);
                // Retrieve the data from the database and filter it based on the date range
                ArrayList<DSDonNhapMD> dsDN = donNhapBLL.getDanhSachDN("NgayNhap <="+ endDateString );
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
                else if(endDate == null){
                    String startDateString = dateFormat.format(startDate);
                
                // Retrieve the data from the database and filter it based on the date range
                ArrayList<DSDonNhapMD> dsDN = donNhapBLL.getDanhSachDN("NgayNhap >= " + startDateString );
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
                else{
                String startDateString = dateFormat.format(startDate);
                String endDateString = dateFormat.format(endDate);
                // Retrieve the data from the database and filter it based on the date range
                ArrayList<DSDonNhapMD> dsDN = donNhapBLL.getDanhSachDN("NgayNhap >= " + startDateString , "NgayNhap <="+ endDateString );
                // Update the table with the filtered data
                String[] columnNames = {"Mã Đơn ", "Mã kho", "Mã Cty", "Tên Cty", "Mã NV", "Ngày nhập"};
                
                TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsDN), columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                panelDanhSach.SetTable(tableDanhSach, null);
            }}
        
        }
        );
        panelLoc.add(btloc);
        panelLoc.add(date1);
        panelLoc.add(date2);
        SetupPanelLoc(locPanelTitle, columnIndexes, tenLoc);


        
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
            btexport.setEnabled(true);
            btlook.setEnabled(true);
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
        JButton btadd = new JButton("Thêm đơn nhập");
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
                FormDon formTao = new FormDon("FormNhap");
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
                new FormCTDN(arr[0]);
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
    private boolean exportTableToExcel(DonNhapMD dn,ArrayList<ChitietdonnhapMD> dsCT,String selectedFilePath) {
        try {
            Workbook workbook = new XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Đơn nhập");
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
            sheeCell.setCellValue("Đơn nhập "+dn.getMaDonNhap());
            org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(1);
            Cell headerCell1=headerRow.createCell(0);
            headerCell1.setCellValue("Mã đơn nhập");
            headerCell1.setCellStyle(cellStyle);
            Cell headerCell2=headerRow.createCell(1);
            headerCell2.setCellValue("Mã kho");
            headerCell2.setCellStyle(cellStyle);
            Cell headerCell3=headerRow.createCell(2);
            headerCell3.setCellValue("Mã công ty");
            headerCell3.setCellStyle(cellStyle);
            Cell headerCell4=headerRow.createCell(3);
            headerCell4.setCellValue("Mã nhân viên");
            headerCell4.setCellStyle(cellStyle);
            Cell headerCell5=headerRow.createCell(4);
            headerCell5.setCellValue("Ngày nhập");
            headerCell5.setCellStyle(cellStyle);

            org.apache.poi.ss.usermodel.Row dataheaderRow = sheet.createRow(2);
            Cell dataheaderCell1=dataheaderRow.createCell(0);
            dataheaderCell1.setCellValue(dn.getMaDonNhap());
            dataheaderCell1.setCellStyle(cellStyle);
            Cell dataheaderCell2=dataheaderRow.createCell(1);
            dataheaderCell2.setCellValue(dn.getMaKho());
            dataheaderCell2.setCellStyle(cellStyle);
            Cell dataheaderCell3=dataheaderRow.createCell(2);
            dataheaderCell3.setCellValue(dn.getMaCty());
            dataheaderCell3.setCellStyle(cellStyle);
            Cell dataheaderCell4=dataheaderRow.createCell(3);
            dataheaderCell4.setCellValue(dn.getMaNV());
            dataheaderCell4.setCellStyle(cellStyle);
            Cell dataheaderCell5=dataheaderRow.createCell(4);
            dataheaderCell5.setCellValue(dn.getNgayNhap());
            dataheaderCell5.setCellStyle(cellStyle);

            org.apache.poi.ss.usermodel.Row CTDNRow = sheet.createRow(3);
            Cell sheeCell2=CTDNRow.createCell(0);
            sheeCell2.setCellValue("Chi tiết đơn nhập");

            org.apache.poi.ss.usermodel.Row dataCTDNRow = sheet.createRow(4);
            Cell mdn = dataCTDNRow.createCell(0);
            mdn.setCellValue("Mã đơn nhập");
            mdn.setCellStyle(cellStyle);
            Cell mmh = dataCTDNRow.createCell(1);
            mmh.setCellValue("Mã mặt hàng");
            mmh.setCellStyle(cellStyle);
            Cell mkv = dataCTDNRow.createCell(2);
            mkv.setCellValue("Mã khu vực");
            mkv.setCellStyle(cellStyle);
            Cell slnhap = dataCTDNRow.createCell(3);
            slnhap.setCellValue("Số lượng nhập");
            slnhap.setCellStyle(cellStyle);
            Cell slconlai = dataCTDNRow.createCell(4);
            slconlai.setCellValue("Số lượng còn lại");
            slconlai.setCellStyle(cellStyle);

            for (int i=4;i<dsCT.size()+4;i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(i+1);
                ChitietdonnhapMD ct= dsCT.get(i-4);
                Cell madnCTDN=row.createCell(0);
                madnCTDN.setCellValue(ct.getMaDonNhap());
                madnCTDN.setCellStyle(cellStyle);
                Cell mamhCTDN=row.createCell(1);
                mamhCTDN.setCellValue(ct.getMaMH());
                mamhCTDN.setCellStyle(cellStyle);
                Cell makvCTDN=row.createCell(2);
                makvCTDN.setCellValue(ct.getMaKV());
                makvCTDN.setCellStyle(cellStyle);
                Cell slnhapCTDN=row.createCell(3);
                slnhapCTDN.setCellValue(ct.getSLNhap());
                slnhapCTDN.setCellStyle(cellStyle);
                Cell slconlaiCTDN=row.createCell(4);
                slconlaiCTDN.setCellValue(ct.getSLConLai());
                slconlaiCTDN.setCellStyle(cellStyle);
            }

            FileOutputStream fileOutputStream = new FileOutputStream(selectedFilePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void importExceltoTable(String excelFilePath) {
        FormDonBLL formDonBLL = new FormDonBLL();
        
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);

            Workbook workbook = new XSSFWorkbook(inputStream);

            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

            org.apache.poi.ss.usermodel.Row row = sheet.getRow(2);
            String column1Value="";
            String column2Value="";
            String column3Value="";
            String column4Value="";
            String column5Value="";

            Cell cell1 = row.getCell(0);
            if(cell1!=null) {
                column1Value = cell1.getStringCellValue();
            }

            Cell cell2 = row.getCell(1);
            if(cell2!=null) {
                column2Value = cell2.getStringCellValue();
            }

            Cell cell3 = row.getCell(2);
            if(cell3!=null) {
                column3Value = cell3.getStringCellValue();
            }
            System.out.println(column3Value);

            Cell cell4 = row.getCell(3);
            if(cell4!=null) {
                column4Value = cell4.getStringCellValue();
            }

            Cell cell5 = row.getCell(4);
            if(cell5!=null) {
                column5Value = cell5.getStringCellValue();
            }

            FormDon formdon =new FormDon("FormNhap");
            formdon.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e){
                    updateTable();
                }
            });
            formdon.setVisible(false);
            DonNhapMD dn=new DonNhapMD(donNhapBLL.taoMaDonNhapMoi(), column2Value, column3Value, column4Value, column5Value);
            ArrayList<ChitietdonnhapMD> ctDN = new ArrayList<ChitietdonnhapMD>();


            for (int indexRow = 4; indexRow<=sheet.getLastRowNum();indexRow++) {
                String madon ="";
                String mamh="";
                String makv="";
                Float slnhap=0.0f;
                Float slconlai=0.0f;
                org.apache.poi.ss.usermodel.Row rowData = sheet.getRow(indexRow);
                
                Cell o1 = rowData.getCell(0);
            if(o1!=null) {
                madon = donNhapBLL.taoMaDonNhapMoi();
            }

            Cell o2 = rowData.getCell(1);
            if(o2!=null) {
                mamh = o2.getStringCellValue();
            }

            Cell o3 = rowData.getCell(2);
            if(o3!=null) {
                makv = o3.getStringCellValue();
            }

            Cell o4 = rowData.getCell(3);
            if(o4.getCellType()==CellType.NUMERIC) {
                slnhap = (float) o4.getNumericCellValue();
            }

            Cell o5 = rowData.getCell(4);
            if(o5.getCellType()==CellType.NUMERIC) {
                slconlai = (float) o5.getNumericCellValue();
            }
            
            ctDN.add(new ChitietdonnhapMD(madon, mamh, makv, slnhap, slconlai));
            }
            formDonBLL.themDonNhapMoi(dn, ctDN);
            updateTable();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void updateTable(){

        String[] columnNames = {"Mã Đơn ","Mã kho","Mã Cty","Tên Cty","Mã NV","Ngày nhập"};
        ArrayList<DSDonNhapMD> dsDN = donNhapBLL.getDanhSachDN();
        TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsDN),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        panelDanhSach.SetTable(tableDanhSach, null);
        tableTemp = panelDanhSach.getTableDS();
        tableTemp.addMouseListener(actionInfo);
    }
    private void exportTableToPdf(String path){
            // The user selected a file
            System.out.println(path);
            File file = new File(path);
            Converter converter = new Converter(file.getPath());
            PdfConvertOptions options = new PdfConvertOptions();
            converter.convert(file.getPath().replace("xlsx", "pdf"), options);            
            new ThongBaoDialog("Đã xuất ra file pdf", null);
            converter.close();
    }
}