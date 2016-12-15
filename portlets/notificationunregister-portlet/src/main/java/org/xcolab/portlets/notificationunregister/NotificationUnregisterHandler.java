package org.xcolab.portlets.notificationunregister;

import org.xcolab.client.members.pojo.Member;

interface NotificationUnregisterHandler {
    void unregister(Member user);
    String getSuccessResponse();
}
