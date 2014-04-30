package org.xcolab.utils.sitemonitor.checkers;

import org.xcolab.utils.sitemonitor.PageChecker;
import org.xcolab.utils.sitemonitor.SiteMonitor;

public abstract class BasePageChecker implements PageChecker {
	private String message;
	protected SiteMonitor monitor;


	public void configure(String configuration, String message, SiteMonitor monitor) {
		this.message = message;
		this.monitor = monitor;
		configure(configuration);

	}

	public String getMessage() {
		return message;
	}

	protected abstract void configure(String configuration);
}
