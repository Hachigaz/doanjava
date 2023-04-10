package Panel.TraCuuHang;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class LocPanel extends JPanel{
    private HashMap<JCheckBox,String> options;
    public ArrayList<JCheckBox> checkBoxes;

    public LocPanel(String[] name, String[] key){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.checkBoxes = new ArrayList<JCheckBox>();
        this.options = new HashMap<JCheckBox,String>();
        for(int i = 0 ; i < name.length;i++){
            JCheckBox cb = new JCheckBox(name[i]);
            this.add(cb);
            checkBoxes.add(cb);
            this.options.put(cb,key[i]);
        }
    }
    public String getOptionKey(JCheckBox cb){
        return this.options.get(cb);
    }
    public void setActionForCheckBoxes(ItemListener a){
        for(int i = 0;i<this.checkBoxes.size();i++){
            this.checkBoxes.get(i).addItemListener(a);
        }
    }
}
