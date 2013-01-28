/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.xcolab.portlets.models.suggest.ModelCategoryWrapper;
import org.xcolab.portlets.models.suggest.SimulationDecorator;

import com.ext.portlet.model.ModelCategory;
import com.ext.portlet.service.ModelCategoryLocalServiceUtil;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 10, 2010
 * Time: 3:55:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoriesBean {

    public List<ModelCategoryWrapper> categories = new ArrayList<ModelCategoryWrapper>();
    public List<SelectItem> categoriesAsSelectItems = new ArrayList<SelectItem>();
    private Map<Long, ModelCategoryWrapper> categoriesById = new HashMap<Long,ModelCategoryWrapper>();

    private AddCategoryBean add = new AddCategoryBean();

    private boolean adding = false;




    public CategoriesBean() throws SystemException, IOException, PortalException {

          refresh();

    }

    public void refresh() throws SystemException, IOException, PortalException {
        categories.clear();
        categoriesById.clear();
        categoriesAsSelectItems.clear();
        categoriesAsSelectItems.add(new SelectItem(-1,"(none)"));
         for (ModelCategory cat: ModelCategoryLocalServiceUtil.getModelCategories(0,Integer.MAX_VALUE)) {
            ModelCategoryWrapper wrapper = new ModelCategoryWrapper(cat);
            categories.add(wrapper);
            categoriesById.put(cat.getModelCategoryPK(),wrapper);
             categoriesAsSelectItems.add(new SelectItem(cat.getModelCategoryPK(),cat.getModelCategoryName()));
          }

    }

    public void updateCategorySimulations() throws SystemException, IOException, PortalException {
       for (ModelCategoryWrapper category:categories) {
           category.refresh();
       }
    }

    public ModelCategoryWrapper getCategory(Long id) {
        return categoriesById.get(id);
    }

    public List<ModelCategoryWrapper> getCategories() {
        return categories;
    }

    public void addingCategory(ActionEvent evt) {
       adding = true;
    }

    public void cancelAdd(ActionEvent evt) {
        add.reset();
        adding = false;

    }

    public void submitAdd(ActionEvent evt) throws SystemException, IOException, PortalException {
        ModelCategoryLocalServiceUtil.addCategory(add.getName(),add.getDescription());
        add.reset();
        refresh();
        adding = false;

    }

    public boolean isAdding() {
        return adding;

    }

    public AddCategoryBean getAddCategory() {
        return add;
    }

    public void setAdding(boolean adding) {
        this.adding = adding;
    }

    public void changeSimulationCategory(ValueChangeEvent evt) throws SystemException, IOException, PortalException {
        Long categoryId = (Long)evt.getNewValue();
        SimulationDecorator dec = (SimulationDecorator) evt.getComponent().getAttributes().get("simulation");
        addSimulationToCategory(dec,getCategory(categoryId));
    }

    public void addSimulationToCategory(SimulationDecorator sim, ModelCategoryWrapper wrapper) throws SystemException, PortalException, IOException {
        ModelCategory old = ModelGlobalPreferenceLocalServiceUtil.getCategory(sim);
        ModelGlobalPreferenceLocalServiceUtil.updateModelCategory(wrapper == null?null:wrapper.getWrapped(),sim);
        if (old!=null && (wrapper == null || ! (old.getModelCategoryPK() == wrapper.getWrapped().getModelCategoryPK()))) {
           getCategory(old.getModelCategoryPK()).refresh();
        }
        if (wrapper!=null) {
            wrapper.refresh();
        } 
    }

   

    public List<SelectItem> getAvailableCategories() {
        return categoriesAsSelectItems;
    }


}
