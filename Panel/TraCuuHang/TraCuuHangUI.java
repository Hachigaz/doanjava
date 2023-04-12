package Panel.TraCuuHang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import misc.util;
public class TraCuuHangUI extends JPanel{

    private JPanel panelChucNang;
    private JPanel panelLoc;
    private JPanel panelDanhSach;
    
    private TableRowSorter<TableModel> rowSorter;
    private JScrollPane bangDanhSach;

    private JTextField searchBar;


    
    public TraCuuHangUI(){

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

    
    }
    //lọc theo loại sp và khu vực
    private ArrayList<JLabel> locArrLabel = new ArrayList<JLabel>();
    private HashMap<JLabel,JScrollPane> locArrPanel = new HashMap<JLabel,JScrollPane>();
    
    public void SetupPanelLoc(DataSet[] dsLoc,String[] tenLoc){
        for(int i = 0 ; i < tenLoc.length;i++){
            themChucNangLoc(tenLoc[i],dsLoc[i]);
        }
        panelLoc.revalidate();
        panelLoc.repaint();
    }

    private void themChucNangLoc(String title,DataSet dsCNLoc){
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

        LocPanel panel = new LocPanel(util.objToString(dsCNLoc.getColumn(1)),util.objToString(dsCNLoc.getColumn(0)));
        
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

    public void SetupPanelChucNang(DataSet dsKho,ActionListener onChangeMaKho, ActionListener onSubmitSearch){
        JLabel labelChonKho = new JLabel("Chọn kho");

        optionName = util.objToString(dsKho.getColumn(1));
        optionKey = util.objToString(dsKho.getColumn(0));
        
        cbChonKho = new JComboBox<String>(optionName);

        panelChucNang.add(labelChonKho);
        panelChucNang.add(cbChonKho);

        cbChonKho.addActionListener(onChangeMaKho);

        searchBar = new JTextField(20);
        searchBar.addActionListener(onSubmitSearch);

        panelChucNang.add(searchBar);
    }

    //lấy mã kho đang chọn trong combobox
    public String getSelectedMaKhoKey(){
        String selected = cbChonKho.getSelectedItem().toString();   
        for(int i = 0; i < optionKey.length;i++){
            if(selected.equals(optionName[i])){
                return optionKey[i];
            }
        }
        return null;
    }
    //tìm kiếm theo giá trị nhập
    public void timTheoGiaTri(){
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

    public void SetTable(DataSet ds){
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