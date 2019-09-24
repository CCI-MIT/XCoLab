package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;

import java.util.List;

public class OntologyTermToFocusAreaMapper {

    private final List<OntologyTermWrapper> toBeMatchedTerms;

    public OntologyTermToFocusAreaMapper(List<OntologyTermWrapper> terms) {
        this.toBeMatchedTerms = terms;
    }

    /**
     * Returns a focus area that exactly matches all OntologyTerms. That is, only returns a focus area that matches only the
     * terms passed. This is in contrast to org.xcolab.utils.getFocusAreaMatchingTermsPartially
     */
    public FocusAreaWrapper getFocusAreaMatchingTermsExactly() {
            return applyFilterToFocusAreasMatchingExactly(
                    StaticContestContext.getOntologyClient().getAllFocusAreas(), true);
    }

    /**
     * Returns a focus area that exactly matches all OntologyTerms within the passed focusAreas. That is, only returns a focus area that matches only the
     * terms passed. This is in contrast to org.xcolab.utils.getFocusAreaMatchingTermsPartially
     */
    public FocusAreaWrapper getFocusAreaMatchingTermsExactly(List<FocusAreaWrapper> focusAreasToBeSearched) {
        return applyFilterToFocusAreasMatchingExactly(focusAreasToBeSearched, true);
    }

    /**
     * Returns a focus area that matches at least all passed OntologyTerms.
     *
     */
    public FocusAreaWrapper getFocusAreaMatchingTermsPartially() {
        return applyFilterToFocusAreasMatchingExactly(StaticContestContext.getOntologyClient()
                .getAllFocusAreas(), false);
    }

    /**
     * Returns a focus area that partially matches all OntologyTerms within the passed focusAreas. That is, only returns a focus area that matches only the
     * terms passed. This is in contrast to org.xcolab.utils.getFocusAreaMatchingTermsPartially
     *
     */
    public FocusAreaWrapper getFocusAreaMatchingTermsPartially(List<FocusAreaWrapper> focusAreasToBeSearched) {
        return applyFilterToFocusAreasMatchingExactly(focusAreasToBeSearched, false);
    }

    private FocusAreaWrapper applyFilterToFocusAreasMatchingExactly(List<FocusAreaWrapper> toBeSearchedFocusAreas, boolean matchTermsExactly) {
        for (FocusAreaWrapper focusArea : toBeSearchedFocusAreas) {
            if (!isFocusAreaOntologyTermCountMatching(focusArea, toBeMatchedTerms.size()) && matchTermsExactly) {
                continue;
            }
            boolean focusAreaMatchesTerms = true;
            for (OntologyTermWrapper toBeMatchedTerm : toBeMatchedTerms) {
                OntologyTermWrapper
                        focusAreaOntologyTerm = getTermWithSpaceId(focusArea, toBeMatchedTerm.getOntologySpaceId());
                if (focusAreaOntologyTerm!=null && (focusAreaOntologyTerm.getId().longValue() != toBeMatchedTerm.getId().longValue())) {
                    focusAreaMatchesTerms = false;
                    break;
                }
            }

            if (focusAreaMatchesTerms) {
                return focusArea;
            }
        }

        return null;
    }

    private OntologyTermWrapper getTermWithSpaceId(FocusAreaWrapper focusArea, long spaceId) {
            OntologySpaceWrapper space = StaticContestContext.getOntologyClient()
                    .getOntologySpace(spaceId);
            return StaticContestContext.getOntologyClient()
                    .getOntologyTermFromFocusAreaWithOntologySpace(focusArea, space);

    }

    private boolean isFocusAreaOntologyTermCountMatching(FocusAreaWrapper focusArea, int ontologyTermCount) {
            return StaticContestContext.getOntologyClient()
                    .getOntologyTermsForFocusArea(focusArea).size() == ontologyTermCount;
    }
}
