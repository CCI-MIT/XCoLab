<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:liferay-faces="http://liferay.com/tld/faces"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:ice="http://www.icesoft.com/icefaces/component"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

	<h2>
	<c:choose >
		<c:when test="#{unregisteringSubscription }">
			Your subscription has been removed.	
		</c:when> 
		<c:otherwise>
			Your address has been excluded from our newsletter recipients.
		</c:otherwise>
	</c:choose>
	</h2>
</jsp:root>