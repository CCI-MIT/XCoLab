package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Message implements Serializable {

    private static final long serialVersionUID = 1208926668;

    public static final TypeProvider<Message> TYPES =
            new TypeProvider<>(Message.class, new ParameterizedTypeReference<List<Message>>() {});

    private Long messageId;
    private Long fromId;
    private Long repliesTo;
    private Timestamp createDate;
    private String subject;
    private String content;
    private Boolean opened;
    private Boolean archived;

    public Message() {
    }

    public Message(Message value) {
        this.messageId = value.messageId;
        this.fromId = value.fromId;
        this.repliesTo = value.repliesTo;
        this.createDate = value.createDate;
        this.subject = value.subject;
        this.content = value.content;
    }

    public Message(Message value, Boolean opened, Boolean archived) {
        this(value);
        this.opened = opened;
        this.archived = archived;
    }

    public Message(Long messageId, Long fromId, Long repliesTo, Timestamp createDate,
            String subject, String content, Boolean opened, Boolean archived) {
        this.messageId = messageId;
        this.fromId = fromId;
        this.repliesTo = repliesTo;
        this.createDate = createDate;
        this.subject = subject;
        this.content = content;
        this.opened = opened;
        this.archived = archived;
    }

    public Long getMessageId() {
        return this.messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getFromId() {
        return this.fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getRepliesTo() {
        return this.repliesTo;
    }

    public void setRepliesTo(Long repliesTo) {
        this.repliesTo = repliesTo;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "Message (" + messageId +
                ", " + fromId +
                ", " + repliesTo +
                ", " + createDate +
                ", " + subject +
                ", " + content +
                ", " + opened +
                ", " + archived +
                ")";
    }
}
