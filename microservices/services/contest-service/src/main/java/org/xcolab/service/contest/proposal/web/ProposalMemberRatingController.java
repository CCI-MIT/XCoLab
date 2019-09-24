package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.contest.pojo.IProposalSupporter;
import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.client.contest.pojo.wrapper.SupportedProposal;
import org.xcolab.client.contest.proposals.IProposalMemberRatingClient;
import org.xcolab.service.contest.proposal.domain.proposalsupporter.ProposalSupporterDao;
import org.xcolab.service.contest.proposal.domain.proposalvote.ProposalVoteDao;
import org.xcolab.service.contest.proposal.service.ProposalSupportService;
import org.xcolab.util.activities.enums.ProposalActivityType;

import java.util.List;

@RestController
public class ProposalMemberRatingController implements IProposalMemberRatingClient {

    private final ProposalVoteDao proposalVoteDao;
    private final ProposalSupporterDao proposalSupporterDao;

    private final ProposalSupportService proposalSupportService;

    @Autowired
    public ProposalMemberRatingController(ProposalVoteDao proposalVoteDao,
            ProposalSupporterDao proposalSupporterDao,
            ProposalSupportService proposalSupportService) {
        this.proposalVoteDao = proposalVoteDao;
        this.proposalSupporterDao = proposalSupporterDao;
        this.proposalSupportService = proposalSupportService;
    }

    @Override
    @GetMapping("/proposalVotes")
    public List<IProposalVote> getProposalVotes(@RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId) {
        return proposalVoteDao.findByGiven(proposalId, contestPhaseId, userId);
    }

    @Override
    @GetMapping("/proposalVotes/count")
    public int countProposalVotes(@RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Boolean isValidOverride) {
        return proposalVoteDao.countByGiven(proposalId, contestPhaseId, userId, isValidOverride);
    }

    @Override
    @GetMapping("/proposalVotes/hasUserVoted")
    public boolean hasUserVoted(@RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId) {
        return proposalVoteDao.countByGiven(proposalId, contestPhaseId, userId, null) != 0;
    }

    @Override
    @PostMapping("/proposalVotes")
    public IProposalVote createProposalVote(@RequestBody IProposalVote proposalVote) {
        return this.proposalVoteDao.create(proposalVote);
    }

    @Override
    @DeleteMapping("/proposalVotes/deleteVote")
    public boolean deleteProposalVote(@RequestParam Long proposalId,
            @RequestParam Long contestPhaseId, @RequestParam Long userId) {
        return proposalVoteDao.delete(proposalId, userId, contestPhaseId) > 0;
    }

    @Override
    @PutMapping("/proposalVotes/updateVote")
    public boolean updateProposalVote(@RequestBody IProposalVote proposalVote) {
        return proposalVoteDao.update(proposalVote);
    }

    @Override
    @GetMapping("/proposalVotes/proposalVoteByProposalIdUserId")
    public IProposalVote getProposalVoteByProposalIdUserId(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId) {
        List<IProposalVote> votesForUser = proposalVoteDao.findByGiven(proposalId, null, userId);
        if (votesForUser != null && !votesForUser.isEmpty()) {
            return votesForUser.get(0);
        }
        return null;
    }

    @GetMapping("/proposalSupporters")
    public List<IProposalSupporter> getProposalSupporters(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId) {
        return proposalSupporterDao.findByGiven(proposalId, userId);
    }

    @Override
    @GetMapping("/supportedProposals")
    public List<IProposalSupporter> getSupportedProposals(@RequestParam Long userId) {
        return proposalSupportService.getSupportedProposalsForUser(userId, true, true);
    }

    @Override
    @RequestMapping("/proposalSupporters/count")
    public int getProposalSupportersCount(@RequestParam Long proposalId) {
        return proposalSupporterDao.countByProposalId(proposalId);
    }

    @Override
    @PostMapping("/proposalSupporters")
    public IProposalSupporter createProposalSupporter(
            @RequestBody IProposalSupporter proposalSupporter) {
        return this.proposalSupporterDao.create(proposalSupporter);
    }

    @Override
    @GetMapping("/proposalSupporters/isMemberProposalSupporter")
    public boolean isMemberProposalSupporter(@RequestParam Long proposalId,
            @RequestParam Long userId) {
        List<IProposalSupporter> ret = proposalSupporterDao.findByGiven(proposalId, userId);
        if (ret != null && ret.size() == 1) {
            return true;
        }
        return false;
    }

    @Override
    @DeleteMapping("/proposalSupporters/deleteProposalSupporter")
    public boolean deleteProposalSupporter(@RequestParam Long proposalId,
            @RequestParam Long userId) {
        this.proposalSupporterDao.delete(proposalId, userId);
        final IActivityClient activitiesClient = StaticActivityContext.getActivityClient();
        activitiesClient
                .createActivityEntry(ProposalActivityType.SUPPORT_REMOVED, userId, proposalId);
        return true;
    }
}
