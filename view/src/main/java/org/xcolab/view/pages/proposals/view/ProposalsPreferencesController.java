package org.xcolab.view.pages.proposals.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.commons.IdListUtil;
import org.xcolab.util.enums.contest.ContestPhaseTypeValue;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.view.util.entity.EntityIdListUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.widgets.AbstractWidgetController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(ProposalsPreferencesController.BASE_URL)
public class ProposalsPreferencesController extends AbstractWidgetController<ProposalsPreferencesWrapper> {

    private static final Logger _log = LoggerFactory.getLogger(ProposalsPreferencesController.class);

    public static final String BASE_URL = "/proposals";

    protected ProposalsPreferencesController() {
        super(BASE_URL, ProposalsPreferencesWrapper::new);
    }

    private static List<ContestPhase> getPhasesByContest(Contest c, final int sortModifier) {
        List<ContestPhase> contestPhases = ContestClientUtil.getAllContestPhases(c.getId());

        //sort the phases by startdate
        contestPhases.sort((cp1, cp2) ->
                sortModifier * cp1.getPhaseStartDate().compareTo(cp2.getPhaseStartDate()));

        return contestPhases;
    }

    @GetMapping(PREFERENCES_URL_PATH)
    public String showPreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, ProposalContext proposalContext,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {

        //get all contests
        List<Contest> contests = ContestClientUtil.getContestsByActivePrivate(true,true);

        //contestId to contestphases
        Map<Long, ContestPhaseType> contestPhaseTypeMap = new HashMap<>();
        Map<Long, List<ContestPhase>> contestPhasesMap = new HashMap<>();
        //contestphaseId to proposal
        Map<Long, List<Proposal>> proposalsMap = new HashMap<>();
        for (Contest c : contests) {
            List<ContestPhase> contestPhases = getPhasesByContest(c, 1);

            contestPhasesMap.put(c.getId(), contestPhases);

            for (ContestPhase cp: contestPhases) {
                if (!contestPhaseTypeMap.containsKey(cp.getContestPhaseTypeId())) {
                    contestPhaseTypeMap.put(cp.getContestPhaseTypeId(), ContestClientUtil.getContestPhaseType(cp.getContestPhaseTypeId()));
                }
                List<Proposal> proposals = proposalContext.getClients().getProposalClient().getProposalsInContestPhase(cp.getId());
                List<Proposal> wrappers = new ArrayList<>();
                wrappers.addAll(proposals);
                proposalsMap.put(cp.getId(), wrappers);
            }
        }

        model.addAttribute("availableRibbons", ContestClientUtil.getAllContestPhaseRibbonType());
        model.addAttribute("contests", contests);
        model.addAttribute("contestPhaseType", contestPhaseTypeMap);
        model.addAttribute("contestPhases", contestPhasesMap);
        model.addAttribute("proposals", proposalsMap);
        model.addAttribute("contestTypes", ContestTypeClient.getAllContestTypes());

        return showPreferencesInternal(response, model, member, preferenceId, language,
                "proposals/editPreferences");
    }


//    TODO COLAB-2603: this wasn't active - do we still need that?
    //-- @RequestMapping(params = "action=judging")
//    public void releaseJudgingMails(HttpServletRequest request) {
//        Integer[] phaseIds = { 1308611,1309131,1309135,1309139,1309143,1309147,1309151,1309155,1309159,1309163,1309167,1309171,1309175,1309179,1309183,1309187,1309191,1309201,1309707  };
//        for (Integer phaseId : phaseIds) {
//            ContestPhase contestPhase = ContestClientUtil.getContestPhase(phaseId.longValue());
//            for (Proposal proposal : proposalContext.getClients().getProposalClient().getProposalsInContestPhase(phaseId.longValue())) {
//                ContestPhasePromotionEmail.contestPhasePromotionEmailNotifyProposalContributors(proposal, contestPhase);
//            }
//        }
//    }


    @PostMapping(PREFERENCES_URL_PATH)
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, ProposalContext proposalContext, ProposalsPreferencesWrapper preferences) {

        //care about moving proposals
        Long moveToContestPhaseId = preferences.getMoveToContestId();
        Long moveFromContestId = preferences.getMoveFromContestId();
        List<Long> proposalIdsToBeMoved = IdListUtil.getIdsFromString(preferences.getProposalIdsToBeMoved());
        Long ribbonId = preferences.getRibbonId();

        //moving parameters are set
        moveProposals(proposalContext,
                EntityIdListUtil.PROPOSALS.fromIdList(proposalIdsToBeMoved), moveFromContestId,
                moveToContestPhaseId, ribbonId, false);

        return savePreferencesInternal(request, response, member, preferences);
    }

    //-- @RequestMapping(params = "action=checkForMissingTeamMembers")
    @PostMapping("checkForMissingTeamMembers")
    public void checkForMissingTeamMembers(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext)
            throws  IOException {
        List<Contest> activeContests = ContestClientUtil.getContestsByActivePrivate(true, false);
        StringBuilder message = new StringBuilder();

        for (Contest c : activeContests) {
            ContestPhase activePhase = ContestClientUtil.getActivePhase(c.getId());
            if (activePhase == null || activePhase.getContestPhaseTypeId() != 17L) {
                message.append("<br/>\nSkipped contest: ").append(c.getTitle()).append("<br/><br/>\n");
                continue;
            }

            message.append("<br/><br/>\nCONTEST: ").append(c.getTitle()).append("<br/><br/>\n");

            for (Proposal p : proposalContext.getClients().getProposalClient().getProposalsInContest(c.getId())) {
                //author id check
                Long authorUserId = p.getAuthorUserId();

                List<Member> members = proposalContext.getClients().getProposalClient().getProposalMembers(p.getId());
                boolean foundAuthor = false;
                for (Member u: members) {
                    if (u.getId() == authorUserId) {
                        foundAuthor = true;
                    }
                }
                if (!foundAuthor) {
                    message.append("<br/><br/>\nMISSING AUTHOR ").append(authorUserId).append(" FOR PROPOSAL: ").append(p.getId()).append("<br/><br/>\n");
                }

                //proposal version check
                boolean warningIssued = false;
                for (ProposalVersion pv: proposalContext.getClients().getProposalClient().getAllProposalVersions(p.getId())) {
                    boolean foundVersionAuthor = false;
                    for (Member u: members) {
                        if (u.getId() == pv.getAuthorUserId()) {
                            foundVersionAuthor = true;
                        }
                    }
                    if (!foundVersionAuthor) {
                        if (!warningIssued) {
                            warningIssued = true;
                            message.append("<br/><br/>\nversion author ").append(pv.getAuthorUserId()).append(" missing for proposal: ").append(p.getId()).append(" Version: ").append(pv.getVersion()).append("<br/>\n");
                        } else {
                            message.append("a:").append(pv.getAuthorUserId()).append(",v:").append(pv.getVersion()).append(" - ");
                        }
                    }
                }
            }


            AlertMessage.success(message.toString()).flash(request);
        }


        response.sendRedirect("/proposals/preferences");
    }


    @PostMapping("runRibbonDistribution")
    public void runRibbonDistribution(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext)
            throws  IOException {
        List<Contest> activeContests = ContestClientUtil.getContestsByActivePrivate(true, false);
        StringBuilder message = new StringBuilder();

        for (Contest c : activeContests) {
            ContestPhase activePhase = ContestClientUtil.getActivePhase(c.getId());
            if (activePhase == null || activePhase.getContestPhaseTypeId() != 17L) {
                message.append("<br/>\nSkipped contest: ").append(c.getTitleWithEndYear()).append("<br/><br/>\n");
                continue;
            }

            message.append("<br/><br/>\nCONTEST: ").append(c.getTitleWithEndYear()).append("<br/><br/>\n");

            //identify the winners selection phase (= contains all finalist) and the finalist selection (= contains all semi-finalist) phase
            List<ContestPhase> contestPhases = ContestClientUtil.getAllContestPhases(c.getId());
            ContestPhase winnersAwarded = null;
            ContestPhase winnersSelection = null;
            ContestPhase finalistSelection = null;
            ContestPhase proposalCreation = null;

            for (ContestPhase cp : contestPhases) {
                switch (ContestPhaseTypeValue.fromTypeId(cp.getContestPhaseTypeId())) {
                    case WINNERS_AWARDED:
                        winnersAwarded = cp;
                        break;
                    case WINNERS_SELECTION:
                    case SELECTION_OF_WINNERS:
                        winnersSelection = cp;
                        break;
                    case FINALIST_SELECTION_NEW:
                        finalistSelection = cp;
                        break;
                    case PROPOSAL_CREATION:
                        proposalCreation = cp;
                        break;
                    default:
                        _log.warn("Unhandled ContestPhaseType given: {}", cp.getContestPhaseTypeId());
                }
            }

            if (winnersAwarded != null && winnersSelection != null && finalistSelection != null && proposalCreation != null) {
                //get all proposals in Winners selection
                List<Proposal> finalists = proposalContext.getClients().getProposalClient().getActiveProposalsInContestPhase(winnersSelection.getId());
                List<Proposal> semiFinalists = proposalContext.getClients().getProposalClient().getActiveProposalsInContestPhase(finalistSelection.getId());
                List<Proposal> otherProposals = proposalContext.getClients().getProposalClient().getActiveProposalsInContestPhase(proposalCreation.getId());

                final Long finalistRibbon = 1L;
                final Long semiFinalistRibbon = 3L;

                message.append(moveProposals(proposalContext, finalists, c.getId(), winnersAwarded.getId(), finalistRibbon, true));
                message.append(moveProposals(proposalContext, semiFinalists, c.getId(), winnersAwarded.getId(), semiFinalistRibbon, false));
                message.append(moveProposals(proposalContext, otherProposals, c.getId(), winnersAwarded.getId(), -1L, false));
            } else {
                message.append("The proposals in this contests were not moved because the contest phases have not been found.<br/>\n");
            }
        }

        //moving parameters are set
        //model.addAttribute("message", message.toString());
        AlertMessage.success(message.toString()).flash(request);
        response.sendRedirect("/proposals/preferences");
    }

    private String moveProposals(ProposalContext proposalContext, List<Proposal> proposalsToBeMoved,
            Long moveFromContestId, Long moveToContestPhaseId, Long ribbonId,
            boolean forceRibbonCreation) {
        StringBuilder message = new StringBuilder();
        if (!proposalsToBeMoved.isEmpty() && moveToContestPhaseId > 0 && moveFromContestId > 0) {
            try {
                Contest moveFromContest = ContestClientUtil.getContest(moveFromContestId);
                ContestPhase moveToContestPhase = ContestClientUtil.getContestPhase(moveToContestPhaseId);

                assert moveFromContest != null;
                assert moveToContestPhase != null;

                //order phases by start phase descending
                List<ContestPhase> contestPhases = getPhasesByContest(moveFromContest, -1);

                //traverse proposals to be moved
                for (Proposal proposal : proposalsToBeMoved) {
                    //find out the last phase the proposal was in.
                    ContestPhase lastPhaseContainingProposal = null;
                    //traverse phases, later phases are first.
                    for (ContestPhase cp: contestPhases) {
                        List<Proposal> proposalsInThisPhase = proposalContext.getClients().getProposalClient().getProposalsInContestPhase(cp.getId());
                        if (proposalsInThisPhase.contains(proposal)) {
                            //found the last phase
                            lastPhaseContainingProposal = cp;
                            break;
                        }
                    }
                    if (lastPhaseContainingProposal == null) {
                        throw new IllegalStateException("Proposal is not contained in any phases");
                    }

                    //let's make sure that the last phase is before the phase which we move the proposal to!
                    boolean proposalAlreadyInTargetPhase = lastPhaseContainingProposal.getId()
                            == moveToContestPhase.getId().longValue()
                            || lastPhaseContainingProposal.getPhaseEndDate()
                                .after(moveToContestPhase.getPhaseStartDate());
                    if (proposalAlreadyInTargetPhase) {
                        //skip this
                        message.append("Proposal ").append(proposal.getId()).append(" is already in the target phase or in a later phase.<br/>\n");
                    } else {
                        //update the last phase association - set the end version to the current version minus one
                        Integer currentProposalVersion = proposalContext.getClients().getProposalClient().countProposalVersions(proposal.getId());
                        if (currentProposalVersion < 0) {
                            throw new IllegalStateException("Proposal not found");
                        }
                        try {
                            Proposal2Phase oldP2p = proposalContext.getClients().getProposalPhaseClient().getProposal2PhaseByProposalIdContestPhaseId(proposal.getId(), lastPhaseContainingProposal.getId());

                            assert oldP2p != null;

                            boolean isBoundedVersion = false;
                            if (oldP2p.getVersionTo() < 0) {
                                oldP2p.setVersionTo(currentProposalVersion);
                                proposalContext.getClients().getProposalPhaseClient().updateProposal2Phase(oldP2p);
                            } else {
                                isBoundedVersion = true;
                            }

                            Proposal2Phase p2p = new Proposal2Phase();
                            p2p.setProposalId(proposal.getId());
                            p2p.setContestPhaseId(moveToContestPhase.getId());
                            p2p.setVersionFrom(currentProposalVersion);
                            p2p.setVersionTo(isBoundedVersion ? currentProposalVersion : -1);

                            proposalContext.getClients().getProposalPhaseClient().createProposal2Phase(p2p);

                            message.append("Proposal ").append(proposal.getId()).append(" moved successfully (version: ").append(currentProposalVersion).append(").<br/>\n");
                        }catch(Proposal2PhaseNotFoundException ignored){

                        }

                    }

                    if (!proposalAlreadyInTargetPhase || forceRibbonCreation) {
                        //create ribbon in target phase
                        if (ribbonId > 0) {

                            //first, see if a ribbon already exists

                            ProposalContestPhaseAttribute attribute = ProposalPhaseClientUtil
                                    .getProposalContestPhaseAttribute(proposal.getId(),
                                            moveToContestPhase.getId(),
                                            ProposalContestPhaseAttributeKeys.RIBBON);


                            //do not overwrite existing ribbons
                            if (attribute == null) {
                                ContestClientUtil.getContestPhaseRibbonType(ribbonId);
                                proposalContext.getClients().getProposalPhaseClient().setProposalContestPhaseAttribute(
                                            proposal.getId(), moveToContestPhase.getId(),
                                            ProposalContestPhaseAttributeKeys.RIBBON, 0L, ribbonId,"");
                            }
                        }
                    }
                }

                message.append("The operation completed successfully!<br/>\n");
            } catch (ContestNotFoundException e) {
                _log.warn("Exception thrown while moving proposals", e);
                message.append("There was a problem moving the proposals.<br/>\n");
            }
        }

        return message.toString();
    }
}
