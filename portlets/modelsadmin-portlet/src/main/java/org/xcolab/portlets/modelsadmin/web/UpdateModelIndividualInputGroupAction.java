package org.xcolab.portlets.modelsadmin.web;

import edu.mit.cci.roma.client.Simulation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ext.portlet.model.ModelInputItem;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.modeling.RomaClientUtil;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelInputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.portlets.modelsadmin.web.form.UpdateIndividualInputGroupBean;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@RequestMapping("view")
@Controller
public class UpdateModelIndividualInputGroupAction {

    @RequestMapping(params = {"action=udateIndividualInputGroup", "modelId",
            "tab=inputTabs"})
    public void update(ActionRequest request, ActionResponse response,
            UpdateIndividualInputGroupBean updateModelInputGroup,
            @RequestParam Long modelId) throws SystemException,
            IllegalUIConfigurationException, IOException, PortalException {

        Simulation simulation = RomaClientUtil.repository().getSimulation(modelId);
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

        ModelInputItem inputItem = ModelInputItemLocalServiceUtil
                .getItemForMetaData(modelId, displayItem.getMetaData());
        inputItem.setDisplayItemOrder(updateModelInputGroup.getOrder());
        inputItem.setModelGroupId(updateModelInputGroup.getGroupId());


        ModelInputItemLocalServiceUtil.updateModelInputItem(inputItem);
    }

}
