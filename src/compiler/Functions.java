package compiler;

import gen.CParser;
import jdk.jshell.Snippet;

import java.util.ArrayList;

public class Functions {
    static ArrayList<Item> parameter_list_to_str(CParser.ParameterTypeListContext ptl) {
        if (ptl == null) {
            return null;
        }
        var pl = ptl.parameterList();
        ArrayList<Item> result = new ArrayList<>();
        for(var pd : pl.parameterDeclaration()){
            String type = pd.declarationSpecifiers().declarationSpecifier().get(0).typeSpecifier().getText()+
             (pd.declarator().directDeclarator().LeftBracket().size() != 0 ?  " array" +
                            pd.declarator().directDeclarator().Constant() + " " : " ") ;
                     String name =       pd.declarator().directDeclarator().Identifier().getText();
            result.add(new Item("",name, type));
        }
        return result;
    }
}
