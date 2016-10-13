package org.xcolab.portlets.modelsadmin.web;

import com.ext.portlet.model.ModelInputGroup;
import com.ext.portlet.model.ModelInputItem;
import com.ext.portlet.model.ModelOutputChartOrder;
import org.xcolab.client.modeling.RomaClientUtil;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputIndividualDisplayItem;
import com.ext.portlet.models.ui.ModelInputWidgetType;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputSeriesDisplayItem;
import com.ext.portlet.models.ui.ModelOutputSeriesType;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.ext.portlet.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;
import com.ext.portlet.service.ModelOutputChartOrderLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import edu.mit.cci.roma.client.Simulation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.portlets.modelsadmin.web.form.UpdateModelDisplayFromJSONBean;

import java.io.IOException;
import java.util.Collection;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@RequestMapping("view")
@Controller
public class UpdateModelDisplayFromJSONAction {
	
	@RequestMapping(params={"action=updateModelDisplayFromJson", "modelId", "tab=modelDisplayByJSON"})
	public void update(ActionRequest request, ActionResponse response, UpdateModelDisplayFromJSONBean bean, @RequestParam Long modelId) throws SystemException, IllegalUIConfigurationException, IOException, ParseException, PortalException {

		Simulation simulation = RomaClientUtil.repository().getSimulation(modelId);
		ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
		
		
		try {
			JSONObject conf = (JSONObject) new JSONParser().parse(bean.getJson());
			
			for (ModelInputGroup group: ModelInputGroupLocalServiceUtil.getInputGroups(simulation)) {
				ModelInputGroupLocalServiceUtil.deleteModelInputGroup(group.getModelInputGroupPK());
			}
			for (ModelInputItem item: ModelInputItemLocalServiceUtil.getItemsForModel(simulation)) {
				item.setModelGroupId(0);
				ModelInputItemLocalServiceUtil.updateModelInputItem(item);
			}
			
			for (ModelOutputDisplayItem modi: modelDisplay.getOutputs()) {
				ModelOutputChartOrder moco = ModelOutputChartOrderLocalServiceUtil.getChartOrder(simulation, modi.getName());
				ModelOutputChartOrderLocalServiceUtil.deleteModelOutputChartOrder(moco);
			}

			// iterate over inputs and create appropriate groups/inputs config
			
			configureInputArray(modelId, modelDisplay, (JSONArray) conf.get("inputs"), 0, 0);

			configureOutputArray(modelId, modelDisplay, (JSONArray) conf.get("outputs"), 0);
			
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	

	private int configureInputArray(long modelId, ModelDisplay modelDisplay, JSONArray inputArray, int order, long parentGroup) throws SystemException {
		for (int i=0; i < inputArray.size(); i++) {
			configureInput(modelId, modelDisplay, (JSONObject) inputArray.get(i), order + i, parentGroup);
		}
		return inputArray.size() + order;
		
	}
	
	private int configureInput(long modelId, ModelDisplay modelDisplay, JSONObject inputConf, int order, long parentGroup) throws SystemException {
		String type = (String) inputConf.get("type");
		int count = 1;
		if (type.equals("TAB") || type.equals("HORIZONTAL")) {
			long groupId = CounterLocalServiceUtil.increment(ModelInputGroup.class.getName());
			ModelInputGroup group = ModelInputGroupLocalServiceUtil.createModelInputGroup(groupId);
			group.setGroupType(type);
			group.setModelId(modelId);
			if (inputConf.containsKey("name")) {
				group.setName((String) inputConf.get("name"));
			}
			if (inputConf.containsKey("description")) {
				group.setDescription((String) inputConf.get("description"));
			}
			
			if (inputConf.containsKey("metaDataName")) {
				for (ModelInputDisplayItem metaDataItem: modelDisplay.getAllIndividualInputs()) {
					if (metaDataItem.getName().equals(inputConf.get("metaDataName"))) {
						group.setName(metaDataItem.getName());
						group.setDescription(metaDataItem.getDescription());
						group.setNameAndDescriptionMetaDataId(metaDataItem.getMetaData().getId());
					}
				}
			}
			group.setDisplayItemOrder(order);
			group.setParentGroupPK(parentGroup);
			ModelInputGroupLocalServiceUtil.addModelInputGroup(group);
			
			if (inputConf.containsKey("children")) {
				JSONArray children = (JSONArray) inputConf.get("children");
				count += configureInputArray(modelId, modelDisplay, children, order+count+1, groupId);
			}
		}
		else {
			String name = (String) inputConf.get("name");
			String description = (String) inputConf.get("description");
			boolean found = false;
			for (ModelInputDisplayItem displayItem: modelDisplay.getAllIndividualInputs()) {
				if (displayItem.getName().equals(name) && (description == null || description.equals(displayItem.getDescription()))) {
					found = true;
					displayItem.setType(ModelInputWidgetType.valueOf((String) inputConf.get("widget")));
					displayItem.setOrder(order);
					if (displayItem instanceof ModelInputIndividualDisplayItem) {
						((ModelInputIndividualDisplayItem) displayItem).setGroupId(parentGroup);;
					}
				}
			}
			if (!found) {
				//TODO: logging
			}
		}
		return count;
		
	}
	

	private void configureOutputArray(Long modelId, ModelDisplay modelDisplay, JSONArray jsonArray, int order) throws SystemException {
		for (int i=0; i < jsonArray.size(); i++) {
			JSONObject outputObj = (JSONObject) jsonArray.get(i);

			String name = (String) outputObj.get("name");
			Collection<ModelOutputDisplayItem> allOutputs = ModelsAdminController.getAllOutputsFromDisplay(modelDisplay);

			for (ModelOutputDisplayItem item: allOutputs) {

				if (item.getName().equals(name)) {
					item.setOrder(order++);


					if (item instanceof ModelOutputSeriesDisplayItem) {
						ModelOutputSeriesDisplayItem seriesItem = (ModelOutputSeriesDisplayItem) item;
						String chartType = (String) outputObj.get("chartType");
						if (chartType != null) {
							seriesItem.setSeriesType(ModelOutputSeriesType.valueOf(chartType));
						}
						String associatedMetaDataName = (String) outputObj.get("associatedOutput");
						if (associatedMetaDataName != null) {
							for (ModelOutputDisplayItem associatedItem: allOutputs) {
								if (associatedItem instanceof ModelOutputSeriesDisplayItem && associatedItem.getName().equals(associatedMetaDataName)) {
									seriesItem.setAssociatedMetaData(((ModelOutputSeriesDisplayItem) associatedItem).getMetaData());
								}
							}
						}

					}
				}
			}

		}
	}

}
