package src.utils;

import src.ast.*;

public class Type {
    public String typeName;
    public int dim = 0;
    public boolean isClass = false;
    public Type(String typeName) {
        this.typeName = typeName;
        if(typeName.equals("void")||typeName.equals("int")||typeName.equals("bool")||typeName.equals("string")||typeName.equals("this")||typeName.equals("null")){
            isClass = false;
        }
        else {
            isClass = true;
        }
    }

    public Type(String typeName, int dim) {
        this.typeName = typeName;
//        this.typeName.equals(typeName);
        if(typeName.equals("void")||typeName.equals("int")||typeName.equals("bool")||typeName.equals("string")||typeName.equals("this")||typeName.equals("null")){
            isClass = false;
        }
        else {
            isClass = true;
        }
        this.dim = dim;
    }

    public Type(Type type) {
        this.typeName = type.typeName;
        this.dim = type.dim;
        this.isClass = type.isClass;
    }

    public boolean isReferenceType() {
        if(isClass==true||dim > 0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Type))
            return false;
        Type type = (Type) obj;
        if (this.dim != type.dim)
            return false;
        if (!this.typeName.equals(type.typeName))
            return false;
        return true;
    }
};