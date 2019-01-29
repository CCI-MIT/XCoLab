package org.xcolab.client.moderation.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.xcolab.client.moderation.pojo.tables.pojos.Report;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonDeserialize(as = Report.class)
public interface IReport extends Serializable {

    Long getId();

    void setId(Long id);

    Long getReporterUserId();

    void setReporterUserId(Long reporterUserId);

    String getTargetType();

    void setTargetType(String targetType);

    Long getTargetId();

    void setTargetId(Long targetId);

    Long getTargetAdditionalId();

    void setTargetAdditionalId(Long targetAdditionalId);

    String getReason();

    void setReason(String reason);

    String getComment();

    void setComment(String comment);

    Integer getWeight();

    void setWeight(Integer weight);

    String getManagerAction();

    void setManagerAction(String managerAction);

    Long getManagerUserId();

    void setManagerUserId(Long managerUserId);

    Timestamp getManagerActionDate();

    void setManagerActionDate(Timestamp managerActionDate);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
