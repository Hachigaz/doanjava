package Panel.SubPanel;

import java.util.HashMap;

import javax.swing.JComboBox;

public class CustomComboBox extends JComboBox<String>{
    private HashMap<String,String> keys = new HashMap<String,String>();
    public void addItem(String name,String key){
        super.addItem(name);
        keys.put(name,key);
    }
    public String getSelectedKey(){
        return keys.get(super.getSelectedItem());
    }
}
