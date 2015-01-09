<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link rel="stylesheet" href="/climatecolab-theme/css/style.css?tt=$themeTimestamp" type="text/css" />

<portlet:defineObjects />

<%
  String portletTitle = HtmlUtil.escape(PortalUtil.getPortletTitle(renderResponse));

  if (portletTitle == null) {
    portletTitle = LanguageUtil.get(pageContext, "portlet");
  }

%>


<c:set var='requestScope' value='${requestScope}'/>

<%--
pageScope
 Loop over the JSTL implicit object, stored in the
     page-scoped attribute named scope that was set above.
     That implicit object is a map
<c:forEach items='${requestScope}' var='p'>
    <ul>
        Display the key of the current item, which
             represents the parameter name
        <li>Parameter Name: <c:out value='${p.key}'/></li>

        Display the value of the current item, which
             represents the parameter value
        <li>Parameter Value: <c:out value='${p.value}'/></li>
    </ul>
</c:forEach>--%>

<div class="alert alert-error">
    <%= LanguageUtil.format(pageContext, "is-temporarily-unavailable", portletTitle, false) %>
</div>

<!-- ERROR REPORT POPUP -->
<div id="popup_error_reporting" class="popup-wrap">
    <div class="popup">
        <div class="closepopuplogin"><a href="javascript:;" onclick="jQuery('#popup_error_reporting').hide()"><img src="/climatecolab-theme/images/help_close.png" width="20" height="20" alt="X" /></a></div>
        <h4>Oh Snap!</h4>
        <div class="login_popup_box" style="margin: 0 8px 0 0;padding: 0px;">
            <form id="signInForm_form" method="post" action="/c/reportError">
                <input name="url" type="hidden" />
                <input name="stackTrace" type="hidden" value="${requestScope["stackTrace"]}"/>
                <div class="popup-message">
                    <p>Unfortunately your request caused an error. To help us improve our service we would kindly ask you to provide a short description of the steps you took prior to receiving this message.</p>
                </div>
                <textarea name="description" class="username" style="width:98%; min-height: 50px; margin-top: -10px;" placeholder="Description" ></textarea>
                <br/>
                <br/>
                <div class="popup-message">
                    <p>If you provide your e-Mail address below we will let you know as soon as we've fixed to the problem</p>
                </div>
                <input type="text" name="email" class="email" style="width:98%; min-height: 25px; padding-left: 2%; margin-top: -10px;" placeholder="Email address" />
                <div class="clearfix"><!-- --></div>
                <div class="btns">
                    <div class="blue-button">
                        <a href="javascript:;" class="login-submit" onclick="jQuery(this).parents('form').find('input[type=hidden]').filter(':first').val(document.location.href);jQuery(this).parents('form').submit();" id="errorReportPopupSubmit">Submit</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- /ERROR REPORT POPUP -->