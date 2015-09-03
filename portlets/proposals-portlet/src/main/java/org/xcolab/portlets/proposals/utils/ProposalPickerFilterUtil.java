package org.xcolab.portlets.proposals.utils;

import java.util.*;

import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.collections.CollectionUtils;
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
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 04/12/13
 * Time: 10:25
 */
public class ProposalPickerFilterUtil {

    public static ProposalPickerFilter ACCEPTALL = new ProposalPickerFilter();

    public static ProposalPickerFilter TEXTBASED = new ProposalPickerFilter() {
        @Override
        public Set<Long> filter(List<Pair<Proposal,Date>> proposals, Object additionalFilterCriterion) {
            Set<Long> removedProposals = new HashSet<>();
            if (!(additionalFilterCriterion instanceof String)) return removedProposals;
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
                     removedProposals.add(p.getProposalId());
                     i.remove();
                 } catch (SystemException | PortalException e){ /* LR EXCEPTIONS */
                     e.printStackTrace();
                 }
            }
            return removedProposals;
        }

		@Override
		public Set<Long> filterContests(List<Pair<ContestWrapper, Date>> contests,
				Object additionalFilterCriterion) {
            Set<Long> removedContests = new HashSet<>();
            if (!(additionalFilterCriterion instanceof String)) return removedContests;
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
                     removedContests.add(c.getContestPK());
                     i.remove();
                 } catch (SystemException | PortalException e){ /* LR EXCEPTIONS */e.printStackTrace(); }
            }
            return removedContests;
		}
    };

    public static ProposalPickerFilter WINNERSONLY= new ProposalPickerFilter() {
        @Override
        public Set<Long> filter(List<Pair<Proposal, Date>> proposals, Object additionalFilterCriterion) {
            Set<Long> removedProposals = new HashSet<>();
            for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                Proposal p = i.next().getLeft();
                try{
                    List<Long> phases = Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(p.getProposalId());
                    boolean winner = false;
                    for (long phase : phases){
                        try {
                            ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(),phase,"RIBBON");
                            winner = true;
                        } catch (NoSuchProposalContestPhaseAttributeException nspcpae){ /* NO WINNER */ }
                    }
                    if (!winner) {
                        removedProposals.add(p.getProposalId());
                        i.remove();
                    }
                } catch (SystemException | PortalException e){ /* LR EXCEPTIONS */e.printStackTrace(); }
            }
            return removedProposals;
        }
    };

    public static ProposalPickerFilter SECTION_DEF_FOCUS_AREA_FILTER = new ProposalPickerFilter() {
        @Override
        public Set<Long> filter(List<Pair<Proposal, Date>> proposals, Object additionalFilterCriterion) {
            Set<Long> removedProposals = new HashSet<>();
            try {
                List<OntologyTerm> terms = getOntologyTermsFromSectionAndContest((Pair) additionalFilterCriterion);

                if (terms.size() > 0) {
                    List<Contest> contests = ContestLocalServiceUtil.getContestsMatchingOntologyTerms(terms);
                    for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                        Proposal p = i.next().getLeft();
                        try {
                            if (!contests.contains(Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()))) {
                                removedProposals.add(p.getProposalId());
                                i.remove();
                            }
                        } catch (Exception e){
                            removedProposals.add(p.getProposalId());
                            i.remove();
                        }
                    }
                }
            } catch (SystemException | PortalException e){ /* LR EXCEPTIONS */
                e.printStackTrace();
            }
            return removedProposals;
        }

		@Override
		public Set<Long> filterContests(List<Pair<ContestWrapper, Date>> contests,
				Object additionalFilterCriterion) {
            Set<Long> removedContests = new HashSet<>();
            try {
                List<OntologyTerm> requiredTerms = getOntologyTermsFromSectionAndContest((Pair) additionalFilterCriterion);
                for (Iterator<Pair<ContestWrapper,Date>> i = contests.iterator(); i.hasNext();){
                    ContestWrapper contest = i.next().getLeft();
                    try {
                        FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(contest.getFocusAreaId());
                        List<OntologyTerm> contestTerms = FocusAreaLocalServiceUtil.getTerms(focusArea);
                        for (OntologyTerm requiredTerm : requiredTerms) {
                            List<OntologyTerm> requiredDescendantTerms = OntologyTermLocalServiceUtil.getAllDescendantTerms(requiredTerm);
                            requiredDescendantTerms.add(requiredTerm);
                            if (!CollectionUtils.containsAny(requiredDescendantTerms, contestTerms)) {
                                removedContests.add(contest.getContestPK());
                                i.remove();
                            }
                        }
                    } catch (Exception e){
                        removedContests.add(contest.getContestPK());
                        i.remove();
                    }
                }
            } catch (SystemException | PortalException e) { /* LR EXCEPTIONS */
                e.printStackTrace();
            }

            return removedContests;
        }

        private List<OntologyTerm> getOntologyTermsFromSectionAndContest(Pair focusAreaIds) throws SystemException, PortalException {
            Long focusAreaId = (Long) focusAreaIds.getLeft();
            Long contestFocusAreaId;
            if (focusAreaId < 0) {
                contestFocusAreaId = (Long) focusAreaIds.getRight();
                focusAreaId = Math.abs(focusAreaId);
            } else {
                contestFocusAreaId = 0L;
            }
            List<OntologyTerm> terms = new ArrayList<>();

            FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(focusAreaId);
            FocusArea contestFocusArea = FocusAreaLocalServiceUtil.getFocusArea(contestFocusAreaId);
            if (focusArea != null) {
                terms.addAll(FocusAreaLocalServiceUtil.getTerms(focusArea));
            }
            if (contestFocusArea != null) {
                terms.addAll(FocusAreaLocalServiceUtil.getTerms(contestFocusArea));
            }

            return terms;
        }
    };

    public static ProposalPickerFilter CONTEST_TIER = new ProposalPickerFilter() {
        @Override
        public Set<Long> filter(List<Pair<Proposal, Date>> proposals, Object additionalFilterCriterion) {
            Set<Long> removedProposals = new HashSet<>();
            try{
                final Set<Long> allowedContestTiers = getAllowedTiers((Long) additionalFilterCriterion);

                if (allowedContestTiers.size() > 0) {
                    Set<Contest> tierFilteredContests = new HashSet<>();
                    for (Long tier : allowedContestTiers) {
                        ContestTier contestTier = ContestTier.getContestTierByTierType(tier);
                        if (contestTier != null) {
                            tierFilteredContests.addAll(ContestLocalServiceUtil.getContestsMatchingTier(contestTier.getTierType()));
                        }
                    }

                    for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                        Proposal p = i.next().getLeft();
                        try {
                            if (!tierFilteredContests.contains(Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId())))
                                i.remove();
                        } catch (Exception e){
                            i.remove();
                        }
                    }
                }
            } catch (SystemException | PortalException e){ /* LR EXCEPTIONS */
                e.printStackTrace();
            }
            return removedProposals;
        }

        @Override
        public Set<Long> filterContests(List<Pair<ContestWrapper, Date>> contests,
                                   Object additionalFilterCriterion) {
            Set<Long> removedContests = new HashSet<>();

            final Set<Long> allowedContestTiers = getAllowedTiers((Long) additionalFilterCriterion);

            if (allowedContestTiers.size() > 0) {
                for (Iterator<Pair<ContestWrapper,Date>> i = contests.iterator(); i.hasNext();){
                    ContestWrapper contest = i.next().getLeft();
                    try {
                        if (allowedContestTiers.contains(contest.getContestTier())) {
                            removedContests.add(contest.getContestPK());
                            i.remove();
                        }
                    } catch (Exception e){
                        removedContests.add(contest.getContestPK());
                        i.remove();
                    }
                }
            }
            return removedContests;
        }

        private Set<Long> getAllowedTiers(Long filterTier) {
            // if filterTier < 0:
            //  allow tier < (-filterTier)
            // else if filterTier > 0
            //  only allow tier == filterTier - 1
            Set<Long> allowedTiers = new HashSet<>();
            allowedTiers.add(filterTier);

            if (filterTier < 0) {
                for (Long currentTier = Math.abs(filterTier) - 1; currentTier >= 0; currentTier--) {
                    allowedTiers.add(currentTier);
                }
            }
            return allowedTiers;
        }
    };

    /**
     * Parse filter from frontend parameter and filter the contents of the proposals parameter
     * @param filterKey the filter key passed as parameter from the frontend
     * @param proposals A list of Proposals paired with their date
     */
    public static void filterByParameter(String filterKey, List<Pair<Proposal, Date>> proposals) {
        if (filterKey != null && filterKey.equalsIgnoreCase("WINNERSONLY")) {
            WINNERSONLY.filter(proposals, null);
        } else {
            ACCEPTALL.filter(proposals, null);
        }
    }
}
