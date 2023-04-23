package Panel.ThongTinKho;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    public HeaderOptionPanel(){
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);

        this.khoHienTaiLabel=new JLabel("Kho hiện tại");
        this.tongSucChuaLabel = new JLabel("Tổng sức chứa");
        this.themKhuVucBtn=new JButton("Thêm khu vực mới");
        
        
        this.add(this.khoHienTaiLabel);
        this.add(this.tongSucChuaLabel);
        this.add(themKhuVucBtn);
    }
    public void setupPanel(String tenKhoHienTai,String tongSucChua,ActionListener themKhuVucAction){
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 20,20));
        this.khoHienTaiLabel.setText("Kho hiện tại: "+tenKhoHienTai);
        this.tongSucChuaLabel.setText("Tổng sức chứa: "+tongSucChua);
        this.repaint();
        this.revalidate();

        this.themKhuVucBtn.addActionListener(themKhuVucAction);
    }
}

class InfoDisplayPanel extends JPanel{
    //panel hiện lên khi vừa bấm vào chức năng
    public JPanel startPanel = new JPanel();
    //panel hiện chi tiết khu vực và option
    public JPanel infoPanel = new JPanel(new BorderLayout());
    
    public JPanel optionPanel = new JPanel();
    private JButton themLoaiBtn = new JButton("Thêm loại hàng vào khu vực");
    private JButton xoaLoaiBtn = new JButton("Xoá loại hàng đã chọn");

    public TablePanel chiTietKhuVucPanel = new TablePanel();
    //panel hiện khi bảng trống
    public JPanel tableNullMessagePanel = new JPanel();
    //panel form nhập ctkv
    public JPanel themCTKVPanel = new JPanel();
    private JComboBox<String> loaiHangCB = new JComboBox<>();
    private JButton submitCTKVBtn = new JButton("Thêm");
    private JButton cancelCTKVBtn = new JButton("Huỷ");
        public InfoDisplayPanel(){
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        startPanel.add(new JLabel("Chọn vào một khu vực để xem chi tiết"));


        tableNullMessagePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        tableNullMessagePanel.add(new JLabel("Khu vực chưa thêm loại hàng"));
        tableNullMessagePanel.setBackground(Color.orange);
        tableNullMessagePanel.setOpaque(true);


        optionPanel.setBackground(Color.orange);
        optionPanel.setOpaque(true);
        optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        optionPanel.add(themLoaiBtn);
        optionPanel.add(xoaLoaiBtn);

        infoPanel.add(optionPanel,BorderLayout.NORTH);
        infoPanel.add(chiTietKhuVucPanel,BorderLayout.CENTER);
        
        themCTKVPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,20));
        
        themCTKVPanel.add(new JLabel("Thêm loại mới vào khu vực"));
        themCTKVPanel.add(loaiHangCB);
        themCTKVPanel.add(submitCTKVBtn);
        themCTKVPanel.add(cancelCTKVBtn);
        

        infoPanel.setVisible(false);
        tableNullMessagePanel.setVisible(false);
        themCTKVPanel.setVisible(false);


        this.setLayout(new CardLayout());
        this.add(startPanel);
        this.add(infoPanel);
        this.add(tableNullMessagePanel);
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
        tableNullMessagePanel.setVisible(true);
    }
    public void setDisplayTable(){
        hideAllPanels();
        infoPanel.setVisible(true);
    }
    public void setDisplayThemCTKVPanel(){
        hideAllPanels();
        themCTKVPanel.setVisible(true);
    }


    private void hideAllPanels(){
        if(startPanel.isVisible()){
            startPanel.setVisible(false);
        }
        infoPanel.setVisible(false);
        tableNullMessagePanel.setVisible(false);
        themCTKVPanel.setVisible(false);
    }
    public int getSelectedTableRow(){
        return chiTietKhuVucPanel.getSelectedRow();
    }
}