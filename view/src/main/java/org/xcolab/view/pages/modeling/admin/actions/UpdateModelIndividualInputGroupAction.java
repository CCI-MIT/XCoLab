package org.xcolab.view.pages.modeling.admin.actions;

import edu.mit.cci.roma.client.Simulation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.modeling.ModelingClient;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelInputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.client.modeling.pojo.ModelInputItem;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.view.pages.modeling.admin.ModelsAdminController;
import org.xcolab.view.pages.modeling.admin.form.UpdateIndividualInputGroupBean;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/modeling")
public class UpdateModelIndividualInputGroupAction {

    @PostMapping("model/{modelId}/updateIndividualInputGroup")
    public void update(HttpServletRequest request, HttpServletResponse response,
            UpdateIndividualInputGroupBean updateModelInputGroup, @PathVariable long modelId)
            throws IllegalUIConfigurationException, IOException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

        ModelInputDisplayItem displayItem = null;
        for (ModelInputDisplayItem item : modelDisplay.getAllIndividualInputs()) {
            if (item.getMetaData().getId() == updateModelInputGroup.getModelInputItemId()) {
                displayItem = item;
            }
        }

        if (displayItem == null) {
            return;
        }

        ModelInputItem inputItem =
                ModelingClient.instance().getItemForMetaData(modelId, displayItem.getMetaData());
        inputItem.setDisplayItemOrder(updateModelInputGroup.getOrder());
        inputItem.setModelGroupId(updateModelInputGroup.getGroupId());


        ModelingClient.instance().updateModelInputItem(inputItem);
        response.sendRedirect(ModelsAdminController.getTabMapping(modelId, "inputTabs"));
    }

}
