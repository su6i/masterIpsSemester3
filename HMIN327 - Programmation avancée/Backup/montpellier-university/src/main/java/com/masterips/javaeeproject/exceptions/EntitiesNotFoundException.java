package com.masterips.javaeeproject.exceptions;

public class EntitiesNotFoundException extends RuntimeException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public EntitiesNotFoundException(String numCelebrite) {
		super("Could not find Celebrite " + numCelebrite);
  }

}
