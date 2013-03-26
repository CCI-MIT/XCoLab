<jsp:root 
	xmlns:c="http://java.sun.com/jsp/jstl/core"
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


<portlet:actionURL var="createAccountURL">
    <portlet:param name="saveLastPath" value="${isRegistering ? 0 : 1 }"/>
    <portlet:param name="isRegistering" value="true"/>
    <portlet:param name="redirect" value="${redirect}" />
</portlet:actionURL>
<div class="popupreg_form">
	<div class="popupreg_head"><h1>Join the CoLab</h1></div>
	
	<div class="is-required"><img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" /> is a required field.</div>
	
	<c:if test="${ error != null and isRegistering }">
    	<div class="error-message">${error}</div>
    </c:if>
    
    <form:form action="${createAccountURL }" commandName="createUserBean" id="registrationForm">
    	<div class="reg_errors">
    		<form:errors cssClass="alert alert-error" />
    	</div>
    	
    	<table class="reg">
    		<tr>
    			<th nowrap="nowrap">Screen name <img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" /></th>
    			<td>
    				<form:input cssClass="popupreg_input" path="screenName" /><br/>
    				<div class="reg_errors">
	    				<form:errors cssClass="alert alert-error" path="screenName" />
	    			</div>
    			</td>
    			
    			<th class="second" rowspan="4">Photo</th>
    			<td rowspan="4">
    				<form:input cssClass="popupreg_input" path="screenName" /><br/>
    				<div class="reg_errors">
	    				<form:errors cssClass="alert alert-error" path="screenName" />
	    			</div>
	    		</td>
    			
    		</tr>
    		<tr>
    			<th nowrap="nowrap">Email <img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" /></th>
    			<td>
    			<form:input cssClass="popupreg_input" path="email" />
		    	    <div class="reg_errors">
		    			<form:errors cssClass="alert alert-error" path="email" />
		    		</div>
	    		</td>
    		</tr>
    		
    		<tr>
    			<th nowrap="nowrap">First name <img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" /></th>
    			<td>
    			<form:input cssClass="popupreg_input" path="firstName" />
		    	    <div class="reg_errors">
		    			<form:errors cssClass="alert alert-error" path="firstName" />
		    		</div>
	    		</td>
    		</tr>
    		
    		<tr>
    			<th nowrap="nowrap">Last name <img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" /></th>
    			<td>
    			<form:input cssClass="popupreg_input" path="lastName" />
		    	    <div class="reg_errors">
		    			<form:errors cssClass="alert alert-error" path="lastName" />
		    		</div>
	    		</td>
    		</tr>
    		
    		<tr>
    			<th nowrap="nowrap">Password <img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" /></th>
    			<td>
    			<form:password cssClass="popupreg_input" path="password" />
		    	    <div class="reg_errors">
		    			<form:errors cssClass="alert alert-error" path="password" />
		    		</div>
	    		</td>
    		</tr>
    		
    		
    		<tr>
    			<th nowrap="nowrap">Retype Password <img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" /></th>
    			<td>
    			<form:password cssClass="popupreg_input" path="retypePassword" />
		    	    <div class="reg_errors">
		    			<form:errors cssClass="alert alert-error" path="retypePassword" />
		    		</div>
	    		</td>
	    		
	    		<th class="second">Country</th>
	    		<td>
    				<form:password cssClass="popupreg_input" path="country" />
		    	    <div class="reg_errors">
		    			<form:errors cssClass="alert alert-error" path="country" />
		    		</div>
	    		</td>
    		</tr>
    		
    		<tr>
    			<th>Short Bio</th>
    			<td>
    			bla
    			</td>
    		</tr>
    		
    		<tr>
    			<th nowrap="nowrap">Retype the words <img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" /></th>
    			<td colspan="3">
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
		    				
	    		</td>
    		</tr>
    		
    		<tr>
    			<th nowrap="nowrap">Terms of use</th>
    			<td class="popupreg_terms-right" colspan="3">
    				By registering for this site, you agree to abide<br />
            		by the
            	<a href="/web/guest/resources/-/wiki/Main/Terms+of+use">Terms of Use</a>
    			</td>
    		</tr>
    	</table>
    	
    	<div class="clearfix"><!--  --></div>
    	<div class="popupreg_submit_wrap">
        	<div class="blue-button">
            	<a href="javascript:;" onclick="pageTracker._trackPageview('/user/registerSubmit');jQuery('#registrationForm').submit();">CREATE account</a>
        	</div>
    	</div>
    </form:form>
    
	
</div>
		
</jsp:root>