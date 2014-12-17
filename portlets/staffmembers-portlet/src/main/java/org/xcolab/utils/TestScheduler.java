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


    @Override
    public void receive(Message message) throws MessageListenerException {
        System.out.println("digest-debug: TestScheduler reached");
	}

}
