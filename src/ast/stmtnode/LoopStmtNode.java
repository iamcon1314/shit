package src.ast.stmtnode;

import src.MXIR.IRBasicBlock;
import src.ast.ExprNode;
import src.ast.StmtNode;
import src.ast.exprnode.*;
import src.util.*;
import java.util.ArrayList;

public abstract class LoopStmtNode extends StmtNode {
    public ExprNode condition;
    public IRBasicBlock condBlock, loopBlock, nextBlock;
    public ArrayList<StmtNode> stmts = new ArrayList<StmtNode>();

    public LoopStmtNode(Position pos) {
        super(pos);
    }
}
