# isilanguage-py
Projeto final Compiladores - 12/2020

Exemplos:

IsiLanguage:
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


