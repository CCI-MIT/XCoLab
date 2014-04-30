package org.xcolab.utils.sitemonitor.config;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;


public class SiteMonitorConfig {

	@ElementList(type=CheckerConfig.class)
	private List<CheckerConfig> checkers;
	
	@ElementList(entry="checkerMapping")
	private List<CheckerMapping> checkerMappings;

	@ElementList(type=CrawlConfig.class)
	private List<CrawlConfig> crawlerConfig;
	
	@Element(name="emailNotification")
	private EmailNotificationConfig emailNotificationConfig;
	
	public List<CheckerConfig> getCheckers() {
		return checkers;
	}
	public void setCheckers(List<CheckerConfig> checkers) {
		this.checkers = checkers;
	}
	public List<CheckerMapping> getCheckerMappings() {
		return checkerMappings;
	}
	public void setCheckerMappings(List<CheckerMapping> checkerMappings) {
		this.checkerMappings = checkerMappings;
	}
	public List<CrawlConfig> getCrawlerConfig() {
		return crawlerConfig;
	}
	public void setCrawlerConfig(List<CrawlConfig> crawlerConfig) {
		this.crawlerConfig = crawlerConfig;
	}
	public EmailNotificationConfig getEmailNotificationConfig() {
		return emailNotificationConfig;
	}
	public void setEmailNotificationConfig(
			EmailNotificationConfig emailNotificationConfig) {
		this.emailNotificationConfig = emailNotificationConfig;
	}

}
