package org.xcolab.portlets.contestmanagement.utils;

import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

/**
 * Created by Thomas on 4/27/2015.
 */
public class SetRenderParameterUtil {

    private static final String DEFAULT_SUCCESS_MESSAGE = "Changes saved!";
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Exception occured!";

    public static void setNoPermissionErrorRenderParameter(ActionResponse response){
        setErrorRenderParameter(response, "showNoPermission");
    }

    public static void setNotFoundErrorRenderParameter(ActionResponse response){
        setErrorRenderParameter(response, "showNotFound");
    }

    public static void setExceptionRenderParameter(ActionResponse response, Exception exception){
        setErrorRenderParameter(response, "showException");
        response.setRenderParameter("exception", exception.getMessage());
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

    public static void setSuccessRenderRedirectDetailsTab (ActionResponse response, Long contestPK, String tabName) throws Exception{
        response.sendRedirect("/web/guest/cms/-/contestmanagement/contestId/" + contestPK + "/tab/" + tabName);
    }

    public static void setSuccessRenderRedirectManagerTab (ActionResponse response, String tabName) throws Exception{
        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tabName);
    }

    public static void setSuccessRenderRedirectManagerTab (ActionResponse response, String tabName, Long elementId) throws Exception{
        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tabName + "/elementId/" + elementId);
    }

}
