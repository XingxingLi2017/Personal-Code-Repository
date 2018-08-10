package com.xing.exception;

public class StudentNotExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentNotExistException() {
		super();
	}
	public StudentNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StudentNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentNotExistException(String message) {
		super(message);
	}

	public StudentNotExistException(Throwable cause) {
		super(cause);
	}
}
