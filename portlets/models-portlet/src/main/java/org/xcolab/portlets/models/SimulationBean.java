package org.xcolab.portlets.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.xcolab.portlets.models.jsintegration.JSEvent;
import org.xcolab.portlets.models.jsintegration.JSEventHandler;
import org.xcolab.portlets.models.jsintegration.JSEventManager;
import org.xcolab.portlets.models.suggest.ModelDisplayWrapper;
import org.xcolab.portlets.models.suggest.ModelInputDisplayItemWrapper;
import org.xcolab.portlets.models.suggest.ModelInputGroupDisplayItemWrapper;
import org.xcolab.portlets.models.suggest.ModelOutputErrorSettingWrapper;
import org.xcolab.portlets.models.suggest.SimulationsHelper;
import org.xcolab.portlets.models.suggest.SupportBean;

import ys.wikiparser.WikiParser;

import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItemType;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItemType;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelOutputSeriesDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import edu.mit.cci.simulation.client.MetaData;
import edu.mit.cci.simulation.client.Scenario;
import edu.mit.cci.simulation.client.Simulation;
import edu.mit.cci.simulation.client.TupleStatus;
import edu.mit.cci.simulation.client.Variable;
import edu.mit.cci.simulation.client.comm.ModelNotFoundException;
import edu.mit.cci.simulation.client.comm.ScenarioNotFoundException;


public class SimulationBean implements JSEventHandler {

    private Simulation simulation;
    private Scenario scenario;
    private boolean editing;
    private String description;
    private JSEventManager jsEventManager;
    private ModelDisplayWrapper display;
    private boolean embeddedEditing;
    private Map<ModelInputDisplayItem, ModelInputDisplayItemWrapper> wrappedInputs = new HashMap<ModelInputDisplayItem, ModelInputDisplayItemWrapper>();
    private List<ModelOutputErrorSettingWrapper> outputErrorSettingWrappers = new ArrayList<ModelOutputErrorSettingWrapper>();
    private static Log _log = LogFactoryUtil.getLog(SimulationBean.class);

    public boolean isEmbeddedEditing() {
        return embeddedEditing;
    }

    public void setEmbeddedEditing(boolean embeddedEditing) {
        this.embeddedEditing = embeddedEditing;
    }

    private List<SimulationChangedListener> simulationChangedListeners = new ArrayList<SimulationChangedListener>();

    private Map<Long, Object> inputsValues = new HashMap<Long, Object>();
    private boolean scenarioSaved;
    private ModelInputGroupDisplayItemWrapper newGroupWrapper;

    public SimulationBean() {
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public ModelDisplayWrapper getDisplay() {
        return display;
    }

    public boolean getHasTabs() {
       return display.hasTabs();
    }

    public void setSimulation(Simulation simulation) {
        if (simulation == null) {
            this.simulation = null;
            return;
        }

        this.simulation = simulation;
        scenario = null;
       // inputsValues.clear();
        editing = false;

        updateDisplay();

        JSEvent event = new JSEvent();
        event.setId("renderModelInputs");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload(simulation.getInputs());

        jsEventManager.sendEvent(event);

        for (SimulationChangedListener listener : simulationChangedListeners) {
            listener.onSimulationChanged(simulation, display.getWrapped());
        }
    }

    public void setScenario(Scenario scenario) {
        if (scenario == null) {
            this.simulation = null;
            this.scenario = null;
            return;
        }

        this.scenario = scenario;
        this.simulation = scenario.getSimulation();
        scenarioSaved = false;

       // inputsValues.clear();
        editing = false;

        updateDisplay();
        for (Variable var: scenario.getInputSet()) {
            inputsValues.put(var.getMetaData().getId(), var.getValue().get(0).getValues()[0]);
        }
        JSEvent event = new JSEvent();
        event.setId("modelRunSuccessful");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload(simulation.getInputs());

        jsEventManager.sendEvent(event);

        for (SimulationChangedListener listener : simulationChangedListeners) {
            listener.onSimulationChanged(simulation, display.getWrapped());
        }
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public String getDescription() {
        return simulation.getDescription();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateSimulation(ActionEvent event) throws IOException, ModelNotFoundException, SystemException, PortalException {
        simulation.setDescription(description);
        // FIXME updating simulations was turned off in new interface
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
                        //outputErrorSettingWrappers.add(new ModelOutputErrorSettingWrapper(item, status, this));
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

    public void setJSEventManager(JSEventManager jsEventManager) {
        this.jsEventManager = jsEventManager;
        jsEventManager.addJsEventHandler(this, "simulationInputsDefined");
        jsEventManager.addJsEventHandler(this, "modelRun");

        // simulationChangedListeners.add(e)
    }

    /** Runs the model */
    public void onJsEvent(JSEvent event) {
        // JSONObject.fromevent.getPayload();

        DynaBean bean = (DynaBean) event.getPayload();
        Map<Long, Object> inputs = new HashMap<Long, Object>();
        for (MetaData md : simulation.getInputs()) {
            try {
                Object value = bean.get(md.getId().toString());
                inputs.put(md.getId(), value);
                inputsValues.put(md.getId(), value.toString());
            }
            catch (Exception e) {
                // ignore
                e.printStackTrace();
                if (inputsValues.containsKey(md.getId())) {
                    inputs.put(md.getId(), inputsValues.get(md.getId()));
                }
                else {
                    inputs.put(md.getId(), md.getMin()[0]);
                }
            }
        }
        try {
            Map<Long, Object> vals = display.getInputsValues();
            scenario = SimulationsHelper.getInstance().runSimulation(simulation, inputs);
            for (Variable var: scenario.getInputSet()) {
                inputsValues.put(var.getId(), var.getValue().get(0).getValues()[0]);
            }


            updateDisplay();

            event.setId("modelRunSuccessful");
            event.setTimestamp(System.currentTimeMillis());
            event.setPayload(simulation.getInputs());

            jsEventManager.sendEvent(event);
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
    
    public void runModel(ActionEvent e) throws SystemException, IOException, ScenarioNotFoundException, ModelNotFoundException, PortalException {
        Map<Long, Object> values = display.getInputsValues();
        /*
        List<Long> inputIds = new ArrayList<Long>();
        Integer year = 2010;
        for (MetaData md: simulation.getInputs()) {
            inputIds.add(md.getId());
            if (! values.containsKey(md.getId())) {
                values.put(md.getId(), year.toString());
                year++;
            }
        }*/
        
        
        Map<Long, Object> vals = new HashMap<Long, Object>();
        inputsValues.putAll(values);
        
        for (Long id: inputsValues.keySet()) {
            vals.put(id, inputsValues.get(id).toString());
        }
        scenario = SimulationsHelper.getInstance().runSimulation(simulation, vals);

        updateDisplay();
        JSEvent event = new JSEvent();
        event.setId("modelRunSuccessful");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload(simulation.getInputs());

        jsEventManager.sendEvent(event);
        scenarioSaved = false;

    }

    public Map<Long, Object> getInputValues() {
        return inputsValues;
    }

    public void editActions(ActionEvent e) {
        embeddedEditing = !embeddedEditing;
        scenarioSaved = false;
    }

    public void saveScenario(ActionEvent e) throws ScenarioNotFoundException, IOException, SystemException, PortalException {
        SimulationsHelper.getInstance().saveScenario(scenario);

        JSEvent event = new JSEvent();

        event.setId("modelSaved");
        event.setTimestamp(System.currentTimeMillis());
        event.setPayload("");

        jsEventManager.sendEvent(event);
        embeddedEditing = false;

    }

    public boolean isScenarioSaved() {
        return scenarioSaved;
    }

    public void setScenarioSaved(boolean scenarioSaved) {
        this.scenarioSaved = scenarioSaved;
    }
    
    public void updateInputs(ActionEvent e) {
        updateDisplay();
    }
    
    
    public Map<ModelInputDisplayItem, ModelInputDisplayItemWrapper> getWrappedInputs() {
        return wrappedInputs;
    }
    
    public void updateDisplay() {
        display = null;
        /*
        if (scenario != null) {
            try {
            //display = new ModelDisplayWrapper(ModelUIFactory.getInstance().getDisplay(scenario), this, inputsValues);
            } catch (IllegalUIConfigurationException e) {
                e.printStackTrace();
            } catch (SystemException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
           
        }
        else {
            /*
            try {
              //  display = new ModelDisplayWrapper(ModelUIFactory.getInstance().getDisplay(simulation), this, inputsValues);
            } catch (SystemException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalUIConfigurationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (Throwable t) {
                t.printStackTrace();
            }
        } 
        
            */
        wrappedInputs.clear();
        for (ModelInputDisplayItem item: display.getOryginalInputs()) {
            //wrappedInputs.put(item, ModelInputDisplayItemWrapper.getInputWrapper(item, this, inputsValues));
        }
        
        //newGroupWrapper = new ModelInputGroupDisplayItemWrapper(this);
    }
    
    public List<SelectItem> getModelInputsOptions() {
        return SupportBean.getModelInputsOptions(display.getWrapped());
    }
    
    public ModelInputGroupDisplayItemWrapper getNewGroupWrapper() {
        return newGroupWrapper;
    }
    
    public List<ModelInputDisplayItem> getIndividualInputsFromDisplay() {
        List<ModelInputDisplayItem> inputs = new ArrayList<ModelInputDisplayItem>();
        for (ModelInputDisplayItem input: display.getOryginalInputs()) {
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
    
    public String getExpertEvaluation() throws PortalException, SystemException {
        Long wikiPageId = ModelUIFactory.getSimulationExpertEvaluationPageId(simulation);
        if (wikiPageId == null) {
            return "";
        }
        WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(wikiPageId);
        
        // replace internal links with external links
        String content = wikiPage.getContent();
        Pattern pattern = Pattern.compile("\\[\\[(http)?([^\\]^|]*)\\|?([^\\]]*)\\]\\]");
        Matcher matcher = pattern.matcher(content); 
        int lastEnd = 0;
        
        StringBuilder sb = new StringBuilder();
        PortletRequest request =  (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String wikiPageUrlPrefix = String.format("http://%s:%d/web/guest/resources/-/wiki/Main/", 
                request.getServerName(), request.getServerPort());
        
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // external link... ignore
                continue;
            }
            String name = matcher.group(3) != null && matcher.group(3).length() > 0 ? matcher.group(3) : matcher.group(2);
            sb.append(content.substring(lastEnd, matcher.start()));
            sb.append("[[");
            sb.append(wikiPageUrlPrefix);
            sb.append(matcher.group(2).replaceAll("\\s", "+"));
            sb.append("|");
            sb.append(name);
            sb.append("]]");
            
            lastEnd = matcher.end();
        }
        sb.append(content.substring(lastEnd));
        
        return WikiParser.renderXHTML(sb.toString());
    }
    
    public Long getExpertEvaluationPageId() throws SystemException {
        return ModelUIFactory.getSimulationExpertEvaluationPageId(simulation);
    }
    
    public void setExpertEvaluationPageId(Long pageId) throws SystemException {
        ModelUIFactory.setSimulationExpertEvaluationPageId(simulation, pageId);
    }
}