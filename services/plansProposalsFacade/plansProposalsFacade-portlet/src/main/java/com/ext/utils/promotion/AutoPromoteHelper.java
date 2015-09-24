package com.ext.utils.promotion;

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
 * Created by johannes on 9/9/15.
 *
 * Helper class to automatically promote proposals to the next phase.
 */
public class AutoPromoteHelper {
    private final static Log _log = LogFactoryUtil.getLog(AutoPromoteHelper.class);
    
    public static final String STATUS_OPEN_FOR_SUBMISSION = "OPEN_FOR_SUBMISSION";
    public static final String STATUS_CLOSED = "CLOSED";
    public static final long SEMIFINALIST_RIBBON_ID = 3L;
    public static final long FINALIST_RIBBON_ID = 1L;

    private Date now;
    private ServiceContext serviceContext;

    public AutoPromoteHelper(Date now, ServiceContext serviceContext) {
        this.now = now;
        this.serviceContext = serviceContext;
    }

    public void doBasicPromotion() throws SystemException, PortalException {
        for (ContestPhase phase : ContestPhaseUtil.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE.getValue())) {
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase);
            if (phasePromotionHelper.isPhaseContestScheduleTemplatePhase()) {
                continue;
            }

            if (phasePromotionHelper.isPhaseContestHasNoValidContest()) {
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
                        if (phasePromotionHelper.hasProposalAlreadyBeenPromoted(p)) {
                            continue;
                        }

                        if(!phasePromotionHelper.proposalIsVisible(p)) {
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

    public void doJudgingBasedPromotion() throws SystemException, PortalException {
        for (ContestPhase phase : ContestPhaseUtil.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE_JUDGED.getValue())) {
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase);
            if (phasePromotionHelper.isPhaseContestScheduleTemplatePhase()) {
                continue;
            }

            if (phasePromotionHelper.isPhaseContestHasNoValidContest()) {
                continue;
            }

            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now) && !ContestPhaseLocalServiceUtil.getPhaseActive(phase)) {
                _log.info("promoting phase " + phase.getContestPhasePK() + " (judging)");

                // Only do the promotion if all proposals have been successfully reviewed
                if (phasePromotionHelper.allProposalsReviewed()) {
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
                        if (phasePromotionHelper.hasProposalAlreadyBeenPromoted(p)) {
                            System.out.println("Proposal already promoted "+p.getProposalId());
                            continue;
                        }

                        // Decide about the promotion
                        if (phasePromotionHelper.didJudgeDecideToPromote(p)) {
                            System.out.println("Promote proposal "+p.getProposalId());
                            ContestPhaseLocalServiceUtil.promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
                        }

                        // Enable this line to post promotion comment to Evaluation tab comment section
                        // proposalLocalService.contestPhasePromotionCommentNotifyProposalContributors(p, phase);

                        // Add this check for extra security to prevent proposal authors from being spammed (see COLAB-500)
                        if (phasePromotionHelper.hasProposalAlreadyBeenReviewed(p)) {
                            try {
                                ProposalLocalServiceUtil.contestPhasePromotionEmailNotifyProposalContributors(p,  phase, null);
                                PhasePromotionHelper.createProposalContestPhasePromotionDoneAttribute(p.getProposalId(), phase.getContestPhasePK());
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

    public void distributeRibbons() throws SystemException, PortalException {
        for (ContestPhase phase : ContestPhaseUtil.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE_RIBBONIZE.getValue())) {
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase);
            if (phasePromotionHelper.isPhaseContestScheduleTemplatePhase()) {
                continue;
            }

            if (phasePromotionHelper.isPhaseContestHasNoValidContest()) {
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

                    // We want to wait showing all ribbons until the winners are determined
                    contest.setHideRibbons(true);
                    contest.persist();

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

    private void addAllVisibleProposalsToCollection(Collection<Proposal> sourceCollection, Collection<Proposal> toCollection, ContestPhase inPhase) {
        PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(inPhase);
        for (Proposal p : sourceCollection) {
            if(!phasePromotionHelper.proposalIsVisible(p)) {
                continue;
            }
            toCollection.add(p);
        }
    }

    private void associateProposalsWithCompletedPhase(Set<Proposal> proposals, ContestPhase previousPhase, ContestPhase completedPhase)
            throws SystemException, PortalException {

        for (Proposal proposal : proposals) {
            //update the last phase association - set the end version to the current version
            Long currentProposalVersion = ProposalVersionLocalServiceUtil.countByProposalId(proposal.getProposalId());
            if (currentProposalVersion <= 0) {
                _log.error(String.format("Proposal %d not found: version was %d", proposal.getProposalId(), currentProposalVersion));
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

    private void assignRibbonsToProposals(Long ribbonId, List<Proposal> proposals, Long phasePK)
            throws SystemException, PortalException {
        if (ribbonId > 0) {
            for (Proposal proposal : proposals) {
                //don't overwrite existing ribbons, as they might be manually assigned winner's ribbons!
                if (!ProposalContestPhaseAttributeLocalServiceUtil.hasProposalContestPhaseAttribute(proposal.getProposalId(), phasePK,
                        ProposalContestPhaseAttributeKeys.RIBBON)) {
                    try {
                        ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(ribbonId);
                        ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(proposal.getProposalId(), phasePK,
                                ProposalContestPhaseAttributeKeys.RIBBON, ribbonId);
                    } catch (NoSuchContestPhaseRibbonTypeException e) {
                        ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(proposal.getProposalId(), phasePK,
                                ProposalContestPhaseAttributeKeys.RIBBON);
                    }
                } else {
                    _log.error(String.format(
                            "Skipping ribbon (id = %d) assignment for proposal %d: this proposal already has a ribbon assigned",
                            ribbonId, proposal.getProposalId()
                    ));
                }
            }
        }
    }
}
