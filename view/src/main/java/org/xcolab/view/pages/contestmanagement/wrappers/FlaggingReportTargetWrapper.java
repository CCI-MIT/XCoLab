package org.xcolab.view.pages.contestmanagement.wrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.flagging.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.flagging.pojo.ReportTarget;

public class FlaggingReportTargetWrapper {

    private final static Logger _log = LoggerFactory.getLogger(FlaggingReportTargetWrapper.class);

    private ReportTarget reportTarget;
    private Boolean createNew = false;

    public FlaggingReportTargetWrapper() {
        reportTarget = new ReportTarget();
    }

    public FlaggingReportTargetWrapper(long reportTargetId)
            throws ReportTargetNotFoundException {
        reportTarget = FlaggingClient.getReportTarget(reportTargetId);
    }

    public Boolean getCreateNew() {
        return createNew;
    }

    public void setCreateNew(Boolean createNew) {
        this.createNew = createNew;
    }

    public void persist() {
        if (createNew) {
            createReportTargetFromExisting();
        } else {
            persistUpdatedReportTarget();
        }
    }

    private void createReportTargetFromExisting() {
        reportTarget = FlaggingClient.createReportTarget(reportTarget);
    }

    private void persistUpdatedReportTarget() {
        FlaggingClient.updateReportTarget(reportTarget);
    }

    public long getReportTargetId() {
        return reportTarget.getReportTargetId();
    }

    public void setReportTargetId(long reportTargetId) {
        reportTarget.setReportTargetId(reportTargetId);
    }

    public String getType() {
        return reportTarget.getType();
    }

    public void setType(String type) {
        reportTarget.setType(type);
    }

    public String getReason() {
        return reportTarget.getReason();
    }

    public void setReason(String reason) {
        reportTarget.setReason(reason);
    }

    public int getScreeningThreshold() {
        return reportTarget.getScreeningThreshold();
    }

    public void setScreeningThreshold(int threshold) {
        reportTarget.setScreeningThreshold(threshold);
    }

    public int getNotificationThreshold() {
        return reportTarget.getNotificationThreshold();
    }

    public void setNotificationThreshold(int threshold) {
        reportTarget.setNotificationThreshold(threshold);
    }
}
