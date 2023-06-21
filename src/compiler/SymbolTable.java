package compiler;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable extends HashMap<String, Item> {

    public SymbolTable Parent = null;
    private final String name;
    private final int number;
    public ArrayList<SymbolTable> Children = new ArrayList<>();

    public SymbolTable(SymbolTable parent, String name , int number) {
        Parent = parent;
        this.name = name;
        this.number = number;
    }

    public void add_children(SymbolTable s) {
        this.Children.add(s);
    }

    @Override
    public String toString() {
        return  "----------------" + this.name + " : " + number + "---------------------------------------------------\n" +
                printItem() +
                "-----------------------------------------------------------------------------------------------------\n";
    }

    private String printItem() {
        StringBuilder itemsStr = new StringBuilder();
        for (var entry:
                this.entrySet()) {
              itemsStr.append("Key = ").append(entry.getKey()).append("  | Value = ").append(entry.getValue()).append("\n");
        }
        return itemsStr.toString();
    }
}