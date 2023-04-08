package Quyen;
import javax.swing.*;

public abstract class Quyen {
    public String title;
    public JPanel panel;
    public String[] image;
    public Quyen(String title,JPanel panel){
        this.title = title;
        this.panel = panel;
    }
}
