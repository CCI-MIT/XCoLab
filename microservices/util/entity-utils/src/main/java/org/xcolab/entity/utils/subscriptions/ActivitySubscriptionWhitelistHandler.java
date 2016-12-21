package org.xcolab.entity.utils.subscriptions;

import java.util.List;

public interface ActivitySubscriptionWhitelistHandler {
    public List<Long> getWhitelistedUsers(long classPk);
}
