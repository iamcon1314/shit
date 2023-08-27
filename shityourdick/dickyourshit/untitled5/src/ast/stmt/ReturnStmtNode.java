package src.ast.stmt;

import src.ast.*;
import src.ast.expr.*;
import src.utils.*;

public class ReturnStmtNode extends StmtNode {
  public ExprNode expr;

  public ReturnStmtNode(Position pos, ExprNode expr) {
    super(pos);
    this.expr = expr;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}