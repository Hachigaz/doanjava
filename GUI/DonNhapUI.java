package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
public class DonNhapUI extends JPanel{
    //BLL
    private DonNhapBLL donNhapBLL = new DonNhapBLL();
    private JTable tableTemp;
    private JPanel panelChucNang;
    private JPanel panelLoc;
    private TablePanel panelDanhSach;
    public static JButton SPButton,btlook, btexport;


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
                //exportTableToExcel();
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
        SetupPanelChucNang();
        panelChucNang.add(btlook);
        panelChucNang.add(btexport);
        panelChucNang.add(btimport);
        panelChucNang.add(btreload);
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
        

        SetupPanelLoc(locPanelTitle, columnIndexes, tenLoc);

        //setup bảng
        // String[] columnNames = {"Mã Đơn ","Mã kho","Mã Cty","Tên Cty","Mã NV","Ngày nhập"};
        // ArrayList<DSDonNhapMD> dsDN = donNhapBLL.getDanhSachDN();
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
                new FormDon("FormNhap");
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
    private void exportTableToExcel() {
        try {
            Workbook workbook = new XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Đơn nhập");
            org.apache.poi.ss.usermodel.Row sheetname = sheet.createRow(0);
            Cell sheeCell=sheetname.createCell(0);
            sheeCell.setCellValue("Danh sách đơn nhập");
            org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(1);
            Cell headerCell1=headerRow.createCell(0);
            headerCell1.setCellValue("Mã đơn nhập");
            Cell headerCell2=headerRow.createCell(1);
            headerCell2.setCellValue("Mã kho");
            Cell headerCell3=headerRow.createCell(2);
            headerCell3.setCellValue("Mã công ty");
            Cell headerCell4=headerRow.createCell(3);
            headerCell4.setCellValue("Mã nhân viên");
            Cell headerCell5=headerRow.createCell(4);
            headerCell5.setCellValue("Ngày nhập");

            for (int i=0;i<panelDanhSach.getTableDS().getModel().getRowCount();i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(i+1);
                for (int j=0;j<panelDanhSach.getTableDS().getModel().getColumnCount();j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue(String.valueOf(panelDanhSach.getTableDS().getValueAt(i, j)));
                }
            }
            String filePath = "D:/Java/BT_javaa/src/doanjava/Excel/Donnhap.xlsx";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    System.out.println("A");
                }
            });
            formdon.setVisible(false);
            DonNhapMD dn=new DonNhapMD(column1Value, column2Value, column3Value, column4Value, column5Value);
            ArrayList<ChitietdonnhapMD> ctDN = new ArrayList<ChitietdonnhapMD>();


            for (int indexRow = 5; indexRow<=sheet.getLastRowNum();indexRow++) {
                String madon ="";
                String mamh="";
                String makv="";
                Float slnhap=0.0f;
                Float slconlai=0.0f;
                org.apache.poi.ss.usermodel.Row rowData = sheet.getRow(indexRow);
                
                Cell o1 = rowData.getCell(0);
            if(o1!=null) {
                madon = o1.getStringCellValue();
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



}