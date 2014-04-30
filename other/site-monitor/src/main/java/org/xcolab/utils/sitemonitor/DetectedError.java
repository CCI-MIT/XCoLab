package org.xcolab.utils.sitemonitor;

public class DetectedError {
	private String checker;
	private String message;
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public DetectedError(String checker, String message) {
		super();
		this.checker = checker;
		this.message = message;
	}
	

}
