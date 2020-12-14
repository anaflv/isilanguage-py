grammar IsiLang;

@header {
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.CommandLeitura;
	import br.com.professorisidro.isilanguage.ast.CommandEscrita;
	import br.com.professorisidro.isilanguage.ast.CommandAtribuicao;
	import br.com.professorisidro.isilanguage.ast.CommandDecisao;
	import br.com.professorisidro.isilanguage.ast.CommandEnquanto;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members {
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private Stack<String> conditionStack = new Stack<String>();
	
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;	
	private String _exprDecisionFinal;
	
	private String _decisionId;
	
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private int tabCount;
	
	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}
	
	
	public void variavelUsada(String id){

      	IsiVariable var = (IsiVariable)symbolTable.get(id);
      	var.variableUsed();
	}
	
	public void variavelAttribuida(String id){

      	IsiVariable var = (IsiVariable)symbolTable.get(id);
      	var.variableAttributed();
	}
	
	public void verificaAtribuida(String id){

      	IsiVariable var = (IsiVariable)symbolTable.get(id);
      	if (!var.getAttributedValue()){
      		throw new IsiSemanticException("Variable " + id + " not attributed.");
		
      	}

	}
	
	
	
	public void verificaVariaveisUsadas(){
		
		for (String _id : symbolTable.getAllIds()) {			
			IsiVariable var = (IsiVariable)symbolTable.get(_id);
			var.printUsed();
			
		}
		
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generatePythonTarget();
		
	}
	
	public void compareTypes(String _id1, String _id2){
		
		
		IsiVariable var1 = (IsiVariable)symbolTable.get(_id1);
		IsiVariable var2 = (IsiVariable)symbolTable.get(_id2);
	  	                	  	
		if (var1.getType() != var2.getType()){
			
			throw new IsiSemanticException("Incompatible types: " + _id1 + " and " + _id2 +  ".");
		}
	}
	
	public void checkIfCompatible(String _id, String value, int type){
		
		
		IsiVariable var = (IsiVariable)symbolTable.get(_id);
		if (type == IsiVariable.TEXT & var.getType() != IsiVariable.TEXT){
                    			
			throw new IsiSemanticException("Incompatible types: number " + _id + " and text " +  value + ".");
        }
        
        if (type == IsiVariable.NUMBER & var.getType() != IsiVariable.NUMBER){
                    			
			throw new IsiSemanticException("Incompatible types: text " + _id + " and number " +  value + ".");
        }
        
                    		
	}
	
	
	public void checkIfNumber(String _id, String num){
		
		//Ver se va variável é um numero, para que seja comparada com outro numero
		
		IsiVariable var = (IsiVariable)symbolTable.get(_id);
		if (var.getType() != IsiVariable.NUMBER){
                    			
			throw new IsiSemanticException("Incompatible types: text " + _id + " and number " +  num + ".");
        }
                    		
	}
	
	public void checkIfNumberOperation(String _id, String op){
		
		
		IsiVariable var = (IsiVariable)symbolTable.get(_id);
		if (var.getType() != IsiVariable.NUMBER){
                    			
			throw new IsiSemanticException("Cannot perform " + op + " operation on text " + _id);
        }
                    		
	}
	
	public void checkIfTextOperation(String _id, String op){
		
		IsiVariable var = (IsiVariable)symbolTable.get(_id);
		if (var.getType() != IsiVariable.TEXT){
                    			
			throw new IsiSemanticException("Cannot perform " + op + " operation on number " + _id);
        }
                    		
	}
	
	public String traduzirOperador(String _op){
		
		switch(_op) {
		  case "mesmo_texto":
		    return "==";
		    
		  case "dif_texto":
		    return "!=";
		    
		   default:
		   	return _op;
		}
		
	}
	
	
	
}

prog
:
	'programa'
	{
		 tabCount = 0;
	}

	decl bloco 'fimprog.'
	{  program.setVarTable(symbolTable);
   	   program.setComandos(stack.pop());
   	   verificaVariaveisUsadas();
           	 
     }

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
	{
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }

	(
		VIR ID
		{
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }

	)* PONTO
;

tipo
:
	'numero'
	{ _tipo = IsiVariable.NUMBER;  }

	| 'texto'
	{ _tipo = IsiVariable.TEXT;  }

;

bloco
:
{ curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }

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
	'leia' AP ID
	{
         	verificaID(_input.LT(-1).getText());
         	_readID = _input.LT(-1).getText();
         	variavelAttribuida(_readID);
         	
            }

	FP PONTO
	{
              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var, tabCount);
              	stack.peek().add(cmd);
              }

;

cmdescrita
:
	'escreva' AP
	(
		ID
		{
	     	  verificaID(_input.LT(-1).getText());
	     	  verificaAtribuida(_input.LT(-1).getText());
	          _writeID = _input.LT(-1).getText();
			  CommandEscrita cmd = new CommandEscrita(_writeID, null, tabCount);
	   	  	  stack.peek().add(cmd);
         }

		| TEXTO
		{  
       	  String _textoImprimir = _input.LT(-1).getText();
		  CommandEscrita cmd = new CommandEscrita(null, _textoImprimir, tabCount);
   	  	  stack.peek().add(cmd);
        }

	) FP PONTO
;

cmdattrib
:
	ID
	{
		 verificaID(_input.LT(-1).getText());
        _exprID = _input.LT(-1).getText();
        variavelUsada(_input.LT(-1).getText());
        variavelAttribuida(_input.LT(-1).getText());
	                  
   }

	ATTR
	{ _exprContent = ""; }

	(
		expr
		{
                   	
           	IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
           	if (var.getType() != IsiVariable.NUMBER){
            			
				throw new IsiSemanticException("Cannot attribute number to text " + _exprID);
            }
           	
         }

		| expr_texto
		{
			IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
			if (var.getType() != IsiVariable.TEXT){
			throw new IsiSemanticException("Cannot attribute text to number " + _exprID);
		}
           	
    }

	) PONTO
	{
               	 
				 IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
               	 var.setValue(_exprContent);
               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent, tabCount);
               	 stack.peek().add(cmd);
    }

;

cmdselecao
:
	'se' condition 'entao' ACH
	{ 
                    	tabCount++;
                    	curThread = new ArrayList<AbstractCommand>(); 
                      	stack.push(curThread);
                      
     }

	(
		cmd
	)+ FCH
	{                       
                       tabCount--;
                       listaTrue = stack.pop();	                                            
                       listaFalse = new ArrayList<AbstractCommand>();
     }

	(
		'senao' ACH
		{
 	                    tabCount ++;
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	 }

		(
			cmd+
		) FCH
		{
                   		tabCount--;                   		
                   		listaFalse = stack.pop();	

       	}

	)?
	{
			_exprDecision = conditionStack.pop();
			CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse, tabCount);
            stack.peek().add(cmd);
	}

;

cmdenquanto
:
	'enquanto' condition ACH
	{ 
                      curThread = new ArrayList<AbstractCommand>(); 
                      stack.push(curThread);
                      tabCount++;
    }

	(
		cmd
	)+ FCH
	{
                    
                   listaTrue = stack.pop();	
               	   tabCount --;
               	   
               	   _exprDecision = conditionStack.pop();
                   CommandEnquanto cmd = new CommandEnquanto(_exprDecision, listaTrue, tabCount);
               	   stack.peek().add(cmd);
                }

;

condition
:
	AP ID
	{ 
                    	_decisionId = _input.LT(-1).getText();
                    	verificaID(_decisionId);
                    	verificaAtribuida(_decisionId);
                    	 variavelUsada(_decisionId);
                    	_exprDecision = _decisionId;

                    }

	(
		OPREL
		{ 
		
						String _op =  _input.LT(-1).getText();
						checkIfNumberOperation(_decisionId, _op);		
                    	_exprDecision += _op;
    }

		| OPTEXTO
		{
						String _op =  _input.LT(-1).getText();
						checkIfTextOperation(_decisionId, _op);					
						_exprDecision += traduzirOperador(_op);
						
    	
    	}

	)
	(
		ID
		{
     
            	  	String _decisionId1 = _decisionId;
            	  	
            	  	_decisionId  = _input.LT(-1).getText();                    	  	
            	  	compareTypes(_decisionId1, _decisionId);
            		         
            	  	_exprDecision += _decisionId ;   
            	  	verificaID(_decisionId);
        			variavelUsada(_decisionId);	  	
        			verificaAtribuida(_decisionId);
            	  
        }

		| NUMBER
		{
                    		                    		
    		_exprDecision += _input.LT(-1).getText();   
    		checkIfNumber(_decisionId, _exprDecision);                    		
    		                  		
    	}

		| TEXTO
		{
         	_exprDecision += _input.LT(-1).getText();   
         	checkIfCompatible(_decisionId, _exprDecision, IsiVariable.TEXT);
         	
         }

	)
	{
		conditionStack.push(_exprDecision);
	}

	FP
;

expr
:
	termo
	(
		OP
		{ 
			_exprContent += _input.LT(-1).getText();
		}

		termo
	)*
;

termo
:
	ID
	{ 
		verificaID(_input.LT(-1).getText());		
	    verificaAtribuida(_input.LT(-1).getText());
		_exprContent += _input.LT(-1).getText();
		variavelUsada(_input.LT(-1).getText());
	               
                 }

	| NUMBER
	{
      		_exprContent += _input.LT(-1).getText();
     }

;

expr_texto
:
	TEXTO
	{
			  	_exprContent += _input.LT(-1).getText();
			  	
	}

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




