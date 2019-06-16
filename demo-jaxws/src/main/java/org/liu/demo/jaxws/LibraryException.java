package org.liu.demo.jaxws;

public class LibraryException extends Exception {

	private static final long serialVersionUID = 1L;
	private String detail;

	public LibraryException(String message, String detail) {
		super(message);
		this.detail = detail;
	}

	public String getDetail() {
		return detail;
	}

}