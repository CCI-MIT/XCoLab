package org.xcolab.client.moderation.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public interface IAggregatedReport extends Serializable {

    Long getReporterUserId();

    void setReporterUserId(Long reporterUserId);

    String getTargetType();

    void setTargetType(String targetType);

    Long getTargetId();

    void setTargetId(Long targetId);

    String getReason();

    void setReason(String reason);

    String getComment();

    void setComment(String comment);

    Integer getAggregatedWeight();

    void setAggregatedWeight(Integer aggregatedWeight);

    Long getTargetAdditionalId();

    void setTargetAdditionalId(Long targetAdditionalId);

    Timestamp getFirstReportDate();

    void setFirstReportDate(Timestamp firstReportDate);

    Timestamp getLastReportDate();

    void setLastReportDate(Timestamp lastReportDate);

    Integer getCount();

    void setCount(Integer count);

    Long getFirstReportId();

    void setFirstReportId(Long firstReportId);
}
