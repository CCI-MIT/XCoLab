package org.xcolab.portlets.redballoon.web.action;

import com.ext.portlet.model.BalloonLink;
import com.ext.portlet.model.BalloonText;
import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.service.BalloonLinkLocalServiceUtil;
import com.ext.portlet.service.BalloonTextLocalServiceUtil;
import com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.redballoon.utils.BalloonUtils;
import org.xcolab.portlets.redballoon.web.beans.UserEmailBean;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RequestMapping("view")
@Controller
public class ProcessUserEmailAction {
	
	private final static String BALLOON_LINK_PATTERN = "/socialnetworkprize2015";

	private final static String FROM_ADDRESS = "no-reply@climatecolab.org";	

	private final static String EMAIL_SENT = AddEditBalloonTextAction.class.getName()
			+ "EMAIL_SENT";

	private final static String USER_EMAIL = AddEditBalloonTextAction.class.getName()
			+ "USER_EMAIL";
    public static final String URLPLACEHOLDER = "URLPLACEHOLDER";

    @RequestMapping(params="action=sendEmail")
	public void processUserEmail(ActionRequest request, ActionResponse response, Model model,
			@Valid UserEmailBean userEmailBean, BindingResult bindingResult) throws PortalException, SystemException, IOException, AddressException, MailEngineException {
		
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
			sendNotificationEmail(request, but, link);
			response.sendRedirect("/socialnetworkprize2015/-/link/" + link.getUuid());
			
		}
	}
	

	private void sendNotificationEmail(PortletRequest request, BalloonUserTracking but, BalloonLink link) throws AddressException,
			MailEngineException, PortalException, SystemException {
		
		PortletSession session = request.getPortletSession();
		if (session.getAttributeMap().containsKey(EMAIL_SENT)) 
			return;
		
		if (but.getBalloonTextId() <= 0) 
			return;
		
		BalloonText text = BalloonTextLocalServiceUtil.getBalloonText(but.getBalloonTextId());
		
		String messageSubject = text.getEmailSubjectTemplate();
		String messageBody = text.getEmailTemplate().replaceAll(URLPLACEHOLDER, BalloonUtils.getBalloonUrlForLink(request,  link));

		InternetAddress addressFrom = new InternetAddress(FROM_ADDRESS);

		String mailAdr = session.getAttribute(USER_EMAIL) == null ? but.getEmail() : session.getAttribute(USER_EMAIL).toString();

		if (StringUtils.isBlank(mailAdr))
			return;

		String[] recipients = new String[] { mailAdr };
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}

		InternetAddress replyTo[] = { new InternetAddress(FROM_ADDRESS) };

		MailEngine.send(addressFrom, addressTo, null, null, null,
				messageSubject, messageBody, true, replyTo, null, null);
		session.setAttribute(EMAIL_SENT, true);

	}

}
