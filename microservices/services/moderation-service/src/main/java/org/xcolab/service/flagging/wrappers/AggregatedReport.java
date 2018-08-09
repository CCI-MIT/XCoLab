package org.xcolab.service.flagging.wrappers;

import java.sql.Timestamp;

public class AggregatedReport {

    private Long firstReportId;
    private Long reporteruserId;
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

    public AggregatedReport(Long firstReportId, Long reporteruserId, String targetType,
            Long targetId,
            String reason, String comment, Integer aggregatedWeight) {
        this.firstReportId = firstReportId;
        this.reporteruserId = reporteruserId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.reason = reason;
        this.comment = comment;
        this.aggregatedWeight = aggregatedWeight;
    }

    public Long getReporteruserId() {
        return this.reporteruserId;
    }

    public void setReporteruserId(Long reporteruserId) {
        this.reporteruserId = reporteruserId;
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
