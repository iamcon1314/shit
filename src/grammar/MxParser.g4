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
  : Identifier (Assign expression)?;
type: typeName ('[' ']')*;
typeName: baseType | Identifier;
baseType: Int | Bool | String;

statement
  : '{' suite '}'
  | varDef
  | ifStmt | whileStmt | forStmt
  | breakStmt | continueStmt | returnStmt
  | expressionStmt;

ifStmt
  : If '(' expression ')' trueStmt=statement (Else falseStmt=statement)?;
whileStmt
  : While '(' expression ')' statement;
forStmt
  : For '(' forInit expressionStmt expression? ')' statement;
forInit
  : varDef | expressionStmt;

breakStmt: Break Semi;
continueStmt: Continue Semi;
returnStmt: Return expression? Semi;

expressionStmt: expression? Semi;
expression
  : '(' expression ')'                                      #parenExpr
  | New typeName (newArrayUnit)* ('(' ')')?           #newExpr
  | expression op=Member Identifier                         #memberExpr
  | expression '[' expression ']'                                 #arrayExpr
  | expression '(' expressionList? ')'                            #funcExpr
  | <assoc=right> expression op=(SelfdoubleAdd | SelfdoubleSub)         #unaryExpr
  | op=(SelfdoubleAdd | SelfdoubleSub) expression                       #preAddExpr
  | <assoc=right> op=(LNot | BNot | Add | Sub) expression   #unaryExpr
  | expression op=(Mul | Div | Mod) expression                    #binaryExpr
  | expression op=(Add | Sub) expression                          #binaryExpr
  | expression op=(LeftShift | RightShift) expression                    #binaryExpr
  | expression op=(LesserThan | GreaterThan | LesserEqual | GreaterEqual) expression    #binaryExpr
  | expression op=(EqualEqual | NotEqual) expression                    #binaryExpr
  | expression op=BAnd expression                                 #binaryExpr
  | expression op=BXor expression                                 #binaryExpr
  | expression op=BOr expression                                  #binaryExpr
  | expression op=LAnd expression                                 #binaryExpr
  | expression op=LOr expression                                  #binaryExpr
  | expression op1=Questionmark expression op2=Colon expression           #triExpr
  | <assoc=right> expression op=Assign expression                 #assignExpr
  | primary                                           #atomExpr
  ;

newArrayUnit: '[' expression? ']';
// may have problem
primary
  : IntConst | StringConst | True | False | Null
  | Identifier
  | This
  ;

expressionList: expression (Comma expression)*;