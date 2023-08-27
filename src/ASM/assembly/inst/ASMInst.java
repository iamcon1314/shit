//package assembly.inst;
package src.ASM.assembly.inst;
import src.ASM.assembly.operand.*;

public abstract class ASMInst {
  public Reg rd, rs1, rs2;
  public Imm imm;

  public abstract String toString();
}