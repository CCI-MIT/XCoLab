package org.xcolab.client.contest.pojo.phases;

import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.util.http.client.enums.ServiceNamespace;


public class ContestPhaseType extends AbstractContestPhaseType {

    public ContestPhaseType() {}

    public ContestPhaseType(ContestPhaseType value) {
        super(value);
    }

    public ContestPhaseType(AbstractContestPhaseType abstractContestPhaseType,
            ServiceNamespace serviceNamespace) {
        super(abstractContestPhaseType);
    }

    public ContestStatus getStatusEnum() {
        return ContestStatus.valueOf(getStatus());
    }
}
