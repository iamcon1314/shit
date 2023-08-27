package src.ASM.inst;

import src.ASM.ASMBlock;
import src.ASM.inst.*;
import src.ASM.operand.Reg;

import java.util.LinkedList;

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