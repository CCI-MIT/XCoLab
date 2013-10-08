<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
<jsp:directive.include file="./init.jspx" />

    <style type="text/css">
        .admin_left {
            width: 400px;
            float: left;
        }
        .admin_right {
            float: left;
        }
    </style>

	<jsp:directive.include file="./proposalDetails/header.jspx" />


    <h1>admin tab</h1>

    <div id="content">
        <div class="admin_left"><h2>LEFT</h2></div>


        <div class="admin_right">
            <div class="addpropbox">
                <strong>Membership requests</strong>

                <c:if test="${fn:length(proposal.membershipRequests.allRequests) lt 1}">
                    <div><i>No new requests</i></div>
                </c:if>


                    <portlet:actionURL var="approveMembershipRequest">
                        <portlet:param name="action_forwardToPage" value="proposalDetails_ADMIN" />
                        <portlet:param name="contestId" value="${contest.contestPK }" />
                        <portlet:param name="planId" value="${proposal.proposalId }" />
                        <portlet:param name="action" value="approveMembershipRequest" />
                    </portlet:actionURL>

                <ul>
                    <c:forEach items="${proposal.membershipRequests.allRequests}" var="request">
                        <li>${request.comments} <a href="">Approve</a><a href="">Deny</a></li>
                    </c:forEach>
                </ul>

                <div id="manage_membership_request_dialog"></div>
            </div>
        </div>
    </div>


</jsp:root>