package src.ast.stmt;

import src.ast.expr.*;
import src.ast.*;
import src.utils.*;

public class ExprStmtNode extends StmtNode {
  public ExprNode expr;

  public ExprStmtNode(Position pos, ExprNode expr) {
    super(pos);
    this.expr = expr;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}