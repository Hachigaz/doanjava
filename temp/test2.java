package temp;

import java.util.ArrayList;

public class test2 {
    public static void main(String[] args) {
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(10);
        arr.add("20");
        for(int i = 0 ; i < arr.size();i++){
            System.out.println(arr.get(i));
        }
    }
}
