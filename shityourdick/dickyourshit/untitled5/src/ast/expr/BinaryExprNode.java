package src.ast.expr;

import src.ast.*;
import src.ast.stmt.*;
import src.utils.*;

public class BinaryExprNode extends ExprNode {
  public String op;
  public ExprNode lhs;
  public ExprNode rhs;
  public BinaryExprNode(Position pos, ExprNode lhs, String op, ExprNode rhs) {
    super(pos);
    this.lhs = lhs;
    this.op = op;
    this.rhs = rhs;
  }

  @Override
  public boolean isLeftValue() {
    return false;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}