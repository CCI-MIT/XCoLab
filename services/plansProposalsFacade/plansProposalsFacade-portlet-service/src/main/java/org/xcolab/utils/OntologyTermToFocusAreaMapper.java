package org.xcolab.utils;

import com.ext.portlet.NoSuchOntologySpaceException;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.contest.pojo.OntologySpace;
import org.xcolab.client.contest.pojo.OntologyTerm;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.exceptions.ReferenceResolutionException;

import java.util.List;

public class OntologyTermToFocusAreaMapper {

    private final List<OntologyTerm> toBeMatchedTerms;

    public OntologyTermToFocusAreaMapper(List<OntologyTerm> terms) {
        this.toBeMatchedTerms = terms;
    }

    /**
     * Returns a focus area that exactly matches all OntologyTerms. That is, only returns a focus area that matches only the
     * terms passed. This is in contrast to org.xcolab.utils.getFocusAreaMatchingTermsPartially
     */
    public FocusArea getFocusAreaMatchingTermsExactly() {
            return applyFilterToFocusAreasMatchingExactly(
                    OntologyClient.getAllFocusAreas(),
                    true);

    }

    /**
     * Returns a focus area that exactly matches all OntologyTerms within the passed focusAreas. That is, only returns a focus area that matches only the
     * terms passed. This is in contrast to org.xcolab.utils.getFocusAreaMatchingTermsPartially
     *
     * @throws SystemException
     * @throws PortalException
     */
    public FocusArea getFocusAreaMatchingTermsExactly(List<FocusArea> focusAreasToBeSearched) throws SystemException, PortalException {
        return applyFilterToFocusAreasMatchingExactly(focusAreasToBeSearched, true);
    }

    /**
     * Returns a focus area that matches at least all passed OntologyTerms.
     *
     * @throws SystemException
     * @throws PortalException
     */
    public FocusArea getFocusAreaMatchingTermsPartially() throws SystemException, PortalException {
        return applyFilterToFocusAreasMatchingExactly(OntologyClient.getAllFocusAreas(), false);
    }

    /**
     * Returns a focus area that partially matches all OntologyTerms within the passed focusAreas. That is, only returns a focus area that matches only the
     * terms passed. This is in contrast to org.xcolab.utils.getFocusAreaMatchingTermsPartially
     *
     * @throws SystemException
     * @throws PortalException
     */
    public FocusArea getFocusAreaMatchingTermsPartially(List<FocusArea> focusAreasToBeSearched) throws SystemException, PortalException {
        return applyFilterToFocusAreasMatchingExactly(focusAreasToBeSearched, false);
    }

    private FocusArea applyFilterToFocusAreasMatchingExactly(List<FocusArea> toBeSearchedFocusAreas, boolean matchTermsExactly) {
        for (FocusArea focusArea : toBeSearchedFocusAreas) {
            if (!isFocusAreaOntologyTermCountMatching(focusArea, toBeMatchedTerms.size()) && matchTermsExactly) {
                continue;
            }
            boolean focusAreaMatchesTerms = true;
            for (OntologyTerm toBeMatchedTerm : toBeMatchedTerms) {
                OntologyTerm focusAreaOntologyTerm = getTermWithSpaceId(focusArea, toBeMatchedTerm.getOntologySpaceId());
                if (focusAreaOntologyTerm.getId_() != toBeMatchedTerm.getId_()) {
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

    private OntologyTerm getTermWithSpaceId(FocusArea focusArea, long spaceId) {
            OntologySpace space = OntologyClient.getOntologySpace(spaceId);
            return OntologyClient
                    .getOntologyTermFromFocusAreaWithOntologySpace(focusArea, space);

    }

    private boolean isFocusAreaOntologyTermCountMatching(FocusArea focusArea, int ontologyTermCount) {
            return OntologyClient.getOntologyTermsForFocusArea(focusArea).size() == ontologyTermCount;

    }
}
