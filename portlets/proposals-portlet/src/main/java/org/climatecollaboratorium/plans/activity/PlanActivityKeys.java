/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans.activity;

import com.ext.portlet.Activity.SubscriberFactory;
import com.ext.portlet.Activity.SubscriptionProvider;
import com.liferay.portal.kernel.exception.SystemException;


public enum PlanActivityKeys implements SubscriberFactory {

	ALL("A plan activity"),
	ADD_PLAN("Plan added"), EDIT_PLAN("Plan edited"), EDIT_DESCRIPTION("Plan description edited"),
	PUBLISH_UPDATES("Plan published"), EDIT_SCENARIO("Plan model run changed"),
	EDIT_POSITIONS("Plan positions changed"), VOTE_FOR_PLAN("Voted for plan"),
	SWICTH_VOTE_FOR_PLAN("Changed vote to another plan"), RETRACT_VOTE_FOR_PLAN("Retrated vote from plan"),
	USER_ADDED_TO_PLAN("User joined plan"), USER_REMOVED_FROM_PLAN("User removed from plan"),
	EDIT_NAME("Plan name edited"), REMOVE_PLAN("Plan removed"), BECOME_A_SUPPORTER("Became a supporter"), 
	STOPPED_BEEING_A_SUPPORTER("Is no longer a supporter"), PROMOTE_PLAN("Plan promoted to next phase"), 
	CHANGE_IMAGE("Changed an image");

	private String prettyName;

	private PlanActivityKeys(String name) {
		this.prettyName = name;
	}

	public String getPrettyName() {
		return prettyName;
	}

	public int id() {
		return ordinal();
	}

	public static PlanActivityKeys fromId(int id) {
		return PlanActivityKeys.values()[id];
	}

	public void subcribe(long userid, long entityid, SubscriptionProvider service) throws SystemException {
		if (this == ALL) {
			for (PlanActivityKeys key:values()) {
				if (key == ALL) continue;
				else key.subcribe(userid,entityid,service);
			}
		}
		service.createSubscription("plans", userid, entityid,ordinal());
	}

	public void unsubcribe(long userid, long entityid, SubscriptionProvider service) throws SystemException {
		if (this == ALL) {
			for (PlanActivityKeys key:values()) {
				if (key == ALL) continue;
				else key.unsubcribe(userid,entityid,service);
			}
		}
		service.deleteSubscription("plans", userid, entityid,ordinal());

	}

    @Override
    public boolean isSubscribed(long userid, long entityid, SubscriptionProvider service) throws SystemException {
       if (this == ALL) {
			for (PlanActivityKeys key:values()) {
				if (key == ALL) continue;
				if (!key.isSubscribed(userid,entityid,service)) return false;
			}
		}
		return service.isSubscribed("plans", userid, entityid,ordinal());
    }
}

//
//	public static final int ADD_PLAN = 1;
//	public static final int EDIT_PLAN = 2;
//	public static final int EDIT_DESCRIPTION = 3;
//	public static final int PUBLISH_UPDATES = 4;
//	//public static final int ADD_SCENARIO = 4;
//	public static final int EDIT_SCENARIO = 5;
//	public static final int EDIT_POSITIONS = 6;
//	public static final int VOTE_FOR_PLAN = 7;
//	public static final int SWICTH_VOTE_FOR_PLAN = 8;
//	public static final int RETRACT_VOTE_FOR_PLAN = 9;
//	public static final int USER_ADDED_TO_PLAN = 10;
//	public static final int USER_REMOVED_FROM_PLAN = 11;
//}