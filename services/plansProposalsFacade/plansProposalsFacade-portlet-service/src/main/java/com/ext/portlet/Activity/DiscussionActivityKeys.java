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
	ADD_COMMENT("Discussion post added"), 
	ADD_DISCUSSION_COMMENT("Comment added");

    /**
     * Used to describe the action of a proposal discussion activity
     */
    public static final String PROPOSAL_DISCUSSION_FORMAT_STRING = "Proposal %s";

	private String prettyName;
	
	private DiscussionActivityKeys(String name) {
		this.prettyName = name;
	}
	
	public String getPrettyName() {
		return prettyName;
	}
	
	public int id() {
		return ordinal();
	}
	
	public static DiscussionActivityKeys fromId(int id) {
		return DiscussionActivityKeys.values()[id];
	}
	
	public void subcribe(long userid, long entityid, SubscriptionProvider service) throws SystemException {
		if (this == ALL) {
			for (DiscussionActivityKeys key:values()) {
				if (key == ALL) continue;
				else key.subcribe(userid,entityid,service);
			}
		}
		service.createSubscription("debates", userid, entityid,ordinal());
	}

	public void unsubcribe(long userid, long entityid, SubscriptionProvider service) throws SystemException {
		if (this == ALL) {
			for (DiscussionActivityKeys key:values()) {
				if (key == ALL) continue;
				else key.unsubcribe(userid,entityid,service);
			}
		}
		service.deleteSubscription("debates", userid, entityid,ordinal());
		
	}

     public boolean isSubscribed(long userid, long entityid, SubscriptionProvider service) throws SystemException {
       if (this == ALL) {
			for (DiscussionActivityKeys key:values()) {
				if (key == ALL) continue;
				if (!key.isSubscribed(userid,entityid,service)) return false;
			}
		}
		boolean result = service.isSubscribed("debates", userid, entityid,ordinal());
        return result;
     }
	
}
	
	
	
//	public static final int ADD_QUESTION = 1;
//	public static final int EDIT_QUESTION = 2;
//	public static final int REMOVE_QUESTION = 3;
//	
//	public static final int ADD_POSITION = 4;
//	public static final int EDIT_POSITION = 5;
//	public static final int REMOVE_POSITION = 6;
//	
//	public static final int ADD_ARGUMENT = 7;
//	public static final int EDIT_ARGUMENT = 8;
//	public static final int REMOVE_ARGUMENT = 9;
//	
//	public static final int VOTE_FOR_POSITION = 10;
//	public static final int RETRACT_VOTE_FOR_POSITION = 11;
	

