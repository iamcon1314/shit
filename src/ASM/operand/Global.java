package src.ASM.operand;

import src.ASM.inst.*;
import java.util.LinkedList;
public abstract class Global extends Reg {
  public String name;
  public Global(String name) {
    this.name = name;
  }
}