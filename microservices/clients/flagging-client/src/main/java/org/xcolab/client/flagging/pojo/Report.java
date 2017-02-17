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
    private Long reporterMemberId;
    private String targetType;
    private Long targetId;
    private Long targetAdditionalId;
    private String reason;
    private String comment;
    private Integer weight;
    private String managerAction;
    private Long managerMemberId;
    private Timestamp managerActionDate;
    private Timestamp createDate;

    public Report() {
    }

    public Report(Long reportId, Long reporterMemberId, String targetType, Long targetId,
            String reason, String comment, Integer weight, String managerAction,
            Long managerMemberId, Timestamp managerActionDate, Timestamp createDate) {
        this.reportId = reportId;
        this.reporterMemberId = reporterMemberId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.reason = reason;
        this.comment = comment;
        this.weight = weight;
        this.managerAction = managerAction;
        this.managerMemberId = managerMemberId;
        this.managerActionDate = managerActionDate;
        this.createDate = createDate;
    }

    public Long getReportId() {
        return this.reportId;
    }

    public void setReportId(Long reportid) {
        this.reportId = reportid;
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

    public Long getManagerMemberId() {
        return this.managerMemberId;
    }

    public void setManagerMemberId(Long managermemberid) {
        this.managerMemberId = managermemberid;
    }

    public Timestamp getManagerActionDate() {
        return this.managerActionDate;
    }

    public void setManagerActionDate(Timestamp manageractiondate) {
        this.managerActionDate = manageractiondate;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createDate = createdate;
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
                ", " + reporterMemberId +
                ", " + targetType +
                ", " + targetId +
                ", " + targetAdditionalId +
                ", " + reason +
                ", " + comment +
                ", " + weight +
                ", " + managerAction +
                ", " + managerMemberId +
                ", " + managerActionDate +
                ", " + createDate +
                ")";
    }
}
