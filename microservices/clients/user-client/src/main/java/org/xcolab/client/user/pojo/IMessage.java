package org.xcolab.client.user.pojo;

import java.sql.Timestamp;

public interface IMessage {

    Long getId();

    void setId(Long id);

    Long getFromId();

    void setFromId(Long fromId);

    Long getRepliesTo();

    void setRepliesTo(Long repliesTo);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    String getSubject();

    void setSubject(String subject);

    String getContent();

    void setContent(String content);
}
