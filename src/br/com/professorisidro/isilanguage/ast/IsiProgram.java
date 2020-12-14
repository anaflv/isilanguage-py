package br.com.professorisidro.isilanguage.ast;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;

public class IsiProgram {
	private IsiSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;


	public void generatePythonTarget() {
		System.out.println("Generating Python Target");
		
		StringBuilder str = new StringBuilder();		
		str.append("# -*- coding: utf-8 -*-\n");

		
		for (AbstractCommand command: comandos) {
			str.append(command.generatePythonCode()+"\n");
		}
		
		try {
			FileWriter fr = new FileWriter(new File("main.py"));
			fr.write(str.toString());
			fr.close() ; 

			System.out.println("Done");
		}  
		
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public IsiSymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(IsiSymbolTable varTable) {
		this.varTable = varTable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

}
