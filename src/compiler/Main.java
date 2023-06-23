package compiler;

import gen.CLexer;
import gen.CParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        CharStream stream = CharStreams.fromFileName("./sample/test.c");
        CLexer lexer = new CLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        CParser parser = new CParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.externalDeclaration();
        ParseTreeWalker walker = new ParseTreeWalker();
        ProgramPrinter2 listener = new ProgramPrinter2();
//        ProgramPrinter listener = new ProgramPrinter();
        walker.walk(listener, tree);
        listener.printSymbolTable(null);
        listener.printRedefinedErrors(null);

    }
}