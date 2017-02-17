package org.xcolab.client.balloons.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BalloonUserTracking implements Serializable {

    private static final long serialVersionUID = -1955846558;

    public static final TypeProvider<BalloonUserTracking> TYPES =
            new TypeProvider<>(BalloonUserTracking.class,
                    new ParameterizedTypeReference<List<BalloonUserTracking>>() {
                    });

    private String    uuid_;
    private String    email;
    private String    parent;
    private String    ip;
    private Timestamp createdate;
    private Timestamp registrationdate;
    private Timestamp formfileddate;
    private Long      userid;
    private Long      balloontextid;
    private String    referrer;
    private Double    latitude;
    private Double    longitude;
    private String    city;
    private String    country;
    private String    extradata;
    private String    balloonlinkuuid;
    private String    balloonlinkcontext;
    private String    useragent;

    public BalloonUserTracking() {}

    public BalloonUserTracking(BalloonUserTracking value) {
        this.uuid_ = value.uuid_;
        this.email = value.email;
        this.parent = value.parent;
        this.ip = value.ip;
        this.createdate = value.createdate;
        this.registrationdate = value.registrationdate;
        this.formfileddate = value.formfileddate;
        this.userid = value.userid;
        this.balloontextid = value.balloontextid;
        this.referrer = value.referrer;
        this.latitude = value.latitude;
        this.longitude = value.longitude;
        this.city = value.city;
        this.country = value.country;
        this.extradata = value.extradata;
        this.balloonlinkuuid = value.balloonlinkuuid;
        this.balloonlinkcontext = value.balloonlinkcontext;
        this.useragent = value.useragent;
    }

    public BalloonUserTracking(
        String    uuid_,
        String    email,
        String    parent,
        String    ip,
        Timestamp createdate,
        Timestamp registrationdate,
        Timestamp formfileddate,
        Long      userid,
        Long      balloontextid,
        String    referrer,
        Double    latitude,
        Double    longitude,
        String    city,
        String    country,
        String    extradata,
        String    balloonlinkuuid,
        String    balloonlinkcontext,
        String    useragent
    ) {
        this.uuid_ = uuid_;
        this.email = email;
        this.parent = parent;
        this.ip = ip;
        this.createdate = createdate;
        this.registrationdate = registrationdate;
        this.formfileddate = formfileddate;
        this.userid = userid;
        this.balloontextid = balloontextid;
        this.referrer = referrer;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.country = country;
        this.extradata = extradata;
        this.balloonlinkuuid = balloonlinkuuid;
        this.balloonlinkcontext = balloonlinkcontext;
        this.useragent = useragent;
    }

    public String getUuid_() {
        return this.uuid_;
    }

    public void setUuid_(String uuid_) {
        this.uuid_ = uuid_;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Timestamp getRegistrationDate() {
        return this.registrationdate;
    }

    public void setRegistrationDate(Timestamp registrationdate) {
        this.registrationdate = registrationdate;
    }

    public Timestamp getFormFiledDate() {
        return this.formfileddate;
    }

    public void setFormFiledDate(Timestamp formfileddate) {
        this.formfileddate = formfileddate;
    }

    public Long getUserId() {
        return this.userid;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public Long getBalloonTextId() {
        return this.balloontextid;
    }

    public void setBalloonTextId(Long balloontextid) {
        this.balloontextid = balloontextid;
    }

    public String getReferrer() {
        return this.referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExtraData() {
        return this.extradata;
    }

    public void setExtraData(String extradata) {
        this.extradata = extradata;
    }

    public String getBalloonLinkUuid() {
        return this.balloonlinkuuid;
    }

    public void setBalloonLinkUuid(String balloonlinkuuid) {
        this.balloonlinkuuid = balloonlinkuuid;
    }

    public String getBalloonLinkContext() {
        return this.balloonlinkcontext;
    }

    public void setBalloonLinkContext(String balloonlinkcontext) {
        this.balloonlinkcontext = balloonlinkcontext;
    }

    public String getUserAgent() {
        return this.useragent;
    }

    public void setUserAgent(String useragent) {
        this.useragent = useragent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BalloonUserTracking (");

        sb.append(uuid_);
        sb.append(", ").append(email);
        sb.append(", ").append(parent);
        sb.append(", ").append(ip);
        sb.append(", ").append(createdate);
        sb.append(", ").append(registrationdate);
        sb.append(", ").append(formfileddate);
        sb.append(", ").append(userid);
        sb.append(", ").append(balloontextid);
        sb.append(", ").append(referrer);
        sb.append(", ").append(latitude);
        sb.append(", ").append(longitude);
        sb.append(", ").append(city);
        sb.append(", ").append(country);
        sb.append(", ").append(extradata);
        sb.append(", ").append(balloonlinkuuid);
        sb.append(", ").append(balloonlinkcontext);
        sb.append(", ").append(useragent);

        sb.append(")");
        return sb.toString();
    }
}
