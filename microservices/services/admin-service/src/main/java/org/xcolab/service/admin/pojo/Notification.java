package org.xcolab.service.admin.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.Date;
import java.util.List;

public class Notification{

    private long notificationId;
    private String notificationText;


    private Date beginTime;
    private Date endTime;

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

    public boolean isExpired() {
            return endTime.before(new Date());
    }
}
