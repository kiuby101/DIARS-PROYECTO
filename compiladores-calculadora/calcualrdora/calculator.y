%{
 #include <stdio.h>
 void yyerror(char *);
 int yylex(void);
 int sym[26] /* PUEDE TENER HASTA 26 VARIABLES */;
%}
/* SIMBOLOS TERMINALES */
%token INTEGER VARIABLE
%left '+' '-' '*'

/* GRAMATICA */
%%
program:    program statement '\n'
       | /* NO HACE NADA */
;
statement:  expression { printf("%d\n", $1); }
         |  VARIABLE '=' expression { sym[$1] = $3; }
;
expression: INTEGER
          | VARIABLE { $$ = sym[$1]; }
          | '-' expression { $$ = -$2; }
          | expression '+' expression { $$ = $1 + $3; }
          | expression '-' expression { $$ = $1 - $3; }
          | expression '*' expression { $$ = $1 * $3; }
          | '(' expression ')' { $$ = $2; }
;
%%
void yyerror(char *s) {
 fprintf(stderr, "%s\n", s);
}
int main(void) {
 printf("Ingrese calculos como 2+3, o (5+8)*2. Solo puede usar operadores +, - y *\n");
 printf("Puede definir variables de una letra asi: a=2, y usarlas luego asi: (a+8)*2\n");
 printf("Digite ahora:\n");
 yyparse();
}