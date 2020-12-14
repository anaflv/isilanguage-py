package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandDecisao extends AbstractCommand {
 
	private String condition;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private int tabCount;
	
	public CommandDecisao(String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf, int tabCount) {
		this.condition = condition;
		this.listaTrue = lt;
		this.listaFalse = lf;
		this.tabCount = tabCount;
	}
	@Override
	public String generateJavaCode() {
		
		StringBuilder str = new StringBuilder();
		str.append("if ("+condition+") {\n");
		for (AbstractCommand cmd: listaTrue) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}");
		if (listaFalse.size() > 0) {
			str.append("else {\n");
			for (AbstractCommand cmd: listaFalse) {
				str.append(cmd.generateJavaCode());
			}
			str.append("}\n");
		
		}
		return str.toString();
	}
	@Override
	public String toString() {
		return "CommandDecisao [condition=" + condition + ", listaTrue=" + listaTrue + ", listaFalse=" + listaFalse
				+ "]";
	}
	@Override
	public String generatePythonCode() {
		
		
		StringBuilder str = new StringBuilder();
		str.append("\t".repeat(tabCount) + "if ("+condition+"):\n");
		for (AbstractCommand cmd: listaTrue) {
			str.append(cmd.generatePythonCode()+"\n");
		}
		
		
		if (listaFalse.size() > 0) {
			str.append("\t".repeat(tabCount) + "else:\n");
			for (AbstractCommand cmd: listaFalse) {
				str.append(cmd.generatePythonCode()+ "\n");
			}
		
		}
		return str.toString();
		
	}
	
	

}
