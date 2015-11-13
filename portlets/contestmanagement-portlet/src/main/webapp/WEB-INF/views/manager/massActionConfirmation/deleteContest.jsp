<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="../../init.jspx"/>

    <portlet:actionURL var="confirmDeleteContestWithPhasesURL">
        <portlet:param name="action" value="confirmMassActionExecution"/>
    </portlet:actionURL>

    <div class="proposal-head">
        <div class="inner">
            <div class="headline" style="padding: 20px;">
                <div class="proposal-title"><h1>Mass Action Confirmation</h1>
                    <span class="floatLeft clearLeft">
                    <h3>
                    The following contest(s) have associated contest phases.<br/>
                    Please select the contest(s) where you wish to delete the contest phases too!
                    </h3>
                </span>
                </div>
            </div>
        </div>
    </div>

    <div id="content">

        <form:form action="${confirmDeleteContestWithPhasesURL }" modelAttribute="massActionConfirmationWrapper"
                   id="editForm" method="post">
            <div class="cmsDetailsBox">
                <div class="outerVerticalCenter">
                    <div class="blue-button innerVerticalCenter">
                        <a href="#" id="submitButton">DELETE contest(s) and phases</a>
                    </div>
                    <div class="gray-button innerVerticalCenter">
                        <a href="/web/guest/cms/-/contestmanagement/manager">DISCARD &amp; go back to overview</a>
                    </div>
                </div>
            </div>
            <div class="cmsDetailsBox">
                <table class="contestOverview">
                    <col span="1" class="extraSmallColumn"/>
                    <col span="1" class="wideColumn"/>
                    <col span="1" class="mediumColumn"/>
                    <col span="1" class="smallColumn"/>
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="selectAllCheckbox"/></th>
                        <th>Contest title</th>
                        <th>Contest phases</th>
                        <th># of Proposals</th>
                    </tr>
                    </thead>
                    <tbody id="contestOverviewBody">
                    <form:hidden path="massActionId"/>
                    <form:hidden path="itemCount"/>
                    <c:forEach var="contestWrapper" items="${massActionConfirmationWrapper.contestWrappers}"
                               varStatus="x">
                        <tr id="${contestWrapper.contestPK}">
                            <form:hidden path="contestIds[${x.index}]"
                                         data-form-name="contestPK"/>
                            <td>
                                <form:checkbox path="selectedContest[${x.index}]" cssClass="checkbox"/>
                            </td>
                            <td>
                                <collab:contestLink contestId="${contestWrapper.contestPK}"
                                                    text="${contestWrapper.contestShortName}"
                                        />
                            </td>
                            <td>
                                <c:forEach var="contestPhaseWrapper" items="${contestWrapper.phases}" varStatus="y">
                                        ${contestPhaseWrapper.name}<br/>
                                </c:forEach>
                            </td>
                            <td>${contestWrapper.proposalsCount} </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form:form>
    </div>

    <script type="text/javascript">
        <![CDATA[

        jQuery('document').ready(function () {
            bindSelectAllClick();
            bindFormSubmits();
        });

        function bindSelectAllClick() {
            var selectAllCheckboxElement = document.getElementById("selectAllCheckbox");
            selectAllCheckboxElement.addEventListener("change", function (ev) {
                var selectAllChecked = selectAllCheckboxElement.checked;
                var contestsTableBody = document.getElementById('contestOverviewBody');
                [].forEach.call(contestsTableBody.getElementsByClassName("checkbox"), function (element) {
                    var parentRow = getClosest(element, "tr");
                    if (selectAllChecked) {
                        element.checked = true;
                    } else {
                        element.checked = false;
                    }
                });
            });
        }

        function bindFormSubmits() {
            var editFormElement = document.getElementById('editForm');
            var submitButtonElement = document.getElementById("submitButton");
            submitButtonElement.addEventListener("click", function () {
                editFormElement.submit();
            })
        }

        ]]>
    </script>

</jsp:root>