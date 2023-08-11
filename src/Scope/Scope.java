package src.Scope;

import src.ast.othernode.Type;
import java.util.HashMap;

import src.ast.othernode.ClassDefNode;
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

    public Scope() {}
    public Scope(Scope parentScope) {
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
}
