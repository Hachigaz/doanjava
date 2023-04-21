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
    public JPanel infoPanel = new JPanel(new BorderLayout());
    public JPanel optionPanel = new JPanel();
    public ArrayList<JButton> actionButtons = new ArrayList<JButton>();
    public TablePanel chiTietKhuVucPanel = new TablePanel();
    
    public JPanel tableNullMessagePanel = new JPanel();

    public InfoDisplayPanel(){
        tableNullMessagePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        tableNullMessagePanel.add(new JLabel("Khu vực chưa thêm loại hàng"));
        tableNullMessagePanel.setBackground(Color.orange);
        tableNullMessagePanel.setOpaque(true);

        optionPanel.setBackground(Color.orange);
        optionPanel.setOpaque(true);
        
        infoPanel.add(optionPanel,BorderLayout.NORTH);
        infoPanel.add(chiTietKhuVucPanel,BorderLayout.CENTER);

        this.setLayout(new CardLayout());

        this.add(infoPanel);
        this.add(tableNullMessagePanel);

        this.setDisplayTable();
    }

    public void setupButtons(ArrayList<JButton> buttons){
        this.actionButtons = buttons;
        optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        for(JButton button : actionButtons){
            optionPanel.add(button);
        }
        this.add(optionPanel);
    }
    public void setDisplayNullMessage(){
        infoPanel.setVisible(false);
        tableNullMessagePanel.setVisible(true);
    }
    public void setDisplayTable(){
        infoPanel.setVisible(true);
        tableNullMessagePanel.setVisible(false);
    }
}