package org.xcolab.activityEntry.proposal;

public class ProposalAttributeUpdateActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Integer getSecondaryType() {
        return 1;
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
