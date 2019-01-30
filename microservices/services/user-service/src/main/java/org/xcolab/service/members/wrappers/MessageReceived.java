package org.xcolab.service.members.wrappers;

import org.xcolab.client.user.pojo.wrapper.MessageWrapper;

public class MessageReceived extends MessageWrapper {

	private Boolean opened;
	private Boolean archived;
    private String threadId;

	public MessageReceived() {}

	public MessageReceived(MessageWrapper value, Boolean opened, Boolean archived, String threadId) {
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
