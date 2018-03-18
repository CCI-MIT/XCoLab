package org.xcolab.client.admin.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Notification implements Serializable {

    private static final long serialVersionUID = 17654;

    public static final TypeProvider<Notification> TYPES =
            new TypeProvider<>(Notification.class,
                    new ParameterizedTypeReference<List<Notification>>() {
                    });

    private long notificationId;
    private String notificationText;
    private Date beginTime;
    private Date endTime;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public boolean isExpired() {
        return endTime.after(new Date());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Notification)) {
            return false;
        }

        Notification that = (Notification) o;

        if (getNotificationId() != that.getNotificationId()) {
            return false;
        }
        if (getNotificationText() != null ? !getNotificationText()
                .equals(that.getNotificationText()) : that.getNotificationText() != null) {
            return false;
        }
        if (getBeginTime() != null ? !getBeginTime().equals(that.getBeginTime())
                : that.getBeginTime() != null) {
            return false;
        }
        return getEndTime() != null ? getEndTime().equals(that.getEndTime())
                : that.getEndTime() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getNotificationId() ^ (getNotificationId() >>> 32));
        result = 31 * result + (getNotificationText() != null ? getNotificationText().hashCode()
                : 0);
        result = 31 * result + (getBeginTime() != null ? getBeginTime().hashCode() : 0);
        result = 31 * result + (getEndTime() != null ? getEndTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Notification{" + "notificationId=" + notificationId + ", notificationText='"
                + notificationText + '\'' + ", beginTime=" + beginTime + ", endTime=" + endTime
                + '}';
    }
}
