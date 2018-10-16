package org.xcolab.client.tracking.pojo.tables.pojos;

import org.xcolab.client.tracking.pojo.IBalloonLink;

import java.sql.Timestamp;

public class BalloonLink implements IBalloonLink {

    private static final long serialVersionUID = 1605019719;

    private String uuid;
    private String targetUrl;
    private Integer visits;
    private String balloonUserUuid;
    private Timestamp createdAt;

    public BalloonLink() {}

    public BalloonLink(BalloonLink value) {
        this.uuid = value.uuid;
        this.targetUrl = value.targetUrl;
        this.visits = value.visits;
        this.balloonUserUuid = value.balloonUserUuid;
        this.createdAt = value.createdAt;
    }

    public BalloonLink(String uuid, String targetUrl, Integer visits, String balloonUserUuid,
            Timestamp createdAt) {
        this.uuid = uuid;
        this.targetUrl = targetUrl;
        this.visits = visits;
        this.balloonUserUuid = balloonUserUuid;
        this.createdAt = createdAt;
    }

    @Override
    public String getUuid() {
        return this.uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getTargetUrl() {
        return this.targetUrl;
    }

    @Override
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public Integer getVisits() {
        return this.visits;
    }

    @Override
    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    @Override
    public String getBalloonUserUuid() {
        return this.balloonUserUuid;
    }

    @Override
    public void setBalloonUserUuid(String balloonUserUuid) {
        this.balloonUserUuid = balloonUserUuid;
    }

    @Override
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        final BalloonLink other = (BalloonLink) obj;
        if (uuid == null) {
            if (other.uuid != null) { return false; }
        } else if (!uuid.equals(other.uuid)) { return false; }
        if (targetUrl == null) {
            if (other.targetUrl != null) { return false; }
        } else if (!targetUrl.equals(other.targetUrl)) { return false; }
        if (visits == null) {
            if (other.visits != null) { return false; }
        } else if (!visits.equals(other.visits)) { return false; }
        if (balloonUserUuid == null) {
            if (other.balloonUserUuid != null) { return false; }
        } else if (!balloonUserUuid.equals(other.balloonUserUuid)) { return false; }
        if (createdAt == null) {
            if (other.createdAt != null) { return false; }
        } else if (!createdAt.equals(other.createdAt)) { return false; }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        result = prime * result + ((targetUrl == null) ? 0 : targetUrl.hashCode());
        result = prime * result + ((visits == null) ? 0 : visits.hashCode());
        result = prime * result + ((balloonUserUuid == null) ? 0 : balloonUserUuid.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BalloonLink (");

        sb.append(uuid);
        sb.append(", ").append(targetUrl);
        sb.append(", ").append(visits);
        sb.append(", ").append(balloonUserUuid);
        sb.append(", ").append(createdAt);

        sb.append(")");
        return sb.toString();
    }
}
