package misc;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TitleDialog extends JDialog{
    private JLabel labelClose=new JLabel();
    private JPanel panelTitleBar=new JPanel();
    public JPanel contentPanel;
    public TitleDialog(){
        this.setModal(true);

        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
    
        ImageIcon iconClose = new ImageIcon("res/img/close.png");
        Image imgClose = iconClose.getImage();
        Image newImgClose = imgClose.getScaledInstance(13,13,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconClose = new ImageIcon(newImgClose);
    
        labelClose.setIcon(newIconClose);
        labelClose.setHorizontalAlignment(JLabel.CENTER);
        labelClose.setPreferredSize(new Dimension(30,20));
        labelClose.setBackground(new Color(255, 255, 153));
        labelClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelClose.setOpaque(true);
        labelClose.addMouseListener(listener);
    
        panelTitleBar = new JPanel();
        panelTitleBar.setPreferredSize(new Dimension(0,30));
        panelTitleBar.setBackground(new Color(255, 255, 153));
        panelTitleBar.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
    
        panelTitleBar.add(labelClose);
        this.add(panelTitleBar,BorderLayout.NORTH);
    }
    
    private MouseListener listener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource()==labelClose){
                dispose();
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
    
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
    
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource()==labelClose){
                labelClose.setBackground(Color.red);
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource()==labelClose){
                labelClose.setBackground(new Color(255, 255, 153));
            }
        }
    };    
    public void setContentPanel(JPanel panel){
        this.contentPanel = panel;
        this.add(contentPanel,BorderLayout.CENTER);
    }
}