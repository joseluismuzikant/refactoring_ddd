package com.veritran.banking.domain.model;

public class Statement {
	
	private Statement() {
		super();
	}


	private  String text;
	
	public static Statement create(String headerLine, int amount, int balance) {
		Statement statement = new Statement();
		statement.text = ""+headerLine+", "+amount+" USD, balance: "+balance+" USD";
		return statement;
	}
	
	public String getText() {
		return new String(text);
	}

}
