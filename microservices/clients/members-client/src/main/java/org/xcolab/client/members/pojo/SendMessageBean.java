package org.xcolab.client.members.pojo;

import java.io.Serializable;
import java.util.List;

public class SendMessageBean extends Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Long> recipientIds;
    private List<String> threadIds;

    public SendMessageBean() {
    }

    public SendMessageBean(Message message, List<Long> recipientIds) {
        super(message);
        this.recipientIds = recipientIds;
    }

    public List<Long> getRecipientIds() {
        return recipientIds;
    }

    public void setRecipientIds(List<Long> recipientIds) {
        this.recipientIds = recipientIds;
    }

    public List<String> getThreadIds() { return threadIds; }

    public void setThreadIds(List<String> threadIds) { this.threadIds = threadIds; }

}
