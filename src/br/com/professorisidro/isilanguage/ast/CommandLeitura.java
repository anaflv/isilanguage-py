package br.com.professorisidro.isilanguage.ast;

import br.com.professorisidro.isilanguage.datastructures.IsiVariable;

public class CommandLeitura extends AbstractCommand {

	private String id;
	private IsiVariable var;
	private int tabCount;
	
	public CommandLeitura (String id, IsiVariable var, int tabCount) {
		this.id = id;
		this.var = var;
		this.tabCount = tabCount;
	}
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return id +"= _key." + (var.getType()==IsiVariable.NUMBER? "nextDouble();": "nextLine();");
	}
	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}
	@Override
	public String generatePythonCode() {

		return "\t".repeat(tabCount) +  id +" = " + (var.getType()==IsiVariable.NUMBER? "float(input())": "input()") + "\n";
		
	}

}
