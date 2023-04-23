package Panel.ThongTinKho.Form;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import UI.TitleFrame;

public class FormThem extends TitleFrame {

    private JPanel content=new JPanel();
    // private JComboBox<String> loaiHangInput = new JComboBox<String>();

    private JPanel submitPanel=new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
    private JButton submitBtn;
    private JButton cancelBtn;

    private JPanel inputPanel;
    private ArrayList<FormInput> inputs;

    public FormThem(String dialogTitle,ArrayList<FormInput> inputFields,ActionListener submitAction,ActionListener cancelAction){
        inputs = inputFields;
        inputPanel = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel(dialogTitle);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        content.add(titleLabel);


        submitBtn = new JButton("Thêm");
        submitBtn.addActionListener(submitAction);

        cancelBtn = new JButton("Huỷ");
        cancelBtn.addActionListener(cancelAction);

    
        submitPanel.add(submitBtn);
        submitPanel.add(cancelBtn);
        

        inputPanel.setLayout(new GridLayout(inputs.size(),1));
        for(FormInput inputField : inputs){
            inputPanel.add(inputField);
        }
        content.add(inputPanel);

        this.content.add(submitPanel);
        this.setContentPanel(content);
        this.setVisible(true);
    }
}

