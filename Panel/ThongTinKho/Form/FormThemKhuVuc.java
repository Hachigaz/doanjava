package Panel.ThongTinKho.Form;

import javax.swing.*;

import UI.TitleDialog;

public class FormThemKhuVuc extends TitleDialog {
    private JPanel content=new JPanel();
    private JPanel inputPanel=new JPanel();
    private JPanel submitPanel=new JPanel();
    private JButton submitBtn;
    private JButton cancelBtn;

    public FormThemKhuVuc(){
        submitBtn = new JButton("Thêm");
        cancelBtn = new JButton("Huỷ");
        submitPanel.add(submitBtn);
        submitPanel.add(cancelBtn);
        
        this.content.add(submitPanel);
        this.content.add(inputPanel);
        this.setContentPanel(content);
        this.setVisible(true);
    }
}
