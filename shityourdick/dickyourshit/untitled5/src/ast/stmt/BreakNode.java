package src.ast.stmt;

import src.ast.*;
import src.utils.*;

public class BreakNode extends StmtNode {
  public BreakNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}