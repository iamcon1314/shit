package src.ast;
import src.util.*;
//import grammar;

//import javax.swing.text.Position;

//import src.util.*;

public abstract class Node implements SomethingExisting {
    public Position pos;// 用于存储节点所指代的对源代码范围

    public Node(Position pos) {
        this.pos = pos;
    }

    public abstract void accept(ASTVisitor visitor);
}