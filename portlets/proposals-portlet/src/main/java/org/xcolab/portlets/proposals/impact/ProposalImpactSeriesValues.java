package org.xcolab.portlets.proposals.impact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.xcolab.client.contest.pojo.impact.ImpactIteration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ProposalImpactSeriesValues represents a data series for exactly one category of data (i.e. BAU, adoption rate, ...).
 */
public class ProposalImpactSeriesValues {
    private final static Logger _log = LoggerFactory.getLogger(ProposalImpactSeriesValues.class);

    private final Map<Integer, Double> yearToValueMap = new HashMap<>();

    public double getValueForYear(int year) {
        Double valueForYear = yearToValueMap.get(year);
        if (valueForYear == null) {
            _log.warn("No value found for year {}", year);
            return 0;
        }
        return valueForYear;
    }

    public Map<Integer, Double> getYearToValueMap() {
        return yearToValueMap;
    }

    public void putSeriesValue(int year, double value) {
        yearToValueMap.put(year, value);
    }

    public void addImpactSeriesValues(ProposalImpactSeriesValues otherSeriesValues) {
        Set<Integer> mergedYearKeySet = new HashSet<>(yearToValueMap.keySet());
        mergedYearKeySet.addAll(otherSeriesValues.yearToValueMap.keySet());

        for (Integer yearKey : mergedYearKeySet) {
            // Get values in fail-safe manner
            double thisValue = getValueForYear(yearKey);
            double otherValue = otherSeriesValues.getValueForYear(yearKey);
            yearToValueMap.put(yearKey, (thisValue + otherValue));
        }
    }

    public void addImpactSeriesValues(ProposalImpactSeriesValues anotherSeriesValues, Map<Integer, Double> yearToValueFactor) {
        Set<Integer> mergedYearKeySet = new HashSet<>(yearToValueMap.keySet());
        mergedYearKeySet.addAll(anotherSeriesValues.yearToValueMap.keySet());

        for (Integer yearKey : mergedYearKeySet) {
            // Get values in fail-safe manner
            double thisValue = getValueForYear(yearKey);
            double otherValue = anotherSeriesValues.getValueForYear(yearKey);
            Double yearFactor = yearToValueFactor.get(yearKey);
            if (yearFactor != null) {
                yearToValueMap.put(yearKey, (thisValue + otherValue * yearFactor));
            } else {
                yearToValueMap.put(yearKey, 0.0);
            }
        }
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
