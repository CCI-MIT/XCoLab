<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:pagination ="urn:jsptagdir:/WEB-INF/tags"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

  <jsp:directive.include file="./init.jspx" />


  <div id="bread" class="pro">
    <a href="/web/guest/community">Community</a> <img
          src="/climatecolab-theme/images/arrow.gif" width="8" height="8" /> <a
          href="/web/guest/members">Members</a> <img
          src="/climatecolab-theme/images/arrow.gif" width="8" height="8" /> <a
          href="/web/guest/member/-/member/userId/${currentUser.userId}">${userBean.screenName}</a>
    <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />Subscriptions
  </div>

  <div id="content">

    <div class="main_long">
        <h1>Subscribed activities</h1>
        <c:if test="${currentUser.viewingOwnProfile}">
          <div class="profile_action">
            <div class="blue-button">
              <a href="/web/guest/member/-/member/userId/${currentUser.userId}/page/subscriptionsManage">MANAGE</a>
            </div>
              <div class="blue-button">
                  <a href="/web/guest/member/-/member/userId/${currentUser.userId}">BACK TO PROFILE</a>
              </div>
          </div>
        </c:if>
        <table id="activitiesTable" class="fullscreen colab">
        <thead class="portlet-section-header">
            <tr>
              <th style="width: 585px" class="Hdr">Activity</th>
              <th style="width: 160px" class="Hdr">Date</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach  var="activity" items="${currentUser.subscribedActivities}"
                        begin="${(currentUser.subscriptionsPaginationPageId-1) * currentUser.subscriptionsPageSize}"
                        end="${currentUser.subscriptionsPaginationPageId * currentUser.subscriptionsPageSize}">
              <tr class="colabRow">
                <td>${activity.body}</td>
                <td><fmt:formatDate value="${activity.createdDate}" type="both" timeStyle="short" dateStyle="medium" timeZone="America/New_York" /></td>
              </tr>

            </c:forEach>
          </tbody>
        </table>

      <portlet:actionURL var="paginationActionUrl">
        <portlet:param name="action" value="navigateSubscriptions" />
      </portlet:actionURL>

        <div class="pager">
          <table style="float: right;">
            <tbody>
              <tr>
                <td>
                  <span class="page">
                  Page ${currentUser.subscriptionsPaginationPageId} of ${currentUser.subscriptionsPaginationPageMax}
                  </span>
                </td>
                <td class="paginatorControls">
                  <table class="comm_controls">
                    <tbody>
                      <tr class="comm_controlsScrBtn">
                        <form:form method="post" action="${paginationActionUrl}">
                          <td><input type="submit" name="paginationAction" value="First" class=" comm_controlsScrBtn btn-info" /></td>
                          <td><input type="submit" name="paginationAction" value="&lt;Previous" class="comm_controlsScrBtn btn-info"/></td>
                            <pagination:pageNavigation  userId="${currentUser.userId}"
                                                        pageId="${currentUser.subscriptionsPaginationPageId}"
                                                        maxPageId="${currentUser.subscriptionsPaginationPageMax}" />
                          <td><input type="submit" name="paginationAction" value="Next&gt;" class="comm_controlsScrBtn btn-info"/></td>
                          <td><input type="submit" name="paginationAction" value="Last" class="comm_controlsScrBtn btn-info" /></td>
                        </form:form>
                      </tr>
                    </tbody>
                  </table>
                </td>
              </tr>
            </tbody>
          </table>
          <div class="clear"><!-- --></div>
        </div>


    </div>

    <div class="clearfix"><!-- --></div>

  </div> <!-- /main -->

</jsp:root>