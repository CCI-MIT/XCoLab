package com.ext.portlet.proposals;

/**
 * Created by manu on 23/06/14.
 */
public enum ProposalJudgeType {
    JUDGE(1),
    FELLOW(2),
    PUBLIC(3);

    private final int id;

    ProposalJudgeType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}