/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.community;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class CommunityUtil {

	public static String generateUserURL(long userId) throws PortalException, SystemException {
		if (userId <= 0) {
			return StringPool.BLANK;
		}
		User u = UserLocalServiceUtil.getUserById(userId);
		return "<a href='" + generateUserHref(userId)+ "'>" + u.getScreenName()+ "</a>";
	}
	
	private static String generateUserHref(long userId) throws PortalException, SystemException {
		if (userId <= 0) {
			return StringPool.BLANK;
		}
		return CommunityConstants.USER_PROFILE_PATH + userId;
	}

}
