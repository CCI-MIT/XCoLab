package org.xcolab.client.user.pojo.wrapper;

import java.io.Serializable;
import java.util.List;

public class SendMessageWrapper extends MessageWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Long> recipientIds;
    private List<String> threadIds;

    public SendMessageWrapper() {
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
