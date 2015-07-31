package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalImpactAttributeKeys;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * This class decides whether impact series values are valid for a given impact series type (i.e. ProposalImpactAttributeKeys.IMPACT_REDUCTION)
 *
 * Created by kmang on 04/05/15.
 */
public abstract class ProposalImpactValueFilterAlgorithm {
    private final static Log _log = LogFactoryUtil.getLog(ProposalImpactValueFilterAlgorithm.class);

    private static String[] impactSeriesTypes = {ProposalImpactAttributeKeys.IMPACT_REDUCTION,
            ProposalImpactAttributeKeys.IMPACT_ADOPTION_RATE};
    private static ProposalImpactValueFilterAlgorithm[] algorithms = {
            // ProposalImpactAttributeKeys.IMPACT_REDUCTION
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
            // ProposalImpactAttributeKeys.IMPACT_ADOPTION_RATE
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
    public static double filterValueForImpactSeriesType(double value, String seriesType) throws SystemException {
        if (Validator.isNull(seriesTypeToAlgorithmMap)) {
            initMap();
        }

        ProposalImpactValueFilterAlgorithm algorithm = seriesTypeToAlgorithmMap.get(seriesType);
        if (Validator.isNotNull(algorithm)) {
            return algorithm.filterValue(value);
        }

        throw new SystemException("Could not find a ProposalImpactValueFilterAlgorithm for the impact series type " + seriesType + "!");
    }

    abstract double filterValue(double value);
}
