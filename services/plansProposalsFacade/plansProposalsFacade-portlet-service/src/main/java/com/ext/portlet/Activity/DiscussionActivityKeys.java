/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.liferay.portal.kernel.exception.SystemException;

public enum DiscussionActivityKeys implements SubscriberFactory {
	
	ALL("A discussion activity"),
	ADD_CATEGORY("Category added"), 
	ADD_DISCUSSION("Discussion created"),
	ADD_FORUM_COMMENT("Discussion post added"),
	ADD_PROPOSAL_DISCUSSION_COMMENT("Comment added");

	private final String prettyName;
	
	DiscussionActivityKeys(String name) {
		this.prettyName = name;
	}
	
	@Override
	public String getPrettyName() {
		return prettyName;
	}
	
	public int id() {
		return ordinal();
	}
	
	public static DiscussionActivityKeys fromId(int id) {
		return DiscussionActivityKeys.values()[id];
	}
	
	@Override
	public void subscribe(long userId, long entityId, SubscriptionProvider service) throws SystemException {
		if (this == ALL) {
			for (DiscussionActivityKeys key:values()) {
				if (key != ALL) {
					key.subscribe(userId, entityId, service);
				}
			}
		}
		service.createSubscription("debates", userId, entityId,ordinal());
	}

	@Override
	public void unsubscribe(long userId, long entityId, SubscriptionProvider service) throws SystemException {
		if (this == ALL) {
			for (DiscussionActivityKeys key:values()) {
				if (key != ALL) {
					key.unsubscribe(userId, entityId, service);
				}
			}
		}
		service.deleteSubscription("debates", userId, entityId,ordinal());
		
	}

     @Override
	 public boolean isSubscribed(long userId, long entityId, SubscriptionProvider service) throws SystemException {
		 if (this == ALL) {
			for (DiscussionActivityKeys key:values()) {
				if (key == ALL) {
					continue;
				}
				if (!key.isSubscribed(userId, entityId,service)) {
					return false;
				}
			}
		 }
		 return service.isSubscribed("debates", userId, entityId,ordinal());
     }
	
}
