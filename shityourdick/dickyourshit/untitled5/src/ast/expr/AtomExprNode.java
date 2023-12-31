package src.ast.expr;

import src.ast.*;
//import src.ast.stmt.*;
import src.utils.*;

public class AtomExprNode extends ExprNode {
  public AtomExprNode(Position pos, String str) {
    super(pos);
    this.str = str;
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
