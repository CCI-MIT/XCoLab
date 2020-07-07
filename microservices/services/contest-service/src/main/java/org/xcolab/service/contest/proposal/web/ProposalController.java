package org.xcolab.service.contest.proposal.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.IProposalReference;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.contest.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;
import org.xcolab.service.contest.proposal.domain.proposalrating.ProposalRatingDao;
import org.xcolab.service.contest.proposal.domain.proposalreference.ProposalReferenceDao;
import org.xcolab.service.contest.proposal.domain.proposalversion.ProposalVersionDao;
import org.xcolab.service.contest.proposal.service.proposal.ProposalService;
import org.xcolab.service.contest.proposal.service.proposal.ProposalVersionService;
import org.xcolab.service.contest.proposal.service.proposalreference.ProposalReferenceService;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.util.List;

@RestController
public class ProposalController implements IProposalClient {

    private final ProposalDao proposalDao;
    private final ProposalRatingDao proposalRatingDao;
    private final ProposalVersionDao proposalVersionDao;
    private final ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao;
    private final ProposalReferenceDao proposalReferenceDao;

    private final ProposalService proposalService;
    private final ProposalVersionService proposalVersionService;
    private final ProposalReferenceService proposalReferenceService;

    @Autowired
    public ProposalController(
            ProposalDao proposalDao,
            ProposalRatingDao proposalRatingDao,
            ProposalVersionDao proposalVersionDao,
            ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao,
            ProposalReferenceDao proposalReferenceDao,
            ProposalService proposalService,
            ProposalVersionService proposalVersionService,
            ProposalReferenceService proposalReferenceService) {
        this.proposalDao = proposalDao;
        this.proposalRatingDao = proposalRatingDao;
        this.proposalVersionDao = proposalVersionDao;
        this.proposalContestPhaseAttributeDao = proposalContestPhaseAttributeDao;
        this.proposalReferenceDao = proposalReferenceDao;
        this.proposalService = proposalService;
        this.proposalVersionService = proposalVersionService;
        this.proposalReferenceService = proposalReferenceService;
    }

    @Override
    @GetMapping("/proposals/{proposalId}/listProposalLinks")
    public List<ProposalWrapper> getLinkingProposalsForProposalId(
            @PathVariable(required = false) Long proposalId) {
        return proposalDao.findLinkedProposalIdsByGivenProposalId(proposalId);
    }

    @Override
    @PostMapping("/proposals")
    public ProposalWrapper createProposal(@RequestBody ProposalWrapper proposal) {
        return this.proposalDao.create(proposal);
    }

    @Override
    @GetMapping("/proposals")
    public List<ProposalWrapper> listProposals(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String filterText,
            @RequestParam(required = false) List<Long> contestIds,
            @RequestParam(required = false) List<Long> contestTierIds,
            @RequestParam(required = false) List<Long> contestTypeIds,
            @RequestParam(required = false) Boolean contestActive,
            @RequestParam(required = false) Boolean contestPrivate,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) List<Integer> ribbons,
            @RequestParam(required = false) Long threadId,
            @RequestParam(required = false) String sort) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return proposalDao
                .findByGiven(paginationHelper, filterText, contestIds, visible, contestPhaseId,
                        ribbons, contestTypeIds, contestTierIds, contestActive, contestPrivate,
                        threadId);
    }

    @Override
    @GetMapping("/proposals/{proposalId}")
    public ProposalWrapper getProposal(@PathVariable Long proposalId,
            @RequestParam(required = false, defaultValue = "false") Boolean includeDeleted) {
        try {
            ProposalWrapper proposal = proposalDao.get(proposalId);
            if (proposal.isVisible() || includeDeleted) {
                return proposal;
            }
        } catch (NotFoundException e) {}
        throw new ProposalNotFoundException(proposalId);
    }

    @Override
    @GetMapping("/proposals/{proposalId}/contestIntegrationRelevantSubproposal")
    public List<ProposalWrapper> getContestIntegrationRelevantSubproposals(
            @PathVariable Long proposalId) {
        return proposalService.getContestIntegrationRelevantSubproposals(proposalId);
    }

    @Override
    @GetMapping("/proposals/{proposalId}/latestContestPhaseIdInProposal")
    public Long getLatestContestPhaseIdInProposal(@PathVariable Long proposalId) {
        try {
            return proposalService.getLatestContestPhaseIdInProposal(proposalId);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "Latest contestPhaseId not found for proposal id " + proposalId);
        }
    }

    @Override
    @GetMapping("/proposals/{proposalId}/subproposals")
    public List<ProposalWrapper> getSubproposals(@PathVariable Long proposalId,
            @RequestParam Boolean includeProposalsInSameContest) {
        return proposalService.getSubproposals(proposalId, includeProposalsInSameContest);
    }

    @Override
    @GetMapping("/proposals/memberProposals")
    public List<ProposalWrapper> getMemberProposals(@RequestParam Long userId) {
        return proposalService.getMemberProposals(userId);
    }

    @Override
    @GetMapping("/proposals/{proposalId}/materializedPoints")
    public Integer getProposalMaterializedPoints(@PathVariable Long proposalId) {
        return proposalDao.getProposalMaterializedPoints(proposalId);
    }

    @Override
    @PostMapping("/proposals/createProposal")
    public ProposalWrapper createProposal(@RequestParam Long authorUserId,
            @RequestParam Long contestPhaseId, @RequestParam Boolean publishActivity) {
        return proposalService.create(authorUserId, contestPhaseId, publishActivity);
    }

    @Override
    @PutMapping("/proposals")
    public boolean updateProposal(@RequestBody ProposalWrapper proposal) {
        try {
            proposalDao.get(proposal.getId());
            return proposalDao.update(proposal);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "Proposal not found with id " + proposal.getId());
        }
    }

    @Override
    @DeleteMapping("/proposals/{proposalId}")
    public boolean deleteProposal(@PathVariable Long proposalId) {
        try {
            ProposalWrapper proposal = proposalDao.get(proposalId);
            proposal.setVisible(false);
            return proposalDao.update(proposal);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException("Proposal not found with id " + proposalId);
        }
    }

    @Override
    @GetMapping("/proposals/numberOfProposalsAlreadyJudgedForJudge")
    public int getNumberOfProposalsAlreadyJudgedForJudge(@RequestParam Long contestPhaseId,
            @RequestParam Long userId) {
        PaginationHelper paginationHelper = new PaginationHelper(0, Integer.MAX_VALUE, null);

        List<ProposalWrapper> proposals = proposalDao
                .findByGiven(paginationHelper, null, null, null, contestPhaseId, null, null, null,
                        null, null, null);
        int counter = 0;
        for (ProposalWrapper p : proposals) {
            List<ProposalRatingWrapper> ret = proposalRatingDao
                    .findByProposalIdJudgeTypeJudgeIdContestPhaseId(p.getId(), null,
                            contestPhaseId, userId);
            if (ret != null && !ret.isEmpty()) {
                counter++;
            }
        }

        return counter;
    }

    @Override
    @GetMapping("/proposals/numberOfProposalsForJudge")
    public int getNumberOfProposalsForJudge(@RequestParam Long contestPhaseId,
            @RequestParam Long userId) {
        PaginationHelper paginationHelper = new PaginationHelper(0, Integer.MAX_VALUE, null);

        List<ProposalWrapper> proposals = proposalDao
                .findByGiven(paginationHelper, null, null, null, contestPhaseId, null, null, null,
                        null, null, null);
        int counter = 0;
        for (ProposalWrapper p : proposals) {
            try {
                IProposalContestPhaseAttribute pcpa = proposalContestPhaseAttributeDao
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
            } catch (NotFoundException e) {
                throw new RuntimeEntityNotFoundException(
                        "ProposalContestPhaseAttribute not found for proposal id " + p.getId());
            }
        }
        return counter;
    }

    @Override
    @GetMapping("/proposals/{proposalId}/isUserInProposalTeam")
    public Boolean isUserInProposalTeam(@PathVariable Long proposalId,
            @RequestParam Long memberUserId) {
        return proposalService.isUserAMember(proposalId, memberUserId);
    }

    //TODO: move to proposals/{proposalId}/teamMembers endpoint
    @Override
    @DeleteMapping("/proposals/{proposalId}/removeMemberFromProposalTeam")
    public boolean removeMemberFromProposalTeam(@PathVariable Long proposalId,
            @RequestParam Long userId) {
        proposalService.removeProposalTeamMember(proposalId, userId);

        IActivityClient activityClient = StaticActivityContext.getActivityClient();
        activityClient.createActivityEntry(ProposalActivityType.MEMBER_REMOVED, userId, proposalId);
        return true;
    }

    @Override
    @PostMapping("/proposals/{proposalId}/promoteMemberToProposalOwner")
    public boolean promoteMemberToProposalOwner(@PathVariable Long proposalId,
            @RequestParam Long userId) {
        proposalService.promoteMemberToProposalOwner(proposalId, userId);
        return true;
    }

    @Override
    @GetMapping("/proposals/{proposalId}/allMembers")
    public List<UserWrapper> getProposalMembers(@PathVariable Long proposalId) {
        return proposalService.getProposalMembers(proposalId);
    }

    @Override
    @GetMapping("/proposalVersions/{proposalId}")
    public List<ProposalVersionWrapper> getAllProposalVersions(
            @PathVariable(required = false) Long proposalId) {
        return proposalVersionDao.findByGiven(proposalId, null);
    }

    @Override
    @GetMapping("/proposals/{proposalId}/maxVersion")
    public Integer getMaxVersion(@PathVariable Long proposalId) {
        return proposalVersionDao.findMaxVersion(proposalId);
    }

    @Override
    @GetMapping("/proposalVersions/groupedVersionsByContest")
    public List<ProposalVersionWrapper> getProposalVersionsGroupedVersionsByContest(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Integer start,
            @RequestParam(required = false) Integer end) {
        return proposalVersionService
                .getGroupedProposalVersionsForProposalAndContest(proposalId, contestId, start, end);
    }

    @Override
    @GetMapping("/count/proposalVersions/groupedVersionsByContest")
    public int countProposalVersionsGroupedVersionsByContest(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestId) {
        return proposalVersionService
                .getGroupedProposalVersionsForProposalAndContest(proposalId, contestId, 0,
                        Integer.MAX_VALUE).size();
    }

    @Override
    @GetMapping("/count/proposalVersions")
    public int countProposalVersions(@RequestParam(required = false) Long proposalId) {
        return proposalVersionDao.countByGiven(proposalId);
    }

    @Override
    @GetMapping("/proposalVersions")
    public ProposalVersionWrapper getProposalVersionByProposalIdVersion(
            @RequestParam Long proposalId, @RequestParam(required = false) Integer version) {
        if (proposalId == null || proposalId == 0) {
            throw new RuntimeEntityNotFoundException(
                    "ProposalVersion not found for proposal id " + proposalId);
        }
        return proposalVersionDao.getByProposalIdVersion(proposalId, version);
    }

    @Override
    @GetMapping("/proposalIds")
    public List<Long> listProposalIds(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Integer ribbon) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, null);
        return proposalDao
                .findIdsByGiven(paginationHelper, contestId, visible, contestPhaseId, ribbon);
    }

    @Override
    @GetMapping("proposalThreadIds")
    public List<Long> listThreadIds(@RequestParam(required = false) List<Long> proposalIds,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Integer ribbon) {
        return proposalDao
                .findDiscussionThreadIdsByGiven(proposalIds, contestId, contestPhaseId, ribbon);
    }

    @Override
    @PostMapping("/proposalReferences/populateTableWithProposal")
    public void populateTableWithProposal(@RequestParam Long proposalId) {
        try {
            ProposalWrapper proposal = proposalDao.get(proposalId);
            proposalReferenceService.populateTableWithProposal(proposal);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException("Proposal not found with id " + proposalId);
        }
    }

    @Override
    @GetMapping("/proposalReferences")
    public List<IProposalReference> getProposalReference(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long subProposalId) {
        return proposalReferenceDao.findByGiven(proposalId, subProposalId);
    }
}
