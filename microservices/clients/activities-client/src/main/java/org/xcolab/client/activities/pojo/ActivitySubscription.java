package org.xcolab.client.activities.pojo;

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

    private Long pk;
    private Long receiverId;
    private String activityCategory;
    private Long categoryId;
    private Long classnameid;
    private Long classpk;
    private Integer automaticSubscriptionCounter;
    private String extraData;
    private Timestamp createDate;
    private Timestamp modifiedDate;

    public ActivitySubscription() {}

    public ActivitySubscription(ActivitySubscription value) {
        this.pk = value.pk;
        this.receiverId = value.receiverId;
        this.classnameid = value.classnameid;
        this.classpk = value.classpk;
        this.automaticSubscriptionCounter = value.automaticSubscriptionCounter;
        this.extraData = value.extraData;
        this.createDate = value.createDate;
        this.modifiedDate = value.modifiedDate;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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

    public Long getClassnameid() {
        return classnameid;
    }

    public void setClassnameid(Long classnameid) {
        this.classnameid = classnameid;
    }

    public Long getClasspk() {
        return classpk;
    }

    public void setClasspk(Long classpk) {
        this.classpk = classpk;
    }

    public Integer getAutomaticSubscriptionCounter() {
        return automaticSubscriptionCounter;
    }

    public void setAutomaticSubscriptionCounter(Integer automaticSubscriptionCounter) {
        this.automaticSubscriptionCounter = automaticSubscriptionCounter;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
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
        return Objects.equals(getPk(), that.getPk())
                && Objects.equals(getReceiverId(), that.getReceiverId())
                && Objects.equals(getActivityCategory(), that.getActivityCategory())
                && Objects.equals(getCategoryId(), that.getCategoryId())
                && Objects.equals(getClassnameid(), that.getClassnameid())
                && Objects.equals(getClasspk(), that.getClasspk())
                && Objects.equals(getAutomaticSubscriptionCounter(),
                        that.getAutomaticSubscriptionCounter())
                && Objects.equals(getExtraData(), that.getExtraData())
                && Objects.equals(getCreateDate(), that.getCreateDate())
                && Objects.equals(getModifiedDate(), that.getModifiedDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPk(), getReceiverId(), getActivityCategory(), getCategoryId(),
                getClassnameid(), getClasspk(), getAutomaticSubscriptionCounter(), getExtraData(),
                getCreateDate(), getModifiedDate());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("pk", pk)
                .append("receiverId", receiverId)
                .append("activityCategory", activityCategory)
                .append("categoryId", categoryId)
                .append("classnameid", classnameid)
                .append("classpk", classpk)
                .append("automaticSubscriptionCounter", automaticSubscriptionCounter)
                .append("extraData", extraData)
                .append("createDate", createDate)
                .append("modifiedDate", modifiedDate)
                .toString();
    }
}
