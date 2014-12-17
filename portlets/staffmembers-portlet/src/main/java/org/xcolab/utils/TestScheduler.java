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

public class TestScheduler implements MessageListener {

    private static final Long COMPANY_ID = 10112L;

    private static final String EXECUTE_SCHEDULER_PATH = "/c/portal/schedulerSendEmailNotifications";

    private static final String SERVER_PORT_PROPS_KEY = "climatecolab.server.port";

    @Override
    public void receive(Message message) throws MessageListenerException {
        System.out.println("digest-debug: SendEmailNotifications line 30 reached");

        String requestUrl = null;
        try {
            Company company = CompanyLocalServiceUtil.getCompany(COMPANY_ID);
            System.out.println("digest-debug: SendEmailNotifications line 35 reached");

            // Workaround to get the right port (80) on production
            int port = GetterUtil.getInteger(PortletProps.get(SERVER_PORT_PROPS_KEY));
            if (Validator.isNull(port) || port <= 0) {
                port = PortalUtil.getPortalPort(false);
            }
            System.out.println("digest-debug: SendEmailNotifications line 42 reached");

            String baseUrl = PortalUtil.getPortalURL(company.getVirtualHostname(), port, false);
            //String baseUrl = "http://localhost:8080";

            requestUrl = baseUrl + EXECUTE_SCHEDULER_PATH;
            URL url = new URL(baseUrl + EXECUTE_SCHEDULER_PATH);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            System.out.println("digest-debug: SendEmailNotifications line 50 reached");

            con.setRequestMethod("GET");
            int statusCode = con.getResponseCode();

            System.out.println("digest-debug: SendEmailNotifications line 55 reached, started request to scheduler path. Return status code: "+statusCode+" URL called is: "+baseUrl + EXECUTE_SCHEDULER_PATH);

            if (statusCode != 200) {
                throw new MessageListenerException(String.format("Could not process request: Bad request: (%s)", requestUrl));
            }
        } catch (IOException | SystemException | PortalException e) {
            e.printStackTrace();
            System.out.println("digest-debug: line 61 reached, exception thrown: "+e.getMessage());
            throw new MessageListenerException(String.format("Could not process request (%s)", requestUrl), e);
        }
    }

}
