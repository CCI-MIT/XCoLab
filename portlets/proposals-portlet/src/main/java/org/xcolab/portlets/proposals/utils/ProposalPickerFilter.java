package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.utils.IdListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.xcolab.portlets.proposals.utils.ProposalPickerFilterUtil.SectionDefFocusAreaArgument;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 04/12/13
 * Time: 10:43
 */
public class ProposalPickerFilter {
    private static final Log _log = LogFactoryUtil.getLog(ProposalPickerFilter.class);
    private static final List<Long> ANY_TERM_IDS = Arrays.asList(1L, 2L, 3L, 1300601L);

    /**
     * Filters the passed list of proposals
     * @param proposals the list of proposals to be filtered. Will be modified and contains the result!
     * @return A list of all proposals that were removed
     */
    public Set<Long> filter(List<Pair<Proposal,Date>> proposals) {
        return filter(proposals, null);
    }
    /**
     * Filters the passed list of proposals
     * @param proposals the list of proposals to be filtered. Will be modified and contains the result!
     * @param additionalFilterCriterion The additional filter criterion - if any - used by this filter
     * @return A list of all proposals that were removed
     */
    public Set<Long> filter(List<Pair<Proposal,Date>> proposals, Object additionalFilterCriterion) {
        return new HashSet<>();
    }

    /**
     * Filters the passed list of contests
     * @param contests the list of contests to be filtered. Will be modified and contains the result!
     * @param additionalFilterCriterion The additional filter criterion - if any - used by this filter
     * @return A list of all contests that were removed
     */
    public Set<Long> filterContests(List<Pair<ContestWrapper,Date>> contests, Object additionalFilterCriterion){
        return new HashSet<>();
    }

    public final static ProposalPickerFilter ACCEPT_ALL = new ProposalPickerFilter();

    public final static ProposalPickerFilter SECTION_DEF_FOCUS_AREA_FILTER = new ProposalPickerFilter() {
        @Override
        public Set<Long> filter(List<Pair<Proposal, Date>> proposals, Object additionalFilterCriterion) {
            Set<Long> removedProposals = new HashSet<>();
            try {
                final SectionDefFocusAreaArgument additionalArguments = (SectionDefFocusAreaArgument) additionalFilterCriterion;
                final List<Long> filterExceptionContestIds = additionalArguments.getFilterExceptionContestIds();
                final Long sectionFocusAreaId = additionalArguments.getSectionFocusAreaId();
                final Long contestFocusAreaId = additionalArguments.getContestFocusAreaId();

                List<OntologyTerm> terms = getOntologyTermsFromSectionAndContest(sectionFocusAreaId, contestFocusAreaId);

                if (!terms.isEmpty()) {
                    List<Contest> contests = ContestLocalServiceUtil.getContestsMatchingOntologyTerms(terms);
                    for (long filterExceptionContestId : filterExceptionContestIds) {
                        final Contest filterExceptionContest = ContestLocalServiceUtil.fetchContest(filterExceptionContestId);
                        if (!contests.contains(filterExceptionContest)) {
                            contests.add(filterExceptionContest);
                        }
                    }
                    for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                        Proposal p = i.next().getLeft();
                        try {
                            if (!contests.contains(Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()))) {
                                removedProposals.add(p.getProposalId());
                                i.remove();
                            }
                        } catch (SystemException | PortalException e){
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
                final SectionDefFocusAreaArgument additionalArguments = (SectionDefFocusAreaArgument) additionalFilterCriterion;
                final List<Long> filterExceptionContestIds = additionalArguments.getFilterExceptionContestIds();
                final Long sectionFocusAreaId = additionalArguments.getSectionFocusAreaId();
                final Long contestFocusAreaId = additionalArguments.getContestFocusAreaId();

                List<OntologyTerm> requiredTerms = getOntologyTermsFromSectionAndContest(sectionFocusAreaId, contestFocusAreaId);

                for (Iterator<Pair<ContestWrapper,Date>> i = contests.iterator(); i.hasNext();){
                    ContestWrapper contest = i.next().getLeft();
                    if (filterExceptionContestIds.contains(contest.getContestPK())) {
                        continue;
                    }
                    try {
                        FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(contest.getFocusAreaId());
                        List<OntologyTerm> contestTerms = FocusAreaLocalServiceUtil.getTerms(focusArea);
                        for (OntologyTerm requiredTerm : requiredTerms) {
                            List<OntologyTerm> requiredDescendantTerms = OntologyTermLocalServiceUtil.getAllDescendantTerms(requiredTerm);
                            requiredDescendantTerms.add(requiredTerm);
                            if (!CollectionUtils.containsAny(requiredDescendantTerms, contestTerms)) {
                                removedContests.add(contest.getContestPK());
                                i.remove();
                                break;
                            }
                        }
                    } catch (SystemException | PortalException e){
                        removedContests.add(contest.getContestPK());
                        i.remove();
                    }
                }
            } catch (SystemException | PortalException e) { /* LR EXCEPTIONS */
                e.printStackTrace();
            }

            return removedContests;
        }

        private List<OntologyTerm> getOntologyTermsFromSectionAndContest(Long focusAreaId, Long contestFocusAreaId) throws SystemException, PortalException {
            Long localContestFocusAreaId;
            if (focusAreaId < 0) {
                localContestFocusAreaId = contestFocusAreaId;
                focusAreaId = Math.abs(focusAreaId);
            } else {
                localContestFocusAreaId = 0L;
            }
            List<OntologyTerm> terms = new ArrayList<>();

            FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(focusAreaId);
            addTermsInFocusArea(terms, focusArea);
            FocusArea contestFocusArea = FocusAreaLocalServiceUtil.getFocusArea(localContestFocusAreaId);
            addTermsInFocusArea(terms, contestFocusArea);

            return terms;
        }

        private void addTermsInFocusArea(List<OntologyTerm> terms, FocusArea focusArea) throws PortalException, SystemException {
            if (focusArea != null) {
                final List<OntologyTerm> focusAreaTerms = FocusAreaLocalServiceUtil.getTerms(focusArea);
                removeRootTerms(focusAreaTerms);
                _log.debug(String.format("Added %d non-root contest terms", focusAreaTerms.size()));
                terms.addAll(focusAreaTerms);
            }
        }

        private void removeRootTerms(List<OntologyTerm> terms) {
            for (Iterator<OntologyTerm> i = terms.iterator(); i.hasNext();){
                OntologyTerm o = i.next();

                if (o.getParentId() == 0 && ANY_TERM_IDS.contains(o.getId())) {
                    i.remove();
                }
            }
        }
    };

    public final static ProposalPickerFilter TEXT_BASED = new ProposalPickerFilter() {
        @Override
        public Set<Long> filter(List<Pair<Proposal,Date>> proposals, Object additionalFilterCriterion) {
            Set<Long> removedProposals = new HashSet<>();
            if (!(additionalFilterCriterion instanceof String)) {
                return removedProposals;
            }
            String searchCriterion = (String) additionalFilterCriterion;
            for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                Proposal p = i.next().getLeft();
                try{
                    // PROPOSAL NAME
                    String proposalName = ProposalLocalServiceUtil.getAttribute(p.getProposalId(), ProposalAttributeKeys.NAME, 0L).getStringValue();
                    if (StringUtils.containsIgnoreCase(proposalName, searchCriterion)){
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
                } catch (SystemException | PortalException e){
                    e.printStackTrace();
                }
            }
            return removedProposals;
        }

        @Override
        public Set<Long> filterContests(List<Pair<ContestWrapper, Date>> contests,
                                        Object additionalFilterCriterion) {
            Set<Long> removedContests = new HashSet<>();
            if (!(additionalFilterCriterion instanceof String)) {
                return removedContests;
            }
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
                } catch (SystemException | PortalException e){
                    e.printStackTrace();
                }
            }
            return removedContests;
        }
    };

    public final static ProposalPickerFilter WINNERSONLY= new ProposalPickerFilter() {
        @Override
        public Set<Long> filter(List<Pair<Proposal, Date>> proposals, Object additionalFilterCriterion) {
            Set<Long> removedProposals = new HashSet<>();
            for (Iterator<Pair<Proposal,Date>> i = proposals.iterator(); i.hasNext();){
                Proposal p = i.next().getLeft();
                try{
                    List<Long> phases = Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(p.getProposalId());
                    boolean winner = false;
                    for (long phase : phases){
                        winner = winner || ProposalContestPhaseAttributeLocalServiceUtil.hasProposalContestPhaseAttribute(
                                p.getProposalId(), phase, "RIBBON");
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

    public final static ProposalPickerFilter CONTEST_TIER = new ProposalPickerFilter() {
        @Override
        public Set<Long> filter(List<Pair<Proposal, Date>> proposals, Object additionalFilterCriterion) {
            Set<Long> removedProposals = new HashSet<>();
            try{
                final Set<Long> allowedContestTiers = getAllowedTiers((Long) additionalFilterCriterion);

                if (!allowedContestTiers.isEmpty()) {
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
                            if (!tierFilteredContests.contains(Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()))) {
                                i.remove();
                            }
                        } catch (SystemException | PortalException e){
                            i.remove();
                        }
                    }
                }
            } catch (SystemException | PortalException e){
                e.printStackTrace();
            }
            return removedProposals;
        }

        @Override
        public Set<Long> filterContests(List<Pair<ContestWrapper, Date>> contests,
                                        Object additionalFilterCriterion) {
            Set<Long> removedContests = new HashSet<>();

            final Set<Long> allowedContestTiers = getAllowedTiers((Long) additionalFilterCriterion);

            if (!allowedContestTiers.isEmpty()) { //empty list = allow all
                for (Iterator<Pair<ContestWrapper,Date>> i = contests.iterator(); i.hasNext();){
                    ContestWrapper contest = i.next().getLeft();
                    if (!allowedContestTiers.contains(contest.getContestTier())) {
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
            // else (i.e. filterTier = 0)
            //  no filtering
            Set<Long> allowedTiers = new HashSet<>();
            if (filterTier > 0) {
                allowedTiers.add(filterTier);
            }

            if (filterTier < 0) {
                for (Long currentTier = Math.abs(filterTier) - 1; currentTier >= 0; currentTier--) {
                    allowedTiers.add(currentTier);
                }
            }
            return allowedTiers;
        }
    };

    public final static ProposalPickerFilter CONTEST_TYPE_FILTER = new ProposalPickerFilter() {
        @Override
        public Set<Long> filter(List<Pair<Proposal, Date>> proposals, Object additionalFilterCriterion) {
            Set<Long> removedProposals = new HashSet<>();

            final String allowedContestTypeIdsString = (String) additionalFilterCriterion;

            if (!allowedContestTypeIdsString.isEmpty()) {//no selection = allow all
                final List<Long> allowedContestTypeIds = IdListUtil.getIdsFromString(allowedContestTypeIdsString);
                for (Iterator<Pair<Proposal, Date>> i = proposals.iterator(); i.hasNext(); ) {
                    Proposal proposal = i.next().getLeft();
                    try {
                        long contestTypeId = ContestTypeLocalServiceUtil.getContestTypeFromProposalId(proposal.getProposalId()).getId();
                        if (!allowedContestTypeIds.contains(contestTypeId)) {
                            i.remove();
                        }
                    } catch (SystemException | PortalException e) {
                        _log.warn(String.format("Could not get ContestType for proposal %d while filtering for contest types",
                                proposal.getProposalId()), e);
                        i.remove();
                    }
                }
            }

            return removedProposals;
        }

        @Override
        public Set<Long> filterContests(List<Pair<ContestWrapper, Date>> contests, Object additionalFilterCriterion) {
            Set<Long> removedContests = new HashSet<>();

            final String allowedContestTypeIdsString = (String) additionalFilterCriterion;

            if (!allowedContestTypeIdsString.isEmpty()) { //no selection = allow all
                List<Long> allowedContestTypeIds = IdListUtil.getIdsFromString(allowedContestTypeIdsString);

                for (Iterator<Pair<ContestWrapper, Date>> i = contests.iterator(); i.hasNext(); ) {
                    ContestWrapper contest = i.next().getLeft();
                    if (!allowedContestTypeIds.contains(contest.getContestTypeId())) {
                        removedContests.add(contest.getContestPK());
                        i.remove();
                    }
                }
            }

            return removedContests;
        }
    };
}
