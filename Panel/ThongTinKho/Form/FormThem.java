package Panel.ThongTinKho.Form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import misc.TitleFrame;

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

        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);
        
        JLabel titleLabel = new JLabel(dialogTitle);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        content.add(titleLabel);

        layout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 10, SpringLayout.HORIZONTAL_CENTER, content);
        

        
        submitBtn = new JButton("Thêm");
        submitBtn.addActionListener(submitAction);

        cancelBtn = new JButton("Huỷ");
        cancelBtn.addActionListener(cancelAction);

    
        submitPanel.add(submitBtn);
        submitPanel.add(cancelBtn);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        for(FormInput inputField : inputs){
            inputPanel.add(inputField);
            inputPanel.add(Box.createVerticalStrut(10));
        }


        inputPanel.setOpaque(true);
        content.add(inputPanel);
        layout.putConstraint(SpringLayout.NORTH, inputPanel, 100, SpringLayout.NORTH,content);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputPanel, 0, SpringLayout.HORIZONTAL_CENTER, content);


        this.content.add(submitPanel);
        layout.putConstraint(SpringLayout.SOUTH, submitPanel, -40, SpringLayout.SOUTH, content);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, submitPanel, 0, SpringLayout.HORIZONTAL_CENTER, content);
        
        this.setContentPane(content);

        this.setVisible(true);
    }
}

