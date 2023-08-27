package src.ast.stmt;

import src.ast.*;
import src.ast.expr.*;
import src.utils.*;
import java.util.ArrayList;

public class WhileStmtNode extends StmtNode {
  public ExprNode cond;
  public ArrayList<StmtNode> stmts = new ArrayList<StmtNode>();

  public WhileStmtNode(Position pos, ExprNode cond) {
    super(pos);
    this.cond = cond;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}