package org.xcolab.portlets.proposals.impact;

import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.contest.pojo.OntologyTerm;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A collection of ProposalImpactSeries representing all data series of each sector-region pair.
 */
public class ProposalImpactSeriesList {

    private static final Map<Long, Map<Integer, Double>>
            ONTOLOGY_REGION_TERM_TO_YEAR_TO_VALUE_FACTOR;
    private static final Double[] US_YEAR_TO_VALUE_FACTOR =
            {0.1832, 0.1740, 0.1592, 0.1464, 0.1348, 0.1595};
    private static final Double[] OTHER_DEVELOPED_YEAR_TO_VALUE_FACTOR =
            {0.1346, 0.1273, 0.1135, 0.1023, 0.0925, 0.1140};
    private static final Double[] EU_YEAR_TO_VALUE_FACTOR =
            {0.1018, 0.0974, 0.0911, 0.0854, 0.0803, 0.0912};
    private static final Double[] CHINA_YEAR_TO_VALUE_FACTOR =
            {0.2453, 0.2616, 0.2858, 0.3042, 0.3199, 0.2834};
    private static final Double[] INDIA_YEAR_TO_VALUE_FACTOR =
            {0.0611, 0.0651, 0.0704, 0.0736, 0.0753, 0.0691};
    private static final Double[] OTHER_DEVELOPING_YEAR_TO_VALUE_FACTOR =
            {0.2740, 0.2747, 0.2801, 0.2881, 0.2973, 0.2828};
    private static final Integer[] YEARS_YEAR_TO_VALUE_FACTOR = {2015, 2020, 2030, 2040, 2050};

    static {
        Map<Integer, Double> usMap = new HashMap<>();
        Map<Integer, Double> euMap = new HashMap<>();
        Map<Integer, Double> indiaMap = new HashMap<>();
        Map<Integer, Double> chinaMap = new HashMap<>();
        Map<Integer, Double> developedMap = new HashMap<>();
        Map<Integer, Double> developingMap = new HashMap<>();

        for (int i = 0; i < YEARS_YEAR_TO_VALUE_FACTOR.length; i++) {
            usMap.put(YEARS_YEAR_TO_VALUE_FACTOR[i], US_YEAR_TO_VALUE_FACTOR[i]);
            euMap.put(YEARS_YEAR_TO_VALUE_FACTOR[i], EU_YEAR_TO_VALUE_FACTOR[i]);
            indiaMap.put(YEARS_YEAR_TO_VALUE_FACTOR[i], INDIA_YEAR_TO_VALUE_FACTOR[i]);
            chinaMap.put(YEARS_YEAR_TO_VALUE_FACTOR[i], CHINA_YEAR_TO_VALUE_FACTOR[i]);
            developedMap
                    .put(YEARS_YEAR_TO_VALUE_FACTOR[i], OTHER_DEVELOPED_YEAR_TO_VALUE_FACTOR[i]);
            developingMap
                    .put(YEARS_YEAR_TO_VALUE_FACTOR[i], OTHER_DEVELOPING_YEAR_TO_VALUE_FACTOR[i]);
        }

        Map<Long, Map<Integer, Double>> ontologyRegionTermToYearToValueFactor = new HashMap<>();
        ontologyRegionTermToYearToValueFactor.put(1300340L, usMap);
        ontologyRegionTermToYearToValueFactor.put(1300341L, euMap);
        ontologyRegionTermToYearToValueFactor.put(1300345L, chinaMap);
        ontologyRegionTermToYearToValueFactor.put(1300346L, indiaMap);
        ontologyRegionTermToYearToValueFactor.put(1311101L, developedMap);
        ontologyRegionTermToYearToValueFactor.put(1311102L, developingMap);
        ONTOLOGY_REGION_TERM_TO_YEAR_TO_VALUE_FACTOR =
                Collections.unmodifiableMap(ontologyRegionTermToYearToValueFactor);
    }

    private final List<ProposalImpactSeries> impactSerieses;

    public ProposalImpactSeriesList(Proposal proposal) throws ContestNotFoundException {
        this(ProposalsClient.getLatestContestInProposal(proposal.getProposalId()), proposal);
    }

    public ProposalImpactSeriesList(Contest contest, Proposal proposal) {

        this.impactSerieses = new ArrayList<>();

        List<FocusArea> proposalFocusAreas = getImpactProposalFocusAreas(proposal);

        for (FocusArea focusArea : proposalFocusAreas) {
            // Get the impact series for the respective focus area
            this.impactSerieses.add(new ProposalImpactSeries(contest, proposal, focusArea));
        }

        Collections.sort(impactSerieses, new Comparator<ProposalImpactSeries>() {
            // Sort by whatTerm name and whereTerm name
            @Override
            public int compare(ProposalImpactSeries o1, ProposalImpactSeries o2) {
                int diff = o1.getWhatTerm().getName().compareTo(o2.getWhatTerm().getName());

                if (diff == 0) {
                    return o1.getWhereTerm().getName().compareTo(o2.getWhereTerm().getName());
                }

                return diff;
            }
        });
    }

    public List<FocusArea> getImpactProposalFocusAreas(Proposal proposal) {
        Set<Long> focusAreaIdSet = new HashSet<>();
        List<FocusArea> impactSeriesFocusAreas = new ArrayList<>();

        for (ProposalAttribute attribute : ProposalAttributeClient
                .getImpactProposalAttributes(proposal)) {
            if (!focusAreaIdSet.contains(attribute.getAdditionalId())) {
                focusAreaIdSet.add(attribute.getAdditionalId());
                impactSeriesFocusAreas
                        .add(OntologyClient.getFocusArea(attribute.getAdditionalId()));
            }
        }

        return impactSeriesFocusAreas;
    }

    public void persistImpactSeriesesWithAuthor(Member author) {
        for (ProposalImpactSeries impactSeries : getImpactSerieses()) {
            impactSeries.persistWithAuthor(author);
        }
    }

    public List<ProposalImpactSeries> getImpactSerieses() {
        return impactSerieses;
    }

    public void addProposalImpactSeries(ProposalImpactSeries proposalImpactSeries) {
        // Check whether serie already exists
        for (ProposalImpactSeries loopedSeries : this.impactSerieses) {
            if (loopedSeries.getFocusArea().getId_() == proposalImpactSeries.getFocusArea()
                    .getId_().longValue()) {
                this.getImpactSerieses().remove(loopedSeries);
                break;
            }
        }

        this.impactSerieses.add(proposalImpactSeries);
    }

    public FocusArea getFocusAreaForTerms(OntologyTerm whatTerm, OntologyTerm whereTerm) {
        for (ProposalImpactSeries impactSeries : impactSerieses) {
            if (impactSeries.getWhatTerm().getId_() == whatTerm.getId_().longValue() &&
                    impactSeries.getWhereTerm().getId_() == whereTerm.getId_().longValue()) {

                return impactSeries.getFocusArea();
            }
        }

        return null;
    }

    /**
     * Returns a map containing aggregated proposal impact series values of all sector-region pairs
     * of a proposal
     *
     * @param seriesTypes seriesTypes that are being calculated
     * @return A map containing a ProposalImpactSeriesValues object for each impact series type
     */
    public Map<String, ProposalImpactSeriesValues> getAggregatedSeriesValues(
            List<String> seriesTypes) {
        Map<String, ProposalImpactSeriesValues> seriesTypeToSeriesSumMap =
                new HashMap<>(seriesTypes.size());

        for (String seriesType : seriesTypes) {
            seriesTypeToSeriesSumMap.put(seriesType, new ProposalImpactSeriesValues());
        }
        seriesTypeToSeriesSumMap
                .put(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY, new ProposalImpactSeriesValues());

        for (ProposalImpactSeries impactSeries : impactSerieses) {
            for (String seriesType : seriesTypes) {
                ProposalImpactSeriesValues integratedSeriesValues =
                        seriesTypeToSeriesSumMap.get(seriesType);
                ProposalImpactSeriesValues impactSeriesValues =
                        impactSeries.getSeriesValuesForType(seriesType);

                // Add up all the impact series values
                integratedSeriesValues.addImpactSeriesValues(impactSeriesValues);
            }

            OntologyTerm ontologyRegionTerm = impactSeries.getWhereTerm();
            Map<Integer, Double> yearToValueFactor =
                    ONTOLOGY_REGION_TERM_TO_YEAR_TO_VALUE_FACTOR.get(ontologyRegionTerm.getId_());

            // Aggregate result impact series
            ProposalImpactSeriesValues integratedSeriesValues =
                    seriesTypeToSeriesSumMap.get(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY);
            ProposalImpactSeriesValues impactSeriesValues = impactSeries.getResultSeriesValues();
            integratedSeriesValues.addImpactSeriesValues(impactSeriesValues, yearToValueFactor);
        }

        return seriesTypeToSeriesSumMap;
    }

    /**
     * Returns a map containing aggregated proposal impact series values of all sector-region pairs
     * of a proposal
     *
     * @param seriesTypes seriesTypes that are being calculated
     * @return A map containing a ProposalImpactSeriesValues object for each impact series type
     */
    public Map<String, ProposalImpactSeriesValues> getAggregatedSeriesValues(
            List<String> seriesTypes, OntologyTerm regionOntologyTerm) {
        Map<String, ProposalImpactSeriesValues> seriesTypeToSeriesSumMap =
                new HashMap<>(seriesTypes.size());

        for (String seriesType : seriesTypes) {
            seriesTypeToSeriesSumMap.put(seriesType, new ProposalImpactSeriesValues());
        }
        seriesTypeToSeriesSumMap
                .put(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY, new ProposalImpactSeriesValues());

        for (ProposalImpactSeries impactSeries : impactSerieses) {
            if (impactSeries.getWhereTerm().equals(regionOntologyTerm)) {
                for (String seriesType : seriesTypes) {
                    ProposalImpactSeriesValues integratedSeriesValues =
                            seriesTypeToSeriesSumMap.get(seriesType);
                    ProposalImpactSeriesValues impactSeriesValues =
                            impactSeries.getSeriesValuesForType(seriesType);

                    // Add up all the impact series values
                    integratedSeriesValues.addImpactSeriesValues(impactSeriesValues);
                }

                // Aggregate result impact series
                ProposalImpactSeriesValues integratedSeriesValues =
                        seriesTypeToSeriesSumMap.get(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY);
                ProposalImpactSeriesValues impactSeriesValues =
                        impactSeries.getResultSeriesValues();
                integratedSeriesValues.addImpactSeriesValues(impactSeriesValues);
            }
        }

        return seriesTypeToSeriesSumMap;
    }

    /**
     * Returns a map containing aggregated proposal impact series values of all sector-region pairs
     * of a proposal
     *
     * @param seriesTypes seriesTypes that are being calculated
     * @return A map containing a ProposalImpactSeriesValues object for each impact series type
     */
    public Map<String, ProposalImpactSeriesValues> getAggregatedSeriesValues(
            List<String> seriesTypes, Long regionOntologyTermId, List<Long> sectorOntologyTermIds) {

        Map<String, ProposalImpactSeriesValues> seriesTypeToSeriesSumMap =
                new HashMap<>(seriesTypes.size());
        OntologyTerm regionOntologyTerm = OntologyClient.getOntologyTerm(regionOntologyTermId);

        for (String seriesType : seriesTypes) {
            seriesTypeToSeriesSumMap.put(seriesType, new ProposalImpactSeriesValues());
        }

        for (Long ontologyTermId : sectorOntologyTermIds) {
            seriesTypeToSeriesSumMap
                    .put(ontologyTermId.toString(), new ProposalImpactSeriesValues());
        }

        seriesTypeToSeriesSumMap
                .put(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY, new ProposalImpactSeriesValues());
        ProposalImpactSeriesValues emptySeries = new ProposalImpactSeriesValues();

        for (ProposalImpactSeries impactSeries : impactSerieses) {

            if (impactSeries.getWhereTerm().equals(regionOntologyTerm)) {

                ProposalImpactSeriesValues impactSeriesValues =
                        impactSeries.getResultSeriesValues();

                ProposalImpactSeriesValues integratedSeriesValues =
                        seriesTypeToSeriesSumMap.get(ProposalImpactSeries.SERIES_TYPE_RESULT_KEY);
                integratedSeriesValues.addImpactSeriesValues(impactSeriesValues);

                for (String seriesType : seriesTypes) {
                    integratedSeriesValues = seriesTypeToSeriesSumMap.get(seriesType);
                    impactSeriesValues = impactSeries.getSeriesValuesForType(seriesType);
                    integratedSeriesValues.addImpactSeriesValues(impactSeriesValues);
                }

                for (Long sectorOntologyTermId : sectorOntologyTermIds) {
                    OntologyTerm sectorOntologyTerm =
                            OntologyClient.getOntologyTerm(sectorOntologyTermId);
                    integratedSeriesValues =
                            seriesTypeToSeriesSumMap.get(sectorOntologyTermId.toString());
                    if (!(impactSeries.getWhatTerm().equals(sectorOntologyTerm))) {
                        for (Integer year : impactSeriesValues.getYearToValueMap().keySet()) {
                            emptySeries.putSeriesValue(year, 0.);
                        }
                        integratedSeriesValues.addImpactSeriesValues(emptySeries);
                    } else {
                        integratedSeriesValues.addImpactSeriesValues(impactSeriesValues);
                    }
                }
            }
        }

        return seriesTypeToSeriesSumMap;
    }

    public ProposalImpactSeriesValues getAggregatedSeriesValuesByOntologyTermId(Long ontologyTermId) {

        ProposalImpactSeriesValues aggregatedSeriesValuesByOntologyTermId =
                new ProposalImpactSeriesValues();

        OntologyTerm ontologyTerm = OntologyClient.getOntologyTerm(ontologyTermId);
        ProposalImpactSeriesValues emptySeries = new ProposalImpactSeriesValues();
        for (ProposalImpactSeries impactSeries : impactSerieses) {

            ProposalImpactSeriesValues impactSeriesValues = impactSeries.getResultSeriesValues();
            if (!impactSeries.getWhatTerm().equals(ontologyTerm)) {
                for (Integer year : impactSeriesValues.getYearToValueMap().keySet()) {
                    emptySeries.putSeriesValue(year, 0.);
                }
                aggregatedSeriesValuesByOntologyTermId.addImpactSeriesValues(emptySeries);
            } else {
                aggregatedSeriesValuesByOntologyTermId.addImpactSeriesValues(impactSeriesValues);
            }
        }

        return aggregatedSeriesValuesByOntologyTermId;
    }


    public Map<String, ProposalImpactSeriesValues>
    getAggregatedSeriesValuesByRegionAndSectorOntologyTermIds(
            Long regionOntologyTermId, List<Long> sectorOntologyTermIds) {
        Map<String, ProposalImpactSeriesValues> ontologyTermIdToSeriesSumMap =
                new HashMap<>(sectorOntologyTermIds.size());
        OntologyTerm regionOntologyTerm = OntologyClient.getOntologyTerm(regionOntologyTermId);

        for (Long ontologyTermId : sectorOntologyTermIds) {
            ontologyTermIdToSeriesSumMap
                    .put(ontologyTermId.toString(), new ProposalImpactSeriesValues());
        }

        ProposalImpactSeriesValues emptySeries = new ProposalImpactSeriesValues();

        for (ProposalImpactSeries impactSeries : impactSerieses) {

            ProposalImpactSeriesValues impactSeriesValues = impactSeries.getResultSeriesValues();

            for (Map.Entry<String, ProposalImpactSeriesValues> entry : ontologyTermIdToSeriesSumMap
                    .entrySet()) {

                final String sectorOntologyTermId = entry.getKey();
                final ProposalImpactSeriesValues integratedSeriesValues = entry.getValue();

                OntologyTerm sectorOntologyTerm =
                        OntologyClient.getOntologyTerm(Long.parseLong(sectorOntologyTermId));

                if (!(impactSeries.getWhatTerm().equals(sectorOntologyTerm) && impactSeries
                        .getWhereTerm().equals(regionOntologyTerm))) {
                    for (Integer year : impactSeriesValues.getYearToValueMap().keySet()) {
                        emptySeries.putSeriesValue(year, 0.);
                    }
                    integratedSeriesValues.addImpactSeriesValues(emptySeries);
                } else {
                    integratedSeriesValues.addImpactSeriesValues(impactSeriesValues);
                }
            }
        }

        return ontologyTermIdToSeriesSumMap;
    }

    public Map<String, ProposalImpactSeriesValues>
    getAggregatedWeightedSeriesValuesBySectorOntologyTermIds(
            List<Long> sectorOntologyTermIds) {
        Map<String, ProposalImpactSeriesValues> ontologyTermIdToSeriesSumMap =
                new HashMap<>(sectorOntologyTermIds.size());

        for (Long ontologyTermId : sectorOntologyTermIds) {
            ontologyTermIdToSeriesSumMap
                    .put(ontologyTermId.toString(), new ProposalImpactSeriesValues());
        }

        ProposalImpactSeriesValues emptySeries = new ProposalImpactSeriesValues();

        for (ProposalImpactSeries impactSeries : impactSerieses) {

            ProposalImpactSeriesValues impactSeriesValues = impactSeries.getResultSeriesValues();

            for (Map.Entry<String, ProposalImpactSeriesValues> entry : ontologyTermIdToSeriesSumMap
                    .entrySet()) {

                final String sectorOntologyTermId = entry.getKey();
                final ProposalImpactSeriesValues integratedSeriesValues = entry.getValue();

                OntologyTerm sectorOntologyTerm =
                        OntologyClient.getOntologyTerm(Long.parseLong(sectorOntologyTermId));

                if (!(impactSeries.getWhatTerm().equals(sectorOntologyTerm))) {
                    for (Integer year : impactSeriesValues.getYearToValueMap().keySet()) {
                        emptySeries.putSeriesValue(year, 0.);
                    }
                    integratedSeriesValues.addImpactSeriesValues(emptySeries);
                } else {
                    OntologyTerm ontologyRegionTerm = impactSeries.getWhereTerm();
                    Map<Integer, Double> yearToValueFactor =
                            ONTOLOGY_REGION_TERM_TO_YEAR_TO_VALUE_FACTOR
                                    .get(ontologyRegionTerm.getId_());
                    integratedSeriesValues
                            .addImpactSeriesValues(impactSeriesValues, yearToValueFactor);
                }
            }
        }

        return ontologyTermIdToSeriesSumMap;
    }
}
