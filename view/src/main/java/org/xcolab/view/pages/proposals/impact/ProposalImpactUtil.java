package org.xcolab.view.pages.proposals.impact;

import org.xcolab.client.contest.OntologyTermToFocusAreaMapper;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.view.util.entity.enums.OntologySpaceEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalImpactUtil {

    private final ContestWrapper contest;

    public ProposalImpactUtil(ContestWrapper contest) {
        this.contest = contest;
    }

    /**
     * Returns a map that maps from a region (WHERE) OntologyTerm to all sector (WHAT) OntologyTerms that are still available
     *
     * @param impactSerieses        A list of impact series objects
     * @return                      A map with region terms as keys and a list of sector terms as values
     */
    public Map<OntologyTermWrapper, List<OntologyTermWrapper>> calculateAvailableOntologyMap(List<ProposalImpactSeries> impactSerieses) {

        Map<OntologyTermWrapper, List<OntologyTermWrapper>> ontologyTermMap = new HashMap<>();
        Map<Long, Boolean> impactSeriesAvailableMap = getImpactSeriesAvailableMap(impactSerieses);

        List<IImpactTemplateMaxFocusArea> impactFocusAreas = StaticContestContext.getImpactClient()
                .getContestImpactFocusAreas(contest);

        for (IImpactTemplateMaxFocusArea impactFocusArea : impactFocusAreas) {
            FocusAreaWrapper focusArea = StaticContestContext.getOntologyClient()
                    .getFocusArea(impactFocusArea.getFocusAreaId());

            // Only consider focus areas where we did not have a filled out impact series
            if (impactSeriesAvailableMap.get(focusArea.getId()) == null) {
                OntologyTermWrapper whatTerm = getWhatTerm(focusArea);
                OntologyTermWrapper whereTerm = getWhereTerm(focusArea);

                List<OntologyTermWrapper> whatTerms =
                        ontologyTermMap.computeIfAbsent(whereTerm, k -> new ArrayList<>());

                whatTerms.add(whatTerm);
            }
        }

        return ontologyTermMap;
    }

    public FocusAreaWrapper getFocusAreaAssociatedWithTerms(OntologyTermWrapper whatTerm, OntologyTermWrapper whereTerm) {
        List<IImpactTemplateMaxFocusArea> impactFocusAreas = StaticContestContext.getImpactClient()
                .getContestImpactFocusAreas(contest);

        List<OntologyTermWrapper> matchingOntologyTerms = new ArrayList<>();
        matchingOntologyTerms.add(whatTerm);
        matchingOntologyTerms.add(whereTerm);

        List<FocusAreaWrapper> focusAreasToBeSearched = new ArrayList<>();
        for (IImpactTemplateMaxFocusArea impactFocusArea : impactFocusAreas) {
            focusAreasToBeSearched.add(StaticContestContext.getOntologyClient()
                    .getFocusArea(impactFocusArea.getFocusAreaId()));
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
            impactSeriesAvailableMap.put(series.getFocusArea().getId(), true);
        }

        return impactSeriesAvailableMap;
    }

    public static OntologyTermWrapper getWhatTerm(FocusAreaWrapper focusArea) {
        return getTermWithSpaceId(focusArea, OntologySpaceEnum.WHAT.getSpaceId());
    }

    public static OntologyTermWrapper getWhereTerm(FocusAreaWrapper focusArea) {
        return getTermWithSpaceId(focusArea, OntologySpaceEnum.WHERE.getSpaceId());
    }

    private static OntologyTermWrapper getTermWithSpaceId(FocusAreaWrapper focusArea, long spaceId) {
        OntologySpaceWrapper space = StaticContestContext.getOntologyClient()
                .getOntologySpace(spaceId);
        return StaticContestContext.getOntologyClient()
                .getOntologyTermFromFocusAreaWithOntologySpace(focusArea, space);
    }
}
