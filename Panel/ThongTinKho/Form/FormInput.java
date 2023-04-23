package Panel.ThongTinKho.Form;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class FormInput extends JPanel{
    private Font inputFont = new Font("Helvetica", Font.BOLD,14);
    private JLabel inputLabel;
    private JComponent inputType;
    public FormInput(String inputName,JComponent inputField){
        this.inputLabel = new JLabel(inputName);
        this.inputLabel.setFont(inputFont);

        this.inputType = inputField;
        this.setLayout(new GridLayout(1,2));
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        layout.putConstraint(SpringLayout.WEST, inputLabel, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, inputType, 180, SpringLayout.WEST, this);
        this.add(inputLabel);
        this.add(inputType);
    }
    public JComponent getInputComponent() {
        return inputType;
    }
}