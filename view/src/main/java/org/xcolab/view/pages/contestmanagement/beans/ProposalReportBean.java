package org.xcolab.view.pages.contestmanagement.beans;

import java.io.Serializable;
import java.util.List;

public class ProposalReportBean implements Serializable {

    private List<Long> contestIds;

    public List<Long> getContestIds() {
        return contestIds;
    }

    public void setContestIds(List<Long> contestIds) {
        this.contestIds = contestIds;
    }
}
