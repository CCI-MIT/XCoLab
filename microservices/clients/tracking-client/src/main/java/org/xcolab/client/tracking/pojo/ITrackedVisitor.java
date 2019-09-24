package org.xcolab.client.tracking.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.tracking.pojo.tables.pojos.TrackedVisitor;

import java.sql.Timestamp;

@JsonDeserialize(as = TrackedVisitor.class)
public interface ITrackedVisitor {

    String getUuid();

    void setUuid(String uuid);

    Long getUserId();

    void setUserId(Long userId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
