package org.xcolab.portlets.contestmanagement.wrappers;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.flagging.exceptions.ReportNotFoundException;
import org.xcolab.client.flagging.pojo.AggregatedReport;

import java.util.Date;

public class FlaggingReportWrapper {
    private final static Log _log = LogFactoryUtil.getLog(FlaggingReportWrapper.class);

    private AggregatedReport report;

    public FlaggingReportWrapper() {
        report = new AggregatedReport();
    }

    public FlaggingReportWrapper(long reportId) throws ReportNotFoundException {
        //TODO:
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

    public Integer getAggregatedWeight() {
        return report.getAggregatedWeight();
    }

    public Integer getCount() {
        return report.getCount();
    }

    public Date getFirstReportDate() {
        return new Date(report.getFirstReportDate().getTime());
    }

    public Date getLastReportDate() {
        return new Date(report.getLastReportDate().getTime());
    }

    public Long getTargetAdditionalId() {
        return report.getTargetAdditionalId();
    }

    public void setTargetAdditionalId(Long targetAdditionalId) {
        report.setTargetAdditionalId(targetAdditionalId);
    }
}
