<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          xmlns:modeling="urn:jsptagdir:/WEB-INF/tags/modeling"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

    <jsp:directive.include file="./init_proposal_tab.jspx"/>

    <jsp:directive.include file="./proposalDetails/header.jspx"/>

    <!-- Content -->
    <div id="content">
        <c:if test="${not empty isGlobalContest and isGlobalContest or not empty isRegionalContest and isRegionalContest}">

            <c:set var="modelId" value="${modelId}"/>
            <c:set var="scenarioId" value="${scenarioId }"/>

            <c:choose>
                <c:when test="${isProposalUsingCombinedScenario}">
                    <c:if test="${not empty consolidatedModelId}">
                        <c:set var="modelToLoadId" value="${consolidatedModelId}"/>
                    </c:if>

                    <c:if test="${not empty consolidatedScenarioId}">
                        <c:set var="scenarioToLoadId" value="${consolidatedScenarioId}"/>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <c:set var="modelToLoadId" value="${modelId}"/>
                    <c:set var="scenarioToLoadId" value="${scenarioId}"/>
                </c:otherwise>
            </c:choose>


            <c:choose>
                <c:when test="${edit and (modelId > 0 or scenarioId > 0)}">
                    <c:if test="${not empty consolidateOptions }">
                        <proposalsPortlet:modelSettingsPicker consolidateOptions="${consolidateOptions }"
                                                              contestPK="${contest.contestPK }"
                                                              modelId="${modelId}"
                                                              scenarioId="${scenarioId}"
                                                              consolidatedScenarioId="${consolidatedScenarioId }"
                                                              consolidatedModelId="${consolidatedModelId }"/>

                        <div id="proposalToModelMap" class="addpropbox" style="display: none;">
                            <c:if test="${not empty proposalToModelMap}">
                                <div class="alert alert-error">
                                    In order to use the values from your regional plans here, all the regional plans
                                    need to use the same modeling engine, and you must include a regional plan for all
                                    the regions.
                                </div>
                                <table>
                                    <col span="1" class="wideColumn"/>
                                    <col span="1" class="smallColumn"/>
                                    <col span="1" class="smallColumn"/>
                                    <col span="1" class="smallColumn"/>
                                    <thead>
                                    <tr>
                                        <th>Region</th>
                                        <th>Proposal</th>
                                        <th>Model</th>
                                        <!-- <th>Scenario</th> -->
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${proposalToModelMap}" var="proposalToModel">
                                        <tr>
                                            <td>${proposalToModel.value.region}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty proposalToModel.value.proposalUrl}">
                                                        <a href="${proposalToModel.value.proposalUrl}"
                                                           target="_blank">${proposalToModel.value.proposalName}</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                            ${proposalToModel.value.proposalName}
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${proposalToModel.value.simulation}</td>
                                            <!-- <td>${proposalToModel.value.scenario}</td> -->
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </div>

                    </c:if>

                    <c:if test="${not empty availableModels }">
                        <div id="modelPickerDiv">
                            <proposalsPortlet:modelPicker availableModels="${availableModels }"
                                                          contestPK="${contest.contestPK }" modelId="${modelToLoadId}"/>
                        </div>
                    </c:if>


                    <modeling:simulationEdit scenarioId="${scenarioToLoadId }"
                                             modelId="${modelToLoadId }"
                                             contestModelDefaultSetting="${contest.defaultModelSettings}"
                                             isRegionalContest="${isRegionalContest}"
                                             proposalRegion="${proposal.modelRegion}"
                                             modelRegions="${proposal.allModelRegions}"/>


                    <portlet:actionURL var="updateProposalScenarioURL">
                        <portlet:param name="action_forwardToPage" value="proposalDetails_IMPACT"/>
                        <portlet:param name="contestId" value="${contest.contestPK }"/>
                        <portlet:param name="planId" value="${proposal.proposalId }"/>
                        <portlet:param name="action" value="updateProposalScenario"/>
                    </portlet:actionURL>


                    <form action="${updateProposalScenarioURL }" id="updateProposalScenarioForm" method="post">
                        <input type="text" id="proposalScenarioId" name="scenarioId" class="hidden"/>
                        <input type="text" id="scenarioModelId" name="modelId" class="hidden"/>
                        <input type="text" id="isConsolidatedScenario" name="isConsolidatedScenario" class="hidden"/>
                        <input type="text" id="proposalModelRegion" name="region" class="hidden"
                               value="${proposal.modelRegion}"/>
                    </form>

                    <div class="admin-overlay-wrap">
                        <div class="admin-overlay">
                            <div class="inner">
                                <div class="admin-left">
                                    <p>
                                        <c:choose>
                                            <c:when test="${proposal.currentVersion le 1}">
                                                You are currently editing a new proposal
                                            </c:when>
                                            <c:otherwise>
                                                You are editing a proposal
                                            </c:otherwise>
                                        </c:choose>
                                        <br/>
                                        <c:if test="${not empty proposal.name }">"${proposal.name}"</c:if>
                                    </p>

                                    <div class="blue-button"><a href="#" id="saveChangesButton">SAVE and PUBLISH
                                        changes</a></div>
                                    <div class="gray-button">
                                        <c:choose>
                                            <c:when test="${proposal.currentVersion > 0 }">
                                                <collab:proposalLink proposal="${proposal}"
                                                                     linkId="discardChangesButton"
                                                                     text="DISCARD changes"/>
                                            </c:when>
                                            <c:otherwise>
                                                <!--  proposal creation, return to contest proposals page on discard -->
                                                <proposalsPortlet:contestLink contest="${contest }"
                                                                              linkId="discardChangesButton"
                                                                              text="DISCARD changes"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="admin-right">
                                    <p>&#160;</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:when test="${modelId > 0 and scenarioId > 0}">
                    <div id="modelContent">
                        <modeling:simulationView scenarioId="${scenarioId }" modelId="${modelId }"
                                                 contestModelDefaultSetting="${contest.defaultModelSettings}"/>
                    </div>
                </c:when>
                <c:when test="${modelId > 0 or scenarioId > 0}">
                    <div id="modelContent">
                        <div class="alert alert-warnig"><h3>
                            The proposal author(s) have not yet estimated the emission impacts of this proposal.
                        </h3></div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="modelContent">
                        <div class="alert alert-error">
                            No modeling engine is configured for this contest. Please contact the administrator.
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

        </c:if>

        <!-- TODO show fallback Graph -->
        <div id="impact-chart"> &#160;</div>
        <div class="clear"><!--  --></div>
        <br/>
        <br/>

        <c:if test="${not isGlobalContest and not isRegionalContest}">
            <div id="impact" class="cmsDetailsBox">
                <h2 class="model_name">Regional
                    <c:if test="${not empty isRegionalSectorContest and isRegionalSectorContest}">
                        Sector
                    </c:if>
                    Plan Impact, By Sector</h2>
                <table>
                    <thead>
                    <tr>
                        <th><!-- --></th>
                        <th class="blue-bg" style="text-align: center" colspan="${fn:length(impactIterations)}">
                            Proposal’s Greenhouse Gas Emission Reductions, Per Decade [GtCO2e]
                            <a href="javascript:;" class="helpTrigger"><img
                                    src="/climatecolab-theme/images/icon-addprop-question.png" width="15"
                                    height="15"/></a><br/>

                            <div class="addprophelp" style="color:white;">
                                This table shows a summary of the emission reductions for all sectors and regions
                                you submitted, in gigatons of carbon dioxide (CO2) equivalent (GtCO2e), for each
                                decade listed.
                                <a target='_blank' style="color: rgb(85, 26, 139)"
                                   href="/resources/-/wiki/Main/Assessing+the+impact+of+your+proposal+or+plan">
                                    Click here for more information.
                                </a>
                            </div>
                            <div class="clearfix"><!-- --></div>
                        </th>
                    </tr>
                    </thead>
                    <tr>
                        <td class="blue-bg" style="text-align: left">Sector
                            <a href="javascript:;" class="helpTrigger"><img
                                    src="/climatecolab-theme/images/icon-addprop-question.png" width="15"
                                    height="15"/></a><br/>

                            <div class="addprophelp" style="color:white;">
                                This table shows a summary of the emission reductions for all sectors and regions
                                you submitted, in gigatons of carbon dioxide (CO2) equivalent (GtCO2e), for each
                                decade listed.
                            </div>
                        </td>
                        <c:forEach var="impactIteration" items="${impactIterations}">
                            <th class="blue-bg" style="text-align: center;">${impactIteration.year}</th>
                        </c:forEach>
                    </tr>
                    <c:forEach var="seriesEntry" items="${impactSeries.seriesTypeToAggregatedSeriesMap}"
                               varStatus="index">
                        <c:set var="seriesValues"
                               value="${impactSeries.seriesTypeToAggregatedSeriesMap[seriesEntry.key]}"/>
                        <tr>
                            <td class="sector">${impactSeries.seriesTypeToDescriptionMap[seriesEntry.key]}</td>
                            <c:catch var="catchException">
                                <c:forEach var="impactIteration" items="${impactIterations}">
                                    <fmt:formatNumber var="value"
                                                      value="${seriesValues.yearToValueMap[impactIteration.year]}"
                                                      maxFractionDigits="2"/>
                                    <td class="impact-value">${value}</td>
                                </c:forEach>
                            </c:catch>
                        </tr>
                    </c:forEach>
                    <tr id="totalSectors">
                        <td class="sector total">Total of above sectors</td>
                        <c:forEach var="impactIteration" items="${impactIterations}">
                            <fmt:formatNumber var="value"
                                              value="${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}"
                                              maxFractionDigits="2"/>
                            <td class="impact-value" data-attr-year="${impactIteration.year}">${value}</td>
                        </c:forEach>
                    </tr>
                    <c:if test="${empty isRegionalSectorContest or not isRegionalSectorContest}">
                        <tr id="modelAdjustments">
                            <td class="sector">Adjustments to total, to correspond with model results</td>
                            <c:forEach var="impactIteration" items="${impactIterations}">
                                <fmt:formatNumber var="value"
                                                  value="${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}"
                                                  maxFractionDigits="2"/>
                                <td class="impact-value" data-attr-year="${impactIteration.year}">${value}</td>
                            </c:forEach>
                        </tr>
                        <tr id="modelTotal">
                            <td class="sector model">Total from model</td>
                            <c:forEach var="impactIteration" items="${impactIterations}">
                                <fmt:formatNumber var="value"
                                                  value="0"
                                                  maxFractionDigits="2"/>
                                <td class="impact-value" data-attr-year="${impactIteration.year}">${value}</td>
                            </c:forEach>
                        </tr>
                    </c:if>

                    <c:if test="${not empty showSubProposalListing and showSubProposalListing}">
                        <tr>
                            <c:choose>
                                <c:when test="${empty proposal.team}">
                                    <td colspan="5">${proposal.author.screenName}'s proposal portfolio sum is an
                                        aggregation of the following proposals:
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td colspan="5">${proposal.team}'s proposal portfolio sum is an aggregation of the
                                        following proposals:
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        <c:forEach var="impactSeries" items="${impactSerieses}" varStatus="index">
                            <tr class="impact-series-clickable" id="impact-row-${index.index}">
                                <td class="region">
                                        ${impactSeries.proposalWrapper.name}
                            <span
                                    id="${impactSeries.whereTerm.id}">(region: ${impactSeries.whereTerm.name}, </span>
                            <span
                                    id="${impactSeries.whatTerm.id}">sector: ${impactSeries.whatTerm.name})</span></td>
                                <c:forEach var="impactIteration" items="${impactIterations}">
                                    <td class="impact-value">${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}</td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
        </c:if>
    </div>
    <c:if test="${edit}">
        <script type="text/javascript">
            jQuery("#saveChangesButton").click(function () {
                jQuery("#proposalScenarioId").val(jQuery("#modelingScenarioId").val());
                jQuery("#scenarioModelId").val(jQuery("#modelingModelId").val());
                disableDirtyCheck();
                jQuery("#updateProposalScenarioForm").submit();
            });

            jQuery(function () {
                enableDirtyCheck();
            });
        </script>
    </c:if>

    <script type="text/javascript">
        var MODEL_DATA_ROW = "GHG emissions";

        /*
         - Emissions from energy
         - Emissions from land use
         - Other GHG emissions

         - Energy/Industry CO2 emissions
         - Land Use CO2 emissions
         */

        function mapValuesToYear(row) {
            var modelTotalValues = row.querySelectorAll('[data-attr-year]');
            var map = {};
            [].forEach.call(modelTotalValues, function (totalYearValue) {
                var year = totalYearValue.getAttribute("data-attr-year");
                var value = parseFloat(totalYearValue.innerHTML).toFixed(2);
                ;
                map[year] = value;
            });
            return map;
        }

        function calculateAverage(modelSeriesList) {
            var averageSeries = {variable: {values: []}};
            var seriesCount = modelSeriesList.length;
            modelSeriesList.forEach(function (series) {
                var modelSeriesValues = series.variable.values;
                var valueIndex = 0;
                modelSeriesValues.forEach(function (modelSeriesValue) {
                    if (typeof(averageSeries.variable.values[valueIndex]) === 'undefined') {
                        averageSeries.variable.values.push([0, 0]);
                        averageSeries.variable.values[valueIndex][0] = modelSeriesValue[0];
                    }
                    averageSeries.variable.values[valueIndex][1] += modelSeriesValue[1] / seriesCount;
                    valueIndex++;
                });
            });
            return averageSeries;
        }

        var scenarioFetchedCallback = function (event) {
            selectModelForFetchedScenario(event);
            var modelOutputs = event.scenario.outputs;
            var totalSectorsRow = document.getElementById("totalSectors");
            if (totalSectorsRow) {
                var totalSectorsValuesToYears = mapValuesToYear(totalSectorsRow);
                var modelSeriesValuesToYears = {};
                modelOutputs.forEach(function (modelOutput) {
                    console.log("modelOutput", modelOutput);
                    if (modelOutput.name.toLowerCase() === MODEL_DATA_ROW.toLowerCase()) {
                        var modelSeries;
                        if (event.scenario.modelName.toLowerCase().includes("emf")) {
                            modelSeries = calculateAverage(modelOutput.series);
                            console.log("avg", modelSeries);
                        } else {
                            modelSeries = modelOutput.series[0];
                        }
                        var modelSeriesValues = modelSeries.variable.values;
                        modelSeriesValues.forEach(function (modelSeriesValue) {
                            modelSeriesValuesToYears[modelSeriesValue[0]] = modelSeriesValue[1];
                        });
                    }
                });

                var modelTotalRow = document.getElementById("modelTotal");
                var modelTotalValues = modelTotalRow.querySelectorAll('[data-attr-year]');
                [].forEach.call(modelTotalValues, function (totalYearValue) {
                    var year = totalYearValue.getAttribute("data-attr-year");
                    var valueToYear = parseFloat(modelSeriesValuesToYears[year]);
                    totalYearValue.innerHTML = valueToYear.toFixed(2);
                });

                var modelAdjustmentsRow = document.getElementById("modelAdjustments");
                var modelAdjustmentValues = modelAdjustmentsRow.querySelectorAll('[data-attr-year]');
                [].forEach.call(modelAdjustmentValues, function (modelAdjustmentValue) {
                    var year = modelAdjustmentValue.getAttribute("data-attr-year");
                    var valueToYear = parseFloat(modelSeriesValuesToYears[year]) - parseFloat(totalSectorsValuesToYears[year]);
                    modelAdjustmentValue.innerHTML = valueToYear.toFixed(2);
                });
            }
        };

        function registerHelpEventHandler() {
            var $impactElement = jQuery("#impact");
            $impactElement.find(".helpTrigger").off("click");
            $impactElement.find(".helpTrigger").click(function () {
                var trigger = jQuery(this);
                trigger.parent().find(".addprophelp").slideToggle("fast");
            });
        }

        var scenarioFetchedCallbackRegistered = false;
        function registerScenarioFetchedCallback() {
            var $modelsOutputContainerElement = $("#modelsOutputContainer");
            if (jQuery($modelsOutputContainerElement.data('modeling')).length !== 0) {
                scenarioFetchedCallbackRegistered = true;
                jQuery($modelsOutputContainerElement.data('modeling')).on('scenarioFetched', scenarioFetchedCallback);
            }
        }

        registerScenarioFetchedCallback();

        $().ready(function () {
            if (!scenarioFetchedCallbackRegistered) {
                registerScenarioFetchedCallback();
            }
            registerHelpEventHandler();
        });
        /*
         var tableColors = [];

         $().ready(function() {
         tableColors = palette('tol-rainbow', 10); // TODO use dynamic value from server
         tableColors.forEach(function(part, index, theArray) {theArray[index] = "#"+part;});

         // Color table columns
         var hitFirstRow = false;
         $('div#impact > table tr').each(function(idx) {
         if (!hitFirstRow) {
         hitFirstRow = true;
         } else {
         $(this).children('td.sector').css('background-color', tableColors[idx - 1]);
         }
         });

         });

         google.load('visualization', '1', {packages: ['corechart', 'line']});

         // Set a callback to run when the Google Visualization API is loaded.
         google.setOnLoadCallback(drawChart);

         function drawChart() {
         var minValue = Number.MAX_VALUE;
         var maxValue = 0;

         // Chart labels
         var data = new google.visualization.DataTable();
         data.addColumn('string', 'Year');
         <c:forEach var="seriesEntry" items="${impactSeries.seriesTypeToDescriptionMap}" varStatus="index">
         data.addColumn('number', '${seriesEntry.value}');
         </c:forEach>
         data.addColumn('number', 'Total of sectors');

         // Build chart data
         <c:forEach var="impactIteration" items="${impactIterations}">
         var dataRow = ["${impactIteration.year}"];

         <c:forEach var="seriesEntry" items="${impactSeries.seriesTypeToAggregatedSeriesMap}" varStatus="index">
         <c:set var="seriesValues" value="${impactSeries.seriesTypeToAggregatedSeriesMap[seriesEntry.key]}" />
         <c:set var="value" value="${seriesValues.yearToValueMap[impactIteration.year]}" />

         dataRow.push(${value});
         minValue = Math.min(minValue, ${value});
         maxValue = Math.max(maxValue, ${value});
         </c:forEach>

         <c:set var="value" value="${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}" />

         dataRow.push(${value});
         data.addRow(dataRow);
         minValue = Math.min(minValue, ${value});
         maxValue = Math.max(maxValue, ${value});
         </c:forEach>

         var dataValueRange = maxValue - minValue;

         var options = {
         title: "Total emissions of ${contest.contestShortName} (Mt CO2)",
         titleTextStyle: {
         fontSize: "16"
         },
         hAxis: {
         title: 'Year',
         gridlines: {
         color: '#333',
         count: 5
         }
         },
         vAxis: {
         title: 'MtCO2',
         viewWindow: {
         max: (maxValue + Math.max((Math.round(dataValueRange / 100.0) + 1) * 5, 5)),
         min: (minValue - Math.max((Math.round(dataValueRange / 100.0) + 1) * 5, 5))
         }
         },
         legend: { position: 'top' },
         pointShape: 'circle',
         pointSize: 6,
         colors:tableColors,
         width:500,
         height:Math.min(150 * Math.max(Math.round(dataValueRange / 50.0), 2), 600.0)
         };

         var chart = new google.visualization.LineChart(document.getElementById('impact-chart'));
         chart.draw(data, options);
         }
         */

    </script>

</jsp:root>