package chap14;
import java.io.FileNotFoundException;
import java.io.FileReader;
import stone.BasicParser;
import stone.TypedParser;
import stone.Lexer;
import stone.Token;
import stone.ParseException;
import stone.ast.ASTree;
import stone.ast.NullStmnt;
import chap11.EnvOptimizer;
import chap11.ResizableArrayEnv;
import chap6.BasicEvaluator;
import chap6.Environment;

public class TypedInterpreter {
    public static void main(String[] args) throws ParseException, FileNotFoundException, TypeException {
        TypeEnv te = new TypeEnv();
        if (args.length < 1) {
            System.err.println("Usage: java TypedInterpreter <source file>");
            System.exit(1);
        }
        String path = args[0];
        Lexer lexer = new Lexer(new FileReader(path));
        run(
            new TypedParser(),
            new TypedNatives(te).environment(new ResizableArrayEnv()),
            te,
            lexer
        );
    }
    public static void run(BasicParser bp, Environment env, TypeEnv typeEnv, Lexer lexer) throws ParseException, TypeException {
        while (lexer.peek(0) != Token.EOF) {
            ASTree tree = bp.parse(lexer);
            if (!(tree instanceof NullStmnt)) {
                ((EnvOptimizer.ASTreeOptEx) tree).lookup(
                    ((EnvOptimizer.EnvEx2) env).symbols()
                );
                TypeInfo type = ((TypeChecker.ASTreeTypeEx) tree).typeCheck(typeEnv);
                Object r = ((BasicEvaluator.ASTreeEx) tree).eval(env);
                System.out.println("=> " + r + " : " + type);
            }
        }
    }
}
