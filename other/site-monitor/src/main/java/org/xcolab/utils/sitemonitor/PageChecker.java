package org.xcolab.utils.sitemonitor;

import org.apache.http.HttpResponse;

public interface PageChecker {

	boolean isPageValid(String page, HttpResponse response);

	void configure(String configuration, String message, SiteMonitor monitor);

	String getMessage();
}
