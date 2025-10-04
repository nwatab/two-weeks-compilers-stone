package chap8;
import stone.ClosureParser;
import stone.ParseException;
import stone.Lexer;
import chap6.BasicInterpreter;
import chap7.NestedEnv;
import java.io.FileReader;
import java.io.IOException;

public class NativeInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException, IOException {
        if (args.length < 1) {
            System.err.println("Usage: java NativeInterpreter <source file>");
            System.exit(1);
        }
        String path = args[0];
        Lexer lexer = new Lexer(new FileReader(path));
        run(new ClosureParser(),
            new Natives().environment(new NestedEnv()), lexer);
    }
}