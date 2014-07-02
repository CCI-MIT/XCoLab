package org.xcolab.portlets.reporting.beans.proposaltextextraction;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
    public List<ProposalTextEntity> get() throws Exception {
        List<ProposalTextEntity> ret = new LinkedList<>();
        List<Contest> targetContests = getContestsIn2013();
        for (Contest contest : targetContests) {
            List<ContestPhase> phasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());

            ContestPhase creationPhase = null; //type=1
            ContestPhase finalistPhase = null; //type=12

            for (ContestPhase contestPhase : phasesForContest) {
                if (contestPhase.getContestPhaseType() == 1) creationPhase = contestPhase;
                if (contestPhase.getContestPhaseType() == 12) finalistPhase = contestPhase;
            }

            if (finalistPhase == null || creationPhase == null) continue; //invalid contest
            List<Proposal> visibleProposals = getVisibleProposals(creationPhase,
                    ProposalLocalServiceUtil.getProposalsInContestPhase(creationPhase.getContestPhasePK()));

            List<Proposal> finalistProposals = ProposalLocalServiceUtil.getProposalsInContestPhase(finalistPhase.getContestPhasePK());

            for (Proposal visibleProposal : visibleProposals) {
                ProposalTextEntity pte = new ProposalTextEntity();
                pte.setId(visibleProposal.getProposalId());
                pte.setUrl("http://climatecolab.org/web/guest/plans/-/plans/contestId/" + contest.getContestPK() + "/planId/" + visibleProposal.getProposalId());

                List<ProposalAttribute> attributes = ProposalLocalServiceUtil.getAttributes(visibleProposal.getProposalId(), visibleProposal.getCurrentVersion());
                for (ProposalAttribute attribute : attributes) {
                    if (attributeTypeAllowed(attribute)) {
                        String htmlValue = attribute.getStringValue();
                        if (htmlValue != null && !htmlValue.equals("")) {
                            Document document = Jsoup.parse(htmlValue);
                            String content = document.text();

                            if (content == null || content.equals(""))
                                content = htmlValue;
                            pte.setHtmlElementCount(document.getAllElements().size());
                            pte.setContent(pte.getContent()+"\n"+content);
                        }
                    }
                }

                pte.setFinalist(proposalIsFinalist(finalistProposals, visibleProposal));
                //if(!pte.getContent().equals(""))
                ret.add(pte);
            }
        }
        return ret;
    }

    private boolean attributeTypeAllowed(ProposalAttribute attribute) {
        Set<String> allowedTypes = new HashSet<>();
        for (String s : new String[]{"SECTION", "NAME", "PITCH", "DESCRIPTION"}) {
            allowedTypes.add(s);
        }
        return allowedTypes.contains(attribute.getName());
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

    private boolean proposalIsFinalist(List<Proposal> finalists, Proposal targetProposal) {
        boolean isFinalist = false;
        for (Proposal finalistProposal : finalists) {
            if (finalistProposal.getProposalId() == targetProposal.getProposalId()) {
                isFinalist = true;
                break;
            }
        }
        return isFinalist;
    }
}
