package src.ast.expr;

import src.ast.*;
import src.ast.stmt.*;
import src.utils.*;
import java.util.ArrayList;

public class FuncExprNode extends ExprNode {
  public ExprNode funcName;
  public ExprListNode args;

  public FuncExprNode(Position pos, ExprNode func) {
    super(pos);
    this.funcName = func;
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