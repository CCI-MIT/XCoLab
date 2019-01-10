package org.xcolab.client.admin.pojo;

import java.util.Date;

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
