package org.xcolab.portlets.proposals.actions;

import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * Created by Mente on 17/04/14.
 */
@Controller
@RequestMapping("view")
public class SchedulerDispatchController {

	private static final Log _log = LogFactoryUtil.getLog(SchedulerDispatchController.class);

	private static final String LOCAL_IP_ADDRESS = "127.0.0.1";

	@RequestMapping(params = {"action=sendProposalActivityNotifications"})
	public void sendProposalActivityNotifications(ActionRequest request, ActionResponse response, Model model) throws MessageListenerException {
		String clientIp = PortalUtil.getHttpServletRequest(request).getRemoteAddr();

		if (!clientIp.equals(LOCAL_IP_ADDRESS)) {
			throw new RuntimeException("Originating host invalid!");
		}
		// TODO: IP check
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setRequest(PortalUtil.getHttpServletRequest(request));

		try {
			ActivitySubscriptionLocalServiceUtil.sendEmailNotifications(serviceContext);
		}
		catch (SystemException | PortalException e) {
			throw new MessageListenerException("Could not process email notification of proposal subscription feature", e);
		}
	}
}
