/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans.activity;



import java.util.HashMap;
import java.util.Map;

import com.ext.portlet.Activity.ICollabActivityInterpreter;
import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;


public class PlansActivityFeedEntry extends BaseSocialActivityInterpreter implements ICollabActivityInterpreter {

    private static Log _log =
		 LogFactoryUtil.getLog(PlansActivityFeedEntry.class);

	public static String PLAN_ADDED = "%s added proposal %s";
	public static String PLAN_REMOVED = "%s removed proposal %s";
	public static String PLAN_POSITIONS_UPDATED = "%s updated positions in proposal %s";
	public static String PLAN_SCENARIO_UPDATED = "%s updated the model run in proposal %s";
	public static String PLAN_DESCRIPTION_UPDATED="%s updated the description in proposal %s";
	public static String PLAN_VOTED = "%s voted for proposal %s";
	public static String PLAN_UNVOTED = "%s retracted vote for proposal %s";
	public static String PLAN_VOTE_SWITCHED="%s switched vote to proposal %s";
	public static String PLAN_USER_ADDED="%s joined proposal %s";
	public static String PLAN_USER_REMOVED = "%s left proposal %s";
	public static String PLAN_PUBLISHED = "%s published proposal %s";
    public static String PLAN_NAME_UPDATED="%s updated the name in proposal %s";
    public static String PLAN_SUPPORTED="%s became a supporter of proposal %s";
    public static String PLAN_UNSUPPORTED="%s is no longer a supporter of proposal %s";
    public static String CHANGE_IMAGE="%s changed image of proposal %s";

	public static Map<PlanActivityKeys,String> msgMap = new HashMap<PlanActivityKeys,String>();
	static {
		msgMap.put(PlanActivityKeys.ADD_PLAN,PLAN_ADDED);
		msgMap.put(PlanActivityKeys.EDIT_DESCRIPTION,PLAN_DESCRIPTION_UPDATED);
		msgMap.put(PlanActivityKeys.EDIT_POSITIONS, PLAN_POSITIONS_UPDATED);
		msgMap.put(PlanActivityKeys.EDIT_SCENARIO,PLAN_SCENARIO_UPDATED);
		msgMap.put(PlanActivityKeys.VOTE_FOR_PLAN, PLAN_VOTED);
		msgMap.put(PlanActivityKeys.RETRACT_VOTE_FOR_PLAN, PLAN_UNVOTED);
		msgMap.put(PlanActivityKeys.SWICTH_VOTE_FOR_PLAN, PLAN_VOTE_SWITCHED);
		msgMap.put(PlanActivityKeys.USER_ADDED_TO_PLAN, PLAN_USER_ADDED);
		msgMap.put(PlanActivityKeys.USER_REMOVED_FROM_PLAN,PLAN_USER_REMOVED);
		msgMap.put(PlanActivityKeys.PUBLISH_UPDATES, PLAN_PUBLISHED);
        msgMap.put(PlanActivityKeys.EDIT_NAME, PLAN_NAME_UPDATED);
        msgMap.put(PlanActivityKeys.REMOVE_PLAN, PLAN_REMOVED);
        msgMap.put(PlanActivityKeys.BECOME_A_SUPPORTER, PLAN_SUPPORTED);
        msgMap.put(PlanActivityKeys.STOPPED_BEEING_A_SUPPORTER, PLAN_UNSUPPORTED);
        msgMap.put(PlanActivityKeys.CHANGE_IMAGE, CHANGE_IMAGE);

	}

	public static String hyperlink = "<a href=\"%s\">%s</a>";

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}


	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PlanActivityKeys activityType = PlanActivityKeys.fromId(activity.getType());
		String title = activityType.getPrettyName();
		String body = "";
		if (msgMap.containsKey(activityType)) {
			body = String.format(msgMap.get(activityType),getUser(activity),getPlan(activity));
		}

		return new SocialActivityFeedEntry("", title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
	    "com.ext.portlet.Activity", "com.ext.portlet.plans.model.PlanItem"
	};

	private String getUser(SocialActivity activity) {
		String user = "&lt;user removed&gt;";

			try {
				user = CommunityUtil.generateUserURL(activity.getUserId());
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				_log.info(e.getMessage());
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				_log.info(e.getMessage());
			}
		return user;
	}

	private String getPlan(SocialActivity activity) {
		String result = "&lt;plan removed&gt;";
		try {
			PlanItem p = PlanItemLocalServiceUtil.getPlan(activity.getClassPK());
			result = String.format(hyperlink, getPlanURL(p),PlanItemLocalServiceUtil.getName(p));
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			_log.info(e.getMessage());
		} catch (SystemException e) {
			_log.info(e.getMessage());
		}
		return result;

	}
	

    private static String getPlanURL(PlanItem p) throws SystemException, PortalException {
        return String.format("/web/guest/plans/-/plans/contestId/" + PlanItemLocalServiceUtil.getContest(p).getContestPK() + "/planId/" + p.getPlanId());
    }


    @Override
    public String getName(Long classNameId, Long classPK, Integer type, String extraData) {
        // name of activity "stream" for given parameters is name of a plan that this activity relates to
        try {
            PlanItem plan = PlanItemLocalServiceUtil.getPlan(classPK);
            return "Proposal: " + String.format(hyperlink, getPlanURL(plan),PlanItemLocalServiceUtil.getName(plan));
        }
        catch (NoSuchPlanItemException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        } catch (SystemException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        } catch (PortalException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        }
        
        return "";
        
    }

}
