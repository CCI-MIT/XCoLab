package org.xcolab.utils.sitemonitor.checkers;

import org.apache.http.HttpResponse;

public class ResponseStatusChecker extends BasePageChecker {
	
	private int expectedStatusCode;
	
	public ResponseStatusChecker() {
	}

	public boolean isPageValid(String page, HttpResponse response) {
		return response.getStatusLine().getStatusCode() == expectedStatusCode;
	}

	protected void configure(String configuration) {
		this.expectedStatusCode = Integer.parseInt(configuration.trim());
	}

}
