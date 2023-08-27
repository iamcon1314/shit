package src;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import src.myerror.MxErrorListener;

import src.Scope.GlobalScope;
import src.ast.othernode.ProgramNode;
import src.grammar.*;

import java.io.*;

import src.frontend.*;

public class Main {
      public static void main(String[] args) throws Exception {
        try {
          CharStream input = CharStreams.fromStream(new FileInputStream("try.in"));
          System.out.println("try.in");

//    CharStream input = CharStreams.fromStream(System.in);
          MxLexer lexer = new MxLexer(input);
          lexer.removeErrorListeners();
          lexer.addErrorListener(new MxErrorListener());
          CommonTokenStream tokens = new CommonTokenStream(lexer);
          MxParser parser = new MxParser(tokens);
          parser.removeErrorListeners();
          parser.addErrorListener(new MxErrorListener());
          ParseTree tree = parser.program();
          ASTBuilder astBuilder = new ASTBuilder();
          ProgramNode ast = (ProgramNode) astBuilder.visit(tree);
          GlobalScope globalScope = new GlobalScope();
          new SymbolCollector(globalScope).visit(ast);
          new SemanticChecker(globalScope).visit(ast);
        }
        catch (Throwable shit) {

          System.out.print("fail\n");
          System.out.print(shit);
          return;
//        System.out.println(s + i + ".mx" + "fuck you");
        }
        System.out.print("suc");
  }
}