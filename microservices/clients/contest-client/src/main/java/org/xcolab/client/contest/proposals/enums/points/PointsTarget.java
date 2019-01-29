package org.xcolab.client.contest.proposals.enums.points;

import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;

public class PointsTarget {
	private long proposalId;
	private long userId;
	private double points;
	private double percentage;
	private double percentageIn = 1d;

	public PointsTarget() {
	}

	public PointsTarget(PointsTarget pointsTarget) {
		this.proposalId = pointsTarget.getProposalId();
		this.userId = pointsTarget.getUserId();
		this.points = pointsTarget.getPoints();
		this.percentage = pointsTarget.getPercentage();

	}

	public PointsTarget(PointsTarget target, double percentageIn) {
		this(target);
		this.percentageIn = percentageIn;
	}

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

	public ProposalWrapper getProposal(){
		try {
			return StaticProposalContext.getProposalClient().getProposal(this.getProposalId());
		} catch (ProposalNotFoundException ignored) {
			return null;
		}
	}

	public double getPercentage() {
		return this.percentage * percentageIn;
	}
	
}
