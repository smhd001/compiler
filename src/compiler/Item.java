package compiler;

import java.util.ArrayList;
import java.util.Iterator;

public class Item {
    String kind = null;
    String name = null;
    String return_type = null;
    ArrayList<Item> Parameter_list = new ArrayList<>();
    String type = null;

    boolean redefined = false;

    public Item(String kind, String name, String return_type, ArrayList<Item> parameter_list) { //for methods
        this.kind = kind;
        this.name = name;
        this.return_type = return_type;
        Parameter_list = parameter_list;
    }

    public Item(String kind, String name, String type) { //for fields
        this.kind = kind;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        if (kind.equals("Method"))
        {
            return kind + " (name : " +  name + ")" + " (return type: " + return_type +
                ") [parameter list: " + parameterListStr() + "]" ;
        }else {
            return kind + " (name : " +  name + ")" + " (type: " + type +")";
        }
    }
    public String parameterListStr () {
        if (Parameter_list == null) {
            return null;
        }
        Iterator<Item> iter = Parameter_list.iterator();
        StringBuilder str = new StringBuilder("[");
        int index = 0;
        while (true)
        {
            var next = iter.next();
            str.append("[type: ").append(next.type);
            str.append(", index: ").append(index).append("]");
            if (!iter.hasNext())
            {
                return str.append("]").toString();
            }
            str.append(", ");
            index++;
        }
    }
}
