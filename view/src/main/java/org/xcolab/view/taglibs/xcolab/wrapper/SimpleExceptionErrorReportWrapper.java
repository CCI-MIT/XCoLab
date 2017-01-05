package org.xcolab.view.taglibs.xcolab.wrapper;

import org.apache.commons.lang3.StringUtils;

public class SimpleExceptionErrorReportWrapper {
    private String exceptionMessage;
    private String exceptionStacktrace;
    private String stepsToReproduce;
    private String userEmailAddress;

    public SimpleExceptionErrorReportWrapper() {

    }

    public SimpleExceptionErrorReportWrapper(String stepsToReproduce, String userEmailAddress) {
        this.stepsToReproduce = stepsToReproduce;
        this.userEmailAddress = userEmailAddress;
    }

    public SimpleExceptionErrorReportWrapper(String exceptionMessage, String stepsToReproduce, String userEmailAddress) {
        this.exceptionMessage = exceptionMessage;
        this.stepsToReproduce = stepsToReproduce;
        this.userEmailAddress = userEmailAddress;
    }

    public SimpleExceptionErrorReportWrapper(String stepsToReproduce, String userEmailAddress, String exceptionMessage, String exceptionStacktrace) {
        this.stepsToReproduce = stepsToReproduce;
        this.userEmailAddress = userEmailAddress;
        this.exceptionMessage = exceptionMessage;
        this.exceptionStacktrace = exceptionStacktrace;
    }

    public boolean isWrapperFilledOut() {
        return StringUtils.isNotEmpty(stepsToReproduce);
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void setStepsToReproduce(String stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }

    public boolean isExceptionMessageAvailable() {
        return StringUtils.isNotEmpty(exceptionMessage);
    }

    public boolean isExceptionStacktraceAvailable() {
        return StringUtils.isNotEmpty(exceptionMessage);
    }

    public String getExceptionMessage() {
        return StringUtils.isNotEmpty(exceptionMessage) ? exceptionStacktrace : "";
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionStacktrace() {
        return StringUtils.isNotEmpty(exceptionStacktrace) ? exceptionStacktrace : "";
    }

    public void setExceptionStacktrace(String exceptionStacktrace) {
        this.exceptionStacktrace = exceptionStacktrace;
    }
}
