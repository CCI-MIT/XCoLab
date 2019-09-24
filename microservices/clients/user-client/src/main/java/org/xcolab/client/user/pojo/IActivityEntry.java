package org.xcolab.client.user.pojo;

import java.sql.Timestamp;

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
}
