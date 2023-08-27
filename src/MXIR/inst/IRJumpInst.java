//package IR.inst;
package src.MXIR.inst;
import src.MXIR.*;

public class IRJumpInst extends IRTerminalInst {
  public IRBasicBlock toBlock;

  public IRJumpInst(IRBasicBlock block, IRBasicBlock toBlock) {
    super(block);
    this.toBlock = toBlock;
  }

  @Override
  public String toString() {
    return "br label %" + toBlock.name;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}