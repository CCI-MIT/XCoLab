<jsp:root 
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">


<portlet:actionURL var="createAccountURL">
    <portlet:param name="saveLastPath" value="${isRegistering ? 0 : 1 }"/>
    <portlet:param name="isRegistering" value="true"/>
    <portlet:param name="redirect" value="${redirect}" />
</portlet:actionURL>
<div class="popupreg_form">
	<div class="popupreg_head"><h1>Join the CoLab</h1></div>
	<div class="popupreg_message"><p>Fill out the form below or import your data from another service.</p></div>
	
	<div class="is-required"><img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" /> is a required field.</div>
	
	<c:if test="${ error != null and isRegistering }">
    	<div class="error-message">${error}</div>
    </c:if>
    
    <form:form action="${createAccountURL }" commandName="createUserBean" id="registrationForm">
    	<div class="reg_errors">
    		<form:errors cssClass="alert alert-error" />
    	</div>
    	<input type="hidden" name="action" value="add" />
        <div class="popupreg_input_container">
        	<div class="popupreg_txt req">Screen name</div>
        	<form:input cssClass="popupreg_input" path="screenName" />
        	<div class="clear"><!--  --></div>
    	    <div class="reg_errors">
    			<form:errors cssClass="alert alert-error" path="screenName" />
    		</div>
    	</div>
    	
    	<div class="popupreg_input_container">
        	<div class="popupreg_txt req">Email</div>
        	<form:input cssClass="popupreg_input" path="email" />
        	<div class="clear"><!--  --></div>
    	    <div class="reg_errors">
    			<form:errors cssClass="alert alert-error" path="email" />
    		</div>
    	</div>
    	
    	<div class="popupreg_input_container">
        	<div class="popupreg_txt req">First name</div>
        	<form:input cssClass="popupreg_input" path="firstName" />
        	<div class="clear"><!--  --></div>
    	    <div class="reg_errors">
    			<form:errors cssClass="alert alert-error" path="firstName" />
    		</div>
    	</div>
    	
    	<div class="popupreg_input_container">
        	<div class="popupreg_txt req">Last name</div>
        	<form:input cssClass="popupreg_input" path="lastName" />
        	<div class="clear"><!--  --></div>
    	    <div class="reg_errors">
    			<form:errors cssClass="alert alert-error" path="lastName" />
    		</div>
    	</div>
    	
    	<div class="popupreg_input_container">
        	<div class="popupreg_txt req">Password</div>
        	<form:password cssClass="popupreg_input" path="password" />
        	<div class="clear"><!--  --></div>
    	    <div class="reg_errors">
    			<form:errors cssClass="alert alert-error" path="password" />
    		</div>
    	</div>
    	
    	<div class="popupreg_input_container">
        	<div class="popupreg_txt req">Retype Password</div>
        	<form:password cssClass="popupreg_input" path="retypePassword" />
        	<div class="clear"><!--  --></div>
    	    <div class="reg_errors">
    			<form:errors cssClass="alert alert-error" path="retypePassword" />
    		</div>
    	</div>
    	
    	
    	<div class="clearfix"><!--  --></div>
    	<div class="popupreg_recap">
    		<div class="popupreg_txt req recap">Retype the words</div>
        	<script type="text/javascript">
            	var RecaptchaOptions = { lang : 'en'};
        	</script>
        	<script type="text/javascript" src="${recaptchaUrlScript}${recaptchaKeyPublic}">
        		//
        	</script>
        	<noscript>
            	<iframe src="${recaptchaUrlNoscript}" height="300" width="500" frameborder="0"><!--  --></iframe><br />
            	<textarea name="recaptcha_challenge_field" rows="3" cols="40"></textarea>
            	<input type="hidden" name="recaptcha_response_field" value="manual_challenge" />
        	</noscript>
    	</div>   
    	
    	<div class="popupreg_terms">
        	<div class="popupreg_txt terms req">Terms of use</div>
        	<div class="popupreg_terms-right">
        		By registering for this site, you agree to abide<br />
            	by the
            	<a href="/web/guest/resources/-/wiki/Main/Terms+of+use">Terms of Use</a>
        	</div>
    	</div>
    	<div class="clearfix"><!--  --></div>
    	<div class="popupreg_submit_wrap">
        	<div class="blue-button">
            	<a href="javascript:;" onclick="pageTracker._trackPageview('/user/registerSubmit');jQuery('#registrationForm').submit();">CREATE account</a>
        	</div>
    	</div>
    </form:form>
    
	
</div>
		
</jsp:root>