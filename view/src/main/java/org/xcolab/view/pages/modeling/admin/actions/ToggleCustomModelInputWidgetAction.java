package org.xcolab.view.pages.modeling.admin.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.modeling.ModelingClientUtil;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.pojo.ModelGlobalPreference;
import org.xcolab.view.pages.modeling.admin.ModelsAdminController;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelInputWidgetsBean;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/modeling")
public class ToggleCustomModelInputWidgetAction {

    @PostMapping("model/{modelId}/toggleCustomInputs")
    public void update(HttpServletRequest request, HttpServletResponse response,
            UpdateModelInputWidgetsBean updateModelWidgetsBean,
            @PathVariable long modelId)
            throws IllegalUIConfigurationException, IOException {

        ModelGlobalPreference modelPreferences = ModelingClientUtil.getModelPreference(modelId);
        modelPreferences.setUsesCustomInputs(!modelPreferences.getUsesCustomInputs());
        ModelingClientUtil.updateModelPreference(modelPreferences);
        response.sendRedirect(ModelsAdminController.getTabMapping(modelId, "inputWidgets"));

    }

}
