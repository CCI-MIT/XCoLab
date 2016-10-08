package org.xcolab.portlets.proposals.impact;

import org.xcolab.client.contest.ImpactTemplateClient;
import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.contest.pojo.ImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.OntologySpace;
import org.xcolab.client.contest.pojo.OntologyTerm;
import org.xcolab.enums.OntologySpaceEnum;
import org.xcolab.utils.OntologyTermToFocusAreaMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalImpactUtil {

    private final Contest contest;

    public ProposalImpactUtil(Contest contest) {
        this.contest = contest;
    }

    // TODO cache this map somehow
    /**
     * Returns a map that maps from a region (WHERE) OntologyTerm to all sector (WHAT) OntologyTerms that are still available
     *
     * @param impactSerieses        A list of impact series objects
     * @return                      A map with region terms as keys and a list of sector terms as values
     */
    public Map<OntologyTerm, List<OntologyTerm>> calculateAvailableOntologyMap(List<ProposalImpactSeries> impactSerieses) {

        Map<OntologyTerm, List<OntologyTerm>> ontologyTermMap = new HashMap<>();
        Map<Long, Boolean> impactSeriesAvailableMap = getImpactSeriesAvailableMap(impactSerieses);

        List<ImpactTemplateMaxFocusArea> impactFocusAreas = ImpactTemplateClient.getContestImpactFocusAreas(contest);

        for (ImpactTemplateMaxFocusArea impactFocusArea : impactFocusAreas) {
            FocusArea focusArea = OntologyClient.getFocusArea(impactFocusArea.getFocusAreaId());

            // Only consider focus areas where we did not have a filled out impact series
            if (impactSeriesAvailableMap.get(focusArea.getId_()) == null) {
                OntologyTerm whatTerm = getWhatTerm(focusArea);
                OntologyTerm whereTerm = getWhereTerm(focusArea);

                List<OntologyTerm> whatTerms = ontologyTermMap.get(whereTerm);
                if (whatTerms == null) {
                    whatTerms = new ArrayList<>();
                    ontologyTermMap.put(whereTerm, whatTerms);
                }

                whatTerms.add(whatTerm);
            }
        }

        return ontologyTermMap;
    }

    public FocusArea getFocusAreaAssociatedWithTerms(OntologyTerm whatTerm, OntologyTerm whereTerm) {
        List<ImpactTemplateMaxFocusArea> impactFocusAreas = ImpactTemplateClient.getContestImpactFocusAreas(contest);

        List<OntologyTerm> matchingOntologyTerms = new ArrayList<>();
        matchingOntologyTerms.add(whatTerm);
        matchingOntologyTerms.add(whereTerm);

        List<FocusArea> focusAreasToBeSearched = new ArrayList<>();
        for (ImpactTemplateMaxFocusArea impactFocusArea : impactFocusAreas) {
            focusAreasToBeSearched.add(OntologyClient.getFocusArea(impactFocusArea.getFocusAreaId()));
        }

        OntologyTermToFocusAreaMapper termMapper = new OntologyTermToFocusAreaMapper(matchingOntologyTerms);
        return termMapper.getFocusAreaMatchingTermsPartially(focusAreasToBeSearched);
    }

    private static Map<Long, Boolean> getImpactSeriesAvailableMap(List<ProposalImpactSeries> impactSerieses) {
        if (impactSerieses == null) {
            return new HashMap<>(0);
        }
        Map<Long, Boolean> impactSeriesAvailableMap = new HashMap<>();

        for (ProposalImpactSeries series : impactSerieses) {
            impactSeriesAvailableMap.put(series.getFocusArea().getId_(), true);
        }

        return impactSeriesAvailableMap;
    }

    public static OntologyTerm getWhatTerm(FocusArea focusArea) {
        return getTermWithSpaceId(focusArea, OntologySpaceEnum.WHAT.getSpaceId());
    }

    public static OntologyTerm getWhereTerm(FocusArea focusArea) {
        return getTermWithSpaceId(focusArea, OntologySpaceEnum.WHERE.getSpaceId());
    }

    private static OntologyTerm getTermWithSpaceId(FocusArea focusArea, long spaceId) {
        OntologySpace space = OntologyClient.getOntologySpace(spaceId);
        return OntologyClient.getOntologyTermFromFocusAreaWithOntologySpace(focusArea, space);
    }
}
