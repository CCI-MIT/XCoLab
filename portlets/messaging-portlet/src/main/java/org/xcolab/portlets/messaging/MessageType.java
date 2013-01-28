package org.xcolab.portlets.messaging;

import com.ext.portlet.messaging.MessageConstants;

public enum MessageType {
    INBOX(MessageConstants.INBOX),
    SENT(MessageConstants.SENT),
    ARCHIVED(MessageConstants.ARCHIVED);
    
    private final String typeStr;
    
    MessageType(String typeStr) {
        this.typeStr = typeStr;
    }
    
    public String getTypeStr() {
        return typeStr;
    }

}
