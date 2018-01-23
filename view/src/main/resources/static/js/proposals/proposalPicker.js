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
var pickerTab;

var PICKER_TAB_ALL_PROPOSALS = 'ALL_PROPOSALS';
var PICKER_TAB_ALL_CONTESTS = 'ALL_CONTESTS';
var PICKER_TAB_SUBSCRIPTIONS_AND_SUPPORTING = 'SUBSCRIBED_SUPPORTED_PROPOSALS';

var loadingWheelOpts = {
    lines: 15, // The number of lines to draw
    length: 60, // The length of each line
    width: 20, // The line thickness
    radius: 60 // The radius of the inner circle
};

var spinner;
var proposalPickerProposalEntryTemplate;
var proposalPickerContestEntryTemplate;

jQuery(function() {
    spinner = new Spinner(loadingWheelOpts);
    proposalPickerProposalEntryTemplate = Handlebars.compile($("#proposalPickerProposalEntryTemplate").html());
    proposalPickerContestEntryTemplate = Handlebars.compile($("#proposalPickerContestEntryTemplate").html());
});

/* Load Proposals for a given tab (determined by var) */
function loadProposals(){
    spinner.spin(document.getElementById('proposalPicker_viewArea'));
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
    });
}

/* Load Proposals for a given tab (determined by var) */
function loadContests(){
    spinner.spin(document.getElementById('proposalPicker_viewArea'));
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
    $('#numberOfContests').html('');
    $('#numberOfProposals').html('');
    $('#numberOfSubscriptionsSupporting').html('');

    var URL = replaceURLPlaceholders(proposalPickerCounterURL);
    $.get(URL, {tab: "ALL_CONTESTS"}, function(data) {
        $('#numberOfContests').html(data);
    });
    $.get(URL, {tab: "ALL_PROPOSALS"}, function(data) {
        $('#numberOfProposals').html(data);
    });
    $.get(URL, {tab: "SUBSCRIBED_SUPPORTED_PROPOSALS"}, function(data) {
        $('#numberOfSubscriptionsSupporting').html(data);
    });
}

/* Replace the URL placeholders with actual values */
function replaceURLPlaceholders(rawUrl){
    var URL = rawUrl.replace('@@REPLACE-TAB@@', pickerTab)
                    .replace('@@REPLACE-FILTERKEY@@', filterKey);
    var $propSearch = $('#prop-search');
    if ($propSearch.val() != 'Filter') {
        URL = URL.replace('@@REPLACE-FILTERTEXT@@', $propSearch.val());
    }
    else URL = URL.replace('@@REPLACE-FILTERTEXT@@',' ');
    URL = URL.replace('@@REPLACE-START@@', proposalPickerPage * proposalsPerPage);
    URL = URL.replace('@@REPLACE-END@@', (proposalPickerPage + 1) * proposalsPerPage);
    URL = URL.replace('@@REPLACE-SORTCOLOMN@@', sortColumn);
    URL = URL.replace('@@REPLACE-CONTESTSORTCOLOMN@@', contestSortColumn);
    URL = URL.replace('@@REPLACE-SORTORDER@@', sortOrder);
    URL = URL.replace('@@REPLACE-SECTIONID@@', currentSectionId);
    URL = URL.replace('@@REPLACE-CONTESTPK@@', contestPK);
    return URL;
}

/* Proposal picker tab selected (click) */
function proposalPickerTabSelected(tab){
    pickerTab = tab;
    $('#js-ProposalPicker__tabBar').children().removeClass('active');
    $('#js-ProposalPicker__tabBar__' + tab).addClass('active');
    proposalPickerPage = 0;
    // check if date should be displayed
    if (tab == PICKER_TAB_ALL_PROPOSALS){
        $('#proposalPickerTable').find('> thead > tr > td:nth-child(3)').children().hide();
    } else {
        $('#proposalPickerTable').find('> thead > tr > td:nth-child(3) > a').show();
    }
    var proposalsPickerProposalsContainer = $("#proposalsPicker_proposalsContainer");
    if (tab == PICKER_TAB_ALL_CONTESTS) {
        proposalsPickerProposalsContainer.hide();
    	$("#proposalPickerTableContests").show();
        proposalsPickerProposalsContainer.find(".breadcrumb").show();
    	loadContests();
    } else {
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
        proposal.proposalId = proposalLink.data('proposal-id');
        proposal.contestId = proposalLink.data('contest-id');
        proposal.linkUrl = proposalLink.attr('href');
		pickedProposals.push(proposal);
	});

}


/* Pick just a single proposal */
function pickProposal(sectionId, defaultTab, proposalNames, proposalNamesPlural, contestNames, contestNamesPlural) {
    currentSectionId = sectionId;
    initializePickedProposals();
    replaceContestTypeNameVariables(proposalNames, proposalNamesPlural, contestNames, contestNamesPlural);
    
    pickMultipleProposals = false;
    updateTabRibbons();
    var popupProposalPicker = $("#proposalPickerModal");
    popupProposalPicker.modal();
    proposalPickerTabSelected(defaultTab);
}

/* Pick a list of proposals */
function pickProposalList(sectionId, defaultTab, proposalNames, proposalNamePlural, contestNames, contestNamesPlural) {
    currentSectionId = sectionId;
    initializePickedProposals();
    replaceContestTypeNameVariables(proposalNames, proposalNamePlural, contestNames, contestNamesPlural);
    
    pickMultipleProposals = true;
    updateTabRibbons();
    var popupProposalPicker = $("#proposalPickerModal");
    popupProposalPicker.modal();
    proposalPickerTabSelected(defaultTab);
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
    if (pickMultipleProposals) {
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
    } else {
        if (inputField.val()) inputField.next().remove();
        inputField.val(proposalId);

        inputField.after('<table><tr><td class="proposalLink"><a href="'+proposalLinkUrl+'">' + proposalName + '</a></td><td>(<a onclick="removePickedProposal(' + currentSectionId + ',' + proposalId + ', $(this), false);" href="javascript:;">remove</a>)</td></tr></table>');
        var popupProposalPicker = $("#proposalPickerModal");
        popupProposalPicker.modal("hide");
    }
}

/* Remove picked proposal from list or single field */
function removePickedProposal(sectionId, proposalId, element, multipleProposals) {
    var inputField = $("input[name='sectionsContent[" + sectionId + "]']");
    if (multipleProposals){
        var proposalIds = inputField.val().split(',');
        proposalIds.splice( $.inArray(proposalId.toString(), proposalIds), 1 );
        inputField.val(proposalIds.join(','));
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
    	highlight = ($.inArray(data.id.toString(), inputField.val().split(',')) >= 0);
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

function sortByColumn(link, column){
    // remove all sort arrows
    link.parent().parent().children().each(function() {
        $(this).children().remove('.js-Table__sortArrow');
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
var container = document.getElementById('proposalPickerModal');
var input = document.getElementById('prop-search');

var highlighterClasses = ['higlightText1'];
var instance;
var highlighter = function() {
    instance && instance.revert();
    if (input != null && input.value) {
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


function savePickedProposals() {
    var inputField = $("input[name='sectionsContent[" + currentSectionId + "]']");
    var proposalListContainer = inputField.siblings('ul');
    var proposalIds = [];
    proposalListContainer.empty();
    for (var i = 0; i < pickedProposals.length; i++) {
        var proposal = pickedProposals[i];
        proposalIds.push(proposal.proposalId);
        proposalListContainer.append('<li><a href="' + proposal.linkUrl + '"'
                + ' data-proposal-id="' + proposal.proposalId + '"'
                + ' data-contest-id="' + proposal.contestId + '"'
                + '>' + proposal.proposalName + '</a> (<a onclick="removePickedProposal(' + currentSectionId + ',' + proposal.proposalId + ', $(this), true);" href="javascript:;">remove</a>)</li>');
    }
    inputField.val(proposalIds.join(","));
    $("#proposalPickerModal").modal("hide");
}

$("#proposalPickerModal").find(".c-Table__cell--title a").click(function(event) {
	event.preventDefault();
	var link = $(this);
	var parentContainer = link.parents(".c-Table");
	var column = link.attr('data-sort-column');
    link.parent().parent().children().each(function() {
        $(this).children().remove('.c-Table__sortArrow');
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
    } else {
        contestSortColumn = column;
    	loadContests();
    }
	
	return false;
	
});
