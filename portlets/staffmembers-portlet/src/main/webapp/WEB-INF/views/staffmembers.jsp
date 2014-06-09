<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./init.jspx" />

    <c:if test="${not empty portletTitle}">
        <h2>${portletTitle}</h2>
    </c:if>

    <c:choose>
        <c:when test="${displayPhoto}">
            <table class="staffmembers-table">
                <tr>
                <c:forEach items="${staffMembers}" var="staffMember" varStatus="status">
                    <c:if test="${(status.index % columnAmount) == 0 and status.index > 0}">
                    </c:if>
                        <td>
                            <c:choose>
                                <c:when test="${displayUrl and not empty staffMember.url}">
                                    <a href="${staffMember.url}">
                                            ${staffMember.name}
                                    </a>
                                </c:when>
                                <c:otherwise>
                                        ${staffMember.name}
                                </c:otherwise>
                            </c:choose>
                            <br />
                            <c:if test="${not empty staffMember.photoUrl}">
                                <img src="${staffMember.photoUrl}" height="120" />
                            </c:if>
                        </td>
                </c:forEach>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <ul class="staffmembers-list">
                <c:forEach items="${staffMembers}" var="staffMember">
                    <li>
                        <c:choose>
                            <c:when test="${displayUrl and not empty staffMember.url}">
                                <a href="${staffMember.url}">
                                    ${staffMember.name}
                                </a>
                            </c:when>
                            <c:otherwise>
                                ${staffMember.name}
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>

    </c:choose>
</jsp:root>