package org.xcolab.client.tracking.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.tracking.pojo.tables.pojos.BalloonLink;

import java.sql.Timestamp;

@JsonDeserialize(as = BalloonLink.class)
public interface IBalloonLink {

    String getUuid();

    void setUuid(String uuid);

    String getTargetUrl();

    void setTargetUrl(String targetUrl);

    Integer getVisits();

    void setVisits(Integer visits);

    String getBalloonUserUuid();

    void setBalloonUserUuid(String balloonUserUuid);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
