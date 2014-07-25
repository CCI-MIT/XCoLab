package org.xcolab.portlets.reporting.beans.proposals2013;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.reporting.beans.contests.ContestFetcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author pdeboer
 *         First created on 20/06/14 at 15:50
 */
public class ProposalsInSpecificContests {
    List<ContestPhaseRibbonType> ribbons;

    public ProposalsInSpecificContests() {
        try {
            ribbons = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypes(0, Integer.MAX_VALUE);
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    public List<ProposalWithFinalistAndContent> get() throws Exception {
        return get(ContestFetcher.getContestsIn2013(), false);
    }

    public List<ProposalWithFinalistAndContent> get(List<Contest> targetContests, boolean takeAllProposalsFromCreationPhase) throws Exception {
        List<ProposalWithFinalistAndContent> ret = new LinkedList<>();
        Map<Integer, String> sections = getSectionTypeIDToTextMap();

        for (Contest contest : targetContests) {
            List<ContestPhase> phasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());

            ContestPhase creationPhase = null;
            ContestPhase completedPhase = null;

            for (ContestPhase contestPhase : phasesForContest) {
                if (contestPhase.getPhaseEndDate() == null) completedPhase = contestPhase;
                if (contestPhase.getContestPhaseType() == 1L)
                    creationPhase = contestPhase;
            }

            if (completedPhase == null || creationPhase == null) {
                System.out.println("skipped " + contest);
                continue; //invalid contest
            }
            ContestPhase proposalSourcePhase = takeAllProposalsFromCreationPhase ? creationPhase : completedPhase;
            List<Proposal> visibleProposals = getVisibleProposals(
                    proposalSourcePhase,
                    ProposalLocalServiceUtil.getProposalsInContestPhase(
                            proposalSourcePhase.getContestPhasePK()));

            for (Proposal visibleProposal : visibleProposals) {
                ProposalWithFinalistAndContent pte = new ProposalWithFinalistAndContent();


                int targetVersion = getEndVersionOfCreationPhase(creationPhase, visibleProposal);

                pte.setId(visibleProposal.getProposalId());
                pte.setUrl("http://climatecolab.org/web/guest/plans/-/plans/contestId/" + contest.getContestPK() + "/planId/" + visibleProposal.getProposalId());
                pte.setContestName(contest.getContestShortName());
                pte.setUsedVersion(targetVersion);
                pte.setProposalRibbon(getProposalRibbon(visibleProposal, completedPhase));

                List<ProposalAttribute> attributes = ProposalLocalServiceUtil.getAttributes(visibleProposal.getProposalId(), targetVersion);
                for (ProposalAttribute attribute : attributes) {
                    if (attribute.getName().equals("NAME")) {
                        pte.setProposalName(attribute.getStringValue());
                    }
                    if (attributeTypeAllowed(attribute)) {
                        String htmlValue = attribute.getStringValue();
                        pte.setContent(pte.getContent() + "\n" + htmlValue);
                        String sectionTitle = sections.get((int) attribute.getAdditionalId());
                        if(attribute.getName().equals("DESCRIPTION"))
                            sectionTitle = "Description";
                        else if(attribute.getName().equals("PITCH"))
                            sectionTitle = "Pitch";

                        pte.setContentWithSectionTitles(pte.getContentWithSectionTitles() + "\n<br/><h1>" + sectionTitle + "</h1><br/>\n" + htmlValue);
                    }
                }

                ret.add(pte);
            }
        }
        return ret;
    }

    private Map<Integer, String> getSectionTypeIDToTextMap() throws SystemException {
        Map<Integer, String> sections = new HashMap<>();
        List<PlanSectionDefinition> planSectionDefinitions = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinitions(0, Integer.MAX_VALUE);
        for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            sections.put(
                    (int) planSectionDefinition.getId(), planSectionDefinition.getTitle());
        }
        return sections;
    }

    private List<Proposal> getVisibleProposals(ContestPhase phase, List<Proposal> proposals) {
        List<Proposal> targetProposals = new LinkedList<>();
        for (Proposal proposal : proposals) {
            if (!proposal.getVisible()) continue;

            ProposalContestPhaseAttribute proposalContestPhaseAttribute = null;
            try {
                proposalContestPhaseAttribute = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposal.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.VISIBLE);
                if (proposalContestPhaseAttribute.getNumericValue() == 0) continue;
            } catch (NoSuchProposalContestPhaseAttributeException e) {
                //it's visible then
            } catch (SystemException e) {
                e.printStackTrace();
            }
            targetProposals.add(proposal);
        }
        return targetProposals;
    }

    private int getEndVersionOfCreationPhase(ContestPhase creationPhase, Proposal visibleProposal) throws SystemException, PortalException {
        Proposal2Phase proposal2Phase = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(visibleProposal.getProposalId(), creationPhase.getContestPhasePK());
        return proposal2Phase.getVersionTo();

    }

    private int getProposalRibbon(Proposal proposal, ContestPhase phase) {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = null;
        try {
            proposalContestPhaseAttribute = getRibbonAttribute(proposal, phase);
            return (int) proposalContestPhaseAttribute.getNumericValue();

        } catch (Exception e) {
            return 0;
        }
    }

    private boolean attributeTypeAllowed(ProposalAttribute attribute) {
        Set<String> allowedTypes = new HashSet<>();
        for (String s : new String[]{"SECTION", "PITCH", "DESCRIPTION"}) {
            allowedTypes.add(s);
        }
        return allowedTypes.contains(attribute.getName());
    }

    private ProposalContestPhaseAttribute getRibbonAttribute(Proposal proposal, ContestPhase phase) {
        try {
            ProposalContestPhaseAttribute proposalContestPhaseAttribute = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposal.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.RIBBON);
            return proposalContestPhaseAttribute;
        } catch (Exception e) {
            return null;
        }
    }
}
