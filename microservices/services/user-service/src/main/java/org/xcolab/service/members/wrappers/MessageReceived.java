package org.xcolab.service.members.wrappers;

import org.xcolab.client.user.pojo.Message;

import java.sql.Timestamp;

public class MessageReceived extends Message {

	private Boolean opened;
	private Boolean archived;
    private String threadId;

	public MessageReceived() {}

	public MessageReceived(Message value, Boolean opened, Boolean archived, String threadId) {
        super(value);
		this.opened = opened;
		this.archived = archived;
		this.threadId = threadId;
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

    public String getThreadId() { return threadId; }

    public void setThreadId(String threadId) { this.threadId = threadId; }
}
