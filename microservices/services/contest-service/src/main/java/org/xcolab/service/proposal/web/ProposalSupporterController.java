package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.model.tables.pojos.ProposalSupporter;
import org.xcolab.service.proposal.domain.proposalsupporter.ProposalSupporterDao;
import org.xcolab.service.proposal.service.ProposalSupportService;
import org.xcolab.service.proposal.service.ProposalSupportService.SupportedProposal;
import org.xcolab.util.activities.enums.ProposalActivityType;

import java.util.List;

@RestController
public class ProposalSupporterController {

    private final ProposalSupporterDao proposalSupporterDao;
    private final ProposalSupportService proposalSupportService;
    private final IActivityClient activityClient;

    @Autowired
    private ProposalSupporterController(ProposalSupporterDao proposalSupporterDao,
            ProposalSupportService proposalSupportService, IActivityClient activityClient) {
        this.proposalSupporterDao = proposalSupporterDao;
        this.proposalSupportService = proposalSupportService;
        this.activityClient = activityClient;
    }

    @RequestMapping(value = "/proposalSupporters", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalSupporter> getProposalSupporters(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId
    ) {
        return proposalSupporterDao.findByGiven(proposalId, userId);
    }

    @RequestMapping(value = "/supportedProposals",
            method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<SupportedProposal> getSupportedProposalsForUser(@RequestParam long userId,
            @RequestParam(defaultValue = "true") boolean onlyVisible,
            @RequestParam(defaultValue = "true") boolean excludePrivateContests) {
        return proposalSupportService.getSupportedProposalsForUser(userId, onlyVisible,
                excludePrivateContests);
    }

    @RequestMapping(value = "/proposalSupporters/count", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Integer getProposalSupportersCount(
            @RequestParam Long proposalId
    ) {

        return proposalSupporterDao.countByProposalId(proposalId);
    }

    @RequestMapping(value = "/proposalSupporters", method = RequestMethod.POST)
    public ProposalSupporter createProposalSupporter(@RequestBody ProposalSupporter proposalSupporter) {
        return this.proposalSupporterDao.create(proposalSupporter);

    }

    @RequestMapping(value = "/proposalSupporters/isMemberProposalSupporter", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Boolean getProposalSupportersCount(
            @RequestParam Long proposalId,
            @RequestParam Long userId
    ) {
        List<ProposalSupporter> ret = proposalSupporterDao.findByGiven(proposalId, userId);

        if (ret != null && ret.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/proposalSupporters/deleteProposalSupporter", method = RequestMethod.DELETE)
    public Boolean deleteProposalSupporter(@RequestParam("proposalId") Long proposalId,
                                           @RequestParam("userId") Long userId) {
        this.proposalSupporterDao.delete(proposalId, userId);
        activityClient.createActivityEntry(ProposalActivityType.SUPPORT_REMOVED, userId,
                proposalId);
        return true;
    }

}
