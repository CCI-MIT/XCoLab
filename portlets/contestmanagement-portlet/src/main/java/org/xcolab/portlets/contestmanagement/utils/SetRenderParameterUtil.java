package org.xcolab.portlets.contestmanagement.utils;

import org.xcolab.portlets.contestmanagement.wrappers.ContestOverviewWrapper;

import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import java.io.IOException;

/**
 * Created by Thomas on 4/27/2015.
 */
public final class SetRenderParameterUtil {

    private static final String DEFAULT_SUCCESS_MESSAGE = "Changes saved!";

    private SetRenderParameterUtil() { }

    public static void setNoPermissionErrorRenderParameter(ActionResponse response){
        setErrorRenderParameter(response, "showNoPermission");
    }

    public static void setNotFoundErrorRenderParameter(ActionResponse response){
        setErrorRenderParameter(response, "showNotFound");
    }

    public static void setExceptionRenderParameter(ActionResponse response, Exception exception){
        setErrorRenderParameter(response, "showException");
        response.setRenderParameter("exceptionMessage", exception.getMessage());
        response.setRenderParameter("exceptionStacktrace", getFullStackTraceInHtmlFormat(exception));
    }

    private static String getFullStackTraceInHtmlFormat(Throwable rootThrowable) {
        StringBuilder fullStackTraceString = new StringBuilder();
        for (Throwable throwable = rootThrowable; throwable != null; throwable = throwable.getCause()) {
            if (throwable != rootThrowable) {
                fullStackTraceString.append("<br/>Caused by ").append(throwable.getMessage()).append(": ");
            }
            fullStackTraceString.append(getStackTraceInHtmlFormat(throwable.getStackTrace()));
        }
        return fullStackTraceString.toString();
    }

    private static String getStackTraceInHtmlFormat(StackTraceElement[] stackTraceElements){
        String htmlStacktrace = "<pre>";
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            htmlStacktrace += stackTraceElement.toString() + "<br>";
        }
        htmlStacktrace += "</pre>";
        return htmlStacktrace;
    }

    public static void addActionExceptionMessageToSession(PortletRequest request, Exception exception){
        addMessageToSession(request, "exceptionMessage", exception.getMessage());
        addMessageToSession(request, "exceptionStacktrace", getFullStackTraceInHtmlFormat(exception));
    }

    public static void addActionSuccessMessageToSession(PortletRequest request, String successMessage){
        addMessageToSession(request, "actionSuccessMessage", successMessage);
    }

    public static void addActionSuccessMessageToSession(PortletRequest request){
        addActionSuccessMessageToSession(request, DEFAULT_SUCCESS_MESSAGE);
    }

    private static void addMessageToSession(PortletRequest request, String key, String message){
        PortletSession session = request.getPortletSession();
        session.setAttribute( key, message, PortletSession.APPLICATION_SCOPE);
    }

    public static void setErrorRenderParameter(ActionResponse response, String errorActionParameter){
        response.setRenderParameter("error", "true");
        response.setRenderParameter("action", errorActionParameter);
    }

    public static void setSuccessRenderRedirectDetailsTab (ActionResponse response, Long contestPK, String tabName) throws IOException {
        response.sendRedirect("/web/guest/cms/-/contestmanagement/contestId/" + contestPK + "/tab/" + tabName);
    }

    public static void setSuccessRenderRedirectManagerTab (ActionResponse response, String tabName) throws IOException {
        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tabName);
    }

    public static void setSuccessRenderRedirectManagerTab (ActionResponse response, String tabName, Long elementId) throws IOException {
        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tabName + "/elementId/" + elementId);
    }

    public static void setConfirmMassActionRenderRedirect (ActionResponse response, ContestOverviewWrapper contestOverviewWrapper){
        response.setRenderParameter("action", "showMassActionConfirmation");
        response.setRenderParameter("massActionId", contestOverviewWrapper.getSelectedMassAction().toString());
        response.setRenderParameter("contestIds", contestOverviewWrapper.getSelectedContestIds().toString());
    }
}
