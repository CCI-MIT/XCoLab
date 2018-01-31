package org.xcolab.view.pages.contestmanagement.beans;

import org.xcolab.view.pages.contestmanagement.utils.ProposalExportType;

import java.io.Serializable;
import java.util.List;

public class ProposalReportBean implements Serializable {

    private List<Long> contestIds;
    private ProposalExportType proposalExportType;

    public List<Long> getContestIds() {
        return contestIds;
    }

    public void setContestIds(List<Long> contestIds) {
        this.contestIds = contestIds;
    }

    public ProposalExportType getProposalExportType() {
        return proposalExportType;
    }

    public void setProposalExportType(ProposalExportType proposalExportType) {
        this.proposalExportType = proposalExportType;
    }
}
