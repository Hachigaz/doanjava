package Panel.SubPanel;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class LocPanel extends JPanel{
    private int columnIndex;
    public ArrayList<JCheckBox> checkBoxes;

    public LocPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.checkBoxes = new ArrayList<JCheckBox>();
    }
    public void setupPanel(List<String> name,int columnIndex){
        for(JCheckBox cb : checkBoxes){
            this.remove(cb);
        }
        this.checkBoxes.clear();
        this.columnIndex = columnIndex;
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
