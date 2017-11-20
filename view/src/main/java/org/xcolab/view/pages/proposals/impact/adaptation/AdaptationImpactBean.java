package org.xcolab.view.pages.proposals.impact.adaptation;

import java.util.Map;

public class AdaptationImpactBean {

    private Map<String, AdaptationValue> values;
    private Long proposalId;
    private Long authorId;

    public Map<String, AdaptationValue> getValues() {
        return values;
    }

    public void setValues(Map<String, AdaptationValue> values) {
        this.values = values;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
