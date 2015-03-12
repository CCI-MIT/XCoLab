package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesDataLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import org.xcolab.portlets.proposals.utils.ProposalImpactUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A collection of ProposalImpactSeries representing all data series of each sector-region pair.
 *
 * Created by kmang on 12/03/15.
 */
public class ProposalImpactSeriesList {
    private List<ProposalImpactSeries> impactSerieses;

    public ProposalImpactSeriesList(List<ProposalAttribute> proposalImpactAttributes, List<ImpactIteration> impactIterations)
            throws PortalException, SystemException {

        Map<Long, ProposalImpactSeries> focusAreaIdToImpactSeriesMap = new HashMap<>();

        for (ProposalAttribute attribute : proposalImpactAttributes) {
            // Get the impact series for the respective focus area
            FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(attribute.getAdditionalId());
            ProposalImpactSeries impactSeries = focusAreaIdToImpactSeriesMap.get(focusArea.getId()); // additionalId = focusAreaId
            if (Validator.isNull(impactSeries)) {
                impactSeries = new ProposalImpactSeries(focusArea, impactIterations);
                focusAreaIdToImpactSeriesMap.put(focusArea.getId(), impactSeries);
            }

            // Add impact series value for the specified impact type (BAU,...)
            int year = (int)attribute.getNumericValue();
            double value = attribute.getRealValue();
            String impactType = attribute.getName();
            impactSeries.addSeriesValueWithType(impactType, year, value);
        }

        this.impactSerieses = new ArrayList<ProposalImpactSeries>(focusAreaIdToImpactSeriesMap.values());
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

    public List<ProposalImpactSeries> getImpactDefaultSerieses() {
        return impactSerieses;
    }


    /**
     * ProposalImpactSeries represents the data series for one sector-region pair.
     */
    public class ProposalImpactSeries {
        // Static value field names
        private static final String SERIES_TYPE_BAU_KEY = "BAU";
        private static final String SERIES_TYPE_DDPP_KEY = "DDPP";


        private List<ImpactIteration> impactIterations;
        private OntologyTerm whatTerm;
        private OntologyTerm whereTerm;
        private Map<String, ProposalImpactSeriesValues> seriesTypeToSeriesMap;

        private ImpactDefaultSeries bauSeries;

        private ProposalImpactSeriesValues resultValues;

        public ProposalImpactSeries(Map<String, ProposalImpactSeriesValues> seriesYearValuesMap, List<ImpactIteration> impactIterations,
                                    FocusArea focusArea) throws SystemException, PortalException {
            this.seriesTypeToSeriesMap = seriesYearValuesMap;
            this.impactIterations = impactIterations;
            this.whatTerm = ProposalImpactUtil.getWhatTerm(focusArea);
            this.whereTerm = ProposalImpactUtil.getWhereTerm(focusArea);

            // Retrieve static serieses
            bauSeries = ImpactDefaultSeriesLocalServiceUtil.getImpactDefaultSeriesWithFocusAreaAndName(focusArea, SERIES_TYPE_BAU_KEY);
        }

        public ProposalImpactSeries(FocusArea focusArea, List<ImpactIteration> impactIterations) throws PortalException, SystemException {
            this(new HashMap<String, ProposalImpactSeriesValues>(), impactIterations, focusArea);
        }

        public void addSeriesValueWithType(String seriesType, int year, double value) {
            ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);
            if (Validator.isNull(seriesValues)) {
                seriesValues = new ProposalImpactSeriesValues();
                seriesTypeToSeriesMap.put(seriesType, seriesValues);
            }

            seriesValues.putSeriesValue(year, value);
        }

        public Map<String, ProposalImpactSeriesValues> getSeriesTypeToSeriesMap() {
            return seriesTypeToSeriesMap;
        }

        public ProposalImpactSeriesValues getResultSeriesValues() throws PortalException, SystemException {
            if (Validator.isNull(resultValues)) {
                calculateResultSeriesValues();
            }

            return resultValues;
        }

        /**
         * Calculate the result values for each time point in the Iteration
         */
        private void calculateResultSeriesValues() throws SystemException, PortalException {
            resultValues = new ProposalImpactSeriesValues();
            for (ImpactIteration impactIteration : impactIterations) {
                int currentYear = impactIteration.getYear();

                double bauValue =
                        ImpactDefaultSeriesDataLocalServiceUtil.getDefaultSeriesDataBySeriesIdAndYear(bauSeries.getSeriesId(),
                                (int) currentYear).getValue();

                double reductionRate = seriesTypeToSeriesMap.get(ProposalAttributeKeys.IMPACT_REDUCTION).getValueForYear(currentYear);
                double adoptionRate = seriesTypeToSeriesMap.get(ProposalAttributeKeys.IMPACT_ADOPTION_RATE).getValueForYear(currentYear);

                resultValues.putSeriesValue(currentYear, (bauValue * (1.0 - reductionRate * adoptionRate)));
            }
        }

        public OntologyTerm getWhatTerm() {
            return whatTerm;
        }

        public OntologyTerm getWhereTerm() {
            return whereTerm;
        }
    }

    /**
     * ProposalImpactSeriesValues represents a data series for exactly one category of data (i.e. BAU, adoption rate, ...).
     */
    public class ProposalImpactSeriesValues {
        private Map<Integer, Double> yearToValueMap;

        public ProposalImpactSeriesValues() {
            yearToValueMap = new HashMap<>();
        }

        public Map<Integer, Double> getYearToValueMap() {
            return yearToValueMap;
        }

        public void putSeriesValue(int year, double value) {
            yearToValueMap.put(year, value);
        }

        public double getValueForYear(int year) {
            return yearToValueMap.get(year);
        }
    }
}
