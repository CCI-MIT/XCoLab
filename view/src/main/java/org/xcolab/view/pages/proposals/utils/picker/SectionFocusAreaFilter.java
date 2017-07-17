package org.xcolab.view.pages.proposals.utils.picker;

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
import org.xcolab.client.proposals.pojo.Proposal;

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
    public void filterProposals(List<Proposal> proposals, Long sectionFocusAreaId, Long contestFocusAreaId,
        List<Long> filterExceptionContestIds) {

        List<OntologyTerm> terms =
            getOntologyTermsFromSectionAndContest(sectionFocusAreaId, contestFocusAreaId);


        if (!terms.isEmpty()) {
            List<Long> otIds = new ArrayList<>();
            for (OntologyTerm ot : terms) {
                otIds.add(ot.getId_());
            }
            Set<Contest> contests = new HashSet<>(
                ContestClientUtil.getContestMatchingOntologyTerms(otIds));
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
        if (focusAreaId != null && focusAreaId < 0) {
            localContestFocusAreaId = contestFocusAreaId;
            focusAreaId = Math.abs(focusAreaId);
        } else {
            localContestFocusAreaId = null;
        }
        List<OntologyTerm> terms = new ArrayList<>();

        if (focusAreaId != null) {
            FocusArea focusArea = OntologyClientUtil.getFocusArea(focusAreaId);
            addTermsInFocusArea(terms, focusArea);
        }

        if (localContestFocusAreaId != null) {
            FocusArea contestFocusArea = OntologyClientUtil.getFocusArea(localContestFocusAreaId);
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
    public void filterContests(List<Contest> contests, Long sectionFocusAreaId,
        Long contestFocusAreaId, List<Long> filterExceptionContestIds) {

        List<OntologyTerm> requiredTerms =
            getOntologyTermsFromSectionAndContest(sectionFocusAreaId, contestFocusAreaId);

        for (Iterator<Contest> i = contests.iterator(); i.hasNext(); ) {
            Contest contest = i.next();
            if (filterExceptionContestIds.contains(contest.getContestPK())) {
                continue;
            }
            if (contest.getHasFocusArea()) {
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
}
