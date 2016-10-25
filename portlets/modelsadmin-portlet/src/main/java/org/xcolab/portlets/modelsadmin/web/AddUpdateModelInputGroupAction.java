package org.xcolab.portlets.modelsadmin.web;

import edu.mit.cci.roma.client.Simulation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ext.portlet.model.ModelInputGroup;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.ext.portlet.service.ModelInputGroupLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.modeling.RomaClientUtil;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelInputGroupBean;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@RequestMapping("view")
@Controller
public class AddUpdateModelInputGroupAction {

	@RequestMapping(params = { "action=addUpdateInputGroup", "modelId",
			"tab=inputTabs" })
	public void update(ActionRequest request, ActionResponse response,
			UpdateModelInputGroupBean updateModelInputGroup,
			@RequestParam Long modelId) throws SystemException,
			IllegalUIConfigurationException, IOException, PortalException {
		Simulation simulation = RomaClientUtil.repository().getSimulation(modelId);
		ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
		

		ModelInputGroup modelInputGroup;
		if (updateModelInputGroup.getId() == 0) {
			// create new one
			modelInputGroup = ModelInputGroupLocalServiceUtil.createModelInputGroup(CounterLocalServiceUtil.increment(ModelInputGroup.class.getName()));
			modelInputGroup.setModelId(modelId);
		}
		else {
			modelInputGroup = ModelInputGroupLocalServiceUtil.getModelInputGroup(updateModelInputGroup.getId());
		}
		if (updateModelInputGroup.getId() > 0 && "delete".equals(updateModelInputGroup.getInputAction())) {
			ModelInputGroupLocalServiceUtil.deleteModelInputGroup(updateModelInputGroup.getId());
			return;
		}
		
		if (updateModelInputGroup.getNameDescriptionMetaDataId() > 0) {
			for (ModelInputDisplayItem item: modelDisplay.getAllIndividualInputs()) {
				if (item.getMetaData().getId() == updateModelInputGroup.getNameDescriptionMetaDataId()) {
					modelInputGroup.setNameAndDescriptionMetaDataId(item.getMetaData().getId());
					modelInputGroup.setName(item.getName());
					modelInputGroup.setDescription(item.getDescription());
				}
			}
		}
		else {
			modelInputGroup.setName(updateModelInputGroup.getName());
			modelInputGroup.setDescription(updateModelInputGroup.getDescription());
		}
		modelInputGroup.setGroupType(updateModelInputGroup.getGroupType());
		modelInputGroup.setDisplayItemOrder(updateModelInputGroup.getOrder());
		modelInputGroup.setParentGroupPK(updateModelInputGroup.getParentGroupPK());

		if (updateModelInputGroup.getId() == 0) {
			ModelInputGroupLocalServiceUtil.addModelInputGroup(modelInputGroup);
		}
		else {
			ModelInputGroupLocalServiceUtil.updateModelInputGroup(modelInputGroup);
		}
		

	}

}
