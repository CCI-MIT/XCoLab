package org.xcolab.client.activities.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ActivitySubscription implements Serializable {

    public static final TypeProvider<ActivitySubscription> TYPES =
            new TypeProvider<>(ActivitySubscription.class,
            new ParameterizedTypeReference<List<ActivitySubscription>>() {
            });

    private static final long serialVersionUID = 1658857969;

    private Long      pk;
    private Long      classnameid;
    private Long      classpk;
    private Integer   type_;
    private Integer   automaticsubscriptioncounter;
    private String    extradata;
    private Long      receiverid;
    private Timestamp createdate;
    private Timestamp modifieddate;

    public ActivitySubscription() {}

    public ActivitySubscription(ActivitySubscription value) {
        this.pk = value.pk;
        this.classnameid = value.classnameid;
        this.classpk = value.classpk;
        this.type_ = value.type_;
        this.automaticsubscriptioncounter = value.automaticsubscriptioncounter;
        this.extradata = value.extradata;
        this.receiverid = value.receiverid;
        this.createdate = value.createdate;
        this.modifieddate = value.modifieddate;
    }

    public ActivitySubscription(
        Long      pk,
        Long      classnameid,
        Long      classpk,
        Integer   type_,
        Integer   automaticsubscriptioncounter,
        String    extradata,
        Long      receiverid,
        Timestamp createdate,
        Timestamp modifieddate
    ) {
        this.pk = pk;
        this.classnameid = classnameid;
        this.classpk = classpk;
        this.type_ = type_;
        this.automaticsubscriptioncounter = automaticsubscriptioncounter;
        this.extradata = extradata;
        this.receiverid = receiverid;
        this.createdate = createdate;
        this.modifieddate = modifieddate;
    }

    public Long getPk() {
        return this.pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Long getClassNameId() {
        return this.classnameid;
    }

    public void setClassNameId(Long classnameid) {
        this.classnameid = classnameid;
    }

    public Long getClassPK() {
        return this.classpk;
    }

    public void setClassPK(Long classpk) {
        this.classpk = classpk;
    }

    public Integer getType_() {
        return this.type_;
    }

    public void setType_(Integer type_) {
        this.type_ = type_;
    }

    public Integer getAutomaticSubscriptionCounter() {
        return this.automaticsubscriptioncounter;
    }

    public void setAutomaticSubscriptionCounter(Integer automaticsubscriptioncounter) {
        this.automaticsubscriptioncounter = automaticsubscriptioncounter;
    }

    public String getExtraData() {
        return this.extradata;
    }

    public void setExtraData(String extradata) {
        this.extradata = extradata;
    }

    public Long getReceiverId() {
        return this.receiverid;
    }

    public void setReceiverId(Long receiverid) {
        this.receiverid = receiverid;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Timestamp getModifiedDate() {
        return this.modifieddate;
    }

    public void setModifiedDate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ActivitySubscription (");

        sb.append(pk);
        sb.append(", ").append(classnameid);
        sb.append(", ").append(classpk);
        sb.append(", ").append(type_);
        sb.append(", ").append(automaticsubscriptioncounter);
        sb.append(", ").append(extradata);
        sb.append(", ").append(receiverid);
        sb.append(", ").append(createdate);
        sb.append(", ").append(modifieddate);

        sb.append(")");
        return sb.toString();
    }
}
