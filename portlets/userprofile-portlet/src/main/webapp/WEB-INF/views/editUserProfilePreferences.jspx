<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="./init.jspx" />

    <h1>Edit Preferences</h1>
    <br/>
    <br/>
    <portlet:actionURL var="updatePreferencesURL" />
    <style>
        .contactform-preferences input, .contactform-preferences textarea {
            width: 600px;
            font-size: 1.1em;
        }
        .contactform-preferences textarea {
            height: 200px;
        }
    </style>

    <form:form action="${updatePreferencesURL}" class="contactform-preferences" commandName="UserProfilePreferencesBean">
        <input type="hidden" name="action" value="savePreferences" />

        <h3>MyEmma API Settings (Newsletter Tool)</h3>
        <fieldset>
            <p>
                Account ID:<br />
                <form:input path="accountId" />
            </p>
            <p>
                Group ID:<br />
                <form:input path="groupId" />
            </p>
            <p>
                Public API Key:<br />
                <form:input path="publicApiKey" />
            </p>
            <p>
                Private API Key:<br />
                <form:input path="privateApiKey" />
            </p>
            <p>
                <input type="submit" value="Save" />
            </p>
        </fieldset>
    </form:form>
</jsp:root>