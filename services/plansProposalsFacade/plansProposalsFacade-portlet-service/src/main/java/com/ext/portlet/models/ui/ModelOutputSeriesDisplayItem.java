package com.ext.portlet.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.TupleStatus;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.comm.ClientRepository;

import com.ext.portlet.NoSuchModelOutputItemException;
import com.ext.portlet.model.ModelOutputItem;
import com.ext.portlet.service.ModelOutputItemLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ModelOutputSeriesDisplayItem extends ModelOutputDisplayItem{

    ModelOutputItem item;

    private final MetaData metaData;

    private final Map<TupleStatus,ModelOutputErrorBehavior> errorBehaviors = new HashMap<>();

    private static Log _log = LogFactoryUtil.getLog(ModelOutputSeriesDisplayItem.class);

    /**
     * Clients should not need to call this method directly.  Instead, {@link com.ext.portlet.models.ui.ModelUIFactory}
     * should be used to generate the display classes, and any subsequent modifications made there.
     *
     * Note that this constructor implicitly creates a backing store.
     */
    ModelOutputSeriesDisplayItem(Simulation s, MetaData metaData) throws SystemException {
        super(s);
        this.metaData = metaData;
        try {
            item = ModelOutputItemLocalServiceUtil.getOutputItem(metaData);
        } catch (NoSuchModelOutputItemException e) {
            createPersistence();
        } catch (SystemException e) {
           _log.error(e);
            throw(e);
        }
    }

    private void createPersistence() throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(ModelOutputItem.class.getName());
        item = ModelOutputItemLocalServiceUtil.createModelOutputItem(pk);
        item.setModelId(getSimulation().getId());
        item.setModelOutputItemId(metaData.getId());
        item.setModelItemIsVisible(true);
        ModelOutputItemLocalServiceUtil.addModelOutputItem(item);
    }

    public MetaData getMetaData() {
        return metaData;
    }
   

    public Variable getVariable() {
        if (getScenario() == null || getMetaData() == null) {
            return null;
        }
        return ModelUIFactory.getVariableForMetaData(getScenario(),getMetaData(),false);
    }

    @Override
    public String getName() {
      return metaData.getName();
    }


    @Override
    public int getOrder() {
        return item != null ? item.getModelOutputItemOrder() : -1;
    }

    /**
     * Sets the display order for this display item within its parent container.  Generally
     * this will boil down to the order in the legend.
     */
    @Override
    public void setOrder(int i) throws SystemException {
        item.setModelOutputItemOrder(i);
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
    }


    /**
     * Get additional information about this series
     */
    public ModelOutputSeriesType getSeriesType() {
         return (item.getItemType() == null || item.getItemType().trim().equals("")) ? ModelOutputSeriesType.NORMAL:ModelOutputSeriesType.valueOf(item.getItemType());
    }


    /**
     * Sets additional information about this series; this setting is persisted
     * in the backing store.
     */
    public void setSeriesType(ModelOutputSeriesType type) throws SystemException {
        if (item!=null) {
            item.setItemType(type.name());
            ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
        }
    }


    /**
     * If this metadata is "about" another piece of meta data within the same simulation,
     * this method will return that metadata.
     */
    public MetaData getAssociatedMetaData() throws SystemException, IOException {
            long l = item.getRelatedOutputItem();
            // FIXME CollaboratoriumModelingService won't work as PropsUtil can't be found from portlet
            //return l==null?null:CollaboratoriumModelingService.repository().getMetaData(item.getRelatedOutputItem());
            return l <= 0 ?null:ClientRepository.instance().getMetaData(item.getRelatedOutputItem());
    }

    public void setAssociatedMetaData(MetaData md) throws SystemException {
        item.setRelatedOutputItem(md.getId());
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
    }
    
    public Long getAssociatedMetaDataId() {
        return item.getRelatedOutputItem();
    }
    
    public void setAssociatedMetaDataId(Long id) throws SystemException {
        item.setRelatedOutputItem(id);
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);   
    }

    @Override
    public ModelOutputDisplayItemType getDisplayItemType() {
        return ModelOutputDisplayItemType.SERIES;
    }

    public void setErrorBehavior(TupleStatus status, ErrorPolicy policy, String msg) throws SystemException {
      if (status == TupleStatus.OUT_OF_RANGE) {
          item.setModelItemRangeMessage(msg);
          item.setModelItemRangePolicy(policy!=null?policy.name():null);
      } else if (status == TupleStatus.INVALID) {
          item.setModelItemErrorMessage(msg);
          item.setModelItemErrorPolicy(policy!=null?policy.name():null);
      }
        errorBehaviors.remove(status);
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
    }

    @Override
    public ModelOutputErrorBehavior getErrorBehavior(TupleStatus status) {
       ModelOutputErrorBehavior behavior = null;
       if (!errorBehaviors.containsKey(status)) {
         behavior = ModelOutputErrorBehavior.getBehavior(status,item);
         errorBehaviors.put(status,behavior);
       }
       return errorBehaviors.get(status);
    }

     @Override
    public ModelOutputErrorBehavior getRangeError() {
        return getError(getVariable(),TupleStatus.OUT_OF_RANGE,1);
    }

    @Override
    public ModelOutputErrorBehavior getInvalidError() {
        return getError(getVariable(),TupleStatus.INVALID,1);
    }

    
     public void setVisible(boolean b) throws SystemException {
        item.setModelItemIsVisible(b);
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);

    }

    @Override
    public boolean isVisible() {
        if (item.getModelItemIsVisible()) {
            try {
                setVisible(true);
            } catch(SystemException e) {
                _log.error("Error setting chart visibility to default of true",e);
            }
        }

        return item.getModelItemIsVisible();
    }
    
    public String getLabelFormatString() {
        return item.getModelItemLabelFormat();
    }
    
    public void setLabelFormatString(String format) throws SystemException {
        item.setModelItemLabelFormat(format);
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
    }
    
    @Override
    public ModelOutputChartType getChartType() {
        return ModelOutputChartType.TIME_SERIES;
    }
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        if (getVariable() != null) {
            jsonObject.put("variable", ModelUIFactory.convertToJson(getVariable()));
        }
        else {

            JSONObject variableObj = JSONFactoryUtil.createJSONObject();
            jsonObject.put("variable", variableObj);
            variableObj.put("metaData", ModelUIFactory.convertToJson(getMetaData()));
        }
        jsonObject.put("outputType", "SERIES");
        jsonObject.put("labelFormatString", getLabelFormatString());
        Long associatedMetaDataId = getAssociatedMetaDataId();
        if (associatedMetaDataId != null && associatedMetaDataId > 0) {
            jsonObject.put("associatedMetaDataId", associatedMetaDataId);
        }
        
        
        return jsonObject;
    }


}
