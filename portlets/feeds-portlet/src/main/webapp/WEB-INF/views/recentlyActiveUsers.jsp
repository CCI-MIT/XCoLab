<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./init.jspx" />
	
    <div class="comm_rightcol-bdr">
        <h3>Recently active</h3><br />
        <c:forEach items="${recentlyActiveUsers }" var="member">
            <div class="comm_rightcol-time">
                ${member.lastActivityDateAgo}
            </div>
            <p>
				<c:out value="${member.lastActivityBody}" escapeXml="false" />
            </p>
        </c:forEach>
    </div>

</jsp:root>