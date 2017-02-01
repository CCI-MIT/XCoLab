package org.xcolab.view.pages.contestmanagement.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public enum ContestMassActions {
    ORDER("Order only"),
    MESSAGE("Message contributors in active phase", ContestMassActionMethods.class,
            "sendMassMessage"),
    MESSAGE_ALL_AUTHORS("Message all proposal authors", ContestMassActionMethods.class,
            "sendMassMessageToAllProposalAuthors"),
    REPORT_PEOPLE_IN_CURRENT_PHASE("Generate report of contributors in active phase",
            ContestMassActionMethods.class,
            "reportOfPeopleInCurrentPhase"),
    DELETE("Delete contests", ContestMassActionMethods.class, "deleteContest"),
    DELETE_WITH_PHASES("Delete contests including phases", ContestMassActionMethods.class,
            "deleteContestwithPhases"),
    ACTIVE("Active", "Prior", Contest.class, "setContestActive"),
    PRIVATE("Private", "Public", Contest.class, "setContestPrivate"),
    FEATURED("Feature", "Remove feature", Contest.class, "setFeatured_"),
    FLAG("Set contest flag", ContestMassActionMethods.class, "setFlag"),
    MODEL_SETTINGS("Set model settings", ContestMassActionMethods.class, "setModelSettings"),
    SUBSCRIBE("Subscribe to activity", "Unsubscribe from activity", ContestMassActionMethods.class,
            "changeSubscriptionStatus"),
    SHOW_IN_TILE_VIEW("Show in tile view", "Hide in tile viw", Contest.class,
            "setShow_in_tile_view"),
    SHOW_IN_LIST_VIEW("Show in list view", "Hide in list view", Contest.class,
            "setShow_in_list_view"),
    SHOW_IN_OUTLINE_VIEW("Show in outline view", "Hide in outline view", Contest.class,
            "setShow_in_outline_view"),
    HIDE_RIBBONS("Hide contest ribbons", "Show contest ribbons", Contest.class, "setHideRibbons");

    private final Logger _log = LoggerFactory.getLogger(ContestMassActions.class);
    private final String actionDisplayName;
    private String reverseActionDisplayName = "";
    private Boolean hasReverseAction = false;
    private Method method;

    ContestMassActions(String actionDisplayName, String reverseActionDisplayName, Class className,
            String methodName) {
        this.actionDisplayName = actionDisplayName;
        this.reverseActionDisplayName = reverseActionDisplayName;
        this.hasReverseAction = true;
        setMethodFromClassName(methodName, className);
    }

    private void setMethodFromClassName(String methodName, Class className) {
        try {
            if (className == Contest.class) {
                this.method = Contest.class.getMethod(methodName, Boolean.class);
            } else if (className == ContestMassActionMethods.class) {
                this.method = ContestMassActionMethods.class
                        .getMethod(methodName, List.class, Object.class, HttpServletRequest.class);
            }
        } catch (NoSuchMethodException e) {
            _log.warn("Could not find mass action method with name: {}", methodName);
        }
    }

    ContestMassActions(String actionDisplayName, Class className, String methodName) {
        this.actionDisplayName = actionDisplayName;
        setMethodFromClassName(methodName, className);
    }

    ContestMassActions(String actionDisplayName) {
        this.actionDisplayName = actionDisplayName;
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
