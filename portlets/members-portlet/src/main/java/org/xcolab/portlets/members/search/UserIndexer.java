/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.xcolab.portlets.members.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import org.xcolab.portlets.members.MemberCategory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.DefaultFullNameGenerator;
import com.liferay.portal.security.auth.FullNameGenerator;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

/**
 * @author Raymond Augé
 * @author Zsigmond Rab
 * @author Hugo Huijser
 */
public class UserIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {User.class.getName()};

	public static final String PORTLET_ID = PortletKeys.USERS_ADMIN;

    private static Map<Long, MemberCategory> roleIdToCategoryMap;
    private final static long DEFAULT_COMPANY_ID = 10112L;
    private static Log _log = LogFactoryUtil.getLog(UserIndexer.class);

	public UserIndexer() {
		setStagingAware(false);
	}

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	public boolean isIndexerEnabled() {
		return true;
	}

	@Override
	public boolean isPermissionAware() {
		return _PERMISSION_AWARE;
	}

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		int status = GetterUtil.getInteger(
			searchContext.getAttribute(Field.STATUS),
			WorkflowConstants.STATUS_APPROVED);

		if (status != WorkflowConstants.STATUS_ANY) {
			contextQuery.addRequiredTerm(Field.STATUS, status);
		}

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				if (value == null) {
					continue;
				}

				Class<?> clazz = value.getClass();

				if (clazz.isArray()) {
					Object[] values = (Object[])value;

					if (values.length == 0) {
						continue;
					}
				}

				addContextQueryParams(contextQuery, searchContext, key, value);
			}
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, "city", false);
		addSearchTerm(searchQuery, searchContext, "country", false);
		addSearchTerm(searchQuery, searchContext, "emailAddress", false);
		addSearchTerm(searchQuery, searchContext, "firstName", false);
		addSearchTerm(searchQuery, searchContext, "fullName", false);
		addSearchTerm(searchQuery, searchContext, "lastName", false);
		addSearchTerm(searchQuery, searchContext, "middleName", false);
		addSearchTerm(searchQuery, searchContext, "region", false);
		addSearchTerm(searchQuery, searchContext, "screenName", false);
		addSearchTerm(searchQuery, searchContext, "street", false);
		addSearchTerm(searchQuery, searchContext, "zip", false);

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String)params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	protected void addContextQueryParams(
			BooleanQuery contextQuery, SearchContext searchContext, String key,
			Object value)
		throws Exception {

		if (key.equals("usersOrgs")) {
			if (value instanceof Long[]) {
				Long[] values = (Long[])value;

				BooleanQuery usersOrgsQuery = BooleanQueryFactoryUtil.create(
					searchContext);

				for (long organizationId : values) {
					usersOrgsQuery.addTerm("organizationIds", organizationId);
					usersOrgsQuery.addTerm(
						"ancestorOrganizationIds", organizationId);
				}

				contextQuery.add(usersOrgsQuery, BooleanClauseOccur.MUST);
			}
			else {
				contextQuery.addRequiredTerm(
					"organizationIds", String.valueOf(value));
			}
		}
		else if (key.equals("usersOrgsCount")) {
			contextQuery.addRequiredTerm(
				"organizationCount", String.valueOf(value));
		}
		else if (key.equals("usersRoles")) {
			contextQuery.addRequiredTerm("roleIds", String.valueOf(value));
		}
		else if (key.equals("usersTeams")) {
			contextQuery.addRequiredTerm("teamIds", String.valueOf(value));
		}
		else if (key.equals("usersUserGroups")) {
			contextQuery.addRequiredTerm("userGroupIds", String.valueOf(value));
		}
	}

	protected void addReindexCriteria(
		DynamicQuery dynamicQuery, long companyId) {

		Property property = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(property.eq(companyId));
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		User user = (User)obj;

		deleteDocument(user.getCompanyId(), user.getUserId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		User user = (User)obj;

		Document document = getBaseModelDocument(PORTLET_ID, user);

		long[] organizationIds = user.getOrganizationIds();

		document.addKeyword(Field.COMPANY_ID, user.getCompanyId());
		document.addDate(Field.MODIFIED_DATE, user.getModifiedDate());
		document.addKeyword(Field.STATUS, user.getStatus());
		document.addKeyword(Field.USER_ID, user.getUserId());
		document.addKeyword(Field.USER_NAME, user.getFullName());

		document.addKeyword(
			"ancestorOrganizationIds",
			getAncestorOrganizationIds(
				user.getUserId(), user.getOrganizationIds()));
		document.addText("emailAddress", user.getEmailAddress());
		document.addText("firstName", user.getFirstName());
		document.addText("fullName", user.getFullName());
		document.addKeyword("groupIds", user.getGroupIds());
		document.addText("jobTitle", user.getJobTitle());
		document.addText("lastName", user.getLastName());
		document.addText("middleName", user.getMiddleName());
		document.addKeyword("organizationIds", organizationIds);
		document.addKeyword(
			"organizationCount", String.valueOf(organizationIds.length));
		document.addKeyword("roleIds", user.getRoleIds());
		document.addText("screenName", user.getScreenName());
		document.addKeyword("teamIds", user.getTeamIds());
		document.addKeyword("userGroupIds", user.getUserGroupIds());
		document.addDate("joinDate", user.getCreateDate());
        document.addNumber("activities", ActivityUtil.getActivitiesCount(user.getUserId()));
		document.addKeyword("memberCategory", getUserCategories(user));
        document.addKeyword("realName", new DefaultFullNameGenerator().getFullName(
                user.getFirstName(),  user.getMiddleName(),  user.getLastName()));

		populateAddresses(document, user.getAddresses(), 0, 0);

		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (orderByCol.equals("email-address")) {
			return "emailAddress";
		}
		else if (orderByCol.equals("first-name")) {
			return "firstName";
		}
		else if (orderByCol.equals("job-title")) {
			return "jobTitle";
		}
		else if (orderByCol.equals("last-name")) {
			return "lastName";
		}
		else if (orderByCol.equals("screen-name")) {
			return "screenName";
		}
		else {
			return orderByCol;
		}
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		String firstName = document.get("firstName");
		String middleName = document.get("middleName");
		String lastName = document.get("lastName");

		FullNameGenerator fullNameGenerator = new DefaultFullNameGenerator();

		String title = fullNameGenerator.getFullName(
			firstName, middleName, lastName);

		String content = null;

		String userId = document.get(Field.USER_ID);

		portletURL.setParameter("struts_action", "/users_admin/edit_user");
		portletURL.setParameter("p_u_i_d", userId);

		return new Summary(title, content, portletURL);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		if (obj instanceof List<?>) {
			List<User> users = (List<User>)obj;

			for (User user : users) {
				doReindex(user);
			}
		}
		else if (obj instanceof Long) {
			long userId = (Long)obj;

			User user = UserLocalServiceUtil.getUserById(userId);

			doReindex(user);
		}
		else if (obj instanceof long[]) {
			long[] userIds = (long[])obj;

			Map<Long, Collection<Document>> documentsMap =
				new HashMap<Long, Collection<Document>>();

			for (long userId : userIds) {
				User user = UserLocalServiceUtil.getUserById(userId);

				if (user.isDefaultUser()) {
					continue;
				}

				Document document = getDocument(user);

				long companyId = user.getCompanyId();

				Collection<Document> documents = documentsMap.get(companyId);

				if (documents == null) {
					documents = new ArrayList<Document>();

					documentsMap.put(companyId, documents);
				}

				documents.add(document);
			}

			for (Map.Entry<Long, Collection<Document>> entry :
					documentsMap.entrySet()) {

				long companyId = entry.getKey();
				Collection<Document> documents = entry.getValue();

				SearchEngineUtil.updateDocuments(
					getSearchEngineId(), companyId, documents);
			}
		}
		else if (obj instanceof User) {
			User user = (User)obj;

			if (user.isDefaultUser()) {
				return;
			}

			Document document = getDocument(user);

			SearchEngineUtil.updateDocument(
				getSearchEngineId(), user.getCompanyId(), document);
		}
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		User user = UserLocalServiceUtil.getUserById(classPK);

		doReindex(user);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexUsers(companyId);
	}

	protected long[] getAncestorOrganizationIds(
			long userId, long[] organizationIds)
		throws Exception {

		List<Organization> ancestorOrganizations =
			new ArrayList<Organization>();

		for (long organizationId : organizationIds) {
			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(organizationId);

			ancestorOrganizations.addAll(organization.getAncestors());
		}

		long[] ancestorOrganizationIds = new long[ancestorOrganizations.size()];

		for (int i = 0; i < ancestorOrganizations.size(); i++) {
			Organization ancestorOrganization = ancestorOrganizations.get(i);

			ancestorOrganizationIds[i] =
				ancestorOrganization.getOrganizationId();
		}

		return ancestorOrganizationIds;
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindexUsers(long companyId) throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);

		Projection minUserIdProjection = ProjectionFactoryUtil.min("userId");
		Projection maxUserIdProjection = ProjectionFactoryUtil.max("userId");

		ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

		projectionList.add(minUserIdProjection);
		projectionList.add(maxUserIdProjection);

		dynamicQuery.setProjection(projectionList);

		addReindexCriteria(dynamicQuery, companyId);

		List<Object[]> results = UserLocalServiceUtil.dynamicQuery(
			dynamicQuery);

		Object[] minAndMaxUserIds = results.get(0);

		if ((minAndMaxUserIds[0] == null) || (minAndMaxUserIds[1] == null)) {
			return;
		}

		long minUserId = (Long)minAndMaxUserIds[0];
		long maxUserId = (Long)minAndMaxUserIds[1];

		long startUserId = minUserId;
		long endUserId = startUserId + DEFAULT_INTERVAL;

		while (startUserId <= maxUserId) {
			reindexUsers(companyId, startUserId, endUserId);

			startUserId = endUserId;
			endUserId += DEFAULT_INTERVAL;
		}
	}

	protected void reindexUsers(
			long companyId, long startUserId, long endUserId)
		throws Exception {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);

		Property property = PropertyFactoryUtil.forName("userId");

		dynamicQuery.add(property.ge(startUserId));
		dynamicQuery.add(property.lt(endUserId));

		addReindexCriteria(dynamicQuery, companyId);

		List<User> users = UserLocalServiceUtil.dynamicQuery(dynamicQuery);

		if (users.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<Document>(users.size());

		for (User user : users) {
			if (user.isDefaultUser()) {
				continue;
			}

			Document document = getDocument(user);

			documents.add(document);
		}

		SearchEngineUtil.updateDocuments(
			getSearchEngineId(), companyId, documents);
	}

	private static final boolean _PERMISSION_AWARE = true;
	
	   private static String[] getUserCategories(User user) throws SystemException {
	        initRoleIdToCategoryMap();
	        List<String> categories = new ArrayList<String>();
	        
	        for (Long roleId: user.getRoleIds()) {
	            
	                
	            if (roleIdToCategoryMap.containsKey(roleId)) {
	                categories.add(roleIdToCategoryMap.get(roleId).name().toLowerCase());
	            }
	        }
	        String[] ret = new String[categories.size()];
	        return categories.toArray(ret);
	    }
	    
	    private static synchronized void initRoleIdToCategoryMap() {
	            roleIdToCategoryMap = new HashMap<Long, MemberCategory>();

	            for (MemberCategory category : MemberCategory.values()) {
	                try {
	                    if (category.equals(MemberCategory.ALL)) continue;
	                    for (String roleName: category.getRoleNames()) {
	                        Role role = RoleLocalServiceUtil.getRole(DEFAULT_COMPANY_ID, roleName);
	                        roleIdToCategoryMap.put(role.getRoleId(), category);
	                    }
	                } catch (PortalException e) {
	                    _log.error("Can't find role for user category " + category, e);
	                } catch (SystemException e) {
	                    _log.error("Can't find role for user category " + category, e);
	                }
	        }
	    }

}