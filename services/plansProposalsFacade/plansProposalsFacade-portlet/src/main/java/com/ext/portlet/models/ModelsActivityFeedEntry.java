/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;


public class ModelsActivityFeedEntry extends BaseSocialActivityInterpreter{
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {
		
		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);
		String receiverUserName = getUserName(
			activity.getReceiverUserId(), themeDisplay);

		User creatorUser = UserLocalServiceUtil.getUserById(
			activity.getUserId());
		User receiverUser=null;
		if(activity.getReceiverUserId()!=0)
		{
			receiverUser = UserLocalServiceUtil.getUserById(
			activity.getReceiverUserId());
		}
		
		int activityType = activity.getType();
		
		
		// Link

		StringBuilder sb = new StringBuilder();

		sb.append(themeDisplay.getURLPortal());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);
		sb.append(creatorUser.getScreenName());
		sb.append("/friends");

		String link = sb.toString();

		// Title

		String title = StringPool.BLANK;
		String body =  StringPool.BLANK;
		
		if (activityType == ModelsActivityKeys.EDIT_POSITIONS) {
			sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getURLPortal());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(StringPool.SLASH);
			sb.append(creatorUser.getScreenName());
			sb.append("/profile\">");
			sb.append(creatorUserName);
			sb.append("</a>");

			String creatorUserNameURL = sb.toString();

			sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getURLPortal());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(StringPool.SLASH);
			if(receiverUser!=null)
			sb.append(receiverUser.getScreenName());
			sb.append("/profile\">");
			sb.append(receiverUserName);
			sb.append("</a>");

			//String receiverUserNameURL = sb.toString();

			

			sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getURLPortal());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(StringPool.SLASH);
			if(receiverUser!=null)
			sb.append(receiverUser.getScreenName());
			sb.append("/profile\">");
			sb.append(receiverUserName);
			sb.append("</a>");
			
			// Body
			body = "time when model positions added";

			
			title = creatorUserNameURL+" added positions on model "+activity.getClassPK();
		}

		

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		//EditPlanAction.class.getName()
	};

}
