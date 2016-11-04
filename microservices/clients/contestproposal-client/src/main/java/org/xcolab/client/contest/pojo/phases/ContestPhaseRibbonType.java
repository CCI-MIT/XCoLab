package org.xcolab.client.contest.pojo.phases;

import org.xcolab.util.http.client.RestService;

public class ContestPhaseRibbonType extends AbstractContestPhaseRibbonType {

    public ContestPhaseRibbonType() {}

    public ContestPhaseRibbonType(ContestPhaseRibbonType value) {
        super(value);
    }

    public ContestPhaseRibbonType(Long id_, Integer ribbon, String hovertext, String description,
            Boolean copyonpromote, Integer sortorder) {
        super(id_, ribbon, hovertext, description, copyonpromote, sortorder);
    }

    public ContestPhaseRibbonType(AbstractContestPhaseRibbonType abstractContestPhaseRibbonType,
            RestService restService) {
        super(abstractContestPhaseRibbonType);
    }
}
