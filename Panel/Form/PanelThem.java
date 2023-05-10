package Panel.Form;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.*;

public class PanelThem extends JPanel {
    
    // private JComboBox<String> loaiHangInput = new JComboBox<String>();

    private JPanel submitPanel=new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
    private JButton submitBtn;
    private JButton cancelBtn;

    private JPanel inputPanel;
    private ArrayList<FormInput> inputs;

    public PanelThem(String dialogTitle,ArrayList<FormInput> inputFields,ActionListener submitAction,ActionListener cancelAction){
        inputs = inputFields;
        inputPanel = new JPanel();

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        
        JLabel titleLabel = new JLabel(dialogTitle);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        this.add(titleLabel);

        layout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 10, SpringLayout.HORIZONTAL_CENTER, this);
        

        
        submitBtn = new JButton("Thêm");
        submitBtn.addActionListener(submitAction);

        cancelBtn = new JButton("Huỷ");
        cancelBtn.addActionListener(cancelAction);

    
        submitPanel.add(submitBtn);
        submitPanel.add(cancelBtn);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int parentWidth = getWidth();
                Dimension childSize = inputPanel.getPreferredSize();
                childSize.width = parentWidth-30;
                inputPanel.setPreferredSize(childSize);
                inputPanel.revalidate();
            }
        });

        for(FormInput inputField : inputs){
            inputPanel.add(inputField);
            inputPanel.add(Box.createVerticalStrut(10));
        }


        inputPanel.setOpaque(true);
        this.add(inputPanel);
        layout.putConstraint(SpringLayout.NORTH, inputPanel, 100, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);


        this.add(submitPanel);
        layout.putConstraint(SpringLayout.SOUTH, submitPanel, -40, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, submitPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);

        this.setVisible(true);
    }
}
