package src.ast.othernode;

import src.ast.ASTVisitor;
import src.ast.Node;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;
//import javax.swing.text.Position;
import java.util.ArrayList;

public class ParameterListNode extends Node {
    public ArrayList<VarDefUnitNode> units = new ArrayList<VarDefUnitNode>();

    public ParameterListNode(Position pos) {
        super(pos);
    }
    public ParameterListNode(Position pos, Type type, int cnt) {
        super(pos);
        for (int i = 0; i < cnt; ++i) {
            units.add(new VarDefUnitNode(pos, new TypeNode(pos, type.typeName, type.dimension), "p" + i));
        }
    }
    public ParameterListNode(Position pos , ArrayList<Type>types,int cnt){
        super(pos);
        for(int i=0;i<cnt;i++){
            units.add(new VarDefUnitNode(pos,new TypeNode(pos, types.get(i)),"p"+i));
        }
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
