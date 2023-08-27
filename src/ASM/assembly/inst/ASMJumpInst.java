//package assembly.inst;
package src.ASM.assembly.inst;
import src.ASM.assembly.*;

public class ASMJumpInst extends ASMInst {
  ASMBlock toBlock;

  public ASMJumpInst(ASMBlock toBlock) {
    this.toBlock = toBlock;
  }

  @Override
  public String toString() {
    return "j " + toBlock.name;
  }
}