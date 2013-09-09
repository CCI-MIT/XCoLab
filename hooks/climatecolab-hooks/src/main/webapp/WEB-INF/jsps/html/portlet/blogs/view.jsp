<%--
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
--%>

<%@page import="com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil" %>
<%@page import="com.liferay.portal.kernel.dao.orm.OrderFactoryUtil" %>
<%@page import="com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil" %>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil" %>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQuery" %>

<%@page import="com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil" %>



<%@ include file="/html/portlet/blogs/init.jsp" %>


<%
String F_DISPLAY_DATE = "displayDate";
String F_GROUP_ID = "groupId";

long assetCategoryId = ParamUtil.getLong(request, "categoryId");
String assetTagName = ParamUtil.getString(request, "tag");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/blogs/view");

Integer year = ParamUtil.getInteger(request, "filterYear", -1);
Integer month = ParamUtil.getInteger(request, "filterMonth", 0);

if (year > 0) {
    portletURL.setParameter("filterYear", String.valueOf(year));
}
if (month >= 0) {
    portletURL.setParameter("filterMonth", String.valueOf(month));
}
month--;

%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="struts_action" value="/blogs/search" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm1">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="groupId" type="hidden" value="<%= String.valueOf(scopeGroupId) %>" />

	<%
	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, pageDelta, portletURL, null, null);

	searchContainer.setDelta(pageDelta);
	searchContainer.setDeltaConfigurable(false);

	DynamicQuery dq = DynamicQueryFactoryUtil.forClass(BlogsEntry.class);

	Calendar c = Calendar.getInstance();
	if (year > 0) {
    	c.set(Calendar.YEAR, year);
    
    	if (month >= 0) {
        	c.set(Calendar.MONTH, month);
    	}
    	else {
        	c.set(Calendar.MONTH, 0);
    	}
    
    	c.set(Calendar.DAY_OF_MONTH, 0);
    	c.set(Calendar.HOUR, 0);
    	c.set(Calendar.MINUTE, 0);
    
    	Date fromDate = c.getTime();
    
    	if (month >=0) {
        	c.add(Calendar.MONTH, 1);
    	}
    	else {
        	c.add(Calendar.YEAR, 1);
    	}
    
    	Date toDate = c.getTime();
    	dq.add(RestrictionsFactoryUtil.ge(F_DISPLAY_DATE, fromDate));
    	dq.add(RestrictionsFactoryUtil.lt(F_DISPLAY_DATE, toDate));
	}
    
	dq.add(RestrictionsFactoryUtil.eq(F_GROUP_ID, scopeGroupId));
	dq.addOrder(OrderFactoryUtil.desc(F_DISPLAY_DATE));

	int total = 0;
	List results = BlogsEntryLocalServiceUtil.dynamicQuery(dq, searchContainer.getStart(), searchContainer.getEnd());


	dq.setProjection(ProjectionFactoryUtil.rowCount());
	List countResult = BlogsEntryLocalServiceUtil.dynamicQuery(dq);
	total = countResult.isEmpty() ? 0 : ((Long) countResult.get(0)).intValue();
/*
	if ((assetCategoryId != 0) || Validator.isNotNull(assetTagName)) {
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery(BlogsEntry.class.getName(), searchContainer);

		assetEntryQuery.setExcludeZeroViewCount(false);

		if (BlogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY)) {
			assetEntryQuery.setVisible(Boolean.TRUE);
		}

		total = AssetEntryServiceUtil.getEntriesCount(assetEntryQuery);
		results = AssetEntryServiceUtil.getEntries(assetEntryQuery);
	}
	else { 
		int status = WorkflowConstants.STATUS_APPROVED;

		if (BlogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY)) {
			status = WorkflowConstants.STATUS_ANY;
		}

		total = BlogsEntryServiceUtil.getGroupEntriesCount(scopeGroupId, status);
		results = BlogsEntryServiceUtil.getGroupEntries(scopeGroupId, status, searchContainer.getStart(), searchContainer.getEnd());
	}
*/

	searchContainer.setTotal(total);
	searchContainer.setResults(results);
	%>

	<%@ include file="/html/portlet/blogs/view_entries.jspf" %>
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm1.<portlet:namespace />keywords);
	</aui:script>
</c:if>