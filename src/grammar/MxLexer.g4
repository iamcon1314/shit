lexer grammar MxLexer;


//tokens


@header { package src.grammar; }


//Punctuation


Add: '+';
Sub: '-';
Mul: '*';
Div: '/';
Mod: '%';
GreaterThan: '>';
LesserThan: '<';
GreaterEqual: '>=';
LesserEqual: '<=';
NotEqual: '!=';
EqualEqual: '==';
LAnd: '&&';
LOr: '||';
LNot: '!';
RightShift: '>>';
LeftShift: '<<';
BAnd: '&';
BOr: '|';
BXor: '^';
BNot: '~';
Assign: '=';
SelfdoubleAdd: '++';
SelfdoubleSub: '--';
Member: '.';
LeftmidParen: '[';
RightmidParen: ']';
LeftParen: '(';
RightParen: ')';
Semi: ';';
Comma: ',';
LeftbigParen: '{';
RightbigParen: '}';
Questionmark: '?';
Colon:':';
Quote: '"';
Arrow: '->';


//Keywords


Void: 'void';
Bool: 'bool';
Int: 'int';
String: 'string';
New: 'new';
Class: 'class';
Null: 'null';



True: 'true';
False: 'false';


This: 'this';
If: 'if';
Else: 'else';



For: 'for';
While: 'while';
Break: 'break';
Continue: 'continue';
Return: 'return';


//Identifier



Identifier: [A-Za-z][0-9A-Za-z_]*;
IntConst: [1-9][0-9]* | '0';

StringConst : '"' (EscapeSequence | [ -~])*? '"';
BoolConst: (True|False);

fragment EscapeSequence : '\\n' | '\\\\' | '\\"';


WhiteSpace: [ \t\r\n]+ -> skip;






BlockComment
    :   '/*' .*? '*/'
        -> channel(HIDDEN)
    ;





LineComment
    :   '//' ~[\r\n]*
     -> channel(HIDDEN)
    ;
