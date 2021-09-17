package com.registration.employee.config;

public class ResourceExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceExistException(String message) {	
	      super(message);	
	}

}
