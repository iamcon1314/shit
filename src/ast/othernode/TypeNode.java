package src.ast.othernode;

import src.ast.ASTVisitor;
import src.ast.Node;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;

public class TypeNode extends Node {
    public Type type;
    public TypeNode(Position pos) {
        super(pos);
    }
    public TypeNode(Position pos, String name) {
        super(pos);
        this.type = new Type(name);
    }
    public TypeNode(Position pos, String name, int dim) {
        super(pos);
        this.type = new Type(name, dim);
    }

    public TypeNode(Position pos,Type type){
        super(pos);
        this.type=type;
        this.pos=pos;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}