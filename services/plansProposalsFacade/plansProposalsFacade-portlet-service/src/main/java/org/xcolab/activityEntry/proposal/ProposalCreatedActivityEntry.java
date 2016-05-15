package org.xcolab.activityEntry.proposal;

public class ProposalCreatedActivityEntry extends ProposalBaseActivityEntry{


    @Override
    public Integer getSecondaryType() {
        return 0;
    }

    @Override
    public String getBodyTemplate() {
        return "created <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "Created <proposal/>";
    }

}
