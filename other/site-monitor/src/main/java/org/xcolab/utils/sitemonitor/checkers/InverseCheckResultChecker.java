package org.xcolab.utils.sitemonitor.checkers;

import org.apache.http.HttpResponse;
import org.xcolab.utils.sitemonitor.PageChecker;

/**
 * Runs sub checker on a page and negates it's result so if result was "error"
 * then this checker will treat that page as valid. It can be used to check that
 * certain string can't be found on a page. 
 * 
 * @author janusz
 *
 */
public class InverseCheckResultChecker extends BasePageChecker {
	private PageChecker subChecker; 

	@Override
    public boolean isPageValid(String page, HttpResponse response) {
		return ! subChecker.isPageValid(page, response);
	}

	@Override
	protected void configure(String configuration) {
		subChecker = monitor.getChecker(configuration.trim());
		if (subChecker == null) {
			throw new RuntimeException("Can't find checker with name " + configuration);
		}

	}

}
