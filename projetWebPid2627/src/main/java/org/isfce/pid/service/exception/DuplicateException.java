package org.isfce.pid.service.exception;

public class DuplicateException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String champ;

	public DuplicateException(String msg, String champ) {
		super(msg);
		this.champ = champ;
	}

	public String getChamp() {
		return champ;
	}
}
