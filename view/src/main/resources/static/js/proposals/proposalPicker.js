var proposalsPerPage = 50;
var proposalPickerPage = 0;
var sortOrder = 'DESC';
var sortColumn = 'Supporters';
var contestSortColumn = 'Proposals';
var currentSectionId;
var pickMultipleProposals = false;
var contestPK = 0;
var contests = [];
var pickedProposals = [];

var proposalPickerProposalEntryTemplate = Handlebars.compile($("#proposalPickerProposalEntryTemplate").html());
var proposalPickerContestEntryTemplate = Handlebars.compile($("#proposalPickerContestEntryTemplate").html());

/* Load Proposals for a given tab (determined by var) */
function loadProposals(){
    spinner.spin(document.getElementById('proposalPickerTableContainer'));
    var URL = replaceURLPlaceholders(proposalPickerURL);
    $.getJSON(URL, { get_param: 'value' }, function(data) {
    	$("#proposalPicker_proposalsContainer").empty();
        $('#proposalPickerTable').find('> tbody').empty();
        var even = true;
        $.each(data.proposals, function(index, attr) {
            addToProposalPickerTable(attr,even);
            even = ! even;
        });
        highlighter();
        if (data.proposals.length > 0) addPaginationToProposalPickerTable(proposalPickerPage > 0,data.totalCount > ((proposalPickerPage+1) * proposalsPerPage),Math.ceil(data.totalCount / proposalsPerPage));
        spinner.stop();
        initTooltips();
    });
}

/* Load Proposals for a given tab (determined by var) */
function loadContests(){
    spinner.spin(document.getElementById('proposalPickerTableContainer'));
    var URL = replaceURLPlaceholders(proposalPickerContestsURL);
    
    $.getJSON(URL, { get_param: 'value' }, function(data) {
        contests = data.contests;
        $('#proposalPickerTable').find('> tbody').empty();
        var even = true;
        var container = $("#proposalPicker_contestsContainer");
        container.empty();
        $.each(data.contests, function(index, attr) {
        	container.append(proposalPickerContestEntryTemplate({contest: attr}));
            //addToProposalPickerTable(attr,even);
            //even = ! even;
        });
        highlighter();
        
        if (data.contests.length > 0) addPaginationToContestsPickerTable(proposalPickerPage > 0,data.totalCount > ((proposalPickerPage+1) * proposalsPerPage),Math.ceil(data.totalCount / proposalsPerPage));
        
        spinner.stop();
    });
}

/* Update the small badges holding the counter for each tab*/
function updateTabRibbons(){
    var URL = replaceURLPlaceholders(proposalPickerCounterURL);

    /*
    $.getJSON(URL, { get_param: 'value' }, function(data) {
        $('#numberOfProposals').html(data.numberOfProposals);
        $('#numberOfSubscriptions').html(data.numberOfSubscriptions);
        $('#numberOfSupporting').html(data.numberOfSupporting);
        $('#numberOfSubscriptionsSupporting').html(data.numberOfSubscriptionsSupporting);
    });
    */
}

/* Replace the URL placeholders with actual values */
function replaceURLPlaceholders(rawUrl){
    var URL = rawUrl.replace('%40%40REPLACE-TYPE%40%40',proposalType).replace('%40%40REPLACE-FILTERKEY%40%40',filterKey);
    var $propSearch = $('#prop-search');
    if ($propSearch.val() != 'Filter') {
        URL = URL.replace('%40%40REPLACE-FILTERTEXT%40%40',$propSearch.val());
    }
    else URL = URL.replace('%40%40REPLACE-FILTERTEXT%40%40','');
    URL = URL.replace('%40%40REPLACE-START%40%40',proposalPickerPage * proposalsPerPage);
    URL = URL.replace('%40%40REPLACE-END%40%40',(proposalPickerPage + 1) * proposalsPerPage);
    URL = URL.replace('%40%40REPLACE-SORTCOLOMN%40%40',sortColumn);
    URL = URL.replace('%40%40REPLACE-CONTESTSORTCOLOMN%40%40',contestSortColumn);
    URL = URL.replace('%40%40REPLACE-SORTORDER%40%40',sortOrder);
    URL = URL.replace('%40%40REPLACE-SECTIONID%40%40',currentSectionId);
    URL = URL.replace('%40%40REPLACE-CONTESTPK%40%40',contestPK);
    return URL;
}

/* Proposal picker tab selected (click) */
function proposalPickerTabSelected(element, type){
    proposalType = type;
    element.parent().parent().children().removeClass('active');
    element.parent().addClass('active'); proposalPickerPage = 0;
    // check if date should be displayed
    if (type == 'all'){
        $('#proposalPickerTable').find('> thead > tr > td:nth-child(3)').children().hide();
    } else {
        $('#proposalPickerTable').find('> thead > tr > td:nth-child(3) > a').show();
    }
    var proposalsPickerProposalsContainer = $("#proposalsPicker_proposalsContainer");
    if (type == 'contests') {

        proposalsPickerProposalsContainer.hide();
    	$("#proposalPickerTableContests").show();
        proposalsPickerProposalsContainer.find(".breadcrumb").show();
    	loadContests();
    }
    else {
    	contestPK = 0;
        proposalsPickerProposalsContainer.show();
    	$("#proposalPickerTableContests").hide();
        proposalsPickerProposalsContainer.find(".breadcrumb").hide();
    	loadProposals();
    }
}

function initializePickedProposals() {
    pickedProposals = [];
	$("input[name='sectionsContent[" + currentSectionId + "]']").siblings('ul').find('li').each(function() {
		var proposalLink = $(this).find('a').eq(0);
		var proposal = {};
		proposal.proposalName = proposalLink.text();
		
		var linkElementRegex =  /.*contestId\/(\d*)\/planId\/(\d*)/;
        var proposalLinkUrl = proposalLink.attr('href');
        var match = linkElementRegex.exec(proposalLinkUrl);
		if (match != null) {
			proposal.proposalId = match[2];
			proposal.contestId = match[1];
            proposal.linkUrl = proposalLinkUrl;
            console.log(proposalLinkUrl)
		}
		pickedProposals.push(proposal);
	});
    
    
}


/* Pick just a single proposal */
function pickProposal(sectionId, proposalNames, proposalNamesPlural, contestNames, contestNamesPlural) {
    currentSectionId = sectionId;
    initializePickedProposals();
    replaceContestTypeNameVariables(proposalNames, proposalNamesPlural, contestNames, contestNamesPlural);
    
    pickMultipleProposals = false;
    updateTabRibbons();
    var popupProposalPicker = $('#popup_proposalPicker');
    popupProposalPicker.show();
    proposalPickerTabSelected(popupProposalPicker.find('> div > .prop-tabs > ul > li:first > a'),'contests');
}

/* Pick a list of proposals */
function pickProposalList(sectionId, proposalNames, proposalNamePlural, contestNames, contestNamesPlural) {
    currentSectionId = sectionId;
    initializePickedProposals();
    replaceContestTypeNameVariables(proposalNames, proposalNamePlural, contestNames, contestNamesPlural);
    
    pickMultipleProposals = true;
    updateTabRibbons();
    var popupProposalPicker = $('#popup_proposalPicker');
    popupProposalPicker.show();
    proposalPickerTabSelected(popupProposalPicker.find('> div > .prop-tabs > ul > li:first > a'),'contests');
}

function replaceContestTypeNameVariables(proposalNames, proposalNamesPlural, contestNames, contestNamesPlural) {
    if (!proposalNames) {
        console.log("WARNING: contest types for proposal picker not defined");
    }
    $('.contestTypeProposalNames').each(function() {
        $(this).text(proposalNames);
    });
    $('.contestTypeProposalNamesPlural').each(function() {
        $(this).text(proposalNamesPlural);
    });
    $('.contestTypeContestNames').each(function() {
        $(this).text(contestNames);
    });
    $('.contestTypeContestNamesPlural').each(function() {
        $(this).text(contestNamesPlural);
    });
}

/* click "select" in the picker */
function selectProposal(proposalId, proposalName, contestName, proposalLinkUrl, linkClicked, contestId){
	
    var inputField = $("input[name='sectionsContent[" + currentSectionId + "]']");
    linkClicked.parent().parent().addClass('ui-datatable-highlight');
    //linkClicked.remove();
    if(pickMultipleProposals) {
    	var idx = -1;
    	var proposal = null;
		for (var i = 0; i < pickedProposals.length; i++) {
			if (pickedProposals[i].proposalId == proposalId) {
				proposal = pickedProposals[i];
				idx = i;
			}
		}
    	if (linkClicked.hasClass('selected')) {
    		linkClicked.removeClass('selected');
			pickedProposals.splice(idx, 1);
			return;
    	}
    	else {
    		linkClicked.addClass('selected');
    		if (idx == -1) {
    			pickedProposals.push({proposalId: proposalId, proposalName: proposalName, linkUrl: proposalLinkUrl, contestName: contestName, contestId: contestId});
    		}
    	}
    } else{
        if (inputField.val()) inputField.next().remove();
        inputField.val(proposalId);
        inputField.after('<span><a href="'+proposalLinkUrl+'">' + proposalName + '</a> (<a onclick="removePickedProposal(' + currentSectionId + ',' + proposalId + ', $(this), false);" href="javascript:;">remove</a>)</span>');
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
        element.parent().parent().remove();
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

    var tableRow = jQuery(proposalPickerProposalEntryTemplate({data: data}));
    if (highlight) tableRow.addClass('selected');
    $("#proposalPicker_proposalsContainer").append(tableRow);
    
    tableRow.click(function() {
    	var event = jQuery.Event("proposalPicker_proposalSelected", {
    		contestId: data.contestId,
    		proposalId: data.id, 
    		proposalName: data.proposalName,  
    		contestName: data.contestName,
            linkUrl: data.linkUrl,
    		sectionId: currentSectionId});
    	
    	jQuery(document).trigger(event);
    	selectProposal(data.id, data.proposalName,  data.contestName, data.linkUrl, $(this),data.contestId);
    });
    //$('#proposalPickerTable > tbody').append(tableRow);
}


function addPaginationToProposalPickerTable(prev,next,totalPages){
    var output = "<span>";
    if (prev) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage - 1); loadProposals();" class="blue-arrow-left"></a>';
    output += ' Page ' + (proposalPickerPage + 1) + ' of ' + totalPages + ' ';
    if (next) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage + 1); loadProposals();" class="blue-arrow-right"></a>';
    output += '</span>';
    $('#proposalPickerTable').find('> tbody').append('<tr><td colspan="4" style="text-align:center !important; background-color: white;">' + output + '</td></tr>');
}

function addPaginationToContestsPickerTable(prev,next,totalPages){
    var output = "<span>";
    if (prev) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage - 1); loadContests();" class="blue-arrow-left"></a>';
    output += ' Page ' + (proposalPickerPage + 1) + ' of ' + totalPages + ' ';
    if (next) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage + 1); loadContests();" class="blue-arrow-right"></a>';
    output += '</span>';
    $('#proposalPicker_contestsContainer').find('> tbody').append('<tr><td colspan="6" style="text-align:center !important; background-color: white;">' + output + '</td></tr>');
}

var pickerTimer;
var inputHandler =  function(){
    pickerTimer && clearTimeout(pickerTimer);
    if ($("#proposalPickerTableContests").is(":visible")) {
    	pickerTimer = setTimeout(loadContests, 400);	
    }
    else {
    	pickerTimer = setTimeout(loadProposals, 400);
    }
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

var highlighterClasses = ['higlightText1'];
var instance;
var highlighter = function() {
    instance && instance.revert();
    if (input.value) {
        var called = false;
        try {
            var regex = new RegExp(input.value, 'gi');
        } catch(e) {
            return;
        }
        try {
            instance = findAndReplaceDOMText(container, {
                find: regex,
                replace: function(portion, match) {
                	console.log('findandreplace.replace', 'portion', portion, 'match', match);
                    called = true;
                    var el = document.createElement('span');
                    $(el).addClass(highlighterClasses[match.index % highlighterClasses.length]);
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
if (input) {
	input.onkeyup = inputHandler;
	input.oninput = function() {
		input.onkeyup = null;
		inputHandler();
	};
}

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
	
});

$("#breadContestsList").click(function(event) {
	event.preventDefault();
	$("#proposalsPicker_proposalsContainer").hide();
	$("#proposalPickerTableContests").show();
});

$("#cancelPickedProposals").click(function(event) {
	$('#popup_proposalPicker').hide();   
	event.preventDefault();
	return false;
});


$("#savePickedProposals").click(function(event) {
    var inputField = $("input[name='sectionsContent[" + currentSectionId + "]']");
    var proposalListContainer = inputField.siblings('table');
    var proposalIds = [];
    proposalListContainer.empty();
	for (var i = 0; i < pickedProposals.length; i++) {
		var proposal = pickedProposals[i];
		proposalIds.push(proposal.proposalId);
		proposalListContainer.append('<tr><td><a href="' + proposal.linkUrl + '">' + proposal.proposalName + '</a></td><td class="removeProposalFromList"><a onclick="removePickedProposal(' + currentSectionId + ',' + proposal.proposalId + ', $(this), true);" href="javascript:;">remove</a></td></tr>');
	}
	inputField.val(proposalIds.join(","));
	$('#popup_proposalPicker').hide();   
	event.preventDefault();
	return false;
});

$("#popup_proposalPicker").find(".c-TitleBar a").click(function(event) {
	event.preventDefault();
	var link = $(this);
	var parentContainer = link.parents(".c-TitleBar");
	var column = link.attr('data-sort-column');
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
    if (parentContainer.attr('data-entity') == 'proposal') {
        sortColumn = column;
    	loadProposals();
    }
    else {
        contestSortColumn = column;
    	loadContests();
    }
	
	return false;
	
});
