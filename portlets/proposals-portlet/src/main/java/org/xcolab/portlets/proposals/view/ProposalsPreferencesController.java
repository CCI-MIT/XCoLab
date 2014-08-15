package org.xcolab.portlets.proposals.view;

import java.io.IOException;
import java.util.*;

import javax.portlet.*;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

        //contest to contestphases
        Map<Long, List<ContestPhase>> contestPhasesMap = new HashMap<Long, List<ContestPhase>>();
        //contestphase to proposal
        Map<Long, List<Proposal>> proposalMap = new HashMap<Long, List<Proposal>>();
        for (Contest c : contests) {
            List<ContestPhase> contestPhases = getPhasesByContest(c, 1);

            contestPhasesMap.put(c.getContestPK(), contestPhases);

            for (ContestPhase cp: contestPhases) {
                proposalMap.put(cp.getContestPhasePK(), ProposalLocalServiceUtil.getProposalsInContestPhase(cp.getContestPhasePK()));
            }
        }

        model.addAttribute("contests", contests);
        model.addAttribute("contestPhases", contestPhasesMap);
        model.addAttribute("proposals", proposalMap);


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
                }

                model.addAttribute("message", "All proposals have been moved successfully!");
            } catch (SystemException e) {
                e.printStackTrace();
                model.addAttribute("message", "There was a problem moving the proposals.");
            }
        }
    }
}
