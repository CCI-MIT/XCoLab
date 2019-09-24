package org.xcolab.view.pages.proposals.utils.picker;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SectionFocusAreaFilter {

    private static final Logger _log = LoggerFactory.getLogger(SectionFocusAreaFilter.class);

    private static final List<Long> ANY_TERM_IDS = Arrays.asList(1L, 2L, 3L, 1300601L);

    /**
     * Filters the passed list of proposals
     *
     * @param proposals the list of proposals to be filtered. Will be modified and contains the
     * result!
     */
    public void filterProposals(List<ProposalWrapper> proposals, Long sectionFocusAreaId,
            Long contestFocusAreaId, List<Long> filterExceptionContestIds) {

        List<OntologyTermWrapper> terms =
                getOntologyTermsFromSectionAndContest(sectionFocusAreaId, contestFocusAreaId);


        if (!terms.isEmpty()) {
            List<Long> otIds = new ArrayList<>();
            for (OntologyTermWrapper ot : terms) {
                otIds.add(ot.getId());
            }
            Set<ContestWrapper> contests =
                    new HashSet<>(StaticContestContext.getContestClient()
                            .getContestMatchingOntologyTerms(otIds));
            for (long filterExceptionContestId : filterExceptionContestIds) {
                try {
                    final ContestWrapper filterExceptionContest =
                            StaticContestContext.getContestClient()
                                    .getContest(filterExceptionContestId);
                    contests.add(filterExceptionContest);
                } catch (ContestNotFoundException ignored) {

                }
            }
            proposals.removeIf(proposal -> !contests.contains(
                    StaticProposalContext.getProposalClient()
                            .getCurrentContestForProposal(proposal.getId())));
        }
    }

    private List<OntologyTermWrapper> getOntologyTermsFromSectionAndContest(Long focusAreaId,
            Long contestFocusAreaId) {
        Long localContestFocusAreaId;
        if (focusAreaId != null && focusAreaId < 0) {
            localContestFocusAreaId = contestFocusAreaId;
            focusAreaId = Math.abs(focusAreaId);
        } else {
            localContestFocusAreaId = null;
        }
        List<OntologyTermWrapper> terms = new ArrayList<>();

        if (focusAreaId != null) {
            FocusAreaWrapper focusArea = StaticContestContext.getOntologyClient()
                    .getFocusArea(focusAreaId);
            addTermsInFocusArea(terms, focusArea);
        }

        if (localContestFocusAreaId != null) {
            FocusAreaWrapper contestFocusArea = StaticContestContext.getOntologyClient()
                    .getFocusArea(localContestFocusAreaId);
            addTermsInFocusArea(terms, contestFocusArea);
        }

        return terms;
    }

    /**
     * Filters the passed list of contests
     *
     * @param contests the list of contests to be filtered. Will be modified and contains the
     * result!
     */
    public void filterContests(List<ContestWrapper> contests, Long sectionFocusAreaId,
            Long contestFocusAreaId, List<Long> filterExceptionContestIds) {

        List<OntologyTermWrapper> requiredTerms =
                getOntologyTermsFromSectionAndContest(sectionFocusAreaId, contestFocusAreaId);

        for (Iterator<ContestWrapper> i = contests.iterator(); i.hasNext(); ) {
            ContestWrapper contest = i.next();
            if (filterExceptionContestIds.contains(contest.getId())) {
                continue;
            }
            if (contest.getHasFocusArea()) {
                FocusAreaWrapper focusArea = StaticContestContext.getOntologyClient()
                        .getFocusArea(contest.getFocusAreaId());
                List<OntologyTermWrapper> contestTerms = StaticContestContext.getOntologyClient()
                        .getOntologyTermsForFocusArea(focusArea);
                for (OntologyTermWrapper requiredTerm : requiredTerms) {
                    List<OntologyTermWrapper> requiredDescendantTerms =
                            StaticContestContext.getOntologyClient()
                                    .getAllOntologyTermDescendant(requiredTerm.getId());
                    requiredDescendantTerms.add(requiredTerm);
                    if (!CollectionUtils.containsAny(requiredDescendantTerms, contestTerms)) {
                        i.remove();
                        break;
                    }
                }
            }

        }

    }

    private void addTermsInFocusArea(List<OntologyTermWrapper> terms, FocusAreaWrapper focusArea) {
        if (focusArea != null) {
            final List<OntologyTermWrapper> focusAreaTerms =
                    StaticContestContext.getOntologyClient().getOntologyTermsForFocusArea(focusArea);
            removeRootTerms(focusAreaTerms);
            _log.debug(String.format("Added %d non-root contest terms", focusAreaTerms.size()));
            terms.addAll(focusAreaTerms);
        }
    }

    private void removeRootTerms(List<OntologyTermWrapper> terms) {
        terms.removeIf(o -> o.getParentId() == 0 && ANY_TERM_IDS.contains(o.getId()));
    }
}
