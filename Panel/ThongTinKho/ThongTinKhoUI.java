package Panel.ThongTinKho;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import Panel.SubPanel.TablePanel;

public class ThongTinKhoUI extends JPanel{
    //sửa thông tin kho(tên, địa chỉ),thêm khu vực mới
    private HeaderOptionPanel headerPanel = new HeaderOptionPanel();
    private InfoDisplayPanel sidePanel = new InfoDisplayPanel();
    //danh sách có nút sửa thong tin và xoá khu vực
    private TablePanel danhSachPanel = new TablePanel();
    public ThongTinKhoUI(Dimension size){
        this.setLayout(new BorderLayout());
        this.setOpaque(true);
        this.setPreferredSize(size);


        headerPanel.setPreferredSize(new Dimension(size.width,70));
        sidePanel.setPreferredSize(new Dimension(700, size.height-70));
        danhSachPanel.setPreferredSize(new Dimension(size.width-700,size.height-70));
        
        sidePanel.setOpaque(true);
        sidePanel.setBackground(Color.darkGray);

        
        this.add(headerPanel,BorderLayout.NORTH);
        this.add(sidePanel,BorderLayout.EAST);
        this.add(danhSachPanel,BorderLayout.CENTER);
    }

    public void setupDanhSachPanel(TableModel tableModel,ListSelectionListener listener){
        this.danhSachPanel.SetTable(tableModel,listener);
    }

    public HeaderOptionPanel getHeaderPanel() {
        return headerPanel;
    }
    public InfoDisplayPanel getSidePanel() {
        return sidePanel;
    }
    public TablePanel getDanhSachPanel() {
        return danhSachPanel;
    }
}

class HeaderOptionPanel extends JPanel{
    public JLabel khoHienTaiLabel;
    public JLabel tongSucChuaLabel;
    public JButton themKhuVucBtn;
    public JButton xoaKhuVucBtn;
    private Font buttonFont = new Font("Helvetica",Font.BOLD,14);
    private Font displayFont = new Font("Tahoma",Font.BOLD,20);
    public HeaderOptionPanel(){
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);

        this.khoHienTaiLabel=new JLabel("Kho hiện tại");
        this.khoHienTaiLabel.setFont(displayFont);
        this.tongSucChuaLabel = new JLabel("Tổng sức chứa");
        this.tongSucChuaLabel.setFont(displayFont);
        this.themKhuVucBtn=new JButton("Thêm khu vực mới");
        this.themKhuVucBtn.setFont(buttonFont);
        this.xoaKhuVucBtn=new JButton("Xoá khu vực đã chọn");
        this.xoaKhuVucBtn.setFont(buttonFont);
        
        this.add(this.khoHienTaiLabel);
        this.add(this.tongSucChuaLabel);
        this.add(themKhuVucBtn);
    }
    public void setupPanel(String tenKhoHienTai,String tongSucChua,ActionListener themKhuVucAction,ActionListener xoaKhuVucAction){
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 20,20));
        this.khoHienTaiLabel.setText("Kho hiện tại: "+tenKhoHienTai);
        this.tongSucChuaLabel.setText("Tổng sức chứa: "+tongSucChua);
        this.repaint();
        this.revalidate();

        this.themKhuVucBtn.addActionListener(themKhuVucAction);
        this.xoaKhuVucBtn.addActionListener(xoaKhuVucAction);
    }
}

class InfoDisplayPanel extends JPanel{
    //panel hiện lên khi vừa bấm vào chức năng
    public JPanel startPanel = new JPanel();
    //panel hiện chi tiết khu vực và option
    public JPanel infoPanel = new JPanel(new BorderLayout());
    
    private Font buttonFont = new Font("Helvetica",Font.BOLD,14);
    private Font displayFont = new Font("Helvetica",Font.BOLD,16);

    public JPanel optionPanel = new JPanel();
    private JButton themLoaiBtn = new JButton("Thêm loại hàng vào khu vực");
    private JButton xoaLoaiBtn = new JButton("Xoá loại hàng đã chọn");

    public TablePanel chiTietKhuVucPanel = new TablePanel();
    //panel hiện khi bảng trống
    public JPanel tableNullMessagePanel = new JPanel();
    //panel form nhập ctkv
    public JPanel themCTKVPanel = new JPanel();
    private JComboBox<String> loaiHangCB = new JComboBox<>();
    private JPanel themCTKVOptionPanel = new JPanel();
    private JButton submitCTKVBtn = new JButton("Thêm");
    private JButton cancelCTKVBtn = new JButton("Huỷ");
        public InfoDisplayPanel(){
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        JLabel startLabel =new JLabel("Chọn vào một khu vực để xem chi tiết");
        startLabel.setFont(displayFont);
        startPanel.add(startLabel);


        tableNullMessagePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        JLabel tableNullMsgLabel =new JLabel("Khu vực chưa thêm loại hàng");
        tableNullMsgLabel.setFont(displayFont);
        tableNullMessagePanel.add(tableNullMsgLabel);
        tableNullMessagePanel.setBackground(Color.orange);
        tableNullMessagePanel.setOpaque(true);


        optionPanel.setBackground(Color.orange);
        optionPanel.setOpaque(true);
        optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        themLoaiBtn.setFont(buttonFont);
        optionPanel.add(themLoaiBtn);
        xoaLoaiBtn.setFont(buttonFont);
        optionPanel.add(xoaLoaiBtn);

        infoPanel.add(optionPanel,BorderLayout.NORTH);
        JPanel infoSubPanel = new JPanel(new CardLayout());
        infoPanel.add(infoSubPanel,BorderLayout.CENTER);
        infoSubPanel.add(chiTietKhuVucPanel);
        infoSubPanel.add(tableNullMessagePanel);
        
        themCTKVPanel.setLayout(new BoxLayout(themCTKVPanel,BoxLayout.Y_AXIS));

        JPanel headerCTKVOptionPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        JLabel themLoaiLabel = new JLabel("Thêm loại mới vào khu vực");
        themLoaiLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        headerCTKVOptionPanel.add(themLoaiLabel);
        themCTKVPanel.add(headerCTKVOptionPanel);
        JPanel inputOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        
        JLabel loaiHangCBLabel = new JLabel("Chọn loại hàng");
        loaiHangCBLabel.setFont(displayFont);
        inputOptionPanel.add(loaiHangCBLabel);
        loaiHangCB.setFont(buttonFont); 
        inputOptionPanel.add(loaiHangCB);
        
        themCTKVPanel.add(inputOptionPanel);

        submitCTKVBtn.setFont(buttonFont);
        cancelCTKVBtn.setFont(buttonFont);
        themCTKVOptionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20, 0));
        themCTKVOptionPanel.add(submitCTKVBtn);
        themCTKVOptionPanel.add(cancelCTKVBtn);

        themCTKVPanel.add(themCTKVOptionPanel);
        

        infoPanel.setVisible(false);
        tableNullMessagePanel.setVisible(false);
        themCTKVPanel.setVisible(false);


        this.setLayout(new CardLayout());
        this.add(startPanel);
        this.add(infoPanel);
        this.add(themCTKVPanel);

        setupXoaButton(null);
    }
    public void setupThemButton(ActionListener themKVAction){
        removeButtonListeners(themLoaiBtn);
        themLoaiBtn.addActionListener(themKVAction);
    }

    public void setupXoaButton(ActionListener xoaKVAction){
        if(xoaKVAction!=null){
            xoaLoaiBtn.setEnabled(true);
            removeButtonListeners(xoaLoaiBtn);
            xoaLoaiBtn.addActionListener(xoaKVAction);
        }
        else{
            xoaLoaiBtn.setEnabled(false);
        }
    }

    public void removeButtonListeners(JButton button){
        for(ActionListener listener : button.getActionListeners()){
            button.removeActionListener(listener);
        }
    }

    public void setupThemCTKVForm(String[] tenLoai,ActionListener submitAction,ActionListener cancelAction){
        for(int i = 0 ; i < tenLoai.length;i++){
            loaiHangCB.addItem(tenLoai[i]);
        }
        removeButtonListeners(submitCTKVBtn);
        submitCTKVBtn.addActionListener(submitAction);
        removeButtonListeners(cancelCTKVBtn);
        cancelCTKVBtn.addActionListener(cancelAction);

    }

    public String getSelectedItemInComboBox(){
        return loaiHangCB.getSelectedItem().toString();
    }

    public void setDisplayNullMessage(){
        hideAllPanels();
        infoPanel.setVisible(true);
        tableNullMessagePanel.setVisible(true);
    }
    public void setDisplayTable(){
        hideAllPanels();
        infoPanel.setVisible(true);
        chiTietKhuVucPanel.setVisible(true);
    }
    public void setDisplayThemCTKVPanel(){
        hideAllPanels();
        themCTKVPanel.setVisible(true);
    }


    private void hideAllPanels(){
        if(startPanel.isVisible()){
            startPanel.setVisible(false);
        }
        chiTietKhuVucPanel.setVisible(false);
        tableNullMessagePanel.setVisible(false);
        infoPanel.setVisible(false);
        themCTKVPanel.setVisible(false);
    }
    public int getSelectedTableRow(){
        return chiTietKhuVucPanel.getSelectedRow();
    }
}