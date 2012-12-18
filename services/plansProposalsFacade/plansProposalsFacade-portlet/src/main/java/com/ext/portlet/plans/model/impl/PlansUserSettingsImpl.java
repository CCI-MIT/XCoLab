/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ext.portlet.plans.NoSuchPlanAttributeFilterException;
import com.ext.portlet.plans.NoSuchPlanColumnSettingsException;
import com.ext.portlet.plans.NoSuchPlanPropertyFilterException;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.PlanColumnSettings;
import com.ext.portlet.plans.model.PlanPropertyFilter;
import com.ext.portlet.plans.service.PlanAttributeFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlanColumnSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPropertyFilterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class PlansUserSettingsImpl extends PlansUserSettingsBaseImpl {
    
    private Map<String, PlanColumnSettings> columnSettingsCache = new HashMap<String, PlanColumnSettings>();
    private Map<String, PlanAttributeFilter> attributeFiltersCache = new HashMap<String, PlanAttributeFilter>();
    private Map<String, PlanPropertyFilter> propertyFiltersCache = new HashMap<String, PlanPropertyFilter>();
    private List<Long> positionsIds = new ArrayList<Long>();
    
    private boolean filtersDefined = false;
    
    public PlansUserSettingsImpl() {
    }
    
    public PlanColumnSettings getColumnSettings(String name) throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings columnSettings = null;
        if (!columnSettingsCache.containsKey(name)) {
            try {
                columnSettings = PlanColumnSettingsLocalServiceUtil.findByPlanUserSettingsIdColumnName(this.getPlanUserSettingsId(), name);
            }
            catch (NoSuchPlanColumnSettingsException e) {
                // ignore
            }
            if (columnSettings == null) {
                // get default value
                columnSettings = PlanColumnSettingsLocalServiceUtil.createPlanColumnSettings(0L);
                columnSettings.setColumnName(name);
                columnSettings.setVisible(Columns.valueOf(name).isDefault());
            }
            columnSettingsCache.put(columnSettings.getColumnName(), columnSettings);
        }
        
        return columnSettingsCache.get(name);
    }
    
    public void addColumnSettings(PlanColumnSettings settings) {
        columnSettingsCache.put(settings.getColumnName(), settings);
    }
    
    public List<PlanColumnSettings> getUpdatedColumnSettings() {
        return Collections.unmodifiableList(new ArrayList<PlanColumnSettings>(columnSettingsCache.values()));
    }
    
    
    /**
     * Getter for positionsIds.
     * @return the positionsIds
     */
    public List<Long> getPositionsIds() {
        return positionsIds;
    }
    /**
     * Setter for positionsIds.
     * @param positionsIds the positionsIds to set
     */
    public void setPositionsIds(List<Long> positionsIds) {
        this.positionsIds = positionsIds;
    }
    
    
    public PlanAttributeFilter getAttributeFilter(String name) throws NoSuchPlanAttributeFilterException, SystemException {
        PlanAttributeFilter filter = null;
        if (!attributeFiltersCache.containsKey(name)) {
            try {
                attributeFiltersCache.put(name,PlanAttributeFilterLocalServiceUtil.getByPlansUserSettingsIdAttributeName(this.getPlanUserSettingsId(), name));

            }
            catch (NoSuchPlanAttributeFilterException e) {
                return null;
            }

        }
        return attributeFiltersCache.get(name);
    }

    
    public void addPlanAttributeFilter(PlanAttributeFilter filter) {
        attributeFiltersCache.put(filter.getAttributeName(), filter);
        filtersDefined = true;
    }
    
    @SuppressWarnings("unchecked")
    public List<PlanAttributeFilter> getUpdatedPlanAttributeFilters() {
        return Collections.unmodifiableList(new ArrayList<PlanAttributeFilter>(attributeFiltersCache.values()));
    }
    
    
    public PlanPropertyFilter getPropertyFilter(String name) throws NoSuchPlanPropertyFilterException, SystemException {
        PlanPropertyFilter filter = null;
        if (!propertyFiltersCache.containsKey(name)) {
            try {
                return PlanPropertyFilterLocalServiceUtil.getByPlanPlanUserSettingsIdPropertyName(this.getPlanUserSettingsId(), name);
            }
            catch (NoSuchPlanPropertyFilterException e) {
                return null;
            }
            //propertyFiltersCache.put(name, filter);
        }
        return propertyFiltersCache.get(name);
    }

    
    public void addPlanPropertyFilter(PlanPropertyFilter filter) {
        propertyFiltersCache.put(filter.getPropertyName(), filter);
        filtersDefined = true;
    }
    
    @SuppressWarnings("unchecked")
    public List<PlanPropertyFilter> getUpdatedPlanPropertyFilters() {
        return Collections.unmodifiableList(new ArrayList<PlanPropertyFilter>(propertyFiltersCache.values()));
    }
    
    public boolean isFiltersDefined() {
        return filtersDefined || this.getPlanUserSettingsId() != 0L;
    }
}
