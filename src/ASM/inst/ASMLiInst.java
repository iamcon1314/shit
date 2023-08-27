package src.ASM.inst;

import src.ASM.inst.*;
import src.ASM.operand.Reg;
import src.ASM.operand.VirtualImm;

import java.util.LinkedList;

public class ASMLiInst extends ASMInst {
  public VirtualImm pseudoImm;

  public ASMLiInst(Reg rd, VirtualImm imm) {
    this.rd = rd;
    this.pseudoImm = imm;
  }

  @Override
  public String toString() {
    return "li " + rd + ", " + pseudoImm;
  }
}