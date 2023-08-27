package src.ast.stmtnode;

import src.MXIR.IRBasicBlock;
import src.ast.*;
import src.ast.ExprNode;
import src.ast.StmtNode;
import src.util.*;

import java.util.ArrayList;

public class WhileStmtNode extends LoopStmtNode {
//  public IRBasicBlock condBlock, loopBlock, nextBlock;

  public WhileStmtNode(Position pos, ExprNode condition) {
    super(pos);
    this.condition = condition;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}