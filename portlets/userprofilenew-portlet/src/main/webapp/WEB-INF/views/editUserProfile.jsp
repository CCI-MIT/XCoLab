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

	<portlet:actionURL var="updateUserProfileForm">
		<portlet:param name="action" value="update" />
	</portlet:actionURL>

	<form style="margin-top: 20px;" action="/userprofilenew-portlet/fileUploadServlet" method="post"
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
						<div class="clearfix">
							<!--  -->
						</div>
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
								<form:input  id="retypeEmail" cssClass="profile_input" path="emailStored" readonly="true"></form:input>
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

						<!--
						<tr>
							<td class="b m nowrap">Current password
								<img src="/climatecolab-theme/images/reg-star.png" width="8" height="7" align="texttop" />
							</td>
							<td>
								<form:password cssClass="profile_input" path="currentPassword" />
								<div class="reg_errors">
									<form:errors cssClass="alert alert-error" path="currentPassword" />
								</div>
							</td>
						</tr> -->
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


						<c:if test="${currentUser.hasFacebookId}">
							<tr>
								<th class="b m">Facebook</th>
								<td>
									<div class="blue-button">
										<a href="javascript:;" onclick="updateTextarea();jQuery('#updateUserProfileForm').submit();">Unlink Facebook account</a>
										<!-- TODO <ice:commandLink  actionListener="${userprofileBean.unlinkSSOAccount}" onclick="updateTextarea();" value="Unlink Facebook account"><f:attribute name="accountType" value="FACEBOOK" /> </ice:commandLink> -->
									</div>
								</td>
							</tr>
						</c:if>

						<c:if test="${currentUser.hasOpenId}">
							<tr>
								<th class="b m">Google</th>
								<td>
									<div class="blue-button">
										<a href="javascript:;" onclick="updateTextarea();jQuery('#updateUserProfileForm').submit();">Unlink Google account</a>
										<!-- TODO <ice:commandLink  actionListener="${userprofileBean.unlinkSSOAccount}" onclick="updateTextarea();" value="Unlink Google account"><f:attribute name="accountType" value="GOOGLE" /> </ice:commandLink>-->
									</div>
								</td>
							</tr>
						</c:if>
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
					<tr>
						<td>
							<form:checkbox cssClass="profile_cbx" path="sendEmailOnMessage"/>
						</td>
						<td class="profile_settings">
							Send me an email copy of messages I receive.
						</td>
					</tr>
					<tr>
						<td>
							<form:checkbox id="sendEmailOnActivity" cssClass="profile_cbx" path="sendEmailOnActivity"/>
						</td>
						<td class="profile_settings">
							Send me an email copy of activities that I'm subscribed to.
						</td>
					</tr>
					<tr>
						<td>
							<form:checkbox id="sendDailyEmailOnActivity" disabled="${not userBean.sendEmailOnActivity}"  cssClass="profile_cbx" path="sendDailyEmailOnActivity" />
						</td>
						<td class="profile_settings">
							Only send me a daily summary of all activities I'm subscribed to
						</td>
					</tr>
				</table>
				<div class="clearfix"><!-- --></div>
			</div>
		</div> <!-- /right_col3 -->

		<form:hidden id="userRegistrationImageId" path="imageId" />
	</form:form>

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
			jQuery("#fileUploadForm").submit();
			jQuery("#userPortrait").block({
				message : "Sending message"
			});

		});
		jQuery("#fileUploadFrame").load(
				function() {
					try {
						console.log("jQuery(this).contents()", jQuery(this).contents());

						var imageIdPos = jQuery(this).contents().text().indexOf('{"imageId":')

						if (imageIdPos > 0) {

							var response = eval("("
							+ jQuery(this).contents().text().substring(imageIdPos) + ")");

							console.log("response -> ", response);
							jQuery("#userPortrait").attr("src",
									"/image/contest?img_id=" + response.imageId);
							console.log("#userPortrait.src -> ", response.imageId);
							jQuery("#userPortrait").unblock();

							jQuery("#userRegistrationImageId")
									.val(response.imageId);
						}

					}
					catch (e) {
						// ignore
						console.log("fileUploadFrame error",e);
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

	</script>

</jsp:root>