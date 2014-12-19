/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.community;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

public class CommunityUtil {

	private static Log _log = LogFactoryUtil.getLog(CommunityUtil.class);

	public static void createExpandoTable() throws Exception {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.addTable(
				User.class.getName(), CommunityConstants.EXPANDO);
		}
		catch (DuplicateTableNameException dtne) {
				table = ExpandoTableLocalServiceUtil.getTable(
					User.class.getName(), CommunityConstants.EXPANDO);
				_log.warn("Expando table already exists");
		}
		try {
		ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(),CommunityConstants.BIO,
				ExpandoColumnConstants.STRING);
		

        ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(),CommunityConstants.FB_ID,
				ExpandoColumnConstants.STRING);
		}

		catch (DuplicateColumnNameException dcne) {
			_log.warn("Expando column already exists");
			
		}


	}

	public static String generateUserURL(long userId) throws PortalException, SystemException {
		if (userId <= 0) return StringPool.BLANK;
		User u = UserLocalServiceUtil.getUserById(userId);
		String url = "<a href='" + generateUserHref(userId)+ "'>" + u.getScreenName()+ "</a>";
		return url;
		
	}
	
	public static String generateUserHref(long userId) throws PortalException, SystemException {
		if (userId <= 0) return StringPool.BLANK;
		return CommunityConstants.USER_PROFILE_PATH + userId;
		
	}

}
