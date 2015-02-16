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

	<portlet:actionURL var="updateContestAdminURL">
		<portlet:param name="action_forwardToPage" value="adminTab" />
		<portlet:param name="action_errorForwardToPage" value="adminTab" />
		<portlet:param name="tab" value="ADMIN" />
		<portlet:param name="contestId" value="${contestWrapper.contestPK }" />
		<portlet:param name="action" value="updateContestAdmin" />
	</portlet:actionURL>

	<script type="text/javascript" src="/html/js/editor/ckeditor_old/ckeditor.js" ><!-- --></script>
	<div class="cmsDetailsBox">

		<h1>Introduction</h1>
		Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et
		dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.
		Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
		Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy dolor sit amet.

		<h1>Admin section</h1>
		<div id="resourcesSections">
			<form:form action="${updateContestAdminURL }" commandName="contestAdminBean"
					   cssClass="addpropform" id="editForm" method="post">
				<div class="addpropbox">
					<label>
						<strong>Appearance</strong>
					</label>
					<form:checkbox path="contestActive"/> Contest active
					<form:checkbox path="featured"/> Contest featured
				</div>

				<div class="addpropbox">
					<label>
						<strong>Flag settings</strong>
					</label>
					<form:input path="flag"/> Flag code
					<form:input path="flagText"/> Flag text
				</div>
			</form:form>
		</div>

			<jsp:directive.include file="./footer.jspx"/>
</jsp:root>