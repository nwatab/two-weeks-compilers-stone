package chap13;
import stone.FuncParser;
import stone.ParseException;
import stone.Lexer;
import chap11.EnvOptInterpreter;
import chap8.Natives;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class VmInterpreter extends EnvOptInterpreter {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        if (args.length < 1) {
            System.err.println("Usage: java VmInterpreter <source file>");
            System.exit(1);
        }
        String path = args[0];
        Lexer lexer = new Lexer(new FileReader(path));
        run(new FuncParser(),
            new Natives().environment(
                new StoneVMEnv(100000, 100000, 1000)
            ),
            lexer
        );
    }
}
