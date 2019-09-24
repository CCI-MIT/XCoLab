package org.xcolab.client.user.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.client.user.pojo.tables.pojos.Message;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class MessageWrapper extends Message {

    private Boolean opened;
    private Boolean archived;
    private String threadId;

    public MessageWrapper() {
    }

    public MessageWrapper(MessageWrapper value) {
        this.setId(value.getId());
        this.setFromId(value.getFromId());
        this.setRepliesTo(value.getRepliesTo());
        this.setCreatedAt(value.getCreatedAt());
        this.setSubject(value.getSubject());
        this.setContent(value.getContent());
        this.opened = value.opened;
        this.archived = value.archived;
        this.threadId = value.threadId;
    }

    public MessageWrapper(MessageWrapper value, Boolean opened, Boolean archived, String threadId) {
        this(value);
        this.opened = opened;
        this.archived = archived;
        this.threadId = threadId;
    }

    public MessageWrapper(Long id, Long fromId, Long repliesTo, Timestamp createdAt,
            String subject, String content, Boolean opened, Boolean archived, String threadId) {
        this.setId(id);
        this.setFromId(fromId);
        this.setRepliesTo(repliesTo);
        this.setCreatedAt(createdAt);
        this.setSubject(subject);
        this.setContent(content);
        this.opened = opened;
        this.archived = archived;
        this.threadId = threadId;
    }


    @Override
    public String toString() {
        return "Message (" + getId() +
                ", " + getFromId() +
                ", " + getRepliesTo() +
                ", " + getCreatedAt() +
                ", " + getSubject() +
                ", " + getContent() +
                ", " + opened +
                ", " + archived +
                ", " + threadId +
                ")";
    }

    public Boolean getOpened() {
        return opened;
    }



    public Boolean getArchived() {
        return archived;
    }


    public String getThreadId() {
        return threadId;
    }


}
