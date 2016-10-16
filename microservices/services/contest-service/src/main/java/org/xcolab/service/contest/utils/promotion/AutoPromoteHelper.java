package org.xcolab.service.contest.utils.promotion;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.proposals.Proposal2PhaseClient;
import org.xcolab.client.proposals.ProposalContestPhaseAttributeClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphaseribbontype.ContestPhaseRibbonTypeDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contest.ContestService;
import org.xcolab.service.contest.service.contestphase.ContestPhaseService;
import org.xcolab.service.contest.utils.email.EmailToAdminDispatcher;
import org.xcolab.service.contest.utils.promotion.enums.ContestPhasePromoteType;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

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
@Service
public class AutoPromoteHelper {

    private static final Logger _log = LoggerFactory.getLogger(AutoPromoteHelper.class);

    public static final String STATUS_OPEN_FOR_SUBMISSION = "OPEN_FOR_SUBMISSION";
    public static final String STATUS_CLOSED = "CLOSED";
    public static final long SEMIFINALIST_RIBBON_ID = 3L;
    public static final long FINALIST_RIBBON_ID = 1L;



    private Date now;

    private final ContestPhaseDao contestPhaseDao;
    private final ContestPhaseRibbonTypeDao contestPhaseRibbonTypeDao;
    private final ContestDao contestDao;
    private final ContestPhaseTypeDao contestPhaseTypeDao;

    private final ContestPhaseService contestPhaseService;
    private final ContestService contestService;

    @Autowired
    public AutoPromoteHelper( ContestPhaseDao contestPhaseDao, ContestDao contestDao, ContestPhaseRibbonTypeDao contestPhaseRibbonTypeDao, ContestPhaseService contestPhaseService, ContestPhaseTypeDao contestPhaseTypeDao, ContestService contestService) {

        this.contestPhaseDao = contestPhaseDao;
        this.contestDao = contestDao;
        this.contestPhaseRibbonTypeDao = contestPhaseRibbonTypeDao;
        this.contestPhaseTypeDao = contestPhaseTypeDao;
        this.contestPhaseService = contestPhaseService;
        this.contestService = contestService;
    }

    public void doBasicPromotion() {
        for (ContestPhase phase : contestPhaseDao.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE.getValue())) {
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase, contestDao);
            if (phasePromotionHelper.isPhaseContestScheduleTemplatePhase()) {
                continue;
            }

            if (phasePromotionHelper.isPhaseContestHasNoValidContest()) {
                continue;
            }

            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now) && !contestPhaseDao.isPhaseActive(phase)) {
                // we have a candidate for promotion, find next phase
                try {
                    _log.info("promoting phase " + phase.getContestPhasePK());
                    Contest contest = contestDao.get(phase.getContestPK());
                    _log.info("promoting contest " + contest.getContestPK());
                    ContestPhase nextPhase = contestPhaseService.getNextContestPhase(phase);
                    for (Proposal p : ProposalsClient.getProposalsInContestPhase(phase.getContestPhasePK())) {
                        //skip already promoted proposal
                        if (phasePromotionHelper.hasProposalAlreadyBeenPromoted(p)) {
                            continue;
                        }

                        if (!phasePromotionHelper.proposalIsVisible(p)) {
                            continue;
                        }

                        Proposal2PhaseClient.promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
                    }

                    // update phase for which promotion was done (mark it as
                    // "promotion done")
                    phase.setContestPhaseAutopromote("PROMOTE_DONE");
                    contestPhaseDao.update(phase);

                    // if transition is to voting phase
                    if (contestPhaseService.getContestStatus(nextPhase).isCanVote()) {
                        contestPhaseService.transferSupportsToVote(contest);
                    }

                    // Add contest year suffix, if contest has been completed
                    contestService.addContestYearSuffixToContest(contest, true);

                    _log.info("done promoting phase " + phase.getContestPhasePK());
                } catch (NotFoundException e) {
                    _log.error("Exception thrown when doing autopromotion for phase " + phase.getContestPhasePK(), e);
                }
            }
        }
    }

    public void doJudgingBasedPromotion() {
        for (ContestPhase phase : contestPhaseDao.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE_JUDGED.getValue())) {
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase, contestDao);
            if (phasePromotionHelper.isPhaseContestScheduleTemplatePhase()) {
                continue;
            }

            if (phasePromotionHelper.isPhaseContestHasNoValidContest()) {
                continue;
            }

            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now) && !contestPhaseDao.isPhaseActive(phase)) {
                _log.info("promoting phase " + phase.getContestPhasePK() + " (judging)");

                try {
                    // Only do the promotion if all proposals have been successfully reviewed
                    if (phasePromotionHelper.allProposalsReviewed()) {
                        Contest contest = contestDao.get(phase.getContestPK());
                        _log.info("promoting contest " + contest.getContestPK() + " (judging) ");
                        ContestPhase nextPhase = contestPhaseService.getNextContestPhase(phase);
                        for (Proposal p : ProposalsClient.getProposalsInContestPhase(phase.getContestPhasePK())) {
                            try {
                                // check if proposal isn't already associated with requested phase
                                if (Proposal2PhaseClient.getProposal2PhaseByProposalIdContestPhaseId(p.getProposalId(), nextPhase.getContestPhasePK()) != null) {
                                    _log.warn("Proposal is already associated with given contest phase");
                                    continue;
                                }
                            } catch (Proposal2PhaseNotFoundException e) {
                                // no such proposal2phase, we can safely add association
                            }
                            //skip already promoted proposal
                            if (phasePromotionHelper.hasProposalAlreadyBeenPromoted(p)) {
                                _log.trace("Proposal already promoted " + p.getProposalId());
                                continue;
                            }

                            // Decide about the promotion
                            if (phasePromotionHelper.didJudgeDecideToPromote(p)) {
                                _log.info("Promote proposal " + p.getProposalId());
                                Proposal2PhaseClient.promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
                            }

                            // Enable this line to post promotion comment to Evaluation tab comment section
                            // proposalLocalService.contestPhasePromotionCommentNotifyProposalContributors(p, phase);

                            // Add this check for extra security to prevent proposal authors from being spammed (see COLAB-500)
                            if (phasePromotionHelper.hasProposalAlreadyBeenReviewed(p)) {
                                //ProposalLocalServiceUtil.contestPhasePromotionEmailNotifyProposalContributors(p, phase, null);
                                PhasePromotionHelper.createProposalContestPhasePromotionDoneAttribute(p.getProposalId(), phase.getContestPhasePK());

                            }
                        }

                        // if transition is to voting phase
                        if (contestPhaseService.getContestStatus(nextPhase).isCanVote()) {
                            contestPhaseService.transferSupportsToVote(contest); //TODO enable me again
                        }
                        phase.setContestPhaseAutopromote("PROMOTE_DONE");
                        contestPhaseDao.update(phase);

                        // Add contest year suffix, if contest has been completed
                        contestService.addContestYearSuffixToContest(contest, true);

                        _log.info("done promoting phase " + phase.getContestPhasePK());
                    } else {
                        _log.warn("Judge promoting failed for ContestPhase with ID " + phase.getContestPhasePK() + " - not all proposals have been reviewed");
                    }
                } catch (NotFoundException ignored) {

                }
            }
        }
    }

    public void distributeRibbons() {
        for (ContestPhase phase : contestPhaseDao.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE_RIBBONIZE.getValue())) {
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase, contestDao);
            if (phasePromotionHelper.isPhaseContestScheduleTemplatePhase()) {
                continue;
            }

            if (phasePromotionHelper.isPhaseContestHasNoValidContest()) {
                continue;
            }

            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now) && !contestPhaseDao.isPhaseActive(phase)) {
                _log.info("promoting phase " + phase.getContestPhasePK() + " (ribbonize)");

                try {
                    _log.info("promoting phase " + phase.getContestPhasePK());
                    Contest contest = contestDao.get(phase.getContestPK());
                    ContestPhase nextPhase = contestPhaseService.getNextContestPhase(phase);

                    List<ContestPhase> contestPhases = contestService.getAllContestPhases(contest.getContestPK());
                    ContestPhase finalistSelection = null;
                    ContestPhase semifinalistSelection = null;
                    Set<ContestPhase> proposalCreationPhases = new HashSet<>();

                    for (ContestPhase cp : contestPhases) {
                        ContestPhaseType phaseType = contestPhaseTypeDao.get(cp.getContestPhaseType()).get();

                        if (phaseType.getStatus().equals(STATUS_OPEN_FOR_SUBMISSION)) {
                            proposalCreationPhases.add(cp);
                        } else if (phaseType.getStatus().equals(STATUS_CLOSED)) {
                            if (finalistSelection == null || cp.getPhaseEndDate().after(finalistSelection.getPhaseEndDate())) {
                                semifinalistSelection = finalistSelection;
                                finalistSelection = cp;
                            }
                        }
                    }

                    Set<Proposal> allProposals = new HashSet<>();
                    if (!proposalCreationPhases.isEmpty()) {
                        for (ContestPhase creationPhase : proposalCreationPhases) {
                            final List<Proposal> proposalsInContestPhase = ProposalsClient.getProposalsInContestPhase(creationPhase.getContestPhasePK());
                            addAllVisibleProposalsToCollection(proposalsInContestPhase, allProposals, creationPhase);
                        }
                    } else {
                        _log.error(String.format("Can't distribute ribbons: No proposal creation phase found in contest %d", phase.getContestPK()));
                    }

                    List<Proposal> finalists = null;
                    List<Proposal> semifinalists = null;
                    if (finalistSelection != null) {
                        ContestPhase finalsPhase = contestPhaseService.getNextContestPhase(finalistSelection);
                        finalists = ProposalsClient.getProposalsInContestPhase(finalsPhase.getContestPhasePK());
                        //make sure all finalists are in the set
                        addAllVisibleProposalsToCollection(finalists, allProposals, finalsPhase);

                        if (semifinalistSelection != null) {
                            ContestPhase semifinalsPhase = contestPhaseService.getNextContestPhase(semifinalistSelection);
                            semifinalists = ProposalsClient.getProposalsInContestPhase(semifinalsPhase.getContestPhasePK());
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
                    if (semifinalists != null) {
                        assignRibbonsToProposals(SEMIFINALIST_RIBBON_ID, semifinalists, nextPhase.getContestPhasePK());
                    }
                    if (finalists != null) {
                        assignRibbonsToProposals(FINALIST_RIBBON_ID, finalists, nextPhase.getContestPhasePK());
                    } else {
                        _log.warn("No finalists found to distribute ribbons to.");
                    }

                    // We want to wait showing all ribbons until the winners are determined
                    contest.setHideRibbons(true);
                    contestDao.update(contest);

                    // update phase for which promotion was done (mark it as "promotion done")
                    phase.setContestPhaseAutopromote("PROMOTE_DONE");
                    contestPhaseDao.update(phase);

                    // Add contest year suffix, if contest has been completed
                    contestService.addContestYearSuffixToContest(contest, true);

                    _log.info("done promoting phase " + phase.getContestPhasePK());
                } catch (NotFoundException e) {
                    _log.error("Exception thrown when doing auto promotion for phase " + phase.getContestPhasePK(), e);
                }
            }
        }
    }

    private void addAllVisibleProposalsToCollection(Collection<Proposal> sourceCollection, Collection<Proposal> toCollection, ContestPhase inPhase) {
        PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(inPhase, contestDao);
        for (Proposal p : sourceCollection) {
            if (!phasePromotionHelper.proposalIsVisible(p)) {
                continue;
            }
            toCollection.add(p);
        }
    }

    private void associateProposalsWithCompletedPhase(Set<Proposal> proposals, ContestPhase previousPhase, ContestPhase completedPhase)
            throws NotFoundException {

        for (Proposal proposal : proposals) {
            //update the last phase association - set the end version to the current version
            final long proposalId = proposal.getProposalId();
            Integer currentProposalVersion = ProposalsClient.countProposalVersions(proposalId);
            if (currentProposalVersion <= 0) {
                _log.error(String.format("Proposal %d not found: version was %d", proposalId, currentProposalVersion));
                throw new NotFoundException("Proposal not found");
            }

            final int currentProposalVersionNumber = currentProposalVersion.intValue();
            try {
                //make sure that proposals in the phase directly before have final versions
                Proposal2Phase oldP2p = Proposal2PhaseClient.getProposal2PhaseByProposalIdContestPhaseId(proposalId, previousPhase.getContestPhasePK());

                if (oldP2p != null) {
                    if (oldP2p.getVersionTo() < 0) {
                        oldP2p.setVersionTo(currentProposalVersionNumber);
                        Proposal2PhaseClient.updateProposal2Phase(oldP2p);
                    }
                }
            } catch (Proposal2PhaseNotFoundException ignored) {
            }

            Proposal2Phase p2p;
            final long completedPhasePK = completedPhase.getContestPhasePK();
            try {
                //This is a workaround for a bug that caused two new p2p's to be created
                p2p = Proposal2PhaseClient.getProposal2PhaseByProposalIdContestPhaseId(proposalId, completedPhasePK);
                //If this succeeds, we want to log the error to help diagnose the problem
                _log.error(String.format("P2p found while associating proposal %d with phase %d.", proposalId, completedPhasePK));
                new EmailToAdminDispatcher("Duplicate primary key for P2p in auto promotion",
                        String.format("Unexpectedly found p2p. Setting versionFrom = %d and versionTo = %d: %s",
                                currentProposalVersionNumber, currentProposalVersionNumber, p2p)).sendMessage();
            } catch (Proposal2PhaseNotFoundException e) {
                p2p = new Proposal2Phase();
                p2p.setContestPhaseId(proposalId);
                p2p.setContestPhaseId(completedPhasePK);
                Proposal2PhaseClient.createProposal2Phase(p2p);
                _log.debug(String.format("Created new p2p: %s", p2p));
            }

            p2p.setVersionFrom(currentProposalVersionNumber);
            p2p.setVersionTo(currentProposalVersionNumber);
            Proposal2PhaseClient.updateProposal2Phase(p2p);
        }
    }

    private void assignRibbonsToProposals(Long ribbonId, List<Proposal> proposals, Long phasePK) {
        if (ribbonId > 0) {
            for (Proposal proposal : proposals) {
                //don't overwrite existing ribbons, as they might be manually assigned winner's ribbons!
                if (!ProposalContestPhaseAttributeClient.hasProposalContestPhaseAttribute(proposal.getProposalId(), phasePK,
                        ProposalContestPhaseAttributeKeys.RIBBON)) {

                    if (ContestClient.getContestPhaseRibbonType(ribbonId) != null) {
                        ProposalContestPhaseAttributeClient.setProposalContestPhaseAttribute(proposal.getProposalId(), phasePK,
                                ProposalContestPhaseAttributeKeys.RIBBON, 0l, ribbonId, "");
                    } else {

                        ProposalContestPhaseAttributeClient.deleteProposalContestPhaseAttribute(proposal.getProposalId(), phasePK,
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

    public void setNow(Date now) {
        this.now = now;
    }
}
