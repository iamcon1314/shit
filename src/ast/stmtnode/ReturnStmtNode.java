package src.ast.stmtnode;

import src.ast.*;
import src.ast.ExprNode;
import src.ast.StmtNode;
import src.util.*;

public class ReturnStmtNode extends StmtNode {
  public ExprNode value;

  public ReturnStmtNode(Position pos, ExprNode value) {
    super(pos);
    this.value = value;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}