package org.xcolab.utils.sitemonitor.config;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class CheckerMapping {

	@Attribute
	private String checker;

	@ElementList(entry="url", inline=true)
	private List<String> url;
	
	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}

	
	
}
