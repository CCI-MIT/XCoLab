package org.xcolab.view.pages.proposals.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.IdListUtil;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.view.pages.proposals.utils.ProposalPickerFilterUtil.SectionDefFocusAreaArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ProposalPickerFilter {

    public final static ProposalPickerFilter ACCEPT_ALL = new ProposalPickerFilter();
    public final static ProposalPickerFilter WINNERS_ONLY = new ProposalPickerFilter() {
        @Override
        public void filter(List<Proposal> proposals, Object additionalFilterCriterion) {
            for (Iterator<Proposal> i = proposals.iterator(); i.hasNext(); ) {
                Proposal p = i.next();
                List<Proposal2Phase> phases =
                    ProposalPhaseClientUtil.getProposal2PhaseByProposalId(p.getProposalId());
                boolean winner = false;
                for (Proposal2Phase phase : phases) {
                    winner = winner || ProposalPhaseClientUtil
                        .hasProposalContestPhaseAttribute(p.getProposalId(),
                            phase.getContestPhaseId(), "RIBBON");
                }
                if (!winner) {
                    i.remove();
                }
            }
        }
    };
    private static final Logger _log = LoggerFactory.getLogger(ProposalPickerFilter.class);
    public final static ProposalPickerFilter CONTEST_TIER = new ProposalPickerFilter() {
        @Override
        public void filter(List<Proposal> proposals, Object additionalFilterCriterion) {


            final Set<Long> allowedContestTiers = new HashSet<>(
                ProposalPickerFilterUtil.getAllowedTiers((Long) additionalFilterCriterion));

            if (!allowedContestTiers.isEmpty()) {
                Set<Contest> tierFilteredContests = new HashSet<>();
                for (Long tier : allowedContestTiers) {
                    ContestTier contestTier = ContestTier.getContestTierByTierType(tier);
                    if (contestTier != null) {
                        tierFilteredContests.addAll(
                            ContestClientUtil.getContestsMatchingTier(contestTier.getTierType()));
                    } else {
                        _log.error(String
                            .format("Could not find contest tier %d. Tier ignored in filtering.",
                                tier));
                    }
                }

                proposals.removeIf(proposal -> !tierFilteredContests.contains(
                    ProposalClientUtil.getCurrentContestForProposal(proposal.getProposalId())));
            }
        }

        @Override
        public void filterContests(List<Contest> contests, Object additionalFilterCriterion) {

            final Set<Long> allowedContestTiers = new HashSet<>(
                ProposalPickerFilterUtil.getAllowedTiers((Long) additionalFilterCriterion));

            if (!allowedContestTiers.isEmpty()) { //empty list = allow all
                for (Iterator<Contest> i = contests.iterator(); i.hasNext(); ) {
                    Contest contest = i.next();
                    if (!allowedContestTiers.contains(contest.getContestTier())) {
                        i.remove();
                        _log.error(String.format(
                            "Contest %d caused an error while filtering for contest tier."
                                + "Removing contests from list...", contest.getContestPK()));
                    }
                }
            }
        }

    };
    public final static ProposalPickerFilter CONTEST_TYPE_FILTER = new ProposalPickerFilter() {
        @Override
        public void filter(List<Proposal> proposals, Object additionalFilterCriterion) {

            final String allowedContestTypeIdsString = (String) additionalFilterCriterion;

            if (!allowedContestTypeIdsString.isEmpty()) {//no selection = allow all
                final List<Long> allowedContestTypeIds =
                    IdListUtil.getIdsFromString(allowedContestTypeIdsString);
                for (Iterator<Proposal> i = proposals.iterator(); i.hasNext(); ) {
                    Proposal proposal = i.next();
                    try {

                        long contestTypeId = ProposalClientUtil
                            .getCurrentContestForProposal(proposal.getProposalId())
                            .getContestTypeId();
                        if (!allowedContestTypeIds.contains(contestTypeId)) {
                            i.remove();
                        }
                    } catch (ContestNotFoundException e) {
                        _log.warn(String.format(
                            "Could not get ContestType for proposal %d while filtering for contest types",
                            proposal.getProposalId()), e);
                        i.remove();
                    }
                }
            }
        }

        @Override
        public void filterContests(List<Contest> contests, Object additionalFilterCriterion) {

            final String allowedContestTypeIdsString = (String) additionalFilterCriterion;

            if (!allowedContestTypeIdsString.isEmpty()) { //no selection = allow all
                List<Long> allowedContestTypeIds =
                    IdListUtil.getIdsFromString(allowedContestTypeIdsString);

                contests.removeIf(
                    contest -> !allowedContestTypeIds.contains(contest.getContestTypeId()));
            }

        }
    };
    private static final List<Long> ANY_TERM_IDS = Arrays.asList(1L, 2L, 3L, 1300601L);
    public final static ProposalPickerFilter SECTION_DEF_FOCUS_AREA_FILTER =
        new ProposalPickerFilter() {
            @Override
            public void filter(List<Proposal> proposals, Object additionalFilterCriterion) {
                final SectionDefFocusAreaArgument additionalArguments =
                    (SectionDefFocusAreaArgument) additionalFilterCriterion;
                final List<Long> filterExceptionContestIds =
                    additionalArguments.getFilterExceptionContestIds();
                final Long sectionFocusAreaId = additionalArguments.getSectionFocusAreaId();
                final Long contestFocusAreaId = additionalArguments.getContestFocusAreaId();

                List<OntologyTerm> terms =
                    getOntologyTermsFromSectionAndContest(sectionFocusAreaId, contestFocusAreaId);


                if (!terms.isEmpty()) {
                    List<Long> otIds = new ArrayList<>();
                    for (OntologyTerm ot : terms) {
                        otIds.add(ot.getId_());
                    }
                    List<Contest> contests =
                        ContestClientUtil.getContestMatchingOntologyTerms(otIds);
                    for (long filterExceptionContestId : filterExceptionContestIds) {
                        try {
                            final Contest filterExceptionContest =
                                ContestClientUtil.getContest(filterExceptionContestId);
                            if (!contests.contains(filterExceptionContest)) {
                                contests.add(filterExceptionContest);
                            }
                        } catch (ContestNotFoundException ignored) {

                        }
                    }
                    proposals.removeIf(proposal -> !contests.contains(
                        ProposalClientUtil.getCurrentContestForProposal(proposal.getProposalId())));
                }
            }

            private List<OntologyTerm> getOntologyTermsFromSectionAndContest(Long focusAreaId,
                Long contestFocusAreaId) {
                Long localContestFocusAreaId;
                if (focusAreaId < 0) {
                    localContestFocusAreaId = contestFocusAreaId;
                    focusAreaId = Math.abs(focusAreaId);
                } else {
                    localContestFocusAreaId = 0L;
                }
                List<OntologyTerm> terms = new ArrayList<>();

                FocusArea focusArea = OntologyClientUtil.getFocusArea(focusAreaId);
                addTermsInFocusArea(terms, focusArea);
                FocusArea contestFocusArea =
                    OntologyClientUtil.getFocusArea(localContestFocusAreaId);
                addTermsInFocusArea(terms, contestFocusArea);

                return terms;
            }            @Override
            public void filterContests(List<Contest> contests, Object additionalFilterCriterion) {
                final SectionDefFocusAreaArgument additionalArguments =
                    (SectionDefFocusAreaArgument) additionalFilterCriterion;
                final List<Long> filterExceptionContestIds =
                    additionalArguments.getFilterExceptionContestIds();
                final Long sectionFocusAreaId = additionalArguments.getSectionFocusAreaId();
                final Long contestFocusAreaId = additionalArguments.getContestFocusAreaId();

                List<OntologyTerm> requiredTerms =
                    getOntologyTermsFromSectionAndContest(sectionFocusAreaId, contestFocusAreaId);

                for (Iterator<Contest> i = contests.iterator(); i.hasNext(); ) {
                    Contest contest = i.next();
                    if (filterExceptionContestIds.contains(contest.getContestPK())) {
                        continue;
                    }
                    FocusArea focusArea = OntologyClientUtil.getFocusArea(contest.getFocusAreaId());
                    List<OntologyTerm> contestTerms =
                        OntologyClientUtil.getOntologyTermsForFocusArea(focusArea);
                    for (OntologyTerm requiredTerm : requiredTerms) {
                        List<OntologyTerm> requiredDescendantTerms =
                            OntologyClientUtil.getAllOntologyTermDescendant(requiredTerm.getId_());
                        requiredDescendantTerms.add(requiredTerm);
                        if (!CollectionUtils.containsAny(requiredDescendantTerms, contestTerms)) {
                            i.remove();
                            break;
                        }
                    }

                }

            }

            private void addTermsInFocusArea(List<OntologyTerm> terms, FocusArea focusArea) {
                if (focusArea != null) {
                    final List<OntologyTerm> focusAreaTerms =
                        OntologyClientUtil.getOntologyTermsForFocusArea(focusArea);
                    removeRootTerms(focusAreaTerms);
                    _log.debug(
                        String.format("Added %d non-root contest terms", focusAreaTerms.size()));
                    terms.addAll(focusAreaTerms);
                }
            }

            private void removeRootTerms(List<OntologyTerm> terms) {
                terms.removeIf(o -> o.getParentId() == 0 && ANY_TERM_IDS.contains(o.getId_()));
            }


        };

    /**
     * Filters the passed list of proposals
     *
     * @param proposals the list of proposals to be filtered. Will be modified and contains the
     * result!
     */
    public void filter(List<Proposal> proposals) {
        filter(proposals, null);
    }

    /**
     * Filters the passed list of proposals
     *
     * @param proposals the list of proposals to be filtered. Will be modified and contains the
     * result!
     * @param additionalFilterCriterion The additional filter criterion - if any - used by this
     * filter
     */
    public void filter(List<Proposal> proposals, Object additionalFilterCriterion) {

    }

    /**
     * Filters the passed list of contests
     *
     * @param contests the list of contests to be filtered. Will be modified and contains the
     * result!
     * @param additionalFilterCriterion The additional filter criterion - if any - used by this
     * filter
     */
    public void filterContests(List<Contest> contests, Object additionalFilterCriterion) {

    }
}
