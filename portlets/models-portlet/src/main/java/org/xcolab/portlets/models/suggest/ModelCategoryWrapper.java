/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.models.suggest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.ext.portlet.model.ModelCategory;
import com.ext.portlet.model.ModelGlobalPreference;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.ext.portlet.service.ModelCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 10, 2010
 * Time: 2:46:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModelCategoryWrapper {
    private ModelCategory wrapped;
    private List<SimulationDecorator> simulations = new ArrayList<SimulationDecorator>();
    private List<SimulationDecorator> visible = new ArrayList<SimulationDecorator>();
    private boolean editing = false;


    public ModelCategoryWrapper(ModelCategory category) throws SystemException, IOException, PortalException {
        this.wrapped = category;
        refresh();
    }

    public void refresh() throws SystemException, IOException, PortalException {
        simulations.clear();
        visible.clear();
        for (ModelGlobalPreference pref:ModelCategoryLocalServiceUtil.getModelPreferences(wrapped)) {
            SimulationDecorator dec =SimulationsHelper.getInstance().getSimulationById(pref.getModelId());
            if (dec == null) {
                continue;
            }
            simulations.add(dec);

        }
         Collections.sort(simulations, new Comparator<SimulationDecorator>() {

                    @Override
                    public int compare(SimulationDecorator arg0, SimulationDecorator arg1) {
                        try {
                            return ModelUIFactory.getSimulationWeight(arg0.getWrapped()) - ModelUIFactory.getSimulationWeight(arg1);
                        } catch (SystemException e) {
                            // ignore
                        }
                        return 0;
                    }
                });

        for (SimulationDecorator sim:simulations) {
            if (sim.isVisible()) {
                visible.add(sim);
            }
        }

    }

    public ModelCategory getWrapped() {
        return wrapped;
    }

    public boolean getEditing() {
        return editing;
    }

    public void setEditing(boolean v) {
        editing = v;
    }

    public void edit(ActionEvent e) {
        editing = !editing;
    }

    public String getName() {
        return wrapped.getModelCategoryName();

    }

    public void setName(String name) throws SystemException {
        wrapped.setModelCategoryName(name);
        ModelCategoryLocalServiceUtil.updateModelCategory(wrapped);
    }

    public String getDescription() {
        return wrapped.getModelCategoryDescription();
    }

    public void setDescription(String desc) throws SystemException {
        wrapped.setModelCategoryDescription(desc);
        ModelCategoryLocalServiceUtil.updateModelCategory(wrapped);
    }

    public int getDisplayWeight() {
        return wrapped.getModelCategoryDisplayWeight();
    }

    public void setDisplayWeight(int weight) throws SystemException {
        wrapped.setModelCategoryDisplayWeight(weight);
        ModelCategoryLocalServiceUtil.updateModelCategory(wrapped);
    }

    public List<SimulationDecorator> getAllSimulations() {
        return simulations;
    }

    public List<SimulationDecorator> getVisibleSimulations() {
        return visible;
    }



    public void updateSimulations() {
    }


}
