package org.xcolab.view.pages.modeling.admin;

import edu.mit.cci.roma.client.Simulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.modeling.IModelingClient;
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
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.view.pages.modeling.admin.form.UpdateIndividualInputGroupBean;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelDisplayFromJSONBean;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelInputGroupBean;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelInputWidgetsBean;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelOutputWidgetsBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/modeling")
public class ModelsAdminController {

    private static final String BASE_MODEL_PATH = "modeling/admin/";
    private static final String MODELING_BASE_URL = "/admin/modeling/";

    private final IModelingClient modelingClient;

    @Autowired
    public ModelsAdminController(IModelingClient modelingClient) {
        this.modelingClient = modelingClient;
    }

    @GetMapping
    public String showAvailableModels(Model model,
            @RequestParam(value = "refresh", required = false) boolean refresh) {
        if (refresh) {
            RomaClientUtil.client().getManager().clearCache();
        }

        List<ModelWrapper> simulationsSorted =
                RomaClientUtil.client().getAllSimulations().stream().map(ModelWrapper::new)
                        .collect(Collectors.toList());
        simulationsSorted.sort((o1, o2) -> (int) (o2.getId() - o1.getId()));
        model.addAttribute("models", simulationsSorted);
        return BASE_MODEL_PATH + "modelsIndex";
    }

    @GetMapping("model/{modelId}")
    public String showModelDetails(Model model, @PathVariable long modelId) throws IOException {

        model.addAttribute("model",
                new ModelWrapper(RomaClientUtil.client().getSimulation(modelId)));

        model.addAttribute("tab", "details");
        model.addAttribute("modelPreferences", modelingClient.getModelPreference(modelId));
        return BASE_MODEL_PATH + "modelDetails";
    }

    @GetMapping("model/{modelId}/tab/inputWidgets")
    public String showModelInputWidgets(Model model, @PathVariable long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

        List<ModelInputGroupDisplayItem> groupsAndTabs = new ArrayList<>();
        for (ModelInputGroupDisplayItem tab : modelDisplay.getTabs()) {
            groupsAndTabs.addAll(getSubGroups(tab));
        }

        groupsAndTabs.addAll(modelDisplay.getGroups());

        Map<Long, String> groupInputsById = new TreeMap<>();
        groupInputsById.put(0L, "-- none --");
        for (ModelInputGroupDisplayItem item : groupsAndTabs) {
            groupInputsById.put(item.getGroupId(), item.getName());
        }

        model.addAttribute("model", new ModelWrapper(simulation));
        model.addAttribute("modelDisplay", modelDisplay);
        model.addAttribute("tab", "inputWidgets");
        model.addAttribute("availableInputWidgets", ModelInputWidgetType.values());
        model.addAttribute("updateWidgetsBean",
                new UpdateModelInputWidgetsBean(modelDisplay, modelId));
        model.addAttribute("modelPreferences", modelingClient.getModelPreference(modelId));
        model.addAttribute("groupsAndTabs", groupsAndTabs);
        model.addAttribute("groupInputsById", groupInputsById);

        return BASE_MODEL_PATH + "modelInputWidgets";
    }

    @GetMapping("model/{modelId}/tab/outputWidgets")
    public String showModelOutputWidgets(Model model, @PathVariable long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

        model.addAttribute("model", new ModelWrapper(simulation));
        model.addAttribute("modelDisplay", modelDisplay);
        model.addAttribute("tab", "outputWidgets");
        model.addAttribute("availableChartTypes", ModelOutputChartType.values());
        model.addAttribute("availableOutputWidgets", ModelOutputDisplayItemType.values());
        model.addAttribute("availableOutputSeriesTypes", ModelOutputSeriesType.values());
        model.addAttribute("allOutputs", getAllOutputsFromDisplay(modelDisplay));
        model.addAttribute("updateWidgetsBean",
                new UpdateModelOutputWidgetsBean(modelDisplay, modelId));
        model.addAttribute("modelPreferences", modelingClient.getModelPreference(modelId));

        return BASE_MODEL_PATH + "modelOutputWidgets";
    }

    public static List<ModelOutputDisplayItem> getAllOutputsFromDisplay(ModelDisplay display) {
        List<ModelOutputDisplayItem> outputs = new ArrayList<>();
        for (ModelOutputDisplayItem output : display.getOutputs()) {
            outputs.add(output);
            if (output.getDisplayItemType() == ModelOutputDisplayItemType.INDEXED) {
                outputs.addAll(((ModelOutputIndexedDisplayItem) output).getSeries());
            }
        }
        return outputs;
    }

    @GetMapping("model/{modelId}/tab/inputTabs")
    public String showModelInputTabsEditWidget(Model model, @PathVariable long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

        Map<Long, String> individualInputsById = new HashMap<>();
        individualInputsById.put(0L, "-- none --");
        for (ModelInputDisplayItem item : modelDisplay.getAllIndividualInputs()) {
            individualInputsById.put(item.getMetaData().getId(), item.getName());
        }

        List<ModelInputGroupDisplayItem> groupsAndTabs = new ArrayList<>();
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

        model.addAttribute("model", new ModelWrapper(simulation));
        model.addAttribute("modelDisplay", modelDisplay);
        model.addAttribute("tab", "inputTabs");
        model.addAttribute("modelPreferences", modelingClient.getModelPreference(modelId));
        model.addAttribute("updateModelInputGroupBean", new UpdateModelInputGroupBean());
        model.addAttribute("udateIndividualInputGroupBean", new UpdateIndividualInputGroupBean());
        model.addAttribute("individualInputsById", individualInputsById);
        model.addAttribute("groupsAndTabs", groupsAndTabs);
        model.addAttribute("groupInputsById", groupInputsById);

        return BASE_MODEL_PATH + "modelInputTabs";
    }

    @GetMapping("model/{modelId}/tab/modelDisplayByJson")
    public String modelDisplayByJSON(Model model, @PathVariable long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
        model.addAttribute("command", new UpdateModelDisplayFromJSONBean());
        model.addAttribute("model", new ModelWrapper(simulation));
        model.addAttribute("tab", "modelDisplayByJSON");


        return BASE_MODEL_PATH + "modelDisplayByJSON";
    }

    private List<ModelInputGroupDisplayItem> getSubGroups(ModelInputGroupDisplayItem parent) {
        List<ModelInputGroupDisplayItem> ret = new ArrayList<>();
        ret.add(parent);

        for (ModelInputGroupDisplayItem child : parent.getChildGroups()) {
            ret.addAll(getSubGroups(child));
        }
        return ret;
    }

    public static String getTabMapping(long modelId, String tabName) {
        if (tabName == null) {
            return MODELING_BASE_URL + "model/" + modelId;
        }
        return MODELING_BASE_URL + "model/" + modelId + "/tab/" + tabName;
    }
}
