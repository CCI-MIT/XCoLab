package org.xcolab.activityEntry.proposal;

public class ProposalAttributeRemoveActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Integer getSecondaryType() {
        return 2;
    }

    @Override
    public String getBodyTemplate() {
        return "updated <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "<proposal/> updated";
    }
}
