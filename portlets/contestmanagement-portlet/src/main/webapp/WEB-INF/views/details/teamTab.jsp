<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="../init.jspx" />
	<jsp:directive.include file="./header.jspx"/>

	<portlet:actionURL  var="updateContestTeamURL">
		<portlet:param name="tab" value="TEAM" />
		<portlet:param name="contestId" value="${contestWrapper.contestPK }" />
		<portlet:param name="action" value="updateContestTeam" />
		<portlet:param name="action_forwardToPage" value="teamTab" />
		<portlet:param name="action_errorForwardToPage" value="teamTab" />
	</portlet:actionURL>

	<script type="text/javascript">
		var users = [];
		var usersMap = {};
		var userNames = [];
		<c:forEach var="user" items="${usersList}">
			usersMap["${user.screenName}"] = "${user.userId}";
			userNames.push("${user.screenName}");
		</c:forEach>
	</script>

		<div class="cmsDetailsBox">
			<p>Select the members of your contest team by searching for their Climate CoLab username.
				Please ensure each member of your team has uploaded a photo and bio to their profile.
				You can register new members by <a href="http://climatecolab.org/web/guest/loginregister" target="_blank">clicking here.</a>
				<i>(After registering new members you have to reload this page. You won't be able to select before that)</i>
			</p>
			<h2>Contest team</h2>
		<form:form action="${updateContestTeamURL }" commandName="contestTeamBean" id="editForm" method="post">
			<div class="teamTabContainer">
				<collab:teamBox title="Advisors" list="${contestWrapper.contestAdvisors}"/>
				<collab:teamBox title="Fellows" list="${contestWrapper.contestFellows}"/>
				<collab:teamBox title="Judges" list="${contestWrapper.contestJudges}"/>
				<!-- <collab:teamBox title="Managers" list="${contestWrapper.contestManager}"/> -->
			</div>
		</form:form>
		<div class="clear"><!-- --></div>
	</div>
	<jsp:directive.include file="./footer.jspx"/>
</jsp:root>