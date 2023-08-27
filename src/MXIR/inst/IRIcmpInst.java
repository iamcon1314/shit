//package IR.inst;
package src.MXIR.inst;
import src.MXIR.entity.*;
import src.MXIR.type.*;
import src.MXIR.*;

// eq, ne, sgt, sge, slt, sle, ugt, uge, ult, ule

public class IRIcmpInst extends IRInst {
  public IRType type;
  public IRRegister cmpReg;
  public IREntity lhs, rhs;
  public String op;

  public IRIcmpInst(IRBasicBlock block, IRType type, IRRegister cmpReg, IREntity lhs, IREntity rhs, String op) {
    super(block);
    this.type = type;
    this.cmpReg = cmpReg;
    this.lhs = lhs;
    this.rhs = rhs;
    this.op = op;
  }

  @Override
  public String toString() {
    return cmpReg + " = icmp " + op +" " + type +  " " + lhs + ", " + rhs;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}