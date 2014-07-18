package com.ext.portlet.service.impl;

import java.util.Collection;
import java.util.List;

import org.xcolab.points.DistributionStrategy;
import org.xcolab.points.PointsTarget;
import org.xcolab.points.ReceiverLimitationStrategy;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PointDistributionTarget;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.Points;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.base.PointsLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the points local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PointsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PointsLocalServiceBaseImpl
 * @see com.ext.portlet.service.PointsLocalServiceUtil
 */
public class PointsLocalServiceImpl extends PointsLocalServiceBaseImpl {
	
	/**
	 * Returns number of materialized points for given user.
	 * 
	 * @param userId
	 * @return
	 * @throws SystemException
	 */
	public int getUserMaterializedPoints(long userId) throws SystemException {
		int count = 0;
		for (Points points: pointsPersistence.findByUserId(userId)) {
			count += points.getMaterializedPoints();
		}
		return count;
	}
	
	/**
	 * Returns number of points for hypothetical user. 
	 * 
	 * @param userId
	 * @return
	 * @throws SystemException
	 */
	public long getUserHypotheticalPoints(long userId) throws SystemException {
		int count = 0;
		for (Points points: pointsPersistence.findByUserId(userId)) {
			count += points.getHypotheticalPoints();
		}
		return count;
	}
	
	/**
	 * Distribute points for given contest
	 * 
	 * @param contestPK
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	public void distributePoints(long contestPK, long pointsSourceId) throws PortalException, SystemException {
		Contest contest = contestLocalService.getContest(contestPK);
		
		// clean up old data
		pointsPersistence.removeByOriginatingContestPK(contestPK);
		
		PointType pointType = pointTypeLocalService.getPointType(contest.getDefaultParentPointType());
		
		if (contestLocalService.hasContestEnded(contest)) {
			// check if distribution targets have been defined manually
			Collection<PointDistributionTarget> distributionTargets = pointDistributionTargetPersistence.findByContestId(contestPK);
			if (distributionTargets.isEmpty()) {
				// distribution targets haven't been defined - distribute points to the winner
				Proposal proposal = contestLocalService.getWinnerProposal(contestPK);
				distributePointsToProposal(proposal, contest, 0, pointType, 0, contest.getPoints());
			}
			else {
				for (PointDistributionTarget pdt: distributionTargets) {
					Proposal proposal = proposalLocalService.getProposal(pdt.getProposalId());
					PointType pointTypeToUse = pointType;
					if (pdt.getPointTypeOverride() > 0) {
						pointTypeToUse = pointTypeLocalService.getPointType(pdt.getPointTypeOverride());
					}

					distributePointsToProposal(proposal, contest, 0, pointTypeToUse, 0, pdt.getNumberOfPoints());
				}
			}
			
		}
		else {
			// apply points to all of the proposals
			for (Proposal proposal: proposalLocalService.getProposalsInContest(contestPK)) {
				distributePointsToProposal(proposal, contest, 0, pointType, 0, contest.getPoints());
				
			}
		}
	}
	
	private void distributePointsToProposal(Proposal proposal, 
			Contest originatingContest, 
			long pointsSourceId, 
			PointType pointType, 
			double materializedPoints,
			double hypotheticalPoints) throws SystemException, PortalException {

		DistributionStrategy defaultDistributionStrategy = DistributionStrategy.valueOf(pointType.getDistributionStrategy());
		ReceiverLimitationStrategy receiverLimitationStrategy = ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy());
		
		List<PointsTarget> targets = receiverLimitationStrategy.getTargets(proposal, pointType, defaultDistributionStrategy);
		
		for (PointsTarget target: targets) {
			Points points = createPoints(counterLocalService.increment(Points.class.getName()));
			
			points.setOriginatingContestPK(originatingContest.getContestPK());
			points.setPointsSourceId(pointsSourceId);
			points.setProposalId(proposal.getProposalId());
			points.setHypotheticalPoints(hypotheticalPoints * target.getPercentage());
			points.setMaterializedPoints(materializedPoints * target.getPercentage());
			if (target.isUser()) {
				points.setUserId(target.getUserId());
			}
			
			addPoints(points);
			if (target.isProposal()) {
				Proposal subProposal = proposalLocalService.getProposal(target.getProposalId());
				Contest subProposalContest = proposalLocalService.getLatestProposalContest(target.getProposalId());
				PointType subProposalPointType = pointTypeLocalService.getPointType(subProposalContest.getDefaultParentPointType());
				
				distributePointsToProposal(subProposal, originatingContest, points.getId(), 
						subProposalPointType, points.getMaterializedPoints(), points.getHypotheticalPoints());
			}
			
		}
		
		
	}
}
