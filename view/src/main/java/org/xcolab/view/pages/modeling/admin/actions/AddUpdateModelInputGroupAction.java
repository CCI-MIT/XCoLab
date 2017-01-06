package org.xcolab.view.pages.modeling.admin.actions;

import edu.mit.cci.roma.client.Simulation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.modeling.ModelingClientUtil;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelInputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.client.modeling.pojo.ModelInputGroup;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.view.pages.modeling.admin.ModelsAdminController;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelInputGroupBean;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/modeling")
public class AddUpdateModelInputGroupAction {

    @PostMapping("model/{modelId}/addUpdateInputGroup")
    public void update(HttpServletRequest request, HttpServletResponse response,
            UpdateModelInputGroupBean updateModelInputGroup, @PathVariable long modelId)
            throws IllegalUIConfigurationException, IOException {
        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);


        ModelInputGroup modelInputGroup;
        if (updateModelInputGroup.getId() == 0) {
            // create new one
            modelInputGroup = new ModelInputGroup();
            modelInputGroup.setModelId(modelId);
        } else {
            modelInputGroup = ModelingClientUtil.getModelInputGroup(updateModelInputGroup.getId());
        }
        if (updateModelInputGroup.getId() > 0 && "delete"
                .equals(updateModelInputGroup.getInputAction())) {
            ModelingClientUtil.deleteModelInputGroup(updateModelInputGroup.getId());
            return;
        }

        if (updateModelInputGroup.getNameDescriptionMetaDataId() > 0) {
            for (ModelInputDisplayItem item : modelDisplay.getAllIndividualInputs()) {
                if (item.getMetaData().getId() == updateModelInputGroup
                        .getNameDescriptionMetaDataId()) {
                    modelInputGroup.setNameAndDescriptionMetaDataId(item.getMetaData().getId());
                    modelInputGroup.setName(item.getName());
                    modelInputGroup.setDescription(item.getDescription());
                }
            }
        } else {
            modelInputGroup.setName(updateModelInputGroup.getName());
            modelInputGroup.setDescription(updateModelInputGroup.getDescription());
        }
        modelInputGroup.setGroupType(updateModelInputGroup.getGroupType());
        modelInputGroup.setDisplayItemOrder(updateModelInputGroup.getOrder());
        modelInputGroup.setParentGroupPK(updateModelInputGroup.getParentGroupPK());

        if (updateModelInputGroup.getId() == 0) {
            ModelingClientUtil.createModelInputGroup(modelInputGroup);
        } else {
            ModelingClientUtil.updateModelInputGroup(modelInputGroup);
        }
        response.sendRedirect(ModelsAdminController.getTabMapping(modelId, "inputTabs"));
    }

}
