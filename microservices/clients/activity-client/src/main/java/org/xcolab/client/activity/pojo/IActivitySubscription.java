package org.xcolab.client.activity.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.activity.pojo.tables.pojos.ActivitySubscription;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.sql.Timestamp;

@JsonDeserialize(as = ActivitySubscription.class)
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

    @JsonIgnore
    default ActivityCategory getActivityCategoryEnum() {
        //TODO COLAB-2486: once fixed, this can't be UNKNOWN
        String activityCategory = getActivityCategory();
        return activityCategory != null ? ActivityCategory.valueOf(activityCategory)
                : ActivityCategory.UNKNOWN;
    }
}
