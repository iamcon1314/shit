package src.frontend;

import src.Scope.GlobalScope;
import src.Scope.Scope;
import src.myerror.BaseError;
import src.util.*;

import src.ast.*;
import src.ast.stmtnode.*;
import src.ast.exprnode.*;
import src.ast.othernode.*;

import java.util.HashMap;

public class SemanticChecker implements ASTVisitor, SomethingExisting {
  private GlobalScope globalScope;
  private Scope nowScope;
  private int level = 0;
  public  int depth=0;
 public HashMap<String, Integer>ClassBuilt=new HashMap<>();

 public boolean LeftInt(ExprNode expression){
   if ((expression.isLeftValue() && expression.type.equals(Int))) {
//     throw new BaseError(expression.pos, "Left Value SHIT AWAAAAA！！！！！！！FUCK");
     return false;
   }
   return true;
 }
 public boolean ifBuiltMuity(String name){
   if(ClassBuilt.containsKey(name)) {
     return true;
   }
   else{
     return false;
   }
 }
  public SemanticChecker(GlobalScope globalScope) {
    this.globalScope = globalScope;
    nowScope = globalScope;
  }

  public boolean ifmainexist(){
    if(globalScope.getFuncDef("main")==null){
      return false;
    }
    return true;
  }

  public void visit(ProgramNode node) {
    //检查main是否存在
    if (!ifmainexist()) {
      throw new BaseError(node.pos, "Oh!!SHit you even do not have correct main function FUCKYOU!!!!!!!!");
    }
    FuncDefNode mainFunc = globalScope.getFuncDef("main");
    if (!mainFunc.returnType.type.equals(new Type("int"))  ) {
      throw new BaseError(node.pos, "src.Main Always Return INT!!!!!!!!!!   NOT "+mainFunc.returnType.type+"   SHIT YOUR SHIT !!!!!!!!!!");
    }
    if(mainFunc.params != null){
      throw new BaseError(node.pos, "src.Main Always MUST NOT HAVE Params !!! SHIT YOUR SHIT !!!!!!!!!!");
    }
    //有mian
    for (int i = 0; i < node.defList.size(); i++) {
      var def = node.defList.get(i);
      def.accept(this);
    }
  }

//  public void visit(ProgramNode node) {
//    if(!ifmainexist()){
//      throw new BaseError(node.pos, "do not have correct main function");
//    }
//    FuncDefNode mainFunc = globalScope.getFuncDef("main");
//    if (!mainFunc.returnType.type.equals(IntType) || mainFunc.params != null)
//      throw new BaseError(node.pos, "do not have correct main function");
//    for (var def : node.defList) {
//      def.accept(this);
//    }
//  }
//  public void visit(FuncDefNode node) {
//    // Already add the funcName to globalScope in SymbolCollector
//    node.returnType.accept(this);
//    nowScope = new Scope(nowScope, node.returnType.type);
//    if (node.params != null) {
//      node.params.accept(this);
//    }
//    node.stmts.forEach(stmt -> stmt.accept(this));
//    if ((!VoidType.equals(node.returnType.type) && !node.name.equals("main")) && !nowScope.isReturned)
//      throw new BaseError(node.pos, "Function " + node.name + " should have return statement");
//    nowScope = nowScope.parentScope;
//  }

  public void visit(FuncDefNode node){
    node.returnType.accept(this);
    Scope tmpscope=new Scope(nowScope,node.returnType.type);
    nowScope=tmpscope;
    if(node.params==null){

      //throw new BaseError(node.pos, "src.Main Always MUST NOT HAVE Params !!! SHIT YOUR SHIT !!!!!!!!!!");
    }
    else{
      node.params.accept(this);
    }
    node.stmtnodes.forEach(stmt -> stmt.accept(this));
    //Void
    if(Void.equals(node.returnType.type)){
//      if(nowScope.isReturned==true){
//        throw new BaseError(node.pos, "Function Void " + node.name + " should have return statement");
//      }
      nowScope = nowScope.parentScope;
      return;
    }
    if(nowScope.isReturned==false) {
      if ( !node.name.equals("main")) {
        throw new BaseError(node.pos, "Function " + node.name + " SHould Have Return Statement SHIT you!!!!!!");
      }
    }
    nowScope = nowScope.parentScope;
    return;
  }


  public void visit(ClassDefNode node) {
    // Already add the className to globalScope in SymbolCollector
//    Scope tmpscope=new Scope(nowScope,node.returnType.type);
//    nowScope=tmpscope;

    nowScope = new Scope(nowScope, node);
    node.varDefList.forEach(varDef -> varDef.accept(this)); // add varName to nowScope
//    if (node.classBuild != null) {
//      if (node.name.equals(node.classBuild.name)) {
//        node.classBuild.accept(this);
//      }
//      else {
//        throw new BaseError(node.classBuild.pos, node.name+"Class Name Does Not Match Its CONSTURCT FUCKYOU!!!!!!!!!");
//      }
//    }
        if (node.classBuild != null) {
      if (node.name.equals(node.classBuild.name)) {
        node.classBuild.accept(this);
      }
      else {
        throw new BaseError(node.classBuild.pos, node.name+"Class Name Does Not Match Its CONSTURCT FUCKYOU!!!!!!!!!");
      }
    }
    node.funcDefList.forEach(funcDef -> funcDef.accept(this)); // add funcName to nowScope
    nowScope = nowScope.parentScope;
  }

  public void visit(VarDefNode node) {
    node.units.forEach(unit -> unit.accept(this));
  }

  public void visit(VarDefUnitNode node) {
    node.type.accept(this);
    if (node.initVal != null) {
      node.initVal.accept(this);
    }
    //you should check the initVal first
    if (nowScope.ifVarexsit(node.varName)) {
//      nowScope.addVar(node.varName, node.type.type);
      throw new BaseError(node.pos, "Value FUCK: " + node.varName+" ALready EXISTS!!!!!!!!!!BEACH!!!");
    }
    if(globalScope.getFuncDef(node.varName)!=null&&nowScope==globalScope){
      throw new BaseError(node.pos, "Value FUCK: " + node.varName+" ALready EXISTS AS FUNCTION!!!!!!!!!!BEACH!!!");
    }
//    类与变量可崇明
//    if(globalScope.getClassDef(node.varName)!=null&&nowScope==globalScope){
//      throw new BaseError(node.pos, "Value FUCK: " + node.varName+" ALready EXISTS AS FUNCTION!!!!!!!!!!BEACH!!!");
//    }
    nowScope.addVar(node.varName, node.type.type);
  }

  public void visit(ParameterListNode node) {
    node.units.forEach(param -> param.accept(this));
  }

  public void visit(TypeNode node) {
    switch (node.type.typeName) {
      case "int":return;
      case "bool":return;
      case "string":return;
      case "void":return;
      case "null":return;
      case "this":return;
      default: {
//        if(globalScope.getFuncDef(node.type.typeName)!=null){
//          return;
//        }
        if (globalScope.getClassDef(node.type.typeName) == null) {
          throw new BaseError(node.pos, "YOU DONT DEFIEN THE CLASS " + node.type.typeName + "FUCKING!!!!!");
        }

      }
    }
  }


  public void visit(ClassBuildNode node) {
    nowScope = new Scope(nowScope, Void);
    if(ifBuiltMuity(node.name)){
      throw new BaseError(node.pos, "Multiple Constructors FUCKING YOU!!!!!!!!!");
    }
    ClassBuilt.put(node.name,1);

    node.suite.accept(this);
    nowScope = nowScope.parentScope;
  }

  public void visit(SuiteNode node) {
    nowScope = new Scope(nowScope);
//    try {
      node.stmtnodes.forEach(stmt -> stmt.accept(this));

    nowScope = nowScope.parentScope;
  }

  public void visit(IfStmtNode node) {
    node.condition.accept(this);
    if (!Bool.equals(node.condition.type)) {
      throw new BaseError(node.pos, "IF CONDITION MUST BE BOOL!!!!!!!!!!SHIT!!!!");
    }
    nowScope = new Scope(nowScope);
    node.thenStmts.forEach(stmt -> stmt.accept(this));
//    nowScope = nowScope.parentScope;
    if (node.elseStmts != null) {
      nowScope = nowScope.parentScope;
      nowScope = new Scope(nowScope);
      node.elseStmts.forEach(stmt -> stmt.accept(this));
      nowScope = nowScope.parentScope;
    }
    else{
      nowScope = nowScope.parentScope;
      return;
    }
  }

  public void visit(WhileStmtNode node) {
    node.condition.accept(this);
    if (!Bool.equals(node.condition.type)) {
      throw new BaseError(node.pos, "WHILE CONDITION MUST BE BOOL!!!!!!!!!!SHIT!!!!");
    }
    nowScope = new Scope(nowScope);

    nowScope.inLoop=true;

    node.stmts.forEach(stmt -> stmt.accept(this));

    nowScope = nowScope.parentScope;
  }

  public void visit(ForStmtNode node) {
    nowScope = new Scope(nowScope);
    nowScope.inLoop=true
            ;
    if (node.varDef != null) {
      node.varDef.accept(this);
    }
    if (node.init != null) {
      node.init.accept(this);
    }
    if (node.condition != null) {
      node.condition.accept(this);
      if (!Bool.equals(node.condition.type)) {
        throw new BaseError(node.pos, "FOR CONDITION MUST BE BOOL!!!!!!!!!!SHIT!!!!");
      }
    }
    if (node.step != null) {
      node.step.accept(this);
    }
    node.stmtnodes.forEach(stmt -> stmt.accept(this));
    nowScope = nowScope.parentScope;
  }

  public void visit(ContinueNode node) {
    if (nowScope.ifinloop()==false) {
      throw new BaseError(node.pos, "YOUR Continue FUCK your WRONG POSITION!!!!!!!!");
    }
  }

  public void visit(BreakNode node) {
    if (nowScope.ifinloop()==false) {
      throw new BaseError(node.pos, "YOUR BREAK FUCK your WRONG POSITION!!!!!!!!");
    }
  }

  public void visit(ReturnStmtNode node) {
    for (var tmpSCOPE = nowScope; tmpSCOPE != null; tmpSCOPE = tmpSCOPE.getParentScope()) {
      if(tmpSCOPE.returnType==null){
        continue;
      }
      else {
        if (node.value == null) {
          if (!tmpSCOPE.returnType.equals(Void)) {
            throw new BaseError(node.pos, "RETURN TYPE WRONG !!!!!!!!!!!!! FUCK !");
          }
        } else {
          node.value.accept(this);
          if (Auto.equals(tmpSCOPE.returnType)) {

              tmpSCOPE.returnType = node.value.type;

          } else if (!tmpSCOPE.returnType.equals(node.value.type)
                  && (!tmpSCOPE.returnType.isReferenceType() || !Null.equals(node.value.type))) {
            throw new BaseError(node.pos, "RETURN TYPE WRONG !!!!!!!!!!!!! FUCK !");
          }
        }
        tmpSCOPE.isReturned = true;
        return;
      }

    }
    throw new BaseError(node.pos, "return statement outside function");
  }

  public void visit(ExprStmtNode node) {
    if (node.expr != null) {
      node.expr.accept(this);
    }
  }

  public void visit(AtomExprNode node) {
    if (node.str.equals("null")) {
      node.type = Null;
    } else if (node.str.equals("true") || node.str.equals("false")) {
      node.type = Bool;
    } else if (node.str.matches("\".*\"")) {
      node.type = String;
    } else if (node.str.equals("this")) {
      if (nowScope. nowclass == null)
        throw new BaseError(node.pos, "ITS NOT IN CLASS FUCKING CRAZY!!!!");
      node.type = new Type(nowScope. nowclass.name);
    } else {
      node.type = Int;
    }
    // System.out.println(node.type.typeName);
//    throw new BaseError(node.pos, "NO TYPE!!!!!!!!!!!!");
  }

  public void visit(VarExprNode node) {
    node.type = nowScope.getVarType(node.str);
    if (nowScope. nowclass != null && nowScope. nowclass.getFuncDef(node.str) != null) {
      node.funcDef = nowScope.nowclass.getFuncDef(node.str);
    }
    else {
      node.funcDef = globalScope.getFuncDef(node.str);
    }
    // System.out.println(node.type.typeName);
  }

  public void visit(BinaryExprNode node) {
    node.lhs.accept(this);
    node.rhs.accept(this);
    //没有类型
    if (node.lhs.type == null || node.rhs.type == null || Void.equals(node.lhs.type) || Void.equals(node.rhs.type)) {
      throw new BaseError(node.pos, "BinaryExprNode CONtains NULL (VOID)!!!!!!!!!!SHIT!!!!!!!");
    }
    if (!(Null.equals(node.lhs.type) || Null.equals(node.rhs.type)) && !node.lhs.type.equals(node.rhs.type)) {
      throw new BaseError(node.pos, "Type WAAAAAAAAAA!!!!!!!!");
    }
    // 两个 null 不一样
    if (Null.equals(node.lhs.type) || Null.equals(node.rhs.type)) {
      // maybe an object compare with null
      if ((node.op.equals("==") || node.op.equals("!="))
              && (node.lhs.type.isReferenceType() || node.rhs.type.isReferenceType())) {
        node.type = Bool;
        return;
      } else if (!node.lhs.type.equals(node.rhs.type)) {
        throw new BaseError(node.pos, "CANOT JUDGE SHIT!!!!!!!!!!!!!!!");
      }
    }
//    if () {
//      throw new BaseError(node.pos, "invalid expression");
//    }
    IntType ifINT = new IntType(node.op);
    if (ifINT.optype == Int) {
      if (node.lhs.type.equals(Int) == false) {
        throw new BaseError(node.pos, "Type Canot Match SHIT!!!!!!!!!!!!");
      }
//        if(node.rhs.str=="0"){
//          if(node.op=="/"){
//            throw new
//          }
//        }
      node.type = Int;
      return;
    }
    MoreType ifMORE = new MoreType(node.op);
    if (ifMORE.optype == "MoreType") {
      if (!(node.lhs.type.equals(Int) || node.lhs.type.equals(String)) || !(node.rhs.type.equals(Int) || node.rhs.type.equals(String))) {
        throw new BaseError(node.pos, "Type Canot Match SHIT!!!!!!!!!!!!");
      } else {
        if ((node.lhs.type.equals(Int) && node.rhs.type.equals(String)) || (node.rhs.type.equals(Int) && node.lhs.type.equals(String))) {
          throw new BaseError(node.pos, "Type Canot Match LEFT and RIGHT SHIT!!!!!!!!!!!!");
        }
//          node.type = node.op.equals("+") ? new Type(node.lhs.type) : Bool;
        if (node.op.equals("+")) {
          node.type = node.lhs.type;
        } else {
          node.type = Bool;
        }

      }
      return;
    }
    BoolType ifBOOL = new BoolType(node.op);
    if (ifBOOL.optype == Bool) {
      if (!node.lhs.type.equals(Bool))
        throw new BaseError(node.pos, "Type mismatch");
      node.type = Bool;
      return;
    }
    node.type = Bool;
    return;
  }
//下面是错误代码
//  public void visit(UnaryExprNode node) {
//    node.expr.accept(this);
//    if (node.expr.type == null) {
//      throw new BaseError(node.pos, "Type WAAAAAAAAAA!!!!!!!!");
//    }
//    if (node.op.equals("++") || node.op.equals("--")) {
//      if (!node.expr.isLeftValue() || !node.expr.type.equals(Int)) {
//        throw new BaseError(node.pos, "Left value required");
//      }
//      node.type = new Type(Int);
//    } else if (node.op.equals("!")) {
//      if (!node.expr.type.equals(Bool)) {
//        throw new BaseError(node.pos, "Type is not bool");
//      }
//      node.type = new Type(Bool);
//    } else {
//      if (!node.expr.type.equals(Int)) {
//        throw new BaseError(node.pos, "Type is not int");
//      }
//      node.type = new Type(Int);
//    }
//  }

  public void visit(UnaryExprNode node) {
    node.expr.accept(this);
    checkExprType(node.expr, node.op);
    setTypeBasedOnOperator(node.op, node.pos, node);
  }

  private void checkExprType(ExprNode expr, String op) {
    if (expr.type == null) {
      throw new BaseError(expr.pos, "Type WAAAAAAAAAA!!!!!!!!");
    }
    switch (op) {
      case "++":
      case "--": {
        if (LeftInt(expr)) {
          throw new BaseError(expr.pos, "Left Value SHIT AWAAAAA！！！！！！！FUCK");
        }
        break;
      }
      case "!": {
        if (!expr.type.equals(Bool)) {
          throw new BaseError(expr.pos, "BOLL NEEDED FUCK!!!!!!!!!!!");
        }
        break;
      }
      default:
        if (!expr.type.equals(Int)) {
          throw new BaseError(expr.pos, "INTTT NEEDED FUCK!!!!!!!!!!!");
        }
        break;
    }
  }

  private void setTypeBasedOnOperator(String op, Position pos, UnaryExprNode node) {
    switch (op) {
      case "++":
      case "--": {
        node.type = new Type(Int);
        break;
      }
      case "!": {
        node.type = new Type(Bool);
        break;
      }
      default: {
        node.type = new Type(Int);
        break;
      }
    }
  }

  public void visit(PreAddExprNode node) {
    node.expr.accept(this);
    if (node.expr.type == null) {
      throw new BaseError(node.pos, "YOUR TYPE WHERE??????????");
    }
    if (LeftInt(node.expr)) {
      throw new BaseError(node.pos, "Left Value SHIT AWAAAAA！！！！！！！FUCK");
    }
    node.type = new Type(Int);
  }


//public void visit(PreAddExprNode node) {
//    validateExpression(node.expr);
//
//    setTypeAndCheckLeftValue(node);
//}
//
//private void validateExpression(ExprNode expr) {
//    if (expr.type == null) {
//        throw new BaseError(expr.pos, "invalid expression");
//    }
//}
//
//private void setTypeAndCheckLeftValue(PreAddExprNode node) {
//    ExprNode expr = node.expr;
//
//    if (!expr.isLeftValue() || !expr.type.equals(Int)) {
//        throw new BaseError(node.pos, "Left value required");
//    }
//
//    node.type = new Type(Int);
//}
  public void visit(AssignExprNode node) {
    node.lhs.accept(this);
    node.rhs.accept(this);
    if (node.lhs.type == null || node.rhs.type == null) {
      throw new BaseError(node.pos, "YOUR TYPE WHERE??????????");
    }
    if (Void.equals(node.lhs.type) || Void.equals(node.rhs.type)) {
      throw new BaseError(node.pos, "YOUR TYPE SERIOUS??????????");
    }
    if (!node.lhs.type.equals(node.rhs.type) && (!node.lhs.type.isReferenceType() || !Null.equals(node.rhs.type))) {
      throw new BaseError(node.pos, "Type Mismatch YOU !!!!!!!!!!!FUCK!!!!!!!!");
    }
    node.type = new Type(node.lhs.type);
    if (!node.lhs.isLeftValue()) {
      throw new BaseError(node.pos, "Left Value SHIT AWAAAAA！！！！！！！FUCK");
    }
  }

  public void visit(FuncExprNode node) {
    node.funcName.accept(this);
    if (node.funcName.funcDef == null) {
      throw new BaseError(node.pos, "Function " + node.funcName.str + " Not defined SHIT!!!!!!!!!!!!");
    }
    var funcDef = node.funcName.funcDef;
    if (node.args != null) {
      node.args.accept(this);
      if (funcDef.params == null || funcDef.params.units.size() != node.args.exprnodes.size()) {
        throw new BaseError(node.pos, "Parameter Number WAAAAA!!!!!!!!!!!!");
      }
      for (int i = 0; i < funcDef.params.units.size(); i++) {
        var param = funcDef.params.units.get(i);
        var arg = node.args.exprnodes.get(i);
        if (!param.type.type.equals(arg.type) && (!param.type.type.isReferenceType() || !Null.equals(arg.type))) {
          throw new BaseError(node.pos, "Parameter Type WAAAAAAAAA!");
        }
      }
    } else {
      if (funcDef.params != null) {
        throw new BaseError(node.pos, "Parameter Number WAAAAAAA!!!!!!");
      }
    }
    node.type = new Type(funcDef.returnType.type);
  }

  public void visit(ArrayExprNode node) {
//    node.array.accept(this);
    node.array.accept(this);
    node.index.accept(this);
    if(node.index.type == null){
      throw new BaseError(node.pos, "YOUR INDEX WHERE!!!!!?????????????");
    }
    if(!node.index.type.equals(Int)){
      throw new BaseError(node.pos, "YOUR INDEX NOT INT!!!!!!!!!!!!!!!!!");
    }
    if (node.array.type == null) {
      throw new BaseError(node.pos, "YOUR TYPE EROOR!!!!!!!!!!!!!!!!!");
    }
    node.type = new Type(node.array.type);
    node.type.dimension=node.type.dimension-1;
    if (node.type.dimension < 0) {
      throw new BaseError(node.pos, "YOUR DIMENSION EROOR!!!!!!!!!!!!!!!!!");
    }
  }

  public void visit(MemberExprNode node) {
    node.obj.accept(this);
    if (node.obj.type==null||(!node.obj.type.isReferenceType() && !node.obj.type.equals(This) && !node.obj.type.equals(String)) ){
      throw new BaseError(node.pos, "CLASS Type FUCKING WRONG!!!!!!!!!");
    }
    ClassDefNode classDef;
    if(This.equals(node.obj.type)){
      classDef=nowScope. nowclass;
    }
    else{
      classDef=globalScope.getClassDef(node.obj.type.typeName);
      if(classDef==null){
        throw new BaseError(node.pos, "WHERRE YOUR CLASS!!!!!!!!!!!!!!");
      }
    }
//    ClassDef classDef;
//if (This.equals(node.obj.type)) {
//    classDef = nowScope.nowclass;
//} else {
//    classDef = globalScope.getClassDef(node.obj.type.typeName);
//}
    if (node.obj.type.dimension > 0) {
      if (node.member.equals("size")) {
        node.funcDef = ArraySizeFunc;
      }
    }
    else {
      node.type = classDef.getVarType(node.member);
      node.funcDef = classDef.getFuncDef(node.member);
      if(node.type==null&&node.funcDef==null){
        throw new BaseError(node.pos, "WHERRE YOUR CLASS ELE!!!!!!!!!!!!!!");
      }
//      if(node.type==null||node.funcDef)
    }
  }
//public void visit(MemberExprNode node) {
//  node.obj.accept(this);
//  if (node.obj.type == null) {
//      throw new BaseError(node.pos, "invalid expression");
//    }
//    if (!node.obj.type.isReferenceType() && !This.equals(node.obj.type) && !String.equals(node.obj.type))
//      throw new BaseError(node.pos, "Type mismatch");
//  for (int i = 0; i < node.sizeList.size(); i++) {
//    var size = node.sizeList.get(i);
//    size.accept(this);
//    if (size.type == null || !size.type.equals(Int)) {
//      throw new BaseError(node.pos, "invalid expression");
//    }
//  }
//
//  new TypeNode(node.pos, node.typeName).accept(this);
//
//  if (node.obj.type == null) {
//    throw new BaseError(node.pos, "invalid expression");
//  }
//  if (!node.obj.type.isReferenceType() && !This.equals(node.obj.type) && !String.equals(node.obj.type)) {
//    throw new BaseError(node.pos, "Type mismatch");
//  }
//
//  var classDef = This.equals(node.obj.type)
//          ? nowScope.nowclass
//          : globalScope.getClassDef(node.obj.type.typeName);
//
//  if (node.obj.type.dimension > 0) {
//    if (classDef == null) {
//      throw new BaseError(node.pos, "Type mismatch");
//    }
//    if (node.member.equals("size")) {
//      node.funcDef = ArraySizeFunc;
//    }
//  } else {
//    if (classDef == null) {
//      throw new BaseError(node.pos, "Class " + node.obj.type.typeName + " is not defined");
//    }
//    node.type = classDef.getVarType(node.member);
//    node.funcDef = classDef.getFuncDef(node.member);
//  }
//}

//  public void visit(NewExprNode node) {
//    for (var size : node.sizeList) {
//      size.accept(this);
//      if ( !size.type.equals(Int)) {
//        throw new BaseError(node.pos, "ARRAY [] MUST BE INT!!!!!!!!FUCK!");
//      }
//    }
//    new TypeNode(node.pos, node.typeName).accept(this);
//    node.type = new Type(node.typeName, node.dimension);
//
//  }
  public void visit(NewExprNode node) {
    for (int i = 0; i < node.sizeList.size(); i++) {
      var tmp = node.sizeList.get(i);
      tmp.accept(this);
      if (tmp.type == null || !tmp.type.equals(Int)) {
        throw new BaseError(node.pos, "NEW SHIT YOU!!!!!!!!!!!");
      }
    }
    new TypeNode(node.pos, node.typeName).accept(this);
    node.type = new Type(node.typeName, node.dimension);
    //    if(node.dimension==0){
//      throw new BaseError(node.pos, "ARRAY [] MUST BE INT!!!!!!!!FUCK!");
//    }
  }


  public void visit(TriExprNode node) {

  }

  public void visit(ExprListNode node) {

    node.exprnodes.forEach(expr -> expr.accept(this));
  }
}