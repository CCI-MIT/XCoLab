package org.xcolab.utils.sitemonitor;

import org.xcolab.utils.sitemonitor.config.CrawlConfig;

public class UrlToCrawl {
	private final String url;
	private final int level;
	private final CrawlConfig crawlConfig;
	
	
	
	public UrlToCrawl(String url, int level, CrawlConfig crawlConfig) {
		super();
		this.url = url;
		this.level = level;
		this.crawlConfig = crawlConfig;
	}
	
	public String getUrl() {
		return url;
	}
	public int getLevel() {
		return level;
	}
	public CrawlConfig getCrawlConfig() {
		return crawlConfig;
	}

}
