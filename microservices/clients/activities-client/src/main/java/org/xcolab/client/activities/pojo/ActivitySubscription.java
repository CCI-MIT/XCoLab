package org.xcolab.client.activities.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class ActivitySubscription implements Serializable {

    public static final TypeProvider<ActivitySubscription> TYPES =
            new TypeProvider<>(ActivitySubscription.class,
                    new ParameterizedTypeReference<List<ActivitySubscription>>() {});

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long receiverUserId;
    private String activityCategory;
    private Long categoryId;
    private Integer automaticSubscriptionCounter;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ActivitySubscription() {}

    public ActivitySubscription(ActivitySubscription value) {
        this.id = value.id;
        this.receiverUserId = value.receiverUserId;
        this.automaticSubscriptionCounter = value.automaticSubscriptionCounter;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
        this.activityCategory = value.activityCategory;
        this.categoryId = value.categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    @JsonIgnore
    public ActivityCategory getActivityCategoryEnum() {
        //TODO COLAB-2486: once fixed, this can't be UNKNOWN
        return activityCategory != null ? ActivityCategory.valueOf(activityCategory)
                : ActivityCategory.UNKNOWN;
    }

    public String getActivityCategory() {
        return activityCategory;
    }

    public void setActivityCategory(String activityCategory) {
        this.activityCategory = activityCategory;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getAutomaticSubscriptionCounter() {
        return automaticSubscriptionCounter;
    }

    public void setAutomaticSubscriptionCounter(Integer automaticSubscriptionCounter) {
        this.automaticSubscriptionCounter = automaticSubscriptionCounter;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivitySubscription)) {
            return false;
        }
        ActivitySubscription that = (ActivitySubscription) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getReceiverUserId(), that.getReceiverUserId())
                && Objects.equals(getActivityCategory(), that.getActivityCategory())
                && Objects.equals(getCategoryId(), that.getCategoryId())
                && Objects.equals(getAutomaticSubscriptionCounter(),
                        that.getAutomaticSubscriptionCounter())
                && Objects.equals(getCreatedAt(), that.getCreatedAt())
                && Objects.equals(getUpdatedAt(), that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReceiverUserId(), getActivityCategory(), getCategoryId(),
                getAutomaticSubscriptionCounter(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                .append("receiverUserId", receiverUserId)
                .append("activityCategory", activityCategory)
                .append("categoryId", categoryId)
                .append("automaticSubscriptionCounter", automaticSubscriptionCounter)
                .append("createdAt", createdAt)
                .append("updatedAt", updatedAt)
                .toString();
    }
}
