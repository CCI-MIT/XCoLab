package org.xcolab.client.flagging.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

public class ReportTarget implements Serializable {

    private static final long serialVersionUID = 1382979461;

    public static final TypeProvider<ReportTarget> TYPES = new TypeProvider<>(ReportTarget.class,
                    new ParameterizedTypeReference<List<ReportTarget>>() {});

    private long reportTargetId;
    private String type;
    private String reason;
    private Integer notificationthreshold;
    private Integer screeningthreshold;

    public ReportTarget() {
    }

    public ReportTarget(String type, String reason,
            Integer notificationthreshold, Integer screeningthreshold) {
        this.type = type;
        this.reason = reason;
        this.notificationthreshold = notificationthreshold;
        this.screeningthreshold = screeningthreshold;
    }

    public long getReportTargetId() {
        return reportTargetId;
    }

    public void setReportTargetId(long reportTargetId) {
        this.reportTargetId = reportTargetId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getNotificationThreshold() {
        return this.notificationthreshold;
    }

    public void setNotificationThreshold(Integer notificationthreshold) {
        this.notificationthreshold = notificationthreshold;
    }

    public Integer getScreeningThreshold() {
        return this.screeningthreshold;
    }

    public void setScreeningThreshold(Integer screeningthreshold) {
        this.screeningthreshold = screeningthreshold;
    }

    @Override
    public String toString() {

        return "ReportTarget (" + reportTargetId +
                ", " + type +
                ", " + reason +
                ", " + notificationthreshold +
                ", " + screeningthreshold +
                ")";
    }
}
