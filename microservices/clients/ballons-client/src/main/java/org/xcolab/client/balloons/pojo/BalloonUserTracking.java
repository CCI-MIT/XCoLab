package org.xcolab.client.balloons.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static org.xcolab.util.http.exceptions.ExceptionUtils.getOrNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BalloonUserTracking implements Serializable {

    private static final long serialVersionUID = -1955846558;

    public static final TypeProvider<BalloonUserTracking> TYPES =
            new TypeProvider<>(BalloonUserTracking.class,
                    new ParameterizedTypeReference<List<BalloonUserTracking>>() {});

    private String uuid_;
    private String email;
    private String parent;
    private String ip;
    private Timestamp createdAt;
    private Timestamp registrationdate;
    private Timestamp formfileddate;
    private Long userid;
    private Long balloontextid;
    private String referrer;
    private Double latitude;
    private Double longitude;
    private String city;
    private String country;
    private String extradata;
    private String balloonlinkuuid;
    private String balloonlinkcontext;
    private String useragent;

    public BalloonUserTracking() {}

    public BalloonUserTracking(BalloonUserTracking value) {
        this.uuid_ = value.uuid_;
        this.email = value.email;
        this.parent = value.parent;
        this.ip = value.ip;
        this.createdAt = value.createdAt;
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

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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

    public BalloonText getBalloonText() {
        return getOrNull(() -> BalloonsClient.getBalloonText(getBalloonTextId()));
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

    public void updateUserIdAndEmailIfEmpty(long memberId, String email) {
        final boolean isUserIdEmpty = getUserId() == null;
        if (isUserIdEmpty) {
            setUserId(memberId);
        }

        final boolean isEmailBlank = StringUtils.isBlank(getEmail());
        if (isEmailBlank) {
            setEmail(email);
        }

        if (isUserIdEmpty || isEmailBlank) {
            BalloonsClient.updateBalloonUserTracking(this);
        }
    }

    @JsonIgnore
    public BalloonLink getBalloonLink() {
        return getOrNull(() -> BalloonsClient.getLinkByBalloonUserTrackingUuid(getUuid_()));
    }

    @JsonIgnore
    public boolean isUsed() {
        return getParent() != null || getBalloonLink() != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BalloonUserTracking)) {
            return false;
        }
        BalloonUserTracking that = (BalloonUserTracking) o;
        return Objects.equals(getUuid_(), that.getUuid_())
                && Objects.equals(getEmail(), that.getEmail())
                && Objects.equals(getParent(), that.getParent())
                && Objects.equals(getIp(), that.getIp())
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(registrationdate, that.registrationdate)
                && Objects.equals(formfileddate, that.formfileddate)
                && Objects.equals(userid, that.userid)
                && Objects.equals(balloontextid, that.balloontextid)
                && Objects.equals(getReferrer(), that.getReferrer())
                && Objects.equals(getLatitude(), that.getLatitude())
                && Objects.equals(getLongitude(), that.getLongitude())
                && Objects.equals(getCity(), that.getCity())
                && Objects.equals(getCountry(), that.getCountry())
                && Objects.equals(extradata, that.extradata)
                && Objects.equals(balloonlinkuuid, that.balloonlinkuuid)
                && Objects.equals(balloonlinkcontext, that.balloonlinkcontext)
                && Objects.equals(useragent, that.useragent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid_(), getEmail(), getParent(), getIp(), createdAt,
                registrationdate, formfileddate, userid, balloontextid, getReferrer(),
                getLatitude(), getLongitude(), getCity(), getCountry(), extradata,
                balloonlinkuuid, balloonlinkcontext, useragent);
    }

    @Override
    public String toString() {
        return "BalloonUserTracking (" + uuid_ + ", " + email + ", " + parent + ", " + ip + ", "
                + createdAt + ", " + registrationdate + ", " + formfileddate + ", " + userid + ", "
                + balloontextid + ", " + referrer + ", " + latitude + ", " + longitude + ", " + city
                + ", " + country + ", " + extradata + ", " + balloonlinkuuid + ", "
                + balloonlinkcontext + ", " + useragent + ")";
    }
}
