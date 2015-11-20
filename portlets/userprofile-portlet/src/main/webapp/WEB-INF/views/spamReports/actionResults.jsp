<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
    <div id="content">
        <div class="${success ? 'success-message' : 'error-message'}">
            <c:choose>
                <c:when test="${messageType == 'deletedComment'}">
                    The comment was deleted successfully.
                </c:when>
                <c:when test="${messageType == 'deletedUser'}">
                    The user has been deactivated.
                </c:when>
            </c:choose>
            <c:otherwise>
                An unexpected error occurred. Please contact the administrator.
            </c:otherwise>
        </div>
    </div>
</jsp:root>
