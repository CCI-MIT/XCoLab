package org.xcolab.portlets.models.suggest;

import java.io.Serializable;
import java.util.Map;

import org.xcolab.portlets.models.SimulationDetailsBean;

import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItemType;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelInputIndividualDisplayItem;
import com.ext.portlet.models.ui.ModelInputWidgetType;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.MetaData;

public class ModelInputDisplayItemWrapper implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient ModelInputDisplayItem wrappedItem;
    private Double value;


    public static ModelInputDisplayItemWrapper getInputWrapper(ModelInputDisplayItem input, SimulationDetailsBean bean, Map<Long, Object> values) {
        if (input.getDisplayItemType() == ModelInputDisplayItemType.GROUP) {
            return new ModelInputGroupDisplayItemWrapper((ModelInputGroupDisplayItem) input, bean, values);
        }
        return new ModelInputDisplayItemWrapper(input, values);
    }
    
    public ModelInputDisplayItemWrapper(ModelInputDisplayItem wrappedItem, Map<Long, Object> values) {
        this.wrappedItem = wrappedItem;
        
        if (this.hasValue()) {
            if (values.containsKey(wrappedItem.getMetaData().getId())) {
                value = Double.valueOf(values.get(getId()).toString());
            }
            else {
                String defVal = wrappedItem.getMetaData() != null && wrappedItem.getMetaData().getDefault() != null ? wrappedItem.getMetaData().getDefault()[0] : null;
                try {
                	value = defVal == null || defVal.trim().length() == 0 ? Double.parseDouble(wrappedItem.getMetaData().getMin()[0]) : Double.parseDouble(defVal);
                }
                catch (NumberFormatException e) {
                	value = null;
                }
            }
        }
    }
    
    public Long getGroupId() {
        if (wrappedItem instanceof ModelInputGroupDisplayItem) {
            return ((ModelInputGroupDisplayItem) wrappedItem).getGroupId();
        }
        else if (wrappedItem instanceof ModelInputIndividualDisplayItem) {
            return ((ModelInputIndividualDisplayItem) wrappedItem).getGroupId();
        }
        return 0L;
    }
    
    public void setGroupId(Long groupId) throws SystemException {
        // ignore
        if (wrappedItem instanceof ModelInputIndividualDisplayItem) {
            ((ModelInputIndividualDisplayItem) wrappedItem).setGroupId(groupId);
        }
    }
    
    public Object getTypedValue() {
        if (wrappedItem.getMetaData().getProfile()[0].equals(Integer.class)) {
            return Math.round(value);
        }
        return value;
    
        
    }
    
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ModelInputDisplayItem getWrapped() {
        return wrappedItem;
    }

    public MetaData getMetaData() {
        return wrappedItem == null ? null : wrappedItem.getMetaData();
    }

    public ModelInputWidgetType getType() {
        return wrappedItem.getType();
    }

    public ModelInputDisplayItemType getDisplayItemType() {
        return wrappedItem.getDisplayItemType();
    }

    public String getName() {
        return wrappedItem.getName();
    }

    public String getDescription() {
        return wrappedItem.getDescription();
    }
    
    protected boolean hasValue() {
        return true;
    }
    
    public Long getId() {
        return getMetaData().getId();
    }
    
    public int getOrder() {
        return wrappedItem.getOrder();
    }
    
    public void setOrder(int order) throws SystemException {
        wrappedItem.setOrder(order);
    }
}