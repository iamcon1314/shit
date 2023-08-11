package src.ast.stmtnode;
//package src.frontend;
import src.util.*;
//import grammar;
import src.ast.*;

public class ContinueNode extends StmtNode {
  public ContinueNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}