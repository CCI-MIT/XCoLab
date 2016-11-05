package org.xcolab.portlets.modelsadmin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ext.portlet.model.ModelGlobalPreference;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
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
            throws SystemException, IllegalUIConfigurationException, IOException {

        ModelGlobalPreference modelPreferences =
                ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId);
        modelPreferences.setUsesCustomInputs(!modelPreferences.isUsesCustomInputs());
        ModelGlobalPreferenceLocalServiceUtil.updateModelGlobalPreference(modelPreferences);
    }

}
