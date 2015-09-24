package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.PortletRequest;

interface ProposalTabCanAccessAlgorithm {
	boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request);


	public final static ProposalTabCanAccessAlgorithm alwaysTrue = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			return true;
		}
	};

	public final static ProposalTabCanAccessAlgorithm alwaysFalse = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			return false;
		}
	};

	public final static ProposalTabCanAccessAlgorithm adminOnlyAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			return permissions.getCanAdminProposal();
		}
	};

	public final static ProposalTabCanAccessAlgorithm advancingAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			try {
				if (!(permissions.getCanFellowActions() || permissions.getCanAdminAll()
						|| permissions.getCanContestManagerActions())) {
					return false;
				}

				ContestPhase contestPhase = context.getContestPhase(request);
				ContestPhasePromoteType phasePromoteType = ContestPhasePromoteType.getPromoteType(contestPhase.getContestPhaseAutopromote());
				if (phasePromoteType == ContestPhasePromoteType.PROMOTE_JUDGED && !contestPhase.isFellowScreeningActive()) {
					return true;
				}

				ProposalWrapper proposalWrapper = new ProposalWrapper(context.getProposal(request), context.getContestPhase(request));
				ProposalJudgeWrapper wrapper = new ProposalJudgeWrapper(proposalWrapper, context.getUser(request));
				return wrapper.shouldShowJudgingTab(context.getContestPhase(request).getContestPhasePK());

			} catch (PortalException | SystemException e) {
				e.printStackTrace();
			}

			return false;
		}
	};

	public final static ProposalTabCanAccessAlgorithm fellowReviewAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			try {
				ProposalWrapper proposalWrapper = new ProposalWrapper(context.getProposal(request), context.getContestPhase(request));
				if (proposalWrapper.getContest().getContestTier() < 1) {
					return false;
				}
			} catch (PortalException | SystemException e) {
				e.printStackTrace();
			}
			return permissions.getCanAdminAll() || permissions.getCanJudgeActions()
					|| permissions.getCanFellowActions() || permissions.getCanIAFActions();
		}
	};

	public final static ProposalTabCanAccessAlgorithm screeningAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			try {
				ContestPhase contestPhase = context.getContestPhase(request);
				if (!(permissions.getCanFellowActions() || permissions.getCanAdminAll() || permissions.getCanContestManagerActions()) ||
						!contestPhase.isFellowScreeningActive()) {
					return false;
				}

				ContestPhasePromoteType phasePromoteType = ContestPhasePromoteType.getPromoteType(contestPhase.getContestPhaseAutopromote());
				return permissions.getCanFellowActions() && phasePromoteType == ContestPhasePromoteType.PROMOTE_JUDGED ||
						permissions.getCanAdminAll();
			} catch (PortalException | SystemException e) {
				e.printStackTrace();
			}

			return false;
		}
	};

	public final static ProposalTabCanAccessAlgorithm canEditAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			try {
				return permissions.getCanEdit();
			} catch (SystemException e) {
				_log.error("can't check if user is allowed to edit proposal", e);
			} catch (PortalException e) {
				_log.error("can't check if user is allowed to edit proposal", e);
			}
			return false;
		}

		private final Log _log = LogFactoryUtil.getLog(ProposalTabCanAccessAlgorithm.class);
	};


	public final static ProposalTabCanAccessAlgorithm pointsViewAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			try {
				Contest contest = context.getContest(request);

				//first, check if the contest has points activated.
				if ((contest != null && contest.getDefaultParentPointType() > 0)) {
					//if yes, check if contest phase allows viewing
					Integer pointsAccessible = ContestLocalServiceUtil.getPointsAccessibleForActivePhaseOfContest(contest);
					return (pointsAccessible != null && pointsAccessible >= 1);
				}
			} catch (SystemException | PortalException e) {
				_log.error("can't check if user is allowed to access points", e);
			}
			return false;
		}

		private final Log _log = LogFactoryUtil.getLog(ProposalTabCanAccessAlgorithm.class);
	};


	public final static ProposalTabCanAccessAlgorithm pointsEditAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			try {
				Contest contest = context.getContest(request);

				//first, check if user is a team member and if the contest has points activated.
				if ((contest != null && contest.getDefaultParentPointType() > 0) && (permissions.getIsTeamMember() || permissions.getCanAdminProposal())) {
					//if yes, check if contest phase allows editing
					Integer pointsAccessible = ContestLocalServiceUtil.getPointsAccessibleForActivePhaseOfContest(contest);
					return permissions.getCanAdminAll() || (pointsAccessible != null && pointsAccessible >= 2);
				}
			} catch (SystemException | PortalException e) {
				_log.error("can't check if user is allowed to edit points", e);
			}
			return false;
		}

		private final Log _log = LogFactoryUtil.getLog(ProposalTabCanAccessAlgorithm.class);
	};

	public final static ProposalTabCanAccessAlgorithm impactViewAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			try {
				Contest contest = context.getContest(request);

				long ADAPTATION_ONTOLOGY_TERM_ID = 1300355L;
				long focusAreaId = contest.getFocusAreaId();
				boolean isAnyOntologyTermOfFocusAreaADescendantOfOntologyTerm =
						OntologyTermLocalServiceUtil.isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(focusAreaId, ADAPTATION_ONTOLOGY_TERM_ID);

				if ((contest != null && contest.getContestTier() != ContestTier.NONE.getTierType() &&
						contest.getContestTier() != ContestTier.REGION_SECTOR.getTierType() &&
						!isAnyOntologyTermOfFocusAreaADescendantOfOntologyTerm)) {
					return true;
				}
			} catch (Exception e) {
				_log.error("can't check if user is allowed to view impact tab", e);
			}
			return false;
		}

		private final Log _log = LogFactoryUtil.getLog(ProposalTabCanAccessAlgorithm.class);
	};

	public final static ProposalTabCanAccessAlgorithm impactEditAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			try {
				Contest contest = context.getContest(request);

				// Only let team members, IAF fellows or admins edit impact
				if ((contest != null && (
						contest.getContestTier() == ContestTier.BASIC.getTierType()) ||
						contest.getContestTier() == ContestTier.REGION_AGGREGATE.getTierType() ||
						contest.getContestTier() == ContestTier.GLOBAL.getTierType()) &&
						(permissions.getIsTeamMember() || permissions.getCanAdminProposal() || permissions.getCanIAFActions())) {
					return true;
				}
			} catch (SystemException | PortalException e) {
				_log.error("can't check if user is allowed to edit impact tab", e);
			}
			return false;
		}

		private final Log _log = LogFactoryUtil.getLog(ProposalTabCanAccessAlgorithm.class);
	};

}