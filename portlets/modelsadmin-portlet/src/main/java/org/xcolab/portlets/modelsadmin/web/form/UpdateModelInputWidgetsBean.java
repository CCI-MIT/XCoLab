package org.xcolab.portlets.modelsadmin.web.form;

import java.util.HashMap;
import java.util.Map;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputWidgetType;

public class UpdateModelInputWidgetsBean {
	private Map<Long, ModelInputWidgetType> widgets = new HashMap<>();
	
	public UpdateModelInputWidgetsBean() {
	}
	
	public UpdateModelInputWidgetsBean(ModelDisplay display) {
		for (ModelInputDisplayItem item: display.getAllIndividualInputs()) {
			widgets.put(item.getMetaData().getId(), item.getType());
		}
	}

	public Map<Long, ModelInputWidgetType> getWidgets() {
		return widgets;
	}

	public void setWidgets(Map<Long, ModelInputWidgetType> widgets) {
		this.widgets = widgets;
	}

}
