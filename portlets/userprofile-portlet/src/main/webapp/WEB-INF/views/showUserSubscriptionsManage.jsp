<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:subscriptions ="urn:jsptagdir:/WEB-INF/tags"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

  <jsp:directive.include file="./init.jspx" />


  <portlet:actionURL var="removeSubscriptionURL">
    <portlet:param name="action" value="removeSubscription" />
    <portlet:param name="userId" value="${currentUserProfile.userId}" />
  </portlet:actionURL>


  <div id="bread" class="pro">
    <a href="/web/guest/community">Community</a> <img
          src="/climatecolab-theme/images/arrow.gif" width="8" height="8" /> <a
          href="/web/guest/members">Members</a> <img
          src="/climatecolab-theme/images/arrow.gif" width="8" height="8" /> <a
          href="/web/guest/member/-/member/userId/${currentUserProfile.userId}">${userBean.screenName}</a>
    <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />Manage Subscriptions
  </div>

  <div id="content">

      <div class="main_long">

          <div class="comm_disc-head">
            <h1>Subscribed activities</h1>
            <div class="comm_info-left subscriptions"><span>${currentUserProfile.userSubscriptions.subscriptionsCount}</span> Subscriptions</div>
          </div>

        <form:form commandName="userSubscriptions" id="removeSubscriptionsForm" method="post" action="${removeSubscriptionURL}">
          <table id="activitiesTable" class="colab">
            <tr>
              <th class="subscriptionSelect Hdr" style="width: 40px;"><input type="checkbox" id="checkboxSelectAll"/></th>
              <th class="header Hdr">Subject</th>
              <th class="header Hdr" style="width: 100px;">Category</th>
              <th style="width: 160px;" class="Hdr">Updated</th>
            </tr>


              <c:forEach var="subscription" items="${userSubscriptions.subscriptions}" varStatus="x" >
                <tr class="colabRow">
                  <td class="subscriptionSelect"><form:checkbox path="subscriptions[${x.index}].selected" class="subscriptionSelect"/>
                      <form:hidden path="subscriptions[${x.index}].subscriptionPk"/>
                  </td>
                  <td>${subscription.name}</td>
                  <td>${subscription.type.printName}</td>
                  <td><fmt:formatDate value="${subscription.updated}" type="both" timeStyle="short" dateStyle="medium"  timeZone="America/New_York" /></td>
                </tr>
              </c:forEach>
          </table>

        <div class="com-info bot">
          <div class="blue-button"><a href="javascript:;" onclick="selectAllSubscriptions()">SELECT ALL</a>
          </div>
          <div class="blue-button"><a href="javascript:;" onclick="jQuery('#removeSubscriptionsForm').submit();" >REMOVE SELECTED
          </a>
          </div>
        </div>
        </form:form>

      </div>

      <div class="right_col3" style="margin-top: 49px!important;">
        <div class="comm_list">
          <h3>Display</h3>
          <ul>
            <li class="${currentUserProfile.userSubscriptions.typeFilterName == null ? 'c' : ''}">
              <subscriptions:subscriptionsPaginationFilterLink userId="${currentUserProfile.userId}" typeFilter="null" text="Show All Subscriptions"/>
            </li>
            <li class="${currentUserProfile.userSubscriptions.typeFilterName == 'DISCUSSION' ? 'c' : ''}">
              <subscriptions:subscriptionsPaginationFilterLink userId="${currentUserProfile.userId}" typeFilter="DISCUSSION" text="Discussions"/>
            </li>
            <li class="${currentUserProfile.userSubscriptions.typeFilterName == 'PLAN' ? 'c' : ''}">
              <subscriptions:subscriptionsPaginationFilterLink userId="${currentUserProfile.userId}" typeFilter="PLAN" text="Proposals"/>
            </li>
          </ul>
          <h3>Settings</h3>
          <ul>
            <li>
              <a href="/web/guest/member/-/member/userId/${currentUserProfile.userId}/page/edit">Manage your email notification settings</a>
            </li>
          </ul>
        </div>

      </div> <!-- /right_col3 -->

    <div class="clearfix"><!-- --></div>

  </div> <!-- /content -->

  <script type="text/javascript">
    jQuery("#checkboxSelectAll").change(function() {
      if(document.getElementById('checkboxSelectAll').checked){
        selectAllSubscriptions();
      } else {
        unSelectAllSubscriptions();
      }
    });
    </script>

</jsp:root>