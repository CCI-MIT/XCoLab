package org.xcolab.service.members.wrappers;

import org.xcolab.model.tables.pojos.MessageImpl;

import java.util.List;

public class SendMessageBean extends MessageImpl {

    private List<Long> recipientIds;

    public SendMessageBean() {
    }

    public SendMessageBean(MessageImpl message, List<Long> recipientIds) {
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
