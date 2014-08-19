package org.xcolab.portlets.proposals.view;

import java.io.IOException;
import java.util.*;

import javax.portlet.*;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;

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


        model.addAttribute("contests", contests);
        model.addAttribute("contestPhaseType", contestPhaseTypeMap);
        model.addAttribute("contestPhases", contestPhasesMap);
        model.addAttribute("proposals", proposalsMap);


        return "preferences";
    }
    

    @RequestMapping(params = "action=save")
    public void showPreferences(ActionRequest request, ActionResponse response, Model model, ProposalsPreferencesWrapper preferences)
            throws ReadOnlyException, ValidatorException, IOException, PortalException {
        //save terms
        preferences.store(request);

        //care about moving proposals
        Long moveToContestPhaseId = preferences.getMoveToContestPhaseId();
        Long moveFromContestId = preferences.getMoveFromContestId();
        String proposalIdsToBeMovedString = preferences.getProposalIdsToBeMoved();

        String[] proposalIdsToBeMoved = proposalIdsToBeMovedString.split(",");
        String message = "";

        //moving parameters are set
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
                    if (lastPhaseContainingProposal.getContestPhasePK() == moveToContestPhase.getContestPhasePK() ||
                             lastPhaseContainingProposal.getPhaseEndDate().after(moveToContestPhase.getPhaseStartDate())) {
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
                }

                model.addAttribute("message", message += "The operation completed successfully!<br/>\n");
            } catch (SystemException e) {
                e.printStackTrace();
                model.addAttribute("message", message += "There was a problem moving the proposals.<br/>\n");
            }
        }
    }
}
