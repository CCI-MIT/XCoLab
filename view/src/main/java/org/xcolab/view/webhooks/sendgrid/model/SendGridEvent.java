package org.xcolab.view.webhooks.sendgrid.model;

public class SendGridEvent {

    private String email;
    private int timestamp;
    private String smtp_id;
    private EventType event;
    private String category;
    private String sg_event_id;
    private String sg_message_id;
    private String response;
    private String attempt;
    private String userAgent;
    private String ip;
    private String url;
    private String reason;
    private String status;
    private int asm_group_id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSmtp_id() {
        return smtp_id;
    }

    public void setSmtp_id(String smtp_id) {
        this.smtp_id = smtp_id;
    }

    public EventType getEvent() {
        return event;
    }

    public void setEvent(EventType event) {
        this.event = event;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSg_event_id() {
        return sg_event_id;
    }

    public void setSg_event_id(String sg_event_id) {
        this.sg_event_id = sg_event_id;
    }

    public String getSg_message_id() {
        return sg_message_id;
    }

    public void setSg_message_id(String sg_message_id) {
        this.sg_message_id = sg_message_id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAsm_group_id() {
        return asm_group_id;
    }

    public void setAsm_group_id(int asm_group_id) {
        this.asm_group_id = asm_group_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SendGridEvent)) {
            return false;
        }

        SendGridEvent that = (SendGridEvent) o;

        if (getTimestamp() != that.getTimestamp()) {
            return false;
        }
        if (getAsm_group_id() != that.getAsm_group_id()) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) {
            return false;
        }
        if (getSmtp_id() != null ? !getSmtp_id().equals(that.getSmtp_id())
                : that.getSmtp_id() != null) {
            return false;
        }
        if (getEvent() != null ? !getEvent().equals(that.getEvent()) : that.getEvent() != null) {
            return false;
        }
        if (getCategory() != null ? !getCategory().equals(that.getCategory())
                : that.getCategory() != null) {
            return false;
        }
        if (getSg_event_id() != null ? !getSg_event_id().equals(that.getSg_event_id())
                : that.getSg_event_id() != null) {
            return false;
        }
        if (getSg_message_id() != null ? !getSg_message_id().equals(that.getSg_message_id())
                : that.getSg_message_id() != null) {
            return false;
        }
        if (getResponse() != null ? !getResponse().equals(that.getResponse())
                : that.getResponse() != null) {
            return false;
        }
        if (getAttempt() != null ? !getAttempt().equals(that.getAttempt())
                : that.getAttempt() != null) {
            return false;
        }
        if (getUserAgent() != null ? !getUserAgent().equals(that.getUserAgent())
                : that.getUserAgent() != null) {
            return false;
        }
        if (getIp() != null ? !getIp().equals(that.getIp()) : that.getIp() != null) {
            return false;
        }
        if (getUrl() != null ? !getUrl().equals(that.getUrl()) : that.getUrl() != null) {
            return false;
        }
        if (getReason() != null ? !getReason().equals(that.getReason())
                : that.getReason() != null) {
            return false;
        }
        return getStatus() != null ? getStatus().equals(that.getStatus())
                : that.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + getTimestamp();
        result = 31 * result + (getSmtp_id() != null ? getSmtp_id().hashCode() : 0);
        result = 31 * result + (getEvent() != null ? getEvent().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getSg_event_id() != null ? getSg_event_id().hashCode() : 0);
        result = 31 * result + (getSg_message_id() != null ? getSg_message_id().hashCode() : 0);
        result = 31 * result + (getResponse() != null ? getResponse().hashCode() : 0);
        result = 31 * result + (getAttempt() != null ? getAttempt().hashCode() : 0);
        result = 31 * result + (getUserAgent() != null ? getUserAgent().hashCode() : 0);
        result = 31 * result + (getIp() != null ? getIp().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getReason() != null ? getReason().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + getAsm_group_id();
        return result;
    }

    @Override
    public String toString() {
        return "SendGridEvent{" + "email='" + email + '\'' + ", timestamp=" + timestamp
                + ", smtp_id='" + smtp_id + '\'' + ", event='" + event + '\'' + ", category="
                + category + ", sg_event_id='" + sg_event_id + '\'' + ", sg_message_id='"
                + sg_message_id + '\'' + ", response='" + response + '\'' + ", attempt='" + attempt
                + '\'' + ", userAgent='" + userAgent + '\'' + ", ip='" + ip + '\'' + ", url='" + url
                + '\'' + ", reason='" + reason + '\'' + ", status='" + status + '\''
                + ", asm_group_id=" + asm_group_id + '}';
    }
}
