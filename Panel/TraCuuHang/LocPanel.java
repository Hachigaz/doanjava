package Panel.TraCuuHang;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class LocPanel extends JPanel{
    private int columnIndex;
    public ArrayList<JCheckBox> checkBoxes;

    public LocPanel(List<String> name, int columnIndex){
        this.columnIndex = columnIndex;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.checkBoxes = new ArrayList<JCheckBox>();
        for(int i = 0 ; i < name.size();i++){
            JCheckBox cb = new JCheckBox(name.get(i));
            cb.setName(name.get(i));
            this.add(cb);
            checkBoxes.add(cb);
        }
    }
    public void setActionForCheckBoxes(ItemListener a){
        for(int i = 0;i<this.checkBoxes.size();i++){
            this.checkBoxes.get(i).addItemListener(a);
        }
    }
    public int getColumnIndex() {
        return columnIndex;
    }
}
