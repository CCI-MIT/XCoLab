package org.xcolab.portlets.modelsadmin.web.form;

import java.util.HashMap;
import java.util.Map;

import com.ext.portlet.models.ui.ModelOutputDisplayItemType;

public class UpdateModelOutputWidgetsBean {
	private Map<Long, ModelOutputDisplayItemType> widgets = new HashMap<>();
	
	public UpdateModelOutputWidgetsBean() {
	}
	/*
	public UpdateModelOutputWidgetsBean(ModelDisplay display) {
		for (ModelOutputDisplayItemType item: display.getAllIndividualInputs()) {
			widgets.put(item.getMetaData().getId(), item.getType());
		}
	}

	public Map<Long, ModelInputWidgetType> getWidgets() {
		return widgets;
	}

	public void setWidgets(Map<Long, ModelInputWidgetType> widgets) {
		this.widgets = widgets;
	}*/

}
