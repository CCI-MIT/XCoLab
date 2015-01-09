package org.xcolab.utils;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendEmailNotifications implements MessageListener {

	private static final Long COMPANY_ID = 10112L;

	private static final String EXECUTE_SCHEDULER_PATH = "/c/portal/schedulerSendEmailNotifications";

    private static final String SERVER_PORT_PROPS_KEY = "climatecolab.server.port";

    @Override
    public void receive(Message message) throws MessageListenerException {
    	String requestUrl = null;
		try {
			Company company = CompanyLocalServiceUtil.getCompany(COMPANY_ID);

            int port = GetterUtil.getInteger(PortletProps.get(SERVER_PORT_PROPS_KEY));
            if (Validator.isNull(port) || port <= 0) {
                port = PortalUtil.getPortalPort(false);
            }

            String baseUrl = PortalUtil.getPortalURL(company.getVirtualHostname(), port, false);

            requestUrl = baseUrl + EXECUTE_SCHEDULER_PATH;
			URL url = new URL(baseUrl + EXECUTE_SCHEDULER_PATH);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			int statusCode = con.getResponseCode();

			if (statusCode != 200) {
				throw new MessageListenerException(String.format("Could not process request: Bad request: (%s)", requestUrl));
			}
		} catch (IOException | SystemException | PortalException e) {
			throw new MessageListenerException(String.format("Could not process request (%s)", requestUrl), e);
		}
	}

}
