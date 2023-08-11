package src.frontend;
import src.ast.othernode.*;
import src.grammar.MxParser;
import src.grammar.MxParserBaseVisitor;
import src.myerror.BaseError;
import src.util.*;
//import grammar;
import src.ast.*;
import src.ast.stmtnode.*;
import src.ast.exprnode.*;


public class ASTBuilder extends MxParserBaseVisitor<Node> {
  @Override
  public Node visitProgram(MxParser.ProgramContext ctx) {
    ProgramNode program = new ProgramNode(new Position(ctx));
    for (int i=0;i<ctx.children.size();i++){
      var def = ctx.children.get(i);
      if (def instanceof MxParser.ClassDefContext) {
        program.defList.add((ClassDefNode) visit(def));
      } else if (def instanceof MxParser.FuncDefContext) {
        program.defList.add((FuncDefNode) visit(def));
      } else if (def instanceof MxParser.VarDefContext) {
        program.defList.add((VarDefNode) visit(def));
      }
    }
    return program;
  }
  @Override
  public Node visitFuncDef(MxParser.FuncDefContext ctx) {
    FuncDefNode funcDef = new FuncDefNode(new Position(ctx), ctx.Identifier().getText());
    funcDef.returnType = (TypeNode) visit(ctx.returnType());
    if(ctx.parameterList()==null){
      funcDef.stmtnodes = ((SuiteNode) visit(ctx.suite())).stmtnodes;
      return funcDef;
    }
    else{

      funcDef.params = (ParameterListNode) visit(ctx.parameterList());

      funcDef.stmtnodes = ((SuiteNode) visit(ctx.suite())).stmtnodes;
      return funcDef;
    }
  }

  @Override
  public Node visitReturnType(MxParser.ReturnTypeContext ctx) {
    //this is wrong !
//    return new TypeNode(new Position(ctx), ctx.getText());
    if (ctx.Void() == null) {
      return (TypeNode) visit(ctx.type());
    }
    return new TypeNode(new Position(ctx),"void");
  }

  @Override
  public Node visitParameterList(MxParser.ParameterListContext ctx) {
    ParameterListNode parameterList = new ParameterListNode(new Position(ctx));
    for (int i = 0; i < ctx.type().size(); ++i)
      parameterList.units.add(new VarDefUnitNode(
          new Position(ctx.type(i)),
          (TypeNode) visit(ctx.type(i)),
          ctx.Identifier(i).getText(),
          null));
    return parameterList;
  }

//  @Override
//  public Node visitSuite(MxParser.SuiteContext ctx) {
//    SuiteNode suite = new SuiteNode(new Position(ctx));
//    ctx.statement().forEach(stmt -> suite.stmts.add((StmtNode) visit(stmt)));
//    return suite;
//  }

//  @Override
//  public Node visitSuite(MxParser.SuiteContext ctx){
//    SuiteNode mysuitenode = new SuiteNode(new Position(ctx));
//    ctx.statement().forEach(s);
//  }
  @Override
  public Node visitSuite(MxParser.SuiteContext ctx) {
    SuiteNode suite = new SuiteNode(new Position(ctx));
    for (int i = 0; i < ctx.statement().size(); i++) {
      MxParser.StatementContext stmt = ctx.statement(i);
      suite.stmtnodes.add((StmtNode) visit(stmt));
    }
    return suite;
  }



//  public Node visitClassDef(MxParser.ClassDefContext ctx) {
//    ClassDefNode classDef = new ClassDefNode(new Position(ctx), ctx.Identifier().getText());
//    boolean ifconstructed = false;
//    for (var def : ctx.children)
//      if (def instanceof FuncDefContext) {
//        classDef.funcDefList.add((FuncDefNode) visit(def));
//      } else if (def instanceof VarDefContext) {
//        classDef.varDefList.add((VarDefNode) visit(def));
//      } else if (def instanceof ClassBuildContext) {
//        if (ifconstructed==true) {
//          throw new BaseError(new Position(ctx), "Multiple constructors");
//        }
//        else {
//          ifconstructed = true;
//          classDef.classBuild = (ClassBuildNode) visit(def);
//        }
//      }
//    return classDef;
//  }

  @Override
  public Node visitClassDef(MxParser.ClassDefContext ctx) {
    ClassDefNode classDef = new ClassDefNode(new Position(ctx), ctx.Identifier().getText());
    boolean ifbuild = false;
    for (int i = 0; i < ctx.children.size(); i++) {
      var def = ctx.children.get(i);
      if (def instanceof MxParser.FuncDefContext) {
        classDef.funcDefList.add((FuncDefNode) visit(def));
      } else if (def instanceof MxParser.VarDefContext) {
        classDef.varDefList.add((VarDefNode) visit(def));
      } else if (def instanceof MxParser.ClassBuildContext) {
        if (ifbuild == true) {
          throw new BaseError(new Position(ctx), "Multiple Constructors FUCKING YOU!!!!!!!!!");
        } else {
          ifbuild = true;
          classDef.classBuild = (ClassBuildNode) visit(def);
        }
      }
    }
    return classDef;
  }

  @Override
  public Node visitClassBuild(MxParser.ClassBuildContext ctx) {
    ClassBuildNode classBuild = new ClassBuildNode(
        new Position(ctx),
        ctx.Identifier().getText(),
        (SuiteNode) visit(ctx.suite()));
    return classBuild;
  }

//  @Override
//  public Node visitVarDef(MxParser.VarDefContext ctx) {
//    VarDefNode varDef = new VarDefNode(new Position(ctx));
//    TypeNode type = (TypeNode) visit(ctx.type());
//    for (var unit : ctx.varDefUnit())
//      varDef.units.add((new VarDefUnitNode(
//          new Position(unit),
//          type,
//          unit.Identifier().getText(),
//          unit.expr() == null ? null : (ExprNode) visit(unit.expr()))));
//    return varDef;
//  }
  @Override
  public Node visitVarDef(MxParser.VarDefContext ctx) {
    VarDefNode varDef = new VarDefNode(new Position(ctx));
    TypeNode type = (TypeNode) visit(ctx.type());
    for (int i = 0; i < ctx.varDefUnit().size(); i++) {
      var unit = ctx.varDefUnit(i);
      varDef.units.add(new VarDefUnitNode(
              new Position(unit),
              type,
              unit.Identifier().getText(),
              unit.expression() == null ? null : (ExprNode) visit(unit.expression())));
    }
    return varDef;
  }

  @Override
  public Node visitType(MxParser.TypeContext ctx) {
    return new TypeNode(new Position(ctx), ctx.typeName().getText(), ctx.LeftmidParen().size());
  }

  @Override
  public Node visitStatement(MxParser.StatementContext ctx) {
    if (ctx.suite() != null)
      return visit(ctx.suite());
    else if (ctx.varDef() != null)
      return visit(ctx.varDef());
    else if (ctx.expressionStmt() != null)
      return visit(ctx.expressionStmt());
    else if (ctx.ifStmt() != null)
      return visit(ctx.ifStmt());
    else if (ctx.forStmt() != null)
      return visit(ctx.forStmt());
    else if (ctx.whileStmt() != null)
      return visit(ctx.whileStmt());
    else if (ctx.returnStmt() != null)
      return visit(ctx.returnStmt());
    else if (ctx.breakStmt() != null)
      return visit(ctx.breakStmt());
    else if (ctx.continueStmt() != null)
      return visit(ctx.continueStmt());
    else
      return visitChildren(ctx); // no need to change
  }
//    @Override
//    public ASTNode visitIfStmt(YxParser.IfStmtContext ctx) {
//        StmtNode thenStmt = (StmtNode) visit(ctx.trueStmt), elseStmt = null;
//        ExprNode condition = (ExprNode) visit(ctx.expression());
//        if (ctx.falseStmt != null) elseStmt = (StmtNode) visit(ctx.falseStmt);
//        return new IfStmtNode(condition, thenStmt, elseStmt, new Position(ctx));
//    }
  @Override
  public Node visitIfStmt(MxParser.IfStmtContext ctx) {
//    StmtNode thenStmt = (StmtNode) visit(ctx.trueStmt), elseStmt = null;
    ExprNode condition = (ExprNode) visit(ctx.expression());
//    IfStmtNode ifStmt=new IfStmtNode(new Position(ctx),(ExprNode) visit(ctx.expression()))
    IfStmtNode ifStmt = new IfStmtNode(new Position(ctx), condition);
    if (ctx.statement(0).suite() != null) {
      ifStmt.thenStmts = ((SuiteNode) visit(ctx.statement(0).suite())).stmtnodes;
    } else {
      ifStmt.thenStmts.add((StmtNode) visit(ctx.statement(0)));
    }
    if (ctx.Else() == null) {
      return ifStmt;
    }
    if (ctx.statement(1).suite() != null) {
      ifStmt.elseStmts = ((SuiteNode) visit(ctx.statement(1).suite())).stmtnodes;
    }
    else {
      ifStmt.elseStmts.add((StmtNode) visit(ctx.statement(1)));
    }
    return ifStmt;
  }

  @Override
  public Node visitWhileStmt(MxParser.WhileStmtContext ctx) {
    ExprNode condition = (ExprNode) visit(ctx.expression());
    WhileStmtNode whileStmt = new WhileStmtNode(new Position(ctx), condition);
    if (ctx.statement().suite() != null) {
      whileStmt.stmts = ((SuiteNode) visit(ctx.statement().suite())).stmtnodes;
    }
    else {
      whileStmt.stmts.add((StmtNode) visit(ctx.statement()));
    }
    return whileStmt;
  }

  @Override
  public Node visitForStmt(MxParser.ForStmtContext ctx) {
    ForStmtNode forStmt = new ForStmtNode(new Position(ctx));

    if (ctx.forInit().varDef() != null) {
      forStmt.varDef = (VarDefNode) visit(ctx.forInit().varDef());
    }
    else {
      forStmt.init = ((ExprStmtNode) visit(ctx.forInit().expressionStmt())).expr;
    }



    forStmt.condition = ((ExprStmtNode) visit(ctx.expressionStmt())).expr;



    if (ctx.expression() != null) {
      forStmt.step = (ExprNode) visit(ctx.expression());
    }

    //for后面的{}

    if (ctx.statement().suite() != null) {
      forStmt.stmtnodes = ((SuiteNode) visit(ctx.statement().suite())).stmtnodes;
    }
    else {
      forStmt.stmtnodes.add((StmtNode) visit(ctx.statement()));
    }
    return forStmt;
  }

  @Override
  public Node visitBreakStmt(MxParser.BreakStmtContext ctx) {
    return new BreakNode(new Position(ctx));
  }

  @Override
  public Node visitContinueStmt(MxParser.ContinueStmtContext ctx) {
    return new ContinueNode(new Position(ctx));
  }

  @Override
  public Node visitReturnStmt(MxParser.ReturnStmtContext ctx) {
    return new ReturnStmtNode(new Position(ctx),
        ctx.expression() == null ? null : (ExprNode) visit(ctx.expression()));
  }

  @Override
  public Node visitExpressionStmt(MxParser.ExpressionStmtContext ctx) {
    return new ExprStmtNode(new Position(ctx), ctx.expression() == null ? null : (ExprNode) visit(ctx.expression()));
  }

//  @Override
//  public Node visitNewExpr(MxParser.NewExprContext ctx) {
//    NewExprNode newExpr = new NewExprNode(new Position(ctx), ctx.typeName().getText());
//    newExpr.dim = ctx.newArrayUnit().size();
//    boolean isEmpty = false;
//    for (var unit : ctx.newArrayUnit()) {
//      if (unit.expr() == null)
//        isEmpty = true;
//      else if (isEmpty)
//        throw new BaseError(new Position(ctx), "Array dimension cannot be empty");
//      else
//        newExpr.sizeList.add((ExprNode) visit(unit.expr()));
//
//    }
//    return newExpr;
//  }
  @Override
  public Node visitNewExpr(MxParser.NewExprContext ctx) {
      NewExprNode newExpr = new NewExprNode(new Position(ctx), ctx.typeName().getText());
      newExpr.dimension = ctx.newArrayUnit().size();
      boolean isEmpty = false;
      for (int i = 0; i < ctx.newArrayUnit().size(); i++) {
          var unit = ctx.newArrayUnit().get(i);
          if (unit.expression() == null)
              isEmpty = true;
          else if (isEmpty) {
            throw new BaseError(new Position(ctx), "Array dimension cannot be empty");
          }
          else {
            newExpr.sizeList.add((ExprNode) visit(unit.expression()));
//            size.accept(this);
          }
      }
      return newExpr;
  }
  @Override
  public Node visitUnaryExpr(MxParser.UnaryExprContext ctx) {
    return new UnaryExprNode(new Position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expression()));
  }

  @Override
  public Node visitPreAddExpr(MxParser.PreAddExprContext ctx) {
    return new PreAddExprNode(new Position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expression()));
  }

  @Override
  public Node visitFuncExpr(MxParser.FuncExprContext ctx) {
    FuncExprNode funcExpr = new FuncExprNode(new Position(ctx), (ExprNode) visit(ctx.expression()));
    if (ctx.expressionList() != null)
      funcExpr.args = (ExprListNode) visit(ctx.expressionList());
    return funcExpr;
  }

  @Override
  public Node visitArrayExpr(MxParser.ArrayExprContext ctx) {
    return new ArrayExprNode(new Position(ctx), (ExprNode) visit(ctx.expression(0)), (ExprNode) visit(ctx.expression(1)));
  }

  @Override
  public Node visitMemberExpr(MxParser.MemberExprContext ctx) {
    return new MemberExprNode(new Position(ctx), (ExprNode) visit(ctx.expression()), ctx.Identifier().getText());
  }

  @Override
  public Node visitAtomExpr(MxParser.AtomExprContext ctx) {

    return visitChildren(ctx); // no need to change
  }

  @Override
  public Node visitBinaryExpr(MxParser.BinaryExprContext ctx) {
    return new BinaryExprNode(
        new Position(ctx),
        (ExprNode) visit(ctx.expression(0)),
        ctx.op.getText(),
        (ExprNode) visit(ctx.expression(1)));
  }
  @Override
  public Node visitTriExpr(MxParser.TriExprContext ctx) {
    return  new TriExprNode(
            new Position(ctx),
            (ExprNode) visit(ctx.expression(0)),
            (ExprNode) visit(ctx.expression(1)),
            (ExprNode) visit(ctx.expression(2))
            );


  }
  @Override
  public Node visitAssignExpr(MxParser.AssignExprContext ctx) {
    return new AssignExprNode(
        new Position(ctx),
        (ExprNode) visit(ctx.expression(0)),
        (ExprNode) visit(ctx.expression(1)));
  }

  @Override
  public Node visitParenExpr(MxParser.ParenExprContext ctx) {
    return (ExprNode) visit(ctx.expression());
  }

  @Override
  public Node visitPrimary(MxParser.PrimaryContext ctx) {
    return ctx.Identifier() == null
        ? new AtomExprNode(new Position(ctx), ctx.getText())
        : new VarExprNode(new Position(ctx), ctx.getText());
  }

  @Override
  public Node visitExpressionList(MxParser.ExpressionListContext ctx) {
    ExprListNode expressionList = new ExprListNode(new Position(ctx));
    ctx.expression().forEach(expression -> expressionList.exprnodes.add((ExprNode) visit(expression)));
    return expressionList;
  }
}