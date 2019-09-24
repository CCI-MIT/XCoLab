package org.xcolab.client.activity.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.activity.pojo.tables.pojos.ActivityEntry;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.activities.enums.ActivityType;

import java.sql.Timestamp;

@JsonDeserialize(as = ActivityEntry.class)
public interface IActivityEntry {

    Long getId();

    void setId(Long id);

    Long getUserId();

    void setUserId(Long userId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    String getActivityCategory();

    void setActivityCategory(String activityCategory);

    String getActivityType();

    void setActivityType(String activityType);

    Long getCategoryId();

    void setCategoryId(Long categoryId);

    Long getAdditionalId();

    void setAdditionalId(Long additionalId);

    Long getPrimaryType();

    void setPrimaryType(Long primaryType);

    Long getSecondaryType();

    void setSecondaryType(Long secondaryType);

    Long getClassPrimaryKey();

    void setClassPrimaryKey(Long classPrimaryKey);

    String getExtraData();

    void setExtraData(String extraData);

    @JsonIgnore
    default ActivityCategory getActivityCategoryEnum() {
        //TODO COLAB-2486: once fixed, this can't be UNKNOWN
        String activityCategory = getActivityCategory();
        return activityCategory != null ? ActivityCategory.valueOf(activityCategory)
                : ActivityCategory.UNKNOWN;
    }

    @JsonIgnore
    default ActivityType getActivityTypeEnum() {
        //TODO COLAB-2486: neither can be null once fixed
        String activityCategory = getActivityCategory();
        String activityType = getActivityType();
        if (activityCategory != null && activityType != null) {
            return getActivityCategoryEnum().getActivityType(activityType);
        }
        return null;
    }
}
