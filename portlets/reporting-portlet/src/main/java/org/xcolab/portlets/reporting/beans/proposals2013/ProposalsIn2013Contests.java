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
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

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
public class ProposalsIn2013Contests {
    List<ContestPhaseRibbonType> ribbons;

    public ProposalsIn2013Contests() {
        try {
            ribbons = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypes(0, Integer.MAX_VALUE);
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    public List<Proposal2013> get() throws Exception {
        List<Proposal2013> ret = new LinkedList<>();
        List<Contest> targetContests = getContestsIn2013();
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
            List<Proposal> visibleProposals = getVisibleProposals(completedPhase,
                    ProposalLocalServiceUtil.getProposalsInContestPhase(completedPhase.getContestPhasePK()));

            for (Proposal visibleProposal : visibleProposals) {
                Proposal2013 pte = new Proposal2013();


                int targetVersion = getEndVersionOfCreationPhase(creationPhase, visibleProposal);
                /*
                if(targetVersion < -1) {
                    targetVersion = visibleProposal.getCurrentVersion();
                    pte.setUsedVersionInProposalCreation(false);
                }*/

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

    public List<Contest> getContestsIn2013() throws SystemException {
        List<Contest> targetContests = new LinkedList<>();
        for (Contest contest : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if (contest.getContestShortName().endsWith("2013")) {
                targetContests.add(contest);
            }
        }
        return targetContests;
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

    private List<Proposal> getVisibleProposals(ContestPhase creationPhase, List<Proposal> proposals) {
        List<Proposal> targetProposals = new LinkedList<>();
        for (Proposal proposal : proposals) {
            if (!proposal.getVisible()) continue;

            ProposalContestPhaseAttribute proposalContestPhaseAttribute = null;
            try {
                proposalContestPhaseAttribute = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposal.getProposalId(), creationPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.VISIBLE);
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
