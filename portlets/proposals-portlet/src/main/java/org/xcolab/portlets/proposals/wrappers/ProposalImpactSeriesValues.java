package org.xcolab.portlets.proposals.wrappers;

/**
 * Created by kmang on 23/03/15.
 */

import com.ext.portlet.model.ImpactIteration;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public void addImpactSeriesValues(ProposalImpactSeriesValues anotherSeriesValues) {
        Set<Integer> mergedYearKeySet = new HashSet<Integer>(yearToValueMap.keySet());
        mergedYearKeySet.addAll(anotherSeriesValues.yearToValueMap.keySet());
        for (Integer yearKey : mergedYearKeySet) {
            // Get values in fail-safe manner
            double thisValue = Validator.isNotNull(yearToValueMap.get(yearKey)) ? yearToValueMap.get(yearKey) : 0.0;
            double otherValue = Validator.isNotNull(anotherSeriesValues.getValueForYear(yearKey)) ? anotherSeriesValues.getValueForYear(yearKey) : 0.0;
            yearToValueMap.put(yearKey, (thisValue + otherValue));
        }
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

    public boolean isEmpty() {
        return yearToValueMap.isEmpty();
    }
}
