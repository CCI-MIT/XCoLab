package org.xcolab.portlets.redballoon.web;

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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xcolab.portlets.redballoon.utils.BalloonUtils;
import org.xcolab.portlets.redballoon.web.beans.UserEmailBean;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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
					throws SystemException, PortalException, IOException, ParserConfigurationException {
		
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
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

			Element element = doc.createElement( "meta");

			element.setAttribute( "property", "og:title" );
			element.setAttribute("content", text.getFacebookSubject());
			response.addProperty( MimeResponse.MARKUP_HEAD_ELEMENT, element );

			element = doc.createElement( "meta");
			element.setAttribute( "property", "og:description" );
			element.setAttribute("content", text.getFacebookDescription());
			
			response.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, element);
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
				UserEmailBean ueb = new UserEmailBean();
				ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				User user = td.getUser();
				if (!user.getDefaultUser()) {
					ueb.setEmail(user.getEmailAddress());
				}
				model.addAttribute("userEmailBean", ueb);
			}
			return "view";
		}
	}
}
