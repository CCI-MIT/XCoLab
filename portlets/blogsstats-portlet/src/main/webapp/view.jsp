<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.xcolab.portlets.blogsstats.BlogsEntryServiceHelper"%>
<%@page import="org.xcolab.portlets.blogsstats.BlogsEntryServiceHelper.BlogsEntryStats"%>
<%
DateFormat month = new SimpleDateFormat("M");
DateFormat year = new SimpleDateFormat("yyyy");
DateFormat sdf = new SimpleDateFormat("yyyy MMMM");
long scopeGroupId = 10136L;
%>
<div class="div2"></div>
<h3>Archives</h3><br />
<ul id="blogArchives">

    
<% for (BlogsEntryStats stats: BlogsEntryServiceHelper.getEntryDates(scopeGroupId)) { %>
    <liferay-portlet:renderURL portletName="33" var="viewEntryURL">
        <liferay-portlet:param name="struts_action" value="/blogs/view" />
        <liferay-portlet:param name="filterYear" value="<%= year.format(stats.getDate()) %>" />
        <liferay-portlet:param name="filterMonth" value="<%= month.format(stats.getDate()) %>" />

</liferay-portlet:renderURL>

    <li><strong><a href="${viewEntryURL}"><%= sdf.format(stats.date) %> (<%= stats.count %>)</a></strong></li>
<% } %>
</ul>
