package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class SetRenderParameterUtil {

    private static final String DEFAULT_SUCCESS_MESSAGE = "Changes saved!";

    private SetRenderParameterUtil() { }

    public static void setNoPermissionErrorRenderParameter(HttpServletResponse response) {
        setErrorRenderParameter(response, "showNoPermission");
    }

    public static void setErrorRenderParameter(HttpServletResponse response,
            String errorActionParameter) {
        //TODO: replace
        //        response.setRenderParameter("error", "true");
        //        response.setRenderParameter("action", errorActionParameter);
    }

    public static void setNotFoundErrorRenderParameter(HttpServletResponse response) {
        setErrorRenderParameter(response, "showNotFound");
    }

    public static void setExceptionRenderParameter(HttpServletResponse response,
            Throwable exception) {
        setErrorRenderParameter(response, "showException");
        //TODO: replace
        //        response.setRenderParameter("exceptionMessage", exception.getMessage());
        //        response.setRenderParameter("exceptionStacktrace",
        // getFullStackTraceInHtmlFormat(exception));
    }

    public static void addActionExceptionMessageToSession(HttpServletRequest request,
            Throwable exception) {
        addMessageToSession(request, "exceptionMessage", exception.getMessage());
        addMessageToSession(request, "exceptionStacktrace",
                getFullStackTraceInHtmlFormat(exception));
    }

    private static String getFullStackTraceInHtmlFormat(Throwable rootThrowable) {
        StringBuilder fullStackTraceString = new StringBuilder();
        for (Throwable throwable = rootThrowable; throwable != null;
                throwable = throwable.getCause()) {
            if (!Objects.equals(throwable, rootThrowable)) {
                fullStackTraceString.append("<br/>Caused by ").append(throwable.getMessage())
                        .append(": ");
            }
            fullStackTraceString.append(getStackTraceInHtmlFormat(throwable.getStackTrace()));
        }
        return fullStackTraceString.toString();
    }

    private static String getStackTraceInHtmlFormat(StackTraceElement[] stackTraceElements) {
        String htmlStacktrace = "<pre>";
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            htmlStacktrace += stackTraceElement.toString() + "<br>";
        }
        htmlStacktrace += "</pre>";
        return htmlStacktrace;
    }

    private static void addMessageToSession(HttpServletRequest request, String key,
            String message) {
        HttpSession session = request.getSession();
        session.setAttribute(key, message);
    }

    public static void addActionSuccessMessageToSession(HttpServletRequest request) {
        addActionSuccessMessageToSession(request, DEFAULT_SUCCESS_MESSAGE);
    }

    public static void addActionSuccessMessageToSession(HttpServletRequest request,
            String successMessage) {
        addMessageToSession(request, "actionSuccessMessage", successMessage);
    }

    public static void setSuccessRenderRedirectDetailsTab(HttpServletResponse response,
            Long contestPK, String tabName)
            throws IOException {
        response.sendRedirect(
                "/web/guest/cms/-/contestmanagement/contestId/" + contestPK + "/tab/" + tabName);
    }

    public static void setSuccessRenderRedirectManagerTab(HttpServletResponse response,
            String tabName) throws IOException {
        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tabName);
    }

    public static void setSuccessRenderRedirectManagerTab(HttpServletResponse response,
            String tabName, Long elementId)
            throws IOException {
        response.sendRedirect(
                "/web/guest/cms/-/contestmanagement/manager/tab/" + tabName + "/elementId/"
                        + elementId);
    }

    public static void setConfirmMassActionRenderRedirect(HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper) {
        //TODO: replace
        //        response.setRenderParameter("action", "showMassActionConfirmation");
        //        response.setRenderParameter("massActionId", contestOverviewWrapper.getSelectedMassAction().toString());
        //        response.setRenderParameter("contestIds", contestOverviewWrapper.getSelectedContestIds().toString());
    }
}
