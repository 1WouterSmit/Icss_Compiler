grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';
EQUALS_OPERATOR: '==';
GREATER_OPERATOR: '>';
LESSER_OPERATOR: '<';
NOT_OPERATOR: '!';



//--- PARSER: ---

// Stylesheets
stylesheet: variable_assignment* stylerule* EOF;

// Variables
variable_assignment: variable_reference ASSIGNMENT_OPERATOR expression SEMICOLON;
variable_reference: CAPITAL_IDENT;

// Stylerules
stylerule: selector OPEN_BRACE body CLOSE_BRACE;
body: (declaration | if_clause | variable_assignment)*;

// Declarations
declaration: property_name COLON expression SEMICOLON;

// Property
property_name: 'color' | 'width' | 'background-color' | 'height';

// Expression
expression: literal | variable_reference | expression MUL expression | expression (PLUS | MIN) expression |
    NOT_OPERATOR expression | expression EQUALS_OPERATOR expression | expression LESSER_OPERATOR expression |
    expression GREATER_OPERATOR expression;

// Literal
literal: boolean_literal | pixel_literal | percentage_literal | scalar_literal | color_literal;
boolean_literal: TRUE | FALSE;
pixel_literal: PIXELSIZE;
percentage_literal: PERCENTAGE;
scalar_literal: SCALAR;
color_literal: COLOR;

// Selectors
selector: tag_selector | id_selector | class_selector;
tag_selector: LOWER_IDENT;
id_selector: ID_IDENT;
class_selector: CLASS_IDENT;

// If-Else
if_clause: IF condition OPEN_BRACE body CLOSE_BRACE else_clause?;
else_clause: ELSE OPEN_BRACE body CLOSE_BRACE;
condition: BOX_BRACKET_OPEN expression BOX_BRACKET_CLOSE;


