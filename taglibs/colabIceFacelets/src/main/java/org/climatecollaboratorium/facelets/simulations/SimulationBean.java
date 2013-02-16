package org.climatecollaboratorium.facelets.simulations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.facelets.simulations.support.ModelDisplayWrapper;
import org.climatecollaboratorium.facelets.simulations.support.ModelInputDisplayItemWrapper;
import org.climatecollaboratorium.facelets.simulations.support.ModelInputGroupDisplayItemWrapper;
import org.climatecollaboratorium.facelets.simulations.support.ModelOutputErrorSettingWrapper;
import org.climatecollaboratorium.facelets.simulations.support.SimulationsHelper;
import org.climatecollaboratorium.facelets.simulations.support.SupportBean;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItemType;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItemType;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelOutputSeriesDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.TupleStatus;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;

public class SimulationBean {

    private Long lastInitSimulationId;
    private Long lastInitScenarioId;
    private Simulation simulation;
    private Scenario scenario;
    private boolean editing;
    private String description;
    private ModelDisplayWrapper display;
    private boolean embeddedEditing;
    private boolean firstRun = false;
    private Map<ModelInputDisplayItem, ModelInputDisplayItemWrapper> wrappedInputs = new HashMap<ModelInputDisplayItem, ModelInputDisplayItemWrapper>();
    private List<ModelOutputErrorSettingWrapper> outputErrorSettingWrappers = new ArrayList<ModelOutputErrorSettingWrapper>();
    
    private EventBus eventBus;
    private final static Log _log = LogFactoryUtil.getLog(SimulationBean.class);
    
    public SimulationBean() {
    }

    public boolean isEmbeddedEditing() {
        return embeddedEditing;
    }

    public void setEmbeddedEditing(boolean embeddedEditing) {
        this.embeddedEditing = embeddedEditing;
    }

    private Map<Long, Object> inputsValues = new HashMap<Long, Object>();
    private boolean scenarioSaved;
    private ModelInputGroupDisplayItemWrapper newGroupWrapper;

    public Simulation getSimulation() {
        return simulation;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public ModelDisplayWrapper getDisplay() {
        return display;
    }

    public void setSimulation(Simulation simulation) throws SystemException, IllegalUIConfigurationException, IOException {
        if (simulation == null) {
            this.simulation = null;
            return;
        }

        this.simulation = simulation;
        scenario = null;
        inputsValues.clear();
        editing = false;

        updateDisplay(false);

    }

    public void setScenario(Scenario scenario) throws SystemException, IllegalUIConfigurationException, IOException {
        if (scenario == null) {
            this.simulation = null;
            this.scenario = null;
            return;
        }

        this.scenario = scenario;
        this.simulation = scenario.getSimulation();
        scenarioSaved = false;
        
        updateInputValues();
        editing = false;

        updateDisplay(false);

    }
    
    private void updateInputValues() {

        inputsValues.clear();
        if (scenario != null) {
            for (Variable var: scenario.getInputSet()) {
                inputsValues.put(var.getMetaData().getId(), var.getValue().get(0).getValues()[0]);
            }
        }
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public boolean isFirstRun() {
        return firstRun;
    }

    public String getDescription() {
        return simulation.getDescription();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateSimulation(ActionEvent event) throws IOException, ModelNotFoundException, SystemException {
        simulation.setDescription(description);
        // FIXME removing updateSimulation as it isn't available in client 2.0
        //SimulationsHelper.getInstance().getRepository().updateSimulation(simulation);
        editing = false;
    }

    public void editSimulation(ActionEvent event) {
        editing = true;
        outputErrorSettingWrappers.clear();
        for (ModelOutputDisplayItem item: getAllOutputsFromDisplay()) {
            if (item instanceof ModelOutputSeriesDisplayItem || item instanceof ModelOutputIndexedDisplayItem) {
                for (TupleStatus status: TupleStatus.values()) {
                    if (status.getCode() != null) {
                        outputErrorSettingWrappers.add(new ModelOutputErrorSettingWrapper(item, status, this));
                    }
                }
            }
        }
        
    }

    public void cancelEditing(ActionEvent event) {
        editing = false;
    }

    public String getInputsJSON() {
        return JSONArray.fromObject(simulation.getInputs()).toString();
    }

    public void setInputsJSON(String x) {
    }


    /** Runs the model */
    public void runSimulation(ActionEvent event) {
        // JSONObject.fromevent.getPayload();
        try {
            Map<Long, Object> inputs = display.getInputsValues();
            _log.debug("Running simulation " + simulation.getId());
            firstRun = scenario == null;
            scenario = SimulationsHelper.getInstance().runSimulation(simulation, inputs);
            _log.debug("Scenario id after run: " + scenario.getId());

            for (Long id: inputs.keySet()) {
                inputsValues.put(id, inputs.get(id));
            }
            
            for (Variable var: scenario.getInputSet()) {
                if (! var.getValue().isEmpty() && var.getValue().get(0).getValues().length > 0 && var.getValue().get(0).getValues()[0] != null) {
                    inputsValues.put(var.getMetaData().getId(), var.getValue().get(0).getValues()[0]);
                }
            }

            updateDisplay(true);
            
            scenarioSaved = false;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ScenarioNotFoundException e) {
            e.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            // FIXME
            e.printStackTrace();
        }
    }

    public Map<Long, Object> getInputValues() {
        return inputsValues;
    }
    
    public String getInputValuesJson() {
        Map<String, String> tmpVals = new HashMap<String, String>();
        for (Map.Entry<Long, Object> entry: inputsValues.entrySet()) {
            tmpVals.put(entry.getKey().toString(), String.valueOf(entry.getValue()));
        }
        return JSONObject.fromObject(tmpVals).toString();
    }

    public void editActions(ActionEvent e) throws SystemException, IllegalUIConfigurationException, IOException {
        embeddedEditing = !embeddedEditing;
        scenarioSaved = false;
        if (! embeddedEditing) {
            // revert input values to old ones
            updateInputValues();
            updateDisplay(false);
        }
        eventBus.fireEvent(new ScenarioEditEvent(embeddedEditing));
    }

    public void saveScenario(ActionEvent e) throws ScenarioNotFoundException, IOException, SystemException {
        if (scenario != null) {
            SimulationsHelper.getInstance().saveScenario(scenario);
        }
        embeddedEditing = false;
        eventBus.fireEvent(new ScenarioSavedEvent(scenario));
    }

    public boolean isScenarioSaved() {
        return scenarioSaved;
    }

    public void setScenarioSaved(boolean scenarioSaved) {
        this.scenarioSaved = scenarioSaved;
    }
    
    public void updateInputs(ActionEvent e) throws SystemException, IllegalUIConfigurationException, IOException {
        updateDisplay(false);
    }
    
    
    public Map<ModelInputDisplayItem, ModelInputDisplayItemWrapper> getWrappedInputs() {
        return wrappedInputs;
    }
    
    public void updateDisplay(boolean reuseInputs) throws SystemException, IllegalUIConfigurationException, IOException {
        List<ModelInputDisplayItemWrapper> oldDisplayInputs = new ArrayList<ModelInputDisplayItemWrapper>();
        if (display != null) {
            /*
            System.out.println("Old display: " + String.valueOf(display.hashCode()));
            for (ModelInputDisplayItemWrapper input: display.getWrappedInputs() ) {
                System.out.println("\t" + input.getName() + ": " + String.valueOf(input.hashCode()));
            }
            */
            oldDisplayInputs = display.getWrappedInputs();
        }
        if (scenario == null && simulation == null) {
        	_log.error("Simulation and scenario not set");
        	return;
        }
        if (scenario != null) {
            display = new ModelDisplayWrapper(ModelUIFactory.getInstance().getDisplay(scenario), this, inputsValues);
        }
        else {
            display = new ModelDisplayWrapper(ModelUIFactory.getInstance().getDisplay(simulation), this, inputsValues);
        } 
        if (reuseInputs) {
            display.reuseInputs(oldDisplayInputs);
        }

        /*
         
         System.out.println("New display: " + String.valueOf(display.hashCode()));
         
        for (ModelInputDisplayItemWrapper input: display.getWrappedInputs() ) {
            System.out.println("\t" + input.getName() + ": " + String.valueOf(input.hashCode()));
        }
        */
        
        wrappedInputs.clear();
        
        for (ModelInputDisplayItem item: display.getWrapped().getInputs()) {
            wrappedInputs.put(item, ModelInputDisplayItemWrapper.getInputWrapper(item, this, inputsValues));
        }

        newGroupWrapper = new ModelInputGroupDisplayItemWrapper(this);
    }
    
    public List<SelectItem> getModelInputsOptions() {
        return SupportBean.getModelInputsOptions(display.getWrapped());
    }
    
    public ModelInputGroupDisplayItemWrapper getNewGroupWrapper() {
        return newGroupWrapper;
    }
    
    public List<ModelInputDisplayItem> getIndividualInputsFromDisplay() {
        List<ModelInputDisplayItem> inputs = new ArrayList<ModelInputDisplayItem>();
        for (ModelInputDisplayItem input: display.getWrapped().getInputs()) {
            if (input.getDisplayItemType() == ModelInputDisplayItemType.INDIVIDUAL) {
                inputs.add(input);
            }
            else {
                for (ModelInputDisplayItem groupedInput: ((ModelInputGroupDisplayItem) input).getDisplayItems()) {
                    inputs.add(groupedInput);
                }
            }
        }
        return inputs;
    }
    
    public List<ModelOutputDisplayItem> getAllOutputsFromDisplay() {
        List<ModelOutputDisplayItem> outputs = new ArrayList<ModelOutputDisplayItem>();
        for (ModelOutputDisplayItem output: display.getOutputs()) {
            outputs.add(output);
            if (output.getDisplayItemType() == ModelOutputDisplayItemType.INDEXED) {
                for (ModelOutputDisplayItem serie: ((ModelOutputIndexedDisplayItem) output).getSeries()) {
                    outputs.add(serie);
                }
            }
        }
        return outputs;
    }
    
    public Map<ModelOutputDisplayItem, List<SelectItem>> getOutputAssociationOptions() {
        Map<ModelOutputDisplayItem, List<SelectItem>> itemsMap = new HashMap<ModelOutputDisplayItem, List<SelectItem>>();
        for (ModelOutputDisplayItem output: display.getOutputs()) {
            if (output.getDisplayItemType() == ModelOutputDisplayItemType.INDEXED) {
                List<SelectItem> options = new ArrayList<SelectItem>();
                for (ModelOutputDisplayItem serie: ((ModelOutputIndexedDisplayItem) output).getSeries()) {
                    itemsMap.put(serie, options);
                    options.add(new SelectItem( ((ModelOutputSeriesDisplayItem) serie).getMetaData().getId(), serie.getName()));
                }
            }
        }
        return itemsMap;
    }
    
    public List<ModelOutputErrorSettingWrapper> getOutputErrorSettingWrappers() {
        return outputErrorSettingWrappers;
    }
    

    public void init(Long simulationId, Long scenarioId, Boolean edit) throws SystemException, IOException, IllegalUIConfigurationException {
        
        if (((simulationId == lastInitSimulationId || (simulationId != null && simulationId.equals(lastInitSimulationId))) &&
                (scenarioId == lastInitScenarioId || (scenarioId != null && scenarioId.equals(lastInitScenarioId)))) ||
                ((lastInitSimulationId != null && lastInitSimulationId > 0 && (simulationId == null || simulationId == 0)))) {
            // ||
            // (lastInitScenarioId != null && lastInitScenarioId > 0 && (scenarioId == null || scenarioId == 0)))) {
            // same values are used, do nothing 
            return;
        }
        lastInitSimulationId = simulationId;
        lastInitScenarioId = scenarioId;
        
        if (edit != null && edit) {
            editActions(null);
        }
        
        // check if scenario exist
        Exception exception = null; 
        if (scenarioId != null && scenarioId > 0) {
            try {
                Scenario scenario = CollaboratoriumModelingService.repository().getScenario(scenarioId);
                if (scenario != null) {
                    setScenario(scenario);
                    return;
                }
            } 
            catch (Exception ioE) {
                _log.debug("Can't read scenario with id: " + scenarioId, ioE);
                exception = ioE;
            }
            
        }
        if (simulationId != null && simulationId > 0) {
            Simulation simulation = CollaboratoriumModelingService.repository().getSimulation(simulationId);
            if (simulation != null) {
                setSimulation(simulation);
                return;
            }
            
        }
        
        if (exception != null) {
            throw new SystemException(exception);
        }
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public boolean getHasTabs() {
        return display != null ? display.hasTabs() : false;
     }
}
