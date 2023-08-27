package src.ast.stmt;

import src.ast.*;
import src.ast.expr.*;
import src.utils.*;
import java.util.ArrayList;

public class ForStmtNode extends StmtNode {
  public VarDefNode varDef;
  public ExprNode init, cond, step;
  public ArrayList<StmtNode> stmts = new ArrayList<StmtNode>();

  public ForStmtNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}