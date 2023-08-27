package src.ast.stmt;

import src.ast.*;
import java.util.ArrayList;
import src.utils.*;

public class SuiteNode extends StmtNode {
  public ArrayList<StmtNode> stmts = new ArrayList<StmtNode>();

  public SuiteNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}