package chap9;
import java.io.FileReader;
import java.io.IOException;
import stone.ClassParser;
import stone.Lexer;
import stone.ParseException;
import chap6.BasicInterpreter;
import chap7.NestedEnv;
import chap8.Natives;

public class ClassInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException, IOException {
    if (args.length < 1) {
        System.err.println("Usage: java ClassInterpreter <source file>");
        System.exit(1);
    }
    String path = args[0];
    Lexer lexer = new Lexer(new FileReader(path));
    run(new ClassParser(),
        new Natives().environment(new NestedEnv()), lexer);
    }
}