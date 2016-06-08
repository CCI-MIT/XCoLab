package org.xcolab.client.flagging.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Report implements Serializable {

    private static final long serialVersionUID = -2000370137;

    private Long reportid;
    private Long reportermemberid;
    private String targettype;
    private Long targetid;
    private Long targetAdditionalId;
    private String reason;
    private String comment;
    private Integer weight;
    private String manageraction;
    private Long managermemberid;
    private Timestamp manageractiondate;
    private Timestamp createdate;

    public Report() {
    }

    public Report(Long reportid, Long reportermemberid, String targettype, Long targetid,
            String reason, String comment, Integer weight, String manageraction,
            Long managermemberid, Timestamp manageractiondate, Timestamp createdate) {
        this.reportid = reportid;
        this.reportermemberid = reportermemberid;
        this.targettype = targettype;
        this.targetid = targetid;
        this.reason = reason;
        this.comment = comment;
        this.weight = weight;
        this.manageraction = manageraction;
        this.managermemberid = managermemberid;
        this.manageractiondate = manageractiondate;
        this.createdate = createdate;
    }

    public Long getReportId() {
        return this.reportid;
    }

    public void setReportId(Long reportid) {
        this.reportid = reportid;
    }

    public Long getReporterMemberId() {
        return this.reportermemberid;
    }

    public void setReporterMemberId(Long reportermemberid) {
        this.reportermemberid = reportermemberid;
    }

    public String getTargetType() {
        return this.targettype;
    }

    public void setTargetType(String targettype) {
        this.targettype = targettype;
    }

    public Long getTargetId() {
        return this.targetid;
    }

    public void setTargetId(Long targetid) {
        this.targetid = targetid;
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
        return this.manageraction;
    }

    public void setManagerAction(String manageraction) {
        this.manageraction = manageraction;
    }

    public Long getManagerMemberId() {
        return this.managermemberid;
    }

    public void setManagerMemberId(Long managermemberid) {
        this.managermemberid = managermemberid;
    }

    public Timestamp getManagerActionDate() {
        return this.manageractiondate;
    }

    public void setManagerActionDate(Timestamp manageractiondate) {
        this.manageractiondate = manageractiondate;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Long getTargetAdditionalId() {
        return targetAdditionalId;
    }

    public void setTargetAdditionalId(Long targetAdditionalId) {
        this.targetAdditionalId = targetAdditionalId;
    }

    @Override
    public String toString() {

        return "Report (" + reportid +
                ", " + reportermemberid +
                ", " + targettype +
                ", " + targetid +
                ", " + targetAdditionalId +
                ", " + reason +
                ", " + comment +
                ", " + weight +
                ", " + manageraction +
                ", " + managermemberid +
                ", " + manageractiondate +
                ", " + createdate +
                ")";
    }
}
