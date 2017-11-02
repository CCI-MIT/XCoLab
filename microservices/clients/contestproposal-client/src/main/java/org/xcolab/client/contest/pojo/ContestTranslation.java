package org.xcolab.client.contest.pojo;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ContestTranslation extends AbstractContestTranslation {

    public ContestTranslation() {
    }

    public ContestTranslation(AbstractContestTranslation value) {
        super(value);
    }

    public ContestTranslation(AbstractContestTranslation contestTranslation,
            ServiceNamespace serviceNamespace) {
        super(contestTranslation);
    }
}
