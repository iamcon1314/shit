package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;

// add, sub, mul, udiv, sdiv, urem, srem, shl, lshr, ashr, and, or, xor

public class IRCalcInst extends IRInst {
  public IRType resultType;
  public String op;
  public IRRegister res;
  public IREntity lhs, rhs;

  public IRCalcInst(IRBasicBlock block, IRType resultType, IRRegister res, IREntity lhs, IREntity rhs, String op) {
    super(block);
    this.resultType = resultType;
    this.res = res;
    this.lhs = lhs;
    this.rhs = rhs;
    this.op = op;
  }
  
  @Override
  public String toString() {
    return res + " = " + op + " " + lhs.toStringWithType() + ", " + rhs;
  }
  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}