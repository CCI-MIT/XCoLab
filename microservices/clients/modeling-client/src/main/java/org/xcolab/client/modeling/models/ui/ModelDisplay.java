package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelDisplay implements Serializable {

    private static final Logger _log = LoggerFactory.getLogger(ModelDisplay.class);

	private static final long serialVersionUID = 1L;
	private final List<ModelInputDisplayItem> inputs;
    private final List<ModelOutputDisplayItem> outputs;

    private final List<ModelInputGroupDisplayItem> tabs = new ArrayList<>();
    private final List<ModelInputGroupDisplayItem> groups = new ArrayList<>();
    private final Map<MetaData, ModelInputIndividualDisplayItem> individuals = new HashMap<>();
    private final List<ModelInputDisplayItem> nonTabs = new ArrayList<>();

    public ModelDisplay(Simulation sim) throws IllegalUIConfigurationException, IOException {
        inputs = ModelUIFactory.getInstance().parseInputs(sim);
        for (ModelInputDisplayItem item:inputs) {
            if (item instanceof ModelInputGroupDisplayItem) {
                if (((ModelInputGroupDisplayItem)item).getGroupType()== ModelInputGroupType.TAB) {
                    tabs.add((ModelInputGroupDisplayItem) item);
                } else {
                    groups.add((ModelInputGroupDisplayItem) item);
                }
            } else if (item instanceof ModelInputIndividualDisplayItem) {
               individuals.put(item.getMetaData(),(ModelInputIndividualDisplayItem)item);
            }
        }
        nonTabs.addAll(inputs);
        nonTabs.removeAll(tabs);
        outputs = ModelUIFactory.getInstance().parseOutputs(sim);

    }

    public ModelDisplay(Scenario scenario) throws IllegalUIConfigurationException, IOException {
        this(scenario.getSimulation());
        try {
            setScenario(scenario);
        } catch (IncompatibleScenarioException e) {
            _log.error("Cannot set scenario", e);

        }
    }

    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        for (ModelDisplayItem input : getInputs()) {
            input.setScenario(s);
        }
        for (ModelDisplayItem output : getOutputs()) {
            output.setScenario(s);
        }
    }

    public List<ModelInputDisplayItem> getInputs() {
        Collections.sort(inputs);
        return inputs;
    }

    public List<ModelInputGroupDisplayItem> getTabs() {
        Collections.sort(tabs);
        return tabs;
    }

    public List<ModelInputDisplayItem> getNonTabs() {
       Collections.sort(nonTabs);
        return nonTabs;
    }
    
    public List<ModelInputGroupDisplayItem> getGroups() {
    	Collections.sort(groups);
    	return groups;
    }

    public List<ModelInputDisplayItem> getAllIndividualInputs() {
        List<ModelInputDisplayItem> result = new ArrayList<>();
        for (ModelInputDisplayItem item:getInputs()) {
            result.addAll(collectItems(item));
        }
        return result;
    }

    private List<ModelInputDisplayItem> collectItems(ModelInputDisplayItem item) {
        List<ModelInputDisplayItem> result = new ArrayList<>();
        if (item instanceof ModelInputGroupDisplayItem) {
            for (ModelInputDisplayItem child:((ModelInputGroupDisplayItem)item).getAllItems()) {
                result.addAll(collectItems(child));
            }
        } else {
            result.add(item);
        }
        return result;
    }

    public List<ModelOutputDisplayItem> getOutputs() {
        outputs.sort((o1, o2) -> {
            int compareResult = o1.compareTo(o2);
            if (compareResult != 0) {
                return compareResult;
            }
            if (o1.getOrder() != -1) {
                return compareResult;
            }

            return o1.getName().compareTo(o2.getName());
        });
        return outputs;
    }

}
