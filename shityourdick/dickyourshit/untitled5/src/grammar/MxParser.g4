parser grammar MxParser;
options {
	tokenVocab = MxLexer;
}

@header { package src.grammar; }
program: (funcDef | classDef | varDef)* EOF;

funcDef
  : returnType Identifier '(' parameterList? ')' '{' suite '}';
returnType
  : type | Void;
parameterList
  : (type Identifier) (Comma type Identifier)*;

suite: statement*;

classDef
  : Class Identifier '{' (varDef | classBuild | funcDef)* '}' Semi;
classBuild
  : Identifier '(' ')' '{' suite '}';

//以下定义与文档一致
varDef
  : type varDefUnit (Comma varDefUnit)* Semi;
varDefUnit
  : Identifier (Assign expr)?;
type: typeName ('[' ']')*;
typeName: baseType | Identifier;
baseType: Int | Bool | String;

statement
  : '{' suite '}'
  | varDef
  | ifStmt | whileStmt | forStmt
  | breakStmt | continueStmt | returnStmt
  | exprStmt;

ifStmt
  : If '(' expr ')' statement (Else statement)?;
whileStmt
  : While '(' expr ')' statement;
forStmt
  : For '(' forInit exprStmt expr? ')' statement;
forInit
  : varDef | exprStmt;

breakStmt: Break Semi;
continueStmt: Continue Semi;
returnStmt: Return expr? Semi;

exprStmt: expr? Semi;
expr
  : '(' expr ')'                                      #parenExpr
  | New typeName (newArrayUnit)* ('(' ')')?           #newExpr
  | expr op=Member Identifier                         #memberExpr
  | expr '[' expr ']'                                 #arrayExpr
  | expr '(' exprList? ')'                            #funcExpr
  | <assoc=right> expr op=(SelfdoubleAdd | SelfdoubleSub)         #unaryExpr
  | op=(SelfdoubleAdd | SelfdoubleSub) expr                       #preAddExpr

  | <assoc=right> op=(LNot | BNot | Add | Sub) expr   #unaryExpr
  | expr op=(Mul | Div | Mod) expr                    #binaryExpr
  | expr op=(Add | Sub) expr                          #binaryExpr
  | expr op=(LeftShift | RightShift) expr                    #binaryExpr
  | expr op=(LesserThan | GreaterThan | LesserEqual | GreaterEqual) expr    #binaryExpr
  | expr op=(EqualEqual | NotEqual) expr                    #binaryExpr
  | expr op=BAnd expr                                 #binaryExpr
  | expr op=BXor expr                                 #binaryExpr
  | expr op=BOr expr                                  #binaryExpr
  | expr op=LAnd expr                                 #binaryExpr
  | expr op=LOr expr                                  #binaryExpr
  | expr op1=Questionmark expr op2=Colon expr           #triExpr
  | <assoc=right> expr op=Assign expr                 #assignExpr
  | primary                                           #atomExpr
  ;

newArrayUnit: '[' expr? ']';
// may have problem
primary
  : IntConst | StringConst | True | False | Null
  | Identifier
  | This
  ;

exprList: expr (Comma expr)*;