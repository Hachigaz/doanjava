package Panel.Form;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class FormInput extends JPanel{
    private Font inputFont = new Font("Helvetica", Font.BOLD,14);
    private JLabel inputLabel;
    private JComponent inputType;
    public FormInput(String inputName,JComponent inputField){
        this.inputLabel = new JLabel(inputName);
        this.inputLabel.setFont(inputFont);

        this.inputType = inputField;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        layout.putConstraint(SpringLayout.WEST, inputLabel, 20, SpringLayout.WEST, this);
        layout.putConstraint(inputName, inputField, null, inputName, inputField);
        layout.putConstraint(SpringLayout.EAST, inputType, -10, SpringLayout.EAST, this);
        this.add(inputLabel);
        this.add(inputType);
        this.setPreferredSize(new Dimension(180+inputType.getPreferredSize().width, inputType.getPreferredSize().height));
    }
    public JComponent getInputComponent() {
        return inputType;
    }
}