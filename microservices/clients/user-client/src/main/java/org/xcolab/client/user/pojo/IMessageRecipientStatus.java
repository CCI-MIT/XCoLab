package org.xcolab.client.user.pojo;

public interface IMessageRecipientStatus {

    Long getId();

    void setId(Long id);

    Long getMessageId();

    void setMessageId(Long messageId);

    Long getUserId();

    void setUserId(Long userId);

    String getThreadId();

    void setThreadId(String threadId);

    Boolean isOpened();

    void setOpened(Boolean opened);

    Boolean isArchived();

    void setArchived(Boolean archived);
}
