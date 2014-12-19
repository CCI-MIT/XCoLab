/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.liferay.portal.SystemException;


public interface SubscriberFactory {
	
	public void subcribe(long userid, long entityid, SubscriptionProvider service) throws SystemException;
	public void unsubcribe(long userid, long entityid, SubscriptionProvider service) throws SystemException;
	public boolean isSubscribed(long userid, long entityid, SubscriptionProvider service) throws SystemException;
    public String getPrettyName();
}
