package org.xcolab.client.admin.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailTemplate implements Serializable {

	public static final TypeProvider<EmailTemplate> TYPES =
			new TypeProvider<>(EmailTemplate.class,
					new ParameterizedTypeReference<List<EmailTemplate>>() {
					});

	private static final long serialVersionUID = -689051730;

	private String name;
	private String subject;
	private String header;
	private String footer;

	public EmailTemplate() {}

	public EmailTemplate(EmailTemplate value) {
		this.name = value.name;
		this.subject = value.subject;
		this.header = value.header;
		this.footer = value.footer;
	}

	public EmailTemplate(String name, String subject, String header, String footer) {
		this.name = name;
		this.subject = subject;
		this.header = header;
		this.footer = footer;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return this.footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	@Override
	public String toString() {

		return "ContestEmailTemplate (" + name +
				", " + subject +
				", " + header +
				", " + footer +
				")";
	}
}
