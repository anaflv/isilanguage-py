# Projeto final Compiladores - 12/2020 

Nesse projeto, foi construido um compilador, que traduz código da linguagem IsiLanguage :dragon: para o Python :snake:.

Observações:
1. No Python, não há chaves e as apenas indentações são usadas para determinar o código que pertence a um bloco. Foi implementada a indentação correspondente aos blocos originais, no código em Isilanguage.
1. É possível implementar blocos aninhados, ou seja, um "enquanto" dentro de um "se", dentro de outro bloco "se". É também possível implementar um "se" com ou sem o "senão".
1. É possível comparar textos, com as operações "mesmo_texto" e "dif_texto".


## Gramática:

A gramática da linguagem IsiLanguage :dragon: possui a seguinte estrutura, nesse projeto:

```java
prog
:
	'programa' decl bloco 'fimprog.'
;

decl
:
	(
		declaravar
	)+
;

declaravar
:
	'declare' tipo ID
	(
		VIR ID
	)* PONTO
;

tipo
:
	'numero'
	| 'texto'
;

bloco
:
	(
		cmd
	)+
;

cmd
:
	cmdleitura
	| cmdescrita
	| cmdattrib
	| cmdselecao
	| cmdenquanto
;

cmdleitura
:
	'leia' AP ID FP PONTO
;

cmdescrita
:
	'escreva' AP
	(
		ID
		| TEXTO
	) FP PONTO
;

cmdattrib
:
	ID ATTR
	(
		expr
		| expr_texto
	) PONTO
;

cmdselecao
:
	'se' condition 'entao' ACH
	(
		cmd
	)+ FCH
	(
		'senao' ACH
		(
			cmd+
		) FCH
	)?
;

cmdenquanto
:
	'enquanto' condition ACH
	(
		cmd
	)+ FCH
;

condition
:
	AP ID
	(
		OPREL
		| OPTEXTO
	)
	(
		ID
		| NUMBER
		| TEXTO
	) FP
;

expr
:
	termo
	(
		OP termo
	)*
;

termo
:
	ID
	| NUMBER
;

expr_texto
:
	TEXTO
;

PONTO
:
	'.'
;

AP
:
	'('
;

FP
:
	')'
;

OP
:
	'+'
	| '-'
	| '*'
	| '/'
;

ATTR
:
	':='
;

VIR
:
	','
;

ACH
:
	'{'
;

FCH
:
	'}'
;

OPREL
:
	'<'
	| '>'
	| '>='
	| '<='
	| '=='
	| '!='
;

ID
:
	(
		[a-z]
		| [A-Z]
	)
	(
		[a-z]
		| [A-Z]
		| [0-9]
	)*
;

NUMBER
:
	[0-9]+
	(
		'.' [0-9]+
	)?
;

TEXTO
:
	'"'
	(
		[0-9]
		| [a-z]
		| [A-Z]
		| ' '
	)+ '"'
;

OPTEXTO
:
	'mesmo_texto'
	| 'dif_texto'
;

WS
:
	(
		' '
		| '\t'
		| '\n'
		| '\r'
	) -> skip
;
```




## Exemplos:

### Exemplo 1:
Texto em IsiLanguage:

```
programa

	declare numero a, b.
	declare texto x, y.

	leia (a).
	leia (b).
	
	escreva("Digite x e y").
	leia (x).
	leia (y).
	
	
	enquanto (a < b){
		a := a + 3.
	}
	
	se (x mesmo_texto y) entao{
	 	escreva ("Mesmo texto").
	} senao {
		escreva ("textos diferentes").
	}
	 

fimprog.
```

Resultado (em Python):

```python
# -*- coding: utf-8 -*-
a = float(input())

b = float(input())

print("Digite x e y")
x = input()

y = input()

while (a<b):
	a = a+3

if (x==y):
	a = a+1
	print("Mesmo texto")
else:
	print("Textos diferentes")
```

### Exemplo 2:
Texto em IsiLanguage:

```
programa
	
	declare texto x, y.	
	x := "Hello world".
	
	se (x dif_texto "Hello hello") entao{
		leia (y).
	}
		
		
fimprog.
```

Resultado (em Python):

```python
# -*- coding: utf-8 -*-
x = "Hello world"
if (x!="Hello hello"):
	y = input()
```




