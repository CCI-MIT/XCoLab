package org.xcolab.portlets.notificationunregister;

import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.members.pojo.Member;

interface NotificationUnregisterHandler {
    void unregister(Member user) throws SystemException;
    String getSuccessResponse();
}
