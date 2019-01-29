package org.xcolab.view.pages.unsubscribe;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;

interface NotificationUnregisterHandler {

    void unregister(UserWrapper user);

    String getSuccessResponse();
}
