package org.xcolab.portlets.proposals.view;

import com.ext.portlet.NoSuchContestPhaseRibbonTypeException;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("edit")
public class ProposalsPreferencesController {

    private static List<ContestPhase> getPhasesByContest(Contest c, final int sortModifier) {
        List<ContestPhase> contestPhases = ContestLocalServiceUtil.getAllPhases(c);

        //sort the phases by startdate
        Collections.sort(contestPhases, new Comparator<ContestPhase>() {
            public int compare(ContestPhase cp1, ContestPhase cp2) {
                return sortModifier*cp1.getPhaseStartDate().compareTo(cp2.getPhaseStartDate());
            }
        });

        return contestPhases;
    }

    @RequestMapping
    public String showPreferences(RenderRequest request, RenderResponse response, Model model) throws SystemException, PortalException {
        model.addAttribute("prefs", new ProposalsPreferencesWrapper(request));

        //get all contests
        List<Contest> contests = ContestLocalServiceUtil.findByActive(true);

        //contestId to contestphases
        Map<Long, ContestPhaseType> contestPhaseTypeMap = new HashMap<Long, ContestPhaseType>();
        Map<Long, List<ContestPhase>> contestPhasesMap = new HashMap<Long, List<ContestPhase>>();
        //contestphaseId to proposal
        Map<Long, List<ProposalWrapper>> proposalsMap = new HashMap<Long, List<ProposalWrapper>>();
        for (Contest c : contests) {
            List<ContestPhase> contestPhases = getPhasesByContest(c, 1);

            contestPhasesMap.put(c.getContestPK(), contestPhases);

            for (ContestPhase cp: contestPhases) {
                if (!contestPhaseTypeMap.containsKey(cp.getContestPhaseType())) {
                    contestPhaseTypeMap.put(cp.getContestPhaseType(), ContestPhaseTypeLocalServiceUtil.fetchContestPhaseType(cp.getContestPhaseType()));
                }
                List<Proposal> proposals = ProposalLocalServiceUtil.getProposalsInContestPhase(cp.getContestPhasePK());
                List<ProposalWrapper> wrappers = new ArrayList<ProposalWrapper>();
                for (Proposal p : proposals) {
                    wrappers.add(new ProposalWrapper(p));
                }
                proposalsMap.put(cp.getContestPhasePK(), wrappers);
            }
        }

        model.addAttribute("availableRibbons", ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypes(0, Integer.MAX_VALUE));
        model.addAttribute("contests", contests);
        model.addAttribute("contestPhaseType", contestPhaseTypeMap);
        model.addAttribute("contestPhases", contestPhasesMap);
        model.addAttribute("proposals", proposalsMap);


        return "preferences";
    }


    @RequestMapping(params = "action=judging")
    public void releaseJudgingMails(ActionRequest request) throws Exception {
        Integer[] phaseIds = new Integer[] { 1308611,1309131,1309135,1309139,1309143,1309147,1309151,1309155,1309159,1309163,1309167,1309171,1309175,1309179,1309183,1309187,1309191,1309201,1309707  };
        for (Integer phaseId : phaseIds) {
            ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseId);
            for (Proposal proposal : ProposalLocalServiceUtil.getProposalsInContestPhase(phaseId)) {
                ProposalLocalServiceUtil.contestPhasePromotionEmailNotifyProposalContributors(proposal, contestPhase, request);
            }
        }
    }

    @RequestMapping(params = "action=save")
    public void savePreferences(ActionRequest request, ActionResponse response, Model model, ProposalsPreferencesWrapper preferences)
            throws ReadOnlyException, ValidatorException, IOException, PortalException {
        //save terms
        preferences.store(request);

        //care about moving proposals
        Long moveToContestPhaseId = preferences.getMoveToContestPhaseId();
        Long moveFromContestId = preferences.getMoveFromContestId();
        String proposalIdsToBeMovedString = preferences.getProposalIdsToBeMoved();
        Long ribbonId = preferences.getRibbonId();

        String[] proposalIdsToBeMoved = proposalIdsToBeMovedString.split(",");


        //moving parameters are set
        String message = moveProposals(proposalIdsToBeMoved, moveFromContestId, moveToContestPhaseId, ribbonId, false);
        model.addAttribute("message", message);
    }

    @RequestMapping(params = "action=checkForMissingTeamMembers")
    public void checkForMissingTeamMembers(ActionRequest request, ActionResponse response, Model model)
            throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        List<Contest> activeContests = ContestLocalServiceUtil.getContestsByActivePrivate(true, false);
        String message = "";

        for (Contest c : activeContests) {
            if (ContestLocalServiceUtil.getActiveOrLastPhase(c) == null || ContestLocalServiceUtil.getActiveOrLastPhase(c).getContestPhaseType() != 17L) {
                message += "<br/>\nSkipped contest: "+c.getContestShortName()+"<br/><br/>\n";
                continue;
            }

            message += "<br/><br/>\nCONTEST: "+c.getContestShortName()+"<br/><br/>\n";


            for (Proposal p :ProposalLocalServiceUtil.getProposalsInContest(c.getContestPK())) {
                //author id check
                Long authorId = p.getAuthorId();

                List<User> members = ProposalLocalServiceUtil.getMembers(p.getProposalId());
                boolean foundAuthor = false;
                for (User u: members) {
                    if (u.getUserId() == authorId) {
                        foundAuthor = true;
                    }
                }
                if (!foundAuthor) {
                    message += "<br/><br/>\nMISSING AUTHOR "+authorId+" FOR PROPOSAL: "+p.getProposalId()+"<br/><br/>\n";
                }

                //proposal version check
                boolean warningIssued = false;
                for (ProposalVersion pv: ProposalVersionLocalServiceUtil.getByProposalId(p.getProposalId(), 0, Integer.MAX_VALUE)) {
                    boolean foundVersionAuthor = false;
                    for (User u: members) {
                        if (u.getUserId() == pv.getAuthorId()) {
                            foundVersionAuthor = true;
                        }
                    }
                    if (!foundVersionAuthor) {
                        if (!warningIssued) {
                            warningIssued = true;
                            message += "<br/><br/>\nversion author "+pv.getAuthorId()+" missing for proposal: "+p.getProposalId()+" Version: "+pv.getVersion()+"<br/>\n";
                        } else {
                            message += "a:" + pv.getAuthorId() + ",v:" + pv.getVersion() + " - ";
                        }
                    }
                }
            }

            model.addAttribute("message", message);
        }
    }

    @RequestMapping(params = "action=runRibbonDistribution")
    public void runRibbonDistribution(ActionRequest request, ActionResponse response, Model model)
            throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        List<Contest> activeContests = ContestLocalServiceUtil.getContestsByActivePrivate(true, false);
        String message = "";

        for (Contest c : activeContests) {
            if (ContestLocalServiceUtil.getActiveOrLastPhase(c) == null || ContestLocalServiceUtil.getActiveOrLastPhase(c).getContestPhaseType() != 17L) {
                message += "<br/>\nSkipped contest: "+c.getContestShortName()+"<br/><br/>\n";
                continue;
            }

            message += "<br/><br/>\nCONTEST: "+c.getContestShortName()+"<br/><br/>\n";

            //identify the winners selection phase (= contains all finalist) and the finalist selection (= contains all semi-finalist) phase
            List<ContestPhase> contestPhases = ContestLocalServiceUtil.getAllPhases(c);
            ContestPhase winnersAwarded = null;
            ContestPhase winnersSelection = null;
            ContestPhase finalistSelection = null;
            ContestPhase proposalCreation = null;

            for (ContestPhase cp : contestPhases) {
                if (cp.getContestPhaseType() == 17) {
                    winnersAwarded = cp;
                } else if (cp.getContestPhaseType() == 15 || cp.getContestPhaseType() == 20) {
                    winnersSelection = cp;
                } else if (cp.getContestPhaseType() == 19) {
                    finalistSelection = cp;
                } else if (cp.getContestPhaseType() == 1) {
                    proposalCreation = cp;
                }
            }

            if (winnersAwarded != null && winnersSelection != null && finalistSelection != null && proposalCreation != null) {
                //get all proposals in Winners selection
                List<Proposal> finalists = ProposalLocalServiceUtil.getActiveProposalsInContestPhase(winnersSelection.getContestPhasePK());
                List<Proposal> semiFinalists = ProposalLocalServiceUtil.getActiveProposalsInContestPhase(finalistSelection.getContestPhasePK());
                List<Proposal> otherProposals = ProposalLocalServiceUtil.getActiveProposalsInContestPhase(proposalCreation.getContestPhasePK());

                final Long finalistRibbon = 1L;
                final Long semiFinalistRibbon = 3L;

                message += moveProposals(proposalListToStringArray(finalists), c.getContestPK(), winnersAwarded.getContestPhasePK(), finalistRibbon, true);
                message += moveProposals(proposalListToStringArray(semiFinalists), c.getContestPK(), winnersAwarded.getContestPhasePK(), semiFinalistRibbon, false);
                message += moveProposals(proposalListToStringArray(otherProposals), c.getContestPK(), winnersAwarded.getContestPhasePK(), -1L, false);
            } else {
                message += "The proposals in this contests were not moved because the contest phases have not been found.<br/>\n";
            }
        }

        //moving parameters are set
        model.addAttribute("message", message);
    }

    private static String[] proposalListToStringArray(List<Proposal> proposalList) {
        String[] ids = new String[proposalList.size()];
        int i = 0;
        for (Proposal p : proposalList) {
            ids[i++] = String.valueOf(p.getProposalId());
        }
        return ids;
    }

    private String moveProposals(String[] proposalIdsToBeMoved, Long moveFromContestId, Long moveToContestPhaseId, Long ribbonId, boolean forceRibbonCreation) throws PortalException {
        String message = "";
        if (proposalIdsToBeMoved.length > 0 && moveToContestPhaseId > 0 && moveFromContestId > 0) {
            try {
                Contest moveFromContest = ContestLocalServiceUtil.fetchContest(moveFromContestId);
                ContestPhase moveToContestPhase = ContestPhaseLocalServiceUtil.fetchContestPhase(moveToContestPhaseId);

                assert moveFromContest != null;
                assert moveToContestPhase != null;

                //order phases by start phase descending
                List<ContestPhase> contestPhases = getPhasesByContest(moveFromContest, -1);

                //traverse proposals to be moved
                for (String idStr: proposalIdsToBeMoved) {
                    Long id = Long.parseLong(idStr);
                    Proposal proposal = ProposalLocalServiceUtil.fetchProposal(id);

                    assert proposal != null;

                    //find out the last phase the proposal was in.
                    ContestPhase lastPhaseContainingProposal = null;
                    //traverse phases, later phases are first.
                    for (ContestPhase cp: contestPhases) {
                        List<Proposal> proposalsInThisPhase = ProposalLocalServiceUtil.getProposalsInContestPhase(cp.getContestPhasePK());
                        if (proposalsInThisPhase.contains(proposal)) {
                            //found the last phase
                            lastPhaseContainingProposal = cp;
                            break;
                        }
                    }

                    //let's make sure that the last phase is before the phase which we move the proposal to!
                    boolean proposalAlreadyInTargetPhase = lastPhaseContainingProposal.getContestPhasePK() == moveToContestPhase.getContestPhasePK() ||
                            lastPhaseContainingProposal.getPhaseEndDate().after(moveToContestPhase.getPhaseStartDate());
                    if (proposalAlreadyInTargetPhase) {
                        //skip this
                        message += "Proposal "+proposal.getProposalId()+" is already in the target phase or in a later phase.<br/>\n";
                    } else {
                        //update the last phase association - set the end version to the current version minus one
                        Long currentProposalVersion = ProposalVersionLocalServiceUtil.countByProposalId(proposal.getProposalId());
                        if (currentProposalVersion == null || currentProposalVersion < 0) {
                            throw new SystemException("Proposal not found");
                        }

                        Proposal2Phase oldP2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), lastPhaseContainingProposal.getContestPhasePK());

                        assert oldP2p != null;

                        boolean isBoundedVersion = false;
                        if (oldP2p.getVersionTo() < 0) {
                            oldP2p.setVersionTo(currentProposalVersion.intValue());
                            Proposal2PhaseLocalServiceUtil.updateProposal2Phase(oldP2p);
                        } else isBoundedVersion = true;

                        Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.create(proposal.getProposalId(), moveToContestPhase.getContestPhasePK());
                        p2p.setVersionFrom(currentProposalVersion.intValue());
                        p2p.setVersionTo(isBoundedVersion ? currentProposalVersion.intValue() : -1);
                        Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);

                        message += "Proposal "+proposal.getProposalId()+" moved successfully (version: "+currentProposalVersion+").<br/>\n";
                    }

                    if (!proposalAlreadyInTargetPhase || forceRibbonCreation) {
                        //create ribbon in target phase
                        if (ribbonId > 0) {

                            //first, see if a ribbon already exists
                            ProposalContestPhaseAttribute attribute = null;
                            try {
                                attribute = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposal.getProposalId(), moveToContestPhase.getContestPhasePK(),
                                        ProposalContestPhaseAttributeKeys.RIBBON);
                            }
                            catch (NoSuchProposalContestPhaseAttributeException e) {
                            }

                            //do not overwrite existing ribbons
                            if (attribute == null) {
                                try {
                                    ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(ribbonId);
                                    ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(proposal.getProposalId(), moveToContestPhase.getContestPhasePK(),
                                            ProposalContestPhaseAttributeKeys.RIBBON, ribbonId);
                                } catch (NoSuchContestPhaseRibbonTypeException e) {
                                    ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(proposal.getProposalId(), moveToContestPhase.getContestPhasePK(),
                                            ProposalContestPhaseAttributeKeys.RIBBON);
                                }
                            }
                        }
                    }
                }

                message += "The operation completed successfully!<br/>\n";
            } catch (SystemException e) {
                e.printStackTrace();
                message += "There was a problem moving the proposals.<br/>\n";
            }
        }

        return message;
    }
}
