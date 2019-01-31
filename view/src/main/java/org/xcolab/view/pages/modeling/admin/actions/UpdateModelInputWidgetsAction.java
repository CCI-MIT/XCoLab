package org.xcolab.view.pages.modeling.admin.actions;

import edu.mit.cci.roma.client.Simulation;
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
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.client.modeling.pojo.IModelGlobalPreference;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.view.pages.modeling.admin.ModelsAdminController;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelInputWidgetsBean;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/modeling")
public class UpdateModelInputWidgetsAction {

    private final IModelingClient modelingClient;

    @Autowired
    public UpdateModelInputWidgetsAction(IModelingClient modelingClient) {
        this.modelingClient = modelingClient;
    }

    @PostMapping("model/{modelId}/updateInputs")
    public void update(HttpServletRequest request, HttpServletResponse response,
            UpdateModelInputWidgetsBean updateModelWidgetsBean, @PathVariable long modelId)
            throws IllegalUIConfigurationException, IOException {

        IModelGlobalPreference modelPreferences = modelingClient.getModelPreference(modelId);
        if (modelPreferences.isUsesCustomInputs()) {
            modelPreferences
                    .setCustomInputsDefinition(updateModelWidgetsBean.getCustomInputWidgets());
            modelingClient.updatePreferences(modelPreferences);
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

        response.sendRedirect(ModelsAdminController.getTabMapping(modelId, "inputWidgets"));
    }
}
