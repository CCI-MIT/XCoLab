package com.ext.utils.subscriptions;

import java.util.List;

public interface ActivitySubscriptionWhitelistHandler {
    public List<Long> getWhitelistedUsers(long classPk);
}
