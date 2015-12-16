<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:liferay-faces="http://liferay.com/tld/faces"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:ice="http://www.icesoft.com/icefaces/component"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<c:if test="${success }">
		<h1>Message has been sent</h1>
	</c:if>
    <div id="contactForm" style="${not error ? 'display:none;' : ''};">
		
		<portlet:actionURL var="sendContactForm" >
			<portlet:param name="action" value="send" />
		</portlet:actionURL>
		<form:form action="${sendContactForm }" commandName="contactBean" id="contactFormForm">
			<div class="reg_errors"><!--  --></div>
			<form:errors cssClass="alert alert-error" />
			
			<table class="reg">
				<tr>
					<th nowrap="nowrap">Your name<img
						src="/climatecolab-theme/images/reg-star.png" width="8" height="7"
						align="texttop" /></th>
					<td><form:input cssClass="popupreg_input" path="name" /><br />
						<div class="reg_errors"><!--  -->
							<form:errors cssClass="alert alert-error" path="name" />
						</div>
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">Email (only for reply)<img
						src="/climatecolab-theme/images/reg-star.png" width="8" height="7"
						align="texttop" /></th>
					<td><form:input cssClass="popupreg_input" path="email" />
						<div class="reg_errors"><!--  -->
							<form:errors cssClass="alert alert-error" path="email" />
						</div>
                    </td>
				</tr>

				<tr>
					<th nowrap="nowrap">Message<img
						src="/climatecolab-theme/images/reg-star.png" width="8" height="7"
						align="texttop" /></th>
					<td><form:textarea cssClass="shortBioContent" path="message" style="width: 100%; height: 150px;" />
						<div class="reg_errors"><!--  -->
							<form:errors cssClass="alert alert-error" path="message" />
						</div></td>
				</tr>
				
				<tr>
					<th nowrap="nowrap">Retype the words <img
						src="/climatecolab-theme/images/reg-star.png" width="8" height="7"
						align="texttop" /></th>
					<td colspan="3">
						<portlet:resourceURL var="getCaptchaImage" id="ajaxHandler"/>
						<img src="${getCaptchaImage }" style="margin-bottom: 10px;"/><br />
						<form:input cssClass="popupreg_input" path="captchaText" />
						<div class="reg_errors"><!--  -->
							<form:errors cssClass="alert alert-error" path="captchaText" />
						</div>
					</td>
				</tr>
				
				<tr>
					<th></th>
					<td colspan="3">
						<a class="primary-button" href="javascript:;" onclick="$('#contactFormForm').submit()">Send message</a>
					</td>
				</tr>
			</table>
		</form:form>
    </div>

</jsp:root>