package src.ASM.inst;

import src.ASM.inst.*;
import src.ASM.operand.Imm;
import src.ASM.operand.Reg;

import java.util.LinkedList;

public class ASMLuiInst extends ASMInst {
  public ASMLuiInst(Reg dest, Imm imm) {
    this.rd = dest;
    this.imm = imm;
  }

  @Override
  public String toString() {
    return "lui " + rd + ", " + imm;
  }
}