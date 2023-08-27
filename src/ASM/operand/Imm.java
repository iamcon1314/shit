package src.ASM.operand;

import src.ASM.inst.*;
import java.util.LinkedList;

public class Imm extends Operand {
  int value;

  public Imm(int value) {
    this.value = value;
  }

  public String toString() {
    return Integer.toString(value);
  }
}