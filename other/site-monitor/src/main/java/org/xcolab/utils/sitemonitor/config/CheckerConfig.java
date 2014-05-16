package org.xcolab.utils.sitemonitor.config;

import org.simpleframework.xml.Element;

public class CheckerConfig {
	@Element
	private String name;
	@Element(name="class")
	private String clasz;
	@Element
	private String configuration;
	@Element
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClasz() {
		return clasz;
	}
	public void setClasz(String clasz) {
		this.clasz = clasz;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

}
