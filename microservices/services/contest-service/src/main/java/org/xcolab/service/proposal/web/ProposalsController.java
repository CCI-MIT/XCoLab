package org.xcolab.service.proposal.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.commons.spring.web.annotation.ListMapping;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.model.tables.pojos.ProposalRating;
import org.xcolab.model.tables.pojos.ProposalVersion;
import org.xcolab.model.tables.pojos.ProposalVote;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;
import org.xcolab.service.proposal.domain.proposalrating.ProposalRatingDao;
import org.xcolab.service.proposal.domain.proposalversion.ProposalVersionDao;
import org.xcolab.service.proposal.domain.proposalvote.ProposalVoteDao;
import org.xcolab.service.proposal.service.proposal.ProposalService;
import org.xcolab.service.proposal.service.proposal.ProposalVersionService;
import org.xcolab.service.proposal.service.proposal2phase.Proposal2PhaseService;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProposalsController {

    private final ProposalDao proposalDao;
    private final ProposalService proposalService;

    private final ProposalVoteDao proposalVoteDao;
    private final Proposal2PhaseService proposal2PhaseService;

    private final ProposalRatingDao proposalRatingDao;

    private final ProposalVersionDao proposalVersionDao;
    private final ProposalVersionService proposalVersionService;
    private final ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao;

    @Autowired
    public ProposalsController(ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao,
            ProposalVersionDao proposalVersionDao, ProposalDao proposalDao,
            ProposalVoteDao proposalVoteDao, Proposal2PhaseService proposal2PhaseService,
            ProposalVersionService proposalVersionService, ProposalService proposalService,
            ProposalRatingDao proposalRatingDao) {
        this.proposalContestPhaseAttributeDao = proposalContestPhaseAttributeDao;
        this.proposalVersionDao = proposalVersionDao;
        this.proposalDao = proposalDao;
        this.proposalVoteDao = proposalVoteDao;
        this.proposal2PhaseService = proposal2PhaseService;
        this.proposalService = proposalService;
        this.proposalVersionService = proposalVersionService;
        this.proposalRatingDao = proposalRatingDao;

    }

    @RequestMapping(value = "/proposals/{proposalId}/listProposalLinks",
            method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Proposal> listProposalLinks(@PathVariable(required = false) Long proposalId) {
        return proposalDao.findLinkedProposalIdsByGivenProposalId(proposalId);
    }

    @PostMapping("/proposals")
    public Proposal createProposal(@RequestBody Proposal proposal) {
        return this.proposalDao.create(proposal);
    }

    @RequestMapping(value = "/proposals", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Proposal> listProposals(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String filterText,
            @RequestParam(required = false) List<Long> contestIds,
            @RequestParam(required = false) List<Long> contestTierIds,
            @RequestParam(required = false) List<Long> contestTypeIds,
            @RequestParam(required = false) Boolean contestActive,
            @RequestParam(required = false) Boolean contestPrivate,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) List<Integer> ribbon,
            @RequestParam(required = false) Long threadId,
            @RequestParam(required = false) String sort) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return proposalDao
                .findByGiven(paginationHelper, filterText, contestIds, visible, contestPhaseId,
                        ribbon, contestTypeIds, contestTierIds, contestActive, contestPrivate,
                        threadId);
    }

    @GetMapping("/proposals/{proposalId}")
    public Proposal getProposal(@PathVariable long proposalId,
            @RequestParam(required = false, defaultValue = "false") boolean includeDeleted)
            throws NotFoundException {
        final Proposal proposal = proposalDao.get(proposalId);
        if (proposal.getVisible() || includeDeleted) {
            return proposal;
        }
        throw new NotFoundException();
    }

    @GetMapping("/proposals/{proposalId}/phaseIds")
    public List<Long> getContestPhasesForProposal(@PathVariable long proposalId) {
        return proposal2PhaseService.getContestPhasesForProposal(proposalId);
    }

    @GetMapping("/proposals/{proposalId}/contestIntegrationRelevantSubproposal")
    public List<Proposal> listProposals(@PathVariable long proposalId) {
        return proposalService.getContestIntegrationRelevantSubproposals(proposalId);
    }

    @GetMapping("/proposals/{proposalId}/getLatestContestPhaseIdInProposal")
    public Long getLatestContestPhaseIdInProposal(@PathVariable long proposalId)
            throws NotFoundException {
        return proposalService.getLatestContestPhaseIdInProposal(proposalId);
    }


    @GetMapping(value = "/proposals/{proposalId}/getSubproposals")
    public List<Proposal> listProposals(@PathVariable long proposalId,
            @RequestParam Boolean includeProposalsInSameContest) {
        return proposalService.getSubproposals(proposalId, includeProposalsInSameContest);
    }

    @GetMapping("/proposals/getMemberProposals")
    public List<Proposal> getMemberProposals(@RequestParam long userId) {
        return proposalService.getMemberProposals(userId);
    }

    @GetMapping(value = "/proposals/{proposalId}/materializedPoints")
    public Integer getMaterializedPoints(@PathVariable long proposalId) {
        return proposalDao.getProposalMaterializedPoints(proposalId);
    }

    @PostMapping(value = "/proposals/createProposal")
    public Proposal createProposal(@RequestParam long authorUserId, @RequestParam long contestPhaseId,
            @RequestParam boolean publishActivity) {
        return proposalService.create(authorUserId, contestPhaseId, publishActivity);
    }

    @PutMapping(value = "/proposals/{proposalId}")
    public boolean updateProposal(@RequestBody Proposal proposal, @PathVariable long proposalId)
            throws NotFoundException {
        proposalDao.get(proposalId);
        return proposalDao.update(proposal);
    }

    @DeleteMapping("/proposals/{proposalId}")
    public boolean deleteProposal(@PathVariable long proposalId) throws NotFoundException {
        final Proposal proposal = proposalDao.get(proposalId);
        proposal.setVisible(false);
        return proposalDao.update(proposal);
    }

    @GetMapping("/proposals/numberOfProposalsAlreadyJudgedForJudge")
    public Integer numberOfProposalsJudgedForJudge(@RequestParam long contestPhaseId,
            @RequestParam long userId) {
        PaginationHelper paginationHelper = new PaginationHelper(0, Integer.MAX_VALUE, null);

        List<Proposal> proposals = proposalDao
                .findByGiven(paginationHelper, null, null, null, contestPhaseId, null, null, null,
                        null, null, null);
        int counter = 0;
        for (Proposal p : proposals) {
            List<ProposalRating> ret = proposalRatingDao
                    .findByProposalIdJudgeTypeJudgeIdContestPhaseId(p.getId(), null,
                            contestPhaseId, userId);
            if (ret != null && !ret.isEmpty()) {
                counter++;
            }
        }

        return counter;
    }

    @GetMapping(value = "/proposals/numberOfProposalsForJudge")
    public Integer numberOfProposalsForJudge(@RequestParam long contestPhaseId,
            @RequestParam long userId) throws NotFoundException {
        PaginationHelper paginationHelper = new PaginationHelper(0, Integer.MAX_VALUE, null);

        List<Proposal> proposals = proposalDao
                .findByGiven(paginationHelper, null, null, null, contestPhaseId, null, null, null,
                        null, null, null);
        int counter = 0;
        for (Proposal p : proposals) {
            ProposalContestPhaseAttribute pcpa = proposalContestPhaseAttributeDao
                    .getByProposalIdContestPhaseIdName(p.getId(), contestPhaseId,
                            ProposalContestPhaseAttributeKeys.SELECTED_JUDGES);
            if (pcpa == null) {
                pcpa = new ProposalContestPhaseAttribute();
                pcpa.setProposalId(p.getId());
                pcpa.setContestPhaseId(contestPhaseId);
                pcpa.setName(ProposalContestPhaseAttributeKeys.SELECTED_JUDGES);
                pcpa.setStringValue("");
                pcpa.setNumericValue(0L);
                pcpa.setRealValue(0.0);
                pcpa.setAdditionalId(0L);
                pcpa = proposalContestPhaseAttributeDao.create(pcpa);
            }
            String judges = pcpa.getStringValue();
            if (StringUtils.containsIgnoreCase(judges, userId + "")) {
                counter++;
            }
        }
        return counter;
    }

    @GetMapping("/proposals/{proposalId}/isUserInProposalTeam")
    public Boolean isUserOnTeam(@PathVariable Long proposalId, @RequestParam Long memberUserId)
            throws NotFoundException {
        return proposalService.isUserAMember(proposalId, memberUserId);
    }

    @DeleteMapping("/proposals/{proposalId}/removeMemberFromProposalTeam")
    public Boolean removeMemberFromProposalTeam(@PathVariable Long proposalId,
            @RequestParam Long userId) throws NotFoundException {

        try {
            proposalService.removeProposalTeamMember(proposalId, userId);
            return true;
        } catch (ProposalNotFoundException ignored) {
            throw new NotFoundException();
        }
    }

    @PostMapping("/proposals/{proposalId}/promoteMemberToProposalOwner")
    public Boolean promoteMemberToProposalOwner(@PathVariable Long proposalId,
            @RequestParam Long userId) throws NotFoundException {

        try {
            proposalService.promoteMemberToProposalOwner(proposalId, userId);
            return true;
        } catch (ProposalNotFoundException ignored) {
            throw new NotFoundException();
        }
    }

    //TODO: this service should not be returning a member!
    @GetMapping("/proposals/{proposalId}/allMembers")
    public List<Member> getProposalMembers(@PathVariable Long proposalId) throws NotFoundException {

        try {
            return proposalService.getProposalMembers(proposalId);
        } catch (ProposalNotFoundException ignored) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/proposalVersions", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalVersion> getProposalVersions(
            @RequestParam(required = false) Long proposalId) {
        return proposalVersionDao.findByGiven(proposalId, null);
    }

    @GetMapping(value = "/proposals/{proposalId}/maxVersion")
    public Integer getMaxVersion(@PathVariable Long proposalId) {
        return proposalVersionDao.findMaxVersion(proposalId);
    }

    @RequestMapping(value = "/proposalVersions/getGroupedVersionsByContest",
            method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalVersion> getGroupedVersionsByContest(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Integer start,
            @RequestParam(required = false) Integer end) {
        return proposalVersionService
                .getGroupedProposalVersionsForProposalAndContest(proposalId, contestId, start, end);
    }

    @RequestMapping(value = "/proposalVersions/countGroupedVersionsByContest",
            method = {RequestMethod.GET, RequestMethod.HEAD})
    public Integer countGroupedVersionsByContest(@RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestId) {
        return proposalVersionService
                .getGroupedProposalVersionsForProposalAndContest(proposalId, contestId, 0,
                        Integer.MAX_VALUE).size();
    }

    @GetMapping("/proposalVersions/count")
    public Integer getProposalVersionsCount(@RequestParam(required = false) Long proposalId) {
        return proposalVersionDao.countByGiven(proposalId);
    }

    @GetMapping(value = "/proposalVersions/getByProposalIdVersion")
    public ProposalVersion getProposalVersion(@RequestParam("proposalId") Long proposalId,
            @RequestParam("version") Integer version) throws NotFoundException {
        if (proposalId == null || proposalId == 0) {
            throw new NotFoundException("No proposalVersion Id given");
        } else {

            return proposalVersionDao.getByProposalIdVersion(proposalId, version);
        }
    }

    @GetMapping("/proposalVotes")
    public List<ProposalVote> getProposalVotes(@RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId) {
        return proposalVoteDao.findByGiven(proposalId, contestPhaseId, userId);
    }


    @GetMapping("/proposalVotes/count")
    public Integer countProposalVotes(@RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Boolean isValidOverride) {
        return proposalVoteDao.countByGiven(proposalId, contestPhaseId, userId, isValidOverride);
    }

    @GetMapping("/proposalVotes/hasUserVoted")
    public Boolean hasUserVoted(@RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId) {
        return proposalVoteDao.countByGiven(proposalId, contestPhaseId, userId, null) != 0;
    }

    @PostMapping("/proposalVotes")
    public ProposalVote createProposalVote(@RequestBody ProposalVote proposalVote) {
        return this.proposalVoteDao.create(proposalVote);
    }

    @DeleteMapping("/proposalVotes/deleteVote")
    public boolean deleteProposalVote(@RequestParam long proposalId,
            @RequestParam long contestPhaseId, @RequestParam long userId) {
        return proposalVoteDao.delete(proposalId, userId, contestPhaseId) > 0;
    }

    @PostMapping("/proposalVotes/updateVote")
    public boolean updateProposalVote(@RequestBody ProposalVote proposalVote) {
        return proposalVoteDao.update(proposalVote);
    }

    @GetMapping("/proposalVotes/getProposalVoteByProposalIdUserId")
    public ProposalVote getProposalVoteByProposalIdUserId(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId) throws NotFoundException {
        List<ProposalVote> votesForUser = proposalVoteDao.findByGiven(proposalId, null, userId);
        if (votesForUser != null && !votesForUser.isEmpty()) {
            return votesForUser.get(0);
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/proposalIds", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Long> listProposalIds(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Integer ribbon,
            @RequestParam(required = false) String sort) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return proposalDao
                .findIdsByGiven(paginationHelper, contestId, visible, contestPhaseId, ribbon);
    }

    @ListMapping("proposalThreadIds")
    public List<Long> listThreadIds(@RequestParam(required = false) List<Long> proposalIds,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Integer ribbon,
            @RequestParam(required = false) Boolean includeResultsDiscussion) {

        final List<Long> threadIds = new ArrayList<>(proposalDao
                .findDiscussionThreadIdsByGiven(proposalIds, contestId, contestPhaseId, ribbon));
        if (includeResultsDiscussion) {
            threadIds.addAll(proposalDao.findResultsDiscussionThreadIds(proposalIds, contestId,
                            contestPhaseId, ribbon));
        }
        return threadIds;
    }
}
