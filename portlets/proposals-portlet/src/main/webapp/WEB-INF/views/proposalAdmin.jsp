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

                <c:if test="${fn:length(proposal.membershipRequests) lt 1}">
                    <div><i>No new requests</i></div>
                </c:if>
                <portlet:actionURL var="replyToMembershipRequest">
                    <portlet:param name="action_forwardToPage" value="proposalDetails_ADMIN" />
                    <portlet:param name="contestId" value="${contest.contestPK }" />
                    <portlet:param name="planId" value="${proposal.proposalId }" />
                    <portlet:param name="action" value="replyToMembershipRequest" />
                </portlet:actionURL>
                <c:set var="count" value="1" scope="page" />
                    <c:forEach items="${proposal.membershipRequests}" var="request">
                        <form action="${replyToMembershipRequest }" method="post" style="padding: 10px 0; ${count lt fn:length(proposal.membershipRequests) ? 'border-bottom: 1px solid gray;' :''}">
                            <proposalsPortlet:userLinkSimple userId="${request.requestUser.userId}" text="${request.requestUser.firstName} ${request.requestUser.lastName} (${request.requestUser.screenName})" />
                            <div>${request.membershipRequest.comments }</div>
                            <input type="hidden" name="requestId" value="${request.membershipRequest.primaryKey}" />
                            <div style="margin-top: 10px;">
                                <textarea name="comment" class="membership-request-comment-admin" onfocus="clearContents(this);">Optional response</textarea>
                                <div class="allow-deny-membership-box">
                                    <div>
                                        <input type="radio" name="approve" value="approve" id="radio-approve"/>
                                        <label for="radio-approve" style="margin-left: 10px;">Approve</label>
                                    </div>
                                    <div>
                                        <input type="radio" name="approve" value="deny" id="radio-deny" />
                                        <label for="radio-deny" style="margin-left: 10px;">Deny</label>
                                    </div>
                                </div>
                                <a class="sendButton" href="javascript:;" onclick="jQuery(this).parents('form').submit();">Send</a>
                            </div>
                        </form>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:forEach>

                <div id="manage_membership_request_dialog"></div>
            </div>
        </div>
    </div>


</jsp:root>