package org.xcolab.activityEntry.proposal;

public class ProposalSupporterRemovedActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Integer getSecondaryType() {
        return 9;
    }

    @Override
    public String getBodyTemplate() {
        return "retracted support for <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "New <proposal/> supporter";
    }
}
