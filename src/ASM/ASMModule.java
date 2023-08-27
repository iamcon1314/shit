package src.ASM;

import src.ASM.inst.*;
import src.ASM.operand.GlobalString;
import src.ASM.operand.GlobalValue;

import java.util.ArrayList;
import java.util.LinkedList;

public class ASMModule {
  public ArrayList<GlobalValue> globalValues = new ArrayList<GlobalValue>();
  public ArrayList<GlobalString> globalStrings = new ArrayList<GlobalString>();
  public ArrayList<ASMFunction> functions = new ArrayList<ASMFunction>();

  public String toString() {
    String ret = "";
    if (globalValues.size() > 0)
      ret += "  .section .data\n";
    for (GlobalValue globalValue : globalValues)
      ret += globalValue;
    if (globalStrings.size() > 0)
      ret += "  .section .rodata\n";
    for (GlobalString globalString : globalStrings)
      ret += globalString;
    for (ASMFunction function : functions)
      ret += function;
    return ret;
  }
}