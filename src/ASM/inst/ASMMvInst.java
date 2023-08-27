package src.ASM.inst;

import src.ASM.inst.*;
import src.ASM.operand.Reg;

import java.util.LinkedList;

public class ASMMvInst extends ASMInst {
  public ASMMvInst(Reg rd, Reg rs) {
    this.rd = rd;
    this.rs1 = rs;
  }

  @Override
  public String toString() {
    return "mv " + rd + ", " + rs1;
  }
}