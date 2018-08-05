package org.xcolab.client.activities.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.activities.enums.ActivityType;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class ActivityEntry implements Serializable {

    public static final TypeProvider<ActivityEntry> TYPES = new TypeProvider<>(ActivityEntry.class,
            new ParameterizedTypeReference<List<ActivityEntry>>() {});

    private static final long serialVersionUID = 489920719;

    private Long id;
    private Long userId;
    private Timestamp createdAt;
    private String activityCategory;
    private String activityType;
    private long categoryId;
    private Long additionalId;

    public ActivityEntry() {}

    public ActivityEntry(ActivityEntry value) {
        this.id = value.id;
        this.userId = value.userId;
        this.createdAt = value.createdAt;
        this.activityCategory = value.activityCategory;
        this.activityType = value.activityType;
        this.categoryId = value.categoryId;
        this.additionalId = value.additionalId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long activityentryid) {
        this.id = activityentryid;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long memberid) {
        this.userId = memberid;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdate) {
        this.createdAt = createdate;
    }

    public String getActivityCategory() {
        return activityCategory;
    }

    @JsonIgnore
    public ActivityCategory getActivityCategoryEnum() {
        //TODO COLAB-2486: once fixed, this can't be UNKNOWN
        return activityCategory != null ? ActivityCategory.valueOf(activityCategory)
                : ActivityCategory.UNKNOWN;
    }

    public void setActivityCategory(String activityCategory) {
        this.activityCategory = activityCategory;
    }

    public String getActivityType() {
        return activityType;
    }

    @JsonIgnore
    public ActivityType getActivityTypeEnum() {
        //TODO COLAB-2486: neither can be null once fixed
        if (activityCategory != null && activityType != null) {
            return getActivityCategoryEnum().getActivityType(activityType);
        }
        return null;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getAdditionalId() {
        return additionalId;
    }

    public void setAdditionalId(Long additionalId) {
        this.additionalId = additionalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivityEntry)) {
            return false;
        }
        ActivityEntry that = (ActivityEntry) o;
        return Objects.equals(id, that.id)
                && Objects.equals(userId, that.userId) && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(getActivityCategory(), that.getActivityCategory())
                && Objects.equals(getActivityType(), that.getActivityType())
                && Objects.equals(categoryId, that.categoryId)
                && Objects.equals(additionalId, that.additionalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, createdAt, getActivityCategory(),
                getActivityType(), categoryId, additionalId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                .append("userId", userId)
                .append("createdAt", createdAt)
                .append("activityCategory", activityCategory)
                .append("activityType", activityType)
                .append("categoryId", categoryId)
                .append("additionalId", additionalId)
                .toString();
    }
}
