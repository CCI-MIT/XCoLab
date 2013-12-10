var proposalsPerPage = 5;
var proposalPickerPage = 0;
var sortOrder = 'ASC';
var sortColumn = 'Contest';

/* Load Proposals for a given tab (determined by var) */
function loadProposals(){
    spinner.spin(document.getElementById('proposalPickerTableContainer'));
    var URL = replaceURLPlaceholders(proposalPickerURL);
    $.getJSON(URL, { get_param: 'value' }, function(data) {
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

/* Update the small badges holding the counter for each tab*/
function updateTabRibbons(){
    var URL = replaceURLPlaceholders(proposalPickerCounterURL);

    $.getJSON(URL, { get_param: 'value' }, function(data) {
        $('#numberOfProposals').html(data.numberOfProposals);
        $('#numberOfSubscriptions').html(data.numberOfSubscriptions);
        $('#numberOfSupporting').html(data.numberOfSupporting);
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
    return URL;
}

/* Proposal picker tab selected (click) */
function proposalPickerTabSelected(element, type){
    proposalType = type;
    element.parent().parent().children().removeClass('c');
    element.parent().addClass('c'); proposalPickerPage = 0;
    loadProposals();
}

function addToProposalPickerTable(data, even){
    $('#proposalPickerTable > tbody').append('<tr class="' + (even ? ' ui-datatable-even' : ' ui-datatable-odd') + '"><td>' + data.contestName + '</td><td>' + data.proposalName + '</td><td>' + dateTimeFormatter.date(data.dateSubscribed) + '</td><td style="text-align: center;">' + '<a href="#">choose</a>' + '</td></tr>');
}


function addPaginationToProposalPickerTable(prev,next,totalPages){
    var output = "<span>";
    if (prev) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage - 1); loadProposals();" class="blue-arrow-left"></a>';
    output += ' Page ' + (proposalPickerPage + 1) + ' of ' + totalPages + ' ';
    if (next) output += '<a href="javascript:;" onClick="proposalPickerPage = (proposalPickerPage + 1); loadProposals();" class="blue-arrow-right"></a>';
    output += '</span>';
    $('#proposalPickerTable > tbody').append('<tr><td colspan="4" style="text-align:center !important; background-color: white;">' + output + '</td></tr>');
}

var pickerTimer;
var inputHandler =  function(){
    pickerTimer && clearTimeout(pickerTimer);
    pickerTimer = setTimeout(loadProposals, 400);
}

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