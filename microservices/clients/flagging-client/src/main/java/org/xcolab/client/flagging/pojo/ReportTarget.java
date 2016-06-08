package org.xcolab.client.flagging.pojo;

import java.io.Serializable;

public class ReportTarget implements Serializable {

    private static final long serialVersionUID = 1382979461;

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

        return "ReportTarget (" + type +
                ", " + reason +
                ", " + notificationthreshold +
                ", " + screeningthreshold +
                ")";
    }
}
