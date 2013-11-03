/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ext.portlet.model.ModelInputGroup;
import com.ext.portlet.model.ModelInputItem;
import com.ext.portlet.models.ModelInputGroupType;
import com.ext.portlet.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;

/**
 * Encapsulates a "group" of input elements to be displayed together.  This
 * element is backed by an {@link ModelInputGroup}
 *
 *
 * @author: jintrone
 * @date: May 24, 2010
 */
public class ModelInputGroupDisplayItem extends ModelInputDisplayItem implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Log _log = LogFactoryUtil.getLog(ModelInputGroupDisplayItem.class);

    ModelInputGroup group;

    ModelInputGroupDisplayItem parent = null;
    List<ModelInputDisplayItem> items = new ArrayList<ModelInputDisplayItem>();
    List<ModelInputGroupDisplayItem> groups = new ArrayList<ModelInputGroupDisplayItem>();
    Set<MetaData> knownmd = new HashSet<MetaData>();
    /**
     * Public constructor requires an existing backed dao. Generally, clients
     * will not call this directly, and the factory will take care of instantiating groups
     * or the static factory method on this class is called.
     *
     * @param group
     * @throws SystemException
     * @throws IOException 
     */
    public ModelInputGroupDisplayItem(ModelInputGroup group) throws SystemException, IOException {
        super(ModelInputGroupLocalServiceUtil.getModel(group), ModelInputGroupLocalServiceUtil.getMetaData(group));
        this.group = group;
        populateChildren();
    }

    public ModelInputGroupType getGroupType() {
        //return ModelInputGroupType.valueOf("HORIZONTAL");
        return group.getGroupType()==null||group.getGroupType().trim().length()==0?ModelInputGroupType.HORIZONTAL:ModelInputGroupType.valueOf(group.getGroupType());
    }

    public void setGroupType(ModelInputGroupType type) {
        group.setGroupType(type.name());

    }


    private void populateChildren() throws SystemException, IOException {
        knownmd = new HashSet<MetaData>();
        items = new ArrayList<ModelInputDisplayItem>();
        for (ModelInputItem item : ModelInputGroupLocalServiceUtil.getInputItems(group)) {
            knownmd.add(ModelInputItemLocalServiceUtil.getMetaData(item));
            items.add(ModelUIFactory.getInstance().getInputItem(item));
        }

        groups = new ArrayList<ModelInputGroupDisplayItem>();
        for (ModelInputGroup child: ModelInputGroupLocalServiceUtil.getChildGroups(group)) {
            groups.add(ModelUIFactory.getInstance().getGroupItem(child));
        }
        //why is this here?
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
    }


    @Override
    /**
     * Returns the name on the metadata, or if present, the name stored in the backing data for this elemtn
     * A non-null name in the dao will always override the metadata name.
     *
     */
    public String getName() {
        try {
            return group.getName() == null || group.getName().trim().equals("") ? 
                    ModelInputGroupLocalServiceUtil.getMetaData(group) == null ? 
                            null : ModelInputGroupLocalServiceUtil.getMetaData(group).getName() : group.getName();
        } catch (SystemException e) {
            _log.error("Could not retrive group name", e);
        } catch (IOException e) {
            _log.error("Could not retrive group description", e);
        }
        return null;
    }

/**
     * Sets the name for this element.  Will override any name in the metadata.  This method
     * sets a value in the backing store.
     *
     * @param name
     */
    public void setName(String name) throws SystemException {
        group.setName(name);
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
    }


    @Override
    /**
     * Returns the description on the metadata, or if present the description stored in the backing data for this element
     * A non-null description in the dao will always override the metadata description.
     *
     */
    public String getDescription() {
        try {
            return group.getDescription() == null || group.getDescription().trim().equals("") ? 
                    ModelInputGroupLocalServiceUtil.getMetaData(group) == null ? 
                            null : ModelInputGroupLocalServiceUtil.getMetaData(group).getDescription() : group.getDescription();
        } catch (SystemException e) {
            _log.error("Could not retrive group description", e);
        } catch (IOException e) {
            _log.error("Could not retrive group description", e);
        }
        return null;
    }

    /**
     * Sets the description for this element.  Will override any description in the metadata.  This method
     * sets a value in the backing store.
     *
     * @param desc
     */
    public void setDescription(String desc) throws SystemException {
        group.setDescription(desc);
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
    }

    /**
     * Sets the scdenario for this display group; sets the scenario for all children
     * @param s
     * @throws IncompatibleScenarioException
     */
    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        super.setScenario(s);
        for (ModelInputDisplayItem item : getDisplayItems()) {
            item.setScenario(s);
        }
    }

    @Override
    public int getOrder() {
        return group.getDisplayItemOrder();
    }

    /**
     * Set the index of this (group) element in the parent contains.  **Currently the
     * parent container is always the top most simulation display.
     *
     * @param o
     * @throws SystemException
     */
    public void setOrder(int o) throws SystemException  {
        group.setDisplayItemOrder(o);
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
    }


    /**
     * Get all display items contained by this group.  Items sorted according to their order
     * **Currently, each of these will be
     * a {@link com.ext.portlet.models.ui.ModelInputIndividualDisplayItem}
     * @return
     */
    public List<ModelInputDisplayItem> getDisplayItems() {
        Collections.sort(items);
        return items;
     }

    public List<ModelInputDisplayItem> getAllItems() {
        List<ModelInputDisplayItem> result = new ArrayList<ModelInputDisplayItem>();
        result.addAll(items);
        result.addAll(groups);
        Collections.sort(result);
        return result;
    }


    /**
     * This method will add a new element to this group, and create a new dao for this item.
     * This is the primary means by which new groups can be formed.
     *
     * @param d
     * @param type
     * @throws SystemException
     * @throws IOException 
     */
    public ModelInputDisplayItem addDisplayItem(MetaData d, ModelInputWidgetType type) throws SystemException, IOException {
        if (!knownmd.contains(d)) {
            ModelInputIndividualDisplayItem item = ModelInputIndividualDisplayItem.create(getSimulation(), d, type);
            item.item.setModelGroupId(group.getModelInputGroupPK());
            ModelInputItemLocalServiceUtil.updateModelInputItem(item.item);
            items.add(item);
            return item;
        }
        return null;
    }

    public List<ModelInputGroupDisplayItem> getChildGroups() {
        Collections.sort(groups);
        return groups;
    }

    public void setParent(ModelInputGroupDisplayItem parent) throws SystemException, IOException {
        ModelInputGroupDisplayItem old = this.parent;
        group.setParentGroupPK(parent==null?null:parent.group.getModelInputGroupPK());
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
        this.parent = parent;
        if (old!=null) old.populateChildren();
        parent.populateChildren();


    }

    public void addChildGroup(ModelInputGroupDisplayItem group) throws IllegalUIConfigurationException, SystemException, IOException {
        if (group.getGroupType() == ModelInputGroupType.TAB) {
            throw new IllegalUIConfigurationException("Tabs cannot be contained by other groups");
        }
        group.setParent(this);
    }

    /**
     * This method will remove a display item from this group, and update the backing store accordingly
     * @param d
     * @throws SystemException
     * @throws IOException 
     */
    public void removeDisplayItem(MetaData d) throws SystemException, IOException {
        ModelInputIndividualDisplayItem toremove = null;
        for (ModelInputDisplayItem item : getDisplayItems()) {
            if (item.getMetaData().equals(d)) {
                toremove = (ModelInputIndividualDisplayItem) item;
                break;
            }
        }
        if (toremove != null) {
             knownmd.remove(toremove.getMetaData());
            ModelInputItemLocalServiceUtil.deleteModelInputItem(toremove.item);

        }
        populateChildren();

    }


    public void removeGroup(ModelInputGroupDisplayItem child) throws SystemException, IOException {
        if (groups.contains(child)) {
            child.setParent(null);

        }
    }

    /**
     * This is the preferred means for creating a new group with a specifc name / description
     *
     * @param s
     * @param name
     * @param description
     * @param type
     * @return
     * @throws SystemException
     * @throws IOException 
     */
    public static ModelInputGroupDisplayItem create(Simulation s, String name, String description, 
            ModelInputGroupType type, Long parentGroupPK) throws SystemException, IOException {
        Long pk = CounterLocalServiceUtil.increment(ModelInputGroup.class.getName());
        ModelInputGroup group = ModelInputGroupLocalServiceUtil.createModelInputGroup(pk);
        group.setName(name);
        group.setDescription(description);
        group.setModelId(s.getId());
        group.setGroupType(type.name());
        
        if (parentGroupPK != null && parentGroupPK > 0) {
            group.setParentGroupPK(parentGroupPK);
        }
        
        ModelInputGroupLocalServiceUtil.addModelInputGroup(group);

        return new ModelInputGroupDisplayItem(group);

    }


    /**
     * This is the preferred means for creating a new group with a piece of metadata to be used for the
     * name and description
     * @throws IOException 
     *
     */
    public static ModelInputGroupDisplayItem create(Simulation s, MetaData md, ModelInputGroupType type, Long parentGroupPK) throws SystemException, IOException {
        Long pk = CounterLocalServiceUtil.increment(ModelInputGroup.class.getName());
        ModelInputGroup group = ModelInputGroupLocalServiceUtil.createModelInputGroup(pk);
        group.setModelId(s.getId());
        group.setNameAndDescriptionMetaDataId(md.getId());
        group.setGroupType(type.name());
        if (parentGroupPK != null && parentGroupPK > 0) {
            group.setParentGroupPK(parentGroupPK);
        }
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
        
        return new ModelInputGroupDisplayItem(group);
    }
    
    /**
     * Removes given group from the model.
     * @throws SystemException 
     * @throws PortalException 
     * @throws IOException 
     */
    public void delete() throws PortalException, SystemException, IOException {
        for (ModelInputGroupDisplayItem gchild:getChildGroups()) {
            gchild.setParent(null);
        }
        for (ModelInputDisplayItem item:getDisplayItems()) {
            ((ModelInputIndividualDisplayItem)item).setGroupId(null);
        }
        populateChildren();
        ModelInputGroupLocalServiceUtil.deleteModelInputGroup(group.getModelInputGroupPK());
    }


    /**
     * Returns display item type.
     */
    @Override
    public ModelInputDisplayItemType getDisplayItemType() {
        return ModelInputDisplayItemType.GROUP;
    }
    
    /**
     * Returns group ID. 
     */
    public Long getGroupId() {
        return group.getModelInputGroupPK();
    }
    
    /** 
     * Returns original value of name property (from db).
     */
    public String getOriginalName() {
        return group.getName();
    }
    
    /** 
     * Returns original value of description property (from db).
     */
    public String getOriginalDescription() {
        return group.getDescription();
    }


    /**
     * Sets meta data to passed value
     * @param md meta data
     * @throws SystemException 
     */
    public void setMetaData(MetaData md) throws SystemException {
        group.setNameAndDescriptionMetaDataId(md == null ? null : md.getId());
        ModelInputGroupLocalServiceUtil.updateModelInputGroup(group);
    }
    
    public Long getParentGroupId() {
        return group.getParentGroupPK();
    }
    
    public void setParentGroupId(Long groupId) throws SystemException, PortalException, IOException {
        setParent(new ModelInputGroupDisplayItem(ModelInputGroupLocalServiceUtil.getModelInputGroup(groupId)));
    }

    
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("groupType", getGroupType().name());
        jsonObject.put("groupId", getGroupId());
        jsonObject.put("parentGroupId", getParentGroupId());
        JSONArray childInputs = JSONFactoryUtil.createJSONArray();
        
        for (ModelInputDisplayItem input: getAllItems()) {
            childInputs.put(input.toJson());
        }
        jsonObject.put("inputs", childInputs);
        
        return jsonObject;    
    }
}
