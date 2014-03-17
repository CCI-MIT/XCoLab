package org.xcolab.portlets.redballoon.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.redballoon.BalloonCookie;
import org.xcolab.portlets.redballoon.utils.BalloonUtils;
import org.xcolab.portlets.redballoon.web.beans.UserEmailBean;

import com.ext.portlet.NoSuchBalloonLinkException;
import com.ext.portlet.NoSuchBalloonUserTrackingException;
import com.ext.portlet.model.BalloonLink;
import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.service.BalloonLinkLocalServiceUtil;
import com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

@RequestMapping("view")
@Controller
public class ProcessUserEmailAction {
	
	private final static String BALLOON_LINK_PATTERN = "/web/guest/balloon";
	
	@RequestMapping(params="action=sendEmail")
	public void processUserEmail(ActionRequest request, ActionResponse response, Model model,
			@Valid UserEmailBean userEmailBean, BindingResult bindingResult) throws PortalException, SystemException, IOException {
		
		if (userEmailBean != null && !bindingResult.hasErrors()) {
			BalloonUserTracking but = BalloonUtils.getBalloonUserTracking(request, response, null, null, null);
			if (StringUtils.isNotBlank(but.getEmail())) {
				// don't change the email address, just ignore the request
			}
			
			but.setEmail(userEmailBean.getEmail());
			but.setFormFiledDate(new Date());
				
			BalloonUserTrackingLocalServiceUtil.updateBalloonUserTracking(but);
				
			// create link to be used by user
			BalloonLink link = BalloonLinkLocalServiceUtil.createBalloonLink(UUID.randomUUID().toString());
			link.setBalloonUserUuid(but.getUuid());
			link.setCreateDate(new Date());
			link.setTargetUrl(String.format(BALLOON_LINK_PATTERN, link.getUuid()));
				
			BalloonLinkLocalServiceUtil.addBalloonLink(link);
			response.sendRedirect("/balloon/-/balloon/" + link.getUuid());
			
		}
	}
	
	private BalloonLink getLinkForBalloonUserTracking(BalloonUserTracking but) throws SystemException {
		BalloonLink link = BalloonLinkLocalServiceUtil.getBalloonLinkForUser(but.getUuid());
		
		if (link == null) {
			link = BalloonLinkLocalServiceUtil.createBalloonLink(UUID.randomUUID().toString());
			link.setBalloonUserUuid(but.getUuid());
			link.setCreateDate(new Date());
			link.setTargetUrl(String.format(BALLOON_LINK_PATTERN, link.getUuid()));
			
			BalloonLinkLocalServiceUtil.addBalloonLink(link);
		}
		
		return link;
	}

}
