package org.xcolab.client.contest.pojo;

import org.xcolab.util.http.client.RestService;

public class ContestType extends AbstractContestType {

    public ContestType() {
    }

    public ContestType(ContestType value) {
        super(value);
    }

    public ContestType(AbstractContestType abstractContestType, RestService restService) {
        super(abstractContestType);
    }

    public String getLabelName() {
        return String.format("%d - %s with %s",
                this.getId_(), this.getContestName(), this.getProposalNamePlural());
    }

    public String getCreationPrompt() {
        if (getOverrideCreationPrompt() != null) {
            return getOverrideCreationPrompt();
        }
        return "CREATE " + getProposalName();
    }
}
