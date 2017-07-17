package org.xcolab.client.activities.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ActivityEntry implements Serializable {

    public static final TypeProvider<ActivityEntry> TYPES = new TypeProvider<>(ActivityEntry.class,
            new ParameterizedTypeReference<List<ActivityEntry>>() {
            });

    private static final long serialVersionUID = 489920719;

    private Long      activityentryid;
    private Long      memberid;
    private Timestamp createdate;
    private Long      primarytype;
    private Long      secondarytype;
    private Long      classprimarykey;
    private String    extradata;
    private String    activityentrytitle;
    private String    activityentrybody;
    private String    activityentryname;

    public ActivityEntry() {}

    public ActivityEntry(ActivityEntry value) {
        this.activityentryid = value.activityentryid;
        this.memberid = value.memberid;
        this.createdate = value.createdate;
        this.primarytype = value.primarytype;
        this.secondarytype = value.secondarytype;
        this.classprimarykey = value.classprimarykey;
        this.extradata = value.extradata;
        this.activityentrytitle = value.activityentrytitle;
        this.activityentrybody = value.activityentrybody;
        this.activityentryname = value.activityentryname;
    }

    public ActivityEntry(
        Long      activityentryid,
        Long      memberid,
        Timestamp createdate,
        Long      primarytype,
        Long      secondarytype,
        Long      classprimarykey,
        String    extradata,
        String    activityentrytitle,
        String    activityentrybody,
        String    activityentryname
    ) {
        this.activityentryid = activityentryid;
        this.memberid = memberid;
        this.createdate = createdate;
        this.primarytype = primarytype;
        this.secondarytype = secondarytype;
        this.classprimarykey = classprimarykey;
        this.extradata = extradata;
        this.activityentrytitle = activityentrytitle;
        this.activityentrybody = activityentrybody;
        this.activityentryname = activityentryname;
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

    public String getActivityEntryTitle() {
        return this.activityentrytitle;
    }

    public void setActivityEntryTitle(String activityentrytitle) {
        this.activityentrytitle = activityentrytitle;
    }

    public String getActivityEntryBody() {
        return this.activityentrybody;
    }

    public void setActivityEntryBody(String activityentrybody) {
        this.activityentrybody = activityentrybody;
    }

    public String getActivityEntryName() {
        return this.activityentryname;
    }

    public void setActivityEntryName(String activityentryname) {
        this.activityentryname = activityentryname;
    }

    @Override
    public String toString() {
        String sb = "ActivityEntry (" + activityentryid + ", " + memberid + ", " + createdate + ", "
                + primarytype + ", " + secondarytype + ", " + classprimarykey + ", " + extradata
                + ", " + activityentrytitle + ", " + activityentrybody + ", " + activityentryname
                + ")";

        return sb;
    }
}
