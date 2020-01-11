package me.naman.todoapp.todoapp;

public class todoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public todoException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public todoException() {
		super();
	}
}