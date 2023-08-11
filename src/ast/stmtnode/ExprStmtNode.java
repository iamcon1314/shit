package src.ast.stmtnode;

import src.ast.*;
import src.ast.ExprNode;
import src.ast.StmtNode;
import src.util.*;
//package src.frontend;
//import grammar;

public class ExprStmtNode extends StmtNode {
  public ExprNode expr;
  public ExprStmtNode(Position pos, ExprNode expr) {
    super(pos);
    this.expr = expr;
  }
  public Node getexpr(){
    return expr;
  }
  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}