package src.ASM.operand;

import src.ASM.inst.*;
import java.util.LinkedList;

public class GlobalString extends Global {
  public String str;

  public GlobalString(String name, String str) {
    super(name);
    this.str = str;
  }

  public String toString() {
    String ret = name + ":\n";
    ret += "  .string \"" + str.replace("\\", "\\\\")
        .replace("\n", "\\n")
        .replace("\0", "")
        .replace("\t", "\\t")
        .replace("\"", "\\\"") + "\"\n";
    return ret;
  }
}