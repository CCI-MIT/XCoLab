package org.xcolab.portlets.modelsadmin.web;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.modelsadmin.web.form.UpdateIndividualInputGroupBean;

import com.ext.portlet.model.ModelInputItem;
import org.xcolab.client.modeling.RomaClientUtil;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.Simulation;

@RequestMapping("view")
@Controller
public class UpdateModelIndividualInputGroupAction {

	@RequestMapping(params = { "action=udateIndividualInputGroup", "modelId",
			"tab=inputTabs" })
	public void update(ActionRequest request, ActionResponse response,
			UpdateIndividualInputGroupBean updateModelInputGroup,
			@RequestParam Long modelId) throws SystemException,
			IllegalUIConfigurationException, IOException, PortalException {

		Simulation simulation = RomaClientUtil.repository().getSimulation(modelId);
		ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
		
		ModelInputDisplayItem displayItem = null;
		for (ModelInputDisplayItem item: modelDisplay.getAllIndividualInputs()) {
			if (item.getMetaData().getId() == updateModelInputGroup.getModelInputItemId()) {
				displayItem = item;
			}
		}
		
		if (displayItem == null) return;
		
		ModelInputItem inputItem = ModelInputItemLocalServiceUtil.getItemForMetaData(modelId, displayItem.getMetaData());
		inputItem.setDisplayItemOrder(updateModelInputGroup.getOrder());
		inputItem.setModelGroupId(updateModelInputGroup.getGroupId());
		
		
		ModelInputItemLocalServiceUtil.updateModelInputItem(inputItem);
	}

}
