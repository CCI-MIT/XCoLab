package org.xcolab.portlets.redballoon.web;

import java.io.IOException;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.redballoon.utils.BalloonUtils;
import org.xcolab.portlets.redballoon.web.beans.UserEmailBean;

import com.ext.portlet.model.BalloonLink;
import com.ext.portlet.model.BalloonText;
import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.service.BalloonLinkLocalServiceUtil;
import com.ext.portlet.service.BalloonTextLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@RequestMapping("view")
@Controller
public class BalloonController {

	
	@RequestMapping
	public String showBalloon(Model model, 
			PortletRequest request, 
			RenderResponse response,
			@RequestParam(required=false) String linkuuid, 
			@RequestParam(required=false) String context,
			@Valid UserEmailBean userEmailBean, BindingResult bindingResult) 
					throws SystemException, PortalException, IOException {
		
		BalloonLink link = null;
		BalloonUserTracking but = null;
		if (linkuuid != null) {
			link = BalloonLinkLocalServiceUtil.getBalloonLink(linkuuid);
			
			if (link != null) {
				model.addAttribute("balloonLink", link);
				
				// get user tracking information, if user is new, then owner of this link should be set as a parent
				but = BalloonUtils.getBalloonUserTracking(request, response, link.getBalloonUserUuid(), linkuuid, context);
				link.setVisits(link.getVisits() + 1);
				
				BalloonLinkLocalServiceUtil.updateBalloonLink(link);
			}
		}
		
		if (but == null) {
			// user wasn't following any link so we need to create new root of a reference tree
			but = BalloonUtils.getBalloonUserTracking(request, response, null, null, null);
		}
		if (but.getBalloonTextId() > 0) {
			BalloonText text = BalloonTextLocalServiceUtil.getBalloonText(but.getBalloonTextId());
			model.addAttribute("balloonText", text);
		}
		
		if (StringUtils.isNotBlank(but.getEmail())) {
			BalloonLink bl = BalloonLinkLocalServiceUtil.getBalloonLinkForUser(but.getUuid());

			
			model.addAttribute("shareLink", BalloonUtils.getBalloonUrlForLink(request,  bl));
			
			model.addAttribute("balloonLink", bl);
			return "sharePage";
		}
		else {
			if (userEmailBean != null && userEmailBean.getEmail() != null) {
				model.addAttribute("userEmailBean", userEmailBean);
			}
			else {
				ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				User user = td.getUser();
				UserEmailBean ueb = new UserEmailBean();
				
				if (!user.getDefaultUser()) {
					ueb.setEmail(user.getEmailAddress());
				}
				model.addAttribute("userEmailBean", ueb);
			}
			return "view";
		}
	}
	
	
	
	
	
}
