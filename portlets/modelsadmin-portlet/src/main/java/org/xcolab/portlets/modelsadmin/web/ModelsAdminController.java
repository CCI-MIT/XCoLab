package org.xcolab.portlets.modelsadmin.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelInputWidgetsBean;

import com.ext.portlet.model.ModelGlobalPreference;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputWidgetType;
import com.ext.portlet.models.ui.ModelOutputChartType;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItemType;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.Simulation;

@RequestMapping("view")
@Controller
public class ModelsAdminController {
	
	@RequestMapping
	public String showAvailableModels(Model model) throws SystemException {
		model.addAttribute("models", CollaboratoriumModelingService.repository().getAllSimulations());
		System.out.println("CollaboratoriumModelingService.repository().getAllSimulations()");
		return "modelsIndex";
	}
	
	@RequestMapping(params="modelId")
	public String showModelDetails(Model model, @RequestParam Long modelId) throws SystemException, IOException {
		
		model.addAttribute("model", CollaboratoriumModelingService.repository().getSimulation(modelId));
		
		model.addAttribute("tab", "details");
		model.addAttribute("modelPreferences", ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId));
		return "modelDetails/modelDetails";
	}
	
	@RequestMapping(params={"modelId", "tab=inputWidgets"})
	public String showModelInputWidgets(Model model, @RequestParam Long modelId) throws SystemException, IOException, IllegalUIConfigurationException {
		
		Simulation simulation = CollaboratoriumModelingService.repository().getSimulation(modelId);
		ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
		model.addAttribute("model", simulation);
		model.addAttribute("modelDisplay", modelDisplay);
		model.addAttribute("tab", "inputWidgets");
		model.addAttribute("availableInputWidgets", ModelInputWidgetType.values());
		model.addAttribute("updateWidgetsBean", new UpdateModelInputWidgetsBean(modelDisplay, modelId));
		model.addAttribute("modelPreferences", ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId));
		
		return "modelDetails/modelInputWidgets";
	}
	
	@RequestMapping(params={"modelId", "tab=outputWidgets"})
	public String showModelOutputWidgets(Model model, @RequestParam Long modelId) throws SystemException, IOException, IllegalUIConfigurationException {
		
		Simulation simulation = CollaboratoriumModelingService.repository().getSimulation(modelId);
		ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);
		
		model.addAttribute("model", simulation);
		model.addAttribute("modelDisplay", modelDisplay);
		model.addAttribute("tab", "outputWidgets");
		model.addAttribute("availableChartTypes", ModelOutputChartType.values());
		model.addAttribute("availableOutputWidgets", ModelOutputDisplayItemType.values());
		model.addAttribute("allOutputs", getAllOutputsFromDisplay(modelDisplay));
		model.addAttribute("updateWidgetsBean", new UpdateModelInputWidgetsBean(modelDisplay, modelId));
		model.addAttribute("modelPreferences", ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId));
		
		return "modelDetails/modelOutputWidgets";
	}

    private List<ModelOutputDisplayItem> getAllOutputsFromDisplay(ModelDisplay display) {
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
