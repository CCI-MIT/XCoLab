package org.xcolab.client.flagging.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Report implements Serializable {

    private static final long serialVersionUID = -2000370137;

    public static final TypeProvider<Report> TYPES = new TypeProvider<>(Report.class,
                    new ParameterizedTypeReference<List<Report>>() {});

    private Long reportId;
    private Long reporteruserId;
    private String targetType;
    private Long targetId;
    private Long targetAdditionalId;
    private String reason;
    private String comment;
    private Integer weight;
    private String managerAction;
    private Long manageruserId;
    private Timestamp managerActionDate;
    private Timestamp createdAt;

    public Report() {
    }

    public Report(Long reportId, Long reporteruserId, String targetType, Long targetId,
            String reason, String comment, Integer weight, String managerAction,
            Long manageruserId, Timestamp managerActionDate, Timestamp createdAt) {
        this.reportId = reportId;
        this.reporteruserId = reporteruserId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.reason = reason;
        this.comment = comment;
        this.weight = weight;
        this.managerAction = managerAction;
        this.manageruserId = manageruserId;
        this.managerActionDate = managerActionDate;
        this.createdAt = createdAt;
    }

    public Long getReportId() {
        return this.reportId;
    }

    public void setReportId(Long reportid) {
        this.reportId = reportid;
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

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getManagerAction() {
        return this.managerAction;
    }

    public void setManagerAction(String manageraction) {
        this.managerAction = manageraction;
    }

    public Long getManageruserId() {
        return this.manageruserId;
    }

    public void setManageruserId(Long manageruserId) {
        this.manageruserId = manageruserId;
    }

    public Timestamp getManagerActionDate() {
        return this.managerActionDate;
    }

    public void setManagerActionDate(Timestamp manageractiondate) {
        this.managerActionDate = manageractiondate;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTargetAdditionalId() {
        return targetAdditionalId;
    }

    public void setTargetAdditionalId(Long targetAdditionalId) {
        this.targetAdditionalId = targetAdditionalId;
    }

    @Override
    public String toString() {

        return "Report (" + reportId +
                ", " + reporteruserId +
                ", " + targetType +
                ", " + targetId +
                ", " + targetAdditionalId +
                ", " + reason +
                ", " + comment +
                ", " + weight +
                ", " + managerAction +
                ", " + manageruserId +
                ", " + managerActionDate +
                ", " + createdAt +
                ")";
    }
}
