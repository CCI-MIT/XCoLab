<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="./init.jspx" />

	<c:if test="${updateError}">
		<script type="text/javascript" >
			updateError();
		</script>
	</c:if>

	<portlet:resourceURL var="unlinkFacebookSSO" id="unlinkFacebookSSO" />
	<portlet:resourceURL var="unlinkGoogleSSO" id="unlinkGoogleSSO"/>

	<portlet:resourceURL var="updateUserSendEmailOnMessageSettings" id="updateUserSendEmailOnMessageSettings"/>
	<portlet:resourceURL var="updateUserSendEmailOnActivitySettings" id="updateUserSendEmailOnActivitySettings"/>
	<portlet:resourceURL var="updateUserSendDailyEmailOnActivitySettings" id="updateUserSendDailyEmailOnActivitySettings"/>

	<portlet:resourceURL var="newsletterSubscribtionStatus" id="newsletterSubscribtionStatus" >
		<portlet:param name="email" value="${userBean.emailStored}"/>
	</portlet:resourceURL>
	<portlet:resourceURL var="newsletterSubscribe" id="newsletterSubscribe" >
		<portlet:param name="email" value="${userBean.emailStored}"/>
	</portlet:resourceURL>
	<portlet:resourceURL var="newsletterUnSubscribe" id="newsletterUnSubscribe" >
		<portlet:param name="email" value="${userBean.emailStored}"/>
	</portlet:resourceURL>

	<portlet:actionURL var="updateUserProfileForm">
		<portlet:param name="action" value="update" />
	</portlet:actionURL>


	<form style="margin-top: 20px;" action="/userprofile-portlet/fileUploadServlet" method="post"
		  enctype="multipart/form-data" target="_fileUploadFrame" id="fileUploadForm">
		<input type="file" name="file" id="portraitUploadInput" />
	</form>

	<h1>Edit Member Profile</h1>
	<div class="is-required">
		<img src="/climatecolab-theme/images/reg-star.png" width="8"
			 height="7" align="texttop" /> is a required field.
	</div>


	<form:form autocomplete="off" id="updateUserProfileForm"
			   action="${updateUserProfileForm}" commandName="userBean" method="post">
		<div id="main" class="userprofile">
			<div class="reg_errors"><!--  --></div>
			<form:errors cssClass="alert alert-error" />

			<!-- use these fields to prevent chrome from wrongly filling in country and current password automatically -->
			<input type="text" style="display:none" />
			<input type="password" style="display:none" />

			<div class="comm_member">
				<div class="comm_member-photo">
					<div style="float: right">
						<img src="${baseImagePath}${userBean.portrait}" id="userPortrait"
							 width="150" height="150" alt="${userBean.screenName}" />
						<div class="clearfix"><!--  --></div>
						<div id="uploadImageContainer"><!--  --></div>
					</div>
				</div>
				<div class="comm_member-info">
					<table border="0" cellpadding="0" cellspacing="0" class="colab nlpad">
						<tr>
							<th class="b m nowrap" width="170">First Name
								<img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" />
							</th>
							<td>
								<form:input id="firstName" path="firstName" cssClass="profile_input"/>
								<div class="reg_errors"><!--  -->
									<form:errors cssClass="alert alert-error" path="firstName" />
								</div>
							</td>
						</tr>
						<tr>
							<th class="b nowrap">Last Name
								<img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" />
							</th>
							<td>
								<form:input id="lastName" path="lastName" cssClass="profile_input"/>
								<div class="reg_errors"><!--  -->
									<form:errors cssClass="alert alert-error" path="lastName" />
								</div>
							</td>
						</tr>
						<tr>
							<th class="b m nowrap" width="170">Screen Name
								<img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" />
							</th>
							<td>
								<form:input id="screenNameMessage" cssClass="profile_input" path="screenName"></form:input>
								<div class="reg_errors"><!--  -->
									<form:errors cssClass="alert alert-error" path="screenName" />
								</div>
							</td>
						</tr>

						<tr>
							<th class="b m">Country</th>
							<td> <jsp:include page="./countrySelect.jspx"/></td>
						</tr>

						<!--
						<tr>
							<th class="b">Member Since</th>
							<td class="l">
								&#160;
								<fmt:formatDate value="${currentUser.joinDate}" type="date" dateStyle="medium" timeZone="America/New_York" />
							</td>
						</tr>
						-->
					</table>

				</div>
				<div class="clearfix"><!--  --></div>
			</div>

			<div>
				<table border="0" cellpadding="0" cellspacing="0" class="colab nlpad">
					<tbody>
						<tr>
							<th class="b m" >Stored email
							</th>
							<td>
								<form:input  id="emailStored" cssClass="profile_input" path="emailStored" readonly="true"></form:input>
							</td>
						</tr>
						<tr id="EmailRow">
							<th class="b m" >New email
								<img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" />
							</th>
							<td>
								<form:input  id="email" cssClass="profile_input" path="email"></form:input>
								<div class="reg_errors"><!--  -->
									<form:errors cssClass="alert alert-error" path="email" />
								</div>
							</td>
						</tr>

						<tr id="retypeEmailRow" style="${emailError ? '' : 'display: none;'}">
							<th class="b m">New email retype
								<img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" />
							</th>
							<td>
								<form:input  id="retypeEmail" cssClass="profile_input" path="retypeEmail"></form:input>
								<div class="reg_errors"><!--  -->
									<form:errors cssClass="alert alert-error" path="retypeEmail" />
								</div>
							</td>
						</tr>

						<tr>
							<th class="b m nowrap">Current password
								<img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" />
							</th>
							<td>
								<form:password cssClass="profile_input" path="currentPassword" />
								<div class="reg_errors">
									<form:errors cssClass="alert alert-error" path="currentPassword" />
								</div>
							</td>
						</tr>
						<tr>
							<th class="b m nowrap">New password
								<img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" />
							</th>
							<td>
								<form:password cssClass="profile_input" path="password"/>
								<div class="reg_errors"><!--  -->
									<form:errors cssClass="alert alert-error" path="password" />
								</div>
							</td>
						</tr>
						<tr id="retypePasswordRow" style="${passwordError ? '' : 'display: none;'}">
							<th class="b m nowrap">New password retype
								<img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" />
							</th>
							<td>
								<form:password cssClass="profile_input" path="retypePassword" />
								<div class="reg_errors"><!--  -->
									<form:errors cssClass="alert alert-error" path="retypePassword" />
								</div>
							</td>
						</tr>
					</tbody>
				</table>

				<div class="profile_about-head">Short bio</div>
				<div class="userProfileAbout" >
					<form:textarea
						cssClass="ckeditor_placeholder shortBioContent" path="shortBio" />
					<div class="inputLimitContainer" style="margin-top: 10px; float:right; ">
						<span class="limit_characterCount"><!--  --></span>/&#160;
						<span class="limit_charactersMax">2000</span> characters
					</div>
					<div class="reg_errors"><!--  -->
						<form:errors cssClass="alert alert-error" path="shortBio" />
					</div>
					<div class="clearfix">&#160;</div>
				</div>

				<div class="blue-button">
					<a href="javascript:;" onclick="updateTextarea();jQuery('#updateUserProfileForm').submit();">Save</a>
				</div>
				<div class="blue-button"><a href="/web/guest/member/-/member/userId/${currentUser.userId}">Cancel</a></div>
			</div>
		</div> <!-- /main -->

		<div class="right_col v1">
			<div class="comm_rightcol">
				<div class="comm_rightcol-title2">MESSAGE SETTINGS</div>
				<table border="0" cellpadding="0" cellspacing="0" class="colab">
					<tr class="colabRow">
						<td>
							<form:checkbox id="sendEmailOnMessage" cssClass="cmn-toggle cmn-toggle-round"
										   path="sendEmailOnMessage" onchange="updateUserSendEmailOnMessageSettings(this)"/>
							<form:label path="sendEmailOnMessage"/>
						</td>
						<td class="profile_settings">
							Send me an email copy of messages I receive.
						</td>
					</tr>
					<tr class="colabRow">
						<td>
							<form:checkbox id="sendEmailOnActivity" cssClass="cmn-toggle cmn-toggle-round"
										   path="sendEmailOnActivity" onchange="updateUserSendEmailOnActivitySettings(this)"/>
							<form:label path="sendEmailOnActivity"/>
						</td>
						<td class="profile_settings">
							Send me an email copy of activities that I'm subscribed to.
						</td>
					</tr>
					<tr class="colabRow">
						<td>
							<form:checkbox id="sendDailyEmailOnActivity" disabled="${not userBean.sendEmailOnActivity}"
										   cssClass="cmn-toggle cmn-toggle-round" path="sendDailyEmailOnActivity"
										   onchange="updateUserSendDailyEmailOnActivitySettings(this)" />
							<form:label path="sendDailyEmailOnActivity"/>
						</td>
						<td class="profile_settings">
							Only send me a daily summary of all activities I'm subscribed to.
						</td>
					</tr>
				</table>
				<div class="clearfix"><!-- --></div>
			</div>
		</div> <!-- /right_col3 -->

		<form:hidden id="userRegistrationImageId" path="imageId" />
	</form:form>

	<div class="right_col v1">
		<div class="comm_rightcol">
			<div class="comm_rightcol-title2">COLAB NEWSLETTER SETTINGS</div>
			<table border="0" cellpadding="0" cellspacing="0" class="colab">
				<form:form commandName="newsletterBean">
					<tr class="colabRow">
						<td>
							<form:checkbox id="emailSubscribedToNewsletter" cssClass="cmn-toggle cmn-toggle-round"
										   path="emailSubscribedToNewsletter" onchange="updateNewsletterSettings(this)" />
							<form:label path="emailSubscribedToNewsletter"/>
						</td>
						<td>
							Send me the CoLab newsletter.
						</td>
					</tr>
				</form:form>
			</table>
			<div class="clearfix"><!-- --></div>
		</div>
	</div> <!-- /right_col -->

	<div class="right_col v1">
		<div class="comm_rightcol">
			<div class="comm_rightcol-title2">SSO SETTINGS</div>
			<table border="0" cellpadding="0" cellspacing="0" class="colab">
				<tr class="colabRow">
					<td>
						<c:choose>
							<c:when test="${currentUser.hasFacebookId}">
								<input id="hasFacebookId" class="cmn-toggle cmn-toggle-round" type="checkbox"
									   value="true" checked="checked" onClick="unlinkFacebookSSO();" />
							</c:when>
							<c:otherwise>
								<input id="hasFacebookId" class="cmn-toggle cmn-toggle-round" type="checkbox"
									   disabled="true" />
							</c:otherwise>
						</c:choose>
						<label for="hasFacebookId"></label>
					</td>
					<td>
						Facebook account
					</td>
				</tr>
				<tr class="colabRow">
					<td>
						<c:choose>
							<c:when test="${currentUser.hasOpenId}">
								<input id="hasOpenId" class="cmn-toggle cmn-toggle-round" type="checkbox"
									   value="true" checked="checked" onClick="unlinkGoogleSSO();" />
							</c:when>
							<c:otherwise>
								<input id="hasOpenId" class="cmn-toggle cmn-toggle-round" type="checkbox"
									   disabled="true" />
							</c:otherwise>
						</c:choose>
						<label for="hasOpenId"></label>
					</td>
					<td>
						Google account
					</td>
				</tr>
			</table>
			<div class="clearfix"><!-- --></div>
		</div>
	</div> <!-- /right_col3 -->

		<script type="text/javascript" src="/html/js/editor/ckeditor_old/ckeditor.js"><!-- --></script>
	<script>
		function updateTextarea() {
			for ( var instance in CKEDITOR.instances) {
				CKEDITOR.instances[instance].updateElement();
			}
		}
		updateTextarea();
	</script>


	<iframe name="_fileUploadFrame" id="fileUploadFrame" class="hidden"
			style="display: none;">
		<!-- comment -->
	</iframe>

	<script type="text/javascript">
		jQuery("#portraitUploadInput").change(function() {
			console.log("Start Upload.");
			jQuery("#fileUploadForm").submit();
			jQuery("#userPortrait").block({
				message : "Sending message"
			});

		});

		function isJSONavailable(){
			return typeof JSON === 'object' &amp;&amp; typeof JSON.parse === 'function';
		}
		function updateProfilePictureId (imageId){
			jQuery("#userPortrait").attr("src","/image/contest?img_id=" + imageId);
			jQuery("#userRegistrationImageId").val(imageId);
		}

		jQuery("#fileUploadFrame").load(
				function() {
					try {
						if(jQuery(this).contents().text()) {
							var response;
							if (isJSONavailable()) {
								response = JSON.parse(jQuery(this).contents().text());
							} else {
								response = eval("(" + jQuery(this).contents().text() + ")");
							}
							if (response.hasOwnProperty("imageId") &amp;&amp; response.imageId > 0) {
								updateProfilePictureId(response.imageId);
								jQuery("#userPortrait").unblock();
								jQuery.growlUI('', 'Profile image uploaded, press save to store!');
							}
						}
					}
					catch (e) {
						jQuery.growlUI('', 'Profile image upload failed!');
						//console.log("fileUploadFrame error",e);
					}

				});

		function updateUploadBtnOffset() {
			var container = jQuery("#uploadImageContainer");
			var containerOffset = container.offset();
			jQuery("#portraitUploadInput").offset(containerOffset);

		}
		jQuery(function() {
			updateUploadBtnOffset();
			$(window).resize(updateUploadBtnOffset);
			initializeCkeditor();

			for (var key in CKEDITOR.instances) {
				// Set initial text length
				var len = jQuery.trim(strip(CKEDITOR.instances[key].getData()).replace(/&amp;lt;[^&gt;]*&gt;/g, "").replace(/\s+/g, " ").length);
				validateCharlength( $('[id="'+key+'"]'), len);
			}
		});

		var data = $('.ckeditor-placeholder').val();
		function updateTextarea() {
			for (var instance in CKEDITOR.instances) {
				CKEDITOR.instances[instance].updateElement();
			}
			data = jQuery('.ckeditor-placeholder').val();
		}

		function initializeCkeditor() {
			jQuery('ckeditor_placeholder').val(data);
			for (var key in CKEDITOR.instances) {
				CKEDITOR.instances[key].destroy(true);
			}

			$('.ckeditor_placeholder').each(function (idx, val) {
				var textId = $(this).attr('id');
				var editor = CKEDITOR.replace(textId);
				editor.setData($(this).val());
			});

			// Editor change callback
			for (var key in CKEDITOR.instances) {
				CKEDITOR.instances[key].on('saveSnapshot', function(ev) {
					var len = jQuery.trim(strip(ev.editor.getData()).replace(/&amp;lt;[^&gt;]*&gt;/g, "").replace(/\s+/g, " ").length);
					validateCharlength( $('[id="'+key+'"]'), len);
				});
			}
		}

		function strip(html)
		{
			var tmp = document.createElement("DIV");
			tmp.innerHTML = html;
			return tmp.textContent || tmp.innerText || "";
		}

		function validateCharlength(elem, len) {
			var charCountContainer = elem.parent().find('.inputLimitContainer');

			var elem = charCountContainer.children('.limit_characterCount');
			var max = parseInt(charCountContainer.children('.limit_charactersMax').text());
			if (elem) {
				if (len > max) {
					charCountContainer.addClass('overflow');
				}
				else {
					charCountContainer.removeClass('overflow');
				}
				elem.text(""+len);
			}
		}

		jQuery("#email").keyup(function(){
			if(jQuery("#email").val() !== "" &amp;&amp; jQuery("#email").val() !== jQuery("#emailStored").val()) {
				jQuery("#retypeEmailRow").slideDown();
			} else{
				jQuery("#retypeEmailRow").slideUp();
			}
		})

		jQuery("#password").keyup(function(){
			if(jQuery("#password").val() !== "") {
				jQuery("#retypePasswordRow").slideDown();
			} else {
				jQuery("#retypePasswordRow").slideUp();
			}
		})



		jQuery("#sendEmailOnActivity").change(function(){

			if(!jQuery("#sendEmailOnActivity").is(":checked")){
				jQuery("#sendDailyEmailOnActivity").attr('checked', false);
				jQuery("#sendDailyEmailOnActivity").attr("disabled", true);
			} else{
				jQuery("#sendDailyEmailOnActivity").removeAttr("disabled");
			}
		})


		function updateMessageSettingsOnServer(updateUrl, formData){
			var deferred = jQuery.Deferred();
				sendAjaxToServer(updateUrl, formData).done(function(response){
					if(response){
						jQuery.growlUI('', 'Successfully updated message settings!');
					} else{
						jQuery.growlUI('','Updating message settings failed!');
					}
				});
			return deferred;
		}

		/*
		function updateMessageSettings(element){

			var updateMessageSettingsURL = '${updateUserSendEmailOnMessageSettings}';
			var checkedFormData = createCheckedFormData(element.checked);
			updateMessageSettingsOnServer(updateMessageSettingsURL, checkedFormData).done(function (result) {
				if(!result) {
					jQuery("#" + element.id +"").attr('checked', true);
				}

			});
		}*/

		function updateUserSendEmailOnActivitySettings(element){

			var updateMessageSettingsURL = '${updateUserSendEmailOnActivitySettings}';
			var checkedFormData = createCheckedFormData(element.checked);
			updateMessageSettingsOnServer(updateMessageSettingsURL, checkedFormData).done(function (result) {
				if(!result) {
					jQuery("#updateUserSendEmailOnActivitySettings").attr('checked', !element.checked);
				}
			});

			/*
			 var updateMessageSettingsActivityDailyURL = '${updateUserSendDailyEmailOnActivitySettings}';
			sendAjaxToServer(updateMessageSettingsActivityURL, formData).done(function(response){
				if(response){

					if(!element.checked) {
						sendAjaxToServer(updateMessageSettingsActivityDailyURL, formData).done(function (response) {
							if (response) {
								jQuery.growlUI('', 'Successfully updated message settings!');
							} else {
								jQuery.growlUI('', 'Updating message settings partly failed!');
								jQuery("#updateUserSendDailyEmailOnActivitySettings").attr('checked', true);
							}
						});
					} else{
						jQuery.growlUI('', 'Successfully updated message settings!');
					}

				} else{
					jQuery.growlUI('','Updating message settings failed!');
					jQuery("#updateUserSendDailyEmailOnActivitySettings").attr('checked', true);
				}
			});*/

		}

		function updateUserSendEmailOnMessageSettings(element){

			var updateMessageSettingsURL = '${updateUserSendEmailOnMessageSettings}';
			var checkedFormData = createCheckedFormData(element.checked);
			updateMessageSettingsOnServer(updateMessageSettingsURL, checkedFormData).done(function (result) {
				if(!result) {
					jQuery("#updateUserSendEmailOnMessageSettings").attr('checked', !element.checked);
				}
			});
		}

		function createCheckedFormData(elementChecked){
			var formData = "messageSetting=";
			if(elementChecked) {
				formData += "true";
			} else {
				formData += "false";
			}
			return formData;
		}

		function updateUserSendDailyEmailOnActivitySettings(element){

			var updateMessageSettingsURL = '${updateUserSendDailyEmailOnActivitySettings}';
			var checkedFormData = createCheckedFormData(element.checked);
			updateMessageSettingsOnServer(updateMessageSettingsURL, checkedFormData).done(function (result) {
				if(!result) {
					jQuery("#updateUserSendDailyEmailOnActivitySettings").attr('checked', !element.checked);
				}
			});
		}


		function updateNewsletterSettings(element){
			//console.log("updateNewsletterSettings", element.checked);
			if(element.checked){
				subscribeNewsletter();
			} else{
				unSubscribeNewsletter();
			}
		}

		function updateNewsletterSettingsOnServer(updateUrl){
			var deferred = jQuery.Deferred();
				sendAjaxToServer(updateUrl).done(function(response){
					if(response){
						deferred.resolve(true);
						jQuery.growlUI('', 'Successfully updated Newsletter settings!');
					} else{
						deferred.resolve(false);
						jQuery.growlUI('','Updating Newsletter settings failed!');
					}
				});
			return deferred;
		}

		function subscribeNewsletter(){
			var newsletterSubscribeURL = '${newsletterSubscribe}';
			updateNewsletterSettingsOnServer(newsletterSubscribeURL).done(function (result) {
				if(!result) {
					jQuery("#emailSubscribedToNewsletter").attr('checked', false);
					//jQuery("#newsletterSettingsLabel").text("Un-Subscribe");
				}
			});

		}
		function unSubscribeNewsletter() {
			var newsletterUnSubscribeURL = '${newsletterUnSubscribe}';
			updateNewsletterSettingsOnServer(newsletterUnSubscribeURL).done(function (result) {
				if(!result) {
					jQuery("#emailSubscribedToNewsletter").attr('checked', true);
					//jQuery("#newsletterSettingsLabel").text("Subscribe");
				}
			});
		}

		function unlinkSSOonServer(updateUrl){
			var deferred = jQuery.Deferred();
				sendAjaxToServer(updateUrl).done(function(response){
					if(response){
						deferred.resolve(true);
						jQuery.growlUI('','Unlinking SSO successfull!');
					} else{
						deferred.resolve(false);
						jQuery.growlUI('','Unlinking SSO failed!');
					}
				});
			return deferred;
		}

		function unlinkFacebookSSO(){
			var unlinkSSOURL = '${unlinkFacebookSSO}';
			unlinkSSOonServer(unlinkSSOURL).done(function (result) {
				if(result) {
					jQuery("#hasFacebookId").attr('onclick',"");
					jQuery("#hasOpenId").attr('disabled', true);
					jQuery("#hasOpenId").removeAttr('checked');
				} else{
					jQuery("#hasFacebookId").attr('checked', true);
				}
			});
		}

		function unlinkGoogleSSO(){
			var unlinkSSOURL = '${unlinkGoogleSSO}';
			unlinkSSOonServer(unlinkSSOURL).done(function (result) {
				if(result) {
					jQuery("#hasOpenId").attr('onclick', "");
					jQuery("#hasOpenId").attr('disabled', "true");
					jQuery("#hasOpenId").removeAttr('checked');
				} else {
					jQuery("#hasOpenId").attr('checked', true);
				}
			});
		}

	</script>

</jsp:root>