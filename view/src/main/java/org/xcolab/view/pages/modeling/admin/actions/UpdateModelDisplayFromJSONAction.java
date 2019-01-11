package org.xcolab.view.pages.modeling.admin.actions;

import edu.mit.cci.roma.client.Simulation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.modeling.IModelingClient;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelInputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelInputIndividualDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelInputWidgetType;
import org.xcolab.client.modeling.models.ui.ModelOutputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputSeriesDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputSeriesType;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.client.modeling.pojo.IModelInputGroup;
import org.xcolab.client.modeling.pojo.IModelInputItem;
import org.xcolab.client.modeling.pojo.IModelOutputChartOrder;
import org.xcolab.client.modeling.pojo.tables.pojos.ModelInputGroup;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.view.pages.modeling.admin.ModelsAdminController;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelDisplayFromJSONBean;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/modeling")
public class UpdateModelDisplayFromJSONAction {

    private final IModelingClient modelingClient;

    @Autowired
    public UpdateModelDisplayFromJSONAction(IModelingClient modelingClient) {
        this.modelingClient = modelingClient;
    }

    @PostMapping("model/{modelId}/updateModelDisplayFromJson")
    public void update(HttpServletRequest request, HttpServletResponse response,
            UpdateModelDisplayFromJSONBean bean, @PathVariable long modelId)
            throws IllegalUIConfigurationException, IOException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

        final JSONObject conf = new JSONObject(bean.getJson());

        for (IModelInputGroup group : modelingClient.getInputGroups(simulation)) {
            modelingClient.deleteModelInputGroup(group.getId());
        }
        for (IModelInputItem item : modelingClient.getItemsForModel(simulation)) {
            item.setModelGroupId(0L);
            modelingClient.updateModelInputItem(item);
        }

        for (ModelOutputDisplayItem modi : modelDisplay.getOutputs()) {
            IModelOutputChartOrder moco =
                    modelingClient.getModelOutputChartOrder(simulation.getId(), modi.getName());
            modelingClient.deleteModelOutputChartOrder(moco.getId());
        }

        // iterate over inputs and create appropriate groups/inputs config

        configureInputArray(modelId, modelDisplay, conf.getJSONArray("inputs"), 0, 0);

        configureOutputArray(modelDisplay, conf.getJSONArray("outputs"), 0);
        response.sendRedirect(ModelsAdminController.getTabMapping(modelId, "modelDisplayByJson"));
    }


    private int configureInputArray(long modelId, ModelDisplay modelDisplay, JSONArray inputArray,
            int order, long parentGroup) {
        for (int i = 0; i < inputArray.length(); i++) {
            configureInput(modelId, modelDisplay, (JSONObject) inputArray.get(i), order + i,
                    parentGroup);
        }
        return inputArray.length() + order;

    }

    private int configureInput(long modelId, ModelDisplay modelDisplay, JSONObject inputConf,
            int order, long parentGroup) {
        String type = (String) inputConf.get("type");
        int count = 1;
        if (type.equals("TAB") || type.equals("HORIZONTAL")) {
            IModelInputGroup group = new ModelInputGroup();
            group.setGroupType(type);
            group.setModelId(modelId);
            if (inputConf.has("name")) {
                group.setName((String) inputConf.get("name"));
            }
            if (inputConf.has("description")) {
                group.setDescription((String) inputConf.get("description"));
            }

            if (inputConf.has("metaDataName")) {
                for (ModelInputDisplayItem metaDataItem : modelDisplay.getAllIndividualInputs()) {
                    if (metaDataItem.getName().equals(inputConf.get("metaDataName"))) {
                        group.setName(metaDataItem.getName());
                        group.setDescription(metaDataItem.getDescription());
                        group.setNameAndDescriptionMetaDataId(metaDataItem.getMetaData().getId());
                    }
                }
            }
            group.setDisplayItemOrder(order);
            group.setParentGroupId(parentGroup);
            group = modelingClient.createModelInputGroup(group);

            if (inputConf.has("children")) {
                JSONArray children = (JSONArray) inputConf.get("children");
                count += configureInputArray(modelId, modelDisplay, children, order + count + 1,
                        group.getId());
            }
        } else {
            String name = (String) inputConf.get("name");
            String description = (String) inputConf.get("description");
            boolean found = false;
            for (ModelInputDisplayItem displayItem : modelDisplay.getAllIndividualInputs()) {
                if (displayItem.getName().equals(name) && (description == null || description
                        .equals(displayItem.getDescription()))) {
                    found = true;
                    displayItem.setType(
                            ModelInputWidgetType.valueOf((String) inputConf.get("widget")));
                    displayItem.setOrder(order);
                    if (displayItem instanceof ModelInputIndividualDisplayItem) {
                        ((ModelInputIndividualDisplayItem) displayItem).setGroupId(parentGroup);
                    }
                }
            }
            if (!found) {
                //TODO: error handling
            }
        }
        return count;

    }


    private void configureOutputArray(ModelDisplay modelDisplay, JSONArray jsonArray, int order) {
        for (Object aJsonArray : jsonArray) {
            JSONObject outputObj = (JSONObject) aJsonArray;

            String name = (String) outputObj.get("name");
            Collection<ModelOutputDisplayItem> allOutputs =
                    ModelsAdminController.getAllOutputsFromDisplay(modelDisplay);

            for (ModelOutputDisplayItem item : allOutputs) {

                if (item.getName().equals(name)) {
                    item.setOrder(order++);


                    if (item instanceof ModelOutputSeriesDisplayItem) {
                        ModelOutputSeriesDisplayItem seriesItem =
                                (ModelOutputSeriesDisplayItem) item;
                        String chartType = (String) outputObj.get("chartType");
                        if (chartType != null) {
                            seriesItem.setSeriesType(ModelOutputSeriesType.valueOf(chartType));
                        }
                        String associatedMetaDataName = (String) outputObj.get("associatedOutput");
                        if (associatedMetaDataName != null) {
                            for (ModelOutputDisplayItem associatedItem : allOutputs) {
                                if (associatedItem instanceof ModelOutputSeriesDisplayItem
                                        && associatedItem.getName()
                                        .equals(associatedMetaDataName)) {
                                    seriesItem.setAssociatedMetaData(
                                            ((ModelOutputSeriesDisplayItem) associatedItem)
                                                    .getMetaData());
                                }
                            }
                        }

                    }
                }
            }

        }
    }
}
