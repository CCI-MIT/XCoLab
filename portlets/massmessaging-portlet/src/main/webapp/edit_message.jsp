<%@ include file="init.jsp"%>
<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

 
<script type="text/javascript">

var recipients = [];
var recipientsByName = {};
var emailRecipients = [];
var screenNameRecipients = [];

var formApproved = false;


function <portlet:namespace />deleteRecipient(id) {
	var recipient = recipients[id];
	recipient.deleted = true;
	
	if (recipient.type == 'email') {
		emailRecipients.splice(recipient.typeArrayId, 1);
	}
	else {
		screenNameRecipients.splice(recipient.typeArrayid, 1);
	}
	delete recipientsByName[recipient.name];
	jQuery("#<portlet:namespace />recipient-" + recipient.id).fadeOut('normal', function() { jQuery(this).remove(); });
}

function <portlet:namespace />renderRecipient(recipient) {
	return "<li class='recipient' id='<portlet:namespace />recipient-" + recipient.id + "'>" + recipient.name + " <span class='delete'><a href='#' onClick='<portlet:namespace />deleteRecipient(" + recipient.id + "); return false'>X</a></span></li>";
}

function <portlet:namespace />renderRecipients(recipientsArray) {
	var output = [];
	for (var i=0; i < recipientsArray.length; i++) {
		recipientsArray[i].typeArrayId = i;
		output.push(<portlet:namespace />renderRecipient(recipientsArray[i]));
	}
	return output.join(' ');
}

function <portlet:namespace />sortRecipients(a, b) {
	if (a.name == b.name) {
		return 0;
	}
	else if (a.name < b.name) {
		return -1;
	}
	return 1;
}

function <portlet:namespace />refreshScreenNameRecipients() {
	
	
    jQuery('#<portlet:namespace />screenNameRecipientsList').html('');
	
}
function <portlet:namespace />addRecipients(recipientsArray) {
	var recipientsViewStr = [];
	var emailRecipientAdded = false;
	var screenNameRecipientAdded = false;
	for (var i=0; i < recipientsArray.length; i++) {
		var recipient = recipientsArray[i];
		var name = recipient.name;
		if (typeof(recipientsByName[name]) == 'undefined') {
			recipient.id = recipients.length;
			
			recipientsByName[recipient.name] = recipient;

			recipients.push(recipient);
			if (recipient.type == 'email') {
				emailRecipients.push(recipient);
				emailRecipientAdded = true;
			}
			else {
				screenNameRecipients.push(recipient);
				screenNameRecipientAdded = true;
			}
		}	 
	}

	// sort and display
	if (screenNameRecipientAdded) {
		screenNameRecipients.sort(<portlet:namespace />sortRecipients);
	    jQuery('#<portlet:namespace />screenNameRecipientsList').html(<portlet:namespace />renderRecipients(screenNameRecipients));
	}
	if (emailRecipientAdded) {
		emailRecipients.sort(<portlet:namespace />sortRecipients);
	    jQuery('#<portlet:namespace />emailRecipientsList').html(<portlet:namespace />renderRecipients(emailRecipients));
	}
	
	
	//jQuery('#<portlet:namespace />recipientsList').prepend(recipientsViewStr.join(" "));
}






    /**
     Function called to initialize WYSIWYG editor. Returned value will be used to fill 
     editor's textarea.

     @return String that should be used to initialize WYSIWYG content editor.
      
     */ 
    function <portlet:namespace />initEditor() {
        return "";
    }

    /**
    Function responsible for retrieval of WYSIWYG editor content and assigning it to 
    appropriate plan edit form field.
    
    */
   function <portlet:namespace />getHtmlValue() {
       if (typeof(window.<portlet:namespace />editor) !== 'undefined') {
           var x = window.<portlet:namespace />editor.getHTML();
           document.<portlet:namespace />messageForm.body.value = x;
       }
   }
   
    function <portlet:namespace />preSubmit() {
        if (!formApproved) {
            alert("Please review the form and check if all provided information is valid. This operation can't be undone.");
            formApproved = true; 
            return false;
        }
        <portlet:namespace />getHtmlValue();
        
        return true;
    }

    function <portlet:namespace />getRecipientsValue() {
    	var serializedRecipients = [];
    	for (var i=0; i < recipients.length; i++) {
        	var recipient = recipients[i];
        	if (recipient.deleted) {
            	continue;
        	}
        	serializedRecipients.push(recipient.name);
    	}
    	document.<portlet:namespace />messageForm.recipients.value = serializedRecipients.join('|');
    }

    jQuery(document).ready(function() {
        jQuery('#<portlet:namespace />messageForm').validate({
            rules: {
                name: "required",
                replyto: { 
                    required: true, 
                    email: true
                },
                subject: "required",
                body: { 
                    required : { depends: function() {
                        <portlet:namespace />getHtmlValue();
                        return true;
                    }}
                },
                recipients: {
                    required : { depends: function() {
                	    <portlet:namespace />getRecipientsValue();
                	    if (document.<portlet:namespace />messageForm.sendtoall.checked) {
                    	    return false;
                	    }
                	    return true;
                    }}
                }
                
            },
            messages: {
                recipients: "Specify at least one recipient."
            }
        });
        
        if (jQuery.trim(jQuery("#emailRecipients").val()).length > 0) {
            jQuery("#emailRecipients").val( jQuery.trim(jQuery("#emailRecipients").val()).replace(/\|/g, ",") );
            
            <portlet:namespace />addRecipientsByName();
        }
    });
    
    function showEnterRecipientsDialog() {
        jQuery("#emailRecipients").val('');
        jQuery('#<portlet:namespace />enterRecipientsByEmail').slideDown();
    }

    function <portlet:namespace />addRecipientsByName() {
        var recipientsStr = jQuery('#emailRecipients').val();
        var splitChars = [",", ";", "\n" ];
        var splitStrings = [ recipientsStr ];
        
        
        for (var i = 0; i < splitChars.length; i++) {
            var chr = splitChars[i];
            var tmp = [];
            for (var j=0; j < splitStrings.length; j++) {
                tmp = tmp.concat(splitStrings[j].split(chr));
            }
            splitStrings = tmp;
        }

        // validate all entered recipients
        var emailValidationRegexp = /^([a-zA-Z0-9_\.-])+@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        var screenNameValidationRegexp = /^([a-zA-Z0-9_\.-])+$/;

        var recipientsArray = [];
        var errorMessages = [];
        for (var i=0; i < splitStrings.length; i++) {
        	var recipient = {};
        	
            recipient.name = jQuery.trim(splitStrings[i]);
            // ignore empty strings
            if (recipient.name == "") {
                continue;
            }
            
            if (recipient.name.indexOf('@') > 0) {
                // we have an email
                if (!emailValidationRegexp.test(recipient.name)) {
                    errorMessages.push("Invalid email address: " + recipient.name);
                    continue;
                }
                recipient.type = 'email';
            }
            else {
                // we have a screen name
                if (!screenNameValidationRegexp.test(recipient.name)) {
                    errorMessages.push("Invalid screen name: " + recipient.name);
                    continue;
                }
                recipient.type = 'screenname';
            }

            recipientsArray.push(recipient);
        }
        if (errorMessages.length > 0) {
            alert (errorMessages.join('\n'));
        }
        else {
            <portlet:namespace />addRecipients(recipientsArray);
            jQuery('#<portlet:namespace />enterRecipientsByEmail').slideUp();
        }
        
    }
    function initMessageTextEditor(arg1, arg2, arg3) {
        var messageText = jQuery('#messageTextVal').val();
        return messageText;
    }
</script>

<portlet:actionURL windowState="MAXIMIZED" var="submitActionURL" name="sendMessage">
    <portlet:param name="struts_action" value="/view.jsp" />
</portlet:actionURL>

<%
    String name = ParamUtil.getString(request, "name");
    String description = ParamUtil.getString(request, "description");
    String replyTo = ParamUtil.getString(request, "replyto");
    String messageSenderName = ParamUtil.getString(request, "messageSenderName");
    String subject = ParamUtil.getString(request, "subject");
    String messageText = ParamUtil.getString(request, "body");
    String recipients = ParamUtil.getString(request, "recipients");
    List sendAs = (List) request.getAttribute("sendAs");
%>



<form action="${submitActionURL}" id='<portlet:namespace />messageForm' method="POST" name="<portlet:namespace />messageForm" onsubmit="return <portlet:namespace />preSubmit()" >

<liferay-ui:error exception="<%= InvalidMessageRecipientException.class %>"  message="Provided message recipients are invalid."></liferay-ui:error>
<fieldset>
    <legend>Basic message information</legend>
    <dl>
        <dt><label for="name">Message name <span class="required">*</span></label></dt>
        <dd><input name="name" type="text" size="40" value="<%= name %>"></dd>
        <dt><label for="description">Description</label></dt>
        <dd><textarea name="description" rows="5" cols="40"><%= description %></textarea></dd>
    </dl>
</fieldset>

<fieldset>
    <legend>Message details</legend>
    <dl>
        <dt><label for="sendas">Send as <span class="required">*</span> </label></dt>
        <dd>
        	<select name="sendas" id="sendAsSelect" >
        		<option value="">no-reply@climatecolab.org</option>
        		<c:forEach var="sendAsUser" items="${sendAs }">
        			<option value="${sendAsUser.name }">${sendAsUser.email }</option>
        		</c:forEach>
        	</select>
        	<script>
        	var sendAs = {};
    		<c:forEach var="sendAsUser" items="${sendAs }">
    			sendAs['${sendAsUser.name }'] = {
    					name: '${sendAsUser.name }',
    					email: '${sendAsUser.email }',
    					fullName: '${sendAsUser.fullName }'
    			};
    		</c:forEach>
        		jQuery("#sendAsSelect").change(function() {
        			var user = sendAs[jQuery(this).val()];
        			if (user) {
        				jQuery("#messageSenderNameInput").val(user.fullName);
        				jQuery("#replyToInput").val(user.email);
        			}
        		});
        	</script>
        </dd>
        <dt><label for="replyto">Reply to <span class="required">*</span> </label></dt>
        <dd><input name="replyto" type="text" value="<%= replyTo %>"  id="replyToInput" /></dd>
        <dt><label for="replyto">Name of message sender</label></dt>
        <dd><input name="messageSenderName" type="text" value="<%= messageSenderName %>"  id="messageSenderNameInput" /></dd>
        <dt><label for="subject">Message subject <span class="required">*</span></label></dt>
        <dd><input name="subject" type="text" value="<%= subject %>"/></dd>
        
        <dt><label for="body">Message text <span class="required">*</span></label></dt>
        <dd><input id="messageTextVal" type="hidden" name="body" value="<%=messageText %>"/> 
        <liferay-ui:input-editor width="100%" initMethod="initMessageTextEditor"/>
            <p><strong>Note</strong> Any link added in this field will be modified to track if user has clicked it.</p>
        
        
         </dd>
        
    
    </dl>
    
</fieldset>

<fieldset>
    <legend>Message recipients</legend>
    <liferay-ui:error exception="<%= InvalidMessageRecipientException.class %>"  message="Provided message recipients are invalid."></liferay-ui:error>
    <input type="hidden" name="recipients" />
    
    <div class="action-link">
        <a href="javascript:;" class="action" onclick="showEnterRecipientsDialog()"> <liferay-ui:icon image="add" /> Add recipients</a>
    </div>
    
    <div id="<portlet:namespace />enterRecipientsByEmail" style="display:none">
        <div>
            <div>
                <label for="emailRecipients">Email addresses or screen names separated by comma, semicolon or new line <span class="required">*</span></label><br />
                <textarea class="email-recipients" name="emailRecipients" id="emailRecipients"> <%= recipients %> </textarea><br />
            </div>
    
            <div class="action-link">
                <button onClick="jQuery('#<portlet:namespace />enterRecipientsByEmail').slideUp(); return false;"><span><liferay-ui:icon image="close" />Cancel</span></button>
                <button onClick="<portlet:namespace />addRecipientsByName(); return false;" ><span><liferay-ui:icon image="checked" />Add</span></button>
            </div>
        </div>
    </div>
    
    
    <dl>
        <dt><span>Email recipients</span></dt>
        <dd>
            <ul class="recipients" id="<portlet:namespace />emailRecipientsList">None</ul>
            <div class="clear"></div>
        </dd>
    </dl>
    <dl>
        
        <dt><span>Screen name recipients</span></dt>
            
        <dd>
            <ul class="recipients" id="<portlet:namespace />screenNameRecipientsList">None</ul>
            <div class="clear"></div>
        </dd>
    </dl>
    <div><input name="sendtoall" type="checkbox" /> Send message to all users</div>
</fieldset>


<input name="operation" type="submit" value="Send" />

</form>