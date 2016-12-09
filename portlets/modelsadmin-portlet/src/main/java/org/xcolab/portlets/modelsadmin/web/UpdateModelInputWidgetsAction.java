package org.xcolab.portlets.modelsadmin.web;

import edu.mit.cci.roma.client.Simulation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.modeling.ModelingClientUtil;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelInputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelInputIndividualDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.client.modeling.pojo.ModelGlobalPreference;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelInputWidgetsBean;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@RequestMapping("view")
@Controller
public class UpdateModelInputWidgetsAction {

    @RequestMapping(params = {"action=updateInputs", "modelId", "tab=inputWidgets"})
    public void update(ActionRequest request, ActionResponse response,
            UpdateModelInputWidgetsBean updateModelWidgetsBean,
            @RequestParam Long modelId)
            throws IllegalUIConfigurationException, IOException {

        ModelGlobalPreference modelPreferences = ModelingClientUtil.getModelPreference(modelId);
        if (modelPreferences.getUsesCustomInputs()) {
            modelPreferences
                    .setCustomInputsDefinition(updateModelWidgetsBean.getCustomInputWidgets());
            ModelingClientUtil.updateModelPreference(modelPreferences);
        } else {
            Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
            ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

            for (ModelInputDisplayItem item : modelDisplay.getAllIndividualInputs()) {
                if (updateModelWidgetsBean.getWidgets().containsKey(item.getMetaData().getId())) {
                    item.setType(
                            updateModelWidgetsBean.getWidgets().get(item.getMetaData().getId()));
                }

                if (updateModelWidgetsBean.getGroups().containsKey(item.getMetaData().getId())) {
                    ((ModelInputIndividualDisplayItem) item).setGroupId(
                            updateModelWidgetsBean.getGroups().get(item.getMetaData().getId()));
                }


                if (updateModelWidgetsBean.getOrders().containsKey(item.getMetaData().getId())) {
                    item.setOrder(
                            updateModelWidgetsBean.getOrders().get(item.getMetaData().getId()));
                }
            }
        }

    }

}
