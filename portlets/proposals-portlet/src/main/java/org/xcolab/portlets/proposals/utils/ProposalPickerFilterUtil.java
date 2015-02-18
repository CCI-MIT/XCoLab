package org.xcolab.portlets.proposals.utils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ext.portlet.model.PlanSectionDefinition;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 04/12/13
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
public enum ProposalPickerFilterUtil {

    // why are lamdba expressions not supported here?
    ACCEPTALL(new ProposalPickerFilter() {
        @Override
        public void filter(List<Pair<Proposal,Date>> proposals, Object additionalFilterCriterion) {
            // do not modify the list
        }

		@Override
		public void filterContests(List<Pair<ContestWrapper, Date>> proposals,
				Object additionalFilterCriterion) {
			// do nothing
		}
    }),
    TEXTBASED(new ProposalPickerFilter() {
        @Override
        public void filter(List<Pair<Proposal,Date>> proposals, Object additionalFilterCriterion) {
            if (!(additionalFilterCriterion instanceof String)) return;
            String searchCriterion = (String) additionalFilterCriterion;
            for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                Proposal p = i.next().getLeft();
                 try{
                     // PROPOSAL NAME
                     String proposalName = ProposalLocalServiceUtil.getAttribute(p.getProposalId(), ProposalAttributeKeys.NAME,0l).getStringValue();
                     if (StringUtils.containsIgnoreCase(proposalName,searchCriterion)){
                         continue;
                     }
                     // CONTEST NAME
                     String contestName = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()).getContestName();
                     if (StringUtils.containsIgnoreCase(contestName,searchCriterion)){
                         continue;
                     }
                     // Remove element if it does not match any criterion
                     i.remove();
                 } catch (Exception e){ /* LR EXCEPTIONS */e.printStackTrace(); }
            }

        }

		@Override
		public void filterContests(List<Pair<ContestWrapper, Date>> contests,
				Object additionalFilterCriterion) {
            if (!(additionalFilterCriterion instanceof String)) return;
            String searchCriterion = (String) additionalFilterCriterion;
            for (Iterator<Pair<ContestWrapper,Date>> i = contests.iterator(); i.hasNext();){
                ContestWrapper c = i.next().getLeft();
                
                 try{
                     // CONTEST NAME
                     if (StringUtils.containsIgnoreCase(c.getContestName(),searchCriterion)){
                         continue;
                     }
                     if (StringUtils.containsIgnoreCase(c.getContestShortName(), searchCriterion)) {
                    	 continue;
                     }
                     // focus area
                     if (StringUtils.containsIgnoreCase(c.getWhatName(), searchCriterion)) {
                    	 continue;
                     }
                     if (StringUtils.containsIgnoreCase(c.getWhoName(), searchCriterion)) {
                    	 continue;
                     }
                     if (StringUtils.containsIgnoreCase(c.getWhereName(), searchCriterion)) {
                    	 continue;
                     }
                     
                     // Remove element if it does not match any criterion
                     i.remove();
                 } catch (Exception e){ /* LR EXCEPTIONS */e.printStackTrace(); }
            }
		}
    }),
    WINNERSONLY(new ProposalPickerFilter() {
        @Override
        public void filter(List<Pair<Proposal, Date>> proposals, Object additionalFilterCriterion) {
            for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                Proposal p = i.next().getLeft();
                try{
                    List<Long> phases = Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(p.getProposalId());
                    boolean winner = false;
                    for (long phase : phases){
                        try{
                            ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(),phase,"RIBBON");
                            winner = true;
                        }catch (NoSuchProposalContestPhaseAttributeException nspcpae){ /* NO WINNER */ }
                    }
                    if (!winner) i.remove();
                } catch (Exception e){ /* LR EXCEPTIONS */e.printStackTrace(); }
            }
        }

		@Override
		public void filterContests(List<Pair<ContestWrapper, Date>> proposals,
				Object additionalFilterCriterion) {
			// do nothing
			
		}
    }),
    ONTOLOGY(new ProposalPickerFilter() {
        @Override
        public void filter(List<Pair<Proposal, Date>> proposals, Object additionalFilterCriterion) {
            try{
                long definitionId = (Long) additionalFilterCriterion;
                // FocusArea
                PlanSectionDefinition planSectionDefinition = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(definitionId);
                FocusArea fa = PlanSectionDefinitionLocalServiceUtil.getFocusArea(planSectionDefinition);
                // List of terms in this area

                if (fa != null) {
                	List<OntologyTerm> terms = FocusAreaLocalServiceUtil.getTerms(fa);
                    List<Contest> contests = ContestLocalServiceUtil.getContestsMatchingOntologyTerms(terms);

                    for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                        Proposal p = i.next().getLeft();
                        if (!contests.contains(Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()))) i.remove();
                    }
                }
                // Filter by contest tier
                if (ContestTier.getContestTierByTierType(planSectionDefinition.getTier()) != ContestTier.NONE) {
                    ContestTier contestTier = ContestTier.getContestTierByTierType(planSectionDefinition.getTier());
                    List<Contest> tierFilteredContests = ContestLocalServiceUtil.getContestsMatchingTier(contestTier.getTierType());

                    for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                        Proposal p = i.next().getLeft();
                        if (!tierFilteredContests.contains(Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()))) i.remove();
                    }
                }
            } catch (Exception e){ /* LR EXCEPTIONS */ e.printStackTrace(); }
        }

		@Override
		public void filterContests(List<Pair<ContestWrapper, Date>> proposals,
				Object additionalFilterCriterion) {
			// do nothing
			
		}
    });

    private final ProposalPickerFilter proposalPickerFilter;

    private ProposalPickerFilterUtil(ProposalPickerFilter proposalPickerFilter) {
        this.proposalPickerFilter = proposalPickerFilter;
    }

    public ProposalPickerFilter getProposalPickerFilter() {
        return proposalPickerFilter;
    }

    // CONVENIENCE METHODS
    public void filter(List<Pair<Proposal,Date>> proposals){
        this.getProposalPickerFilter().filter(proposals, null);
    }

    public void filter(List<Pair<Proposal,Date>> proposals, Object additionalFilterCriterion){
        this.getProposalPickerFilter().filter(proposals, additionalFilterCriterion);
    }

    // PARSE FILTER FROM FRONT END PARAMETER
    public static ProposalPickerFilterUtil getFilterByParameter(String filterKey) {
        if (filterKey == null) return ACCEPTALL;
        if (filterKey.equalsIgnoreCase("WINNERSONLY")) return WINNERSONLY;
        return ACCEPTALL;
    }

	public void filterContests(List<Pair<ContestWrapper, Date>> contests,
			String filterText) {
        this.getProposalPickerFilter().filterContests(contests, filterText);
		
	}

}
