// Generated from IsiLang.g4 by ANTLR 4.7.1
package br.com.professorisidro.isilanguage.parser;

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, PONTO=12, AP=13, FP=14, OP=15, ATTR=16, VIR=17, ACH=18, 
		FCH=19, OPREL=20, ID=21, NUMBER=22, TEXTO=23, OPTEXTO=24, WS=25;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdenquanto = 10, RULE_condition = 11, RULE_expr = 12, 
		RULE_termo = 13, RULE_expr_texto = 14;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdenquanto", "condition", "expr", "termo", 
		"expr_texto"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'declare'", "'numero'", "'texto'", 
		"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", "'.'", 
		"'('", "')'", null, "':='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"PONTO", "AP", "FP", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", 
		"NUMBER", "TEXTO", "OPTEXTO", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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
		
		
		

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T__0);

					 tabCount = 0;
				
			setState(32);
			decl();
			setState(33);
			bloco();
			setState(34);
			match(T__1);
			  program.setVarTable(symbolTable);
			   	   program.setComandos(stack.pop());
			   	   verificaVariaveisUsadas();
			           	 
			     
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				declaravar();
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode PONTO() { return getToken(IsiLangParser.PONTO, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(T__2);
			setState(43);
			tipo();
			setState(44);
			match(ID);

				                  _varName = _input.LT(-1).getText();
				                  _varValue = null;
				                  symbol = new IsiVariable(_varName, _tipo, _varValue);
				                  if (!symbolTable.exists(_varName)){
				                     symbolTable.add(symbol);	
				                  }
				                  else{
				                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
				                  }
			                    
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(46);
				match(VIR);
				setState(47);
				match(ID);

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
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(T__3);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(T__4);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>(); 
				        stack.push(curThread);  
			          
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(63);
				cmd();
				}
				}
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdenquantoContext cmdenquanto() {
			return getRuleContext(CmdenquantoContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				cmdleitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				cmdattrib();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				cmdselecao();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 5);
				{
				setState(72);
				cmdenquanto();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode PONTO() { return getToken(IsiLangParser.PONTO, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__5);
			setState(76);
			match(AP);
			setState(77);
			match(ID);

			         	verificaID(_input.LT(-1).getText());
			         	_readID = _input.LT(-1).getText();
			         	variavelAttribuida(_readID);
			         	
			            
			setState(79);
			match(FP);
			setState(80);
			match(PONTO);

			              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
			              	CommandLeitura cmd = new CommandLeitura(_readID, var, tabCount);
			              	stack.peek().add(cmd);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode PONTO() { return getToken(IsiLangParser.PONTO, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLangParser.TEXTO, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__6);
			setState(84);
			match(AP);
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(85);
				match(ID);

					     	  verificaID(_input.LT(-1).getText());
					     	  verificaAtribuida(_input.LT(-1).getText());
					          _writeID = _input.LT(-1).getText();
							  CommandEscrita cmd = new CommandEscrita(_writeID, null, tabCount);
					   	  	  stack.peek().add(cmd);
				         
				}
				break;
			case TEXTO:
				{
				setState(87);
				match(TEXTO);
				  
				       	  String _textoImprimir = _input.LT(-1).getText();
						  CommandEscrita cmd = new CommandEscrita(null, _textoImprimir, tabCount);
				   	  	  stack.peek().add(cmd);
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(91);
			match(FP);
			setState(92);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public TerminalNode PONTO() { return getToken(IsiLangParser.PONTO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_textoContext expr_texto() {
			return getRuleContext(Expr_textoContext.class,0);
		}
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(ID);

					 verificaID(_input.LT(-1).getText());
			        _exprID = _input.LT(-1).getText();
			        variavelUsada(_input.LT(-1).getText());
			        variavelAttribuida(_input.LT(-1).getText());
				                  
			   
			setState(96);
			match(ATTR);
			 _exprContent = ""; 
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case NUMBER:
				{
				setState(98);
				expr();

				                   	
				           	IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
				           	if (var.getType() != IsiVariable.NUMBER){
				            			
								throw new IsiSemanticException("Cannot attribute number to text " + _exprID);
				            }
				           	
				         
				}
				break;
			case TEXTO:
				{
				setState(101);
				expr_texto();

							IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
							if (var.getType() != IsiVariable.TEXT){
							throw new IsiSemanticException("Cannot attribute text to number " + _exprID);
						}
				           	
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(106);
			match(PONTO);

			               	 
							 IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
			               	 var.setValue(_exprContent);
			               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent, tabCount);
			               	 stack.peek().add(cmd);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__7);
			setState(110);
			condition();
			setState(111);
			match(T__8);
			setState(112);
			match(ACH);
			 
			                    	tabCount++;
			                    	curThread = new ArrayList<AbstractCommand>(); 
			                      	stack.push(curThread);
			                      
			     
			setState(115); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(114);
				cmd();
				}
				}
				setState(117); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
			setState(119);
			match(FCH);
			                       
			                       tabCount--;
			                       listaTrue = stack.pop();	                                            
			                       listaFalse = new ArrayList<AbstractCommand>();
			     
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(121);
				match(T__9);
				setState(122);
				match(ACH);

				 	                    tabCount ++;
				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(124);
					cmd();
					}
					}
					setState(127); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
				}
				setState(129);
				match(FCH);

				                   		tabCount--;                   		
				                   		listaFalse = stack.pop();	

				       	
				}
			}


						_exprDecision = conditionStack.pop();
						CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse, tabCount);
			            stack.peek().add(cmd);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdenquantoContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdenquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdenquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdenquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdenquanto(this);
		}
	}

	public final CmdenquantoContext cmdenquanto() throws RecognitionException {
		CmdenquantoContext _localctx = new CmdenquantoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdenquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__10);
			setState(137);
			condition();
			setState(138);
			match(ACH);
			 
			                      curThread = new ArrayList<AbstractCommand>(); 
			                      stack.push(curThread);
			                      tabCount++;
			    
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(140);
				cmd();
				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
			setState(145);
			match(FCH);

			                    
			                   listaTrue = stack.pop();	
			               	   tabCount --;
			               	   
			               	   _exprDecision = conditionStack.pop();
			                   CommandEnquanto cmd = new CommandEnquanto(_exprDecision, listaTrue, tabCount);
			               	   stack.peek().add(cmd);
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode OPTEXTO() { return getToken(IsiLangParser.OPTEXTO, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLangParser.TEXTO, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(AP);
			setState(149);
			match(ID);
			 
			                    	_decisionId = _input.LT(-1).getText();
			                    	verificaID(_decisionId);
			                    	verificaAtribuida(_decisionId);
			                    	 variavelUsada(_decisionId);
			                    	_exprDecision = _decisionId;

			                    
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPREL:
				{
				setState(151);
				match(OPREL);
				 
						
										String _op =  _input.LT(-1).getText();
										checkIfNumberOperation(_decisionId, _op);		
				                    	_exprDecision += _op;
				    
				}
				break;
			case OPTEXTO:
				{
				setState(153);
				match(OPTEXTO);

										String _op =  _input.LT(-1).getText();
										checkIfTextOperation(_decisionId, _op);					
										_exprDecision += traduzirOperador(_op);
										
				    	
				    	
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(163);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(157);
				match(ID);

				     
				            	  	String _decisionId1 = _decisionId;
				            	  	
				            	  	_decisionId  = _input.LT(-1).getText();                    	  	
				            	  	compareTypes(_decisionId1, _decisionId);
				            		         
				            	  	_exprDecision += _decisionId ;   
				            	  	verificaID(_decisionId);
				        			variavelUsada(_decisionId);	  	
				        			verificaAtribuida(_decisionId);
				            	  
				        
				}
				break;
			case NUMBER:
				{
				setState(159);
				match(NUMBER);

				                    		                    		
				    		_exprDecision += _input.LT(-1).getText();   
				    		checkIfNumber(_decisionId, _exprDecision);                    		
				    		                  		
				    	
				}
				break;
			case TEXTO:
				{
				setState(161);
				match(TEXTO);

				         	_exprDecision += _input.LT(-1).getText();   
				         	checkIfCompatible(_decisionId, _exprDecision, IsiVariable.TEXT);
				         	
				         
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

					conditionStack.push(_exprDecision);
				
			setState(166);
			match(FP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			termo();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(169);
				match(OP);
				 
							_exprContent += _input.LT(-1).getText();
						
				setState(171);
				termo();
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_termo);
		try {
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				match(ID);
				 
						verificaID(_input.LT(-1).getText());		
					    verificaAtribuida(_input.LT(-1).getText());
						_exprContent += _input.LT(-1).getText();
						variavelUsada(_input.LT(-1).getText());
					               
				                 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				match(NUMBER);

				      		_exprContent += _input.LT(-1).getText();
				     
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_textoContext extends ParserRuleContext {
		public TerminalNode TEXTO() { return getToken(IsiLangParser.TEXTO, 0); }
		public Expr_textoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_texto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr_texto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr_texto(this);
		}
	}

	public final Expr_textoContext expr_texto() throws RecognitionException {
		Expr_textoContext _localctx = new Expr_textoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr_texto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(TEXTO);

						  	_exprContent += _input.LT(-1).getText();
						  	
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00bd\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\6\3)\n\3\r\3\16\3*\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\64\n"+
		"\4\f\4\16\4\67\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5?\n\5\3\6\3\6\6\6C\n\6"+
		"\r\6\16\6D\3\7\3\7\3\7\3\7\3\7\5\7L\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\\\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\5\nk\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\6\13v\n\13\r\13\16\13w\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u0080\n\13"+
		"\r\13\16\13\u0081\3\13\3\13\3\13\5\13\u0087\n\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\6\f\u0090\n\f\r\f\16\f\u0091\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\5\r\u009e\n\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a6\n\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\7\16\u00af\n\16\f\16\16\16\u00b2\13\16\3\17\3\17"+
		"\3\17\3\17\5\17\u00b8\n\17\3\20\3\20\3\20\3\20\2\2\21\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36\2\2\2\u00c0\2 \3\2\2\2\4(\3\2\2\2\6,\3\2\2\2\b>"+
		"\3\2\2\2\n@\3\2\2\2\fK\3\2\2\2\16M\3\2\2\2\20U\3\2\2\2\22`\3\2\2\2\24"+
		"o\3\2\2\2\26\u008a\3\2\2\2\30\u0096\3\2\2\2\32\u00aa\3\2\2\2\34\u00b7"+
		"\3\2\2\2\36\u00b9\3\2\2\2 !\7\3\2\2!\"\b\2\1\2\"#\5\4\3\2#$\5\n\6\2$%"+
		"\7\4\2\2%&\b\2\1\2&\3\3\2\2\2\')\5\6\4\2(\'\3\2\2\2)*\3\2\2\2*(\3\2\2"+
		"\2*+\3\2\2\2+\5\3\2\2\2,-\7\5\2\2-.\5\b\5\2./\7\27\2\2/\65\b\4\1\2\60"+
		"\61\7\23\2\2\61\62\7\27\2\2\62\64\b\4\1\2\63\60\3\2\2\2\64\67\3\2\2\2"+
		"\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\289\7\16\2\29\7\3"+
		"\2\2\2:;\7\6\2\2;?\b\5\1\2<=\7\7\2\2=?\b\5\1\2>:\3\2\2\2><\3\2\2\2?\t"+
		"\3\2\2\2@B\b\6\1\2AC\5\f\7\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E"+
		"\13\3\2\2\2FL\5\16\b\2GL\5\20\t\2HL\5\22\n\2IL\5\24\13\2JL\5\26\f\2KF"+
		"\3\2\2\2KG\3\2\2\2KH\3\2\2\2KI\3\2\2\2KJ\3\2\2\2L\r\3\2\2\2MN\7\b\2\2"+
		"NO\7\17\2\2OP\7\27\2\2PQ\b\b\1\2QR\7\20\2\2RS\7\16\2\2ST\b\b\1\2T\17\3"+
		"\2\2\2UV\7\t\2\2V[\7\17\2\2WX\7\27\2\2X\\\b\t\1\2YZ\7\31\2\2Z\\\b\t\1"+
		"\2[W\3\2\2\2[Y\3\2\2\2\\]\3\2\2\2]^\7\20\2\2^_\7\16\2\2_\21\3\2\2\2`a"+
		"\7\27\2\2ab\b\n\1\2bc\7\22\2\2cj\b\n\1\2de\5\32\16\2ef\b\n\1\2fk\3\2\2"+
		"\2gh\5\36\20\2hi\b\n\1\2ik\3\2\2\2jd\3\2\2\2jg\3\2\2\2kl\3\2\2\2lm\7\16"+
		"\2\2mn\b\n\1\2n\23\3\2\2\2op\7\n\2\2pq\5\30\r\2qr\7\13\2\2rs\7\24\2\2"+
		"su\b\13\1\2tv\5\f\7\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2xy\3\2\2"+
		"\2yz\7\25\2\2z\u0086\b\13\1\2{|\7\f\2\2|}\7\24\2\2}\177\b\13\1\2~\u0080"+
		"\5\f\7\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\7\25\2\2\u0084\u0085\b\13\1\2"+
		"\u0085\u0087\3\2\2\2\u0086{\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3"+
		"\2\2\2\u0088\u0089\b\13\1\2\u0089\25\3\2\2\2\u008a\u008b\7\r\2\2\u008b"+
		"\u008c\5\30\r\2\u008c\u008d\7\24\2\2\u008d\u008f\b\f\1\2\u008e\u0090\5"+
		"\f\7\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\7\25\2\2\u0094\u0095\b"+
		"\f\1\2\u0095\27\3\2\2\2\u0096\u0097\7\17\2\2\u0097\u0098\7\27\2\2\u0098"+
		"\u009d\b\r\1\2\u0099\u009a\7\26\2\2\u009a\u009e\b\r\1\2\u009b\u009c\7"+
		"\32\2\2\u009c\u009e\b\r\1\2\u009d\u0099\3\2\2\2\u009d\u009b\3\2\2\2\u009e"+
		"\u00a5\3\2\2\2\u009f\u00a0\7\27\2\2\u00a0\u00a6\b\r\1\2\u00a1\u00a2\7"+
		"\30\2\2\u00a2\u00a6\b\r\1\2\u00a3\u00a4\7\31\2\2\u00a4\u00a6\b\r\1\2\u00a5"+
		"\u009f\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a8\b\r\1\2\u00a8\u00a9\7\20\2\2\u00a9\31\3\2\2\2\u00aa\u00b0"+
		"\5\34\17\2\u00ab\u00ac\7\21\2\2\u00ac\u00ad\b\16\1\2\u00ad\u00af\5\34"+
		"\17\2\u00ae\u00ab\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\33\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\7\27\2"+
		"\2\u00b4\u00b8\b\17\1\2\u00b5\u00b6\7\30\2\2\u00b6\u00b8\b\17\1\2\u00b7"+
		"\u00b3\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\35\3\2\2\2\u00b9\u00ba\7\31\2"+
		"\2\u00ba\u00bb\b\20\1\2\u00bb\37\3\2\2\2\21*\65>DK[jw\u0081\u0086\u0091"+
		"\u009d\u00a5\u00b0\u00b7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}