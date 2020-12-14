package br.com.professorisidro.isilanguage.ast;

public class CommandEscrita extends AbstractCommand {

	private String id;
	private int tabCount;
	private String texto;
	
	public CommandEscrita(String id, String texto, int tabCount) {
		this.id = id;
		this.tabCount = tabCount;
		this.texto = texto;
		
	}
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return "System.out.println("+id+");";
	}
	@Override
	public String toString() {
		return "CommandEscrita [id=" + id + "]";
	}
	@Override
	public String generatePythonCode() {
		
		if (texto == null){
			return "\t".repeat(tabCount) +  "print("+ id + ")";
		}
		
		return "\t".repeat(tabCount) +  "print("+ texto + ")";
		
		
	}
	

}
