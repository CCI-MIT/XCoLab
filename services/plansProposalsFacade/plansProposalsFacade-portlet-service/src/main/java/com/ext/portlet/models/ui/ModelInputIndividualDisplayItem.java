package com.ext.portlet.models.ui;

import java.io.IOException;
import java.io.Serializable;

import com.ext.portlet.model.ModelInputItem;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Variable;

/**
 * Layout information for individual (non-group) display elements.
 */
public class ModelInputIndividualDisplayItem extends ModelInputDisplayItem implements Serializable {

	private static final long serialVersionUID = 1L;

	ModelInputWidgetType type = ModelInputWidgetType.TEXT_FIELD;
    ModelInputItem item;


    public ModelInputIndividualDisplayItem(ModelInputItem item) throws SystemException, IOException {
        super(ModelInputItemLocalServiceUtil.getModel(item),ModelInputItemLocalServiceUtil.getMetaData(item));
        this.item = item;
    }


    /**
     * Returns the variable associated with this element if a "setScenario" has
     * been called with a valid scenario.
     */
     public Variable getVariable() {
        if (getScenario()!=null) {
            return ModelUIFactory.getVariableForMetaData(getScenario(),getMetaData(),true);
        }
        return null;
    }

    /**
     * The widget type for this element
     */
    @Override
    public ModelInputWidgetType getType() {
      return item.getType()==null?ModelInputWidgetType.TEXT_FIELD:ModelInputWidgetType.valueOf(item.getType());
    }


    /**
     * Sets the widget type for this element.  Updates the backing store.
     */
    @Override
    public void setType(ModelInputWidgetType type) throws SystemException {
         item.setType(type.name());
         ModelInputItemLocalServiceUtil.updateModelInputItem(item);
    }


    /**
     * The order of this element within its parent container, which may either be a group
     * or the top level display element.
     */
    @Override
    public int getOrder() {
        return item.getDisplayItemOrder();
    }

    /**
     * Set the display position of this element.  Updates the backing store.
     */
    @Override
    public void setOrder(int order) throws SystemException {
       item.setDisplayItemOrder(order);
       ModelInputItemLocalServiceUtil.updateModelInputItem(item);
    }


    /**
     * This is the preferred means for creating a new, non-group display item.
     * Note that calling {@link com.ext.portlet.models.ui.ModelInputGroupDisplayItem#addDisplayItem(mit.simulation.climate.client.MetaData, ModelInputWidgetType)}
     * will call this code and then set the group id on the item.
     */
    public static ModelInputIndividualDisplayItem create(Simulation sim, MetaData md, ModelInputWidgetType type) throws SystemException, IOException {
         Long pk = CounterLocalServiceUtil.increment(ModelInputItem.class.getName());
            ModelInputItem item = ModelInputItemLocalServiceUtil.createModelInputItem(pk);
            item.setModelId(sim.getId());
            item.setModelInputItemID(md.getId());
            item.setType(type.name());
            ModelInputItemLocalServiceUtil.addModelInputItem(item);
        return new ModelInputIndividualDisplayItem(item);
    }
    
    /**
     * Returns display item type.
     */
    @Override
    public ModelInputDisplayItemType getDisplayItemType() {
        return ModelInputDisplayItemType.INDIVIDUAL;
    }
    
    /**
     * Returns group ID for given input (can be null). 
     */
    public Long getGroupId() {
        return item.getModelGroupId();
    }
    
    /**
     * Sets group ID.
     */
    public void setGroupId(Long modelGroupId) throws SystemException {
        if (modelGroupId!=null && modelGroupId <= 0) {
            modelGroupId = 0L;
        }
        item.setModelGroupId(modelGroupId);
        ModelInputItemLocalServiceUtil.updateModelInputItem(item);
    }

    public String getProperty(ModelWidgetProperty prop) {
        return ModelInputItemLocalServiceUtil.getPropertyMap(item).get(prop.toString());
    }
    
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        
        
        
        jsonObject.put("description", getDescription());
        jsonObject.put("groupId", getGroupId());
        if (getVariable() != null) {
            jsonObject.put("value", ModelUIFactory.convertToJson(getVariable()));
        }
        if (getMetaData() != null) {
            jsonObject.put("metaData", ModelUIFactory.convertToJson(getMetaData()));
        }
        
        String maxLabel = getProperty(ModelWidgetProperty.Slider.MAX_LABEL);
        String minLabel = getProperty(ModelWidgetProperty.Slider.MIN_LABEL);
        
        if (maxLabel != null) {
            jsonObject.put("maxLabel", maxLabel);
        }
        if (minLabel != null) {
            jsonObject.put("minLabel", minLabel);
        }
        return jsonObject;    
    }

    
}
