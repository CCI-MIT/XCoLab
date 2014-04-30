package org.xcolab.utils.sitemonitor.config;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class CrawlConfig {
	@Element
	private String url;
	@ElementList
	private List<String> linkPatterns;
	@Element
	private int recursionLevel;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getLinkPatterns() {
		return linkPatterns;
	}
	public void setLinkPatterns(List<String> linkPatterns) {
		this.linkPatterns = linkPatterns;
	}
	public int getRecursionLevel() {
		return recursionLevel;
	}
	public void setRecursionLevel(int recursionLevel) {
		this.recursionLevel = recursionLevel;
	}
	
	
}
