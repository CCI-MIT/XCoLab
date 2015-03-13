package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.ImpactTemplateMaxFocusArea;
import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kmang on 12/03/15.
 */
public class ProposalImpactUtil {

    private static final long WHAT_ONTOLOGY_SPACE_ID = 103;
    private static final long WHERE_ONTOLOGY_SPACE_ID = 104;

    // TODO cache this map somehow
    public static Map<OntologyTerm, List<OntologyTerm>> calculateAvailableOntologyMap(Contest contest,
                     List<ProposalImpactSeries> impactSerieses) throws SystemException, PortalException {

        Map<OntologyTerm, List<OntologyTerm>> ontologyTermMap = new HashMap<>();
        Map<Long, Boolean> impactSeriesAvailableMap = getImpactSeriesAvailableMap(impactSerieses);

        List<ImpactTemplateMaxFocusArea> impactFocusAreas = ContestLocalServiceUtil.getContestImpactFocusAreas(contest);

        for (ImpactTemplateMaxFocusArea impactFocusArea : impactFocusAreas) {
            FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(impactFocusArea.getFocusAreaId());

            // Only consider focus areas where we did not have a filled out impact series
            if (Validator.isNull(impactSeriesAvailableMap.get(focusArea.getId()))) {
                OntologyTerm whatTerm = getWhatTerm(focusArea);
                OntologyTerm whereTerm = getWhereTerm(focusArea);

                List<OntologyTerm> whereTerms = ontologyTermMap.get(whatTerm);
                if (Validator.isNull(whereTerms)) {
                    whereTerms = new ArrayList<OntologyTerm>();
                    ontologyTermMap.put(whatTerm, whereTerms);
                }

                whereTerms.add(whereTerm);
            }
        }

        return ontologyTermMap;
    }

    public static OntologyTerm getWhatTerm(FocusArea focusArea) throws PortalException, SystemException {
        return getTermWithSpaceId(focusArea, WHAT_ONTOLOGY_SPACE_ID);
    }

    public static OntologyTerm getWhereTerm(FocusArea focusArea) throws PortalException, SystemException {
        return getTermWithSpaceId(focusArea, WHERE_ONTOLOGY_SPACE_ID);
    }

    private static OntologyTerm getTermWithSpaceId(FocusArea focusArea, long spaceId) throws SystemException, PortalException {
        OntologySpace space = OntologySpaceLocalServiceUtil.getOntologySpace(spaceId);
        return FocusAreaLocalServiceUtil.getOntologyTermFromFocusAreaWithOntologySpace(focusArea, space);
    }

    private static Map<Long, Boolean> getImpactSeriesAvailableMap(List<ProposalImpactSeries> impactSerieses) {
        Map<Long, Boolean> impactSeriesAvailableMap = new HashMap<>(impactSerieses.size());
        if (Validator.isNull(impactSerieses)) {
            return impactSeriesAvailableMap;
        }

        for (ProposalImpactSeries series : impactSerieses) {
            impactSeriesAvailableMap.put(series.getFocusArea().getId(), true);
        }

        return impactSeriesAvailableMap;
    }
}
