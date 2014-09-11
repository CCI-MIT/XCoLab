package org.xcolab.portlets.reporting.beans;

import com.liferay.portal.model.User;

public class UserActivityReportBean implements Comparable<UserActivityReportBean> {
	private final User user;
	private int commentsCount; 
	private int proposalsAuthoredCount;
	private int proposalsContributedToCount;
	private int proposalsAuthoredOrContributedToCount;
	private int proposalFinalistsAuthoredCount;
	private int proposalFinalistsContributedToCount;
	private int proposalFinalistsAuthoredOrContributedToCount;
	private int proposalWinnersAuthoredCount;
	private int proposalWinnersContributedToCount;
    private int proposalWinnersAuthoredOrContributedToCount;
    private int proposalVotesCount;
	private int totalActivityCount;

    public void addProposalAuthored() {
        this.proposalsAuthoredCount++;
    }
    public void addProposalContributedTo() {
        this.proposalsContributedToCount++;
    }
    public void addProposalAuthoredOrContributedTo() {
        this.proposalsAuthoredOrContributedToCount++;
    }

    public void addProposalFinalistAuthored() {
        this.proposalFinalistsAuthoredCount++;
    }
    public void addProposalFinalistContributedTo() {
        this.proposalFinalistsContributedToCount++;
    }
    public void addProposalFinalistAuthoredOrContributedTo() {
        this.proposalFinalistsAuthoredOrContributedToCount++;
    }

    public void addProposalWinnerAuthored() {
        this.proposalWinnersAuthoredCount++;
    }
    public void addProposalWinnerContributedTo() {
        this.proposalWinnersContributedToCount++;
    }
    public void addProposalWinnerAuthoredOrContributedTo() {
        this.proposalWinnersAuthoredOrContributedToCount++;
    }

    public int getProposalWinnersAuthoredOrContributedToCount() {
        return proposalWinnersAuthoredOrContributedToCount;
    }

    public void setProposalWinnersAuthoredOrContributedToCount(int proposalWinnersAuthoredOrContributedToCount) {
        this.proposalWinnersAuthoredOrContributedToCount = proposalWinnersAuthoredOrContributedToCount;
    }

    public int getProposalsAuthoredCount() {
        return proposalsAuthoredCount;
    }

    public void setProposalsAuthoredCount(int proposalsAuthoredCount) {
        this.proposalsAuthoredCount = proposalsAuthoredCount;
    }

    public int getProposalsContributedToCount() {
        return proposalsContributedToCount;
    }

    public void setProposalsContributedToCount(int proposalsContributedToCount) {
        this.proposalsContributedToCount = proposalsContributedToCount;
    }

    public int getProposalsAuthoredOrContributedToCount() {
        return proposalsAuthoredOrContributedToCount;
    }

    public void setProposalsAuthoredOrContributedToCount(int proposalsAuthoredOrContributedToCount) {
        this.proposalsAuthoredOrContributedToCount = proposalsAuthoredOrContributedToCount;
    }

    public int getProposalFinalistsAuthoredCount() {
        return proposalFinalistsAuthoredCount;
    }

    public void setProposalFinalistsAuthoredCount(int proposalFinalistsAuthoredCount) {
        this.proposalFinalistsAuthoredCount = proposalFinalistsAuthoredCount;
    }

    public int getProposalFinalistsContributedToCount() {
        return proposalFinalistsContributedToCount;
    }

    public void setProposalFinalistsContributedToCount(int proposalFinalistsContributedToCount) {
        this.proposalFinalistsContributedToCount = proposalFinalistsContributedToCount;
    }

    public int getProposalFinalistsAuthoredOrContributedToCount() {
        return proposalFinalistsAuthoredOrContributedToCount;
    }

    public void setProposalFinalistsAuthoredOrContributedToCount(int proposalFinalistsAuthoredOrContributedToCount) {
        this.proposalFinalistsAuthoredOrContributedToCount = proposalFinalistsAuthoredOrContributedToCount;
    }

    public int getProposalWinnersAuthoredCount() {
        return proposalWinnersAuthoredCount;
    }

    public void setProposalWinnersAuthoredCount(int proposalWinnersAuthoredCount) {
        this.proposalWinnersAuthoredCount = proposalWinnersAuthoredCount;
    }

    public int getProposalWinnersContributedToCount() {
        return proposalWinnersContributedToCount;
    }

    public void setProposalWinnersContributedToCount(int proposalWinnersContributedToCount) {
        this.proposalWinnersContributedToCount = proposalWinnersContributedToCount;
    }

    public int getProposalVotesCount() {
        return proposalVotesCount;
    }

    public void setProposalVotesCount(int proposalVotesCount) {
        this.proposalVotesCount = proposalVotesCount;
    }

    public void addProposalVote() {
        proposalVotesCount++;
    }

    public UserActivityReportBean(User user) {
		this.user = user;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	
	public void addComment() {
		this.commentsCount ++;
	}

	public int getTotalActivityCount() {
		return totalActivityCount;
	}

	public void setTotalActivityCount(int totalActivityCount) {
		this.totalActivityCount = totalActivityCount;
	}
	
	public void addActivity() {
		this.totalActivityCount ++;
	}

	public User getUser() {
		return user;
	}

	@Override
	public int compareTo(UserActivityReportBean arg0) {
		return (int) (user.getUserId() - arg0.getUser().getUserId());
	}
	
	
}
