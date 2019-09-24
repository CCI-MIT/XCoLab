package org.xcolab.service.contest.utils.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.tables.pojos.Proposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.email.StaticEmailContext;
import org.xcolab.entity.utils.email.ContestPhasePromotionEmail;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contest.ContestService;
import org.xcolab.service.contest.service.contestphase.ContestPhaseService;
import org.xcolab.service.contest.utils.email.EmailToAdminDispatcher;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Helper class to automatically promote proposals to the next phase.
 */
@Service
public class PromotionService {

    private static final Logger log = LoggerFactory.getLogger(PromotionService.class);

    private static final String STATUS_OPEN_FOR_SUBMISSION = "OPEN_FOR_SUBMISSION";
    private static final String STATUS_CLOSED = "CLOSED";
    private static final long SEMIFINALIST_RIBBON_ID = 3L;
    private static final long FINALIST_RIBBON_ID = 1L;

    private final ContestPhaseDao contestPhaseDao;
    private final ContestDao contestDao;
    private final ContestPhaseTypeDao contestPhaseTypeDao;

    private final ContestPhaseService contestPhaseService;
    private final ContestService contestService;

    private Date now;

    @Autowired
    public PromotionService(ContestPhaseDao contestPhaseDao, ContestDao contestDao,
            ContestPhaseService contestPhaseService, ContestPhaseTypeDao contestPhaseTypeDao,
            ContestService contestService) {
        this.contestPhaseDao = contestPhaseDao;
        this.contestDao = contestDao;
        this.contestPhaseTypeDao = contestPhaseTypeDao;
        this.contestPhaseService = contestPhaseService;
        this.contestService = contestService;
    }

    public synchronized int doPromotion(Date now) {
        setNow(now);
        int promotedProposals = 0;
        promotedProposals += doBasicPromotion();
        promotedProposals += doJudgingBasedPromotion();
        promotedProposals += distributeRibbons();
        return promotedProposals;
    }

    private int doBasicPromotion() {
        int promotedProposals = 0;
        final List<ContestPhaseWrapper> phasesToPromote =
                contestPhaseDao.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE.getValue());
        for (ContestPhaseWrapper phase : phasesToPromote) {
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase);
            if (phasePromotionHelper.isPhaseContestScheduleTemplatePhase()) {
                continue;
            }

            if (hasNoValidContest(phase)) {
                continue;
            }

            final boolean hasPhaseEnded = phase.getPhaseEndDate() != null
                    && phase.getPhaseEndDate().before(now);
            if (hasPhaseEnded && !contestPhaseDao.isPhaseActive(phase)) {
                // we have a candidate for promotion, find next phase
                try {
                    log.info("promoting phase {}", phase.getId());
                    ContestWrapper contest = contestDao.get(phase.getContestId());
                    log.info("promoting contest {}", contest.getId());
                    ContestPhaseWrapper nextPhase = contestPhaseService.getNextContestPhase(phase);
                    //TODO: this should not be calling the client
                    final List<ProposalWrapper> proposalsInPhase = StaticProposalContext
                            .getProposalClient().getProposalsInContestPhase(phase.getId());
                    for (ProposalWrapper p : proposalsInPhase) {
                        if (phasePromotionHelper.isProposalPromoted(p)) {
                            continue;
                        }

                        if (!phasePromotionHelper.isProposalVisible(p)) {
                            continue;
                        }

                        StaticProposalContext.getProposalPhaseClient()
                                .promoteProposal(p.getId(), nextPhase.getId(), phase.getId());
                        promotedProposals++;
                    }

                    // update phase for which promotion was done (mark it as
                    // "promotion done")
                    phase.setContestPhaseAutopromote("PROMOTE_DONE");
                    contestPhaseDao.update(phase);

                    if (contestPhaseService.getContestStatus(nextPhase).isCanVote() &&
                            ConfigurationAttributeKey.VOTING_CONVERT_SUPPORTS_TO_VOTES.get()) {
                        contestPhaseService.transferSupportsToVote(contest, nextPhase);
                    }

                    log.info("done promoting phase {}", phase.getId());
                } catch (NotFoundException e) {
                    log.error("Exception thrown when doing autopromotion for phase {}",
                            phase.getId(), e);
                }
            }
        }
        return promotedProposals;
    }

    private int doJudgingBasedPromotion() {
        int promotedProposals = 0;
        final List<ContestPhaseWrapper> phasesToPromote = contestPhaseDao
                .findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE_JUDGED.getValue());
        for (ContestPhaseWrapper phase : phasesToPromote) {
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase);
            if (phasePromotionHelper.isPhaseContestScheduleTemplatePhase()) {
                continue;
            }

            if (hasNoValidContest(phase)) {
                continue;
            }

            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now)
                    && !contestPhaseDao.isPhaseActive(phase)) {
                log.info("promoting phase {} (judging)", phase.getId());

                try {
                    // Only do the promotion if all proposals have been successfully reviewed
                    if (phasePromotionHelper.isAllProposalsReviewed()) {
                        ContestWrapper contest = contestDao.get(phase.getContestId());
                        log.info("promoting contest {} (judging) ", contest.getId());
                        ContestPhaseWrapper nextPhase = contestPhaseService.getNextContestPhase(phase);
                        for (ProposalWrapper p : StaticProposalContext.getProposalClient()
                                .getProposalsInContestPhase(phase.getId())) {
                            try {
                                // check if proposal isn't already associated with requested phase
                                if (StaticProposalContext.getProposalPhaseClient()
                                        .getProposal2PhaseByProposalIdContestPhaseId(
                                                p.getId(), nextPhase.getId())
                                        != null) {
                                    log.warn("Proposal is already associated with given contest "
                                            + "phase");
                                    continue;
                                }
                            } catch (Proposal2PhaseNotFoundException e) {
                                // no such proposal2phase, we can safely add association
                            }
                            //skip already promoted proposal
                            if (phasePromotionHelper.isProposalPromoted(p)) {
                                log.trace("Proposal already promoted {}", p.getId());
                                continue;
                            }

                            // Decide about the promotion
                            if (phasePromotionHelper.didJudgeDecideToPromote(p)) {
                                log.info("Promote proposal {}", p.getId());
                                StaticProposalContext.getProposalPhaseClient()
                                        .promoteProposal(p.getId(), nextPhase.getId(),
                                                phase.getId());
                                promotedProposals++;
                            }

                            // Enable this line to post promotion comment to Evaluation tab comment section
                            // proposalLocalService.contestPhasePromotionCommentNotifyProposalContributors(p, phase);

                            // Add this check for extra security to prevent proposal authors from being spammed (see COLAB-500)
                            if (phasePromotionHelper.isProposalReviewed(p)) {
                                //TODO COLAB-2603: Migrate logic to send email.
                                ContestPhaseWrapper cp = StaticContestContext.getContestClient()
                                        .getContestPhase(phase.getId());
                                ContestPhasePromotionEmail.contestPhasePromotionEmailNotifyProposalContributors(p, cp);
                                PhasePromotionHelper.createProposalContestPhasePromotionDoneAttribute(p.getId(), phase.getId());

                            }
                        }

                        if (contestPhaseService.getContestStatus(nextPhase).isCanVote()) {
                            log.info("Transferring supports to votes in contest {}, phase {} ...",
                                    contest.getId(), nextPhase.getId());
                            contestPhaseService.transferSupportsToVote(contest, nextPhase);
                        }
                        phase.setContestPhaseAutopromote("PROMOTE_DONE");
                        contestPhaseDao.update(phase);


                        log.info("done promoting phase {}", phase.getId());
                    } else {
                        log.warn("Judge promoting failed for ContestPhaseWrapper with ID {} - not all "
                                + "proposals have been reviewed", phase.getId());
                    }
                } catch (NotFoundException ignored) {

                }
            }
        }
        return promotedProposals;
    }

    private int distributeRibbons() {
        int promotedProposals = 0;
        final List<ContestPhaseWrapper> phasesToPromote = contestPhaseDao
                .findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE_RIBBONIZE.getValue());
        for (ContestPhaseWrapper phase : phasesToPromote) {
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase);
            if (phasePromotionHelper.isPhaseContestScheduleTemplatePhase()) {
                continue;
            }

            if (hasNoValidContest(phase)) {
                continue;
            }

            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now)
                    && !contestPhaseDao.isPhaseActive(phase)) {
                log.info("promoting phase {} (ribbonize)", phase.getId());

                try {
                    log.info("promoting phase {}", phase.getId());
                    ContestWrapper contest = contestDao.get(phase.getContestId());
                    ContestPhaseWrapper nextPhase = contestPhaseService.getNextContestPhase(phase);

                    List<ContestPhaseWrapper> contestPhases =
                            contestService.getAllContestPhases(contest.getId());
                    ContestPhaseWrapper finalistSelection = null;
                    ContestPhaseWrapper semifinalistSelection = null;
                    Set<ContestPhaseWrapper> proposalCreationPhases = new HashSet<>();

                    for (ContestPhaseWrapper cp : contestPhases) {
                        final Optional<IContestPhaseType> phaseType =
                                contestPhaseTypeDao.get(cp.getContestPhaseTypeId());

                        if (phaseType.isPresent()) {
                            final String status = phaseType.get().getStatus();
                            if (status.equals(STATUS_OPEN_FOR_SUBMISSION)) {
                                proposalCreationPhases.add(cp);
                            } else if (status.equals(STATUS_CLOSED)) {
                                if (finalistSelection == null || cp.getPhaseEndDate()
                                        .after(finalistSelection.getPhaseEndDate())) {
                                    semifinalistSelection = finalistSelection;
                                    finalistSelection = cp;
                                }
                            }
                        }
                    }

                    Set<ProposalWrapper> allProposals = new HashSet<>();
                    if (!proposalCreationPhases.isEmpty()) {
                        for (ContestPhaseWrapper creationPhase : proposalCreationPhases) {
                            final List<ProposalWrapper> proposalsInContestPhase =
                                    StaticProposalContext.getProposalClient()
                                    .getProposalsInContestPhase(creationPhase.getId());
                            addAllVisibleProposalsToCollection(proposalsInContestPhase,
                                    allProposals, creationPhase);
                        }
                    } else {
                        log.error("Can't distribute ribbons: No creation phase found in contest {}",
                                phase.getContestId());
                    }

                    List<ProposalWrapper> finalists = null;
                    List<ProposalWrapper> semifinalists = null;
                    if (finalistSelection != null) {
                        ContestPhaseWrapper finalsPhase =
                                contestPhaseService.getNextContestPhase(finalistSelection);
                        finalists = StaticProposalContext.getProposalClient()
                                .getProposalsInContestPhase(finalsPhase.getId());
                        //make sure all finalists are in the set
                        addAllVisibleProposalsToCollection(finalists, allProposals, finalsPhase);

                        if (semifinalistSelection != null) {
                            ContestPhaseWrapper semifinalsPhase =
                                    contestPhaseService.getNextContestPhase(semifinalistSelection);
                            semifinalists = StaticProposalContext.getProposalClient()
                                    .getProposalsInContestPhase(semifinalsPhase.getId());
                            semifinalists.removeAll(finalists);
                            //make sure all semifinalists are in the set
                            addAllVisibleProposalsToCollection(semifinalists, allProposals,
                                    semifinalsPhase);
                        } else {
                            log.warn("No semifinalist phase found in contest {}",
                                    phase.getContestId());
                        }
                    } else {
                        log.error("Can't distribute ribbons: No finalist phase found in contest {}",
                                phase.getContestId());
                    }

                    promotedProposals += allProposals.size();
                    associateProposalsWithCompletedPhase(allProposals, phase, nextPhase);
                    if (semifinalists != null) {
                        assignRibbonsToProposals(SEMIFINALIST_RIBBON_ID, semifinalists,
                                nextPhase.getId());
                    }
                    if (finalists != null) {
                        assignRibbonsToProposals(FINALIST_RIBBON_ID, finalists,
                                nextPhase.getId());
                    } else {
                        log.warn("No finalists found to distribute ribbons to.");
                    }

                    // We want to wait showing all ribbons until the winners are determined
                    contest.setHideRibbons(true);
                    contestDao.update(contest);

                    // update phase for which promotion was done (mark it as "promotion done")
                    phase.setContestPhaseAutopromote("PROMOTE_DONE");
                    contestPhaseDao.update(phase);


                    log.info("done promoting phase {}", phase.getId());
                } catch (NotFoundException e) {
                    log.error("Exception thrown when doing auto promotion for phase {}",
                            phase.getId(), e);
                }
            }
        }
        return promotedProposals;
    }

    private boolean hasNoValidContest(ContestPhaseWrapper phase) {
        try {
            contestDao.get(phase.getContestId());
        } catch (NotFoundException e) {
            log.warn("promoting phase failed due to invalid contest ", e);
            return true;
        }
        return false;
    }

    private void addAllVisibleProposalsToCollection(Collection<ProposalWrapper> sourceCollection,
            Collection<ProposalWrapper> toCollection, ContestPhaseWrapper inPhase) {
        PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(inPhase);
        for (ProposalWrapper p : sourceCollection) {
            if (!phasePromotionHelper.isProposalVisible(p)) {
                continue;
            }
            toCollection.add(p);
        }
    }

    private void associateProposalsWithCompletedPhase(Set<ProposalWrapper> proposals,
            ContestPhaseWrapper previousPhase, ContestPhaseWrapper completedPhase) throws NotFoundException {

        for (ProposalWrapper proposal : proposals) {
            //update the last phase association - set the end version to the current version
            final long proposalId = proposal.getId();
            Integer currentProposalVersion = StaticProposalContext.getProposalClient()
                    .countProposalVersions(proposalId);
            if (currentProposalVersion <= 0) {
                log.error("Proposal {} not found: version was {}", proposalId,
                        currentProposalVersion);
                throw new NotFoundException("Proposal not found");
            }

            try {
                //make sure that proposals in the phase directly before have final versions
                IProposal2Phase oldP2p = StaticProposalContext.getProposalPhaseClient()
                        .getProposal2PhaseByProposalIdContestPhaseId(proposalId,
                                previousPhase.getId());

                if (oldP2p != null) {
                    if (oldP2p.getVersionTo() < 0) {
                        oldP2p.setVersionTo(currentProposalVersion);
                        StaticProposalContext.getProposalPhaseClient().updateProposal2Phase(oldP2p);
                    }
                }
            } catch (Proposal2PhaseNotFoundException ignored) {
            }

            IProposal2Phase p2p;
            final long completedPhasePK = completedPhase.getId();
            try {
                //This is a workaround for a bug that caused two new p2p's to be created
                p2p = StaticProposalContext.getProposalPhaseClient()
                        .getProposal2PhaseByProposalIdContestPhaseId(proposalId, completedPhasePK);
                //If this succeeds, we want to log the error to help diagnose the problem
                log.error("P2p found while associating proposal {} with phase {}.", proposalId,
                        completedPhasePK);
                new EmailToAdminDispatcher("Duplicate primary key for P2p in auto promotion",
                        String.format("Unexpectedly found p2p. Setting versionFrom = %d and versionTo = %d: %s",
                                currentProposalVersion, currentProposalVersion, p2p), 2,
                        StaticEmailContext.getEmailClient())
                        .sendMessage();
            } catch (Proposal2PhaseNotFoundException e) {
                p2p = new Proposal2Phase();
                p2p.setProposalId(proposalId);
                p2p.setContestPhaseId(completedPhasePK);
                StaticProposalContext.getProposalPhaseClient().createProposal2Phase(p2p);
                log.debug("Created new p2p: {}", p2p);
            }

            p2p.setVersionFrom(currentProposalVersion);
            p2p.setVersionTo(currentProposalVersion);
            StaticProposalContext.getProposalPhaseClient().updateProposal2Phase(p2p);
        }
    }

    private void assignRibbonsToProposals(Long ribbonId, List<ProposalWrapper> proposals, Long phasePK) {
        if (ribbonId > 0) {
            for (ProposalWrapper proposal : proposals) {
                //don't overwrite existing ribbons, as they might be manually assigned winner's ribbons!
                if (!StaticProposalContext.getProposalPhaseClient()
                        .hasProposalContestPhaseAttribute(proposal.getId(), phasePK,
                        ProposalContestPhaseAttributeKeys.RIBBON)) {

                    if (StaticContestContext.getContestClient().getContestPhaseRibbonType(ribbonId) != null) {
                        StaticProposalContext.getProposalPhaseClient().setProposalContestPhaseAttribute(proposal.getId(), phasePK,
                                ProposalContestPhaseAttributeKeys.RIBBON, 0L, ribbonId, "");
                    } else {
                        StaticProposalContext.getProposalPhaseClient().deleteProposalContestPhaseAttribute(proposal.getId(), phasePK,
                                ProposalContestPhaseAttributeKeys.RIBBON);
                    }
                } else {
                    log.error("Skipping ribbon (id = {}) assignment for proposal {}: (already assigned)",
                            ribbonId, proposal.getId());
                }
            }
        }
    }

    private void setNow(Date now) {
        this.now = now;
    }
}
