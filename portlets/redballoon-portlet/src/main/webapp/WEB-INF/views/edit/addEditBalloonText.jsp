<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<link rel='stylesheet' href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"></link>
	
	<ol class="breadcrumb">
		<portlet:renderURL var="backUrl" />
  		<li><a href="${backUrl }">Back</a></li>
  		<li class="active">Add/Edit text</li>
		</ol>
	<h1>
		<c:choose>
			<c:when test="${empty balloonText }">
				Add new balloon text
			</c:when>
			<c:otherwise>
				Edit ${balloonText.name }
			</c:otherwise>
		</c:choose>
	</h1>
	
	<portlet:actionURL var="addEditBalloonTextUrl" />
	
	<form:form action="${addEditBalloonTextUrl }" modelAttribute="addEditBalloonText">
		<input type="hidden" name="action" value="addEditBalloonText" />
		<input type="hidden" name="balloonTextId" value="${addEditBalloonText.balloonTextId }" />
		<div class="control-group">
    		<div class="controls">
      			<label class="checkbox">
        			<form:checkbox path="enabled" /> Enabled
				</label>
    		</div>
		</div>
	  <div class="form-group">
    	<label for="balloonTextName">Name</label>
    	<form:input class="form-control" id="balloonTextName" placeholder="Enter name" path="name" />
  	  </div>
  	  
  	<div class="form-group">
    	<label for="balloonAcceptTosText">Terms of service text</label>
    	<form:textarea class="form-control" id="balloonAcceptTosText" path="acceptTosText" />
  	</div>
  	<div class="form-group ckeditorContainer">
    	<label for="balloonTextTextBeforeForm">Text before form requesting email address</label>
    	<liferay-ui:input-editor editorImpl="editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp" name="textBeforeForm" initMethod="initEditorBefore"/>
    	<form:textarea class="form-control hidden" id="balloonTextTextBeforeForm" path="textBeforeForm" />
  	</div>
  	  <div class="form-group ckeditorContainer">
    	<label for="balloonTextTextBeforeForm">Text after form requesting email address</label>
    	<liferay-ui:input-editor editorImpl="editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp" initMethod="initEditorAfter" name="textAfterForm" />
    	<form:textarea class="form-control hidden" id="balloonTextTextAfterForm" path="textAfterForm" />
  	</div>
  	<div class="form-group ckeditorContainer">
    	<label for="balloonTextTextBeforeForm">Text before share buttons</label>
    	<liferay-ui:input-editor editorImpl="editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp" name="textBeforeShareButtons" initMethod="initEditorBeforeShareButtons"/>
    	<form:textarea class="form-control hidden" id="balloonTextTextBeforeShareButtons" path="textBeforeShareButtons" />
  	</div>
  	  <div class="form-group ckeditorContainer">
    	<label for="balloonTextTextBeforeForm">Text after share buttons</label>
    	<liferay-ui:input-editor editorImpl="editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp" initMethod="initEditorAfterShareButtons" name="textAfterShareButtons" />
    	<form:textarea class="form-control hidden" id="balloonTextTextAfterShareButtons" path="textAfterShareButtons" />
  	</div>
  	
  	<div class="form-group">
    	<label for="balloonTextEmailSubjectTemplate">Subject for email that is sent to the user after registration</label>
    	<form:textarea class="form-control" id="balloonTextEmailSubjectTemplate" path="emailSubjectTemplate" />
  	</div>
  	
  	
  	<div class="form-group">
    	<label for="facebookPageTitle">Facebook - page title</label>
    	<form:input class="form-control" id="facebookPageTitle" path="facebookTitle" />
  	</div>
  	
  	<div class="form-group">
    	<label for="facebookPageDescription">Facebook - page description</label>
    	<form:textarea class="form-control" id="facebookPageDescription" path="facebookDescription" />
  	</div>
  	
  	<div class="form-group">
    	<label for="twitterTitle">Twitter - page title</label>
    	<form:input class="form-control" id="twitterPageTitle" path="twitterTitle" />
  	</div>
  	
  	<div class="form-group">
    	<label for="twitterDscription">Twitter - page description</label>
    	<form:textarea class="form-control" id="twitterPageDescription" path="twitterDescription" />
  	</div>
  	
  	<div class="form-group ckeditorContainer">
    	<label for="balloonTextTextBeforeForm">Email that is sent to the user after registration</label>
       <p><strong>URLPLACEHOLDER</strong> will be replaced with the users link.<br/></p>
    	<liferay-ui:input-editor editorImpl="editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp" initMethod="initEditorEmail" name="emailTemplate" />
    	<form:textarea class="form-control hidden" id="balloonTextEmailTemplate" path="emailTemplate" />
  	</div>
  <button type="submit" class="btn btn-default">Submit</button>
  
  
	
	
	</form:form>
	
    <script type="text/javascript">
    	function initEditor(dataContainerId) {
    		var dataContainer = $(dataContainerId);
    		var val = dataContainer.val();
    		dataContainer.remove();
    	    
    		return val;
    	}    
    	function <portlet:namespace />initEditorBefore() { return initEditor("#balloonTextTextBeforeForm"); }
    	function <portlet:namespace />initEditorAfter() { return initEditor("#balloonTextTextAfterForm"); }
    	function <portlet:namespace />initEditorAfterShareButtons() { return initEditor("#balloonTextTextAfterShareButtons"); }
    	function <portlet:namespace />initEditorBeforeShareButtons() { return initEditor("#balloonTextTextBeforeShareButtons"); }
    	function <portlet:namespace />initEditorEmail() { return initEditor("#balloonTextEmailTemplate"); }
        
        
    </script>
	
	
	
	
</jsp:root>