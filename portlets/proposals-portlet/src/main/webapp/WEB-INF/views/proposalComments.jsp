<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:discussions="http://climatecolab.org/tags/xcollab_discussions_1.0"
	xmlns:addthis="http://www.addthis.com/help/api-spec"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
<jsp:directive.include file="./init.jspx" />

	<jsp:directive.include file="./proposalDetails/header.jspx" />
	<div id="content">
		<discussions:loadDiscussion discussionId="${proposal.discussionId }" >
			<div class="headline prophead">
        		<h2><span>${discussion.commentsCount}</span> comments</h2>
        		<div class="comm-butt">
	        		<img src="/climatecolab-theme/images/icon-add-comment.png" id="addcomment_button1" width="24" height="22" alt="add a comment" /> 
	        		<div class="blue-button" id="addcomment_button2"><a href="javascript:;" onclick='if(!deferUntilLogin()) return false; jQuery("#thecomment")[0].scrollIntoView(); return false;'>Jump to comment form</a></div>
            		<div class="comm-share">
                    	<a href="http://www.addthis.com/bookmark.php?v=250&amp;username=climatecolab" class="addthis_button_compact discussion_share"
                        	addthis:title="${discussion.description}"
                        	addthis:description="${discussion.description}"
                        	addthis:url="${discussion.discussionUrl}" >
	                    	<img src="/climatecolab-theme/images/share.png" width="24" height="24" alt="Share" class="icon_share" />
    	                	Share conversation
        	        	</a>
            		</div>
        		</div>
        	</div>
        	<div class="clearfix"><!--  --></div>
        	<c:choose>
        		<c:when test="${discussion.commentsCount == 0 }">
       				<div> 
						No comments have been posted. Be the first one to add a comment.
        			</div>
        		</c:when>
        		<c:otherwise>
        			<table class="comments" id="commentsTable" >
						<c:forEach var="message" items="${discussion.comments }" varStatus="status">
							<tr class="${message.expertReview ? 'expertReview' : ''} ${status.index mod 2 == 0 ? 'odd' : 'even'}">
								<td class="commentor">
									<a id="_message_${message.messageId}"></a>
									<proposalsPortlet:userPortrait  screenName="${message.author.screenName}" portraitId="${message.author.portraitId}" width="59" height="59" />
									<br />
									<proposalsPortlet:userLinkSimple text="${message.author.screenName}" userId="${message.author.userId}" /> <br />
									<br />
									
									<c:if test="${message.expertReview}">
										<strong>Expert Review</strong><br />
									</c:if>
                    				<fmt:formatDate value="${message.createDate}" type="date" dateStyle="short" pattern="MMM. dd, yyyy" timeZone="America/New_York" /><br />
                    				<fmt:formatDate value="${message.createDate}" type="time" pattern="hh:mm a" timeStyle="short" timeZone="America/New_York" /><br />
                    				
                    				<br /><br /><br />
                    				<a href="#_message_${message.messageId}">
                    					<span class="com_num messageLink" rel="${message.messageId}">${status.index + 1} </span>
                    				</a>
                        			|
                        			<a href="http://www.addthis.com/bookmark.php?v=250&amp;username=climatecolab" class="addthis_button_compact message_add_this"  
                        				addthis:title="${discussion.description}" addthis:description="${message.body}"
                        				addthis:url="${discussion.discussionUrl}#_message_${message.messageId }" >
	                    				<span>share</span>
                        			</a>
								</td>
								<td class="the-comment">
									<div class="content">
										${message.body }
									</div>
									<!-- 
									<div class="commentsActions">
									
									</div>
									 -->
								</td>
							</tr>
							
						</c:forEach>
					</table>
        		
        		</c:otherwise>
        	</c:choose> 
        	<div class="addcomment">
        		<div id="add">
           			<strong>ADD YOUR COMMENT</strong>
        		</div>
        		<div id="thecomment">
        			<portlet:actionURL var="addCommentURL">
        				<portlet:param name="action" value="addDiscussionMessage" />
        			</portlet:actionURL>
        			<form id="addCommentForm" action="${addCommentURL }" method="post">
        				<input name="discussionId" class="title text hidden" maxlength="255" value="${discussion.discussionId }"/>
	       				<input name="title" class="title text hidden" maxlength="255" />
    	   				<textarea id="messageContent" name="description"  class="commentbox commentContent" ><!--  --></textarea>
            			<br />
           				<span class="errorMsg portlet-msg-error" style="display: none;">Value is required</span>
           				<div class="blue-button">
           					<a href="javascript:;" 
           						type="submit" 
           						onclick="if (! window.isAddCommentFormValid()) return false; window.disableAddComment(); $('#addCommentForm').submit()">
           							Add Comment
	           				</a>
    	       			</div>
            		</form>
        		</div>
        	</div>
        	<script>
            function disableAddComment() {
                    jQuery("#thecomment .addCommentButton").attr('disabled', true);
                }
            function isAddCommentFormValid() {
                var isValid = (jQuery.trim(jQuery("#thecomment .commentContent").val()) != '');
                
                if (isValid) {
                    jQuery('#thecomment .errorMsg').hide();
                }
                else {
                    jQuery('#thecomment .errorMsg').show();
                }
                return isValid;
            }
            
            function enableAddComment() {
                jQuery("#thecomment .addCommentButton").removeAttr('disabled');
            }
        	</script>
		</discussions:loadDiscussion>
	</div>
	

</jsp:root>