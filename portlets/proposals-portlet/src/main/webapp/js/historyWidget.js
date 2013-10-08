var itemsPerPage = 10;
function loadHistory(page){
    $.getJSON('/plansProposalsFacade-portlet/api/jsonws/proposal/get-proposal-versions/proposalId/' + proposalId + '/start/' + (page * itemsPerPage) + '/end/' + ((1+page) * itemsPerPage), { get_param: 'value' }, function(data) {
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
    $('#versions > div > div > table > tbody').append('<tr class="' + (even ? ' ui-datatable-even' : ' ui-datatable-odd') + '"><td style="width: 400px;"><a href="/web/guest/plans/-/plans/contestId/' + contestId + '/planId/' + proposalId + '/version/' + data.version + '">' + getDateFormat(new Date(data.date)) + '</a></td><td><em>by <a href="/web/guest/member/-/member/userId/'+ data.author.userId + '">' + data.author.screenName + '</a></em></td></tr>');
}

function addPagination(prev,next,currentPage,totalPages){
    var output = "<span>";
    if (prev) output += '<a href="javascript:;" onClick="loadHistory(' + (currentPage - 1) + ');" class="blue-arrow-left"></a>';
    output += ' Page ' + (currentPage + 1) + ' of ' + (totalPages+1) + ' ';
    if (next) output += '<a href="javascript:;" onClick="loadHistory(' + (currentPage + 1) + ');" class="blue-arrow-right"></a>';
    output += '</span>';
    $('#versions > div > div > table > tbody').append('<tr><td colspan="2" style="text-align:center !important; background-color: white;">' + output + '</td></tr>');
}

function triggerHistoryVisibility(){
    if ($('#versions').hasClass('hidden')) {
        loadHistory(0);
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

function getDateFormat(date){
    var ret = date.getMonth() + '/' + date.getDay() + '/' + date.getFullYear() + ' ';
    var hours = date.getHours();
    var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
    var ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? hours : 12;
    ret += hours + ':' + minutes + ' ' + ampm;
    return ret;
}