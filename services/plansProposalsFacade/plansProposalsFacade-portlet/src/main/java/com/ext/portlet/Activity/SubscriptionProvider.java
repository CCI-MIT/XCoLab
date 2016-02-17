/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.liferay.portal.kernel.exception.SystemException;

public interface SubscriptionProvider {
	public void createSubscription(String portlet, long userid, long entityid, int type) throws SystemException;
	public void deleteSubscription(String portlet, long userid, long entityid, int type) throws SystemException;
    public boolean isSubscribed(String portlet, long userid, long entityid, int type) throws SystemException;
}
