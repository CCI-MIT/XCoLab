package org.xcolab.portlets.reporting.beans.activitiesbycontest;

import com.ext.portlet.model.Contest;

/**
* @author pdeboer
*         First created on 20/06/14 at 15:53
*/
public class ContestActivity {
    private final  Contest contest;
    private int commentCount = 0;
    private int authoredProposalCount = 0;
    private int supportedProposalCount = 0;
    private int votedProposalCount = 0;


    public ContestActivity(Contest contest) {
        this.contest = contest;
    }

    public Contest getContest() {
        return contest;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getAuthoredProposalCount() {
        return authoredProposalCount;
    }

    public void setAuthoredProposalCount(int authoredProposalCount) {
        this.authoredProposalCount = authoredProposalCount;
    }

    public int getSupportedProposalCount() {
        return supportedProposalCount;
    }

    public void setSupportedProposalCount(int supportedProposalCount) {
        this.supportedProposalCount = supportedProposalCount;
    }

    public int getVotedProposalCount() {
        return votedProposalCount;
    }

    public void setVotedProposalCount(int votedProposalCount) {
        this.votedProposalCount = votedProposalCount;
    }
}
