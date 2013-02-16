/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
/**
 * File responsible for generation of model details, actions and impacts tables. 
 * 
 * Base file: /html/portlet/ext/models/js/scenario-create.js
 * 
 * @author janusz.p
 * @version 1.0
 */
/**
 * Array of model inputs.
 */
var inputs = new Array();

/**
 * Input metadata information.
 */
var inputmetadata;

/**
 * Output metadata information.
 */
var outputmetadata;

/**
 * DOM representation of XML with model definition.
 */
var xmlGlobal;

/**
 * DOM representation of XML with scenario definition.
 */
var scenarioXML;

/**
 * Id of simulation 
 */
var simulationId;	

/**
 * Simulation name
 */
var simName;

/**
 * Query string for creating a scenario.
 */
var queryGlobal;

// load parameters and XML definitions
simulationId = parameterSimId;
xmlGlobal = getXMLDoc(simulationId);

if (scenarioId > 0) {
	scenarioXML = getXMLResponse("/simulation-servlet/rest/scenario/" + scenarioId);
}
inputmetadata = $(xmlGlobal).find("inputs>metadata");
outputmetadata = $(xmlGlobal).find("outputs>metadata");

var inputsMapping = new Array();

// display information requested by the caller
$(document).ready(function(){
	if (mode == 'model' || mode == 'all') {
		displayModelDetails();
	}
	if (mode == 'impacts' || mode == 'all') {
		displayImpacts();
	} 
	if (mode = 'actions' || mode == 'all') {
		displayActions();
	} 

});

/**
 * Creates form for specifying model actions.
 */
function makeSliderDisplay()
{
	$("div#sliders").empty();
	inputs = new Array();

	simulationId = parameterSimId;

	xmlGlobal = getXMLDoc(simulationId);
	inputmetadata = $(xmlGlobal).find("inputs>metadata");
	outputmetadata = $(xmlGlobal).find("outputs>metadata");

	// render create plan button
	var currentSim = $(xmlGlobal).find("simulation").get(0);

	getValsFromXML(xmlGlobal);

	addSliders();
	populateActionsFormWithValues();
}

/**
 * Returns XML response for request of given simulation/model.
 * @param simId id of simulation/model
 * @return XML response
 */
function getXMLDoc(simId)
{
	var url = "/simulation-servlet/rest/simulation/"+simId;
	var http;
	if (window.XMLHttpRequest)
	{
		// code for IE7+, Firefox, Chrome, Opera, Safari
		http=new XMLHttpRequest();
	}
	else if (window.ActiveXObject)
	{
		// code for IE6, IE5
		http=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else
	{
		alert("Your browser does not support XMLHTTP!");
	}
	http.open("GET",url,false);
	http.send("");
	return http.responseXML;
}

/**
 * Builds up Actions form (inputs and slider).
 *
 * @param xml XML definition of simulation
 */
function getValsFromXML(xml){
	var counter = 0;
	var doc = xml.getElementsByTagName("name");
	var descs = $(xml).find("inputs").find("description");
	descsDummy = descs;

	for (i=0;i<doc.length;i++)
	{
		if(doc[i].parentNode.parentNode.nodeName=='inputs'){
			inputs[counter] = doc[i].childNodes[0];
			var id = doc[i].parentNode.getElementsByTagName('id')[0].childNodes[0].nodeValue;
			inputsMapping[id] = counter;

			$("div#sliders").append("<div id='input"+counter+"' class='input-var'>");
			$("#input"+counter).append('<span class="expand-icon ui-icon ui-icon-triangle-1-e"></span>');
			$("#input"+counter).append("<span class='slider-label'><a href='#toggle'>"+inputs[counter].nodeValue+"</a></span>");
			$("#input"+counter).append("<input class='amount' type='text' id='amount"+counter+"'></input>");
			$("#input"+counter).append("<div class='slider' id='slider"+counter+"'></div>");

			$("#input"+counter).append("<div id='details"+counter+"' class='input-details'>"+"<strong>description: </strong>"+descs[counter].textContent+"</div>");
			$("#sliders").append("</div>");

			$("#sliders").append("<br />");
			counter++;
		}
	}
	$(".input-details").hide();
	$(".expand-icon").click(function() {
		var sliderDetails = $(this).next().next().next().next();
		sliderDetails.toggle("normal");
		$(this).toggleClass("ui-icon-triangle-1-e");
		$(this).toggleClass("ui-icon-triangle-1-s");
	});

	$("A[href='#toggle']").click(function() {
		var sliderDetails = $(this).parent().next().next().next();
		sliderDetails.toggle("normal");
		var icon = $(this).parent().prev();
		icon.toggleClass("ui-icon-triangle-1-e");
		icon.toggleClass("ui-icon-triangle-1-s");
	});


	addShowAndHideAll();
}

/**
 * Adds "show and hide all" button to form which ehtier shows or hides
 * all action inputs descriptions.
 */
function addShowAndHideAll()
{
	$("A[href='#show_all']").click(function() {
		$(".expand-icon").each(function() {
			var sliderDetails = $(this).next().next().next().next();
			sliderDetails.show("normal");
			$(this).removeClass("ui-icon-triangle-1-s");
			$(this).removeClass("ui-icon-triangle-1-e");
			$(this).addClass("ui-icon-triangle-1-s");
		});
	});

	$("A[href='#hide_all']").click(function() {
		$(".expand-icon").each(function() {
			var sliderDetails = $(this).next().next().next().next();
			sliderDetails.hide("normal");
			$(this).removeClass("ui-icon-triangle-1-s");
			$(this).removeClass("ui-icon-triangle-1-e");
			$(this).addClass("ui-icon-triangle-1-e");
		});
	});
}

/**
 * Adds sliders to the form.
 */
function addSliders(){
	var sliderString = '';
	var amountString = '';
	var counter = 0;

	for (i=0;i<inputs.length;i++)
	{
		sliderString = '#slider'+counter;
		amountString = "#amount"+counter;
		var inputType = $(inputmetadata[i]).attr("vartype");

		if (inputType=="RANGE" || inputType=="FUZZY_DISCRETE"){
			var l_min = getInputMin(i);
			var l_max = getInputMax(i);
			var l_def = getInputDefault(i);

			if (l_def < l_min || isNaN(l_def)) {
				l_def = l_min;
			} else if (l_def > l_max) {
				l_def = l_max;
			}

			$(sliderString).slider({
				range: "min",
				value:((l_def-l_min)/(l_max-l_min))*1000,
				min:0,
				max:1000,
				slide: labelUpdate(i,l_min,l_max)
			});
			var currDataType = getInputDatatype(i);
			$(amountString).val(pickValue($(sliderString).slider("value"),l_min,l_max));
		} else {
			var valArray = getInputCategories(getInputCategories(i));

			$(sliderString).addClass('drop-down');

			$(sliderString).append("<select id='input-drop"+counter+"'></select>");
			// add the select tag
			for (j=0;j<valArray.length;j++){
				$('#input-drop'+counter).append("<option value='"+valArray[j]+"'>"+valArray[j]+"</option>");
			}
		}
		counter += 1;
	}
}

/**
 * Removes first and last character from the string.
 *
 * @param s string for character removal
 * @return string without first and last characters
 */
function extract(s) {
	return s.slice(1,s.length-1);
}

/**
 * Returns minimum value for input with given id. 
 * @param idx id of an input.
 *
 * @return minimum value for given input
 */
function getInputMin(idx) {
	var result = $(inputmetadata[idx]).find("mins>data");
	if (result == null || result.text()==null) {return -100;}
	else return parseFloat(extract(result.text()));
}

/**
 * Returns maximum value for input with given id. 
 * @param idx id of an input.
 *
 * @return maximum value for given input
 */
function getInputMax(idx) {
	var result = $(inputmetadata[idx]).find("maxes>data");
	if (result == null || result.text() == null) {return -100;}
	else return parseFloat(extract(result.text()));
}

/**
 * Returns default value for input with given id. 
 * @param idx id of an input.
 *
 * @return default value for given input
 */
function getInputDefault(idx) {
	var result = $(inputmetadata[idx]).find("defaults>data");
	if (result == null|| result.text() == null) {return -100;}
	else return parseFloat(extract(result.text()));
}

/**
 * Returns input categories for input with given id. 
 * @param idx id of an input.
 * 
 * @return input categories for given input
 */
function getInputCategories(idx) {
	var result = $(inputmetadata[idx]).find("categories>data");
	if (result == null || result.text()==null) {return "none"}
	else return result.split(extract(result.text()));
}

/**
 * Returns datatype input with given id. 
 * @param idx id of an input.
 * 
 * @return datatype for given input
 */
function getInputDatatype(idx) {
	var result = $(inputmetadata[idx]).find("profile>data");
	if (result == null || result.text()==null) {return "java.lang.Integer"}
	else return extract(result.text());
}

/**
 * Returns label update event handler for input with given id.
 *  
 * @param idx id of an input
 * @param min minimum value
 * @param max maximum value
 * 
 * @return label update event handler
 */
function labelUpdate(idx,min,max) {
	var result = function(event,ui) {
		$("#amount"+idx).val(pickValue(ui.value,min,max));
	}
	return result;
}

/**
 * Returns value for a slider (adjusted to granularity of 1000).
 *  
 *  
 * @param value value to be scaled
 * @param min minimum value
 * @param max maximum value
 * 
 * @return minimum value for given input
 */
function pickValue(value,min,max) {
	var result = (value/1000.0 * (max-min))+min;

	if (result.toFixed) {
		result = result.toFixed(2);
	}
	return result;
}

/**
 * Adds new hidden input to the form.
 * 
 * @param name name of input
 * @param value input value
 */
function addElement(name,value){
	var newElement = document.createElement('input');
	newElement.setAttribute('type','hidden');
	newElement.setAttribute('value',value);
	newElement.setAttribute('name',name);
	newElement.setAttribute('class','parameter');
	document.getElementById('param-values').appendChild(newElement);
}


/**
 * Restores default values for Actions form.
 */
function restoreDefaults(){
	var amountString = "";
	var sliderString = "";
	for (var i=0;i<inputs.length;i++){
		amountString = "#amount"+i;
		sliderString = "#slider"+i;
		$(amountString).val(getInputDefault(i));
		$(sliderString).slider('value',getInputDefault(i));
	}
}

/**
 * Creates new scenario.
 */
function makeScenario(){
	var url = "/simulation-servlet/rest/runsim";
	var parameters = document.getElementsByClassName("parameter");

	var query = new Array();
	var pathToExcelModel = $(xmlGlobal).find("url").text();

	for(var i=0; i < inputs.length; i++) {
		var sliderString = "#slider"+i;

		var currInputName = $(inputmetadata[i]).find("internalname:first").text();
		if($(sliderString).hasClass('drop-down')) {
			var currInputValue = $("#slider"+i+" option:selected").attr("value");
			query.push(currInputName+'='+currInputValue);
		}
		else {
			query.push(currInputName+'='+$("#amount"+i).val());
		}
	}
	queryGlobal = query

	var simulationId = parameterSimId;

	query.push('simId=' + simulationId);

	query.push();
	query.push('path='+pathToExcelModel);
	var paramString = "";
	var nextParam;
	for (var i=0;i<query.length;i++)
	{
		nextParam = query[i];
		if (i != query.length-1)
		{
			nextParam += '&';
		}
		paramString += nextParam;
	}

	$.ajax({
		type: "POST",
		url: url,
		data: paramString,
		dataType: "xml",
		timeout: 60000,
		success: function(scenXml) {
			$("#infoBox").unblock();
			scenarioId = $(scenXml).find("scenario").attr("id");
			buildTable(scenXml);
			enableSave();
		},
		error: function() {
			$("#infoBox").unblock();
			$.growlUI('Error running model', 'The model may have timed out due to a system error. Please contact the system admin or try again.');
		}
	});
}

/**
 * Runs a simulation.
 */
function runSim()
{	
	$("#infoBox").css({'display':'block'});
	$("#infoBox").unblock();
	$("#infoBox").block({
		message: '<img src="/climatecolab-theme/images/ajax-loader.gif"/>',
		css: { 	color: '#000',
		padding: "15px",
		'-webkit-border-radius': '10px',
		'-moz-border-radius':    '10px',
		cursor: 'inherit'
	},
	overlayCSS:  {
		backgroundColor: '#FFF',
		opacity: 0.0,
		cursor: 'inherit'
	}
	});
	makeScenario();
}

/**
 * Builds Impacts table
 * @param xml XML definition of a scenario
 * @return
 */
function buildTable(xml) {
	$("div#infoBox").empty();
	$("div#infoBox").append("<table id='outputTable' border='1' class='pretty-table_' style='background-color:white'></table>");
	var variables = outputmetadata.map(function() {
		var mid = $(this).find("id").text();
		var variable = $(xml).find("variable:has(id:contains("+mid+"))");
		return variable;
	});
	var outputNames = outputmetadata.find("name").map(function () {
		return $(this).text();
	});
	$("table#outputTable").append("<tr class='outputNames'></tr>");
	for (var colidx in variables.get()) {
		$("table#outputTable tr.outputNames").append("<td>"+outputNames[colidx]+"</td>");

		var idxed = $(outputmetadata[colidx]).is("[varcontext=INDEXED]");
		var outputVals = getOutputVals(variables[colidx].find("data").text(),idxed);
		for (var rowidx in outputVals) {
			if (colidx == 0) {
				$("table#outputTable").append("<tr class='output"+rowidx+"'></tr>");
			}
			$("tr.output"+rowidx).append("<td>"+outputVals[rowidx]+"</td>");
		}
	}
}


/**
 * Returns list of output values for given field.
 * @param txt field values
 * @param indexed indexed flag
 * @return list of values
 */
function getOutputVals(txt,indexed)
{
	// strip outer brackets
	txt = txt.substring(2,txt.length-2);
	var elts = txt.split(/\]\[/);
	if (indexed) {
		for (var i in elts) {
			var commapos = elts[i].indexOf(',');
			if (commapos > -1) elts[i] = elts[i].substring(commapos+1);
		}
	}
	return elts;
}
/**
 * Saves a scenario.
 */
function saveScenario() {
	if (scenarioId < 0) return;
	$.ajax({
		type: "POST",
		url: "/simulation-servlet/rest/scenariostate/"+scenarioId,
		data: "state=PUBLIC",
		dataType: "xml",
		timeout: 60000,
		success: function() {
			
		},
		error: function() {
			$.growlUI('Error running model', 'The model may have timed out due to a system error. Please contact the system admin or try again.');
		}
	});
}

/**
 * Enables save button.
 */
function enableSave()
{
	$("button#savesim").removeAttr("disabled");
}

/**
 * Displays impacts table.
 */
function displayImpacts() {
	$("div#infoBox").empty();
	
	if (! (scenarioId > 0)) {
		if (canUpdatePlan) {
			$("div#infoBox").append("<span class=\"portlet-plans-plan-content-missing\">Simulation inputs haven't been defined, please visit Actions tab to set them.</span>");
		}
		return;
	}
	
	var values = $(scenarioXML).find("inputs variable");
	$("div#infoBox").append("<table id='outputTable' border='1' class='pretty-table_' style='background-color:white'></table>");
	var variables = outputmetadata.map(function() {
		var mid = $(this).find("id").text();
		var variable = $(scenarioXML).find("variable:has(id:contains("+mid+"))");
		return variable;
	});
	var outputNames = outputmetadata.find("name").map(function () {
		return $(this).text();
	});

	$("table#outputTable").append("<tr class='outputNames'></tr>");

	for (var colidx in variables.get()) {
		$("table#outputTable tr.outputNames").append("<td>"+outputNames[colidx]+"</td>");

		var idxed = $(outputmetadata[colidx]).is("[varcontext=INDEXED]");
		var outputVals = getOutputVals(variables[colidx].find("data").text(),idxed);
		for (var rowidx in outputVals) {
			if (colidx == 0) {
				$("table#outputTable").append("<tr class='output"+rowidx+"'></tr>");
			}
			$("tr.output"+rowidx).append("<td>"+outputVals[rowidx]+"</td>");
		}
	}	   

}
/**
 * Displays read-only table with scenario Actions.
 */
function displayActions() {
	if (! (scenarioId > 0)) {
		if (canUpdatePlan) {
			$("div#actions").append("<span class=\"portlet-plans-plan-content-missing\">Simulation inputs haven't been defined, please visit Actions tab to set them.</span>");
		}
		return;
	}
	var counter = 0;
	var doc = xmlGlobal.getElementsByTagName("name");
	var descs = $(xmlGlobal).find("inputs").find("description");
	descsDummy = descs;
	$("div#actions").append("<table class='actionsData' id='actionsData'><tr><th>Variable</th><th>Value</th></tr></table>");

	for (i=0 ;i < doc.length ; i++)
	{
		if(doc[i].parentNode.parentNode.nodeName=='inputs'){
			inputs[counter] = doc[i].childNodes[0];
			var id = doc[i].parentNode.getElementsByTagName('id')[0].childNodes[0].nodeValue;
			inputsMapping[id] = counter;

			$("table#actionsData").append("<tr id='action" + counter + "'><td class='action-desc' id='action" + 
					counter + "-desc'>" +inputs[counter].nodeValue+ "</td><td class='action-val' id='action" + 
					counter + "-val'></td></tr>");
			counter++;
		}
	}

	populateValues();
}

/**
 * Returns responseXML for given url.
 * @param url that is to be queried
 * @return response XML
 */
function getXMLResponse (url) {
	var http;
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		http=new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		// code for IE6, IE5
		http=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else {
		alert("Your browser does not support XMLHTTP!");
	}
	http.open("GET",url,false);
	http.send("");
	return http.responseXML;
}

/**
 * Populates Actions table with values.
 */
function populateValues() {
	var values = $(scenarioXML).find("inputs variable");

	for (var i = 0; i < values.length; i++) {
		var current = values.eq(i);
		var id = current.find("id").text();

		var value = current.find("data").text().replace("[[", "").replace("]]", ""); 

		actionValString = "#action"+inputsMapping[id] + "-val";

		$(actionValString).text(value);
	}
}

/**
 * Shows edit scenario form.
 */
function editScenario() {
	makeSliderDisplay();
	$("#actions-view").hide();
	$("#actions-edit").show();
}

/**
 * Saves the scenario and assigns it to the plan.
 * @return
 */
function saveAndAssignScenario() {
	if (scenarioId < 0) return;
	$.ajax({
		type: "POST",
		url: "/simulation-servlet/rest/scenariostate/"+scenarioId,
		data: "state=PUBLIC",
		dataType: "xml",
		timeout: 60000,
		success: function() {
    		var planValues = getValuesForPlan(scenarioXML);
			$("#savesim").attr("disabled", true);
			$("#" + portletNamespace + "scenarioId").val(scenarioId);
			
			for (var val in planValues) {
				$("#" + portletNamespace + "fm").append("<input type='hidden' name='" + portletNamespace + val + "' value='" + planValues[val] + "' />");
			}
			$("#" + portletNamespace + "fm").submit();
		},
		error: function() {
			$.growlUI('Error running model', 'The model may have timed out due to a system error. Please contact the system admin or try again.');
		}
	});
}

/**
 * Builds form that will be sent to simulation svc.
 */
function buildForm(){
	var amountString = "";
	clearForm();
  	for (var i=0;i<inputs.length;i++){
  		amountString = "#amount"+i;
  		dropString = "#input-drop"+i;
  		if (inputs[i].parentNode.parentNode.getAttribute("type")=="SCALAR"){
  			addElement(inputs[i].nodeValue,$(amountString).val());
  		} else {
  			addElement(inputs[i].nodeValue,$(dropString).val());
  		}
  	}
}

/**
 * Clears form that will be sent to simulation svc.
 */
function clearForm(){
	$('#param-values').empty();
}

/**
 * Populates edit scenario form with scenario values.
 */
function populateActionsFormWithValues() {
	var values = $(scenarioXML).find("inputs variable");
	// populate inputs/sliders with values
	if (scenarioId > 0) {
		for (var i = 0; i < values.length; i++) { 
			var current = values.eq(i);
			var id = current.find("id").text();
		
	 		var l_min = getInputMin(inputsMapping[id]); 
	 		var l_max = getInputMax(inputsMapping[id]);
	 		var value = current.find("data").text().replace("[[","").replace("]]", "");
	 	
	 		amountString = "#amount" + inputsMapping[id]; 
	 		sliderString = "#slider" + inputsMapping[id];
	 	
	 		$(amountString).val(value);
	 	
	 		var sliderValue = ((value-l_min)/(l_max-l_min))*1000;
	 	
	 		$(sliderString).slider('option', 'value', sliderValue); 
		}
	}
}

/**
 * Displays model details.
 */
function displayModelDetails() {
	simName = $(xmlGlobal).find("inputs + name").text();

	// render model name and description
	var simDescription = $(xmlGlobal).find("creation + description").text();

	$('#modelName').html(simName);
	$('#modelDescription').html(simDescription);
}