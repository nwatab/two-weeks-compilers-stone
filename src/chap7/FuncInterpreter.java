package chap7;
import stone.FuncParser;
import stone.ParseException;
import chap6.BasicInterpreter;
import stone.*;
import stone.ast.ASTree;
import stone.ast.NullStmnt;
import chap6.BasicEvaluator;
import java.io.FileReader;
import java.io.IOException;

public class FuncInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException, IOException {
        if (args.length < 1) {
            System.err.println("Usage: java FuncInterpreter <source file>");
            System.exit(1);
        }
        String path = args[0];
        Lexer lexer = new Lexer(new FileReader(path));
        run(new FuncParser(), new NestedEnv(), lexer);
    }
}
