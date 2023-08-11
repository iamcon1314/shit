package src.ast.stmtnode;

import src.ast.*;
import src.ast.ExprNode;
import src.ast.StmtNode;
import src.util.*;

import java.util.ArrayList;

//package ast;
//
//import util.context.Position;
//
//public class IfStmtNode extends StmtNode {
//    public ExprNode condition;
//    public StmtNode thenStmt, elseStmt;
//
//    public IfStmtNode(ExprNode condition, StmtNode thenStmt, StmtNode elseStmt, Position pos) {
//        super(pos);
//        this.condition = condition;
//        this.thenStmt = thenStmt;
//        this.elseStmt = elseStmt;
//    }
//
//    @Override
//    public void accept(ASTVisitor visitor) {
//        visitor.visit(this);
//    }
//}
public class IfStmtNode extends StmtNode {
  public ExprNode condition;
//  public StmtNode thenStmt, elseStmt;
  public ArrayList<StmtNode> thenStmts = new ArrayList<StmtNode>();
  public ArrayList<StmtNode> elseStmts = new ArrayList<StmtNode>();

  public IfStmtNode(Position pos, ExprNode condition) {
    super(pos);
    this.condition = condition;
//        this.thenStmt = thenStmt;
//        this.elseStmt = elseStmt;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
};