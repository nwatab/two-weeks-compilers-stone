package chap7;
import stone.ClosureParser;
import stone.ParseException;
import stone.Lexer;
import chap6.BasicInterpreter;
import java.io.FileReader;
import java.io.IOException;

public class ClosureInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException, IOException {
        if (args.length < 1) {
            System.err.println("Usage: java ClosureInterpreter <source file>");
            System.exit(1);
        }
        String path = args[0];
        Lexer lexer = new Lexer(new FileReader(path));
        run(new ClosureParser(), new NestedEnv(), lexer);
    }
}