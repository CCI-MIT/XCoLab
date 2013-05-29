package org.climatecollaboratorium.facelets.simulations.support;


import java.io.Serializable;
import java.util.Map;

import com.ext.portlet.models.ui.*;
import org.climatecollaboratorium.facelets.simulations.SimulationBean;

import edu.mit.cci.roma.client.MetaData;



public class ModelInputDisplayItemWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient ModelInputDisplayItem wrappedItem;
    private Double value;


    public static ModelInputDisplayItemWrapper getInputWrapper(ModelInputDisplayItem input, SimulationBean bean, Map<Long, Object> values) {
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
                if (defVal != null && defVal.trim().length() > 0) {
                    value = Double.parseDouble(defVal);
                }
                else if (wrappedItem.getMetaData().getMin() != null && wrappedItem.getMetaData().getMin().length > 1) {
                    value = Double.parseDouble(wrappedItem.getMetaData().getMin()[0]);
                } else {
                    value = 0.;
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
    
    public Object getTypedValue() {
        if (wrappedItem.getMetaData().getProfile()[0].equals(Integer.class)) {
            return Math.round(value);
        }
        else 
        return value;
    
        
    }
    
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
    public String getInternalName() {
        return getMetaData().getInternalName();
    }
    
    public String getIdVal() {
        return String.valueOf(hashCode());
    }

    public ModelInputDisplayItem getWrapped() {
        return wrappedItem;
    }

    public MetaData getMetaData() {
        return wrappedItem.getMetaData();
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

    public boolean getHasLabels() {
        return (wrappedItem instanceof ModelInputIndividualDisplayItem && ((ModelInputIndividualDisplayItem) wrappedItem).getProperty(ModelWidgetProperty.Slider.MAX_LABEL) != null);
    }
    
    public boolean getHasInterval() {
        return (wrappedItem instanceof ModelInputIndividualDisplayItem && ((ModelInputIndividualDisplayItem) wrappedItem).getProperty(ModelWidgetProperty.Slider.INTERVAL) != null);
    }

    public String getMaxLabel() {
        return getHasLabels()? ((ModelInputIndividualDisplayItem) wrappedItem).getProperty(ModelWidgetProperty.Slider.MAX_LABEL):"";

    }

    public String getMinLabel() {
        return getHasLabels()? ((ModelInputIndividualDisplayItem) wrappedItem).getProperty(ModelWidgetProperty.Slider.MIN_LABEL):"";
    }
    
    public String getInterval() {
        return getHasInterval()? ((ModelInputIndividualDisplayItem) wrappedItem).getProperty(ModelWidgetProperty.Slider.INTERVAL):"";
    }

}
