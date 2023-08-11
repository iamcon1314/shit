package src.ast.stmtnode;

import src.ast.*;
import src.ast.StmtNode;
import src.util.*;

import java.util.ArrayList;

public class SuiteNode extends StmtNode {
  public ArrayList<StmtNode> stmtnodes = new ArrayList<StmtNode>();

  public SuiteNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}