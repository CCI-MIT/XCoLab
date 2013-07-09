package org.xcolab.portlets.models.suggest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xcolab.portlets.models.SimulationDetailsBean;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;

public class ModelDisplayWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient ModelDisplay wrapped;
	private transient SimulationDetailsBean simulationBean;
	private transient List<ModelInputDisplayItemWrapper> wrappedInputs = new ArrayList<ModelInputDisplayItemWrapper>();
	private transient List<ModelInputGroupDisplayItemWrapper> wrappedTabs = new ArrayList<ModelInputGroupDisplayItemWrapper>();
	private transient List<ModelInputDisplayItemWrapper> wrappedNonTabs = new ArrayList<ModelInputDisplayItemWrapper>();
	private transient List<ModelInputDisplayItemWrapper> individualInputs = new ArrayList<ModelInputDisplayItemWrapper>();
	private transient List<ModelInputDisplayItemWrapper> inputsWithGroups = new ArrayList<ModelInputDisplayItemWrapper>();
    private transient List<ModelInputDisplayItemWrapper> unassociatedInputs = new ArrayList<ModelInputDisplayItemWrapper>();

	public ModelDisplayWrapper(ModelDisplay wrapped,
			SimulationDetailsBean simulationBean, Map<Long, Object> values) {
		this.wrapped = wrapped;
		Set<Long> inputsDefined = new HashSet<Long>();
		Map<Long, ModelInputDisplayItemWrapper> inputsById = new HashMap<Long, ModelInputDisplayItemWrapper>();

		for (ModelInputGroupDisplayItem item : wrapped.getTabs()) {
			ModelInputGroupDisplayItemWrapper itemWrapper = new ModelInputGroupDisplayItemWrapper(
					item, simulationBean, values);
			wrappedTabs.add(itemWrapper);
			wrappedInputs.addAll(itemWrapper.getAllItems());
			inputsWithGroups.addAll(itemWrapper.getAllItems());
		}

		for (ModelInputDisplayItem item : wrapped.getNonTabs()) {
			ModelInputDisplayItemWrapper itemWrapper = ModelInputDisplayItemWrapper
					.getInputWrapper(item, simulationBean, values);
			wrappedNonTabs.add(itemWrapper);
			wrappedInputs.add(itemWrapper);
			inputsWithGroups.add(itemWrapper);
		}

		for (ModelInputDisplayItemWrapper item : wrappedInputs) {
			if (item != null && item.getMetaData() != null) {
				inputsDefined.add(item.getMetaData().getId());
				if (item.getGroupId() <= 0) {
				    unassociatedInputs.add(item);
				}
			}
			
		}
		for (ModelInputDisplayItem item : wrapped.getAllIndividualInputs()) {
			if (!inputsDefined.contains(item.getMetaData().getId())) {
				wrappedInputs.add(ModelInputDisplayItemWrapper.getInputWrapper(
						item, simulationBean, values));
				inputsDefined.add(item.getMetaData().getId());
			}
		}
	}

	public List<ModelOutputDisplayItem> getOutputs() {
		return wrapped.getOutputs();
	}

	public List<ModelInputDisplayItemWrapper> getInputs() {
		return wrappedInputs;
	}

	public List<ModelInputDisplayItemWrapper> getInputsWithGroups() {
		return inputsWithGroups;
	}

	public ModelDisplay getWrapped() {
		return wrapped;
	}

	public Map<Long, Object> getInputsValues() {
		Map<Long, Object> inputsValues = new HashMap<Long, Object>();
		for (ModelInputDisplayItemWrapper item : wrappedInputs) {
			if (!(item instanceof ModelInputGroupDisplayItemWrapper)) {
				inputsValues.put(item.getId(), item.getTypedValue());
			} else {
				getInputsValues((ModelInputGroupDisplayItemWrapper) item,
						inputsValues);
			}
		}
		return inputsValues;
	}

	public void getInputsValues(ModelInputGroupDisplayItemWrapper group,
			Map<Long, Object> inputsValues) {
		for (ModelInputDisplayItemWrapper groupedItem : group
				.getDisplayItemsWrapped()) {
			if (groupedItem instanceof ModelInputGroupDisplayItemWrapper) {
				getInputsValues(
						(ModelInputGroupDisplayItemWrapper) groupedItem,
						inputsValues);
			} else {
				inputsValues.put(groupedItem.getId(),
						groupedItem.getTypedValue());
			}

		}
	}

	public List<ModelInputGroupDisplayItemWrapper> getTabsWrapped() {
		return wrappedTabs;
	}

	public List<ModelInputGroupDisplayItem> getTabs() {
		return wrapped.getTabs();
	}

	public boolean hasTabs() {
		return wrappedTabs.size() > 0;
	}

	public List<ModelInputDisplayItem> getOryginalInputs() {
		return wrapped.getInputs();
	}

	public List<ModelInputDisplayItemWrapper> getNonTabs() {
		return wrappedNonTabs;
	}

	public List<ModelInputDisplayItemWrapper> getWrappedInputs() {
		return wrappedInputs;
	}

    public List<ModelInputDisplayItemWrapper> getUnassociatedInputs() {
        return unassociatedInputs;
    }

}
