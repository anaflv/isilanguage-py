package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommand {

	private ArrayList<AbstractCommand> listaTrue;
	private String condition;
	private int tabCount;
	
	public CommandEnquanto(String condition, ArrayList<AbstractCommand> lt, int tabCount) {

			this.condition = condition;
			this.listaTrue = lt;		
			this.tabCount = tabCount;
	}

	@Override
	public String generateJavaCode() {	

		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+"):\n");
		for (AbstractCommand cmd: listaTrue) {
			str.append("\t" + cmd.generatePythonCode() + "\n");
		}
		
		return null;
	}

	@Override
	public String generatePythonCode() {
		
		StringBuilder str = new StringBuilder();
		str.append("\t".repeat(tabCount) +  "while ("+condition+"):\n");
		for (AbstractCommand cmd: listaTrue) {
			str.append(cmd.generatePythonCode()+"\n");
		}
		
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandEnquanto[condition=" + condition + ", listaTrue=" + listaTrue 
				+ "]";
	}
	

}
