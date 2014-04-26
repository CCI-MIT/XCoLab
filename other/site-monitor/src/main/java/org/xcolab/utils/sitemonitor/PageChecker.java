package org.xcolab.utils.sitemonitor;

import org.apache.http.HttpResponse;

public interface PageChecker {
	public boolean isPageValid(String page, HttpResponse response);
	public void configure(String configuration, String message, SiteMonitor monitor);
	public String getMessage();
}
