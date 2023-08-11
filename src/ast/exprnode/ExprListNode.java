package src.ast.exprnode;

import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;
//import javax.swing.text.Position;

import java.util.ArrayList;
//import utils.*;

public class ExprListNode extends Node {
  public ArrayList<ExprNode> exprnodes = new ArrayList<ExprNode>();

  public ExprListNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}