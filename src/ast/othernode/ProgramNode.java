package src.ast.othernode;

//package ast;

import src.ast.ASTVisitor;
import src.ast.Node;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;
//import javax.swing.text.Position;
import java.util.ArrayList;

public class ProgramNode extends Node {
    public ArrayList<Node> defList = new ArrayList<Node>();

    public ProgramNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
