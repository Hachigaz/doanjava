package Quyen.XemDSMH;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class DSMHPanel extends JPanel{
    private JPanel filterPanel;
    private JPanel dataPanel;
    public DSMHPanel(){
        filterPanel = new JPanel();
        dataPanel = new JPanel();

        this.add(filterPanel,BorderLayout.NORTH);
        this.add(dataPanel,BorderLayout.CENTER);

        
    }
}
