package org.xcolab.view.pages.proposals.impact;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.pojo.IImpactIteration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ProposalImpactSeriesValues represents a data series for exactly one category of data (i.e. BAU, adoption rate, ...).
 */
public class ProposalImpactSeriesValues {
    private static final Logger _log = LoggerFactory.getLogger(ProposalImpactSeriesValues.class);

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

    public JSONArray toJSONArrayWithIteration(List<IImpactIteration> iterations) {
        JSONArray jsonArray = new JSONArray();

        for (IImpactIteration iteration : iterations) {
            JSONObject jsonValue = new JSONObject();
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
