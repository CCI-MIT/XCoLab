/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ext.portlet.model.ModelInputGroup;
import com.ext.portlet.model.ModelInputItem;
import com.ext.portlet.model.ModelPosition;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;
import com.ext.portlet.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;
import com.ext.portlet.service.ModelPositionLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Variable;

/**
 * @author: jintrone
 * @date: May 24, 2010
 */
public class ModelUIFactory {
    private static ModelUIFactory ourInstance = new ModelUIFactory();

    private static Log _log = LogFactoryUtil.getLog(ModelUIFactory.class);
    
    public static ModelUIFactory getInstance() {
        return ourInstance;
    }

    private ModelUIFactory() {

    }

    /**
     * Returns the layout information for the model
     *
     * @param s
     * @return
     * @throws IOException 
     */
    public ModelDisplay getDisplay(Simulation s) throws SystemException, IllegalUIConfigurationException, IOException {
        return new ModelDisplay(s);
    }


    /**
     * Returns the layout information for the model, and also sets the scenario
     * on the display container (enabling variable retrieval functions through the
     * display classes
     *
     * @param s
     * @return
     * @throws IOException 
     */
    public ModelDisplay getDisplay(Scenario s) throws SystemException, IllegalUIConfigurationException, IOException {
        return new ModelDisplay(s);
    }


    /**
     * Package scoped helper function, used to build the output layout classes for the Simulation
     *
     * @param s
     * @return
     */
    List<ModelOutputDisplayItem> parseOutputs(Simulation s) {
        Map<String, ModelOutputDisplayItem> found = new HashMap<String, ModelOutputDisplayItem>();
        for (MetaData md : s.getOutputs()) {

            if (md.getVarContext() == MetaData.VarContext.INDEXED) {
                ModelOutputIndexedDisplayItem item = null;
                if (md.getVarType() == MetaData.VarType.FREE) {
                    item = (ModelOutputIndexedDisplayItem) found.get(md.getName());
                    if (item == null) {
                        try {
                            item = new ModelOutputIndexedDisplayItem(s, md.getName());
                        } catch (SystemException e) {
                            _log.error(e);
                        }
                        item.setChartType(ModelOutputChartType.FREE);
                        found.put(md.getName(), item);
                    }
                    try {
                        item.addSeriesData(md);
                    } catch (SystemException e) {
                        _log.error(e);
                    }
                } else if (md.getVarType() == MetaData.VarType.RANGE) {
                    if (md.getLabels().length < 2) {
                        _log.warn("Metadata "+md.getName()+" only has one element");
                    }
                    item = (ModelOutputIndexedDisplayItem) found.get(md.getLabels()[1]);
                    if (item == null) {
                        try {
                            item = new ModelOutputIndexedDisplayItem(s, md.getLabels()[1]);
                        } catch (SystemException e) {
                            _log.error(e);
                        }
                        item.setChartType(ModelOutputChartType.TIME_SERIES);
                        found.put(md.getLabels()[1], item);
                    }
                    try {
                        item.addSeriesData(md);
                    } catch (SystemException e) {
                        _log.error(e);
                    }

                } else {
                  _log.warn("Unknown variable type "+md.getVarType());
                   continue;
                }
                if (item.getIndex() == null) {
                    item.setIndex(md.getIndexingMetaData());
                }
            } else if (md.getVarContext() == MetaData.VarContext.SCALAR) {
                found.put(md.getName(), new ModelOutputScalarDisplayItem(s, md));

            }

        }

        return new ArrayList<ModelOutputDisplayItem>(found.values());
    }

    /**
     * Recursive call to process groups
     *
     * @param group
     * @param bareMetaData
     * @return
     * @throws SystemException
     * @throws IllegalUIConfigurationException
     * @throws IOException 
     */
    private ModelInputGroupDisplayItem processGroup(ModelInputGroup group, Set<MetaData> bareMetaData) throws SystemException, IllegalUIConfigurationException, IOException {
        ModelInputGroupDisplayItem result=null;
        for (ModelInputItem item : ModelInputGroupLocalServiceUtil.getInputItems(group)) {
                try {
                    bareMetaData.remove(ModelInputItemLocalServiceUtil.getMetaData(item));
                } catch (SystemException e) {
                    _log.error(e);
                }

            }
        try {
            result = new ModelInputGroupDisplayItem(group);
        } catch (SystemException e) {
            _log.error(e);
            return null;
        } catch (IOException e) {
            _log.error(e);
            return null;
        }
        for (ModelInputGroup g: ModelInputGroupLocalServiceUtil.getChildGroups(group)) {
           result.addChildGroup(processGroup(g,bareMetaData));
        }
        return result;
    }

    /**
     * Package scoped helper function, used to build the input layout classes for the Simulation
     *
     * @param s
     * @return
     * @throws IOException 
     */
    public List<ModelInputDisplayItem> parseInputs(Simulation s) throws SystemException, IllegalUIConfigurationException, IOException {
        List<ModelInputDisplayItem> result = new ArrayList<ModelInputDisplayItem>();
        Set<MetaData> inputs = new HashSet<MetaData>(s.getInputs());

        for (ModelInputGroup group : ModelInputGroupLocalServiceUtil.getInputGroups(s)) {
            if (group.getParentGroupPK() <= 0) {
                result.add(processGroup(group,inputs));
            }
        }


        //any left overs
        for (MetaData md : inputs) {
                try {
                    ModelInputItem item = ModelInputItemLocalServiceUtil.getItemForMetaData(s.getId(), md);
                    ModelInputDisplayItem toadd = item==null?ModelInputIndividualDisplayItem.create(s,md,ModelInputWidgetType.TEXT_FIELD):getInputItem(item);
                    result.add(toadd);


                } catch (SystemException e) {
                   _log.error(e);
                } catch (IOException e) {
                    _log.error(e);
                }
            }

        return result;
    }

    public ModelInputDisplayItem getInputItem(ModelInputItem item) {
        try {
            return new ModelInputIndividualDisplayItem(item);
        } catch (SystemException e) {
            _log.error(e);
        } catch (IOException e) {
            _log.error(e);
        }
        return null;

    }

    public ModelInputGroupDisplayItem getGroupItem(ModelInputGroup item) {
        try {
            return new ModelInputGroupDisplayItem(item);
        } catch (SystemException e) {
            _log.error(e);
        } catch (IOException e) {
            _log.error(e);
        }
        return null;

    }

    public static boolean isSimulationVisible(Simulation sim) throws SystemException {
        return ModelGlobalPreferenceLocalServiceUtil.isVisible(sim);
    }

     public static void setSimulationVisible(Simulation sim, boolean b) throws SystemException {
        ModelGlobalPreferenceLocalServiceUtil.setVisible(sim,b);
    }
     
    public static int getSimulationWeight(Simulation sim) throws SystemException {
        return ModelGlobalPreferenceLocalServiceUtil.getWeight(sim);
    }
    
    public static void setSimulationWeight(Simulation sim, int weight) throws SystemException {
        ModelGlobalPreferenceLocalServiceUtil.setWeight(sim, weight);
    }
    
    public static Long getSimulationExpertEvaluationPageId(Simulation sim) throws SystemException {
        return ModelGlobalPreferenceLocalServiceUtil.getExpertEvaluationPageId(sim);
    }
    
    public static void setSimulationExpertEvaluationPageId(Simulation sim, Long pageId) throws SystemException {
        ModelGlobalPreferenceLocalServiceUtil.setExpertEvaluationPageId(sim, pageId);
    }


    /**
     * Helper function, returns variable for a scenario given its associated metadata
     *
     * @param s
     * @return
     */
    public static Variable getVariableForMetaData(Scenario s, MetaData md, boolean isInput) {
        Variable result = null;
        for (Variable var : (isInput ? s.getInputSet() : s.getOutputSet())) {
            System.out.println("ids: " + md.getId() + " ==? " + var.getMetaData().getId() + " equals = " + var.getMetaData().equals(md));
            if (var.getMetaData().equals(md)) {
                result = var;
                break;
            }

        }
        return result;
    }



    //this is just sample code - could be made into a test case with apppriate mocks
    // (unless of course 999 is actually the id of a model)
   /* private static void example() throws SystemException, IOException, IncompatibleScenarioException, IllegalUIConfigurationException {

        //setting up a new group

        ClientRepository repo = CollaboratoriumModelingService.repository();
        Simulation s = repo.getSimulation(999L);


        //assume first three inputs to be grouped, with the first element serving
        //as name and description of group
        ModelInputGroupDisplayItem group = ModelInputGroupDisplayItem.create(s,s.getInputs().get(0), ModelInputGroupType.HORIZONTAL);
        ModelInputDisplayItem item1 = group.addDisplayItem(s.getInputs().get(0),ModelInputWidgetType.SLIDER);
        ModelInputDisplayItem item2 = group.addDisplayItem(s.getInputs().get(1),ModelInputWidgetType.TEXT_FIELD);
        ModelInputDisplayItem item3 = group.addDisplayItem(s.getInputs().get(2),ModelInputWidgetType.TEXT_FIELD);

        //set the order of these items
        item1.setOrder(3);
        item2.setOrder(1);
        item3.setOrder(2);


        //changed my mind - I want a better description for this group!
        group.setDescription("A better description!");

        //ok, that's it now let's set the order for the inputs
        ModelDisplay display = ModelUIFactory.getInstance().getDisplay(s);
        List<Long> inputorder = Arrays.asList(123L,345L,456L,678L,901L);

        for (ModelInputDisplayItem input:display.getInputs()) {
           input.setOrder(input.getMetaData()!=null?inputorder.indexOf(input.getMetaData().getId()):999);
        }

        //order for outputs
        List<String> outputorder = Arrays.asList("foo","bar","baz","frank");

        for (ModelOutputDisplayItem output:display.getOutputs()) {
            output.setOrder(outputorder.indexOf(output.getName()));


            //Set the order of the elements in one of the charts
            if ("baz".equals(output.getName())) {
                ModelOutputIndexedDisplayItem item = (ModelOutputIndexedDisplayItem) output;
                List seriesorder = Arrays.asList(321L,432L,543L);
                for (ModelOutputSeriesDisplayItem seriesitem:item.getSeries()) {
                  seriesitem.setOrder(seriesorder.indexOf(seriesitem.getMetaData().getId()));
                }

                //set two of the series to be confidence intervals for a third
                ModelOutputSeriesDisplayItem conf5 = item.getSeriesForMetaData(repo.getMetaData(111L));
                ModelOutputSeriesDisplayItem conf95 = item.getSeriesForMetaData(repo.getMetaData(222L));
                ModelOutputSeriesDisplayItem base = item.getSeriesForMetaData(repo.getMetaData(321L));

                conf5.setAssociatedMetaData(base.getMetaData());
                conf5.setSeriesType(ModelOutputSeriesType.CONF_INTERVAL_5);

                conf95.setAssociatedMetaData(base.getMetaData());
                conf95.setSeriesType(ModelOutputSeriesType.CONF_INTERVAL_95);

            }
        }


        //set the scenario for easy access to underlying variables
        display.setScenario(repo.getScenario(888L));


        //and that should be it!
                        



    }
    */
    public static List<Long> getSimulationPositionsIds(Simulation sim) throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        for (ModelPosition position: ModelPositionLocalServiceUtil.getModelPositionsByModelId(sim.getId())) {
            ret.add(position.getPositionId());
        }
        return ret;
    }    
    
    public static void setSimulationPositionsIds(Simulation sim, List<Long> positionsIds) throws SystemException {
        ModelPositionLocalServiceUtil.setModelPositions(sim.getId(), positionsIds);
    }

}
