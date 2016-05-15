package org.xcolab.activityEntry.proposal;

public class ProposalSupporterAddedActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Integer getSecondaryType() {
        return 8;
    }

    @Override
    public String getBodyTemplate() {
        return "is supporting <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "New <proposal/> supporter";
    }
}

