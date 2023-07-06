package compiler;

import gen.CListener;
import gen.CParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class ProgramPrinter2 implements CListener {
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";
    private final SymbolTable Root = new SymbolTable(null, "Program", 1);
    private int nesting = 0;
    private String currentReturnType = null;
    private SymbolTable Current = Root;

    @Override
    public void enterPrimaryExpression(CParser.PrimaryExpressionContext ctx) {

    }

    @Override
    public void exitPrimaryExpression(CParser.PrimaryExpressionContext ctx) {

    }


    @Override
    public void enterPostfixExpression(CParser.PostfixExpressionContext ctx) {

    }

    @Override
    public void exitPostfixExpression(CParser.PostfixExpressionContext ctx) {

    }

    @Override
    public void enterArgumentExpressionList(CParser.ArgumentExpressionListContext ctx) {

    }

    @Override
    public void exitArgumentExpressionList(CParser.ArgumentExpressionListContext ctx) {

    }

    @Override
    public void enterUnaryExpression(CParser.UnaryExpressionContext ctx) {
        //TODO should be tested with more examples
        if (ctx.postfixExpression().primaryExpression().Identifier() != null && ctx.postfixExpression().LeftParen().size() == 0) {
            String name = ctx.postfixExpression().primaryExpression().Identifier().getText();
            if (!isFieldDefined(name, Current)) {
                System.out.println(
                        ANSI_RED +
                                "Error106 : in line [" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "], " +
                                "can not find Variable " + name +
                                ANSI_RESET
                );
            }
            // Function call
        } else if (ctx.postfixExpression().primaryExpression().Identifier() != null && ctx.postfixExpression().LeftParen().size() != 0) {
            String name = ctx.postfixExpression().primaryExpression().Identifier().getText();
            var argsList=ctx.postfixExpression().argumentExpressionList(0).assignmentExpression();
            if (argsList.size() != Root.get("Method_"+name).Parameter_list.size()) {
                System.out.println(
                        ANSI_RED +
                                "Error220 : in line [" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "], " +
                                "Mismatch arguments in " + name +
                                ANSI_RESET
                );
                return;
            }
            for (int i = 0; i < argsList.size(); i++) {
                // TODO
            }
        }

    }

    @Override
    public void exitUnaryExpression(CParser.UnaryExpressionContext ctx) {

    }

    @Override
    public void enterUnaryOperator(CParser.UnaryOperatorContext ctx) {

    }

    @Override
    public void exitUnaryOperator(CParser.UnaryOperatorContext ctx) {

    }

    @Override
    public void enterCastExpression(CParser.CastExpressionContext ctx) {

    }

    @Override
    public void exitCastExpression(CParser.CastExpressionContext ctx) {

    }

    @Override
    public void enterMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {

    }

    @Override
    public void exitMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {

    }

    @Override
    public void enterAdditiveExpression(CParser.AdditiveExpressionContext ctx) {

    }

    @Override
    public void exitAdditiveExpression(CParser.AdditiveExpressionContext ctx) {

    }

    @Override
    public void enterShiftExpression(CParser.ShiftExpressionContext ctx) {

    }

    @Override
    public void exitShiftExpression(CParser.ShiftExpressionContext ctx) {

    }

    @Override
    public void enterRelationalExpression(CParser.RelationalExpressionContext ctx) {

    }

    @Override
    public void exitRelationalExpression(CParser.RelationalExpressionContext ctx) {

    }

    @Override
    public void enterEqualityExpression(CParser.EqualityExpressionContext ctx) {

    }

    @Override
    public void exitEqualityExpression(CParser.EqualityExpressionContext ctx) {

    }

    @Override
    public void enterAndExpression(CParser.AndExpressionContext ctx) {

    }

    @Override
    public void exitAndExpression(CParser.AndExpressionContext ctx) {

    }

    @Override
    public void enterExclusiveOrExpression(CParser.ExclusiveOrExpressionContext ctx) {

    }

    @Override
    public void exitExclusiveOrExpression(CParser.ExclusiveOrExpressionContext ctx) {

    }

    @Override
    public void enterInclusiveOrExpression(CParser.InclusiveOrExpressionContext ctx) {

    }

    @Override
    public void exitInclusiveOrExpression(CParser.InclusiveOrExpressionContext ctx) {

    }

    @Override
    public void enterLogicalAndExpression(CParser.LogicalAndExpressionContext ctx) {

    }

    @Override
    public void exitLogicalAndExpression(CParser.LogicalAndExpressionContext ctx) {

    }

    @Override
    public void enterLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {

    }

    @Override
    public void exitLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {

    }

    @Override
    public void enterConditionalExpression(CParser.ConditionalExpressionContext ctx) {

    }

    @Override
    public void exitConditionalExpression(CParser.ConditionalExpressionContext ctx) {

    }

    @Override
    public void enterAssignmentExpression(CParser.AssignmentExpressionContext ctx) {
        if (ctx.assignmentExpression() != null && ctx.assignmentOperator().getText().equals("=") ){
            var var1 =ctx.unaryExpression();
            var var2 =ctx.assignmentExpression();
            var var1_symbol = Current.get("Field_" + var1.getText());
            var var2_symbol = Current.get("Field_" + var2.getText());

            if((var1_symbol != null) && (var2_symbol != null) && !var1_symbol.type.equals(var2_symbol.type)){
                System.out.println(
                        ANSI_RED +
                                "Error230 : in line [" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "], " +
                                "Incompatible types : [" +  var1_symbol.type + "] can not be converted to ["+ var2_symbol.type + "]" +
                                ANSI_RESET
                );
            }
        }
    }

    @Override
    public void exitAssignmentExpression(CParser.AssignmentExpressionContext ctx) {

    }

    @Override
    public void enterAssignmentOperator(CParser.AssignmentOperatorContext ctx) {

    }

    @Override
    public void exitAssignmentOperator(CParser.AssignmentOperatorContext ctx) {

    }

    @Override
    public void enterExpression(CParser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(CParser.ExpressionContext ctx) {

    }

    @Override
    public void enterConstantExpression(CParser.ConstantExpressionContext ctx) {

    }

    @Override
    public void exitConstantExpression(CParser.ConstantExpressionContext ctx) {

    }

    @Override
    public void enterDeclaration(CParser.DeclarationContext ctx) {
        String type = ctx.declarationSpecifiers().declarationSpecifier().get(0).typeSpecifier().getText();
        String name;
        if (ctx.declarationSpecifiers().declarationSpecifier().size() == 1) //array
        {
            name = ctx.initDeclaratorList().initDeclarator().get(0).declarator().directDeclarator().Identifier().getText();
            int length = Integer.parseInt(ctx.initDeclaratorList().initDeclarator().get(0).declarator().directDeclarator().Constant().get(0).getText());
            type = type + " array[" + length + "]";
        } else {
            name = ctx.declarationSpecifiers().declarationSpecifier().get(1).typeSpecifier().typedefName().getText();
        }
        Current.put("Field_" + name, new Item("Method Field", name, type), ctx);
    }

    @Override
    public void exitDeclaration(CParser.DeclarationContext ctx) {

    }

    @Override
    public void enterDeclarationSpecifiers(CParser.DeclarationSpecifiersContext ctx) {

    }

    @Override
    public void exitDeclarationSpecifiers(CParser.DeclarationSpecifiersContext ctx) {

    }

    @Override
    public void enterDeclarationSpecifier(CParser.DeclarationSpecifierContext ctx) {

    }

    @Override
    public void exitDeclarationSpecifier(CParser.DeclarationSpecifierContext ctx) {

    }

    @Override
    public void enterInitDeclaratorList(CParser.InitDeclaratorListContext ctx) {

    }

    @Override
    public void exitInitDeclaratorList(CParser.InitDeclaratorListContext ctx) {

    }

    @Override
    public void enterInitDeclarator(CParser.InitDeclaratorContext ctx) {

    }

    @Override
    public void exitInitDeclarator(CParser.InitDeclaratorContext ctx) {

    }

    @Override
    public void enterStorageClassSpecifier(CParser.StorageClassSpecifierContext ctx) {

    }

    @Override
    public void exitStorageClassSpecifier(CParser.StorageClassSpecifierContext ctx) {

    }

    @Override
    public void enterTypeSpecifier(CParser.TypeSpecifierContext ctx) {

    }

    @Override
    public void exitTypeSpecifier(CParser.TypeSpecifierContext ctx) {

    }

    @Override
    public void enterStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {

    }

    @Override
    public void exitStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {

    }

    @Override
    public void enterStructOrUnion(CParser.StructOrUnionContext ctx) {

    }

    @Override
    public void exitStructOrUnion(CParser.StructOrUnionContext ctx) {

    }

    @Override
    public void enterStructDeclarationList(CParser.StructDeclarationListContext ctx) {

    }

    @Override
    public void exitStructDeclarationList(CParser.StructDeclarationListContext ctx) {

    }

    @Override
    public void enterStructDeclaration(CParser.StructDeclarationContext ctx) {

    }

    @Override
    public void exitStructDeclaration(CParser.StructDeclarationContext ctx) {

    }

    @Override
    public void enterSpecifierQualifierList(CParser.SpecifierQualifierListContext ctx) {

    }

    @Override
    public void exitSpecifierQualifierList(CParser.SpecifierQualifierListContext ctx) {

    }

    @Override
    public void enterStructDeclaratorList(CParser.StructDeclaratorListContext ctx) {

    }

    @Override
    public void exitStructDeclaratorList(CParser.StructDeclaratorListContext ctx) {

    }

    @Override
    public void enterStructDeclarator(CParser.StructDeclaratorContext ctx) {

    }

    @Override
    public void exitStructDeclarator(CParser.StructDeclaratorContext ctx) {

    }

    @Override
    public void enterEnumSpecifier(CParser.EnumSpecifierContext ctx) {

    }

    @Override
    public void exitEnumSpecifier(CParser.EnumSpecifierContext ctx) {

    }

    @Override
    public void enterEnumeratorList(CParser.EnumeratorListContext ctx) {

    }

    @Override
    public void exitEnumeratorList(CParser.EnumeratorListContext ctx) {

    }

    @Override
    public void enterEnumerator(CParser.EnumeratorContext ctx) {

    }

    @Override
    public void exitEnumerator(CParser.EnumeratorContext ctx) {

    }

    @Override
    public void enterEnumerationConstant(CParser.EnumerationConstantContext ctx) {

    }

    @Override
    public void exitEnumerationConstant(CParser.EnumerationConstantContext ctx) {

    }

    @Override
    public void enterTypeQualifier(CParser.TypeQualifierContext ctx) {

    }

    @Override
    public void exitTypeQualifier(CParser.TypeQualifierContext ctx) {

    }

    @Override
    public void enterDeclarator(CParser.DeclaratorContext ctx) {

    }

    @Override
    public void exitDeclarator(CParser.DeclaratorContext ctx) {

    }

    @Override
    public void enterDirectDeclarator(CParser.DirectDeclaratorContext ctx) {

    }

    @Override
    public void exitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {

    }

    @Override
    public void enterNestedParenthesesBlock(CParser.NestedParenthesesBlockContext ctx) {

    }

    @Override
    public void exitNestedParenthesesBlock(CParser.NestedParenthesesBlockContext ctx) {

    }

    @Override
    public void enterPointer(CParser.PointerContext ctx) {

    }

    @Override
    public void exitPointer(CParser.PointerContext ctx) {

    }

    @Override
    public void enterTypeQualifierList(CParser.TypeQualifierListContext ctx) {

    }

    @Override
    public void exitTypeQualifierList(CParser.TypeQualifierListContext ctx) {

    }

    @Override
    public void enterParameterTypeList(CParser.ParameterTypeListContext ctx) {

    }

    @Override
    public void exitParameterTypeList(CParser.ParameterTypeListContext ctx) {

    }

    @Override
    public void enterParameterList(CParser.ParameterListContext ctx) {

    }

    @Override
    public void exitParameterList(CParser.ParameterListContext ctx) {

    }

    @Override
    public void enterParameterDeclaration(CParser.ParameterDeclarationContext ctx) {

    }

    @Override
    public void exitParameterDeclaration(CParser.ParameterDeclarationContext ctx) {

    }

    @Override
    public void enterIdentifierList(CParser.IdentifierListContext ctx) {

    }

    @Override
    public void exitIdentifierList(CParser.IdentifierListContext ctx) {

    }

    @Override
    public void enterTypeName(CParser.TypeNameContext ctx) {

    }

    @Override
    public void exitTypeName(CParser.TypeNameContext ctx) {

    }

    @Override
    public void enterTypedefName(CParser.TypedefNameContext ctx) {

    }

    @Override
    public void exitTypedefName(CParser.TypedefNameContext ctx) {

    }

    @Override
    public void enterInitializer(CParser.InitializerContext ctx) {

    }

    @Override
    public void exitInitializer(CParser.InitializerContext ctx) {

    }

    @Override
    public void enterInitializerList(CParser.InitializerListContext ctx) {

    }

    @Override
    public void exitInitializerList(CParser.InitializerListContext ctx) {

    }

    @Override
    public void enterDesignation(CParser.DesignationContext ctx) {

    }

    @Override
    public void exitDesignation(CParser.DesignationContext ctx) {

    }

    @Override
    public void enterDesignatorList(CParser.DesignatorListContext ctx) {

    }

    @Override
    public void exitDesignatorList(CParser.DesignatorListContext ctx) {

    }

    @Override
    public void enterDesignator(CParser.DesignatorContext ctx) {

    }

    @Override
    public void exitDesignator(CParser.DesignatorContext ctx) {

    }

    @Override
    public void enterStatement(CParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(CParser.StatementContext ctx) {

    }

    @Override
    public void enterLabeledStatement(CParser.LabeledStatementContext ctx) {

    }

    @Override
    public void exitLabeledStatement(CParser.LabeledStatementContext ctx) {

    }

    @Override
    public void enterCompoundStatement(CParser.CompoundStatementContext ctx) {

    }

    @Override
    public void exitCompoundStatement(CParser.CompoundStatementContext ctx) {

    }

    @Override
    public void enterBlockItemList(CParser.BlockItemListContext ctx) {

    }

    @Override
    public void exitBlockItemList(CParser.BlockItemListContext ctx) {

    }

    @Override
    public void enterBlockItem(CParser.BlockItemContext ctx) {

    }

    @Override
    public void exitBlockItem(CParser.BlockItemContext ctx) {

    }

    @Override
    public void enterExpressionStatement(CParser.ExpressionStatementContext ctx) {

    }

    @Override
    public void exitExpressionStatement(CParser.ExpressionStatementContext ctx) {

    }

    @Override
    public void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
        //if
        nesting++;
        if (nesting > 1) {
            Current = new SymbolTable(Current, "nested_if", ctx.start.getLine());
        }
    }

    @Override
    public void exitSelectionStatement(CParser.SelectionStatementContext ctx) {
        nesting--;
        if (nesting > 0) {
            Current = Current.Parent;
        }
    }

    @Override
    public void enterIterationStatement(CParser.IterationStatementContext ctx) {
        nesting++;
        if (nesting > 1) {
            Current = new SymbolTable(Current, "nested_for", ctx.start.getLine());
        }
    }

    @Override
    public void exitIterationStatement(CParser.IterationStatementContext ctx) {
        nesting--;
        if (nesting > 0) {
            Current = Current.Parent;
        }
    }

    @Override
    public void enterForCondition(CParser.ForConditionContext ctx) {

    }

    @Override
    public void exitForCondition(CParser.ForConditionContext ctx) {

    }

    @Override
    public void enterForDeclaration(CParser.ForDeclarationContext ctx) {
        String name = ctx.initDeclaratorList().initDeclarator().get(0).declarator().directDeclarator().Identifier().getText();
        String type = ctx.declarationSpecifiers().getText();
        Current.put("Field_" + name, new Item("Method Field", name, type), ctx);
    }

    @Override
    public void exitForDeclaration(CParser.ForDeclarationContext ctx) {

    }

    @Override
    public void enterForExpression(CParser.ForExpressionContext ctx) {

    }

    @Override
    public void exitForExpression(CParser.ForExpressionContext ctx) {

    }

    @Override
    public void enterJumpStatement(CParser.JumpStatementContext ctx) {
        //Does not support expression TODO
        //Does not support constants
        //need more testing
        var r = ctx.expression().assignmentExpression(0).conditionalExpression().logicalOrExpression().
                logicalAndExpression(0).inclusiveOrExpression(0).exclusiveOrExpression(0).andExpression(0).
                equalityExpression(0).relationalExpression(0).shiftExpression(0).additiveExpression(0).
                multiplicativeExpression(0).castExpression(0).unaryExpression().postfixExpression().
                primaryExpression();
        if (r.Constant() == null) {
            var rName = r.Identifier().getText();
            var rItem = Current.get("Field_" + rName);
            if (rItem != null) {
                var rType = rItem.type;
                if (!rType.equals(currentReturnType)) {
                    System.out.println(
                            ANSI_RED +
                                    "Error210 : in line [" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "], " +
                                    "return type of this method must be " + currentReturnType +
                                    ANSI_RESET
                    );
                }
            }

        }
    }

    @Override
    public void exitJumpStatement(CParser.JumpStatementContext ctx) {

    }

    @Override
    public void enterExternalDeclaration(CParser.ExternalDeclarationContext ctx) {
    }

    @Override
    public void exitExternalDeclaration(CParser.ExternalDeclarationContext ctx) {
        System.out.println();
        printSymbolTable(Root);
    }

    @Override
    public void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        String name = ctx.declarator().directDeclarator().directDeclarator().getText();
        String return_type = ctx.typeSpecifier().getText();
        ArrayList<Item> para_list = Functions.parameter_list_to_str(ctx.declarator().directDeclarator().parameterTypeList());
        Item method_i = new Item("Method", name, return_type, para_list);
        Current.put("Method_" + name, method_i, ctx);
        currentReturnType = return_type;

        Current = new SymbolTable(Current, name, ctx.start.getLine());
        if (para_list != null) {
            for (var p :
                    para_list) {
                p.kind = "Method Param Field";
                Current.put("Field_" + p.name, p, ctx);
            }
        }
    }

    @Override
    public void exitFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        Current = Current.Parent;
    }

    @Override
    public void enterDeclarationList(CParser.DeclarationListContext ctx) {

    }

    @Override
    public void exitDeclarationList(CParser.DeclarationListContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

    public void printSymbolTable(SymbolTable s) {
        System.out.println(s);
        if (s.getChildren().size() != 0)
            for (var st :
                    s.getChildren()) {
                printSymbolTable(st);
            }
    }

    //**************probably should be moved to somewhere else TODO
    private boolean isFieldDefined(String name, SymbolTable scoop) {
        if (scoop == null) {
            return false;
        }
        if (scoop.containsKey("Field_" + name)) {
            return true;
        }
        return isFieldDefined(name, scoop.Parent);
    }
}
