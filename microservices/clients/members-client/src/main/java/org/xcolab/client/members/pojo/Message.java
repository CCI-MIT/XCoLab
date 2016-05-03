package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Serializable {

    private static final long serialVersionUID = 1208926668;

    private Long messageid;
    private Long fromid;
    private Long repliesto;
    private Timestamp createdate;
    private String subject;
    private String content;
    private Boolean opened;
    private Boolean archived;

    public Message() {
    }

    public Message(Message value) {
        this.messageid = value.messageid;
        this.fromid = value.fromid;
        this.repliesto = value.repliesto;
        this.createdate = value.createdate;
        this.subject = value.subject;
        this.content = value.content;
    }

    public Message(Message value, Boolean opened, Boolean archived) {
        this(value);
        this.opened = opened;
        this.archived = archived;
    }

    public Message(
            Long messageid,
            Long fromid,
            Long repliesto,
            Timestamp createdate,
            String subject,
            String content,
            Boolean opened,
            Boolean archived) {
        this.messageid = messageid;
        this.fromid = fromid;
        this.repliesto = repliesto;
        this.createdate = createdate;
        this.subject = subject;
        this.content = content;
        this.opened = opened;
        this.archived = archived;
    }

    public Long getMessageId() {
        return this.messageid;
    }

    public void setMessageId(Long messageid) {
        this.messageid = messageid;
    }

    public Long getFromId() {
        return this.fromid;
    }

    public void setFromId(Long fromid) {
        this.fromid = fromid;
    }

    public Long getRepliesTo() {
        return this.repliesto;
    }

    public void setRepliesTo(Long repliesto) {
        this.repliesto = repliesto;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Message (");

        sb.append(messageid);
        sb.append(", ").append(fromid);
        sb.append(", ").append(repliesto);
        sb.append(", ").append(createdate);
        sb.append(", ").append(subject);
        sb.append(", ").append(content);
        sb.append(", ").append(opened);
        sb.append(", ").append(archived);

        sb.append(")");
        return sb.toString();
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
}
