package org.xcolab.portlets.modelsadmin.web.form;

import java.util.HashMap;
import java.util.Map;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputWidgetType;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class UpdateModelInputWidgetsBean {
	private Map<Long, ModelInputWidgetType> widgets = new HashMap<>();
	private String customInputWidgets;
	
	public UpdateModelInputWidgetsBean() {
	}
	
	public UpdateModelInputWidgetsBean(ModelDisplay display, long modelId) throws SystemException {
		for (ModelInputDisplayItem item: display.getAllIndividualInputs()) {
			widgets.put(item.getMetaData().getId(), item.getType());
		}
		customInputWidgets = ModelGlobalPreferenceLocalServiceUtil.getByModelId(modelId).getCustomInputsDefinition();
	}

	public Map<Long, ModelInputWidgetType> getWidgets() {
		return widgets;
	}

	public void setWidgets(Map<Long, ModelInputWidgetType> widgets) {
		this.widgets = widgets;
	}

	public String getCustomInputWidgets() {
		return customInputWidgets;
	}

	public void setCustomInputWidgets(String customInputWidgets) {
		this.customInputWidgets = customInputWidgets;
	}

}
