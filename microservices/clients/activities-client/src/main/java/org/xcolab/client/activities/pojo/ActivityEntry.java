package org.xcolab.client.activities.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.activities.enums.ActivityCategory;
import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class ActivityEntry implements Serializable {

    public static final TypeProvider<ActivityEntry> TYPES = new TypeProvider<>(ActivityEntry.class,
            new ParameterizedTypeReference<List<ActivityEntry>>() {});

    private static final long serialVersionUID = 489920719;

    private Long activityentryid;
    private Long memberid;
    private Timestamp createdate;
    private String activityCategory;
    private String activityType;
    private long categoryId;
    private Long additionalId;
    private Long primarytype;
    private Long secondarytype;
    private Long classprimarykey;
    private String extradata;

    public ActivityEntry() {}

    public ActivityEntry(ActivityEntry value) {
        this.activityentryid = value.activityentryid;
        this.memberid = value.memberid;
        this.createdate = value.createdate;
        this.primarytype = value.primarytype;
        this.secondarytype = value.secondarytype;
        this.classprimarykey = value.classprimarykey;
        this.extradata = value.extradata;
    }

    public Long getActivityEntryId() {
        return this.activityentryid;
    }

    public void setActivityEntryId(Long activityentryid) {
        this.activityentryid = activityentryid;
    }

    public Long getMemberId() {
        return this.memberid;
    }

    public void setMemberId(Long memberid) {
        this.memberid = memberid;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public String getActivityCategory() {
        return activityCategory;
    }

    @JsonIgnore
    public ActivityCategory getActivityCategoryEnum() {
        //TODO COLAB-2486: can't be null once fixed
        return activityCategory != null ? ActivityCategory.valueOf(activityCategory) : null;
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

    public Long getPrimaryType() {
        return this.primarytype;
    }

    public void setPrimaryType(Long primarytype) {
        this.primarytype = primarytype;
    }

    public Long getSecondaryType() {
        return this.secondarytype;
    }

    public void setSecondaryType(Long secondarytype) {
        this.secondarytype = secondarytype;
    }

    public Long getClassPrimaryKey() {
        return this.classprimarykey;
    }

    public void setClassPrimaryKey(Long classprimarykey) {
        this.classprimarykey = classprimarykey;
    }

    public String getExtraData() {
        return this.extradata;
    }

    public void setExtraData(String extradata) {
        this.extradata = extradata;
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
        return Objects.equals(activityentryid, that.activityentryid)
                && Objects.equals(memberid, that.memberid) && Objects.equals(createdate, that.createdate)
                && Objects.equals(getActivityCategory(), that.getActivityCategory())
                && Objects.equals(getActivityType(), that.getActivityType())
                && Objects.equals(categoryId, that.categoryId)
                && Objects.equals(additionalId, that.additionalId)
                && Objects.equals(primarytype, that.primarytype)
                && Objects.equals(secondarytype, that.secondarytype)
                && Objects.equals(classprimarykey, that.classprimarykey)
                && Objects.equals(extradata, that.extradata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityentryid, memberid, createdate, getActivityCategory(),
                getActivityType(), categoryId, additionalId, primarytype, secondarytype,
                classprimarykey, extradata);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("activityentryid", activityentryid)
                .append("memberid", memberid)
                .append("createdate", createdate)
                .append("activityCategory", activityCategory)
                .append("activityType", activityType)
                .append("categoryId", categoryId)
                .append("additionalId", additionalId)
                .append("primarytype", primarytype)
                .append("secondarytype", secondarytype)
                .append("classprimarykey", classprimarykey)
                .append("extradata", extradata)
                .toString();
    }
}
