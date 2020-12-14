# Projeto final Compiladores - 12/2020

Nesse projeto, foi construido um compilador, que traduz código da linguagem IsiLanguage para o Python.

Observações:
1. No Python, não há chaves e as apenas indentações são usadas para determinar o código que pertence a um bloco. Foi implementada a indentação correspondente aos blocos originais, no código em Isilanguage.
1. É possível implementar blocos aninhados, ou seja, um "enquanto" dentro de um "se", dentro de outro bloco "se". É também possível implementar um "se" com ou sem o "senão".
1. É possível comparar textos, com as operações "mesmo_texto" e "dif_texto".


## Exemplos:

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


