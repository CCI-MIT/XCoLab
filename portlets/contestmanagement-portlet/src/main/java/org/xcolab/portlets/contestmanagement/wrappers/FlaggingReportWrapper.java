package org.xcolab.portlets.contestmanagement.wrappers;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.flagging.exceptions.ReportNotFoundException;
import org.xcolab.client.flagging.pojo.Report;
import org.xcolab.util.enums.flagging.TargetType;

import java.util.Date;

public class FlaggingReportWrapper {
    private final static Log _log = LogFactoryUtil.getLog(EmailTemplateWrapper.class);

    private Report report;

    public FlaggingReportWrapper() {
        report = new Report();
    }

    public FlaggingReportWrapper(long reportId) throws ReportNotFoundException {
        initReportTarget(reportId);
    }

    private void initReportTarget(long reportId) throws ReportNotFoundException {
        report = FlaggingClient.getReport(reportId);
    }

    public void persist() {
        persistUpdatedReport();
    }

    private void persistUpdatedReport() {
        FlaggingClient.updateReport(report);
    }

    public Long getReportId() {
        return report.getReportId();
    }

    public void setReportId(Long reportId) {
        report.setReportId(reportId);
    }

    public Long getReporterMemberId() {
        return report.getReporterMemberId();
    }

    public void setReporterMemberId(Long reporterMemberId) {
        report.setReporterMemberId(reporterMemberId);
    }

    public String getTargetType() {
        return report.getTargetType();
    }

    public void setTargetType(String targetType) {
        report.setTargetType(targetType);
    }

    public Long getTargetId() {
        return report.getTargetId();
    }

    public void setTargetId(Long targetId) {
        report.setTargetId(targetId);
    }

    public String getReason() {
        return report.getReason();
    }

    public void setReason(String reason) {
        report.setReason(reason);
    }

    public String getComment() {
        return report.getComment();
    }

    public void setComment(String comment) {
        report.setComment(comment);
    }

    public Integer getWeight() {
        return report.getWeight();
    }

    public void setWeight(Integer weight) {
        report.setWeight(weight);
    }

    public String getManagerAction() {
        return report.getManagerAction();
    }

    public void setManagerAction(String managerAction) {
        report.setManagerAction(managerAction);
    }

    public Long getManagerMemberId() {
        return report.getManagerMemberId();
    }

    public void setManagerMemberId(Long managerMemberId) {
        report.setManagerMemberId(managerMemberId);
    }

    public Date getManagerActionDate() {
        return new Date(report.getManagerActionDate().getTime());
    }

    public Date getCreateDate() {
        return new Date(report.getCreateDate().getTime());
    }

    public Long getTargetAdditionalId() {
        return report.getTargetAdditionalId();
    }

    public void setTargetAdditionalId(Long targetAdditionalId) {
        report.setTargetAdditionalId(targetAdditionalId);
    }

    private static void removeReportTarget(TargetType targetType, String reason) {
        //TODO
    }
}
