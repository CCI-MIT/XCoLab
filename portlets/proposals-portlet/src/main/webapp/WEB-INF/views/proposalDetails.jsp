<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="./init.jspx"/>

    <jsp:directive.include file="./proposalDetails/header.jspx"/>

    <div id="content">

        <style type="text/css">
                /*
                        inplace stylesheet is preferred over jquery CSS.
                */
            .ui-widget-content a {
                color: #30a3fb;
            }

            .historyTable * {
                border: 0px !important;
                text-align: left !important;
                font-size: 15px !important;
            }

            .historyTable .ui-datatable-even td {
                background-color: #f1f1e9;
            }

            .historyTable .ui-datatable-odd td {
                background-color: #fff;
            }

            .historyTable table {
                width: 617px;
                margin-top: 10px;
            }

            .ui-datatable table {
                width: auto;
            }

            .ui-widget-header {
                background: none;
            }
        </style>


        <div class="prop-left">

            <div class="edit-prop-wrap" style="width:616px; padding-top: 0px">
                <div class="inner">
                    <div style="display:inline-block; width: 616px;">
                        <c:if test="${not proposal.isLatestVersion and proposal.wasMovedToContest == null}">
                            <div class="edit-prop-butts" style="line-height: normal;">
                                <a href="/web/guest/plans/-/plans/contestId/${contest.contestPK}/planId/${proposal.proposalId}">Go
                                    to current</a>
                            </div>
                        </c:if>

                        <div class="edit-prop-butts" style="line-height: normal;">
                            <a href="javascript:;" id="versionContainerTrigger" onclick="triggerHistoryVisibility();">Show
                                history</a>
                            <span id="versionId" style="display: none;">${proposal.selectedVersion.version}</span>
                        </div>


                        <c:if test="${proposal.wasMovedToContest != null}">
                            <div>This proposal was moved to: <proposalsPortlet:contestLink contestId="${proposal.wasMovedToContest.contestPK}"
                                                                                 text="${proposal.wasMovedToContest.contestShortName}"/></div>
                        </c:if>


                        <c:if test="${not proposal.isLatestVersion and proposal.wasMovedToContest == null}">
                            <div class="lastedited">
                                Currently viewing version from
                                <script>document.write(moment.unix(${proposal.selectedVersion.createDate.time} / 1000).format("MM/DD/YYYY hh:mm A"));
                                    var version = ${proposal.selectedVersion.version};
                                    triggerHistoryVisibility();
                                </script>
                                by
                                <proposalsPortlet:userLinkSimple userId="${proposal.selectedVersion.authorId}"
                                                                 text="${proposal.userForSelectedVersion.screenName}"/>
                            </div>
                        </c:if>

                    </div>

                    <div id="versions" class="versionsContainer hidden">
                        <div class="versions">
                            <div class="historyTable">
                                <table>
                                    <tbody class="ui-datatable-data ui-widget-content">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${not empty proposal.pitch }">
                <h2>Pitch</h2>

                <p class="intro">${proposal.pitch}</p>

                <div class="div1"><!--  --></div>
            </c:if>

            <h2>Description</h2>
            <c:if test="${empty proposal.sections or fn:length(proposal.description) gt 0}">
                        ${proposal.description }
            </c:if>
            <c:if test="${not empty proposal.sections }">
            	<c:forEach var="section" items="${proposal.sections }" varStatus="status">
                	<h3>${section.title }</h3>
                    
					<proposalsPortlet:proposalSectionContent section="${section }" />
                    	
					<c:if test="${not status.last }">
                    	<div class="div2"><!--  --></div>
                    </c:if>
                </c:forEach>
            </c:if>

            <jsp:directive.include file="./proposalDetails/proposalJudging.jspx"/>
        </div>
        <jsp:directive.include file="./proposalDetails/proposalSummary.jspx"/>
    </div>
	<div id="copyProposalContainer" style="display: none;">
    	<div class="popup-wrap p1" id="copyProposalPopup">
			<div class="popup">
				<h4>Please choose contest to which you'd like to copy this proposal</h4>
				<div class="lrContentPlaceholder lfr-column " id="copyProposalPopupContent">
					<div id="copyProposalContests"><!--  --></div>
					<center>
						<div class="blue-button"><a href="javascript:;" onclick="$('#copyProposalContainer').hide();">Cancel</a></div>
					</center>
				</div>
			</div>
		</div>
	</div>
    <div id="messaging-overlay" class="popup-wrap small" style="display: none;">
        <div class="popup" style="overflow: hidden;">
            <jsp:directive.include file="./proposalDetails/proposalShare.jspx" />
        </div>
    </div>
	<script>
		var currentProposal = {
				proposalId: ${proposal.proposalId},
				version: ${proposal.version},
                contestId: ${proposal.contest.getContestPK()}
		}
	</script>

</jsp:root>
