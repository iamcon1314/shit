//package src.ASM;
package src.ASM.inst;
import src.ASM.inst.*;
import src.ASM.operand.Imm;
import src.ASM.operand.Reg;

import java.util.LinkedList;

public abstract class ASMInst {
  public Reg rd, rs1, rs2;
  public Imm imm;

  public abstract String toString();
}