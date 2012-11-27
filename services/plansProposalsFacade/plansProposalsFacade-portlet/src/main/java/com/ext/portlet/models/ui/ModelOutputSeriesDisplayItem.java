/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ext.portlet.models.NoSuchModelOutputItemException;
import com.ext.portlet.models.model.ModelOutputItem;
import com.ext.portlet.models.service.ModelOutputItemLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import edu.mit.cci.simulation.client.MetaData;
import edu.mit.cci.simulation.client.Simulation;
import edu.mit.cci.simulation.client.TupleStatus;
import edu.mit.cci.simulation.client.Variable;
import edu.mit.cci.simulation.client.comm.ClientRepository;

/**
 * Wrapper around series metadata; series metadata is merely that
 * data that is indexed by some other metadata (e.g. in a time series).  It is always part of a {@link
 * com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem}
 *
 * @author: jintrone
 * @date: May 25, 2010
 */
public class ModelOutputSeriesDisplayItem extends ModelOutputDisplayItem{


    ModelOutputItem item;

    private MetaData md;

    private Map<TupleStatus,ModelOutputErrorBehavior> errorBehaviors = new HashMap<TupleStatus,ModelOutputErrorBehavior>();

    private static Log _log = LogFactoryUtil.getLog(ModelOutputSeriesDisplayItem.class);

    /**
     * Clients should not need to call this method directly.  Instead, {@link com.ext.portlet.models.ui.ModelUIFactory}
     * should be used to generate the display classes, and any subsequent modifications made there.
     *
     * Note that this constructor implicitly creates a backing store.
     *
     * @param s
     * @param md
     * @throws SystemException
     */
    ModelOutputSeriesDisplayItem(Simulation s, MetaData md) throws SystemException {
        super(s);
        this.md = md;
        try {
            item = ModelOutputItemLocalServiceUtil.getOutputItem(md);
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
        item.setModelOutputItemId(md.getId());
        item.setModelItemIsVisible(true);
        ModelOutputItemLocalServiceUtil.addModelOutputItem(item);
    }

    public MetaData getMetaData() {
        return md;
    }
   

    public Variable getVariable() {
        return ModelUIFactory.getVariableForMetaData(getScenario(),getMetaData(),false);
    }

    @Override
    public String getName() {
      return md.getName();
    }


    @Override
    public int getOrder() {
        return item!=null && item.getModelOutputItemOrder()!=null?item.getModelOutputItemOrder():-1;
    }

    /**
     * Sets the display order for this display item within its parent container.  Generally
     * this will boil down to the order in the legend.
     *
     * @param i
     * @throws SystemException
     */
    public void setOrder(int i) throws SystemException {
        item.setModelOutputItemOrder(i);
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
    }


    /**
     * Get additional information about this series
     *
     * @return
     */
    public ModelOutputSeriesType getSeriesType() {
         return (item.getItemType() == null || item.getItemType().trim().equals("")) ? ModelOutputSeriesType.NORMAL:ModelOutputSeriesType.valueOf(item.getItemType());
    }


    /**
     * Sets additional information about this series; this setting is persisted
     * in the backing store.
     *
     * @return
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
     *
     * @return
     * @throws SystemException
     * @throws IOException 
     */
    public MetaData getAssociatedMetaData() throws SystemException, IOException {
            Long l = item.getRelatedOutputItem();
            // FIXME CollaboratoriumModelingService won't work as PropsUtil can't be found from portlet
            //return l==null?null:CollaboratoriumModelingService.repository().getMetaData(item.getRelatedOutputItem());
            return l==null?null:ClientRepository.instance().getMetaData(item.getRelatedOutputItem());
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

    public ModelOutputErrorBehavior getInvalidError() {
        return getError(getVariable(),TupleStatus.INVALID,1);
    }

    
     public void setVisible(boolean b) throws SystemException {
        item.setModelItemIsVisible(b);
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);

    }

    @Override
    public boolean isVisible() {
        if (item.getModelItemIsVisible() == null) {
            try {
                setVisible(true);
            } catch(SystemException e) {
                _log.error("Error setting chart visibility to default of true",e);
            }
        }

        return item.getModelItemIsVisible()==null || item.getModelItemIsVisible();
    }
    
    public String getLabelFormatString() {
        return item.getModelItemLabelFormat();
    }
    
    public void setLabelFormatString(String format) throws SystemException {
        item.setModelItemLabelFormat(format);
        ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
    }


}
