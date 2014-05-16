package org.xcolab.utils.sitemonitor.checkers;

import java.util.regex.Pattern;

import org.apache.http.HttpResponse;

public class RegexMatchingChecker extends BasePageChecker {
	
	private Pattern pattern;
	
	public RegexMatchingChecker() {
	}

	public boolean isPageValid(String page, HttpResponse response) {
		return pattern.matcher(page).find();
	}

	protected void configure(String configuration) {
		this.pattern = Pattern.compile(configuration);
	}

}
