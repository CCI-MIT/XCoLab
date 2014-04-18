var proposalsPerPage = 5;
var proposalPickerPage = 0;
var sortOrder = 'ASC';
var sortColumn = 'Contest';
var currentSectionId;
var pickMultipleProposals = false;
var contestPK = 0;
var contests = [];

var proposalPickerProposalEntryTemplate = Handlebars.compile($("#proposalPickerProposalEntryTemplate").html());
var proposalPickerContestEntryTemplate = Handlebars.compile($("#proposalPickerContestEntryTemplate").html());

/* Load Proposals for a given tab (determined by var) */
function loadProposals(){
    spinner.spin(document.getElementById('proposalPickerTableContainer'));
    var URL = replaceURLPlaceholders(proposalPickerURL);
    $.getJSON(URL, { get_param: 'value' }, function(data) {
    	$("#proposalPicker_proposalsContainer").empty();
        $('#proposalPickerTable > tbody').empty();
        var even = true;
        $.each(data.proposals, function(index, attr) {
            addToProposalPickerTable(attr,even);
            even = ! even;
        });
        highlighter();
        if (data.proposals.length > 0) addPaginationToProposalPickerTable(proposalPickerPage > 0,data.totalCount > ((proposalPickerPage+1) * proposalsPerPage),Math.ceil(data.totalCount / proposalsPerPage));
        spinner.stop();
    });
}

/* Load Proposals for a given tab (determined by var) */
function loadContests(){
	console.log('loading contests');
    spinner.spin(document.getElementById('proposalPickerTableContainer'));
    var URL = replaceURLPlaceholders(proposalPickerContestsURL);
    
    $.getJSON(URL, { get_param: 'value' }, function(data) {
    	console.log('data received', URL);
        contests = data.contests;
        $('#proposalPickerTable > tbody').empty();
        var even = true;
        var container = $("#proposalPicker_contestsContainer");
        container.empty();
        $.each(data.contests, function(index, attr) {
        	container.append(proposalPickerContestEntryTemplate({contest: attr}));
            //addToProposalPickerTable(attr,even);
            //even = ! even;
        });
        console.log('highlighting');
        highlighter();
        
        if (data.contests.length > 0) addPaginationToContestsPickerTable(proposalPickerPage > 0,data.totalCount > ((proposalPickerPage+1) * proposalsPerPage),Math.ceil(data.totalCount / proposalsPerPage));
        
        console.log('ok, should remove spinner');
        spinner.stop();
    });
}

/* Update the small badges holding the counter for each tab*/
function updateTabRibbons(){
    var URL = replaceURLPlaceholders(proposalPickerCounterURL);

    $.getJSON(URL, { get_param: 'value' }, function(data) {
        $('#numberOfProposals').html(data.numberOfProposals);
        $('#numberOfSubscriptions').html(data.numberOfSubscriptions);
        $('#numberOfSupporting').html(data.numberOfSupporting);
        $('#numberOfSubscriptionsSupporting').html(data.numberOfSubscriptionsSupporting);
    });
}

/* Replace the URL placeholders with actual values */
function replaceURLPlaceholders(rawUrl){
    var URL = rawUrl.replace('%40%40REPLACE-TYPE%40%40',proposalType).replace('%40%40REPLACE-FILTERKEY%40%40',filterKey);
    if ($('#prop-search').val() != 'Filter') URL = URL.replace('%40%40REPLACE-FILTERTEXT%40%40',$('#prop-search').val());
    else URL = URL.replace('%40%40REPLACE-FILTERTEXT%40%40','');
    URL = URL.replace('%40%40REPLACE-START%40%40',proposalPickerPage * proposalsPerPage);
    URL = URL.replace('%40%40REPLACE-END%40%40',(proposalPickerPage + 1) * proposalsPerPage);
    URL = URL.replace('%40%40REPLACE-SORTCOLOMN%40%40',sortColumn);
    URL = URL.replace('%40%40REPLACE-SORTORDER%40%40',sortOrder);
    URL = URL.replace('%40%40REPLACE-SECTIONID%40%40',currentSectionId);
    URL = URL.replace('%40%40REPLACE-CONTESTPK%40%40',contestPK);
    return URL;
}

/* Proposal picker tab selected (click) */
function proposalPickerTabSelected(element, type){
	console.log('proposal picker tab selected', type);
    proposalType = type;
    element.parent().parent().children().removeClass('c');
    element.parent().addClass('c'); proposalPickerPage = 0;
    // check if date should be displayed
    if (type == 'all'){
        $('#proposalPickerTable > thead > tr > td:nth-child(3)').children().hide();
    } else {
        $('#proposalPickerTable > thead > tr > td:nth-child(3) > a').show();
    }
    if (type == 'contests') {
    	$("#proposalsPicker_proposalsContainer").hide();
    	$("#proposalPickerTableContests").show();
    	$("#proposalsPicker_proposalsContainer .breadcrumb").show();
    	loadContests();
    }
    else {
    	contestPK = 0;
    	$("#proposalsPicker_proposalsContainer").show();
    	$("#proposalPickerTableContests").hide();
    	$("#proposalsPicker_proposalsContainer .breadcrumb").hide();
    	loadProposals();
    }
}


/* Pick just a single proposal */
function pickProposal(sectionId){
    currentSectionId = sectionId;
    pickMultipleProposals = false;
    updateTabRibbons();
    $('#popup_proposalPicker').show();
    proposalPickerTabSelected($('#popup_proposalPicker > div > .prop-tabs > ul > li:first > a'),'contests');
}

/* Pick a list of proposals */
function pickProposalList(sectionId){
    currentSectionId = sectionId;
    pickMultipleProposals = true;
    updateTabRibbons();
    $('#popup_proposalPicker').show();
    proposalPickerTabSelected($('#popup_proposalPicker > div > .prop-tabs > ul > li:first > a'),'contests');
}

/* click "select" in the picker */
function selectProposal(proposalId, proposalName, contestName, linkClicked, contestId){
    var inputField = $("input[name='sectionsContent[" + currentSectionId + "]']");
    linkClicked.parent().parent().addClass('ui-datatable-highlight');
    linkClicked.remove();
    if(pickMultipleProposals) {
        if($.inArray(proposalId.toString(), inputField.val().split(','))<0) {
            inputField.val(inputField.val() + proposalId + ',');
            inputField.siblings('ul').append('<li><a href="/web/guest/plans/-/plans/contestId/' + contestId + '/planId/' + proposalId + '">' + proposalName + '</a> (<a onclick="removePickedProposal(' + currentSectionId + ',' + proposalId + ', $(this), true);" href="javascript:;">remove</a>)</li>');
        }
    } else{
        if (inputField.val()) inputField.next().remove();
        inputField.val(proposalId);
        inputField.after('<span><a href="/web/guest/plans/-/plans/contestId/' + contestId + '/planId/' + proposalId + '">' + proposalName + '</a> (<a onclick="removePickedProposal(' + currentSectionId + ',' + proposalId + ', $(this), false);" href="javascript:;">remove</a>)</span>');
        $('#popup_proposalPicker').hide();
    }
}

/* Remove picked proposal from list or single field */
function removePickedProposal(sectionId,proposalId,element, multipleProposals){
    var inputField = $("input[name='sectionsContent[" + sectionId + "]']");
    if (multipleProposals){
        var proposalIds = inputField.val().split(',');
        var output = "";
        proposalIds.splice( $.inArray(proposalId.toString(), proposalIds), 1 );
        for (var i = 0; i < (proposalIds.length - 1); i++){
            output +=  proposalIds[i] + ',';
        }
        inputField.val(output);
        element.parent().remove();
    } else{
        inputField.val('');
        element.parent().remove();
    }

}

function addToProposalPickerTable(data, even){
    // get ID's for highlighting
    var inputField = $("input[name='sectionsContent[" + currentSectionId + "]']");
    var highlight = false;
    if (inputField.length > 0) {
    	highlight = ($.inArray(data.id.toString(), inputField.val().split(','))>=0);
    	
    }
    var displayDate = (data.dateSubscribed != 0);
    var link = '<a href="javascript:;" class="selectProposalLink">choose</a>';
    var dateCol = '<td>' + dateTimeFormatter.date(data.dateSubscribed) + '</td>';
    
    
    var tableRow = $('<tr class="' + (even ? ' ui-datatable-even' : ' ui-datatable-odd') + (highlight ? ' ui-datatable-highlight' : '') + '"><td>' + data.contestName + '</td><td' + (displayDate ? '' : ' colspan="2"') + '>' + data.proposalName + '</td>' + (displayDate ? dateCol : '') + '<td style="text-align: center;">' + (highlight ? '' : link) + '</td></tr>');
    
    $("#proposalPicker_proposalsContainer").append(jQuery(proposalPickerProposalEntryTemplate({data: data})));
    
    tableRow.find(".selectProposalLink").click(function() {
    	var event = jQuery.Event("proposalPicker_proposalSelected", {
    		contestId: data.contestId,
    		proposalId: data.id, 
    		proposalName: data.proposalName,  
    		contestName: data.contestName,
    		sectionId: currentSectionId});
    	
    	jQuery(document).trigger(event);
    	selectProposal(data.id, data.proposalName,  data.contestName ,$(this),data.contestId);
    });
    //$('#proposalPickerTable > tbody').append(tableRow);
}


function addPaginationToProposalPickerTable(prev,next,totalPages){
    var output = "<span>";
    if (prev) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage - 1); loadProposals();" class="blue-arrow-left"></a>';
    output += ' Page ' + (proposalPickerPage + 1) + ' of ' + totalPages + ' ';
    if (next) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage + 1); loadProposals();" class="blue-arrow-right"></a>';
    output += '</span>';
    $('#proposalPickerTable > tbody').append('<tr><td colspan="4" style="text-align:center !important; background-color: white;">' + output + '</td></tr>');
}

function addPaginationToContestsPickerTable(prev,next,totalPages){
    var output = "<span>";
    if (prev) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage - 1); loadContests();" class="blue-arrow-left"></a>';
    output += ' Page ' + (proposalPickerPage + 1) + ' of ' + totalPages + ' ';
    if (next) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage + 1); loadContests();" class="blue-arrow-right"></a>';
    output += '</span>';
    $('#proposalPicker_contestsContainer > tbody').append('<tr><td colspan="6" style="text-align:center !important; background-color: white;">' + output + '</td></tr>');
}

var pickerTimer;
var inputHandler =  function(){
    pickerTimer && clearTimeout(pickerTimer);
    pickerTimer = setTimeout(loadProposals, 400);
};

// SORT ARROWS

var sortArrowDown = '<div class="sort-arrow"> &nbsp;<img src="/climatecolab-theme/images/sort-arrow-down.png"></div>';
var sortArrowUp = '<div class="sort-arrow"> &nbsp;<img src="/climatecolab-theme/images/sort-arrow-up.png"></div>';

function sortByColumn(link, column){
    // remove all sort arrows
    link.parent().parent().children().each(function() {
        $(this).children().remove('.sort-arrow');
    });

    if (sortOrder == 'ASC'){
        sortOrder = 'DESC';
        link.parent().append(sortArrowDown);
    } else {
        sortOrder = 'ASC';
        link.parent().append(sortArrowUp);
    }
    sortColumn = column;
    loadProposals();
}

// -------------------- PROPOSAL PICKER HIGHLIGHTING --------------------

var container = document.getElementById('popup_proposalPicker');
var input = document.getElementById('prop-search');

var colors = ['#fdff62'];
var instance;
var highlighter = function() {
    instance && instance.revert();
    if (input.value) {
        var called = false;
        try {
            var regex = RegExp(input.value, 'gi');
        } catch(e) {
            return;
        }
        try {
            instance = findAndReplaceDOMText(container, {
                find: regex,
                replace: function(portion, match) {
                    called = true;
                    var el = document.createElement('em');
                    el.style.backgroundColor = colors[match.index % colors.length];
                    el.innerHTML = portion.text;
                    return el;
                }
            });
        } catch(e) {
            throw e;
            return;
        }
        if (!called) {
        }
    }
};

// http://mathiasbynens.be/notes/oninput
input.onkeyup = inputHandler;
input.oninput = function() {
    input.onkeyup = null;
    inputHandler();
};

// -- SPINNING (LOADING) WHEEL --

var loadingWheelOpts = {
    lines: 13, // The number of lines to draw
    length: 20, // The length of each line
    width: 10, // The line thickness
    radius: 30, // The radius of the inner circle
    corners: 1, // Corner roundness (0..1)
    rotate: 0, // The rotation offset
    direction: 1, // 1: clockwise, -1: counterclockwise
    color: '#000', // #rgb or #rrggbb or array of colors
    speed: 1, // Rounds per second
    trail: 60, // Afterglow percentage
    shadow: false, // Whether to render a shadow
    hwaccel: false, // Whether to use hardware acceleration
    className: 'spinner', // The CSS class to assign to the spinner
    zIndex: 2e9, // The z-index (defaults to 2000000000)
    top: 'auto', // Top position relative to parent in px
    left: 'auto' // Left position relative to parent in px
};
var spinner = new Spinner(loadingWheelOpts);

$("#proposalPicker_contestsContainer").on("click", " tr", function(event) {
	event.preventDefault();
	contestPK = $(this).attr('data-contestPK');
	var contest = null;
	for (var i = 0; i < contests.length; i++) {
		if (contests[i].contestPK == contestPK) contest = contests[i]; 
	}
	
	$("#breadContestName").text(contest.contestShortName);
	$("#proposalPickerTableContests").hide();
	$("#proposalsPicker_proposalsContainer").show();
	
	loadProposals();
	console.log("clicked contest", $(this).attr('data-contestPK'));
	
});

$("#breadContestsList").click(function(event) {
	event.preventDefault();
	$("#proposalsPicker_proposalsContainer").hide();
	$("#proposalPickerTableContests").show();
});
