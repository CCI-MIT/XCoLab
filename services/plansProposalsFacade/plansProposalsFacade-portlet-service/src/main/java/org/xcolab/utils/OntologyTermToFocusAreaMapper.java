package org.xcolab.utils;

import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * Created by Klemens Mang on 25/06/15.
 */
public class OntologyTermToFocusAreaMapper {

    private List<OntologyTerm> toBeMatchedTerms;

    public OntologyTermToFocusAreaMapper(List<OntologyTerm> terms) {
        this.toBeMatchedTerms = terms;
    }

    /**
     * Returns a focus area that exactly matches all OntologyTerms. That is, only returns a focus area that matches only the
     * terms passed. This is in contrast to org.xcolab.utils.getFocusAreaMatchingTermsPartially
     *
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public FocusArea getFocusAreaMatchingTermsExactly() throws SystemException, PortalException {
        return applyFilterToFocusAreasMatchingExactly(FocusAreaLocalServiceUtil.getFocusAreas(QueryUtil.ALL_POS, QueryUtil.ALL_POS), true);
    }

    /**
     * Returns a focus area that exactly matches all OntologyTerms within the passed focusAreas. That is, only returns a focus area that matches only the
     * terms passed. This is in contrast to org.xcolab.utils.getFocusAreaMatchingTermsPartially
     *
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public FocusArea getFocusAreaMatchingTermsExactly(List<FocusArea> focusAreasToBeSearched) throws SystemException, PortalException {
        return applyFilterToFocusAreasMatchingExactly(focusAreasToBeSearched, true);
    }

    /**
     * Returns a focus area that matches at least all passed OntologyTerms.
     *
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public FocusArea getFocusAreaMatchingTermsPartially() throws SystemException, PortalException {
        return applyFilterToFocusAreasMatchingExactly(FocusAreaLocalServiceUtil.getFocusAreas(QueryUtil.ALL_POS, QueryUtil.ALL_POS), false);
    }

    /**
     * Returns a focus area that partially matches all OntologyTerms within the passed focusAreas. That is, only returns a focus area that matches only the
     * terms passed. This is in contrast to org.xcolab.utils.getFocusAreaMatchingTermsPartially
     *
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public FocusArea getFocusAreaMatchingTermsPartially(List<FocusArea> focusAreasToBeSearched) throws SystemException, PortalException {
        return applyFilterToFocusAreasMatchingExactly(focusAreasToBeSearched, false);
    }

    private FocusArea applyFilterToFocusAreasMatchingExactly(List<FocusArea> toBeSearchedFocusAreas, boolean matchTermsExactly) throws PortalException, SystemException {
        for (FocusArea focusArea : toBeSearchedFocusAreas) {
            if (!isFocusAreaOntologyTermCountMatching(focusArea, toBeMatchedTerms.size()) && matchTermsExactly) {
                continue;
            }
            boolean focusAreaMatchesTerms = true;
            for (OntologyTerm toBeMatchedTerm : toBeMatchedTerms) {
                OntologyTerm focusAreaOntologyTerm = getTermWithSpaceId(focusArea, toBeMatchedTerm.getOntologySpaceId());
                try {
                    if (focusAreaOntologyTerm.getId() != toBeMatchedTerm.getId()) {
                        focusAreaMatchesTerms = false;
                        break;
                    }
                } catch (Exception e) {
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

    private OntologyTerm getTermWithSpaceId(FocusArea focusArea, long spaceId) throws SystemException, PortalException {
        OntologySpace space = OntologySpaceLocalServiceUtil.getOntologySpace(spaceId);
        return FocusAreaLocalServiceUtil.getOntologyTermFromFocusAreaWithOntologySpace(focusArea, space);
    }

    private boolean isFocusAreaOntologyTermCountMatching(FocusArea focusArea, int ontologyTermCount) throws SystemException, PortalException {
        return FocusAreaLocalServiceUtil.getTerms(focusArea).size() == ontologyTermCount;
    }
}
