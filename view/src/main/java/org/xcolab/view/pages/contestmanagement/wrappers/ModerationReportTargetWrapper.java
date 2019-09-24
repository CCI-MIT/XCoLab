package org.xcolab.view.pages.contestmanagement.wrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.moderation.StaticModerationContext;
import org.xcolab.client.moderation.exceptions.ReportTargetNotFoundException;
import org.xcolab.client.moderation.pojo.IReportTarget;
import org.xcolab.client.moderation.pojo.tables.pojos.ReportTarget;

public class ModerationReportTargetWrapper {

    private static final Logger _log = LoggerFactory.getLogger(ModerationReportTargetWrapper.class);

    private IReportTarget reportTarget;
    private Boolean createNew = false;

    public ModerationReportTargetWrapper() {
        reportTarget = new ReportTarget();
    }

    public ModerationReportTargetWrapper(long reportTargetId)
            throws ReportTargetNotFoundException {
        reportTarget = StaticModerationContext.getModerationClient()
                .getReportTarget(reportTargetId);
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
        reportTarget = StaticModerationContext.getModerationClient()
                .createReportTarget(reportTarget);
    }

    private void persistUpdatedReportTarget() throws ReportTargetNotFoundException {
        StaticModerationContext.getModerationClient().updateReportTarget(reportTarget);
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
