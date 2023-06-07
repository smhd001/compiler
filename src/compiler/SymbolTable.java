package compiler;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable extends HashMap<String, Item> {

    public SymbolTable Parent = null;
    public ArrayList<SymbolTable> Children = new ArrayList<>();

    public SymbolTable(SymbolTable parent) {
        Parent = parent;
    }

    public void add_children(SymbolTable s) {
        this.Children.add(s);
    }

    @Override
    public String toString() {
        //TODO
        return null;
    }
}