package br.com.professorisidro.isilanguage.datastructures;

public class IsiVariable extends IsiSymbol {
	
	public static final int NUMBER=0;
	public static final int TEXT  =1;
	
	private int type;
	private String value;
	private boolean used;
	private boolean attributed;
	
	
	public IsiVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
		this.used = false;
		this.attributed = false;
	}
	
	
	public void printUsed() {
		if (!used){
			System.out.println("Warning! Variable " + name + " was declared but not not used.\n");

		}

	}
	
	
	public void variableUsed() {
		used = true;
	}
	
	public void variableAttributed() {
		attributed = true;
	}
	
	
	public boolean getAttributedValue() {
		return attributed;
	}
	
	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IsiVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
	}
	
	public String generateJavaCode() {
       String str;
       if (type == NUMBER) {
    	   str = "double ";
       }
       else {
    	   str = "String ";
       }
       return str + " "+super.name+";";
	}
	
	
	
	

}
