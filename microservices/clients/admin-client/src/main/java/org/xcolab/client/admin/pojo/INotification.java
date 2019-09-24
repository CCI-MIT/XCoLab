package org.xcolab.client.admin.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.admin.pojo.tables.pojos.Notification;

import java.util.Date;

@JsonDeserialize(as = Notification.class)
public interface INotification {

    Date getBeginTime();

    void setBeginTime(Date beginTime);

    Date getEndTime();

    void setEndTime(Date endTime);

    long getNotificationId();

    void setNotificationId(long notificationId);

    String getNotificationText();

    void setNotificationText(String notificationText);

    default boolean isExpired() {
        return getEndTime().after(new Date());
    }
}
