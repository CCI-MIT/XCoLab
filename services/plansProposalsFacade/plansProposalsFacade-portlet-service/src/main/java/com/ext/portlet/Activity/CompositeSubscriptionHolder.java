/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.Activity.model.ActivitySubscription;

public class CompositeSubscriptionHolder {
	
	private long entityId;
	private String portletId;
	private String hash;
	
	private List<ActivitySubscription> subscriptions = new ArrayList<ActivitySubscription>();
	
	
	public CompositeSubscriptionHolder(ActivitySubscription s) {
		//this.portletId = s.getPortletId();
		//this.entityId = s.getEntityId();
		this.hash = createHash(s);
		subscriptions.add(s);
	}
	
	public long getEntityId() {
		return entityId;
	}
	
	public String getPortletId() {
		return portletId;
	}
	
	public String getHash() {
		return hash;
	}
	
	public boolean addSubscription(ActivitySubscription s) {
		if (s!=null && createHash(s).equals(hash)) {
			subscriptions.add(s);
			return true;
		}
		return false;
	}
	
	public List<ActivitySubscription> getSubscriptions() {
		return subscriptions;
	}
	
	public int hashCode() {
		return (hash.hashCode() * 13)+7;
	}
	
	public boolean equals(Object o)  {
		return ((o instanceof CompositeSubscriptionHolder) && ((CompositeSubscriptionHolder)o).hash.equals(hash));
	}
	
	public static String createHash(ActivitySubscription a) {
		//return a.getEntityId()+a.getPortletId();
	    return String.valueOf(a.hashCode());
	}

}
