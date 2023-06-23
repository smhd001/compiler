package compiler;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable extends HashMap<String, Item> {

    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";
    private final String name;
    private final int lineNumber;
    private final ArrayList<SymbolTable> Children = new ArrayList<>();
    public SymbolTable Parent = null;

    public SymbolTable(SymbolTable parent, String name, int number) {
        Parent = parent;
        if (parent != null) {
            parent.add_children(this);
        }
        this.name = name;
        this.lineNumber = number;
    }

    public Item put(String key, Item value, ParserRuleContext ctx) {
        if (this.containsKey(key)) {
            int line = ctx.start.getLine();
            int column = ctx.start.getCharPositionInLine();
            value.redefined = true;
            if (value.kind.equals("Method")) {
                System.out.println(
                        ANSI_RED +
                                "Error102 : in line [" + line + ":" + column + "]" +
                                ", method " + value.name + "  has been defined already" +
                                ANSI_RESET
                );
            } else {
                System.out.println(
                        ANSI_RED +
                                "Error104 : in line [" + line + ":" + column + "]" +
                                ", field " + value.name + "  has been defined already" +
                                ANSI_RESET
                );
            }
            key += "_" + line + "_" + column;
        }
        return super.put(key, value);
    }

    public void add_children(SymbolTable s) {
        this.Children.add(s);
    }

    public ArrayList<SymbolTable> getChildren() {
        return Children;
    }

    @Override
    public String toString() {
        return "----------------" + this.name + " : " + lineNumber + "---------------------------------------------------\n" +
                printItem() +
                "-----------------------------------------------------------------------------------------------------\n";
    }

    private String printItem() {
        StringBuilder itemsStr = new StringBuilder();
        for (var entry :
                this.entrySet()) {
            itemsStr.append("Key = ").append(entry.getKey()).append("  | Value = ").append(entry.getValue()).append("\n");
        }
        return itemsStr.toString();
    }
}