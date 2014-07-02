package org.xcolab.portlets.modelsadmin.web;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelInputWidgetsBean;

import com.ext.portlet.model.ModelGlobalPreference;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.Simulation;

@RequestMapping("view")
@Controller
public class UpdateModelInputWidgetsAction {
	
	@RequestMapping(params={"action=updateInputs", "modelId", "tab=inputWidgets"})
	public void update(ActionRequest request, ActionResponse response, UpdateModelInputWidgetsBean updateModelWidgetsBean, 
			@RequestParam Long modelId) throws SystemException, IllegalUIConfigurationException, IOException {

		ModelGlobalPreference modelPreferences = ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId);
		if (modelPreferences.getUsesCustomInputs()) {
			modelPreferences.setCustomInputsDefinition(updateModelWidgetsBean.getCustomInputWidgets());
			ModelGlobalPreferenceLocalServiceUtil.updateModelGlobalPreference(modelPreferences);
		}
		else {
			Simulation simulation = CollaboratoriumModelingService.repository().getSimulation(modelId);
			ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
		
			for (ModelInputDisplayItem item: modelDisplay.getAllIndividualInputs()) {
				if (updateModelWidgetsBean.getWidgets().containsKey(item.getMetaData().getId())) {
					item.setType(updateModelWidgetsBean.getWidgets().get(item.getMetaData().getId()));
					//ModelInputItemLocalServiceUtil.updateModelInputItem(item);
				}
			}
		}
		
		
	}

}
