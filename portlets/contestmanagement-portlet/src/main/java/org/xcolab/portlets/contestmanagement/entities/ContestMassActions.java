package org.xcolab.portlets.contestmanagement.entities;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.portlets.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.wrapper.ContestWrapper;

import javax.portlet.PortletRequest;
import java.lang.reflect.Method;

/**
 * Created by Thomas on 2/17/2015.
 */
public enum ContestMassActions{
    NONE("Order only"),
    MESSAGE("Message contributors in active phase", ContestMassActionMethods.class, "sendMassMessage"),
    CAST_SUPPORT_TO_VOTES("Message proposal supporters to vote", ContestMassActionMethods.class, "sendSupport2VotesEmail"),
    REPORT_PEOPLE_IN_CURRENT_PHASE("Generate report of contributors in active phase", ContestMassActionMethods.class, "reportOfPeopleInCurrentPhase"),
    DELETE("Delete contest", ContestMassActionMethods.class, "deleteContest"),
    ACTIVE("Active", "Prior", ContestWrapper.class,"setContestActive"),
    PRIVATE("Hide", "Unhide", ContestWrapper.class, "setContestPrivate"),
    FEATURED("Feature", "Remove feature", ContestWrapper.class, "setFeatured"),
    SUBSCRIBE("Subscribe to activity", "Unsubscribe to activity", ContestMassActionMethods.class, "changeSubscriptionStatus");

    private Log _log = LogFactoryUtil.getLog(ContestMassActions.class);
    private final String actionDisplayName;
    private String reverseActionDisplayName = "";
    private Boolean hasReverseAction = false;
    private Method method;

    ContestMassActions(String actionDisplayName, String reverseActionDisplayName, Class className, String methodName){
        this.actionDisplayName = actionDisplayName;
        this.reverseActionDisplayName = reverseActionDisplayName;
        this.hasReverseAction = true;
        setMethodFromClassName(methodName, className);
    }

    ContestMassActions(String actionDisplayName, Class className, String methodName){
        this.actionDisplayName = actionDisplayName;
        setMethodFromClassName(methodName, className);
    }

    ContestMassActions(String actionDisplayName) {
        this.actionDisplayName = actionDisplayName;
    }

    private void setMethodFromClassName(String methodName, Class className){
        try {
            if(className == ContestWrapper.class){
                this.method = className.getMethod(methodName, boolean.class);
            } else {
                this.method = className.getMethod(methodName, Long.class, Object.class, PortletRequest.class);
            }
        } catch (NoSuchMethodException e){
            _log.warn("Could not find mass action method with name: " + methodName);
        }
    }

    public String getActionDisplayName() {
        return actionDisplayName;
    }

    public String getReverseActionDisplayName() {
        return reverseActionDisplayName;
    }

    public Boolean getHasReverseAction() {
        return hasReverseAction;
    }

    public Method getMethod() {
        return method;
    }
}
