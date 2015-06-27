package org.xcolab.utils;

import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.ImpactTemplateMaxFocusArea;
import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.enums.OntologySpaceEnum;

import java.util.List;

/**
 * Created by Klemens Mang on 25/06/15.
 */
public class OntologyTermToFocusAreaMapper {

    private List<OntologyTerm> toBeMatchedTerms;

    public OntologyTermToFocusAreaMapper(List<OntologyTerm> terms) {
        this.toBeMatchedTerms = terms;
    }

    public FocusArea getAssociatedFocusArea() throws SystemException, PortalException {
        return filterAssociatedFocusArea(FocusAreaLocalServiceUtil.getFocusAreas(QueryUtil.ALL_POS, QueryUtil.ALL_POS));
    }

    public FocusArea filterAssociatedFocusArea(List<FocusArea> toBeSearchedFocusAreas) throws PortalException, SystemException {
        for (FocusArea focusArea : toBeSearchedFocusAreas) {
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
}
