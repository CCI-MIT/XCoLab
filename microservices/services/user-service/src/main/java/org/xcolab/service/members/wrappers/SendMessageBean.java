package org.xcolab.service.members.wrappers;

import org.xcolab.model.tables.pojos.Message;

import java.util.List;

public class SendMessageBean extends Message {

    private List<Long> recipientIds;

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
}
