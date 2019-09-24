package org.xcolab.view.pages.modeling.admin.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.modeling.IModelingClient;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.pojo.IModelGlobalPreference;
import org.xcolab.view.pages.modeling.admin.ModelsAdminController;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelInputWidgetsBean;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/modeling")
public class ToggleCustomModelInputWidgetAction {

    private final IModelingClient modelingClient;

    @Autowired
    public ToggleCustomModelInputWidgetAction(IModelingClient modelingClient) {
        this.modelingClient = modelingClient;
    }

    @PostMapping("model/{modelId}/toggleCustomInputs")
    public void update(HttpServletRequest request, HttpServletResponse response,
            UpdateModelInputWidgetsBean updateModelWidgetsBean, @PathVariable long modelId)
            throws IllegalUIConfigurationException, IOException {

        IModelGlobalPreference modelPreferences = modelingClient.getModelPreference(modelId);
        modelPreferences.setUsesCustomInputs(!modelPreferences.isUsesCustomInputs());
        modelingClient.updatePreferences(modelPreferences);
        response.sendRedirect(ModelsAdminController.getTabMapping(modelId, "inputWidgets"));
    }
}
