package org.xcolab.wrapper;

import com.liferay.portal.kernel.util.Validator;

/**
 * Created by Mente on 29/07/15.
 */
public class SimpleExceptionErrorReportWrapper {
    String exceptionMessage;
    String exceptionStacktrace;
    String stepsToReproduce;
    String userEmailAddress;

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
        return Validator.isNotNull(stepsToReproduce);
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
        return Validator.isNotNull(exceptionMessage);
    }

    public boolean isExceptionStacktraceAvailable() {
        return Validator.isNotNull(exceptionMessage);
    }

    public String getExceptionMessage() {
        return Validator.isNotNull(exceptionMessage) ? exceptionStacktrace : "";
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionStacktrace() {
        return Validator.isNotNull(exceptionStacktrace) ? exceptionStacktrace : "";
    }

    public void setExceptionStacktrace(String exceptionStacktrace) {
        this.exceptionStacktrace = exceptionStacktrace;
    }
}
