package org.xcolab.view.util.entity.subscriptions;

import java.util.List;

public interface ActivitySubscriptionWhitelistHandler {

    List<Long> getWhitelistedUsers(long classPk);
}
