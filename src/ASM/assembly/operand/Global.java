//package assembly.operand;
package src.ASM.assembly.operand;
public abstract class Global extends Reg {
  public String name;
  public Global(String name) {
    this.name = name;
  }
}