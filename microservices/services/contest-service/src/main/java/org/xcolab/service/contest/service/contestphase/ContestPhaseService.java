package org.xcolab.service.contest.service.contestphase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.GroupingHelper;
import org.xcolab.entity.utils.notifications.contest.ContestVoteQuestionNotification;
import org.xcolab.entity.utils.notifications.proposal.ContestVoteNotification;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contest.ContestService;
import org.xcolab.service.contest.utils.promotion.PhasePromotionHelper;
import org.xcolab.service.contest.utils.promotion.enums.ContestStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContestPhaseService {

    private static final Logger _log = LoggerFactory.getLogger(ContestPhaseService.class);

    private static final String COLAB_URL = PlatformAttributeKey.COLAB_URL.get();

    @Autowired
    private ContestService contestService;

    @Autowired
    private ContestPhaseTypeDao contestPhaseTypeDao;

    @Autowired
    private ContestPhaseDao contestPhaseDao;

    @Autowired
    private ContestDao contestDao;

    @Autowired
    private IUserClient userClient;


    public ContestStatus getContestStatus(ContestPhaseWrapper contestPhase) {
        String status = contestPhaseTypeDao.get(contestPhase.getContestPhaseTypeId()).get().getStatus();
        return status == null ? null : ContestStatus.valueOf(status);
    }

    public ContestPhaseWrapper getNextContestPhase(ContestPhaseWrapper contestPhase) throws NotFoundException {
        // First sort by contest phase type (the list has to be initialized as modifiable..)
        List<ContestPhaseWrapper> contestPhases = new ArrayList<>(contestService.getAllContestPhases(contestPhase.getContestId()));
        contestPhases.sort(Comparator.comparing(ContestPhaseWrapper::getPhaseStartDate));

        boolean currentFound = false;
        for (ContestPhaseWrapper phase : contestPhases) {
            if (currentFound) {
                return phase;
            }
            if (phase.getId().longValue() == contestPhase.getId().longValue()) {
                currentFound = true;
            }
        }
        throw new NotFoundException("Can't find next phase for phase with id: " + contestPhase.getId());
    }

    public void transferSupportsToVote(ContestWrapper contest, ContestPhaseWrapper votingPhase) {

//        TODO: this should not be calling the client!
        final List<ProposalWrapper> proposalsInPhase = StaticProposalContext.getProposalClient()
                .getProposalsInContestPhase(votingPhase.getId());
        Set<Long> proposalIdsInPhase = proposalsInPhase.stream()
                .map(ProposalWrapper::getId)
                .collect(Collectors.toSet());

        Map<UserWrapper, Set<ProposalWrapper>> supportedProposalsByMember =
                getSupportedProposalsByMember(contest);
        for (UserWrapper user : supportedProposalsByMember.keySet()) {

            List<ProposalWrapper> supportedProposalsInPhase = supportedProposalsByMember.get(user).stream()
                    .filter(p -> proposalIdsInPhase.contains(p.getId()))
                    .collect(Collectors.toList());

            final Boolean hasVoted = StaticProposalContext.getProposalMemberRatingClient()
                    .hasUserVoted(votingPhase.getId(), user.getId());

            if (hasVoted || supportedProposalsInPhase.isEmpty()) {
                continue;
            }

            //TODO COLAB-2501: we shouldn't use client pojos in the service
            ContestWrapper contestPojo = StaticContestContext.getContestClient()
                    .getContest(contest.getId());
            if (supportedProposalsInPhase.size() == 1) {
                final ProposalWrapper proposal = supportedProposalsInPhase.get(0);
                StaticProposalContext.getProposalMemberRatingClient()
                        .addProposalVote(proposal.getId(), votingPhase.getId(), user.getId(), 1);

                new ContestVoteNotification(user, contestPojo, proposal, COLAB_URL).sendMessage();
            } else {
                new ContestVoteQuestionNotification(user, contestPojo, supportedProposalsInPhase,
                        PlatformAttributeKey.COLAB_URL.get()).sendMessage();
            }
        }
    }

    private Map<UserWrapper, Set<ProposalWrapper>> getSupportedProposalsByMember(ContestWrapper contest) {
        List<ProposalWrapper> proposalsInContest = StaticProposalContext.getProposalClient()
                .getProposalsInContest(contest.getId());

        return new GroupingHelper<>(proposalsInContest).groupWithDuplicateKeysAndValues(
                proposal -> StaticProposalContext.getProposalMemberRatingClient()
                        .getProposalSupporters(proposal.getId()).stream()
                        .map(supporter -> userClient.getMemberUnchecked(supporter.getUserId()))
                        .collect(Collectors.toSet()));
    }

    public void forcePromotionOfProposalInPhase(Long proposalId, Long phaseId) throws NotFoundException {
        ContestPhaseWrapper phase = contestPhaseDao.get(phaseId).get();
        try {
            ProposalWrapper p = StaticProposalContext.getProposalClient().getProposal(proposalId);
            ContestPhaseWrapper nextPhase = getNextContestPhase(phase);
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase);
            //skip already promoted proposal
            if (phasePromotionHelper.isProposalPromoted(p)) {
                return;
            }

            // Decide about the promotion
            if (phasePromotionHelper.didJudgeDecideToPromote(p)) {
                StaticProposalContext.getProposalPhaseClient()
                        .promoteProposal(p.getId(), nextPhase.getId(), phase.getId());
            }

            // Enable this line to post promotion comment to Evaluation tab comment section
            // proposalLocalService.contestPhasePromotionCommentNotifyProposalContributors(p, phase);

            //TODO COLAB-2603: Migrate logic to send email , the method is blank so there may be no need for this
        /*try {
            proposalLocalService.contestPhasePromotionEmailNotifyProposalContributors(p,  phase, null);
        } catch (MailEngineException | AddressException | UnsupportedEncodingException e) {
            _log.error("Could not send proposal promotion colab messaging notification", e);
        }*/

            _log.info("done forcefully promoting proposal {} from phase {}", p.getId(),
                    phase.getId());
        } catch(ProposalNotFoundException pnfe) {
            _log.error("could not find proposal :{} in promotion from phase {}", proposalId,
                    phase.getId());
        }
    }
}
