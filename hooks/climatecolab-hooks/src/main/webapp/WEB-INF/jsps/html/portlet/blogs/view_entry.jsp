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

<%@ include file="/html/portlet/blogs/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect) || layoutTypePortlet.hasPortletId(PortletKeys.BLOGS_AGGREGATOR)) {
	redirect = PortalUtil.getLayoutURL(layout, themeDisplay) + Portal.FRIENDLY_URL_SEPARATOR + "blogs";
}

BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);

//entry = entry.toEscapedModel();

long entryId = BeanParamUtil.getLong(entry, request, "entryId");

pageDisplayStyle = RSSUtil.DISPLAY_STYLE_FULL_CONTENT;

AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(BlogsEntry.class.getName(), entry.getEntryId());

AssetEntryServiceUtil.incrementViewCounter(BlogsEntry.class.getName(), entry.getEntryId());

AssetUtil.addLayoutTags(request, AssetTagLocalServiceUtil.getTags(BlogsEntry.class.getName(), entry.getEntryId()));

request.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, assetEntry);

request.setAttribute("view_entry_content.jsp-entry", entry);

request.setAttribute("view_entry_content.jsp-assetEntry", assetEntry);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	localizeTitle="<%= false %>"
	title="<%= entry.getTitle() %>"
/>

<portlet:actionURL var="editEntryURL">
	<portlet:param name="struts_action" value="/blogs/edit_entry" />
</portlet:actionURL>

<aui:form action="<%= editEntryURL %>" method="post" name="fm1" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveEntry();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="entryId" type="hidden" value="<%= String.valueOf(entryId) %>" />

	<liferay-util:include page="/html/portlet/blogs/view_entry_content.jsp" />
</aui:form>


<c:if test="<%= enableComments %>">
	<liferay-ui:panel-container extended="<%= false %>" id="blogsCommentsPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="blogsCommentsPanel" persistState="<%= true %>" title="comments">
			<c:if test="<%= PropsValues.BLOGS_TRACKBACK_ENABLED && entry.isAllowTrackbacks() %>">
				<liferay-ui:message key="trackback-url" />:

				<liferay-ui:input-resource
					url='<%= PortalUtil.getLayoutFullURL(themeDisplay) + Portal.FRIENDLY_URL_SEPARATOR + "blogs/trackback/" + entry.getUrlTitle() %>'
				/>

				<br /><br />
			</c:if>

			<portlet:actionURL var="discussionURL">
				<portlet:param name="struts_action" value="/blogs/edit_entry_discussion" />
			</portlet:actionURL>

			<liferay-ui:discussion
				className="<%= BlogsEntry.class.getName() %>"
				classPK="<%= entry.getEntryId() %>"
				formAction="<%= discussionURL %>"
				formName="fm2"
				ratingsEnabled="<%= enableCommentRatings %>"
				redirect="<%= currentURL %>"
				subject="<%= entry.getTitle() %>"
				userId="<%= entry.getUserId() %>"
			/>
		</liferay-ui:panel>
	</liferay-ui:panel-container>
</c:if>

<%
PortalUtil.setPageSubtitle(entry.getTitle(), request);
PortalUtil.setPageDescription(entry.getDescription(), request);

List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(BlogsEntry.class.getName(), entry.getEntryId());

PortalUtil.setPageKeywords(ListUtil.toString(assetTags, AssetTag.NAME_ACCESSOR), request);

PortalUtil.addPortletBreadcrumbEntry(request, entry.getTitle(), currentURL);
%>