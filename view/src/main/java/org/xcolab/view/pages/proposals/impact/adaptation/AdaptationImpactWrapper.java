package org.xcolab.view.pages.proposals.impact.adaptation;

import java.util.HashMap;
import java.util.Map;

public class AdaptationImpactWrapper {

    private Map<String, AdaptationValue> values;

    public AdaptationImpactWrapper() {

    }

    public AdaptationImpactWrapper(HashMap<String, AdaptationValue> values) {
        this.values = values;
    }

    public Map<String, AdaptationValue> getValues() {
        return values;
    }

    public void setValues(Map<String, AdaptationValue> values) {
        this.values = values;
    }

    public void save() {
        //TODO: store values
    }
}
