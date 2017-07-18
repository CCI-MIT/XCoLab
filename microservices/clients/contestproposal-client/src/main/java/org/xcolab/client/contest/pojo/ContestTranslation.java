package org.xcolab.client.contest.pojo;

import org.xcolab.util.http.client.RestService;

public class ContestTranslation extends AbstractContestTranslation {

    public ContestTranslation() {
    }

    public ContestTranslation(AbstractContestTranslation value) {
        super(value);
    }

    public ContestTranslation(AbstractContestTranslation contestTranslation,
            RestService contestService) {
        super(contestTranslation);
    }
}
