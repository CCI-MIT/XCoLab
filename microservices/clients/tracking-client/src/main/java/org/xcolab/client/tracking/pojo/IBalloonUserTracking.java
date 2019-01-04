package org.xcolab.client.tracking.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.tracking.pojo.tables.pojos.BalloonUserTracking;

import java.sql.Timestamp;

@JsonDeserialize(as = BalloonUserTracking.class)
public interface IBalloonUserTracking {

    String getUuid();

    void setUuid(String uuid);

    String getEmail();

    void setEmail(String email);

    String getParent();

    void setParent(String parent);

    String getIp();

    void setIp(String ip);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getRegistrationDate();

    void setRegistrationDate(Timestamp registrationDate);

    Timestamp getFormFiledDate();

    void setFormFiledDate(Timestamp formFiledDate);

    Long getUserId();

    void setUserId(Long userId);

    Long getBalloonTextId();

    void setBalloonTextId(Long balloonTextId);

    String getReferrer();

    void setReferrer(String referrer);

    Double getLatitude();

    void setLatitude(Double latitude);

    Double getLongitude();

    void setLongitude(Double longitude);

    String getCity();

    void setCity(String city);

    String getCountry();

    void setCountry(String country);

    String getExtraData();

    void setExtraData(String extraData);

    String getBalloonLinkUuid();

    void setBalloonLinkUuid(String balloonLinkUuid);

    String getBalloonLinkContext();

    void setBalloonLinkContext(String balloonLinkContext);

    String getUserAgent();

    void setUserAgent(String userAgent);
}
