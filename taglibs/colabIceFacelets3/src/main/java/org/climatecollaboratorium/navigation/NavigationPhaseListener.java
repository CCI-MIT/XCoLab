package org.climatecollaboratorium.navigation;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.portlet.PortletRequest;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;

public class NavigationPhaseListener implements PhaseListener {

    private final static Log _log = LogFactoryUtil.getLog(NavigationPhaseListener.class); 
    @Override
    public void afterPhase(PhaseEvent phaseEvent) {

        FacesContext fc = phaseEvent.getFacesContext();
        HttpServletRequestWrapper sr = getPortalRequest(fc);

        String currentUrl = PortalUtil.getCurrentURL(sr);
       // PortalUtil.getHttpServletRequest(sr);
        HttpServletRequest rq = getHttpServletRequest(fc);
        //phaseEvent.getFacesContext().getApplication().getNavigationHandler().handleNavigation(fc, null, "testTag");
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    public static HttpServletRequestWrapper getPortalRequest(FacesContext context) {
        Map<String, Object> requests = context.getExternalContext().getRequestMap();
        
        for (String requestName : requests.keySet()) {
            if (requests.get(requestName) instanceof HttpServletRequestWrapper) {
                return (HttpServletRequestWrapper) requests.get(requestName);
            }
        }
        return null;
    }
    
    public HttpServletRequest getHttpServletRequest(FacesContext context) {
        return PortalUtil.getHttpServletRequest((PortletRequest) context.getExternalContext().getRequestMap().get("javax.portlet.request"));
    }

}
