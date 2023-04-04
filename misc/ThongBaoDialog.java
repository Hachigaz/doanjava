package misc;

import java.awt.Window;

import javax.swing.*;

public class ThongBaoDialog extends JDialog {
    public ThongBaoDialog(String msg,Window window){
        super(window,JDialog.ModalityType.DOCUMENT_MODAL);
        this.setTitle("Thông báo");

        JPanel panel = new JPanel();
        JLabel message = new JLabel(msg);  
        panel.add(message);

        // Add an EmptyBorder to the panel
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        // Add the panel to the dialog's content pane
        this.getContentPane().add(panel);

        // Pack the dialog and set its location
        this.pack();
        this.setLocationRelativeTo(null);

        // Show the dialog
        this.setVisible(true);
    }
}
