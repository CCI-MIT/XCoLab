package org.xcolab.view.pages.proposals.impact;

import org.xcolab.client.contest.proposals.enums.ImpactSeriesType;

import java.util.HashMap;
import java.util.Map;

/**
 * This class decides whether impact series values are valid for a given impact series type (i.e. ImpactSeriesType.IMPACT_REDUCTION)
 */
public abstract class ProposalImpactValueFilterAlgorithm {

    private static final String[] impactSeriesTypes = {ImpactSeriesType.IMPACT_REDUCTION.name(),
            ImpactSeriesType.IMPACT_ADOPTION_RATE.name()};
    private static final ProposalImpactValueFilterAlgorithm[] algorithms = {
            // ImpactSeriesType.IMPACT_REDUCTION
            new ProposalImpactValueFilterAlgorithm() {

                @Override
                double filterValue(double value) {
                    if (value > 100.0) {
                        value = 100.0;
                    } else if (value < -100.0) {
                        value = -100.0;
                    }

                    return value;
                }
            },
            // ImpactSeriesType.IMPACT_ADOPTION_RATE
            new ProposalImpactValueFilterAlgorithm() {

                @Override
                double filterValue(double value) {
                    if (value > 100.0) {
                        value = 100.0;
                    } else if (value < 0.0) {
                        value = 0.0;
                    }

                    return value;
                }
            }
    };


    private static Map<String, ProposalImpactValueFilterAlgorithm> seriesTypeToAlgorithmMap;

    private static void initMap() {
        seriesTypeToAlgorithmMap = new HashMap<>(impactSeriesTypes.length);
        for (int i = 0; i < impactSeriesTypes.length; i++) {
            seriesTypeToAlgorithmMap.put(impactSeriesTypes[i], algorithms[i]);
        }

    }

    /**
     * Filters the passed value if it is not allowed for the passed Proposal Impact series type
     *
     * @param value         The value that should be sanitized
     * @param seriesType    The ProposalImpactAttributeKey in question
     * @return              sanitized proposal impact value
     */
    public static double filterValueForImpactSeriesType(double value, String seriesType) {
        if (seriesTypeToAlgorithmMap == null) {
            initMap();
        }

        ProposalImpactValueFilterAlgorithm algorithm = seriesTypeToAlgorithmMap.get(seriesType);
        if (algorithm != null) {
            return algorithm.filterValue(value);
        }

        throw new RuntimeException("Could not find a ProposalImpactValueFilterAlgorithm for the impact series type " + seriesType + "!");
    }

    abstract double filterValue(double value);
}
