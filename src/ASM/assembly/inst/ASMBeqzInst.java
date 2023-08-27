package src.ASM.assembly.inst;

import src.ASM.assembly.operand.*;
import src.ASM.assembly.*;

public class ASMBeqzInst extends ASMInst {
  ASMBlock toBlock;

  public ASMBeqzInst(Reg rs, ASMBlock toBlock) {
    this.rs1 = rs;
    this.toBlock = toBlock;
  }

  @Override
  public String toString() {
    return "beqz " + rs1 + ", " + toBlock.name;
  }
}