package org.xcolab.view.pages.contestmanagement.wrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.flagging.IFlaggingClient;
import org.xcolab.client.flagging.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.flagging.pojo.IReportTarget;
import org.xcolab.client.flagging.pojo.tables.pojos.ReportTarget;
import org.xcolab.view.moderation.FlaggingController;
import org.xcolab.view.pages.contestmanagement.controller.manager.FlaggingTabController;

public class FlaggingReportTargetWrapper {

    private static final Logger _log = LoggerFactory.getLogger(FlaggingReportTargetWrapper.class);

    private IReportTarget reportTarget;
    private Boolean createNew = false;
    private static IFlaggingClient flaggingClient;

    public static void setFlaggingClient(IFlaggingClient flaggingClient) {
        FlaggingReportTargetWrapper.flaggingClient = flaggingClient;
    }
    public FlaggingReportTargetWrapper() {
        reportTarget = new ReportTarget();
    }

    public FlaggingReportTargetWrapper(long reportTargetId)
            throws ReportTargetNotFoundException {
        reportTarget = flaggingClient.getReportTarget(reportTargetId);
    }

    public Boolean getCreateNew() {
        return createNew;
    }

    public void setCreateNew(Boolean createNew) {
        this.createNew = createNew;
    }

    public void persist() throws ReportTargetNotFoundException {
        if (createNew) {
            createReportTargetFromExisting();
        } else {
            persistUpdatedReportTarget();
        }
    }

    private void createReportTargetFromExisting() {
        reportTarget = flaggingClient.createReportTarget(reportTarget);
    }

    private void persistUpdatedReportTarget() throws ReportTargetNotFoundException {
        flaggingClient.updateReportTarget(reportTarget);
    }

    public long getReportTargetId() {
        return reportTarget.getId();
    }

    public void setReportTargetId(long reportTargetId) {
        reportTarget.setId(reportTargetId);
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

    public Integer getScreeningThreshold() {
        return reportTarget.getScreeningThreshold();
    }

    public void setScreeningThreshold(int threshold) {
        reportTarget.setScreeningThreshold(threshold);
    }

    public Integer getNotificationThreshold() {
        return reportTarget.getNotificationThreshold();
    }

    public void setNotificationThreshold(int threshold) {
        reportTarget.setNotificationThreshold(threshold);
    }
}
