package UI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TitleFrame extends JFrame{
    private JLabel labelHide=new JLabel();
    private JLabel labelClose=new JLabel();
    private JPanel titleBar=new JPanel();
    public JPanel contentPanel;
    public TitleFrame(){
        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());

        ImageIcon iconHide = new ImageIcon("res/img/minimum.png");
        Image imgHide = iconHide.getImage();
        Image newImgHide = imgHide.getScaledInstance(13,13,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconHide = new ImageIcon(newImgHide);

        ImageIcon iconClose = new ImageIcon("res/img/close.png");
        Image imgClose = iconClose.getImage();
        Image newImgClose = imgClose.getScaledInstance(13,13,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconClose = new ImageIcon(newImgClose);

        labelHide.setIcon(newIconHide);
        labelHide.setHorizontalAlignment(JLabel.CENTER);
        labelHide.setPreferredSize(new Dimension(30,20));
        labelHide.setBackground(new Color(0,155,254));
        labelHide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelHide.setOpaque(true);
        labelHide.addMouseListener(listener);

        labelClose.setIcon(newIconClose);
        labelClose.setHorizontalAlignment(JLabel.CENTER);
        labelClose.setPreferredSize(new Dimension(30,20));
        labelClose.setBackground(new Color(0,155,254));
        labelClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelClose.setOpaque(true);
        labelClose.addMouseListener(listener);

        titleBar = new JPanel();
        titleBar.setPreferredSize(new Dimension(0,30));
        titleBar.setBackground(new Color(0,155,254));
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));

        titleBar.add(labelHide);
        titleBar.add(labelClose);
        
        this.add(titleBar,BorderLayout.NORTH);
    }
    public void setContentPanel(JPanel panel){
        this.contentPanel = panel;
        this.add(contentPanel,BorderLayout.CENTER);
    }
    private MouseListener listener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
        }
        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource()==labelHide){
                setExtendedState(JFrame.ICONIFIED); 
            }else if(e.getSource()==labelClose){
                dispose();
            }
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
            }else if(e.getSource()==labelHide){
                labelHide.setBackground(Color.gray);
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource()==labelClose){
                labelClose.setBackground(new Color(0,155,254));
            }else if(e.getSource()==labelHide){
                labelHide.setBackground(new Color(0,155,254));
            }
        }
    };
}
