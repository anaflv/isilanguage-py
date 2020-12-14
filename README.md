# Projeto final Compiladores - 12/2020 

Nesse projeto, foi construido um compilador, que traduz código da linguagem IsiLanguage :dragon: para o Python :snake:.

Observações:
1. No Python, não há chaves e as apenas indentações são usadas para determinar o código que pertence a um bloco. Foi implementada a indentação correspondente aos blocos originais, no código em Isilanguage.
1. É possível implementar blocos aninhados, ou seja, um "enquanto" dentro de um "se", dentro de outro bloco "se". É também possível implementar um "se" com ou sem o "senão".
1. É possível comparar textos, com as operações "mesmo_texto" e "dif_texto".



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

## Checklist:

**1.** Possui 2 tipos de variáveis

Há dois tipos de variáveis na Isilanguage: texto e numero
```
programa

	declare numero a, b.
	declare texto x.
	
	a := 1.
	b := 3.6.
	
	x := "Texto exemplo".
	 

fimprog.
```


**2.** Possui a estrutura If.. else

Há a estrutura "se enquanto .. senão" no IsiLanguage.
```
programa
	declare numero a, b.	
	leia(a).
	leia(b).
	
	se (a > b) entao {
		escreva ("a maior")
	} senao { 
		escreva ("a menor")
	}
	
	se (a == b) entao {
		escreva ("a igual a b").
	}
fimprog.
```

**3.** 1a Estrutura de Repetição

Há a estrutura de repetição "enquanto" no Isilanguage :dragon:, similar ao while, em outras linguagens.

```
programa

	declare numero a, b.		
	leia(a).
	leia(b).
	
	enquanto (a < b){
		escreva (a).
		a := a + 3.		
		se (a == 2) entao {
			a := 9.
		}
	}

fimprog.
```

**5.** Verificar se Variável foi atribuída ou não

Se a variável não foi atribuída e tenta-se usar essa variável, há um erro semântico. A variável é considerada como atribuída também quando ela é lida (pelo comando leia()).

Quando se tenta executar o código:
```
programa
	declare numero a, b.
	b := 3.

	enquanto (a < b){
		a := a + 3.
	}
fimprog.
```

Há o erro:
```
Semantic error - Variable a not attributed.
```



**6.** Possui operações de Entrada e Saída

Há as operações leia() e escreva().

```
programa
	declare numero a, b.		
	leia(a).
	leia(b).
	escreva(a).
	escreva("hello world").
fimprog.
```

7. Aceita números decimais

O programa aceita números decimais.
```
programa
	declare numero a, b.
	a := 1.25.
	b := 7.843.
fimprog.
```

**8.** Verificar se variável foi declarada

Se a variável não foi declarada, há um erro semântico.

Se escrever:
```
programa
	
	enquanto (a < 1){	
		escreva(a).
	}

fimprog.
```

Há o erro: 
```
Semantic error - Symbol a not declared
```

**\9.** Verificar se variável declarada foi ou não usada

Se uma variável foi declarada mas não usada, há a impressão de um Warning.
Por exemplo:
```
programa	
	declare numero a, b, c.
	a := 1.
	b := 2.
fimprog.
```

No console:
```
Warning! Variable c was declared but not not used.
```



**10.** ITENS OPCIONAIS
- Comparação de tipos:
 - Não é possível atribuir um texto (como "hello") a uma variável numérica
 - Não é possivel comparar um texto e um número (por exemplo, "hello" > 4) 
 - Não é possível realizar operações numéricas em textos (como ">" e "!=")
 - Não é possível realizar operações de texto em números ("dif_texto" e "mesmo_texto") 
- É possível fazer vários blocos de se/senão e enquanto, um dentro do outro
 




## Gramática:

A gramática da linguagem IsiLanguage :dragon: possui a seguinte estrutura, nesse projeto:

```java
prog
:
	'programa' decl bloco 'fimprog.'
;

decl
:
	(declaravar)+
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



:christmas_tree:


