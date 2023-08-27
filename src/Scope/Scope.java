package src.Scope;

import src.MXIR.IRFunction;
import src.MXIR.entity.IRRegister;
import src.ast.othernode.Type;
import java.util.HashMap;

import src.ast.othernode.ClassDefNode;
import src.ast.stmtnode.LoopStmtNode;
//import src.util.Type;

public class Scope {
    public HashMap<String, Type> varMember = new HashMap<>();
    public Scope parentScope = null;
//    public Scope getParentScope
    public Type returnType = null;
    public Type getReturnType(){
        return returnType;
    }
    public  Scope getParentScope(){
        return parentScope;
    }
    public ClassDefNode getcclass(){
        return  nowclass;
    }
    public boolean ifinloop(){
        if(inLoop){
            return true;
        }
        return false;
    }

    public boolean ifreturned(){
        if(isReturned) {
            return true;
        }
        return false;
    }
    public ClassDefNode  nowclass = null;
    public boolean inLoop = false;
    public boolean isReturned=false;
    public LoopStmtNode inWhichLoop = null;
    public HashMap<String, IRRegister> IRVarMember = new HashMap<>();
    public HashMap<String, IRFunction> IRFuncMember = new HashMap<>();
    public Scope() {}
    public Scope(Scope parentScope, LoopStmtNode inWhichLoop) {
        this(parentScope);
        this.inLoop = true;
        this.inWhichLoop = inWhichLoop;
    }
    public Scope(Scope parentScope) {
        this.inWhichLoop = parentScope.inWhichLoop;
        this.parentScope = parentScope;
        this.inLoop = parentScope.inLoop;
        this. nowclass = parentScope. nowclass;
    }
    public Scope(Scope parentScope, boolean isLoopScope) {
        this(parentScope);
        this.inLoop = isLoopScope;
    }
    public Scope(Scope parentScope, Type returnType) {
        this.parentScope = parentScope;
        this.returnType = returnType;
        this. nowclass = parentScope. nowclass;
    }
    public Scope(Scope parentScope, ClassDefNode  nowclass) {
        this.parentScope = parentScope;
        this. nowclass =  nowclass;
    }

    public void addVar(String name, Type type) {
        varMember.put(name, type);
    }
    public boolean ifVarexsit(String name) {
        return varMember.containsKey(name);
    }
    public Type getVarType(String name) {
        if (varMember.containsKey(name)) {
            return varMember.get(name);
        }
        else {
            return parentScope != null ? parentScope.getVarType(name) : null;
        }
    }
    public void addIRVar(String name, IRRegister reg) {
        IRVarMember.put(name, reg);
    }

    public IRRegister getIRVarPtr(String name) {
        if (IRVarMember.containsKey(name))
            return IRVarMember.get(name);
        else {
            // TODO : may have problem
            return parentScope != null ? parentScope.getIRVarPtr(name) : null;
        }
    }
}
