package org.xcolab.client.admin.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContestEmailTemplate implements Serializable {

	public static final TypeProvider<ContestEmailTemplate> TYPES =
			new TypeProvider<>(ContestEmailTemplate.class,
					new ParameterizedTypeReference<List<ContestEmailTemplate>>() {
					});

	private static final long serialVersionUID = -689051730;

	private String type_;
	private String subject;
	private String header;
	private String footer;

	public ContestEmailTemplate() {}

	public ContestEmailTemplate(ContestEmailTemplate value) {
		this.type_ = value.type_;
		this.subject = value.subject;
		this.header = value.header;
		this.footer = value.footer;
	}

	public ContestEmailTemplate(String type_, String subject, String header, String footer) {
		this.type_ = type_;
		this.subject = subject;
		this.header = header;
		this.footer = footer;
	}

	public String getType_() {
		return this.type_;
	}

	public void setType_(String type_) {
		this.type_ = type_;
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

		return "ContestEmailTemplate (" + type_ +
				", " + subject +
				", " + header +
				", " + footer +
				")";
	}
}
