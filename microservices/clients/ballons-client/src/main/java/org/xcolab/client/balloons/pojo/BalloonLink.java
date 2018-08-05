package org.xcolab.client.balloons.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BalloonLink implements Serializable {

    private static final long serialVersionUID = -979073513;

    public static final TypeProvider<BalloonLink> TYPES =
            new TypeProvider<>(BalloonLink.class,
                    new ParameterizedTypeReference<List<BalloonLink>>() {
                    });

    private String    uuid_;
    private String    targeturl;
    private Integer   visits;
    private String    balloonuseruuid;
    private Timestamp createdAt;

    public BalloonLink() {}

    public BalloonLink(BalloonLink value) {
        this.uuid_ = value.uuid_;
        this.targeturl = value.targeturl;
        this.visits = value.visits;
        this.balloonuseruuid = value.balloonuseruuid;
        this.createdAt = value.createdAt;
    }

    public BalloonLink(
        String    uuid_,
        String    targeturl,
        Integer   visits,
        String    balloonuseruuid,
        Timestamp createdAt
    ) {
        this.uuid_ = uuid_;
        this.targeturl = targeturl;
        this.visits = visits;
        this.balloonuseruuid = balloonuseruuid;
        this.createdAt = createdAt;
    }

    public String getUuid_() {
        return this.uuid_;
    }

    public void setUuid_(String uuid_) {
        this.uuid_ = uuid_;
    }

    public String getTargetUrl() {
        return this.targeturl;
    }

    public void setTargetUrl(String targeturl) {
        this.targeturl = targeturl;
    }

    public Integer getVisits() {
        return this.visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public String getBalloonUserUuid() {
        return this.balloonuseruuid;
    }

    public void setBalloonUserUuid(String balloonuseruuid) {
        this.balloonuseruuid = balloonuseruuid;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        String sb =
                "BalloonLink (" + uuid_ + ", " + targeturl + ", " + visits + ", " + balloonuseruuid
                        + ", " + createdAt + ")";

        return sb;
    }
}
