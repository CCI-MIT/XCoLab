package org.xcolab.portlets.proposals.utils;



import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.contest.pojo.ImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.OntologySpace;
import org.xcolab.client.contest.pojo.OntologyTerm;
import org.xcolab.enums.OntologySpaceEnum;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeries;
import org.xcolab.utils.OntologyTermToFocusAreaMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kmang on 12/03/15.
 */
public class ProposalImpactUtil {

    private Contest contest;

    public ProposalImpactUtil(Contest contest) {
        this.contest = contest;
    }

    // TODO cache this map somehow

    /**
     * Returns a map that maps from a region (WHERE) OntologyTerm to all sector (WHAT) OntologyTerms that are still available
     *
     * @param impactSerieses        A list of impact series objects
     * @return                      A map with region terms as keys and a list of sector terms as values
     * @throws SystemException
     * @throws PortalException
     */
    public Map<OntologyTerm, List<OntologyTerm>> calculateAvailableOntologyMap(List<ProposalImpactSeries> impactSerieses) throws SystemException, PortalException {

        Map<OntologyTerm, List<OntologyTerm>> ontologyTermMap = new HashMap<>();
        Map<Long, Boolean> impactSeriesAvailableMap = getImpactSeriesAvailableMap(impactSerieses);

        List<ImpactTemplateMaxFocusArea> impactFocusAreas = ContestClient.getContestImpactFocusAreas(contest);

        for (ImpactTemplateMaxFocusArea impactFocusArea : impactFocusAreas) {
            FocusArea focusArea = ContestClient.getFocusArea(impactFocusArea.getFocusAreaId());

            // Only consider focus areas where we did not have a filled out impact series
            if (Validator.isNull(impactSeriesAvailableMap.get(focusArea.getId_()))) {
                OntologyTerm whatTerm = getWhatTerm(focusArea);
                OntologyTerm whereTerm = getWhereTerm(focusArea);

                List<OntologyTerm> whatTerms = ontologyTermMap.get(whereTerm);
                if (Validator.isNull(whatTerms)) {
                    whatTerms = new ArrayList<OntologyTerm>();
                    ontologyTermMap.put(whereTerm, whatTerms);
                }

                whatTerms.add(whatTerm);
            }
        }

        return ontologyTermMap;
    }

    public FocusArea getFocusAreaAssociatedWithTerms(OntologyTerm whatTerm, OntologyTerm whereTerm) throws SystemException, PortalException {
        List<ImpactTemplateMaxFocusArea> impactFocusAreas = ContestClient.getContestImpactFocusAreas(contest);

        List<OntologyTerm> matchingOntologyTerms = new ArrayList<>();
        matchingOntologyTerms.add(whatTerm);
        matchingOntologyTerms.add(whereTerm);

        List<FocusArea> focusAreasToBeSearched = new ArrayList<>();
        for (ImpactTemplateMaxFocusArea impactFocusArea : impactFocusAreas) {
            focusAreasToBeSearched.add(ContestClient.getFocusArea(impactFocusArea.getFocusAreaId()));
        }

        OntologyTermToFocusAreaMapper termMapper = new OntologyTermToFocusAreaMapper(matchingOntologyTerms);
        return termMapper.getFocusAreaMatchingTermsPartially(focusAreasToBeSearched);
    }

    private static Map<Long, Boolean> getImpactSeriesAvailableMap(List<ProposalImpactSeries> impactSerieses) {
        Map<Long, Boolean> impactSeriesAvailableMap = new HashMap<>(impactSerieses.size());
        if (Validator.isNull(impactSerieses)) {
            return impactSeriesAvailableMap;
        }

        for (ProposalImpactSeries series : impactSerieses) {
            impactSeriesAvailableMap.put(series.getFocusArea().getId_(), true);
        }

        return impactSeriesAvailableMap;
    }

    public static OntologyTerm getWhatTerm(FocusArea focusArea) throws PortalException, SystemException {
        return getTermWithSpaceId(focusArea, OntologySpaceEnum.WHAT.getSpaceId());
    }

    public static OntologyTerm getWhereTerm(FocusArea focusArea) throws PortalException, SystemException {
        return getTermWithSpaceId(focusArea, OntologySpaceEnum.WHERE.getSpaceId());
    }

    private static OntologyTerm getTermWithSpaceId(FocusArea focusArea, long spaceId) throws SystemException, PortalException {
        OntologySpace space = OntologySpaceLocalServiceUtil.getOntologySpace(spaceId);
        return FocusAreaLocalServiceUtil.getOntologyTermFromFocusAreaWithOntologySpace(focusArea, space);
    }
}
