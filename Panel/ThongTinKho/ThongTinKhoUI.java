package Panel.ThongTinKho;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import Panel.SubPanel.TablePanel;

public class ThongTinKhoUI extends JPanel{
    //sửa thông tin kho(tên, địa chỉ),thêm khu vực mới
    private HeaderOptionPanel headerPanel = new HeaderOptionPanel();
    private SideOptionPanel sidePanel = new SideOptionPanel();
    //danh sách có nút sửa thong tin và xoá khu vực
    private TablePanel danhSachPanel = new TablePanel();
    
    public ThongTinKhoUI(Dimension size){
        this.setOpaque(true);
        this.setPreferredSize(size);


        headerPanel.setPreferredSize(new Dimension(size.width,100));
        sidePanel.setPreferredSize(new Dimension(200, size.height-100));
        danhSachPanel.setPreferredSize(new Dimension(size.width-200,size.height-100));
        
        


        
        this.add(headerPanel,BorderLayout.NORTH);
        this.add(sidePanel,BorderLayout.WEST);
        this.add(danhSachPanel,BorderLayout.CENTER);
    }
    public void setupSubPanel(String maKhoHienTai){
        this.headerPanel.setupPanel(maKhoHienTai);
    }
}

class HeaderOptionPanel extends JPanel{
    public JLabel khoHienTaiLabel;
    public JButton themKhuVucBtn;
    public HeaderOptionPanel(){
        
        this.setLayout(new FlowLayout(FlowLayout.TRAILING,20,10));

        this.khoHienTaiLabel=new JLabel("Kho hiện tại");
        this.themKhuVucBtn=new JButton("Thêm khu vực mới");

        this.add(this.khoHienTaiLabel);
        this.add(themKhuVucBtn);
    }
    public void setupPanel(String tenKhoHienTai){
        this.khoHienTaiLabel.setText("Kho hiện tại: "+tenKhoHienTai);
        
        this.repaint();
        this.revalidate();
    }
}

class SideOptionPanel extends JPanel{
    
}