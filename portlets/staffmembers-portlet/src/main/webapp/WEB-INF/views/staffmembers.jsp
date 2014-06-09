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

    <c:if test="${not empty portletTitle }">
        <h1>${portletTitle}</h1>
    </c:if>

    <c:choose>
        <c:when test="${displayPhoto}">
            <table class="staffmembers-table">
                <tr>
                <c:forEach items="${staffMembers}" var="staffMember" varStatus="status">
                    <c:if test="${(status.index % columnAmount) == 0 && status.index > 0}">
                        </tr>
                        <tr>
                    </c:if>
                        <td>
                            <c:choose>
                                <c:when test="${displayUrl && not empty staffMember.url}">
                                    <a href="${staffMember.url}">
                                            ${staffMember.firstNames}
                                            ${staffMember.lastName}
                                    </a>
                                </c:when>
                                <c:otherwise>
                                        ${staffMember.firstNames}
                                        ${staffMember.lastName}
                                </c:otherwise>
                            </c:choose>
                            <br>
                            <c:if test="${not empty staffMember.photoUrl}">
                                <img src="${staffMember.photoUrl}" height="120">
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
                            <c:when test="${displayUrl && not empty staffMember.url}">
                                <a href="${staffMember.url}">
                                    ${staffMember.firstNames}
                                    ${staffMember.lastName}
                                </a>
                            </c:when>
                            <c:otherwise>
                                ${staffMember.firstNames}
                                ${staffMember.lastName}
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>

    </c:choose>

    <ul>


        Display Photo: ${displayPhoto}


    </ul>
</jsp:root>