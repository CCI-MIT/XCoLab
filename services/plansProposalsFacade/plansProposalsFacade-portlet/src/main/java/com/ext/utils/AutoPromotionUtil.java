package com.ext.utils;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.NoSuchContestPhaseRibbonTypeException;
import com.ext.portlet.NoSuchProposal2PhaseException;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.persistence.ContestPhaseUtil;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceContext;
import org.xcolab.enums.ContestPhasePromoteType;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by johannes on 9/8/15.
 *
 * Utility class for auto promotion, which is done in ContestPhaseLocalServiceImpl
 */
public class AutoPromotionUtil {
    private final static Log _log = LogFactoryUtil.getLog(AutoPromotionUtil.class);
    
    public static final String STATUS_OPEN_FOR_SUBMISSION = "OPEN_FOR_SUBMISSION";
    public static final String STATUS_CLOSED = "CLOSED";
    public static final long SEMIFINALIST_RIBBON_ID = 3L;
    public static final long FINALIST_RIBBON_ID = 1L;

    public static void doBasicPromotion(Date now, ServiceContext serviceContext) throws SystemException, PortalException {
        for (ContestPhase phase : ContestPhaseUtil.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE.getValue())) {
            if (isPhaseContestScheduleTemplatePhase(phase)) {
                continue;
            }

            if (isPhaseContestHasNoValidContest(phase)) {
                continue;
            }

            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now) && !ContestPhaseLocalServiceUtil.getPhaseActive(phase)) {
                // we have a candidate for promotion, find next phase
                try {
                	_log.info("promoting phase " + phase.getContestPhasePK());
                	Contest contest = ContestLocalServiceUtil.getContest(phase.getContestPK());
                    ContestPhase nextPhase = ContestPhaseLocalServiceUtil.getNextContestPhase(phase);
                    for (Proposal p : ProposalLocalServiceUtil.getProposalsInContestPhase(phase.getContestPhasePK())) {
                        //skip already promoted proposal
                        if (hasProposalAlreadyBeenPromoted(p, phase)) {
                            continue;
                        }

                        if(!proposalIsVisible(p, phase)) {
                            continue;
                        }

                        ContestPhaseLocalServiceUtil.promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
                    }

                    // update phase for which promotion was done (mark it as
                    // "promotion done")
                    phase.setContestPhaseAutopromote("PROMOTE_DONE");
                    ContestPhaseLocalServiceUtil.updateContestPhase(phase);

                    // if transition is to voting phase
                    if (ContestPhaseLocalServiceUtil.getContestStatus(nextPhase).isCanVote()) {
                        ContestLocalServiceUtil.transferSupportsToVote(contest, serviceContext);
                    }

                    // Add contest year suffix, if contest has been completed
                    ContestLocalServiceUtil.addContestYearSuffixToContest(contest, true);

                    _log.info("done promoting phase " + phase.getContestPhasePK());
                } catch (SystemException | PortalException e) {
                    _log.error("Exception thrown when doing autopromotion for phase " + phase.getContestPhasePK(), e);
                }
            }
        }
    }

    public static void doJudgingBasedPromotion(Date now, ServiceContext serviceContext) throws SystemException, PortalException {
        for (ContestPhase phase : ContestPhaseUtil.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE_JUDGED.getValue())) {
            if (isPhaseContestScheduleTemplatePhase(phase)) {
                continue;
            }

            if (isPhaseContestHasNoValidContest(phase)) {
                continue;
            }

            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now) && !ContestPhaseLocalServiceUtil.getPhaseActive(phase)) {
                _log.info("promoting phase " + phase.getContestPhasePK() + " (judging)");

                // Only do the promotion if all proposals have been successfully reviewed
                if (allProposalsReviewed(phase)) {
                	Contest contest = ContestLocalServiceUtil.getContest(phase.getContestPK());
                    ContestPhase nextPhase = ContestPhaseLocalServiceUtil.getNextContestPhase(phase);
                    for (Proposal p : ProposalLocalServiceUtil.getProposalsInContestPhase(phase.getContestPhasePK())) {
                    	try {
                        	// check if proposal isn't already associated with requested phase
                    		if (Proposal2PhaseLocalServiceUtil.getProposal2Phase(new Proposal2PhasePK(p.getProposalId(), nextPhase.getContestPhasePK())) != null) {
                                _log.warn("Proposal is already associated with given contest phase");
                                continue;
                    		}
                        }
                    	catch (NoSuchProposal2PhaseException e) {
                    		// no such proposal2phase, we can safely add association
                    	}
                        //skip already promoted proposal
                        if (hasProposalAlreadyBeenPromoted(p, phase)) {
                            System.out.println("Proposal already promoted "+p.getProposalId());
                            continue;
                        }

                        // Decide about the promotion
                        if (didJudgeDecideToPromote(p, phase)) {
                            System.out.println("Promote proposal "+p.getProposalId());
                            ContestPhaseLocalServiceUtil.promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
                        }

                        // Enable this line to post promotion comment to Evaluation tab comment section
                        // proposalLocalService.contestPhasePromotionCommentNotifyProposalContributors(p, phase);

                        // Add this check for extra security to prevent proposal authors from being spammed (see COLAB-500)
                        if (hasProposalAlreadyBeenReviewed(p, phase)) {
                            try {
                                ProposalLocalServiceUtil.contestPhasePromotionEmailNotifyProposalContributors(p,  phase, null);
                                createProposalContestPhasePromotionDoneAttribute(p.getProposalId(), phase.getContestPhasePK());
                            } catch (Throwable e) {
                                _log.error("Could not send proposal promotion colab messaging notification", e);
                            }
                        }
                    }

                    // if transition is to voting phase
                    if (ContestPhaseLocalServiceUtil.getContestStatus(nextPhase).isCanVote()) {
                    	ContestLocalServiceUtil.transferSupportsToVote(contest, serviceContext); //TODO enable me again
                    }
                    phase.setContestPhaseAutopromote("PROMOTE_DONE");
                    ContestPhaseLocalServiceUtil.updateContestPhase(phase);

                    // Add contest year suffix, if contest has been completed
                    ContestLocalServiceUtil.addContestYearSuffixToContest(contest, true);

                    _log.info("done promoting phase " + phase.getContestPhasePK());
                } else {
                    _log.warn("Judge promoting failed for ContestPhase with ID " + phase.getContestPhasePK() + " - not all proposals have been reviewed");
                }
            }
        }
    }

    public static void distributeRibbons(Date now) throws SystemException, PortalException {
        for (ContestPhase phase : ContestPhaseUtil.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE_RIBBONIZE.getValue())) {
            if (isPhaseContestScheduleTemplatePhase(phase)) {
                continue;
            }

            if (isPhaseContestHasNoValidContest(phase)) {
                continue;
            }

            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now) && !ContestPhaseLocalServiceUtil.getPhaseActive(phase)) {
                _log.info("promoting phase " + phase.getContestPhasePK() + " (ribbonize)");

                try {
                    _log.info("promoting phase " + phase.getContestPhasePK());
                    Contest contest = ContestLocalServiceUtil.getContest(phase.getContestPK());
                    ContestPhase nextPhase = ContestPhaseLocalServiceUtil.getNextContestPhase(phase);

                    List<ContestPhase> contestPhases = ContestLocalServiceUtil.getAllPhases(contest);
                    ContestPhase finalistSelection = null;
                    ContestPhase semifinalistSelection = null;
                    Set<ContestPhase> proposalCreationPhases = new HashSet<>();

                    for (ContestPhase cp : contestPhases) {
                        ContestPhaseType phaseType = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(cp.getContestPhaseType());

                        if (phaseType.getStatus().equals(STATUS_OPEN_FOR_SUBMISSION)) {
                            proposalCreationPhases.add(cp);
                        }
                        else if (phaseType.getStatus().equals(STATUS_CLOSED)) {
                            if (finalistSelection == null || cp.getPhaseEndDate().after(finalistSelection.getPhaseEndDate())) {
                                semifinalistSelection = finalistSelection;
                                finalistSelection = cp;
                            }
                        }
                    }

                    Set<Proposal> allProposals = new HashSet<>();
                    List<Proposal> finalists = null;
                    List<Proposal> semifinalists = null;
                    if (proposalCreationPhases.size() > 0) {
                        for (ContestPhase creationPhase : proposalCreationPhases) {
                            final List<Proposal> proposalsInContestPhase = ProposalLocalServiceUtil.getProposalsInContestPhase(creationPhase.getContestPhasePK());
                            addAllVisibleProposalsToCollection(proposalsInContestPhase, allProposals, creationPhase);
                        }
                    } else {
                        _log.error(String.format("Can't distribute ribbons: No proposal creation phase found in contest %d", phase.getContestPK()));
                    }

                    if (finalistSelection != null) {
                        ContestPhase finalsPhase = ContestPhaseLocalServiceUtil.getNextContestPhase(finalistSelection);
                        finalists = ProposalLocalServiceUtil.getProposalsInContestPhase(finalsPhase.getContestPhasePK());
                        //make sure all finalists are in the set
                        addAllVisibleProposalsToCollection(finalists, allProposals, finalsPhase);

                        if (semifinalistSelection != null) {
                            ContestPhase semifinalsPhase = ContestPhaseLocalServiceUtil.getNextContestPhase(semifinalistSelection);
                            semifinalists = ProposalLocalServiceUtil.getProposalsInContestPhase(semifinalsPhase.getContestPhasePK());
                            semifinalists.removeAll(finalists);
                            //make sure all semifinalists are in the set
                            addAllVisibleProposalsToCollection(semifinalists, allProposals, semifinalsPhase);
                        } else {
                            _log.warn(String.format("No semifinalist phase found in contest %d", phase.getContestPK()));
                        }
                    } else {
                        _log.error(String.format("Can't distribute ribbons: No finalist phase found in contest %d", phase.getContestPK()));
                    }

                    associateProposalsWithCompletedPhase(allProposals, phase, nextPhase);

                    assignRibbonsToProposals(SEMIFINALIST_RIBBON_ID, semifinalists, nextPhase.getContestPhasePK());
                    assignRibbonsToProposals(FINALIST_RIBBON_ID, finalists, nextPhase.getContestPhasePK());

                    // update phase for which promotion was done (mark it as "promotion done")
                    phase.setContestPhaseAutopromote("PROMOTE_DONE");
                    ContestPhaseLocalServiceUtil.updateContestPhase(phase);

                    // Add contest year suffix, if contest has been completed
                    ContestLocalServiceUtil.addContestYearSuffixToContest(contest, true);

                    _log.info("done promoting phase " + phase.getContestPhasePK());
                } catch (SystemException | PortalException e) {
                    _log.error("Exception thrown when doing auto promotion for phase " + phase.getContestPhasePK(), e);
                }
            }
        }
    }

    private static void addAllVisibleProposalsToCollection(Collection<Proposal> sourceCollection, Collection<Proposal> toCollection, ContestPhase inPhase) {
        for (Proposal p : sourceCollection) {
            if(!proposalIsVisible(p, inPhase)) {
                continue;
            }
            toCollection.add(p);
        }
    }

    private static void associateProposalsWithCompletedPhase(Set<Proposal> proposals, ContestPhase previousPhase, ContestPhase completedPhase)
            throws SystemException, PortalException {

        for (Proposal proposal : proposals) {
            //update the last phase association - set the end version to the current version
            Long currentProposalVersion = ProposalVersionLocalServiceUtil.countByProposalId(proposal.getProposalId());
            if (currentProposalVersion < 0) {
                throw new SystemException("Proposal not found");
            }

            try {
                //make sure that proposals in the phase directly before have final versions
                Proposal2Phase oldP2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), previousPhase.getContestPhasePK());

                if (oldP2p != null) {
                    if (oldP2p.getVersionTo() < 0) {
                        oldP2p.setVersionTo(currentProposalVersion.intValue());
                        Proposal2PhaseLocalServiceUtil.updateProposal2Phase(oldP2p);
                    }
                }
            } catch (NoSuchProposal2PhaseException ignored) {}

            Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.create(proposal.getProposalId(), completedPhase.getContestPhasePK());
            p2p.setVersionFrom(currentProposalVersion.intValue());
            p2p.setVersionTo(currentProposalVersion.intValue());
            Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);
        }
    }

    private static void assignRibbonsToProposals(Long ribbonId, List<Proposal> proposals, Long phasePK)
            throws SystemException, PortalException {
        if (ribbonId > 0) {
            for (Proposal proposal : proposals) {
                //first, see if a ribbon already exists
                ProposalContestPhaseAttribute attribute = null;
                try {
                    attribute = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposal.getProposalId(), phasePK,
                            ProposalContestPhaseAttributeKeys.RIBBON);
                } catch (NoSuchProposalContestPhaseAttributeException ignored) {
                }

                //do not overwrite existing ribbons
                if (attribute == null) {
                    try {
                        ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(ribbonId);
                        ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(proposal.getProposalId(), phasePK,
                                ProposalContestPhaseAttributeKeys.RIBBON, ribbonId);
                    } catch (NoSuchContestPhaseRibbonTypeException e) {
                        ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(proposal.getProposalId(), phasePK,
                                ProposalContestPhaseAttributeKeys.RIBBON);
                    }
                }
            }
        }
    }

    private static boolean proposalIsVisible(Proposal p, ContestPhase phase) {
        if(!p.getVisible()) return false;

        try {
            ProposalContestPhaseAttribute attr = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(), phase.getContestPhasePK(), "VISIBLE");
            if(attr.getNumericValue() == 0) return false;
        } catch (Exception ignored) {
        }

        return true;
    }

    public static ProposalContestPhaseAttribute getAttribute(long proposalId, long phaseId, String key) {
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, phaseId, key);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean allProposalsReviewed(ContestPhase phase) throws SystemException, PortalException {
        boolean allProposalsReviewed = true;
        for (Proposal p : ProposalLocalServiceUtil.getProposalsInContestPhase(phase.getContestPhasePK())) {
            if (!hasProposalAlreadyBeenReviewed(p, phase)) {
                allProposalsReviewed = false;
                break;
            }
        }

        return allProposalsReviewed;
    }

    public static boolean hasProposalAlreadyBeenPromoted(Proposal p, ContestPhase phase) throws SystemException {
        boolean hasProposalAlreadyBeenPromoted = ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(
                p.getProposalId(),
                phase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                0
        );

        if (hasProposalAlreadyBeenPromoted) {
            _log.info("proposal "+ p.getProposalId() +" in phase " + phase.getContestPhasePK() + " has already been promoted.");
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasProposalAlreadyBeenReviewed(Proposal p, ContestPhase phase) {
        ProposalContestPhaseAttribute judgeDecision = getAttribute(p.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        Long judgeDecisionValue = (judgeDecision == null) ? JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue() : judgeDecision.getNumericValue();
        ProposalContestPhaseAttribute fellowAction = getAttribute(p.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
        Long fellowActionValue = (fellowAction == null) ? JudgingSystemActions.FellowAction.NO_DECISION.getAttributeValue() : fellowAction.getNumericValue();

        final JudgingSystemActions.AdvanceDecision judgesAdvanceDecision = JudgingSystemActions.AdvanceDecision.fromInt(judgeDecisionValue.intValue());
        final JudgingSystemActions.FellowAction fellowAdvanceDecision = JudgingSystemActions.FellowAction.fromInt(fellowActionValue.intValue());
        return !(judgesAdvanceDecision == JudgingSystemActions.AdvanceDecision.NO_DECISION &&
                !fellowAdvanceDecision.isActionProhibitingAdvancing());
    }

    public static boolean didJudgeDecideToPromote(Proposal p, ContestPhase phase) {
        ProposalContestPhaseAttribute judgeDecision = getAttribute(p.getProposalId(),
                phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        Long judgeDecisionValue;
        if (judgeDecision == null) {
            judgeDecisionValue = (long) JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue();
        } else {
            judgeDecisionValue = judgeDecision.getNumericValue();
        }

        return JudgingSystemActions.AdvanceDecision.fromInt(judgeDecisionValue.intValue())
                == JudgingSystemActions.AdvanceDecision.MOVE_ON;
    }

    public static boolean isPhaseContestHasNoValidContest(ContestPhase phase) throws PortalException, SystemException{
        try{
            ContestPhaseLocalServiceUtil.getContest(phase);
        } catch(NoSuchContestException e){
            _log.warn("promoting phase failed due to invalid contest ", e);
            return true;
        }
        return false;
    }

    public static boolean isPhaseContestScheduleTemplatePhase(ContestPhase phase) {
        // Usually we do not have phases with a ContestId key = 0; used as template contest phases for our ContestSchedules
        return phase.getContestPK() == 0;
    }

    public static void createProposalContestPhasePromotionDoneAttribute(long proposalId, long currentPhaseId) {
        //save the information that the promotion has been done.
        if (currentPhaseId != 0) {
            ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(
                    proposalId,
                    currentPhaseId,
                    ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                    0,
                    "true"
            );
        }
    }
}
