package org.xcolab.portlets.modelsadmin.web;

import org.xcolab.client.modeling.RomaClientUtil;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelInputWidgetType;
import com.ext.portlet.models.ui.ModelOutputChartType;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItemType;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelOutputSeriesType;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import edu.mit.cci.roma.client.Simulation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.portlets.modelsadmin.web.form.UpdateIndividualInputGroupBean;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelDisplayFromJSONBean;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelInputGroupBean;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelInputWidgetsBean;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelOutputWidgetsBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RequestMapping("view")
@Controller
public class ModelsAdminController {
	
	@RequestMapping
	public String showAvailableModels(Model model, @RequestParam(value ="refresh", required=false) boolean refresh) throws SystemException {
		if (refresh) {
			RomaClientUtil.repository().getManager().clearCache();
		}

		List<Simulation> simulationsSorted = new ArrayList<Simulation>(
				RomaClientUtil.repository().getAllSimulations());
		Collections.sort(simulationsSorted, new Comparator<Simulation>() {

			@Override
			public int compare(Simulation o1, Simulation o2) {
				return (int) (o2.getId() - o1.getId());
			}
				
		});
		model.addAttribute("models", simulationsSorted);
		return "modelsIndex";
	}
	
	@RequestMapping(params="modelId")
	public String showModelDetails(Model model, @RequestParam Long modelId) throws SystemException, IOException {
		
		model.addAttribute("model", RomaClientUtil.repository().getSimulation(modelId));
		
		model.addAttribute("tab", "details");
		model.addAttribute("modelPreferences", ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId));
		return "modelDetails/modelDetails";
	}
	
	@RequestMapping(params={"modelId", "tab=inputWidgets"})
	public String showModelInputWidgets(Model model, @RequestParam Long modelId) throws SystemException, IOException, IllegalUIConfigurationException {
		
		Simulation simulation = RomaClientUtil.repository().getSimulation(modelId);
		ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);

		List<ModelInputGroupDisplayItem> groupsAndTabs = new ArrayList<ModelInputGroupDisplayItem>();
		for (ModelInputGroupDisplayItem tab: modelDisplay.getTabs()) {
			groupsAndTabs.addAll(getSubGroups(tab));
		}
		
		groupsAndTabs.addAll(modelDisplay.getGroups());
		
		Map<Long, String> groupInputsById = new TreeMap<Long, String>();
		groupInputsById.put(0L, "-- none --");
		for (ModelInputGroupDisplayItem item: groupsAndTabs) {
			groupInputsById.put(item.getGroupId(), item.getName());
		}
		 
		model.addAttribute("model", simulation);
		model.addAttribute("modelDisplay", modelDisplay);
		model.addAttribute("tab", "inputWidgets");
		model.addAttribute("availableInputWidgets", ModelInputWidgetType.values());
		model.addAttribute("updateWidgetsBean", new UpdateModelInputWidgetsBean(modelDisplay, modelId));
		model.addAttribute("modelPreferences", ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId));
		model.addAttribute("groupsAndTabs", groupsAndTabs);
		model.addAttribute("groupInputsById", groupInputsById);
		
		return "modelDetails/modelInputWidgets";
	}
	
	@RequestMapping(params={"modelId", "tab=outputWidgets"})
	public String showModelOutputWidgets(Model model, @RequestParam Long modelId) throws SystemException, IOException, IllegalUIConfigurationException {
		
		Simulation simulation = RomaClientUtil.repository().getSimulation(modelId);
		ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
		
		model.addAttribute("model", simulation);
		model.addAttribute("modelDisplay", modelDisplay);
		model.addAttribute("tab", "outputWidgets");
		model.addAttribute("availableChartTypes", ModelOutputChartType.values());
		model.addAttribute("availableOutputWidgets", ModelOutputDisplayItemType.values());
		model.addAttribute("availableOutputSeriesTypes", ModelOutputSeriesType.values());
		model.addAttribute("allOutputs", getAllOutputsFromDisplay(modelDisplay));
		model.addAttribute("updateWidgetsBean", new UpdateModelOutputWidgetsBean(modelDisplay, modelId));
		model.addAttribute("modelPreferences", ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId));
		
		return "modelDetails/modelOutputWidgets";
	}
	
	@RequestMapping(params={"modelId", "tab=inputTabs"})
	public String showModelInputTabsEditWidget(Model model, @RequestParam Long modelId) throws SystemException, IOException, IllegalUIConfigurationException {
		
		Simulation simulation = RomaClientUtil.repository().getSimulation(modelId);
		ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
		
		Map<Long, String> individualInputsById = new HashMap<Long, String>();
		individualInputsById.put(0L, "-- none --");
		for (ModelInputDisplayItem item: modelDisplay.getAllIndividualInputs()) {
			individualInputsById.put(item.getMetaData().getId(), item.getName());
		}

		List<ModelInputGroupDisplayItem> groupsAndTabs = new ArrayList<ModelInputGroupDisplayItem>();
		//groupsAndTabs.addAll(modelDisplay.getTabs());
		
		for (ModelInputGroupDisplayItem tab: modelDisplay.getTabs()) {
			groupsAndTabs.addAll(getSubGroups(tab));
		}
		
		groupsAndTabs.addAll(modelDisplay.getGroups());
		
		Map<Long, String> groupInputsById = new HashMap<Long, String>();
		groupInputsById.put(0L, "-- none --");
		for (ModelInputGroupDisplayItem item: groupsAndTabs) {
			groupInputsById.put(item.getGroupId(), item.getName());
		}
		 
		model.addAttribute("model", simulation);
		model.addAttribute("modelDisplay", modelDisplay);
		model.addAttribute("tab", "inputTabs");
		model.addAttribute("modelPreferences", ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId));
		model.addAttribute("updateModelInputGroupBean", new UpdateModelInputGroupBean());
		model.addAttribute("udateIndividualInputGroupBean", new UpdateIndividualInputGroupBean());
		model.addAttribute("individualInputsById", individualInputsById);
		model.addAttribute("groupsAndTabs", groupsAndTabs);
		model.addAttribute("groupInputsById", groupInputsById);
		
		return "modelDetails/modelInputTabs";
	}
	

	@RequestMapping(params={"modelId", "tab=modelDisplayByJSON"})
	public String modelDisplayByJSON(Model model, @RequestParam Long modelId) throws SystemException, IOException, IllegalUIConfigurationException {
		
		Simulation simulation = RomaClientUtil.repository().getSimulation(modelId);
		ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
		model.addAttribute("command", new UpdateModelDisplayFromJSONBean());
		model.addAttribute("model", simulation);
		model.addAttribute("tab", "modelDisplayByJSON");
		
		
		return "modelDetails/modelDisplayByJSON";
	}
	
	private List<ModelInputGroupDisplayItem> getSubGroups(ModelInputGroupDisplayItem parent) {
		List<ModelInputGroupDisplayItem> ret = new ArrayList<ModelInputGroupDisplayItem>();
		ret.add(parent);
		
		for (ModelInputGroupDisplayItem child: parent.getChildGroups()) {
			ret.addAll(getSubGroups(child));
		}
		return ret;
	}

    public static List<ModelOutputDisplayItem> getAllOutputsFromDisplay(ModelDisplay display) {
        List<ModelOutputDisplayItem> outputs = new ArrayList<ModelOutputDisplayItem>();
        for (ModelOutputDisplayItem output : display.getOutputs()) {
            outputs.add(output);
            if (output.getDisplayItemType() == ModelOutputDisplayItemType.INDEXED) {
                for (ModelOutputDisplayItem serie : ((ModelOutputIndexedDisplayItem) output).getSeries()) {
                    outputs.add(serie);
                }
            }
        }
        return outputs;
    }
    
}
