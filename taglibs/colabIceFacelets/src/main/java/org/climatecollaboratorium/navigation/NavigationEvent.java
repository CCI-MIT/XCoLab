package org.climatecollaboratorium.navigation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.climatecollaboratorium.events.Event;

public class NavigationEvent implements Event {
    
    private Map<String, Map<String, String>> parameters = new HashMap<String, Map<String,String>>();
    
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
    
    
    
    

}
