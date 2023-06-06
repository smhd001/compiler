package compiler;

import gen.CParser;

public class Functions {
    static String parameter_list_to_str(CParser.ParameterListContext pl) {
        StringBuilder result = new StringBuilder("[");
        var pl_it = pl.parameterDeclaration().iterator();
        while (true) {
            var pd = pl_it.next();
            result.append(pd.declarationSpecifiers().declarationSpecifier().get(0).typeSpecifier().getText());
            result.append(" ");
            result.append(pd.declarator().directDeclarator().Identifier().getText());
            if (!pl_it.hasNext()) {
                result.append("]");
                return result.toString();
            }
            result.append(", ");
        }
    }
}
