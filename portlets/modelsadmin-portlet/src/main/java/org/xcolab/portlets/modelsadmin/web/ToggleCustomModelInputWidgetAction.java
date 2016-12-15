package org.xcolab.portlets.modelsadmin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.modeling.ModelingClientUtil;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.pojo.ModelGlobalPreference;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelInputWidgetsBean;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@RequestMapping("view")
@Controller
public class ToggleCustomModelInputWidgetAction {

    @RequestMapping(params = {"action=toggleCustomInputs", "modelId", "tab=inputWidgets"})
    public void update(ActionRequest request, ActionResponse response,
            UpdateModelInputWidgetsBean updateModelWidgetsBean,
            @RequestParam Long modelId)
            throws IllegalUIConfigurationException, IOException {

        ModelGlobalPreference modelPreferences = ModelingClientUtil.getModelPreference(modelId);
        modelPreferences.setUsesCustomInputs(!modelPreferences.getUsesCustomInputs());
        ModelingClientUtil.updateModelPreference(modelPreferences);
    }

}
