package org.xcolab.portlets.landingpage;

import java.io.IOException;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.ext.portlet.model.LandingPage;
import com.ext.portlet.service.LandingPageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class LandingpagePortlet extends MVCPortlet {

    @Override
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException,
            PortletException {
        // TODO Auto-generated method stub
        try {
        String cmd = ParamUtil.getString(actionRequest, "cmd");
        String baseUrl = ParamUtil.getString(actionRequest, "baseUrl");
        String targetUrl = ParamUtil.getString(actionRequest, "targetUrl");
        Long landingPageId = ParamUtil.getLong(actionRequest, "landingPageId");
        if ("add".equals(cmd)) {
            LandingPageLocalServiceUtil.store(LandingPageLocalServiceUtil.createNewLandingPage(baseUrl, targetUrl));
        }
        else if ("update".equals(cmd)) {
            LandingPage landingPage = LandingPageLocalServiceUtil.getLandingPage(landingPageId);
            landingPage.setBaseUrl(baseUrl);
            landingPage.setTargetUrl(targetUrl);
            landingPage.setUpdated(new Date());
            LandingPageLocalServiceUtil.store(landingPage);
        }
        else if ("delete".equals(cmd)) {
            LandingPage landingPage = LandingPageLocalServiceUtil.getLandingPage(landingPageId);
            LandingPageLocalServiceUtil.deleteLandingPage(landingPage);
        }
        
        
        super.processAction(actionRequest, actionResponse);
        }
        catch (PortalException e) {
            throw new PortletException(e);
        } catch (SystemException e) {
            throw new PortletException(e);
        }
    }

    @Override
    public void render(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        try {
            request.setAttribute("landingPages", LandingPageLocalServiceUtil.getLandingPages(0, Integer.MAX_VALUE));
            super.render(request, response);
        } catch (SystemException e) {
            throw new PortletException(e);
        }
    }

}
