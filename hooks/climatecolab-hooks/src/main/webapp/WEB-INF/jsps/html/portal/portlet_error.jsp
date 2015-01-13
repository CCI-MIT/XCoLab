<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portal/init.jsp" %>

<portlet:defineObjects />

<%
  String portletTitle = HtmlUtil.escape(PortalUtil.getPortletTitle(renderResponse));

  if (portletTitle == null) {
    portletTitle = LanguageUtil.get(pageContext, "portlet");
  }
%>

<c:set var="stackTrace"  value="${renderRequest.getAttribute('stackTrace').toString()}"/>

<%--
pageScope
 Loop over the JSTL implicit object, stored in the
     page-scoped attribute named scope that was set above.
     That implicit object is a map
<c:forEach items='${pageScope}' var='p'>
    <ul>
        <li>Parameter Name: <c:out value='${p.key}'/></li>
        <li>Parameter Value: <c:out value='${p.value}'/></li>
    </ul>
</c:forEach>

<c:set var='requestScope' value='${requestScope}'/>
--%>

<div class="alert alert-error">
    <%= LanguageUtil.format(pageContext, "is-temporarily-unavailable", portletTitle, false) %>
</div>

<script type="text/javascript">
    jQuery(document).ready(function() {
        var stackTraceMessage = '${stackTrace}';
        jQuery('#stackTrackeInput').val(stackTraceMessage);
        jQuery('#popup_error_reporting').show();
    });
</script>