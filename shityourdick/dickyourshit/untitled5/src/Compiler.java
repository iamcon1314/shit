package src;

import src.grammar.*;
import src.utils.*;
import src.ast.*;

import java.io.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import src.frontend.*;



public class Compiler {
  public static void test(String s) throws IOException {
    try {
      CharStream input = CharStreams.fromStream(new FileInputStream(s));
      System.out.println(s);

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

        try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
            fileWriter.append("fail\n");
        }
        return;
//        System.out.println(s + i + ".mx" + "fuck you");
    }
      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("success\n");
      }
  }
  public static void main(String[] args) throws Exception {
      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/array-package/array    ------    begin\n");
      }

    for(int i=1;i<=11;i++) {
      test("test/testcases/sema/array-package/array-"+i+".mx");
    }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/basic-package/basic    ------    begin\n");
      }

    for(int i=1;i<=72;i++) {
          test("test/testcases/sema/basic-package/basic-"+i+".mx");
    }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/breakcontinue-package/breakcontinue    ------    begin\n");
      }

      for(int i=1;i<=5;i++) {
          test("test/testcases/sema/breakcontinue-package/breakcontinue-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/builtin-package/builtin    ------    begin\n");
      }

      for(int i=1;i<=4;i++) {
          test("test/testcases/sema/builtin-func-package/builtin-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/class-package/class    ------    begin\n");
      }

      for(int i=1;i<=16;i++) {
          test("test/testcases/sema/class-package/class-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/codeforces-package/codeforces    ------    begin\n");
      }

          test("test/testcases/sema/codeforces-package/1A.mx");
      test("test/testcases/sema/codeforces-package/4A.mx");
      test("test/testcases/sema/codeforces-package/69A.mx");
      test("test/testcases/sema/codeforces-package/71A.mx");
      test("test/testcases/sema/codeforces-package/122A.mx");
      test("test/testcases/sema/codeforces-package/158A.mx");
      test("test/testcases/sema/codeforces-package/231A.mx");
      test("test/testcases/sema/codeforces-package/263A.mx");
      test("test/testcases/sema/codeforces-package/399A.mx");
      test("test/testcases/sema/codeforces-package/1145A.mx");

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/expression-package/expression    ------    begin\n");
      }

      for(int i=1;i<=5;i++) {
          test("test/testcases/sema/expression-package/expression-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/function-package/function    ------    begin\n");
      }


      for(int i=1;i<=6;i++) {
          test("test/testcases/sema/function-package/function-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/if-package/if    ------    begin\n");
      }

      for(int i=1;i<=3;i++) {
          test("test/testcases/sema/if-package/if-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/loop-package/loop    ------    begin\n");
      }

      for(int i=1;i<=3;i++) {
          test("test/testcases/sema/loop-package/loop-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/misc-package/misc    ------    begin\n");
      }

      for(int i=1;i<=37;i++) {
          test("test/testcases/sema/misc-package/misc-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/scope-package/scope    ------    begin\n");
      }

      for(int i=1;i<=8;i++) {
          test("test/testcases/sema/scope-package/scope-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/string-package/string    ------    begin\n");
      }

      for(int i=1;i<=1;i++) {
          test("test/testcases/sema/string-package/string-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/symbol-package/symbol    ------    begin\n");
      }

      for(int i=1;i<=7;i++) {
          test("test/testcases/sema/symbol-package/symbol-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/ternary-package/ternary    ------    begin\n");
      }

      for(int i=1;i<=4;i++) {
          test("test/testcases/sema/ternary-package/ternary-expression-"+i+".mx");
      }

      try (FileWriter fileWriter = new FileWriter("ansshit.txt",true)) {
          fileWriter.append("test/testcases/sema/bool-package/bool    ------    begin\n");
      }

      test("test/testcases/sema/bool-compare.mx");
      test("test/testcases/sema/condition.mx");
//      test("test/testcases/sema/data.txt");
//      test("test/testcases/sema/condition.mx");
//      test("test/testcases/sema/data.txt");
  }

  private static InputStream FileInputStream(String string) {
    return null;
  }
}