package org.xcolab.portlets.proposals.wrappers;

import com.liferay.portal.kernel.util.Validator;

/**
 * Created by Mente on 29/07/15.
 */
public class InvalidUrlErrorWrapper {
    String stepsToReproduce;
    String userEmailAddress;

    public InvalidUrlErrorWrapper() {

    }

    public InvalidUrlErrorWrapper(String stepsToReproduce, String userEmailAddress) {
        this.stepsToReproduce = stepsToReproduce;
        this.userEmailAddress = userEmailAddress;
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
}
