package org.xcolab.client.activities.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.ParameterizedTypeReference;

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
    private Long primarytype;
    private Long secondarytype;
    private Long classprimarykey;
    private String extradata;
    private String activityentryname;

    public ActivityEntry() {}

    public ActivityEntry(ActivityEntry value) {
        this.activityentryid = value.activityentryid;
        this.memberid = value.memberid;
        this.createdate = value.createdate;
        this.primarytype = value.primarytype;
        this.secondarytype = value.secondarytype;
        this.classprimarykey = value.classprimarykey;
        this.extradata = value.extradata;
        this.activityentryname = value.activityentryname;
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

    public String getActivityEntryName() {
        return this.activityentryname;
    }

    public void setActivityEntryName(String activityentryname) {
        this.activityentryname = activityentryname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityentryid, memberid, createdate, primarytype, secondarytype,
                classprimarykey, extradata, activityentryname);
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
        return Objects.equals(activityentryid, that.activityentryid) && Objects
                .equals(memberid, that.memberid) && Objects.equals(createdate, that.createdate)
                && Objects.equals(primarytype, that.primarytype) && Objects
                .equals(secondarytype, that.secondarytype) && Objects
                .equals(classprimarykey, that.classprimarykey) && Objects
                .equals(extradata, that.extradata) && Objects
                .equals(activityentryname, that.activityentryname);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("activityentryid", activityentryid)
                .append("memberid", memberid).append("createdate", createdate)
                .append("primarytype", primarytype).append("secondarytype", secondarytype)
                .append("classprimarykey", classprimarykey).append("extradata", extradata)
                .append("activityentryname", activityentryname).toString();
    }
}
