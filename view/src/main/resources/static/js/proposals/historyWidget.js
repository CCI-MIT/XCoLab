var itemsPerPage = 10;
var defaultPhaseId = -1;
var totalCount = 0;
function loadHistory(page) {
    // Load the page with items of the current contest phase
    if (page == -1 && getPhaseId() != defaultPhaseId) {
        $.getJSON('/api/phases/' + getPhaseId() + '/proposals/' + proposalId + '/versionsFirstIndex', { get_param: 'value' }, function(data) {
            var page = 0;
            page = Math.floor(data.index / itemsPerPage);
            totalCount= parseInt(data.index);
            load(page, defaultPhaseId);
        });
    } else if (page == -1) {
        load(page + 1, defaultPhaseId);
    } else {
        load(page, defaultPhaseId);
    }
}
function load(page, phaseId){
    var url = '/api/contests/' + currentProposal.contestId + '/phases/' + phaseId + '/proposals/' + proposalId + '/versions?start=' + (page * itemsPerPage) + '&end=' + ((1+page) * itemsPerPage - 1);
    console.log(url);
    $.getJSON(url, { get_param: 'value' }, function(data) {
        $('#versions').find('> div > div > table > tbody').empty();
        var even = true;
        $.each(data.versions, function(index, attr) {
            addVersionToTable(attr,even);
            even = !even;
        });
        addPagination(page > 0, totalCount > ((page+1) * itemsPerPage),page,Math.ceil(totalCount / itemsPerPage) - 1);
        visibilityCallback();
    });
}

function pad(n, width, z) {
    z = z || '0';
    n = n + '';
    return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
}
function addVersionToTable(data, even) {
    var dateString = dateTimeFormatter.formatMillis(data.date, dateTimeFormatter.TYPE_DATETIME, true);
    $('#versions').find('> div > div > table > tbody').append('<tr class="' + (even ? ' ui-datatable-even' : ' ui-datatable-odd') + (data.version == getVersion() ? ' ui-datatable-highlighted' : '') + '"><td style="width: 200px;"><a href="' + proposalUrl + '/version/' + data.version + '">' + dateString + '</a></td><td><em>by <a href="/members/profile/'+ data.author.userId + '">' + data.author.screenName + '</a></em></td><td><em>in phase <a href="' + phaseUrl + '">' + data.contestPhase.name + '</a></em></td></tr>');
}

function addPagination(prev,next,currentPage,totalPages){
    var output = "<span>";
    if (prev) output += '<a href="javascript:;" onClick="loadHistory(' + (currentPage - 1) + ');" class="blue-arrow-left"></a>';
    output += ' Page ' + (currentPage + 1) + ' of ' + (totalPages+1) + ' ';
    if (next) output += '<a href="javascript:;" onClick="loadHistory(' + (currentPage + 1) + ');" class="blue-arrow-right"></a>';
    output += '</span>';
    $('#versions').find('> div > div > table > tbody').append('<tr><td colspan="3" style="text-align:center !important; background-color: white;">' + output + '</td></tr>');
}

function triggerHistoryVisibility() {
    var $versions = $('#versions');
    if ($versions.hasClass('hidden')) {
        if (getVersion() != -1) {
            loadHistoryForVersion(getVersion());
        } else {
            loadHistory(-1);
        }

        $('#versionContainerTrigger').text(textHideHistory);
    } else  {
        $versions.slideUp( "slow", function() {
            $('#versions').addClass('hidden');
            $('#versionContainerTrigger').text(textShowHistory);
        });
    }
}


function visibilityCallback(){
    var $versions = $('#versions');
    if (!$versions.hasClass('hidden')) return;
    $versions.slideUp(0)         // fix to enable first slide out
        .slideDown(0)
        .slideUp(0)
        .removeClass('hidden')
        .slideDown( "slow");
}

function getPhaseId(){
    var url = window.document.URL.toString().split("/");
    for (var i = 0; i < url.length; i++) {
        if(url[i] == 'phaseId') return url[i+1];
    }
    return -1;
}

function getVersion(){
    // Try to get it when an old version was selected
    var version = $( "#versionId" ).html();
    if (version != 0) {
        return version;
    }

    var url = window.document.URL.toString().split("/");
    for (var i = 0; i < url.length; i++) {
        if(url[i] == 'version') return url[i+1];
    }
    return -1;
}

function loadHistoryForVersion(version) {
    $.getJSON('/api/proposals/' + proposalId + '/versions/' + version + '/index', {}, function(data) {
        var page = 0;
        page = Math.floor(data.index / itemsPerPage);
        totalCount= parseInt(data.index);
        load(page, defaultPhaseId);
    });
}
