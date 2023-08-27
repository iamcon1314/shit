package src.ASM.inst;

import src.ASM.ASMBlock;
import src.ASM.inst.*;
import java.util.LinkedList;

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