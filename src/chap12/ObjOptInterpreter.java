package chap12;
import stone.ClassParser;
import stone.ParseException;
import stone.Lexer;
import java.io.FileReader;
import java.io.FileNotFoundException;
import chap11.EnvOptInterpreter;
import chap11.ResizableArrayEnv;
import chap8.Natives;

public class ObjOptInterpreter extends EnvOptInterpreter {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        if (args.length < 1) {
            System.err.println("Usage: java NativeInterpreter <source file>");
            System.exit(1);
        }
        String path = args[0];
        Lexer lexer = new Lexer(new FileReader(path));
        run(new ClassParser(), new Natives().environment(new ResizableArrayEnv()), lexer);
    }
}