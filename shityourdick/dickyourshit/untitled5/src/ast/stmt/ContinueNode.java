package src.ast.stmt;

import src.ast.*;
import src.utils.*;

public class ContinueNode extends StmtNode {
  public ContinueNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}