package org.xcolab.client.flagging.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class AggregatedReport implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final TypeProvider<AggregatedReport> TYPES = new TypeProvider<>(
            AggregatedReport.class, new ParameterizedTypeReference<List<AggregatedReport>>() {});

    private Long firstReportId;
    private Long reporterMemberId;
    private String targetType;
    private Long targetId;
    private Long targetAdditionalId;
    private String reason;
    private String comment;
    private Integer aggregatedWeight;
    private Integer count;
    private Timestamp firstReportDate;
    private Timestamp lastReportDate;

    public AggregatedReport() {
    }

    public AggregatedReport(Long firstReportId, Long reporterMemberId, String targetType, Long targetId,
            String reason, String comment, Integer aggregatedWeight) {
        this.firstReportId = firstReportId;
        this.reporterMemberId = reporterMemberId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.reason = reason;
        this.comment = comment;
        this.aggregatedWeight = aggregatedWeight;
    }

    public Long getReporterMemberId() {
        return this.reporterMemberId;
    }

    public void setReporterMemberId(Long reportermemberid) {
        this.reporterMemberId = reportermemberid;
    }

    public String getTargetType() {
        return this.targetType;
    }

    public void setTargetType(String targettype) {
        this.targetType = targettype;
    }

    public Long getTargetId() {
        return this.targetId;
    }

    public void setTargetId(Long targetid) {
        this.targetId = targetid;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getAggregatedWeight() {
        return this.aggregatedWeight;
    }

    public void setAggregatedWeight(Integer aggregatedWeight) {
        this.aggregatedWeight = aggregatedWeight;
    }

    public Long getTargetAdditionalId() {
        return targetAdditionalId;
    }

    public void setTargetAdditionalId(Long targetAdditionalId) {
        this.targetAdditionalId = targetAdditionalId;
    }

    public Timestamp getFirstReportDate() {
        return firstReportDate;
    }

    public void setFirstReportDate(Timestamp firstReportDate) {
        this.firstReportDate = firstReportDate;
    }

    public Timestamp getLastReportDate() {
        return lastReportDate;
    }

    public void setLastReportDate(Timestamp lastReportDate) {
        this.lastReportDate = lastReportDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getFirstReportId() {
        return firstReportId;
    }

    public void setFirstReportId(Long firstReportId) {
        this.firstReportId = firstReportId;
    }
}
