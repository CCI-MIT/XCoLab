package org.xcolab.utils;

import com.ext.portlet.NoSuchOntologySpaceException;

import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.ContestClient;
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
                    ContestClient.getAllFocusAreas(),
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
        return applyFilterToFocusAreasMatchingExactly(ContestClient.getAllFocusAreas(), false);
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
        try {
            OntologySpace space = ContestClient.(spaceId);
            return FocusAreaLocalServiceUtil
                    .getOntologyTermFromFocusAreaWithOntologySpace(focusArea, space);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (NoSuchOntologySpaceException e) {
            throw ReferenceResolutionException.toObject(OntologySpace.class, spaceId).build();
        } catch (PortalException e) {
            //TODO: which exception should this throw?
            throw new InternalException("Ontology term not found: focus area "
                    + focusArea.getId() + " and ontology space " + spaceId);
        }
    }

    private boolean isFocusAreaOntologyTermCountMatching(FocusArea focusArea, int ontologyTermCount) {
        try {
            return FocusAreaLocalServiceUtil.getTerms(focusArea).size() == ontologyTermCount;
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw ReferenceResolutionException.toObject(OntologyTerm.class, "list")
                    .fromObject(FocusArea.class, focusArea.getId());
        }
    }
}
