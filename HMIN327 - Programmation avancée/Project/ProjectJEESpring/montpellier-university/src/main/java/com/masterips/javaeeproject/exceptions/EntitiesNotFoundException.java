package com.masterips.javaeeproject.exceptions;

public class EntitiesNotFoundException extends RuntimeException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public EntitiesNotFoundException(String id) {
		super("Could not find id number: " + id);
  }

}
