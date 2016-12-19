package org.xcolab.hooks.climatecolab.strutsaction;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.StringPool;

import org.xcolab.entity.utils.activityEntry.ActivitySubscriptionEmailHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SchedulerDispatchStrutsAction extends BaseStrutsAction {

	private static final Log _log = LogFactoryUtil
			.getLog(SchedulerDispatchStrutsAction.class);

	private static final String LOCAL_IPv4_ADDRESS = "127.0.0.1";

	private static final String LOCAL_IPv6_ADDRESS = "0:0:0:0:0:0:0:1";
	private static final Object mutex = new Object();
	private boolean isRunning;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws MessageListenerException {

		synchronized (mutex) {
			if (isRunning) {
                _log.warn("Activity email notification sending (instant notifications / daily digest) is already running!");
				// if task is already running don't run it again
				return StringPool.BLANK;
			}
			isRunning = true;
		}

		String clientIp = request.getRemoteAddr();
		if (!(clientIp.equals(LOCAL_IPv4_ADDRESS) || clientIp
				.equals(LOCAL_IPv6_ADDRESS))) {
			_log.warn(String
					.format("Denied request from address %s!", clientIp));
			resetIsRunning();
			return null;
		}

		ActivitySubscriptionEmailHelper.sendEmailNotifications();

		resetIsRunning();

		return StringPool.BLANK;

	}

	private void resetIsRunning() {
		synchronized (mutex) {
			isRunning = false;
		}
	}
}