<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

  <portlet:actionURL var="submitUrl">
    <portlet:param name="pageToDisplay" value="contestProposals" />
    <portlet:param name="error" value="invalidUrl" />
  </portlet:actionURL >

  <div id="content" style="width: 500px;">
    <h1>Oh Snap!</h1>
    <p style="margin-top:15px">We could not find that page.  Please check the URL and try again.</p>
    <form action="${submitUrl }" method="POST">
      <input type="hidden" name="url" id="url-field" />
      <p style="margin-top:15px">Could you do us a big favor?  Tell us the steps you took before receiving this message so our web development team can figure out how this could have happened.</p>
      <textarea style="width: 100%; padding: 3px;" name="stepsToReproduce" type="text" rows="4" placeholder="Description"><!-- --> </textarea>

      <p style="margin-top:15px">If you provide your e-mail address below we will let you know as soon as we've fixed to the problem.</p>
      <input style="width: 100%; line-height: 20px; padding: 3px;" name="userEmailAddress" type="text" placeholder="Email address"/>
      <p style="margin-top:15px">Thanks!</p>
      <div class="btns" style="text-align: center;">
        <div class="blue-button" style="display: inline-block;">
          <a href="javascript:;" class="login-submit" onclick="jQuery(this).parents('form').submit();">Submit</a>
        </div>
        <div class="gray-button" style="display: inline-block;">
          <a href="/web/guest/plans" class="">Go back to contests</a>
        </div>
      </div>
    </form>
  </div>
  <script>
    $('input#url-field').val(document.location.href);
  </script>
</jsp:root>