package org.xcolab.service.members.wrappers;

import org.xcolab.model.tables.pojos.Message;

import java.sql.Timestamp;

public class MessageReceived extends Message {

	private Boolean opened;
	private Boolean archived;

	public MessageReceived() {}

	public MessageReceived(Message value, Boolean opened, Boolean archived) {
        super(value);
		this.opened = opened;
		this.archived = archived;
	}

	public MessageReceived(
			Long messageid,
			Long fromid,
			Long repliesto,
			Long threadid,
			Timestamp createdate,
			String subject,
			String content,
			Boolean opened,
            Boolean archived) {
		super(messageid, fromid, repliesto, threadid, createdate, subject, content);
		this.opened = opened;
		this.archived = archived;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
}
