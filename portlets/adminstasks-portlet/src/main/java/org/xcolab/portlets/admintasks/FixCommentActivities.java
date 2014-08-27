package org.xcolab.portlets.admintasks;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class FixCommentActivities {
	
	public String fixCommentActivities() throws SystemException, PortalException {
		
		PortletRequest portletRequest = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		PortletRequest realPortletRequest = (PortletRequest) portletRequest.getAttribute("javax.portlet.request");
		/*HttpServletRequest request = PortalUtil.getHttpServletRequest(realPortletRequest);
		request =  PortalUtil.getOriginalServletRequest(request);

        request.setAttribute(WebKeys.THEME_DISPLAY, Helper.getThemeDisplay());
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setRequest(request);  
        serviceContext.setPortalURL(Helper.getThemeDisplay().getPortalURL());
        //request.setAttribute(WebKeys.THEME_DISPLAY, Helper.getThemeDisplay());
         * 
         */
        
		for (SocialActivity sa: SocialActivityLocalServiceUtil.getActivities(DiscussionCategoryGroup.class.getName(), 0, Integer.MAX_VALUE)) {
			try {
				if (sa.getType() == 4) {
					Long[] ids = ActivityUtil.getIdsFromExtraData(sa.getExtraData());
					if (ids.length == 3) {
						try {
							DiscussionMessage dm = DiscussionMessageLocalServiceUtil.getDiscussionMessage(ids[2]);
						}
						catch (NoSuchDiscussionMessageException e){
							SocialActivityLocalServiceUtil.deleteActivity(sa);
							System.out.println("Can't find message for activity: " + sa);
						}
					}
				}
			}
			catch (Throwable t) {
				System.out.println("******* Exception! " + t.getClass().getName() + "\t" + t.getMessage());
				t.printStackTrace();
				break;
			}
		}
		/*
        SocialActivity sa = SocialActivityLocalServiceUtil.getActivity(1358671L);
		SocialActivityFeedEntry safe =  SocialActivityInterpreterLocalServiceUtil.interpret("", sa, serviceContext);
		
		System.out.println(safe);
		*/
        
		
		return "";
	}

}
