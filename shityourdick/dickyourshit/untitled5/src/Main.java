package src;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import src.utils.*;
import src.ast.*;

import java.io.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import src.frontend.*;

import src.grammar.*;
import  src.ast.*;
import src.frontend.SemanticChecker;
import src.frontend.SymbolCollector;
import  src.utils.*;
//import  src.utils.Error;
//import  src.Semantic.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.io.FileOutputStream;
import java.io.PrintStream;

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

          System.out.print("fail");
          System.out.print(shit.toString());
//        System.out.println(s + i + ".mx" + "fuck you");
        }
        System.out.print("suc");
  }
}