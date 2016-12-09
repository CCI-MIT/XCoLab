package org.xcolab.portlets.modelsadmin.web;

import edu.mit.cci.roma.client.Simulation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.modeling.ModelingClientUtil;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelInputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelInputGroupDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelInputWidgetType;
import org.xcolab.client.modeling.models.ui.ModelOutputChartType;
import org.xcolab.client.modeling.models.ui.ModelOutputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputDisplayItemType;
import org.xcolab.client.modeling.models.ui.ModelOutputIndexedDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputSeriesType;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.portlets.modelsadmin.web.form.UpdateIndividualInputGroupBean;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelDisplayFromJSONBean;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelInputGroupBean;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelInputWidgetsBean;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelOutputWidgetsBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RequestMapping("view")
@Controller
public class ModelsAdminController {

    @RequestMapping
    public String showAvailableModels(Model model,
            @RequestParam(value = "refresh", required = false) boolean refresh) {
        if (refresh) {
            RomaClientUtil.client().getManager().clearCache();
        }

        List<Simulation> simulationsSorted = new ArrayList<>(
                RomaClientUtil.client().getAllSimulations());
        Collections.sort(simulationsSorted, new Comparator<Simulation>() {

            @Override
            public int compare(Simulation o1, Simulation o2) {
                return (int) (o2.getId() - o1.getId());
            }

        });
        model.addAttribute("models", simulationsSorted);
        return "modelsIndex";
    }

    @RequestMapping(params = "modelId")
    public String showModelDetails(Model model, @RequestParam Long modelId)
            throws IOException {

        model.addAttribute("model", RomaClientUtil.client().getSimulation(modelId));

        model.addAttribute("tab", "details");
        model.addAttribute("modelPreferences", ModelingClientUtil.getModelPreference(modelId));
        return "modelDetails/modelDetails";
    }

    @RequestMapping(params = {"modelId", "tab=inputWidgets"})
    public String showModelInputWidgets(Model model, @RequestParam Long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

        List<ModelInputGroupDisplayItem> groupsAndTabs =
                new ArrayList<>();
        for (ModelInputGroupDisplayItem tab : modelDisplay.getTabs()) {
            groupsAndTabs.addAll(getSubGroups(tab));
        }

        groupsAndTabs.addAll(modelDisplay.getGroups());

        Map<Long, String> groupInputsById = new TreeMap<>();
        groupInputsById.put(0L, "-- none --");
        for (ModelInputGroupDisplayItem item : groupsAndTabs) {
            groupInputsById.put(item.getGroupId(), item.getName());
        }

        model.addAttribute("model", simulation);
        model.addAttribute("modelDisplay", modelDisplay);
        model.addAttribute("tab", "inputWidgets");
        model.addAttribute("availableInputWidgets", ModelInputWidgetType.values());
        model.addAttribute("updateWidgetsBean",
                new UpdateModelInputWidgetsBean(modelDisplay, modelId));
        model.addAttribute("modelPreferences", ModelingClientUtil.getModelPreference(modelId));
        model.addAttribute("groupsAndTabs", groupsAndTabs);
        model.addAttribute("groupInputsById", groupInputsById);

        return "modelDetails/modelInputWidgets";
    }

    @RequestMapping(params = {"modelId", "tab=outputWidgets"})
    public String showModelOutputWidgets(Model model, @RequestParam Long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

        model.addAttribute("model", simulation);
        model.addAttribute("modelDisplay", modelDisplay);
        model.addAttribute("tab", "outputWidgets");
        model.addAttribute("availableChartTypes", ModelOutputChartType.values());
        model.addAttribute("availableOutputWidgets", ModelOutputDisplayItemType.values());
        model.addAttribute("availableOutputSeriesTypes", ModelOutputSeriesType.values());
        model.addAttribute("allOutputs", getAllOutputsFromDisplay(modelDisplay));
        model.addAttribute("updateWidgetsBean",
                new UpdateModelOutputWidgetsBean(modelDisplay, modelId));
        model.addAttribute("modelPreferences", ModelingClientUtil.getModelPreference(modelId));

        return "modelDetails/modelOutputWidgets";
    }

    public static List<ModelOutputDisplayItem> getAllOutputsFromDisplay(ModelDisplay display) {
        List<ModelOutputDisplayItem> outputs = new ArrayList<>();
        for (ModelOutputDisplayItem output : display.getOutputs()) {
            outputs.add(output);
            if (output.getDisplayItemType() == ModelOutputDisplayItemType.INDEXED) {
                for (ModelOutputDisplayItem serie : ((ModelOutputIndexedDisplayItem) output)
                        .getSeries()) {
                    outputs.add(serie);
                }
            }
        }
        return outputs;
    }

    @RequestMapping(params = {"modelId", "tab=inputTabs"})
    public String showModelInputTabsEditWidget(Model model, @RequestParam Long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

        Map<Long, String> individualInputsById = new HashMap<>();
        individualInputsById.put(0L, "-- none --");
        for (ModelInputDisplayItem item : modelDisplay.getAllIndividualInputs()) {
            individualInputsById.put(item.getMetaData().getId(), item.getName());
        }

        List<ModelInputGroupDisplayItem> groupsAndTabs =
                new ArrayList<>();
        //groupsAndTabs.addAll(modelDisplay.getTabs());

        for (ModelInputGroupDisplayItem tab : modelDisplay.getTabs()) {
            groupsAndTabs.addAll(getSubGroups(tab));
        }

        groupsAndTabs.addAll(modelDisplay.getGroups());

        Map<Long, String> groupInputsById = new HashMap<>();
        groupInputsById.put(0L, "-- none --");
        for (ModelInputGroupDisplayItem item : groupsAndTabs) {
            groupInputsById.put(item.getGroupId(), item.getName());
        }

        model.addAttribute("model", simulation);
        model.addAttribute("modelDisplay", modelDisplay);
        model.addAttribute("tab", "inputTabs");
        model.addAttribute("modelPreferences", ModelingClientUtil.getModelPreference(modelId));
        model.addAttribute("updateModelInputGroupBean", new UpdateModelInputGroupBean());
        model.addAttribute("udateIndividualInputGroupBean", new UpdateIndividualInputGroupBean());
        model.addAttribute("individualInputsById", individualInputsById);
        model.addAttribute("groupsAndTabs", groupsAndTabs);
        model.addAttribute("groupInputsById", groupInputsById);

        return "modelDetails/modelInputTabs";
    }

    @RequestMapping(params = {"modelId", "tab=modelDisplayByJSON"})
    public String modelDisplayByJSON(Model model, @RequestParam Long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
        model.addAttribute("command", new UpdateModelDisplayFromJSONBean());
        model.addAttribute("model", simulation);
        model.addAttribute("tab", "modelDisplayByJSON");


        return "modelDetails/modelDisplayByJSON";
    }

    private List<ModelInputGroupDisplayItem> getSubGroups(ModelInputGroupDisplayItem parent) {
        List<ModelInputGroupDisplayItem> ret = new ArrayList<>();
        ret.add(parent);

        for (ModelInputGroupDisplayItem child : parent.getChildGroups()) {
            ret.addAll(getSubGroups(child));
        }
        return ret;
    }

}
