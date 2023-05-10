package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.sl.usermodel.*;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.cj.result.Row;

import BLL.DonNhapBLL;
import BLL.FormDonBLL;
import DTO.ChitietdonnhapMD;
import DTO.DonNhapMD;
import Panel.Form.FormDon;
import Panel.SubPanel.TablePanel;
import misc.DataSet;
public class DonNhapUI extends JPanel implements MouseListener{

    private final String sqlDonNhap = "select MaDonNhap as 'Mã đơn nhập', MaKho as 'Mã kho', MaCty as 'Mã công ty',MaNV as 'Mã nhân viên','NgayNhap' as 'Ngày nhập' from donnhap";
    private DonNhapBLL donnhapBLL;
    private JButton btadd,btlook,btexport,btimport;
    private TablePanel tablePanel;
    private JTable table;
    private JPanel pNorth;
    public DonNhapUI(DataSet dsdonnhap)
    {
        setLayout(new BorderLayout());
        TableModel model = new DefaultTableModel(dsdonnhap.getData(), dsdonnhap.getColumnName());
        pNorth = new JPanel();
        tablePanel = new TablePanel();
        tablePanel.SetTable(model, null);
        table=tablePanel.getTableDS();

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

        btexport = new JButton("Export");
        btexport.setPreferredSize(new Dimension(100, 40));
        btexport.setBackground(new Color(255, 197, 70));
        btexport.setForeground(new Color(0, 0, 0));

        btexport.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                exportTableToExcel();
            }
        });

        btimport = new JButton("Import");
        btimport.setPreferredSize(new Dimension(100, 40));
        btimport.setBackground(new Color(255, 197, 70));
        btimport.setForeground(new Color(0, 0, 0));

        btimport.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                importExceltoTable();
            }
        });

        btadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormDon("FormNhap");
            }
        });
        pNorth.add(btadd);
        pNorth.add(btlook);
        pNorth.add(btexport);
        pNorth.add(btimport);

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

            for (int i=0;i<table.getModel().getRowCount();i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(i+1);
                for (int j=0;j<table.getModel().getColumnCount();j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue(String.valueOf(table.getValueAt(i, j)));
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

    private void importExceltoTable() {
        String excelFilePath="D:/Java/BT_javaa/src/doanjava/Excel/Donnhap_import1.xlsx";
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

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

