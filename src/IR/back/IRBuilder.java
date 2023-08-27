package src.IR.back;

import src.Scope.GlobalScope;
import src.Scope.Scope;
import src.ast.othernode.*;
import src.ast.othernode.Type;
import src.util.*;
import src.ast.*;
import src.ast.stmtnode.*;
import src.ast.exprnode.*;
import src.IR.*;
import src.IR.inst.*;
import src.IR.entity.*;
import src.IR.type.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class IRBuilder implements ASTVisitor, SomethingExisting {
  public void initialblockcnt(){
    IRBasicBlock.blockCnt=0;
  }
  IRFunction currentFunction = null;
  //store func
  IRStructType currentClass = null;
  //store class
  IRBasicBlock currentBlock = null;
  //store block
  GlobalScope globalScope;
  //Global Scope
  Scope currentScope;
  //store currentScope;
  IRProgram root;
  //Where the program in
  HashMap<String, IRStructType> structTypeMap = new HashMap<>();
  //store Struct;
  HashMap<IRRegister, Integer> arraySizeMap = new HashMap<>();
  public void AddLoadInst(ExprNode node){
    IRRegister val = new IRRegister("", ((IRPtrType) node.storePtr.type).pointToType());
    currentBlock.addInst(new IRLoadInst(currentBlock, val, node.storePtr));
  }
  //Store Array;
  public IRBuilder(IRProgram root, GlobalScope globalScope) {
    this.root = root;
    this.globalScope = globalScope;
    currentScope = globalScope;
  }
  private IREntity getVal(ExprNode node) {  // to get the value and add the instruction to the current block
    if (node.IRvalue != null) {
//      IREntity tmp=node.IRvalue;
      return node.IRvalue;
    }
    else {
//      currentBlock.addInst(new IRLoadInst(currentBlock, val, node.storePtr));
      assert node.storePtr != null;
//      AddLoadInst();
      IRRegister val = new IRRegister("", ((IRPtrType) node.storePtr.type).pointToType());
      currentBlock.addInst(new IRLoadInst(currentBlock, val, node.storePtr));
      node.IRvalue=val;
      return node.IRvalue;
    }
  }
  private IREntity getCond(ExprNode node) {
    IREntity cond = getVal(node);
    if (cond.type == irBoolType) {
      IRRegister tmp = new IRRegister("", irCondType);
      currentBlock.addInst(new IRTruncInst(currentBlock, tmp, cond, irCondType));
      return tmp;
    }
    if(cond.type==irCondType){
      return cond;
    }

    return cond;
  }
  private IREntity getCondition(ExprNode node) {
    IREntity cond = getVal(node);
    if (cond.type == irBoolType) {
      IRRegister tmp = new IRRegister("", irCondType);
      currentBlock.addInst(new IRTruncInst(currentBlock, tmp, cond, irCondType));
      return tmp;
    }
    return cond;
  }
  private IRType functypeTrans(Type type) {
    IRType tmp_irType;
    switch (type.typeName) {
      case "int":
        tmp_irType = irIntType; break;
      case "bool":
        tmp_irType =  irCondType; break;
      case "string":
        tmp_irType = irStringType; break;
      case "void":
        tmp_irType = irVoidType; break;
      default:
        tmp_irType = new IRPtrType(structTypeMap.get(type.typeName), 1);  // all class is pointer
    }
    if (type.dimension > 0)
      tmp_irType = new IRPtrType(tmp_irType, type.dimension);
    return tmp_irType;
  }
  private IRType typeTrans(Type type, boolean isReturnType) {
    IRType tmp_irType;
    switch (type.typeName) {
      case "int":
        tmp_irType = irIntType; break;
      case "bool":
        tmp_irType = isReturnType ? irCondType : irBoolType; break;
      case "string":
        tmp_irType = irStringType; break;
      case "void":
        tmp_irType = irVoidType; break;
      default:
        tmp_irType = new IRPtrType(structTypeMap.get(type.typeName), 1);  // all class is pointer
    }
    if (type.dimension > 0)
      tmp_irType = new IRPtrType(tmp_irType, type.dimension);
    return tmp_irType;
  }

  private void addStore(IRRegister ptr, ExprNode rhs) {
    if (getVal(rhs).type == irCondType) {
      IRRegister tmp = new IRRegister("", irBoolType);
      currentBlock.addInst(new IRZextInst(currentBlock, tmp, rhs.IRvalue, irBoolType));
      currentBlock.addInst(new IRStoreInst(currentBlock, tmp, ptr));
    } else {
      if (rhs.IRvalue instanceof IRNullConst) {
        rhs.IRvalue = new IRNullConst(((IRPtrType) ptr.type).pointToType());
      }
      currentBlock.addInst(new IRStoreInst(currentBlock, rhs.IRvalue, ptr));
    }
  }

  private IRRegister stringCmp(String cmpName, IREntity lhs, IREntity rhs) {
    IRRegister tmp = new IRRegister("", irBoolType);
    IRRegister res = new IRRegister("", irCondType);
    currentBlock.addInst(new IRCallInst(currentBlock, tmp, irBoolType, cmpName, lhs, rhs));
    currentBlock.addInst(new IRTruncInst(currentBlock, res, tmp, irCondType));
    return res;
  }

  @Override
  public void visit(ProgramNode node) {
    node.defList.forEach(def -> {
      if (def instanceof ClassDefNode)
//        structTypeMap.put(((ClassDefNode) def).name, new IRStructType(((ClassDefNode) def).name));
      structTypeMap.put(((ClassDefNode) def).name, new IRStructType(((ClassDefNode) def).name, ((ClassDefNode) def).varMember.size() << 2));
      // 4 bytes for each member
    });  // first pass to get all struct type
    node.defList.forEach(def -> {
      if (def instanceof ClassDefNode) def.accept(this);
    });
    node.defList.forEach(def -> {
      if (def instanceof VarDefNode) def.accept(this);
    });
    node.defList.forEach(def -> {
      if (def instanceof FuncDefNode) def.accept(this);
    });
//    node.defList.forEach(def->{if(def instanceof ClassDefNod)})
//    node.defList.forEach(def -> {
//      if (def instanceof VarDefNode) def.accept(this);
//    });
//    node.defList.forEach(def -> {
//      if (def instanceof FuncDefNode) def.accept(this);
//    });
    // add global var init function

    if (root.initBlock.insts.size() == 0) {
//      didnotprocessIniFunc();
      root.initFunc = null;
    } else {
      processIniFunc();
    }
    root.mainFunc.finish();
  }
  public void didnotprocessIniFunc(){
//    root.initFunc.finish();
//    IRBasicBlock mainEntry = root.mainFunc.blocks.get(0);
//    mainEntry.insts.addFirst(new IRCallInst(mainEntry, irVoidType, "__mx_global_var_init"));
  }
  public void processIniFunc(){
    root.initFunc.finish();
    IRBasicBlock mainEntry = root.mainFunc.blocks.get(0);
    mainEntry.insts.addFirst(new IRCallInst(mainEntry, irVoidType, "__mx_global_var_init"));
  }




  @Override
  public void visit(FuncDefNode node) {


    if(IRBasicBlock.blockCnt!=0) {
      initialblockcnt();
    }


    IRBasicBlock.blockCnt = 0;

    node.returnType.irType = functypeTrans(node.returnType.type);


    String name;
    if(currentClass!=null){
      name=currentClass.name + "." + node.name;
    }
    else{
      name=node.name;
    }

//    String funcName = currentClass != null ? currentClass.name + "." + node.name : node.name;


    currentFunction = new IRFunction(name, node.returnType.irType);
    root.funcList.add(currentFunction);

    currentScope = new Scope(currentScope, node.returnType.type);
    currentBlock = currentFunction.appendBlock(new IRBasicBlock(currentFunction, "entry_"));

    //below in the Entry Block!
    if (currentClass != null) {  // is a method
      IRPtrType classPtrType = new IRPtrType(currentClass);
      IRRegister thisVal = new IRRegister("this", classPtrType);
      currentFunction.params.add(thisVal);
      // store this pointer
      IRRegister thisAddr = new IRRegister("this.addr", new IRPtrType(classPtrType));
      currentBlock.addInst(new IRAllocaInst(currentBlock, classPtrType, thisAddr));
      currentBlock.addInst(new IRStoreInst(currentBlock, thisVal, thisAddr));
      currentScope.addIRVar("this", thisAddr);
    }
    if (node.params != null) {
      node.params.accept(this);
    }
    else{

    }


    currentFunction.exitBlock = new IRBasicBlock(currentFunction, "return_");



    //below is exit block
    currentBlock.terminalInst = new IRJumpInst(currentBlock, currentFunction.exitBlock);
    if (node.returnType.type.equals(VoidOPr)) {
      currentFunction.exitBlock.terminalInst = new IRRetInst(currentFunction.exitBlock, irVoidConst);
    }
    else {
      IRType retType =node.returnType.irType;
      if(node.returnType.irType==irCondType){
        retType=irBoolType;
      }
//      IRType retValType = node.returnType.irType == irCondType ? irBoolType : node.returnType.irType;
      currentFunction.retAddr = new IRRegister("retval", new IRPtrType(retType));
      currentFunction.exitBlock.addInst(new IRAllocaInst(currentBlock, retType, currentFunction.retAddr));
      IRRegister retVal = new IRRegister("ret", retType);
      currentFunction.exitBlock.addInst(new IRLoadInst(currentBlock, retVal, currentFunction.retAddr));
      if (node.returnType.irType == irCondType) {
        IRRegister tmp = new IRRegister("", irCondType);
        currentFunction.exitBlock.addInst(new IRTruncInst(currentBlock, tmp, retVal, irCondType));
        currentFunction.exitBlock.terminalInst = new IRRetInst(currentFunction.exitBlock, tmp);
      }
      else {
        currentFunction.exitBlock.terminalInst = new IRRetInst(currentFunction.exitBlock, retVal);
      }
    }


    if (name.equals("main")) {
      root.mainFunc = currentFunction;
//      currentBlock.addInst(new IRStoreInst(currentBlock, irIntConst0, currentFunction.retAddr));
    }



    node.stmtnodes.forEach(stmt -> stmt.accept(this));


    if (currentBlock.terminalInst == null) {
      for(int i=1;i<=100;i++) {
        System.out.print("FUCK  YOU   BE" +
                "FUCK  YOU   BEACH" +
                "FUCK  YOU   BEACH" +
                "FUCK  YOU   BEACH" +
                "FUCK  YOU   BEACH" +
                "FUCK  YOU   BEACHFUCK  YOU   BEACH" +
                "FUCK  YOU   BEACH" +
                "FUCK  YOU   BEACH" +
                "FUCK  YOU   BEACH" +
                "ACH");
      }
      if (node.returnType.type.equals(VoidOPr)) {
        currentBlock.addInst(new IRRetInst(currentBlock, irVoidConst));
      }
      else {
        currentBlock.addInst(new IRRetInst(currentBlock, irIntConst0));
      }
    }



    node.irFunc = currentFunction;  // store the ir function
    currentScope = currentScope.parentScope;
    if (!name.equals("main")) {
      currentFunction.finish();
    }
    currentFunction = null;
    currentBlock = null;
  }

  @Override
  public void visit(ClassDefNode node) {
    currentScope = new Scope(currentScope, node);
    currentClass = structTypeMap.get(node.name);  // set current class


    if(currentClass==null){
      System.out.print("FUCK  YOU   BE" +
              "FUCK  YOU   BEACH" +
              "FUCK  YOU   BEACH" +
              "FUCK  YOU   BEACH" +
              "FUCK  YOU   BEACH" +
              "FUCK  YOU   BEACHFUCK  YOU   BEACH" +
              "FUCK  YOU   BEACH" +
              "FUCK  YOU   BEACH" +
              "FUCK  YOU   BEACH" +
              "ACH"+
              "THE CLASS NOT IN");
    }

    //just for debug!!!!!!

    root.structTypeList.add(currentClass);

    node.varDefList.forEach(varDef -> varDef.accept(this)); // first add all the fields


    //check buliding
    if (node.classBuild != null) {
      currentClass.hasBuild = true;
      node.classBuild.accept(this);
    }


    node.funcDefList.forEach(funcDef -> funcDef.className = node.name);
    node.funcDefList.forEach(funcDef -> funcDef.accept(this));
    currentScope = currentScope.parentScope;
//    currentClass.calcSize();
    currentClass = null;
  }

  @Override
  public void visit(VarDefNode node) {
    node.units.forEach(unit -> unit.accept(this));
  }

  @Override
  public void visit(VarDefUnitNode node) {
    node.type.accept(this);


    if (currentFunction != null) {
      //Var in a function
      // check if it's in a function first
      IRRegister definingPtr = new IRRegister(node.varName + ".addr", new IRPtrType(node.type.irType));
      currentScope.addIRVar(node.varName, definingPtr);  // use the varName as the key
      // System.out.println(definingPtr.type);
      currentBlock.addInst(new IRAllocaInst(currentBlock, node.type.irType, definingPtr));
      if (node.initVal != null) {
        node.initVal.accept(this);
        addStore(definingPtr, node.initVal);
        return;
      }
      else if (node.type.type.isReferenceType()) {
        currentBlock.addInst(new IRStoreInst(currentBlock, new IRNullConst(node.type.irType), definingPtr));
        return;
      }
      return;
    }


    if (currentClass != null) {
      currentClass.addMember(node.varName, node.type.irType);
//      System.out.print("!23123123213");
      return;
      // do not add to currentScope
    }

      assert currentScope == globalScope;
      IRGlobalVar gVar = new IRGlobalVar(node.varName, node.type.irType);
      if (node.initVal != null && node.initVal instanceof AtomExprNode
              && !node.initVal.type.equals(StringOPr) && !node.initVal.str.equals("this")) {
        node.initVal.accept(this);
        gVar.initVal = getVal(node.initVal) instanceof IRCondConst
                ? new IRBoolConst(((IRCondConst) node.initVal.IRvalue).val)
                : node.initVal.IRvalue;
        globalScope.addIRVar(node.varName, gVar);
      }
      else {
//        gVar.initVal=node.type.irType.d
        gVar.initVal = node.type.irType.defaultValue();
        globalScope.addIRVar(node.varName, gVar);
        if (node.initVal != null) {
          IRFunction tmpFunc = currentFunction;
          IRBasicBlock tmpBlock = currentBlock;
          currentFunction = root.initFunc;
          currentBlock = root.initBlock;
          node.initVal.accept(this);
          addStore(gVar, node.initVal);
          root.initBlock = currentBlock;
          currentFunction = tmpFunc;
          currentBlock = tmpBlock;
        }
      }
      root.globalVarList.add(gVar);
      return;
//      return;
  }

  @Override
  public void visit(ParameterListNode node) {
    node.units.forEach(unit -> {
//      unit.initVal.accept(this);
      assert unit.initVal == null;
      unit.accept(this);
      IRRegister input = new IRRegister("", unit.type.irType);
      currentFunction.params.add(input);
      currentBlock.addInst(new IRStoreInst(currentBlock, input, currentScope.getIRVarPtr(unit.varName)));
    });
  }

  @Override
  public void visit(TypeNode node) {
    node.irType = typeTrans(node.type, false);
  }

  @Override
  public void visit(ClassBuildNode node) {
    node.transToFuncDef().accept(this);
  }

  @Override
  public void visit(SuiteNode node) {
    currentScope = new Scope(currentScope);
    node.stmtnodes.forEach(stmt -> stmt.accept(this));
    currentScope = currentScope.parentScope;
  }


  @Override
  public void visit(ContinueNode node) {
//    currentBlock.terminalInst=new IRJumpInst(currentBlock, currentScope.inWhichLoop.condBlock);

    if (currentScope.inWhichLoop instanceof WhileStmtNode) {
      currentBlock.terminalInst = new IRJumpInst(currentBlock, currentScope.inWhichLoop.condBlock);
    }
    else {
      currentBlock.terminalInst = new IRJumpInst(currentBlock, ((ForStmtNode) currentScope.inWhichLoop).stepBlock);
    }
    currentBlock.isFinished = true;
  }

  @Override
  public void visit(BreakNode node) {
//    if (currentScope.inWhichLoop instanceof WhileStmtNode) {
//      currentBlock.terminalInst = new IRJumpInst(currentBlock, currentScope.inWhichLoop.condBlock);
//    }
//    else {
//      currentBlock.terminalInst = new IRJumpInst(currentBlock, ((ForStmtNode) currentScope.inWhichLoop).stepBlock);
//    }
//    currentBlock.isFinished = true;
    currentBlock.terminalInst = new IRJumpInst(currentBlock, currentScope.inWhichLoop.nextBlock);
    currentBlock.isFinished = true;
  }

  @Override
  public void visit(ReturnStmtNode node) {
    if (node.value != null) {
      node.value.accept(this);
      addStore(currentFunction.retAddr, node.value);
    }
    currentBlock.terminalInst = new IRJumpInst(currentBlock, currentFunction.exitBlock);
    currentBlock.isFinished = true;
  }

  @Override
  public void visit(ExprListNode node) {
    node.exprnodes.forEach(expr -> expr.accept(this));
  }

  @Override
  public void visit(ExprStmtNode node) {
    if (node.expr != null) node.expr.accept(this);
  }

  @Override
  public void visit(IfStmtNode node) {
    //IF_STMT_NODE
    node.condition.accept(this);
    IREntity cond = getCond(node.condition);
    IRBasicBlock lastBlock = currentBlock;
    IRBasicBlock nextBlock = new IRBasicBlock(currentFunction, "if.end_");
    nextBlock.terminalInst = currentBlock.terminalInst;
    IRBasicBlock thenBlock = new IRBasicBlock(currentFunction, "if.then_", nextBlock);
    currentScope = new Scope(currentScope);
    currentBlock.isFinished = true;
    currentBlock = currentFunction.appendBlock(thenBlock);
    node.thenStmts.forEach(stmt -> stmt.accept(this));
    currentScope = currentScope.parentScope;


    if (node.elseStmts != null && !node.elseStmts.isEmpty()) {
      IRBasicBlock elseBlock = new IRBasicBlock(currentFunction, "if.else_", nextBlock);
      currentScope = new Scope(currentScope);
      currentBlock.isFinished = true;
      currentBlock = currentFunction.appendBlock(elseBlock);
      node.elseStmts.forEach(stmt -> stmt.accept(this));
      currentScope = currentScope.parentScope;
      lastBlock.terminalInst = new IRBranchInst(lastBlock, cond, thenBlock, elseBlock);
    }
    else {
      lastBlock.terminalInst = new IRBranchInst(lastBlock, cond, thenBlock, nextBlock);
    }


    currentBlock.isFinished = true;
    currentBlock = currentFunction.appendBlock(nextBlock);
  }

  @Override
  public void visit(WhileStmtNode node) {
    node.condBlock = new IRBasicBlock(currentFunction, "while.cond_");
    node.loopBlock = new IRBasicBlock(currentFunction, "while.loop_");
    node.nextBlock = new IRBasicBlock(currentFunction, "while.end_");
    node.nextBlock.terminalInst = currentBlock.terminalInst;
    currentBlock.terminalInst = new IRJumpInst(currentBlock, node.condBlock);
    currentBlock.isFinished = true;
    currentBlock = currentFunction.appendBlock(node.condBlock);
    node.condition.accept(this);
    currentBlock.terminalInst = new IRBranchInst(currentBlock, getCond(node.condition), node.loopBlock, node.nextBlock);
    currentBlock = currentFunction.appendBlock(node.loopBlock);
    currentScope = new Scope(currentScope, node);
    node.stmts.forEach(stmt -> stmt.accept(this));
    currentScope = currentScope.parentScope;
    currentBlock.terminalInst = new IRJumpInst(currentBlock, node.condBlock);
    currentBlock.isFinished = true;
    currentBlock = currentFunction.appendBlock(node.nextBlock);
  }

  @Override
  public void visit(ForStmtNode node) {
    currentScope = new Scope(currentScope, node);
    if (node.varDef != null) node.varDef.accept(this);
    if (node.init != null) node.init.accept(this);
    node.condBlock = new IRBasicBlock(currentFunction, "for.cond_");
    node.loopBlock = new IRBasicBlock(currentFunction, "for.loop_");
    node.stepBlock = new IRBasicBlock(currentFunction, "for.step_");
    node.nextBlock = new IRBasicBlock(currentFunction, "for.end_");
    node.nextBlock.terminalInst = currentBlock.terminalInst;
    currentBlock.terminalInst = new IRJumpInst(currentBlock, node.condBlock);
    currentBlock.isFinished = true;
    currentBlock = currentFunction.appendBlock(node.condBlock);
    if (node.condition != null) {
      node.condition.accept(this);
      currentBlock.terminalInst = new IRBranchInst(currentBlock, getCond(node.condition), node.loopBlock, node.nextBlock);
    } else {
      currentBlock.terminalInst = new IRJumpInst(currentBlock, node.loopBlock);
    }
    currentBlock.isFinished = true;
    currentScope = new Scope(currentScope);
    currentBlock = currentFunction.appendBlock(node.loopBlock);
    node.stmts.forEach(stmt -> stmt.accept(this));
    currentBlock.terminalInst = new IRJumpInst(currentBlock, node.stepBlock);
    currentBlock.isFinished = true;
    currentBlock = currentFunction.appendBlock(node.stepBlock);
    // Notice: step is not in the scope of for
    currentScope = currentScope.parentScope;
    if (node.step != null) node.step.accept(this);
    currentBlock.terminalInst = new IRJumpInst(currentBlock, node.condBlock);
    currentBlock.isFinished = true;
    currentBlock = currentFunction.appendBlock(node.nextBlock);
    currentScope = currentScope.parentScope;
  }



  @Override
  public void visit(AtomExprNode node) {
    if (node.type.equals(IntOPr)) {
      node.IRvalue = new IRIntConst(Integer.parseInt(node.str));
    } else if (node.type.equals(BoolOPr)) {
      node.IRvalue = new IRCondConst(node.str.equals("true"));
    } else if (node.type.equals(StringOPr)) {
      IRStringConst strPtr = root.addStringConst(node.str.substring(1, node.str.length() - 1));  // not contain quotes
      node.IRvalue = new IRRegister("", new IRPtrType(irCharType));
      currentBlock.addInst(new IRGetElementPtrInst(currentBlock, strPtr, (IRRegister) node.IRvalue, irIntConst0, irIntConst0));
    } else if (node.type.equals(NullOPr)) {
      node.IRvalue = new IRNullConst();
    } else { // this
      assert currentClass != null && node.str.equals("this");
      node.storePtr = currentScope.getIRVarPtr("this");
    }
  }

  @Override
  public void visit(VarExprNode node) {
    // maybe get a null pointer
    node.storePtr = currentScope.getIRVarPtr(node.str);
    if (node.storePtr == null) {  // is a member or a function
      IRRegister thisAddr = (IRRegister) currentScope.getIRVarPtr("this");
      if (thisAddr != null) {  // is a member
        IRType objPtrType =  ((IRPtrType) thisAddr.type).pointToType(), objRealType = ((IRPtrType) objPtrType).pointToType();
        IRRegister thisVal = new IRRegister("this", objPtrType);

        if (((IRStructType) objRealType).hasMember(node.str)) {
          currentBlock.addInst(new IRLoadInst(currentBlock, thisVal, thisAddr));
          node.storePtr = new IRRegister("this." + node.str, new IRPtrType(((IRStructType) objRealType).getMemberType(node.str)));
          currentBlock.addInst(new IRGetElementPtrInst(currentBlock, thisVal, node.storePtr, irIntConst0,
                  new IRIntConst(((IRStructType) objRealType).memberOffset.get(node.str))));
        }
      }
    }
  }

  @Override
  public void visit(BinaryExprNode node) {
    node.lhs.accept(this);
    if (!node.op.equals("&&") && !node.op.equals("||")) {
      node.rhs.accept(this);
    }
    else {
      IRRegister temp = new IRRegister(".shortCirTemp", new IRPtrType(irBoolType));
      currentBlock.addInst(new IRAllocaInst(currentBlock, irBoolType, temp));
      IRBasicBlock rhsBlock = new IRBasicBlock(currentFunction, "rhsBlock_");
      IRBasicBlock trueBlock = new IRBasicBlock(currentFunction, "trueBlock_");
      IRBasicBlock falseBlock = new IRBasicBlock(currentFunction, "falseBlock_");
      IRBasicBlock nextBlock = new IRBasicBlock(currentFunction, "shortCir.end_");
      nextBlock.terminalInst = currentBlock.terminalInst;
      currentBlock.terminalInst = node.op.equals("&&")
              ? new IRBranchInst(currentBlock, getCond(node.lhs), rhsBlock, falseBlock)
              : new IRBranchInst(currentBlock, getCond(node.lhs), trueBlock, rhsBlock);
      currentBlock.isFinished = true;
      currentBlock = currentFunction.appendBlock(rhsBlock);
      node.rhs.accept(this);
      currentBlock.terminalInst = new IRBranchInst(currentBlock, getCond(node.rhs), trueBlock, falseBlock);
      currentBlock.isFinished = true;
      currentBlock = currentFunction.appendBlock(trueBlock);
      currentBlock.addInst(new IRStoreInst(currentBlock, irBoolTrueConst, temp));
      currentBlock.terminalInst = new IRJumpInst(currentBlock, nextBlock);
      currentBlock.isFinished = true;
      currentBlock = currentFunction.appendBlock(falseBlock);
      currentBlock.addInst(new IRStoreInst(currentBlock, irBoolFalseConst, temp));
      currentBlock.terminalInst = new IRJumpInst(currentBlock, nextBlock);
      currentBlock.isFinished = true;
      currentBlock = currentFunction.appendBlock(nextBlock);
      IRRegister loadTemp = new IRRegister(".loadTemp", irBoolType);
      currentBlock.addInst(new IRLoadInst(currentBlock, loadTemp, temp));
      node.IRvalue = new IRRegister("", irCondType);
      currentBlock.addInst(new IRTruncInst(currentBlock, (IRRegister) node.IRvalue, loadTemp, irCondType));
      return;
    }
    IRRegister dest = null;
    IRType operandType = null;
    String op = null;
    if (node.lhs.type.equals(StringOPr) || node.rhs.type.equals(StringOPr)) {

      if(Objects.equals(node.op, "+")) {
        node.IRvalue = new IRRegister("", irStringType);
//        IRRegister lhsLen = new IRRegister("lhsLen", irIntType);
//        IRRegister rhsLen = new IRRegister("rhsLen", irIntType);
//        currentBlock.addInst(new IRCallInst(currentBlock, lhsLen, irIntType, "strlen", getVal(node.lhs)));
//        currentBlock.addInst(new IRCallInst(currentBlock, rhsLen, irIntType, "strlen", getVal(node.rhs)));
//        IRRegister tmp = new IRRegister("", irIntType);
//        currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, tmp, lhsLen, rhsLen, "add"));
//        IRRegister strLen = new IRRegister("strLen", irIntType);
//        currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, strLen, tmp, irIntConst1, "add"));
//        currentBlock.addInst(new IRCallInst(currentBlock, (IRRegister) node.IRvalue, irStringType, "malloc", strLen));
        currentBlock
                .addInst(new IRCallInst(currentBlock, (IRRegister) node.IRvalue, irStringType, "__mx_stradd",
                        getVal(node.lhs), getVal(node.rhs)));
        return;
      }


      switch (node.op) {
        case "<":
          node.IRvalue = stringCmp("__mx_strlt", getVal(node.lhs), getVal(node.rhs));
          break;
        case "<=":
          node.IRvalue = stringCmp("__mx_strle", getVal(node.lhs), getVal(node.rhs));
          break;
        case ">":
          node.IRvalue = stringCmp("__mx_strgt", getVal(node.lhs), getVal(node.rhs));
          break;
        case ">=":
          node.IRvalue = stringCmp("__mx_strge", getVal(node.lhs), getVal(node.rhs));
          break;
        case "==":
          assert node.lhs.type.equals(node.rhs.type);
          node.IRvalue = stringCmp("__mx_streq", getVal(node.lhs), getVal(node.rhs));
          break;
        case "!=":
          assert node.lhs.type.equals(node.rhs.type);
          node.IRvalue = stringCmp("__mx_strne", getVal(node.lhs), getVal(node.rhs));
          break;
      }
    }
    else {
      switch (node.op) {
        case "+": op = "add"; break;
        case "-": op = "sub"; break;
        case "*": op = "mul"; break;
        case "/": op = "sdiv"; break;
        case "%": op = "srem"; break;


        case "<<": op = "shl"; break;
        case ">>": op = "ashr"; break;
        case "&": op = "and"; break;
        case "|": op = "or"; break;
        case "^": op = "xor"; break;


        case "<": op = "slt"; break;
        case "<=": op = "sle"; break;
        case ">": op = "sgt"; break;
        case ">=": op = "sge"; break;
        case "==": op = "eq"; break;
        case "!=": op = "ne"; break;
      }


      switch (node.op) {
        case "+":
        case "-":
        case "*":
        case "/":
        case "%":

          //allINt
        case "<<":
        case ">>":
        case "&":
        case "|":
        case "^":
          operandType = irIntType;
          dest = new IRRegister("", irIntType);
          currentBlock.addInst(new IRCalcInst(currentBlock, operandType, dest, getVal(node.lhs), getVal(node.rhs), op));
          break;
        case "<":
        case "<=":
        case ">":
        case ">=":
          operandType = irIntType;
          dest = new IRRegister("", irCondType);
          currentBlock.addInst(new IRIcmpInst(currentBlock, operandType, dest, getVal(node.lhs), getVal(node.rhs), op));
          break;
        case "==":
        case "!=": {
          IREntity lhs = getVal(node.lhs);
          IREntity rhs = getVal(node.rhs);
          if (lhs.type instanceof IRIntType && lhs.type != irIntType) {
            IRRegister tmp = new IRRegister("", irIntType);
            currentBlock.addInst(new IRZextInst(currentBlock, tmp, lhs, irIntType));
            lhs = tmp;
          }
          if (rhs.type instanceof IRIntType && rhs.type != irIntType) {
            IRRegister tmp = new IRRegister("", irIntType);
            currentBlock.addInst(new IRZextInst(currentBlock, tmp, rhs, irIntType));
            rhs = tmp;
          }
          operandType = node.lhs.type.equals(NullOPr) ? node.rhs.getIRType() : node.lhs.getIRType();
          dest = new IRRegister("tmp", irCondType);
          currentBlock.addInst(new IRIcmpInst(currentBlock, operandType, dest, lhs, rhs, op));
          break;
        }
      }
      node.IRvalue = dest;
    }
  }

  @Override
  public void visit(UnaryExprNode node) {
    node.expr.accept(this);
    IRRegister dest = null;
    String op = null;
    switch (node.op) {
      case "++":
        op = "add";
        node.IRvalue = getVal(node.expr);
        dest = new IRRegister("", irIntType);
        currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, dest, node.IRvalue, irIntConst1, op));
        currentBlock.addInst(new IRStoreInst(currentBlock, dest, node.expr.storePtr));
        break;
      case "--":
        op = "sub";
        node.IRvalue = getVal(node.expr);
        dest = new IRRegister("", irIntType);
        currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, dest, node.IRvalue, irIntConst1, op));
        currentBlock.addInst(new IRStoreInst(currentBlock, dest, node.expr.storePtr));
        break;
      case "+":
        node.IRvalue = getVal(node.expr);
        break;
      case "-":
        op = "sub";
        dest = new IRRegister("", irIntType);
        currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, dest, irIntConst0, getVal(node.expr), op));
        node.IRvalue = dest;
        break;
      case "~":  // x ^ -1
        op = "xor";
        dest = new IRRegister("", irIntType);
        currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, dest, getVal(node.expr), irIntConstn1, op));
        node.IRvalue = dest;
        break;
      case "!":
        assert node.expr.type.equals(BoolOPr);
        op = "xor";
        dest = new IRRegister("", irCondType);
        currentBlock.addInst(new IRCalcInst(currentBlock, irCondType, dest, getCond(node.expr), irTrueConst, op));
        node.IRvalue = dest;
    }
  }

  @Override
  public void visit(PreAddExprNode node) {
    //"++"&&"--"
    node.expr.accept(this);
    IRRegister dest = null;
    String op = null;
    switch (node.op) {
      case "++":
        op = "add";
        dest = new IRRegister("", irIntType);
        currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, dest, getVal(node.expr), irIntConst1, op));
        currentBlock.addInst(new IRStoreInst(currentBlock, dest, node.expr.storePtr));
        node.IRvalue = dest;
        node.storePtr = node.expr.storePtr;
        break;
      case "--":
        op = "sub";
        dest = new IRRegister("", irIntType);
        currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, dest, getVal(node.expr), irIntConst1, op));
        currentBlock.addInst(new IRStoreInst(currentBlock, dest, node.expr.storePtr));
        node.IRvalue = dest;
        node.storePtr = node.expr.storePtr;
        break;
    }
  }

  @Override
  public void visit(AssignExprNode node) {
    node.rhs.accept(this);
    node.lhs.accept(this);
    node.storePtr = node.lhs.storePtr;
    node.IRvalue = getVal(node.rhs);
    addStore(node.storePtr, node.rhs);
  }

  @Override
  public void visit(FuncExprNode node) {
    node.funcName.accept(this);
    FuncDefNode funcDef = node.funcName.funcDef;
    String funcRealName = funcDef.className == null ? funcDef.name : funcDef.className + "." + funcDef.name;
    assert funcDef != null;
    funcDef.returnType.irType = functypeTrans(funcDef.returnType.type);
    IRCallInst call = new IRCallInst(currentBlock, funcDef.returnType.irType, funcRealName);

    // check builtin function
    if (funcDef == ArraySizeFunc) {
      IRRegister array = ((MemberExprNode) node.funcName).objAddr;
      IRRegister tmp1, tmp2 = new IRRegister("", irIntPtrType);
      if (array.type.toString().equals("i32*"))
        tmp1 = array;
      else
      {
        tmp1 = new IRRegister("", irIntPtrType);
        currentBlock.addInst(new IRBitcastInst(currentBlock, array, irIntPtrType, tmp1));
      }

      currentBlock.addInst(new IRGetElementPtrInst(currentBlock, tmp1, tmp2, irIntConstn1));
      node.IRvalue = new IRRegister("", irIntType);
      currentBlock.addInst(new IRLoadInst(currentBlock, (IRRegister) node.IRvalue, tmp2));

    }
    else {
      if (funcDef == StringLengthFunc) call.funcName = "strlen";
      else if (funcDef == StringSubStringFunc) call.funcName = "__mx_substring";
      else if (funcDef == StringParseIntFunc) call.funcName = "__mx_parseInt";
      else if (funcDef == StringOrdFunc) call.funcName = "__mx_ord";

      if (funcDef.className != null) {  // method
        if (node.funcName instanceof MemberExprNode)
          call.args.add(((MemberExprNode) node.funcName).objAddr);
        else {  // 需要传入 this 指针
          IRRegister thisPtr = currentScope.getIRVarPtr("this");
          IRRegister thisVal = new IRRegister("", ((IRPtrType) thisPtr.type).pointToType());
          currentBlock.addInst(new IRLoadInst(currentBlock, thisVal, thisPtr));
          call.args.add(thisVal);
        }
      }
      if (node.args != null) {
        node.args.accept(this);
        node.args.exprnodes.forEach(arg -> call.args.add(getVal(arg)));
      }
      if (funcDef.returnType.irType != irVoidType)
        call.callReg = new IRRegister("", funcDef.returnType.irType);
      currentBlock.addInst(call);
      node.IRvalue = call.callReg;
    }
  }

  @Override
  public void visit(ArrayExprNode node) {
    node.array.accept(this);
    node.index.accept(this);
    IRRegister dest = new IRRegister("", getVal(node.array).type);
    currentBlock.addInst(new IRGetElementPtrInst(currentBlock, getVal(node.array), dest, getVal(node.index)));
    node.storePtr = dest;
  }

  @Override
  public void visit(MemberExprNode node) {
    // all class is pointer
    node.obj.accept(this);
    IRType objRealType = getVal(node.obj).type;
    node.objAddr = (IRRegister) node.obj.IRvalue;  // store obj addr for member function call
    assert objRealType instanceof IRPtrType;
    objRealType = ((IRPtrType) objRealType).pointToType();
    if (objRealType instanceof IRStructType) {
      IRType memberType = ((IRStructType) objRealType).getMemberType(node.member);
      if (memberType != null) {
        node.storePtr = new IRRegister("", new IRPtrType(memberType));
        currentBlock.addInst(new IRGetElementPtrInst(currentBlock, getVal(node.obj), node.storePtr, irIntConst0,
                new IRIntConst(((IRStructType) objRealType).memberOffset.get(node.member))));
      }
    }
  }

  private IREntity newArray(IRType type, int at, ArrayList<ExprNode> sizeList) {
    IRRegister callReg = new IRRegister("", new IRPtrType(irCharType));
    sizeList.get(at).accept(this);
    IREntity cnt = getVal(sizeList.get(at)), size;
    int sizeOfType = ((IRPtrType) type).pointToType().size;
    if (cnt instanceof IRIntConst) {
      size = new IRIntConst(((IRIntConst) cnt).val * sizeOfType + 4);
    }
    else {
      IRIntConst typeSize = new IRIntConst(sizeOfType);
      IRRegister tmpSize = new IRRegister("", irIntType);
      currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, tmpSize, cnt, typeSize, "mul"));
      size = new IRRegister("", irIntType);
      currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, (IRRegister) size, tmpSize, irIntConst4, "add"));
    }
    currentBlock.addInst(new IRCallInst(currentBlock, callReg, new IRPtrType(irCharType), "malloc", size));

    // store the size of array
    IRRegister ptr, tmp1 = new IRRegister("", irIntPtrType), tmp2 = new IRRegister("", irIntPtrType);
    currentBlock.addInst(new IRBitcastInst(currentBlock, callReg, irIntPtrType, tmp1));
    currentBlock.addInst(new IRStoreInst(currentBlock, cnt, tmp1));
    currentBlock.addInst(new IRGetElementPtrInst(currentBlock, tmp1, tmp2, irIntConst1));
    if (type.toString().equals("i32*")) ptr = tmp2;
    else {
      ptr = new IRRegister("", type);
      currentBlock.addInst(new IRBitcastInst(currentBlock, tmp2, type, ptr));
    }

    if (at + 1 < sizeList.size()) {
      IRRegister idx = new IRRegister("", irIntPtrType);
      currentBlock.addInst(new IRAllocaInst(currentBlock, irIntType, idx));
      currentBlock.addInst(new IRStoreInst(currentBlock, irIntConst0, idx));
      IRBasicBlock condBlock = new IRBasicBlock(currentFunction, "for.cond_");
      IRBasicBlock loopBlock = new IRBasicBlock(currentFunction, "for.loop_");
      IRBasicBlock stepBlock = new IRBasicBlock(currentFunction, "for.step_");
      IRBasicBlock nextBlock = new IRBasicBlock(currentFunction, "for.end_");
      nextBlock.terminalInst = currentBlock.terminalInst;
      currentBlock.terminalInst = new IRJumpInst(currentBlock, condBlock);
      currentBlock.isFinished = true;

      currentBlock = currentFunction.appendBlock(condBlock);
      IRRegister cond = new IRRegister("", irCondType);
      IRRegister iVal = new IRRegister("", irIntType);
      currentBlock.addInst(new IRLoadInst(currentBlock, iVal, idx));
      currentBlock.addInst(new IRIcmpInst(currentBlock, irIntType, cond, iVal, cnt, "slt"));
      currentBlock.terminalInst = new IRBranchInst(currentBlock, cond, loopBlock, nextBlock);
      currentBlock.isFinished = true;

      currentBlock = currentFunction.appendBlock(loopBlock);
      IREntity iPtrVal = newArray(((IRPtrType) type).pointToType(), at + 1, sizeList);
      IRRegister iPtr = new IRRegister("", type);
//      IRRegister iVal2 = new IRRegister("", irIntType);
//      currentBlock.addInst(new IRLoadInst(nextBlock, iVal2, idx));
//      currentBlock.addInst(new IRGetElementPtrInst(currentBlock, ptr, iPtr, iVal2));
      currentBlock.addInst(new IRGetElementPtrInst(currentBlock, ptr, iPtr, iVal));
      currentBlock.addInst(new IRStoreInst(currentBlock, iPtrVal, iPtr));
      currentBlock.terminalInst = new IRJumpInst(currentBlock, stepBlock);
      currentBlock.isFinished = true;

      currentBlock = currentFunction.appendBlock(stepBlock);
//      IRRegister iVal3 = new IRRegister("", irIntType), iRes = new IRRegister("", irIntType);
//      currentBlock.addInst(new IRLoadInst(nextBlock, iVal3, idx));
//      currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, iRes, iVal2, irIntConst1, "add"));
      IRRegister iRes = new IRRegister("", irIntType);
      currentBlock.addInst(new IRCalcInst(currentBlock, irIntType, iRes, iVal, irIntConst1, "add"));
      currentBlock.addInst(new IRStoreInst(currentBlock, iRes, idx));
      currentBlock.terminalInst = new IRJumpInst(currentBlock, condBlock);
      currentBlock.isFinished = true;

      currentBlock = currentFunction.appendBlock(nextBlock);
    }
    return ptr;
  }
  @Override
  public void visit(NewExprNode node) {
    IRType type = typeTrans(node.type, false);
    if (node.dimension > 0) {
      node.IRvalue = node.sizeList.size() > 0 ? newArray(type, 0, node.sizeList) : new IRNullConst(type);
    } else {
      IRStructType classType = (IRStructType) ((IRPtrType) type).pointToType();
      IRRegister callReg = new IRRegister("", irStringType);
      currentBlock.addInst(new IRCallInst(currentBlock, callReg, irStringType, "malloc", new IRIntConst(classType.size)));
      node.IRvalue = new IRRegister("", type);
      currentBlock.addInst(new IRBitcastInst(currentBlock, callReg, type, (IRRegister) node.IRvalue));
      if (classType.hasBuild) {
        currentBlock.addInst(new IRCallInst(currentBlock, null, irVoidType, classType.name + "." + classType.name, node.IRvalue));
      }
    }
  }

  @Override
  public void visit(TriExprNode node) {} // No need to implement


}