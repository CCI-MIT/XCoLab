package org.xcolab.utils.sitemonitor.config;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class EmailNotificationConfig {

	@ElementList(inline = true, entry = "to")
	private List<String> to;

	@Element
	private String from;

	@Element(required = true)
	private String smtphost;

	@Element(required = false)
	private String smtpuser;

	@Element(required = false)
	private String smtppassword;

	@Element(required = true)
	private String subject;

	@Element(required = false, name = "smtpport")
	private int port;

	@Element(required = false, name = "smtpusetsl")
	private boolean useTsl;

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSmtphost() {
		return smtphost;
	}

	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
	}

	public String getSmtpuser() {
		return smtpuser;
	}

	public void setSmtpuser(String smtpuser) {
		this.smtpuser = smtpuser;
	}

	public String getSmtppassword() {
		return smtppassword;
	}

	public void setSmtppassword(String smtppassword) {
		this.smtppassword = smtppassword;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isUseTsl() {
		return useTsl;
	}

	public void setUseTsl(boolean useTsl) {
		this.useTsl = useTsl;
	}

}
