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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;

import org.xcolab.portlets.models.suggest.ModelDisplayWrapper;
import org.xcolab.portlets.models.suggest.ModelInputDisplayItemWrapper;
import org.xcolab.portlets.models.suggest.ModelInputGroupDisplayItemWrapper;
import org.xcolab.portlets.models.suggest.ModelOutputErrorSettingWrapper;
import org.xcolab.portlets.models.suggest.SupportBean;

import ys.wikiparser.WikiParser;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItemType;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItemType;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelOutputSeriesDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.wiki.NoSuchPageResourceException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.TupleStatus;
import edu.mit.cci.roma.client.comm.ClientRepository;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;

public class SimulationDetailsBean {

    private Long modelId;
    private Simulation simulation;
    private int selectedTab;
    private String description;
    private ModelDisplayWrapper display;
    private ModelInputGroupDisplayItemWrapper newGroupWrapper;
    private List<ModelOutputErrorSettingWrapper> outputErrorSettingWrappers = new ArrayList<ModelOutputErrorSettingWrapper>();
    private Map<ModelInputDisplayItem, ModelInputDisplayItemWrapper> wrappedInputs = new HashMap<ModelInputDisplayItem, ModelInputDisplayItemWrapper>();

    private final static Map<String, Integer> tabNameNumberMap = new HashMap<String, Integer>();
    static {
        tabNameNumberMap.put("description", 0);
        tabNameNumberMap.put("expertEval", 1);
        tabNameNumberMap.put("positions", 2);
        tabNameNumberMap.put("actionsImpacts", 3);
    }

    private final static Map<Integer, Boolean> tabEditEnabled = new HashMap<Integer, Boolean>();

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) throws SystemException, IllegalUIConfigurationException, IOException {
        this.modelId = modelId;
        ClientRepository repo = CollaboratoriumModelingService.repository();
        simulation = repo.getSimulation(modelId);
        description = simulation.getDescription();
        selectedTab = 0;
        display = new ModelDisplayWrapper(ModelUIFactory.getInstance().getDisplay(simulation), this,
                new HashMap<Long, Object>());
        newGroupWrapper = new ModelInputGroupDisplayItemWrapper(this);

        wrappedInputs.clear();
        for (ModelInputDisplayItemWrapper item : display.getWrappedInputs()) {
            wrappedInputs.put(item.getWrapped(), item);
        }

        outputErrorSettingWrappers.clear();
        for (ModelOutputDisplayItem item : getAllOutputsFromDisplay()) {
            if (item instanceof ModelOutputSeriesDisplayItem || item instanceof ModelOutputIndexedDisplayItem) {
                for (TupleStatus status : TupleStatus.values()) {
                    if (status.getCode() != null) {
                        outputErrorSettingWrappers.add(new ModelOutputErrorSettingWrapper(item, status, this));
                    }
                }
            }
        }

    }

    public void navigate(Map<String, String> parameters) {
        String tabName = parameters.get("tab");
        if (tabName != null) {
            if (tabNameNumberMap.containsKey(tabName)) {
                selectedTab = tabNameNumberMap.get(tabName);
            }
        }
    }

    public String getExpertEvaluation() throws PortalException, SystemException {
        Long wikiPageId = ModelUIFactory.getSimulationExpertEvaluationPageId(simulation);
        if (wikiPageId == null || wikiPageId <= 0) {
            return "";
        }
        try {
            WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(wikiPageId);

            // replace internal links with external links
            String content = wikiPage.getContent();
            Pattern pattern = Pattern.compile("\\[\\[(http)?([^\\]^|]*)\\|?([^\\]]*)\\]\\]");
            Matcher matcher = pattern.matcher(content);
            int lastEnd = 0;

            StringBuilder sb = new StringBuilder();
            PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String wikiPageUrlPrefix = String.format("http://%s:%d/web/guest/resources/-/wiki/Main/", request
                    .getServerName(), request.getServerPort());

            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    // external link... ignore
                    continue;
                }
                String name = matcher.group(3) != null && matcher.group(3).length() > 0 ? matcher.group(3) : matcher
                        .group(2);
                sb.append(content.substring(lastEnd, matcher.start()));
                sb.append("[[");
                sb.append(wikiPageUrlPrefix);
                sb.append(matcher.group(2).replaceAll("\\s", "+"));
                sb.append("|");
                sb.append(name);
                sb.append("]]");

                lastEnd = matcher.end();
            }
            sb.append(content.substring(lastEnd));

            return WikiParser.renderXHTML(sb.toString());
        }
        catch (NoSuchPageResourceException e) {
            // ignore
        }
        return "";
    }

    public Long getExpertEvaluationPageId() throws SystemException {
        return ModelUIFactory.getSimulationExpertEvaluationPageId(simulation);
    }

    public void setExpertEvaluationPageId(Long pageId) throws SystemException {
        ModelUIFactory.setSimulationExpertEvaluationPageId(simulation, pageId);
    }

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }

    public int getSelectedTab() {
        return selectedTab;
    }

    public void toggleEdit(ActionEvent e) {
        if (tabEditEnabled.containsKey(selectedTab)) {
            tabEditEnabled.remove(selectedTab);
        } else {
            tabEditEnabled.put(selectedTab, true);
        }
    }

    public boolean isTabEditing() {
        return tabEditEnabled.containsKey(selectedTab);
    }

    public void updateSimulation(ActionEvent event) throws IOException, ModelNotFoundException, SystemException, PortalException {
        simulation.setDescription(description);

        // FIXME updating was removed in new version of modeling svc
        // SimulationsHelper.getInstance().getRepository().updateSimulation(simulation);
        toggleEdit(event);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ModelOutputDisplayItem> getAllOutputsFromDisplay() {
        List<ModelOutputDisplayItem> outputs = new ArrayList<ModelOutputDisplayItem>();
        for (ModelOutputDisplayItem output : display.getOutputs()) {
            outputs.add(output);
            if (output.getDisplayItemType() == ModelOutputDisplayItemType.INDEXED) {
                for (ModelOutputDisplayItem serie : ((ModelOutputIndexedDisplayItem) output).getSeries()) {
                    outputs.add(serie);
                }
            }
        }
        return outputs;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public ModelDisplayWrapper getDisplay() {
        return display;
    }

    public List<SelectItem> getModelInputsOptions() {
        return SupportBean.getModelInputsOptions(display.getWrapped());
    }

    public ModelInputGroupDisplayItemWrapper getNewGroupWrapper() {
        return newGroupWrapper;
    }

    public List<ModelInputDisplayItem> getIndividualInputsFromDisplay() {
        List<ModelInputDisplayItem> inputs = new ArrayList<ModelInputDisplayItem>();
        for (ModelInputDisplayItem input : display.getOryginalInputs()) {
            if (input.getDisplayItemType() == ModelInputDisplayItemType.INDIVIDUAL) {
                inputs.add(input);
            } else {
                for (ModelInputDisplayItem groupedInput : ((ModelInputGroupDisplayItem) input).getDisplayItems()) {
                    inputs.add(groupedInput);
                }
            }
        }
        return inputs;
    }

    public Map<ModelOutputDisplayItem, List<SelectItem>> getOutputAssociationOptions() {
        Map<ModelOutputDisplayItem, List<SelectItem>> itemsMap = new HashMap<ModelOutputDisplayItem, List<SelectItem>>();
        for (ModelOutputDisplayItem output : display.getOutputs()) {
            if (output.getDisplayItemType() == ModelOutputDisplayItemType.INDEXED) {
                List<SelectItem> options = new ArrayList<SelectItem>();
                for (ModelOutputDisplayItem serie : ((ModelOutputIndexedDisplayItem) output).getSeries()) {
                    itemsMap.put(serie, options);
                    options.add(new SelectItem(((ModelOutputSeriesDisplayItem) serie).getMetaData().getId(), serie
                            .getName()));
                }
            }
        }
        return itemsMap;
    }

    public List<ModelOutputErrorSettingWrapper> getOutputErrorSettingWrappers() {
        return outputErrorSettingWrappers;
    }

    public Map<ModelInputDisplayItem, ModelInputDisplayItemWrapper> getWrappedInputs() {
        return wrappedInputs;
    }
    
    public void updateInputs(ActionEvent e) throws SystemException, IllegalUIConfigurationException, IOException {
        refresh();
        FacesContext fc = FacesContext.getCurrentInstance();
        
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Request processed");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fc.addMessage(null, fm);
    }
    
    public void refresh() throws SystemException, IllegalUIConfigurationException, IOException {
        ClientRepository repo = CollaboratoriumModelingService.repository();
        simulation = repo.getSimulation(modelId);
        description = simulation.getDescription();
        
        display = new ModelDisplayWrapper(ModelUIFactory.getInstance().getDisplay(simulation), this,
                new HashMap<Long, Object>());
        newGroupWrapper = new ModelInputGroupDisplayItemWrapper(this);

        wrappedInputs.clear();
        for (ModelInputDisplayItemWrapper item : display.getWrappedInputs()) {
            wrappedInputs.put(item.getWrapped(), item);
        }

        outputErrorSettingWrappers.clear();
        for (ModelOutputDisplayItem item : getAllOutputsFromDisplay()) {
            if (item instanceof ModelOutputSeriesDisplayItem || item instanceof ModelOutputIndexedDisplayItem) {
                for (TupleStatus status : TupleStatus.values()) {
                    if (status.getCode() != null) {
                        outputErrorSettingWrappers.add(new ModelOutputErrorSettingWrapper(item, status, this));
                    }
                }
            }
        }
    }

}