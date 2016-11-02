package org.xcolab.portlets.proposals.wrappers;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;

import java.util.List;

import javax.portlet.PortletRequest;

interface ProposalTabCanAccessAlgorithm {
	boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request);


	ProposalTabCanAccessAlgorithm alwaysTrue = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			return true;
		}
	};

	ProposalTabCanAccessAlgorithm alwaysFalse = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			return false;
		}
	};

	ProposalTabCanAccessAlgorithm adminOnlyAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			return permissions.getCanAdminProposal();
		}
	};

	ProposalTabCanAccessAlgorithm advancingAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			if (!permissions.getCanSeeAdvancingTab()) {
				return false;
			}

			ContestPhase contestPhase = context.getContestPhase(request);
			ContestPhasePromoteType phasePromoteType = ContestPhasePromoteType.getPromoteType(contestPhase.getContestPhaseAutopromote());
			if (phasePromoteType == ContestPhasePromoteType.PROMOTE_JUDGED && !contestPhase.getFellowScreeningActive()) {
				return true;
			}

			Proposal proposalWrapper = new Proposal(context.getProposal(request), context.getContestPhase(request));
			ProposalJudgeWrapper wrapper = new ProposalJudgeWrapper(proposalWrapper, context.getMember(request));
			return wrapper.shouldShowJudgingTab();
		}
	};

	ProposalTabCanAccessAlgorithm evaluationResultsAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			return ConfigurationAttributeKey.PUBLISH_JUDGING_RESULTS.get();
		}
	};

	ProposalTabCanAccessAlgorithm fellowReviewAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			Proposal proposalWrapper = new Proposal(context.getProposal(request), context.getContestPhase(request));
			if (proposalWrapper.getContest().getContestTier() < 1) {
				return false;
			}
            //TODO: [COLAB-1161] temporarily hid fellow review tab -> decide what to do with it
			return false;
//			return permissions.getCanAdminAll() || permissions.getCanJudgeActions()
//					|| permissions.getCanFellowActions() || permissions.getCanIAFActions();
		}
	};

	ProposalTabCanAccessAlgorithm screeningAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			ContestPhase contestPhase = context.getContestPhase(request);
			if (!(permissions.getCanFellowActions() || permissions.getCanAdminAll() || permissions.getCanContestManagerActions()) ||
					!contestPhase.getFellowScreeningActive()) {
				return false;
			}

			ContestPhasePromoteType phasePromoteType = ContestPhasePromoteType.getPromoteType(contestPhase.getContestPhaseAutopromote());
			return permissions.getCanFellowActions() && phasePromoteType == ContestPhasePromoteType.PROMOTE_JUDGED ||
					permissions.getCanAdminAll();
		}
	};

	ProposalTabCanAccessAlgorithm canEditAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
				return permissions.getCanEdit();
		}
	};


	ProposalTabCanAccessAlgorithm pointsViewAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
				Contest contest = context.getContest(request);

				//first, check if the contest has points activated.
				if ((contest != null && contest.getDefaultParentPointType() > 0)) {
					//if yes, check if contest phase allows viewing
					Integer pointsAccessible = ContestClientUtil.getPointsAccessibleForActivePhaseOfContest(contest);
					return (pointsAccessible != null && pointsAccessible >= 1);
				}
			return false;
		}
    };

	ProposalTabCanAccessAlgorithm pointsEditAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			Contest contest = context.getContest(request);

			//first, check if user is a team member and if the contest has points activated.
			if ((contest != null && contest.getDefaultParentPointType() > 0) && (permissions.getIsTeamMember() || permissions.getCanAdminProposal())) {
				//if yes, check if contest phase allows editing
				Integer pointsAccessible = ContestClientUtil.getPointsAccessibleForActivePhaseOfContest(contest);
				return permissions.getCanAdminAll() || (pointsAccessible != null && pointsAccessible >= 2);
			}
			return false;
		}

	};

	ProposalTabCanAccessAlgorithm impactViewAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			if (ConfigurationAttributeKey.IMPACT_TAB_IS_ACTIVE.get()) {
                try {
                    final Contest contest = context.getContest(request);

                    if (contest.getContestTier() != ContestTier.NONE.getTierType()
                            && contest.getContestTier() != ContestTier.REGION_SECTOR.getTierType()) {
                        long focusAreaId = contest.getFocusAreaId();
                        if (!isDescendantOfExcludedOntologyTerm(focusAreaId)) {
                            return true;
                        }
                    }
                } catch (SystemException | PortalException e) {
                    _log.error("can't check if user is allowed to view impact tab", e);
                }
            }
			return false;
		}

		private boolean isDescendantOfExcludedOntologyTerm(long focusAreaId)
                throws SystemException, PortalException {

            final List<Long> excludedOntologyTermIds = ConfigurationAttributeKey
					.IMPACT_TAB_EXCLUDED_ONTOLOGY_TERM_IDS.get();
            for (Long excludedOntologyTermId : excludedOntologyTermIds) {
                if (OntologyClientUtil
                        .isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(
                                        focusAreaId, excludedOntologyTermId)) {
                    return true;
                }
            }
            return false;
        }
		private final Log _log = LogFactoryUtil.getLog(ProposalTabCanAccessAlgorithm.class);
	};

	ProposalTabCanAccessAlgorithm impactEditAccess = new ProposalTabCanAccessAlgorithm() {

		@Override
		public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
			Contest contest = context.getContest(request);

			final boolean memberCanAccess = permissions.getIsTeamMember()
					|| permissions.getCanAdminProposal()
					|| permissions.getCanIAFActions();
			if (!memberCanAccess || contest == null || contest.getContestTier() == null) {
				return false;
			}
			switch (ContestTier.getContestTierByTierType(contest.getContestTier())) {
				case BASIC:
				case REGION_AGGREGATE:
				case GLOBAL:
					return true;
				default:
					return false;
			}
		}
	};

}