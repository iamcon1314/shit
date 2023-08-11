package src.ast.othernode;

import src.ast.ASTVisitor;
import src.ast.Node;
import src.util.*;

import java.util.ArrayList;
import java.util.HashMap;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class ClassDefNode extends Node {
    public String name;
    public ArrayList<VarDefNode> varDefList = new ArrayList<VarDefNode>();
    public ArrayList<FuncDefNode> funcDefList = new ArrayList<FuncDefNode>();
    public HashMap<String, FuncDefNode> funcMember = new HashMap<String, FuncDefNode>();
    public HashMap<String, VarDefUnitNode> varMember = new HashMap<String, VarDefUnitNode>();
    public ClassBuildNode classBuild;

    public ClassDefNode(Position pos, String name) {
        super(pos);
        this.name = name;
    }

    public FuncDefNode getFuncDef(String name) {
        FuncDefNode tmp=funcMember.get(name);
        if(tmp==null){
            return null;
        }
        return tmp;
    }
    public Type getVarType(String name) {
        VarDefUnitNode unit = varMember.get(name);
        if (unit == null) return null;
        return unit.type.type;
    }

    public VarDefUnitNode getVarDef(String name){
        VarDefUnitNode tmp = varMember.get(name);
        if (tmp == null) return null;
        return tmp;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}