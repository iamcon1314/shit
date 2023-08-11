package src.ast.othernode;

//package ast;

import src.ast.ASTVisitor;
import src.ast.StmtNode;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;
//import javax.swing.text.Position;
import java.util.ArrayList;

public class VarDefNode extends StmtNode {
    public ArrayList<VarDefUnitNode> units = new ArrayList<VarDefUnitNode>();

    public VarDefNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
