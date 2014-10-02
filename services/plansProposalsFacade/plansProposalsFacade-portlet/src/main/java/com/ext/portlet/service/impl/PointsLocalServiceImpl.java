package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.xcolab.points.DistributionStrategy;
import org.xcolab.points.PointsTarget;
import org.xcolab.points.ReceiverLimitationStrategy;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PointDistributionTarget;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.Points;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.base.PointsLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

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
	
	private final static Log _log = LogFactoryUtil.getLog(PointsLocalServiceImpl.class);
	
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
	 * Calculates the hypothetical points for all proposals for a given contest and
     * if the contest ended, materializes the points for winning proposals.
	 * 
	 * @param contestPK
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	public void distributePoints(long contestPK) throws PortalException, SystemException {
		Contest contest = contestLocalService.getContest(contestPK);
        PointType pointType = pointTypeLocalService.getPointType(contest.getDefaultParentPointType());
        List<Proposal> materializedProposals = new ArrayList<Proposal>();

		// clean up old data
		pointsPersistence.removeByOriginatingContestPK(contestPK);
		
		for (Points points: pointsPersistence.findByOriginatingContestPK(contestPK)) {
			deletePoints(points);
		}
		
        //only materialize if the contest has ended.
        if (contestLocalService.hasContestEnded(contest)) {
            materializePoints(contest, materializedProposals, pointType, false);
        }

        // calculate hypothetical points for all other proposals
        for (Proposal proposal: proposalLocalService.getProposalsInContest(contestPK)) {
            if (!materializedProposals.contains(proposal)) {
                distributePointsToProposal(proposal, proposal, contest, 0, pointType, 0, contest.getPoints(), false);
            }
        }
	}


    public List<Points> previewMaterializedPoints(long contestPK) throws PortalException, SystemException {
        Contest contest = contestLocalService.getContest(contestPK);
        PointType pointType = pointTypeLocalService.getPointType(contest.getDefaultParentPointType());
        List<Proposal> materializedProposals = new ArrayList<Proposal>();

        return materializePoints(contest, materializedProposals, pointType, true);
    }

    private List<Points> materializePoints(Contest contest, List<Proposal> materializedProposals, PointType pointType, boolean previewOnly) throws PortalException, SystemException {
        List<Points> materializedPoints = new ArrayList<Points>();
        // check if distribution targets have been defined manually
        Collection<PointDistributionTarget> distributionTargets = pointDistributionTargetPersistence.findByContestId(contest.getContestPK());
        if (distributionTargets.isEmpty()) {
            // distribution targets haven't been defined - distribute points to the winner
            Proposal proposal = contestLocalService.getWinnerProposal(contest.getContestPK());
            if (proposal != null) {
                materializedProposals.add(proposal);
                materializedPoints.addAll(distributePointsToProposal(proposal, proposal, contest, 0, pointType, contest.getPoints(), contest.getPoints(), previewOnly));
            }
        } else {
            for (PointDistributionTarget pdt: distributionTargets) {
                Proposal proposal = proposalLocalService.getProposal(pdt.getProposalId());
                if (!proposal.isVisible()) {
                    continue;
                }
                PointType pointTypeToUse = pointType;
                if (pdt.getPointTypeOverride() > 0) {
                    pointTypeToUse = pointTypeLocalService.getPointType(pdt.getPointTypeOverride());
                }

                materializedProposals.add(proposal);
                materializedPoints.addAll(distributePointsToProposal(proposal, proposal, contest, 0, pointTypeToUse, pdt.getNumberOfPoints(), contest.getPoints(), previewOnly));
            }
        }
        return materializedPoints;
    }

	private List<Points> distributePointsToProposal(Proposal proposal,
            Proposal originatingProposal,
            Contest originatingContest,
			long pointsSourceId,
			PointType pointType, 
			double materializedPoints,
			double hypotheticalPoints,
            boolean previewOnly) throws SystemException, PortalException {
        String logString = proposal.getProposalId() + ". originatingContest: " + originatingContest.getContestPK() +
                " pointsSourceId: " + pointsSourceId + " pointType: " + pointType + " materializedPoints: " + materializedPoints +
                " hypotheticalPoints: " + hypotheticalPoints;
        if (hypotheticalPoints < 1 && materializedPoints < 1) {
            _log.info("Points left to be distributed are less than 1 for proposal: " + logString);
            return new ArrayList<Points>();
        }
		
		_log.info("Distributing points to proposal: " + logString);
		
		DistributionStrategy distributionStrategy = DistributionStrategy.valueOf(pointType.getDistributionStrategy());
		ReceiverLimitationStrategy receiverLimitationStrategy = ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy());
		
		if (distributionStrategy == DistributionStrategy.DEFINED_BY_CHILDREN_PERCENTAGES) {
            List<Points> materializedPointsList = new ArrayList<Points>();
			for (PointType subPointType: pointTypePersistence.findByParentPointTypeId(pointType.getId())) {
                materializedPointsList.addAll(distributePointsToProposal(proposal, originatingProposal,
						originatingContest, 
						pointsSourceId, 
						subPointType, 
						materializedPoints * subPointType.getPercentageOfParent(), 
						hypotheticalPoints * subPointType.getPercentageOfParent(),
                        previewOnly));
			}
			return materializedPointsList;
		}
		
		List<PointsTarget> targets = receiverLimitationStrategy.getTargets(proposal, pointType, distributionStrategy);

        List<Points> materializedPointsList = new ArrayList<Points>();
		for (PointsTarget target: targets) {
			Points points = createPoints(counterLocalService.increment(Points.class.getName()));

			points.setOriginatingContestPK(originatingContest.getContestPK());
			points.setOriginatingProposalId(originatingProposal.getProposalId());
			points.setPointsSourceId(pointsSourceId);
			points.setProposalId(proposal.getProposalId());

            //round points up to the next integer
            double roundedHypotheticalPoints = Math.ceil(hypotheticalPoints * target.getPercentage());
            double roundedMaterializedPoints = Math.ceil(materializedPoints * target.getPercentage());
			points.setHypotheticalPoints(roundedHypotheticalPoints);
			points.setMaterializedPoints(roundedMaterializedPoints);

			if (target.isUser()) {
				_log.info("Adding points to a user: " + target.getUserId() + ", hypotheticalPoints: " + points.getHypotheticalPoints() + 
						", materializedPoints: " + points.getMaterializedPoints());
				points.setUserId(target.getUserId());
			}
			
			addPoints(points);
			if (target.isProposal()) {
				_log.info("Passing points to a proposal: " + target.getProposalId() + ", hypotheticalPoints: " + points.getHypotheticalPoints() + 
						", materializedPoints: " + points.getMaterializedPoints());
				Proposal subProposal = proposalLocalService.getProposal(target.getProposalId());
				Contest subProposalContest = proposalLocalService.getLatestProposalContest(target.getProposalId());
                //only distribute points if the subproposal's contest has a default point type defined,
                //and it's not the same as the current (= global) contest
                if (subProposalContest.getDefaultParentPointType() > 0 && subProposalContest.getContestPK() != originatingContest.getContestPK()) {
                    PointType subProposalPointType = pointTypeLocalService.getPointType(subProposalContest.getDefaultParentPointType());
                    materializedPointsList.addAll(distributePointsToProposal(subProposal, originatingProposal, originatingContest, points.getId(),
                            subProposalPointType, points.getMaterializedPoints(), points.getHypotheticalPoints(), previewOnly));
				}
			}
		}
        return materializedPointsList;
	}
}
