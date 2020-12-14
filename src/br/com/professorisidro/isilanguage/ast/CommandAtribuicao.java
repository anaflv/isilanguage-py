package br.com.professorisidro.isilanguage.ast;


public class CommandAtribuicao extends AbstractCommand{

	private String id;
	private String expr;
	private int tabCount;
	
	
	public CommandAtribuicao(String id, String expr, int tabCount) {
		this.id = id;
		this.expr = expr;
		this.tabCount = tabCount;
		
	}

	
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return id + " = "+expr+";";
	}
	
	@Override
	public String generatePythonCode() {
		// TODO Auto-generated method stub
		return "\t".repeat(tabCount) +  id + " = "+expr;
	}
	
	
	@Override
	public String toString() {
		return "CommandAtribuicao [id=" + id + ", expr=" + expr + "]";
	}
	

	
	
	

}
 