var itemsPerPage = 10;
var defaultPhaseId = -1;

function loadHistory(page) {
    // Load the page with items of the current contest phase
    if (page == -1 && getPhaseId() != defaultPhaseId) {
        $.getJSON('/plansProposalsFacade-portlet/api/jsonws/proposal/get-proposal-version-first-index/contestPhaseId/' + getPhaseId() + '/proposalId/' + proposalId, { get_param: 'value' }, function(data) {
            var page = 0;
            page = Math.floor(data.index / itemsPerPage);
            load(page, defaultPhaseId);
        });
    } else if (page == -1) {
        load(page + 1, defaultPhaseId);
    } else {
        load(page, defaultPhaseId);
    }
}
function load(page, phaseId){
    $.getJSON('/plansProposalsFacade-portlet/api/jsonws/proposal/get-proposal-versions/contestPhaseId/' + phaseId + '/proposalId/' + proposalId + '/start/' + (page * itemsPerPage) + '/end/' + ((1+page) * itemsPerPage - 1), { get_param: 'value' }, function(data) {
        $('#versions > div > div > table > tbody').empty();
        var even = true;
        $.each(data.versions, function(index, attr) {
            addVersionToTable(attr,even);
            even = ! even;
        });
        addPagination(page > 0,data.totalCount > ((page+1) * itemsPerPage),page,Math.ceil(data.totalCount / itemsPerPage) - 1);
        visibilityCallback();
    });
}


function addVersionToTable(data, even){
    $('#versions > div > div > table > tbody').append('<tr class="' + (even ? ' ui-datatable-even' : ' ui-datatable-odd') + (data.version == getVersion() ? ' ui-datatable-highlighted' : '') + '"><td style="width: 200px;"><a href="/web/guest/plans/-/plans/contestId/' + contestId + '/planId/' + proposalId + '/version/' + data.version + '">' + dateTimeFormatter.dateTime(data.date) + '</a></td><td><em>by <a href="/web/guest/member/-/member/userId/'+ data.author.userId + '">' + data.author.screenName + '</a></em></td><td><em>in phase <a href="/web/guest/plans/-/plans/contestId/' + contestId + '/phaseId/' + data.contestPhase.id + '">' + data.contestPhase.name + '</a></em></td></tr>');
}

function addPagination(prev,next,currentPage,totalPages){
    var output = "<span>";
    if (prev) output += '<a href="javascript:;" onClick="loadHistory(' + (currentPage - 1) + ');" class="blue-arrow-left"></a>';
    output += ' Page ' + (currentPage + 1) + ' of ' + (totalPages+1) + ' ';
    if (next) output += '<a href="javascript:;" onClick="loadHistory(' + (currentPage + 1) + ');" class="blue-arrow-right"></a>';
    output += '</span>';
    $('#versions > div > div > table > tbody').append('<tr><td colspan="3" style="text-align:center !important; background-color: white;">' + output + '</td></tr>');
}

function triggerHistoryVisibility(){
    if ($('#versions').hasClass('hidden')) {
        if (getVersion() != -1) {
            loadHistoryForVersion(getVersion());
        }
        loadHistory(-1);
        $('#versionContainerTrigger').text("Hide history");

    }
    else  {
        $( "#versions" ).slideUp( "slow", function() {
            $('#versions').addClass('hidden');
            $('#versionContainerTrigger').text("Show history");
        });
    }
}


function visibilityCallback(){
    if (!$('#versions').hasClass('hidden')) return;
    $( "#versions" ).slideUp(0);         // fix to enable first slide out
    $( "#versions" ).slideDown(0);
    $( "#versions" ).slideUp(0);
    $('#versions').removeClass('hidden');
    $( "#versions" ).slideDown( "slow");
}

function getPhaseId(){
    var url = window.document.URL.toString().split("/");
    for (var i = 0; i < url.length; i++) {
        if(url[i] == 'phaseId') return url[i+1];
    }
    return -1;
}

function getVersion(){
    var url = window.document.URL.toString().split("/");
    for (var i = 0; i < url.length; i++) {
        if(url[i] == 'version') return url[i+1];
    }
    return -1;
}

function loadHistoryForVersion(version) {
    $.getJSON('/plansProposalsFacade-portlet/api/jsonws/proposal/get-proposal-version-index/version/' + version + '/proposalId/' + proposalId, { get_param: 'value' }, function(data) {
        var page = 0;
        page = Math.floor(data.index / itemsPerPage);
        load(page, defaultPhaseId);
    });
}