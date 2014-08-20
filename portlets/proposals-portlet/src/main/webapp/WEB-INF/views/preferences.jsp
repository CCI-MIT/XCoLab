<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

<div id="content">
    <div id="portlet-preferences">
        <h1>Proposals preferences</h1>
        <portlet:actionURL var="saveURL">
            <portlet:param name="action" value="save" />
        </portlet:actionURL>
        <form:form commandName="preferences" action="${saveURL}">
            <h2>Copy Proposals to Phases</h2>

            <p>
                <strong>${message}</strong>
            </p>
            <p>
                <label>
                    Contest:<br />
                    <form:select id="selectContest" path="moveFromContestId">
                        <form:option value="-1" label="Choose a contest" />
                        <c:forEach var="contest" items="${contests}">
                            <form:option value="${contest.contestPK}" label="${contest.contestShortName}" />
                        </c:forEach>
                    </form:select>
                </label>
            </p>
            <p id="contestPhaseContainer">
                <label>
                    Contest Phase:<br />
                    <select id="selectContestPhase">
                        <option value="-1">Choose a contest phase</option>
                    </select>
                </label>
            </p>
            <p id="proposalContainer">
                <label>
                    Proposals:<br />
                    <select id="selectProposal" multiple="multiple" style="height:120px">
                        <option/>
                    </select>
                </label>
            </p>
            <p id="targetContestPhaseContainer">
                    <label>
                        <strong>Target Phase:</strong><br />
                        <form:select id="selectTargetContestPhase" path="moveToContestPhaseId">
                            <form:option value="-1" label="Choose a contest phase" />
                        </form:select>
                    </label>
            </p>
            <p>
                <form:hidden id="proposalIdsToBeMoved" path="proposalIdsToBeMoved" />
            </p>

            <p>
            <input type="submit" value="Save" />
            </p>


            <h2>General</h2>
            <label for="termsOfService">Terms of service:</label>
            <form:textarea path="termsOfService" id="termsOfService" cssStyle="width: 100%; height: 300px;"/>
            <form:errors path="termsOfService"/>


            <input type="submit" value="Save" />
        </form:form>
    </div>
</div>
<script>
var contestPhases = {
    <c:forEach var="contestId" items="${contestPhases.keySet()}">
        ${contestId}: {
            <c:forEach var="contestPhase" items="${contestPhases.get(contestId)}">
                ${contestPhase.contestPhasePK}: "${contestPhaseType.get(contestPhase.contestPhaseType).name}",
            </c:forEach>
        },
    </c:forEach>
};

var proposals = {
    <c:forEach var="contestPhaseId" items="${proposals.keySet()}">
        ${contestPhaseId}: {
            <c:forEach var="proposal" items="${proposals.get(contestPhaseId)}">
                ${proposal.proposalId}: "${proposal.name.replaceAll("[^a-zA-Z 0-9]", "")}",
            </c:forEach>
        },
    </c:forEach>
};
</script>

</jsp:root>