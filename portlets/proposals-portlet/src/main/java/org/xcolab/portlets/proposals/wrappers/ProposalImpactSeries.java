package org.xcolab.portlets.proposals.wrappers;

/**
 * Created by kmang on 13/03/15.
 */

import com.ext.portlet.NoSuchImpactDefaultSeriesException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.model.ImpactDefaultSeriesData;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.ImpactDefaultSeriesDataLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import org.xcolab.portlets.proposals.utils.ProposalImpactUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private FocusArea focusArea;
    private Map<String, ProposalImpactSeriesValues> seriesTypeToSeriesMap;
    private Map<String, Boolean> seriesTypeToEditableMap;

    private ImpactDefaultSeries bauSeries;

    private ProposalImpactSeriesValues resultValues;

    public ProposalImpactSeries(Map<String, ProposalImpactSeriesValues> seriesYearValuesMap,
                                Map<String, Boolean> seriesTypeToEditableMap,
                                List<ImpactIteration> impactIterations,
                                FocusArea focusArea) throws SystemException, PortalException {
        this.seriesTypeToSeriesMap = seriesYearValuesMap;
        this.seriesTypeToEditableMap = seriesTypeToEditableMap;
        this.impactIterations = impactIterations;
        this.focusArea = focusArea;
        this.whatTerm = ProposalImpactUtil.getWhatTerm(focusArea);
        this.whereTerm = ProposalImpactUtil.getWhereTerm(focusArea);

        // Retrieve static serieses
        bauSeries = ImpactDefaultSeriesLocalServiceUtil.getImpactDefaultSeriesWithFocusAreaAndName(focusArea, SERIES_TYPE_BAU_KEY);
        addSeriesWithType(bauSeries, false);
    }

    public ProposalImpactSeries(FocusArea focusArea, List<ImpactIteration> impactIterations) throws PortalException, SystemException {
        this(new HashMap<String, ProposalImpactSeriesValues>(), new HashMap<String, Boolean>(), impactIterations, focusArea);
    }

    public void addSeriesValueWithType(String seriesType, int year, double value) {
        ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);
        if (Validator.isNull(seriesValues)) {
            seriesValues = new ProposalImpactSeriesValues();
            seriesTypeToSeriesMap.put(seriesType, seriesValues);
            seriesTypeToEditableMap.put(seriesType, true);
        }

        seriesValues.putSeriesValue(year, value);
    }

    public void addSeriesWithType(String seriesType, List<ImpactDefaultSeriesData> seriesDataList, boolean editable) {
        ProposalImpactSeriesValues seriesValues = new ProposalImpactSeriesValues();
        seriesTypeToSeriesMap.put(seriesType, seriesValues);
        seriesTypeToEditableMap.put(seriesType, editable);

        for (ImpactDefaultSeriesData defaultDataPoint : seriesDataList) {
            seriesValues.putSeriesValue(defaultDataPoint.getYear(), defaultDataPoint.getValue());
        }
    }

    public void addSeriesWithType(ImpactDefaultSeries defaultSeries, boolean editable) throws SystemException {
        List<ImpactDefaultSeriesData> seriesDataList = ImpactDefaultSeriesDataLocalServiceUtil.
                getDefaultSeriesDataBySeriesId(defaultSeries.getSeriesId());
        addSeriesWithType(defaultSeries.getName(), seriesDataList, editable);
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

    public boolean getIsEditableForSeriesType(String seriesType) {
        return seriesTypeToEditableMap.get(seriesType);
    }

    public JSONObject toJSONObject() throws SystemException, NoSuchImpactDefaultSeriesException {
        JSONObject returnObject = JSONFactoryUtil.createJSONObject();
        JSONObject serieses = JSONFactoryUtil.createJSONObject();

        returnObject.put("focusAreaId", getFocusArea().getId());
        returnObject.put("serieses", serieses);
        for (String seriesType : seriesTypeToSeriesMap.keySet()) {
            ProposalImpactSeriesValues seriesValues = seriesTypeToSeriesMap.get(seriesType);
            ImpactDefaultSeries defaultSeries = ImpactDefaultSeriesLocalServiceUtil.getImpactDefaultSeriesWithFocusAreaAndName(focusArea, seriesType);

            JSONObject series = JSONFactoryUtil.createJSONObject();
            series.put("name", defaultSeries.getName());
            series.put("description", defaultSeries.getDescription());
            series.put("editable", defaultSeries.isEditable());
            series.put("values", seriesValues.toJSONArrayWithIteration(impactIterations));
            serieses.put(defaultSeries.getName(), series);
        }

        JSONArray iterations = JSONFactoryUtil.createJSONArray();
        for (ImpactIteration impactIteration : impactIterations) {
            iterations.put(impactIteration.getYear());
        }
        returnObject.put("iterations", iterations);

        return returnObject;
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

            resultValues.putSeriesValue(currentYear, (bauValue * (1.0 - reductionRate * 0.01 * adoptionRate * 0.01)));
        }
    }

    public OntologyTerm getWhatTerm() {
        return whatTerm;
    }

    public OntologyTerm getWhereTerm() {
        return whereTerm;
    }

    public FocusArea getFocusArea() {
        return focusArea;
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

        public JSONObject toJSONObect() {
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

            for (Integer year : yearToValueMap.keySet()) {
                jsonObject.put(""+year, yearToValueMap.get(year));
            }

            return jsonObject;
        }

        public JSONArray toJSONArrayWithIteration(List<ImpactIteration> iterations) {
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

            for (ImpactIteration iteration : iterations) {
                JSONObject jsonValue = JSONFactoryUtil.createJSONObject();
                jsonValue.put("year", iteration.getYear());
                jsonValue.put("value", yearToValueMap.get(iteration.getYear()));
                jsonArray.put(jsonValue);
            }

            return jsonArray;
        }
    }
}