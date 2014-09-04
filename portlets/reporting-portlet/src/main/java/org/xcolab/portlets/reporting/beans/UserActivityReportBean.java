package org.xcolab.portlets.reporting.beans;

import com.liferay.portal.model.User;

public class UserActivityReportBean implements Comparable<UserActivityReportBean> {
	private final User user;
	private int commentsCount; 
	private int proposalsCount;
	private int proposalFinalistsCount;
	private int proposalWinnersCount;
    private int proposalVotesCount;
	private int totalActivityCount;

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

	public int getProposalsCount() {
		return proposalsCount;
	}

	public void setProposalsCount(int proposalsCount) {
		this.proposalsCount = proposalsCount;
	}
	
	public void addProposal() {
		this.proposalsCount ++;
	}

	public int getProposalFinalistsCount() {
		return proposalFinalistsCount;
	}

	public void setProposalFinalistsCount(int proposalFinalistsCount) {
		this.proposalFinalistsCount = proposalFinalistsCount;
	}
	
	public void addProposalFinalist() {
		this.proposalFinalistsCount ++;
	}

	public int getProposalWinnersCount() {
		return proposalWinnersCount;
	}

	public void setProposalWinnersCount(int proposalWinnersCount) {
		this.proposalWinnersCount = proposalWinnersCount;
	}
	
	public void addProposalWinner() {
		this.proposalWinnersCount ++;
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
