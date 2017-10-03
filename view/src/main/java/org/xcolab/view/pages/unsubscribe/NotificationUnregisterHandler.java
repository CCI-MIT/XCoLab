package org.xcolab.view.pages.unsubscribe;

import org.xcolab.client.members.pojo.Member;

interface NotificationUnregisterHandler {

    void unregister(Member user);

    String getSuccessResponse();
}
