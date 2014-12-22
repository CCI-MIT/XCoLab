<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

  <jsp:directive.include file="./init.jspx" />


  <div id="bread" class="pro">
    <a href="/web/guest/community">Community</a> <img
          src="/climatecolab-theme/images/arrow.gif" width="8" height="8" /> <a
          href="/web/guest/members">Members</a> <img
          src="/climatecolab-theme/images/arrow.gif" width="8" height="8" /> <a
          href="/web/guest/member/-/member/userId/${currentUser.userId}">${userBean.screenName}</a>
    <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />Manage Subscriptions
  </div>

  <div id="content">
    <form:form>

      <div class="main_long">
        <div class="main_long">

          <div class="comm_disc-head">
            <div class="comm_info-left subscriptions"><span>${currentUser.userSubscriptions.subscriptionsCount}</span> Subscriptions</div>
          </div>

          <h1>Subscribed activities</h1>
          <table id="activitiesTable">
            <tr>
              <th class="header">Subject</th>
              <th class="header" style="width: 100px;">Category</th>
              <th style="width: 140px;">Updated</th>
            </tr>
            <c:forEach var="subscription" items="${currentUser.userSubscriptions.subscriptions}" begin="0" end="5">
            <tr>
              <td>
                <!-- <form:checkbox value="${subscription.selected}" class="subscriptionSelect"/> -->
                &#160; ${subscription.name}</td>
              <td>${subscription.type.printName}</td>
              <td><fmt:formatDate value="${activity.updated}" type="time" timeStyle="medium" timeZone="America/New_York" /></td>
            </tr>

            </c:forEach>

          </table>
        </div>
      </div>

        <div class="right_col3">
          <div class="comm_list">
            <div style="margin-bottom:20px">
              <a href="/web/guest/member/-/member/userId/${currentUser.userId}/page/edit">Manage your email notification settings</a>
            </div>

            <h3>Display</h3>
            <!-- TODO rewrite in JavaScript
            <ul>
              <li class="${userSubscriptionsBean.typeFilterName == null ? 'c' : ''}">
                <ice:commandLink actionListener="#{userSubscriptionsBean.setFilterType}">
                  Show All Subscriptions
                </ice:commandLink>
              </li>
              <li class="${userSubscriptionsBean.typeFilterName == 'DISCUSSION' ? 'c' : ''}">
                <ice:commandLink actionListener="#{userSubscriptionsBean.setFilterType}">
                  <f:attribute name="type" value="DISCUSSION" />
                  Discussions
                </ice:commandLink>
              </li>
              <li class="${userSubscriptionsBean.typeFilterName == 'PLAN' ? 'c' : ''}">
                <ice:commandLink actionListener="#{userSubscriptionsBean.setFilterType}">
                  <f:attribute name="type" value="PLAN" />
                  Proposals
                </ice:commandLink>
              </li>
            </ul> -->
          </div>

        </div> <!-- /right_col4 -->
      <div class="clearfix"><!-- --></div>
    </form:form>
  </div> <!-- /content -->

</jsp:root>