package org.xcolab.view.pages.contestmanagement.beans;

import java.io.Serializable;
import java.util.List;

public class VotingReportBean implements Serializable {

    private List<Long> votingPhaseIds;

    public List<Long> getVotingPhaseIds() {
        return votingPhaseIds;
    }

    public void setVotingPhaseIds(List<Long> votingPhaseIds) {
        this.votingPhaseIds = votingPhaseIds;
    }
}
