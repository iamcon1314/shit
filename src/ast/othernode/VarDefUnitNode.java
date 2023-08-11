package src.ast.othernode;

import src.ast.ASTVisitor;
import src.ast.ExprNode;
import src.ast.Node;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class VarDefUnitNode extends Node {
    public TypeNode type;
    public String varName;
    public ExprNode initVal;

    public VarDefUnitNode(Position pos, TypeNode type, String name) {
        super(pos);
        this.type = type;
        this.varName = name;
    }
    public VarDefUnitNode(Position pos, TypeNode type, String name, ExprNode initVal) {
        super(pos);
        this.type = type;
        this.varName = name;
        this.initVal = initVal;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}