package org.climatecollaboratorium.navigation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.climatecollaboratorium.events.Event;

public class NavigationEvent implements Event, Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, String>> parameters = new HashMap<>();
    private String resultName;
    
    public NavigationEvent(Map<String, Map<String, String>> sourcesAndParameters) {
        parameters = sourcesAndParameters;
    }
    
    public boolean hasSource(String source) {
        return parameters.containsKey(source);
    }
    
    public Map<String, String> getParameters(String source) {
        return parameters.get(source);
    }
    
    public Set<String> getSources() {
        return parameters.keySet();
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }
}
