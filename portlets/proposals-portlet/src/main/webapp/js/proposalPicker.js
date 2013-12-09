var proposalsPerPage = 5;
var proposalPickerPage = 0;
var sortOrder = 'ASC';
var sortColumn = 'Contest';

function loadProposals(){
    var URL = mySubscriptionsURL.replace('%40%40REPLACE-TYPE%40%40',proposalType).replace('%40%40REPLACE-FILTERKEY%40%40',filterKey);
    if ($('#prop-search').val() != 'Filter') URL = URL.replace('%40%40REPLACE-FILTERTEXT%40%40',$('#prop-search').val());
    else URL = URL.replace('%40%40REPLACE-FILTERTEXT%40%40','');
    URL = URL.replace('%40%40REPLACE-START%40%40',proposalPickerPage * proposalsPerPage);
    URL = URL.replace('%40%40REPLACE-END%40%40',(proposalPickerPage + 1) * proposalsPerPage);
    URL = URL.replace('%40%40REPLACE-SORTCOLOMN%40%40',sortColumn);
    URL = URL.replace('%40%40REPLACE-SORTORDER%40%40',sortOrder);

    $.getJSON(URL, { get_param: 'value' }, function(data) {
        $('#proposalPickerTable > tbody').empty();
        var even = true;
        $.each(data.proposals, function(index, attr) {
            addToProposalPickerTable(attr,even);
            even = ! even;
        });
        $('#numberOfProposals').html(data.numberOfProposals);
        $('#numberOfSubscriptions').html(data.numberOfSubscriptions);
        $('#numberOfSupporting').html(data.numberOfSupporting);
        highlighter();
        addPaginationToProposalPickerTable(proposalPickerPage > 0,data.totalCount > ((proposalPickerPage+1) * proposalsPerPage),Math.ceil(data.totalCount / proposalsPerPage));
    });
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
    pickerTimer = setTimeout(loadProposals, 800);
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