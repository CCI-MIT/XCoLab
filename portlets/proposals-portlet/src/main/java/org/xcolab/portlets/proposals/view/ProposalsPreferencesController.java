package org.xcolab.portlets.proposals.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.Proposal2PhaseClientUtil;
import org.xcolab.client.proposals.ProposalContestPhaseAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.enums.ContestPhaseTypeValue;
import org.xcolab.mail.ContestPhasePromotionEmail;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.utils.IdListUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

@Controller
@RequestMapping("edit")
public class ProposalsPreferencesController {

    private static final Log _log = LogFactoryUtil.getLog(ProposalsPreferencesController.class);

    private static List<ContestPhase> getPhasesByContest(Contest c, final int sortModifier) {
        List<ContestPhase> contestPhases = ContestClientUtil.getAllContestPhases(c.getContestPK());

        //sort the phases by startdate
        Collections.sort(contestPhases, new Comparator<ContestPhase>() {
            @Override
            public int compare(ContestPhase cp1, ContestPhase cp2) {
                return sortModifier * cp1.getPhaseStartDate().compareTo(cp2.getPhaseStartDate());
            }
        });

        return contestPhases;
    }

    @RequestMapping
    public String showPreferences(RenderRequest request, RenderResponse response, Model model) throws SystemException, PortalException {
        model.addAttribute("prefs", new ProposalsPreferencesWrapper(request));

        //get all contests
        List<Contest> contests = ContestClientUtil.getContestsByActivePrivate(true,true);

        //contestId to contestphases
        Map<Long, ContestPhaseType> contestPhaseTypeMap = new HashMap<>();
        Map<Long, List<ContestPhase>> contestPhasesMap = new HashMap<>();
        //contestphaseId to proposal
        Map<Long, List<ProposalWrapper>> proposalsMap = new HashMap<>();
        for (Contest c : contests) {
            List<ContestPhase> contestPhases = getPhasesByContest(c, 1);

            contestPhasesMap.put(c.getContestPK(), contestPhases);

            for (ContestPhase cp: contestPhases) {
                if (!contestPhaseTypeMap.containsKey(cp.getContestPhaseType())) {
                    contestPhaseTypeMap.put(cp.getContestPhaseType(), ContestClientUtil.getContestPhaseType(cp.getContestPhaseType()));
                }
                List<Proposal> proposals = ProposalClientUtil.getProposalsInContestPhase(cp.getContestPhasePK());
                List<ProposalWrapper> wrappers = new ArrayList<>();
                for (Proposal p : proposals) {
                    wrappers.add(new ProposalWrapper(p));
                }
                proposalsMap.put(cp.getContestPhasePK(), wrappers);
            }
        }

        model.addAttribute("availableRibbons", ContestClientUtil.getAllContestPhaseRibbonType());
        model.addAttribute("contests", contests);
        model.addAttribute("contestPhaseType", contestPhaseTypeMap);
        model.addAttribute("contestPhases", contestPhasesMap);
        model.addAttribute("proposals", proposalsMap);
        model.addAttribute("contestTypes", ContestClientUtil.getAllContestTypes());

        return "preferences";
    }


    @RequestMapping(params = "action=judging")
    public void releaseJudgingMails(ActionRequest request) {
        Integer[] phaseIds = { 1308611,1309131,1309135,1309139,1309143,1309147,1309151,1309155,1309159,1309163,1309167,1309171,1309175,1309179,1309183,1309187,1309191,1309201,1309707  };
        for (Integer phaseId : phaseIds) {
            ContestPhase contestPhase = ContestClientUtil.getContestPhase(phaseId.longValue());
            for (Proposal proposal : ProposalClientUtil.getProposalsInContestPhase(phaseId.longValue())) {
                ContestPhasePromotionEmail.contestPhasePromotionEmailNotifyProposalContributors(proposal, contestPhase, request);
            }
        }
    }

    @RequestMapping(params = "action=save")
    public void savePreferences(ActionRequest request, ActionResponse response, Model model, ProposalsPreferencesWrapper preferences)
            throws ReadOnlyException, ValidatorException, IOException, PortalException {
        //save terms
        preferences.store(request);

        //care about moving proposals
        Long moveToContestPhaseId = preferences.getMoveToContestId();
        Long moveFromContestId = preferences.getMoveFromContestId();
        List<Long> proposalIdsToBeMoved = IdListUtil.getIdsFromString(preferences.getProposalIdsToBeMoved());
        Long ribbonId = preferences.getRibbonId();

        //moving parameters are set
        String message = moveProposals(IdListUtil.PROPOSALS.fromIdList(proposalIdsToBeMoved), moveFromContestId, moveToContestPhaseId, ribbonId, false);
        model.addAttribute("message", message);
    }

    @RequestMapping(params = "action=checkForMissingTeamMembers")
    public void checkForMissingTeamMembers(ActionRequest request, ActionResponse response, Model model)
            throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        List<Contest> activeContests = ContestClientUtil.getContestsByActivePrivate(true, false);
        StringBuilder message = new StringBuilder();

        for (Contest c : activeContests) {
            ContestPhase activePhase = ContestClientUtil.getActivePhase(c.getContestPK());
            if (activePhase == null || activePhase.getContestPhaseType() != 17L) {
                message.append("<br/>\nSkipped contest: ").append(c.getContestShortName()).append("<br/><br/>\n");
                continue;
            }

            message.append("<br/><br/>\nCONTEST: ").append(c.getContestShortName()).append("<br/><br/>\n");

            for (Proposal p : ProposalClientUtil.getProposalsInContest(c.getContestPK())) {
                //author id check
                Long authorId = p.getAuthorId();

                List<Member> members = ProposalClientUtil.getProposalMembers(p.getProposalId());
                boolean foundAuthor = false;
                for (Member u: members) {
                    if (u.getUserId() == authorId) {
                        foundAuthor = true;
                    }
                }
                if (!foundAuthor) {
                    message.append("<br/><br/>\nMISSING AUTHOR ").append(authorId).append(" FOR PROPOSAL: ").append(p.getProposalId()).append("<br/><br/>\n");
                }

                //proposal version check
                boolean warningIssued = false;
                for (ProposalVersion pv: ProposalClientUtil.getAllProposalVersions(p.getProposalId())) {
                    boolean foundVersionAuthor = false;
                    for (Member u: members) {
                        if (u.getUserId() == pv.getAuthorId()) {
                            foundVersionAuthor = true;
                        }
                    }
                    if (!foundVersionAuthor) {
                        if (!warningIssued) {
                            warningIssued = true;
                            message.append("<br/><br/>\nversion author ").append(pv.getAuthorId()).append(" missing for proposal: ").append(p.getProposalId()).append(" Version: ").append(pv.getVersion()).append("<br/>\n");
                        } else {
                            message.append("a:").append(pv.getAuthorId()).append(",v:").append(pv.getVersion()).append(" - ");
                        }
                    }
                }
            }

            model.addAttribute("message", message.toString());
        }
    }

    @RequestMapping(params = "action=runRibbonDistribution")
    public void runRibbonDistribution(ActionRequest request, ActionResponse response, Model model)
            throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        List<Contest> activeContests = ContestClientUtil.getContestsByActivePrivate(true, false);
        StringBuilder message = new StringBuilder();

        for (Contest c : activeContests) {
            ContestPhase activePhase = ContestClientUtil.getActivePhase(c.getContestPK());
            if (activePhase == null || activePhase.getContestPhaseType() != 17L) {
                message.append("<br/>\nSkipped contest: ").append(c.getContestShortName()).append("<br/><br/>\n");
                continue;
            }

            message.append("<br/><br/>\nCONTEST: ").append(c.getContestShortName()).append("<br/><br/>\n");

            //identify the winners selection phase (= contains all finalist) and the finalist selection (= contains all semi-finalist) phase
            List<ContestPhase> contestPhases = ContestClientUtil.getAllContestPhases(c.getContestPK());
            ContestPhase winnersAwarded = null;
            ContestPhase winnersSelection = null;
            ContestPhase finalistSelection = null;
            ContestPhase proposalCreation = null;

            for (ContestPhase cp : contestPhases) {
                switch (ContestPhaseTypeValue.fromTypeId(cp.getContestPhaseType())) {
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
                        _log.warn("Unhandled ContestPhaseType given: " + cp.getContestPhaseType());
                }
            }

            if (winnersAwarded != null && winnersSelection != null && finalistSelection != null && proposalCreation != null) {
                //get all proposals in Winners selection
                List<Proposal> finalists = ProposalClientUtil.getActiveProposalsInContestPhase(winnersSelection.getContestPhasePK());
                List<Proposal> semiFinalists = ProposalClientUtil.getActiveProposalsInContestPhase(finalistSelection.getContestPhasePK());
                List<Proposal> otherProposals = ProposalClientUtil.getActiveProposalsInContestPhase(proposalCreation.getContestPhasePK());

                final Long finalistRibbon = 1L;
                final Long semiFinalistRibbon = 3L;

                message.append(moveProposals(finalists, c.getContestPK(), winnersAwarded.getContestPhasePK(), finalistRibbon, true));
                message.append(moveProposals(semiFinalists, c.getContestPK(), winnersAwarded.getContestPhasePK(), semiFinalistRibbon, false));
                message.append(moveProposals(otherProposals, c.getContestPK(), winnersAwarded.getContestPhasePK(), -1L, false));
            } else {
                message.append("The proposals in this contests were not moved because the contest phases have not been found.<br/>\n");
            }
        }

        //moving parameters are set
        model.addAttribute("message", message.toString());
    }

    private String moveProposals(List<Proposal> proposalsToBeMoved, Long moveFromContestId, Long moveToContestPhaseId, Long ribbonId, boolean forceRibbonCreation) throws PortalException {
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
                        List<Proposal> proposalsInThisPhase = ProposalClientUtil.getProposalsInContestPhase(cp.getContestPhasePK());
                        if (proposalsInThisPhase.contains(proposal)) {
                            //found the last phase
                            lastPhaseContainingProposal = cp;
                            break;
                        }
                    }
                    if (lastPhaseContainingProposal == null) {
                        throw new SystemException("Proposal is not contained in any phases");
                    }

                    //let's make sure that the last phase is before the phase which we move the proposal to!
                    boolean proposalAlreadyInTargetPhase = lastPhaseContainingProposal.getContestPhasePK() == moveToContestPhase.getContestPhasePK() ||
                            lastPhaseContainingProposal.getPhaseEndDate().after(moveToContestPhase.getPhaseStartDate());
                    if (proposalAlreadyInTargetPhase) {
                        //skip this
                        message.append("Proposal ").append(proposal.getProposalId()).append(" is already in the target phase or in a later phase.<br/>\n");
                    } else {
                        //update the last phase association - set the end version to the current version minus one
                        Integer currentProposalVersion = ProposalClientUtil.countProposalVersions(proposal.getProposalId());
                        if (currentProposalVersion < 0) {
                            throw new SystemException("Proposal not found");
                        }
                        try {
                            Proposal2Phase oldP2p = Proposal2PhaseClientUtil.getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(), lastPhaseContainingProposal.getContestPhasePK());

                            assert oldP2p != null;

                            boolean isBoundedVersion = false;
                            if (oldP2p.getVersionTo() < 0) {
                                oldP2p.setVersionTo(currentProposalVersion.intValue());
                                Proposal2PhaseClientUtil.updateProposal2Phase(oldP2p);
                            } else {
                                isBoundedVersion = true;
                            }

                            Proposal2Phase p2p = new Proposal2Phase();
                            p2p.setProposalId(proposal.getProposalId());
                            p2p.setContestPhaseId(moveToContestPhase.getContestPhasePK());
                            p2p.setVersionFrom(currentProposalVersion.intValue());
                            p2p.setVersionTo(isBoundedVersion ? currentProposalVersion.intValue() : -1);

                            Proposal2PhaseClientUtil.createProposal2Phase(p2p);

                            message.append("Proposal ").append(proposal.getProposalId()).append(" moved successfully (version: ").append(currentProposalVersion).append(").<br/>\n");
                        }catch(Proposal2PhaseNotFoundException ignored){

                        }

                    }

                    if (!proposalAlreadyInTargetPhase || forceRibbonCreation) {
                        //create ribbon in target phase
                        if (ribbonId > 0) {

                            //first, see if a ribbon already exists
                            ProposalContestPhaseAttribute attribute = null;

                                attribute = ProposalContestPhaseAttributeClientUtil.getProposalContestPhaseAttribute(proposal.getProposalId(), moveToContestPhase.getContestPhasePK(),
                                        ProposalContestPhaseAttributeKeys.RIBBON);



                            //do not overwrite existing ribbons
                            if (attribute == null) {
                                    ContestClientUtil.getContestPhaseRibbonType(ribbonId);
                                    ProposalContestPhaseAttributeClientUtil.setProposalContestPhaseAttribute(proposal.getProposalId(), moveToContestPhase.getContestPhasePK(),
                                            ProposalContestPhaseAttributeKeys.RIBBON,0l, ribbonId,"");
                            }
                        }
                    }
                }

                message.append("The operation completed successfully!<br/>\n");
            } catch (SystemException|ContestNotFoundException e) {
                _log.warn("Exception thrown while moving proposals", e);
                message.append("There was a problem moving the proposals.<br/>\n");
            }
        }

        return message.toString();
    }
}
