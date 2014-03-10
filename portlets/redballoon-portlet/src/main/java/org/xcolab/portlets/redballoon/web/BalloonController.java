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
import com.liferay.portal.util.PortalUtil;

@RequestMapping("view")
@Controller
public class BalloonController {
	private final static String SHARE_LINK_PATTERN = "%s/balloon/-/balloon/link/%s";

	
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

			// create URL that should be used when sharing
			String requestUrl = PortalUtil.getHttpServletRequest(request).getRequestURL().toString();
			// find first occurrence of / to get address and port
			String protocolHostPort = requestUrl.substring(0, requestUrl.indexOf("/", 10));
			
			model.addAttribute("shareLink", String.format(SHARE_LINK_PATTERN, protocolHostPort, bl.getUuid()));
			
			model.addAttribute("balloonLink", bl);
			return "sharePage";
		}
		else {
			if (userEmailBean != null && userEmailBean.getEmail() != null) {
				model.addAttribute("userEmailBean", userEmailBean);
			}
			else {
				model.addAttribute("userEmailBean", new UserEmailBean());
			}
			return "view";
		}
	}
	
	
	
	
	
}
