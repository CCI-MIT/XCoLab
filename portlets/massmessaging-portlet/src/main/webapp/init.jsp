<%@ page import="com.ext.portlet.model.MessagingMessage"%>
<%@ page import="com.ext.portlet.model.MessagingMessageConversionType" %>
<%@ page import="com.ext.portlet.service.MessagingMessageLocalServiceUtil"%>
<%@ page import="com.ext.portlet.service.MessagingMessageConversionTypeLocalServiceUtil" %>
<%@ page import="org.xcolab.portlets.massmessaging.InvalidMessageRecipientException" %>
<%@ page import="com.ext.portlet.service.MessagingMessageConversionLocalServiceUtil" %>
<%@ page import="org.xcolab.portlets.massmessaging.MessagingConversionTypes" %>
<%@ page import="org.xcolab.portlets.massmessaging.MessagingUtils" %> 
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil"%>
<%@ page import="com.ext.portlet.model.MessagingIgnoredRecipients"%>
<%@ page import="com.ext.portlet.service.EmailListLocalServiceUtil"%>
<%@ page import="com.ext.portlet.model.EmailList"%>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="java.util.Comparator" %> 
<%@ page import="java.util.Collections" %> 
<%@ page import="java.util.List" %> 
<%@ page import="java.util.Set" %> 
<%@ page import="java.util.TreeSet" %> 
<%@ page import="com.liferay.portlet.PortletURLUtil" %>
<%@ page import="javax.portlet.PortletURL" %>

<%@page import="java.text.NumberFormat"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<portlet:defineObjects />
<%
	String currentURL = request.getRequestURI();       
	if (renderRequest != null && renderResponse != null) {
		PortletURL currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);
		currentURL = currentURLObj.toString();
	}

%>


<script type="text/javascript" src="/html/portlet/ext/js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="/html/portlet/ext/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="/html/portlet/ext/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="/html/portlet/ext/js/jquery.validate.pack.js"></script>
<link type="text/css" href="/html/portlet/ext/js/jquery.autocomplete.css" rel="stylesheet" />