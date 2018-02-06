package org.xcolab.service.contest.service.contestphase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.notifications.contest.ContestVoteQuestionNotification;
import org.xcolab.entity.utils.notifications.proposal.ContestVoteNotification;
import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contest.ContestService;
import org.xcolab.service.contest.utils.promotion.PhasePromotionHelper;
import org.xcolab.service.contest.utils.promotion.enums.ContestStatus;
import org.xcolab.util.GroupingHelper;

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


    public ContestStatus getContestStatus(ContestPhase contestPhase) {
        String status = contestPhaseTypeDao.get(contestPhase.getContestPhaseType()).get().getStatus();
        return status == null ? null : ContestStatus.valueOf(status);
    }

    public ContestPhase getNextContestPhase(ContestPhase contestPhase) throws NotFoundException {
        // First sort by contest phase type (the list has to be initialized as modifiable..)
        List<ContestPhase> contestPhases = new ArrayList<>(contestService.getAllContestPhases(contestPhase.getContestPK()));
        contestPhases.sort(Comparator.comparing(ContestPhase::getPhaseStartDate));

        boolean currentFound = false;
        for (ContestPhase phase : contestPhases) {
            if (currentFound) {
                return phase;
            }
            if (phase.getContestPhasePK().longValue() == contestPhase.getContestPhasePK().longValue()) {
                currentFound = true;
            }
        }
        throw new NotFoundException("Can't find next phase for phase with id: " + contestPhase.getContestPhasePK());
    }

    public void transferSupportsToVote(Contest contest, ContestPhase votingPhase) {

        final List<Proposal> proposalsInPhase = ProposalClientUtil
                .getProposalsInContestPhase(votingPhase.getContestPhasePK());
        Set<Long> proposalIdsInPhase = proposalsInPhase.stream()
                .map(Proposal::getProposalId)
                .collect(Collectors.toSet());

        Map<Member, Set<Proposal>> supportedProposalsByMember =
                getSupportedProposalsByMember(contest);
        for (Member user : supportedProposalsByMember.keySet()) {

            List<Proposal> supportedProposalsInPhase = supportedProposalsByMember.get(user).stream()
                    .filter(p -> proposalIdsInPhase.contains(p.getProposalId()))
                    .collect(Collectors.toList());

            final Boolean hasVoted = ProposalMemberRatingClientUtil
                    .hasUserVoted(votingPhase.getContestPhasePK(), user.getUserId());

            if (hasVoted || supportedProposalsInPhase.isEmpty()) {
                continue;
            }

            //TODO COLAB-2501: we shouldn't use client pojos in the service
            org.xcolab.client.contest.pojo.Contest contestPojo = ContestClientUtil
                    .getContest(contest.getContestPK());
            if (supportedProposalsInPhase.size() == 1) {
                final Proposal proposal = supportedProposalsInPhase.get(0);
                ProposalMemberRatingClientUtil.addProposalVote(proposal.getProposalId(),
                        votingPhase.getContestPhasePK(), user.getUserId(), 1);

                new ContestVoteNotification(user, contestPojo, proposal, COLAB_URL).sendMessage();
            } else {
                new ContestVoteQuestionNotification(user, contestPojo, supportedProposalsInPhase,
                        PlatformAttributeKey.COLAB_URL.get()).sendMessage();
            }
        }
    }

    private Map<Member, Set<Proposal>> getSupportedProposalsByMember(Contest contest) {
        List<Proposal> proposalsInContest = ProposalClientUtil
                .getProposalsInContest(contest.getContestPK());

        return new GroupingHelper<>(proposalsInContest).groupWithDuplicateKeysAndValues(
                proposal -> ProposalMemberRatingClientUtil
                        .getProposalSupporters(proposal.getProposalId()).stream()
                        .map(supporter -> MembersClient.getMemberUnchecked(supporter.getUserId()))
                        .collect(Collectors.toSet()));
    }

    public void forcePromotionOfProposalInPhase(Long proposalId, Long phaseId) throws NotFoundException {
        ContestPhase phase = contestPhaseDao.get(phaseId).get();
        try {
            Proposal p = ProposalClientUtil.getProposal(proposalId);
            ContestPhase nextPhase = getNextContestPhase(phase);
            PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase);
            //skip already promoted proposal
            if (phasePromotionHelper.isProposalPromoted(p)) {
                return;
            }

            // Decide about the promotion
            if (phasePromotionHelper.didJudgeDecideToPromote(p)) {
                ProposalPhaseClientUtil.promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
            }

            // Enable this line to post promotion comment to Evaluation tab comment section
            // proposalLocalService.contestPhasePromotionCommentNotifyProposalContributors(p, phase);

            //TODO COLAB-2603: Migrate logic to send email , the method is blank so there may be no need for this
        /*try {
            proposalLocalService.contestPhasePromotionEmailNotifyProposalContributors(p,  phase, null);
        } catch (MailEngineException | AddressException | UnsupportedEncodingException e) {
            _log.error("Could not send proposal promotion colab messaging notification", e);
        }*/

            _log.info("done forcefully promoting proposal {} from phase {}", p.getProposalId(),
                    phase.getContestPhasePK());
        } catch(ProposalNotFoundException pnfe) {
            _log.error("could not find proposal :{} in promotion from phase {}", proposalId,
                    phase.getContestPhasePK());
        }
    }


}
