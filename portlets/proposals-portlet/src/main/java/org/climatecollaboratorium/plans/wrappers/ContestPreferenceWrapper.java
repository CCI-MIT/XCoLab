package org.climatecollaboratorium.plans.wrappers;

import com.ext.portlet.model.Contest;

public class ContestPreferenceWrapper {

    private Contest contest;
    private int maxProposalsCount;
    
    public ContestPreferenceWrapper(Contest contest, int maxProposalsCount) {
        this.contest = contest;
        this.maxProposalsCount = maxProposalsCount;
    }
    
    public Contest getContest() {
        return contest;
    }
    
    public int getMaxProposalsCount() {
        return maxProposalsCount;
    }
    
    public void setMaxProposalsCount(int maxProposalsCount) {
        this.maxProposalsCount = maxProposalsCount;
    }
}
