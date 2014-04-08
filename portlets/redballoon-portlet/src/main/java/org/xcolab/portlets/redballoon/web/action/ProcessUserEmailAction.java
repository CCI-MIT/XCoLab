package org.xcolab.portlets.redballoon.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.redballoon.utils.BalloonUtils;
import org.xcolab.portlets.redballoon.web.beans.UserEmailBean;

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

@RequestMapping("view")
@Controller
public class ProcessUserEmailAction {
	
	private final static String BALLOON_LINK_PATTERN = "/balloon";

	private final static String FROM_ADDRESS = "no-reply@climatecolab.org";	
	private final static String EMAIL_SUBJECT = "Help find the winner of MIT's Climate CoLab Grand Prize!";
	private final static String EMAIL_BODY = "<p>Here is your unique link, which you can use to tell your friends "
			+ "and family about the MIT Climate CoLab contest:</p>\n\n<p><a href=\"URL_PLACEHOLDER/e\""
			+ ">URL_PLACEHOLDER</a></p>\n<a href=\"http://www.facebook.com/share.php?u=URL_PLACEHOLDER/f\" "
			+ "title=\"Facebook\"><img src=\"http://inventas-it.ch/mit/fb.png\" height=\"30\" border=\"0\" /></a> "
			+ "<a href=\"http://twitter.com/share?url=URL_PLACEHOLDER/t\"><img src=\"http://inventas-it.ch/mit/twitter.png\" "
			+ "height=\"33\" border=\"0\" /></a>\n\n<p>Post it on blogs, send it over email, or share it through Facebook or "
			+ "Twitter to create a chain of referrals. Remember: If your sharing eventually leads to the winner of the $10,000 "
			+ "Grand Prize, you\'ll win some of the $2,000 referral prize, even if that person is up to 10 degrees of separation "
			+ "from you!</p>\n\n<p>For your convenience, we\'ve included a blurb about the contest below for you to send to your "
			+ "friends, but you can spread the word any way you\'d like (just remember to include your unique link, so you can"
			+ " win the referral prize!)</p>\n\n<br/>\n<p><strong>MIT\'s Climate CoLab: Your ideas can help combat climate change, "
			+ "and you might win $10,000</strong></p>\n<p>At MIT\u2019s Climate CoLab you can work with people from all over the "
			+ "world to develop ideas for what we can actually do about climate change.</p>\n \n<p>If you submit one of the winning "
			+ "ideas, you\u2019ll be able to present it to the media, government officials, business executives, and scientists at an "
			+ "MIT conference on November 6-7, where a grand prize of $10,000 will be awarded.</p>\n\n \n<p>Even if you don\u2019t have "
			+ "new ideas yourself, you can help improve other people\u2019s ideas and support the ones you find most promising.  "
			+ "And that\'s not all: If you refer one of your friends or colleagues to the contest via Facebook, Twitter or e-mail, and "
			+ "one of them\u2014or a friend of a friend, or friend of a friend of a friend, etc.\u2014wins the Grand Prize, you\u2019ll "
			+ "receive a share of a $2,000 referral prize!</p>\n \n<p>Current contests address low-carbon energy, building efficiency, "
			+ "adaptation, geoengineering, and many other topics. Entries are due June 15th.</p>";
	
	private final static String EMAIL_SENT = AddEditBalloonTextAction.class.getName()
			+ "EMAIL_SENT";

	private final static String USER_EMAIL = AddEditBalloonTextAction.class.getName()
			+ "USER_EMAIL";
	
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
			response.sendRedirect("/balloon/-/balloon/" + link.getUuid());
			
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
		String messageBody = text.getEmailTemplate().replaceAll("URLPLACEHOLDER", BalloonUtils.getBalloonUrlForLink(request,  link));

		InternetAddress addressFrom = new InternetAddress(FROM_ADDRESS);

		String mailAdr = session.getAttribute(USER_EMAIL) == null ? but.getEmail() : session.getAttribute(USER_EMAIL).toString();

		if (StringUtils.isBlank(mailAdr))
			return;

		String[] receipients = new String[] { mailAdr };
		InternetAddress[] addressTo = new InternetAddress[receipients.length];
		for (int i = 0; i < receipients.length; i++) {
			addressTo[i] = new InternetAddress(receipients[i]);
		}

		InternetAddress replyTo[] = { new InternetAddress(FROM_ADDRESS) };

		MailEngine.send(addressFrom, addressTo, null, null, null,
				messageSubject, messageBody, true, replyTo, null, null);
		session.setAttribute(EMAIL_SENT, true);

	}

}
