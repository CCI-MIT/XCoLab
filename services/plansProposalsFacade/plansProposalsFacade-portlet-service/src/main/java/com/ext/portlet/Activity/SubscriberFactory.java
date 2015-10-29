/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.liferay.portal.kernel.exception.SystemException;



public interface SubscriberFactory {
	
	void subscribe(long userId, long entityId, SubscriptionProvider service) throws SystemException;
	void unsubscribe(long userId, long entityId, SubscriptionProvider service) throws SystemException;
	boolean isSubscribed(long userId, long entityId, SubscriptionProvider service) throws SystemException;
    String getPrettyName();
}
