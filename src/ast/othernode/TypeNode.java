package src.ast.othernode;

import src.MXIR.type.IRType;
import src.ast.ASTVisitor;
import src.ast.Node;
import src.util.Position;

//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;

public class TypeNode extends Node {
    public Type type;  public IRType irType;
    public TypeNode(Position pos) {
        super(pos);
    }
    public TypeNode(Position pos, String name) {
        super(pos);
        this.type = new Type(name);
    }
//    public TypeNode(Position pos, String name, int dim) {
//        super(pos);
//        this.type = new Type(name, dim);
//    }
    public TypeNode(Position pos, String name, int dim) {
        super(pos);
        if (dim == 0) {
            switch (name) {
                case "int":
                    this.type = IntOPr; break;
                case "bool":
                    this.type = BoolOPr; break;
                case "string":
                    this.type = StringOPr; break;
                case "void":
                    this.type = VoidOPr; break;
                default:
                    this.type = new Type(name);
            }
        } else {
            this.type = new Type(name, dim);
        }
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