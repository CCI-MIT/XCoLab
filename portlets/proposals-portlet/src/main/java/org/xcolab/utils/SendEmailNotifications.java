package org.xcolab.utils;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendEmailNotifications implements MessageListener {

	private static final Long COMPANY_ID = 10112L;

    @Override
    public void receive(Message message) throws MessageListenerException {
		try {
			Company company = CompanyLocalServiceUtil.getCompany(COMPANY_ID);
			String baseUrl = PortalUtil.getPortalURL(company.getVirtualHostname(), PortalUtil.getPortalPort(false), false);

			URL url = new URL(baseUrl + "/web/guest/plans/-/plans/sendProposalActivityNotifications");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			int statusCode = con.getResponseCode();

			if (statusCode != 200) {
				throw new MessageListenerException("Could not process request: Bad request");
			}

		} catch (IOException | SystemException | PortalException e) {
			throw new MessageListenerException("Could not process request", e);
		}
	}

}
