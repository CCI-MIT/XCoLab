
function setupProposalMove(){

    var clearOptions = function() {
        if ($(this).val() != '-1') {
            $(this).remove();
        }
    };

    $("#targetContestPhaseContainer").hide();
    $("#proposalContainer").hide();
    $("#contestPhaseContainer").hide();

    $("#selectContest").change(function() {
        var id = $(this).val();

        $("#targetContestPhaseContainer").hide();
        $("#proposalContainer").hide();
        $("#contestPhaseContainer").hide();
        $("#proposalIdsToBeMoved").val("");

        $("#selectContestPhase option").each(clearOptions);
        $("#selectProposal option").each(clearOptions);

        if (id == -1) {
            return;
        }

        $("#contestPhaseContainer").show();

        for (var i in contestPhases[id]) {
            $("#selectContestPhase").append($("<option>", { value: contestPhases[id][i].pk, html: contestPhases[id][i].name }));
        }
    });
    $("#selectContestPhase").change(function() {
        var id = $(this).val();
        var contestId = $("#selectContest").val();

        $("#targetContestPhaseContainer").hide();
        $("#proposalContainer").hide();
        $("#proposalIdsToBeMoved").val("");

        $("#selectProposal").find("option").each(clearOptions);
        $("#selectTargetContestPhase").find("option").each(clearOptions);

        if (id == -1) {
            return;
        }

        $("#proposalContainer").show();
        $("#targetContestPhaseContainer").show();

        var currentSelectedPhaseFound = false;
        for (var i in contestPhases[contestId]) {
            if (currentSelectedPhaseFound) {
                $("#selectTargetContestPhase").append($("<option>", { value: contestPhases[contestId][i].pk, html: contestPhases[contestId][i].name }));
            }

            if (contestPhases[contestId][i].pk == id) {
                currentSelectedPhaseFound = true;
            }
        }

        for (var i in proposals[id]) {
            $("#selectProposal").append($("<option>", { value: i, html: proposals[id][i] }));
        }
    });
    $("#selectProposal").change(function() {
        var ids = $(this).val();
        if (ids) {
            $("#proposalIdsToBeMoved").val(ids.join(","));
        } else {
            $("#proposalIdsToBeMoved").val("");
        }

    });
}
