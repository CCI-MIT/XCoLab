package com.ext.utils.subscriptions;

import java.util.List;

/**
 * Created by kmang on 08/05/14.
 */
public interface ActivitySubscriptionWhitelistHandler {
    public List<Long> getWhitelistedUsers(long classPk);
}
