package src.ast.othernode;

public class Type {
    public String typeName;
    public int dimension = 0;
    public boolean isClass = false;
    public boolean isArray=false;
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
        this.dimension = dim;
    }

    public Type(Type type) {
        this.typeName = type.typeName;
        this.dimension = type.dimension;
        this.isClass = type.isClass;
        this.isArray=type.isArray;
    }

    public boolean isReferenceType() {
        if(isClass){
            return true;
        }
        if(isArray){
            return true;
        }
        if(dimension>0){
            return true;
        }
        return false;
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
        if (this.dimension != type.dimension)
            return false;
        if (!this.typeName.equals(type.typeName))
            return false;
        return true;
    }
};