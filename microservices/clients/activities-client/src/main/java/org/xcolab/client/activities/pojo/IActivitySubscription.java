package org.xcolab.client.activities.pojo;

import java.sql.Timestamp;

public interface IActivitySubscription {

    Long getId();

    void setId(Long id);

    Long getReceiverUserId();

    void setReceiverUserId(Long receiverUserId);

    String getActivityCategory();

    void setActivityCategory(String activityCategory);

    Long getCategoryId();

    void setCategoryId(Long categoryId);

    Long getClassNameId();

    void setClassNameId(Long classNameId);

    Long getClassPk();

    void setClassPk(Long classPk);

    Integer getAutomaticSubscriptionCounter();

    void setAutomaticSubscriptionCounter(Integer automaticSubscriptionCounter);

    String getExtraData();

    void setExtraData(String extraData);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);
}
