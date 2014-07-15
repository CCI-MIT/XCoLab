package org.xcolab.portlets.reporting.beans.proposaltextextraction;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author pdeboer
 *         First created on 20/06/14 at 15:50
 */
public class ProposalTextExtraction {
    List<ContestPhaseRibbonType> ribbons;

    public ProposalTextExtraction() {
        try {
            ribbons = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypes(0, Integer.MAX_VALUE);
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    public List<ProposalTextEntity> get() throws Exception {
        List<ProposalTextEntity> ret = new LinkedList<>();
        List<Contest> targetContests = getContestsIn2013();
        for (Contest contest : targetContests) {
            List<ContestPhase> phasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());

            ContestPhase completedPhase = null;

            for (ContestPhase contestPhase : phasesForContest) {
                if (contestPhase.getPhaseEndDate() == null) completedPhase = contestPhase;
            }

            if (completedPhase == null) {
                continue; //invalid contest
            }
            List<Proposal> visibleProposals = getVisibleProposals(completedPhase,
                    ProposalLocalServiceUtil.getProposalsInContestPhase(completedPhase.getContestPhasePK()));


            for (Proposal visibleProposal : visibleProposals) {
                ProposalTextEntity pte = new ProposalTextEntity();
                pte.setId(visibleProposal.getProposalId());
                pte.setUrl("http://climatecolab.org/web/guest/plans/-/plans/contestId/" + contest.getContestPK() + "/planId/" + visibleProposal.getProposalId());

                List<ProposalAttribute> attributes = ProposalLocalServiceUtil.getAttributes(visibleProposal.getProposalId(), visibleProposal.getCurrentVersion());
                for (ProposalAttribute attribute : attributes) {
                    if (attributeTypeAllowed(attribute)) {
                        String htmlValue = attribute.getStringValue();
                        if (htmlValue != null && !htmlValue.equals("")) {
                            pte.appendHtml(htmlValue);
                        }
                    }
                }

                pte.setRank(getProposalRank(visibleProposal, completedPhase));
                if (pte.getRank() == ProposalRank.WINNER) {
                    ProposalContestPhaseAttribute ribbonAttribute = getRibbonAttribute(visibleProposal, completedPhase);
                    final int[] judgesChoiceTypes = new int[]{4,5,8,9};
                    final int[] popularChoiceTypes = new int[]{2,5,7,8,9};

                    pte.setProposalWinsJudgesChoice(Arrays.binarySearch(judgesChoiceTypes, (int)ribbonAttribute.getNumericValue()) > -1);
                    pte.setProposalWinsPopularChoice(Arrays.binarySearch(popularChoiceTypes, (int)ribbonAttribute.getNumericValue()) > -1);
                }
                //if(!pte.getContent().equals(""))
                ret.add(pte);
            }
        }
        return ret;
    }

    private List<Contest> getContestsIn2013() throws SystemException {
        List<Contest> targetContests = new LinkedList<>();
        for (Contest contest : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if (contest.getContestShortName().endsWith("2013")) {
                targetContests.add(contest);
            }
        }
        return targetContests;
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

    private boolean attributeTypeAllowed(ProposalAttribute attribute) {
        Set<String> allowedTypes = new HashSet<>();
        for (String s : new String[]{"SECTION", "NAME", "PITCH", "DESCRIPTION"}) {
            allowedTypes.add(s);
        }
        return allowedTypes.contains(attribute.getName());
    }

    private ProposalRank getProposalRank(Proposal proposal, ContestPhase phase) {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = null;
        try {
            proposalContestPhaseAttribute = getRibbonAttribute(proposal, phase);

            ContestPhaseRibbonType contestPhaseRibbonType = getRibbonType(proposalContestPhaseAttribute);

            ProposalRank proposalRank = ProposalRank.fromRibbonType(contestPhaseRibbonType.getRibbon());
            if (proposalRank != null) return proposalRank;

        } catch (Exception e) {
            return ProposalRank.NON_FINALIST;
        }
        return ProposalRank.NON_FINALIST;
    }

    private ProposalContestPhaseAttribute getRibbonAttribute(Proposal proposal, ContestPhase phase) {
        try {
            ProposalContestPhaseAttribute proposalContestPhaseAttribute = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposal.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.RIBBON);
            return proposalContestPhaseAttribute;
        } catch (Exception e) {
            return null;
        }
    }

    private ContestPhaseRibbonType getRibbonType(ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        try {
            for (ContestPhaseRibbonType ribbon : ribbons) {
                if (ribbon.getId() == proposalContestPhaseAttribute.getNumericValue()) {
                    return ribbon;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}
