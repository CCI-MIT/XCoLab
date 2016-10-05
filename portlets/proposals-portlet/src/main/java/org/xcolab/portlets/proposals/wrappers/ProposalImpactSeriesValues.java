package org.xcolab.portlets.proposals.wrappers;


import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import org.xcolab.client.contest.pojo.ImpactIteration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ProposalImpactSeriesValues represents a data series for exactly one category of data (i.e. BAU, adoption rate, ...).
 */
public class ProposalImpactSeriesValues {
    private final static Log _log = LogFactoryUtil.getLog(ProposalImpactSeriesValues.class);
    private final Map<Integer, Double> yearToValueMap = new HashMap<>();

    public Map<Integer, Double> getYearToValueMap() {
        return yearToValueMap;
    }

    public void putSeriesValue(int year, double value) {
        yearToValueMap.put(year, value);
    }

    public double getValueForYear(int year) {
        double valueForYear = 0.;
        try{
            valueForYear = yearToValueMap.get(year);
        } catch (NullPointerException e){
            _log.warn("Could not getValueForYear" + year, e);
        }
        return valueForYear;
    }

    public void addImpactSeriesValues(ProposalImpactSeriesValues anotherSeriesValues) {
        Set<Integer> mergedYearKeySet = new HashSet<>(yearToValueMap.keySet());
        mergedYearKeySet.addAll(anotherSeriesValues.yearToValueMap.keySet());

        for (Integer yearKey : mergedYearKeySet) {
            // Get values in fail-safe manner
            double thisValue = Validator.isNotNull(yearToValueMap.get(yearKey)) ? yearToValueMap.get(yearKey) : 0.0;
            double otherValue = Validator.isNotNull(anotherSeriesValues.getValueForYear(yearKey)) ? anotherSeriesValues.getValueForYear(yearKey) : 0.0;
            yearToValueMap.put(yearKey, (thisValue + otherValue));
        }

    }

    public void addImpactSeriesValues(ProposalImpactSeriesValues anotherSeriesValues, Map<Integer, Double> yearToValueFactor) {
        Set<Integer> mergedYearKeySet = new HashSet<>(yearToValueMap.keySet());
        mergedYearKeySet.addAll(anotherSeriesValues.yearToValueMap.keySet());

        for (Integer yearKey : mergedYearKeySet) {
            // Get values in fail-safe manner
            double thisValue = Validator.isNotNull(yearToValueMap.get(yearKey)) ? yearToValueMap.get(yearKey) : 0.0;
            double otherValue = Validator.isNotNull(anotherSeriesValues.getValueForYear(yearKey)) ? anotherSeriesValues.getValueForYear(yearKey) : 0.0;
            double yearFactor = Validator.isNotNull(yearToValueFactor.get(yearKey)) ? yearToValueFactor.get(yearKey) : 0.0;
            yearToValueMap.put(yearKey, (thisValue + otherValue * yearFactor));
        }

    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

        for (Map.Entry<Integer, Double> entry : yearToValueMap.entrySet()) {
            jsonObject.put(""+entry.getKey(),  entry.getValue());
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

    public boolean isEmpty() {
        return yearToValueMap.isEmpty();
    }
}
