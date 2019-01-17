package org.xcolab.service.contest.proposal.enums;


public class PointsTarget {
	private long proposalId;
	private long userId;
	private double points;
	private double percentage;
	public long getProposalId() {
		return proposalId;
	}
	public void setProposalId(long proposalId) {
		this.proposalId = proposalId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public boolean isProposal() {
		return proposalId > 0;
	}
	
	public boolean isUser() {
		return userId > 0;
	}

    public static PointsTarget forUser(long userId, double percentage) {
        PointsTarget target = new PointsTarget();
        target.userId = userId;
        target.percentage = percentage;
        return target;
    }

    public static PointsTarget forProposal(long proposalId, double percentage) {
        PointsTarget target = new PointsTarget();
        target.proposalId = proposalId;
        target.percentage = percentage;
        return target;
    }
	
}
