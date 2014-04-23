package org.xcolab.hooks.climatecolab.utils;

import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kmang on 18/04/14.
 */
public class SchedulerDispatchStrutsAction extends BaseStrutsAction {

	private static final Log _log = LogFactoryUtil.getLog(SchedulerDispatchStrutsAction.class);

	private static final String LOCAL_IPv4_ADDRESS = "127.0.0.1";

	private static final String LOCAL_IPv6_ADDRESS = "0:0:0:0:0:0:0:1";

	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws MessageListenerException {

		String clientIp = request.getRemoteAddr();
		if (!(clientIp.equals(LOCAL_IPv4_ADDRESS) || clientIp.equals(LOCAL_IPv6_ADDRESS))) {
			_log.warn(String.format("Denied request from address %s!", clientIp));
			throw new RuntimeException("Originating host invalid!");
		}

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setRequest(request);

		try {
			ActivitySubscriptionLocalServiceUtil.sendEmailNotifications(serviceContext);
		}
		catch (SystemException e) {
			throw new MessageListenerException("Could not process email notification of proposal subscription feature", e);
		} catch (PortalException e) {
			throw new MessageListenerException("Could not process email notification of proposal subscription feature", e);
		}

		return StringPool.BLANK;
	}

}