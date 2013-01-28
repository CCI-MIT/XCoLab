package org.xcolab.portlets.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.xcolab.portlets.models.suggest.SimulationsHelper;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import edu.mit.cci.simulation.client.Scenario;
import edu.mit.cci.simulation.client.Simulation;

public class NavigationBean {
    private final static String INDEX_PAGE = "index";
    private final static String DETAILS_PAGE = "details";

    private String currentPage = INDEX_PAGE;

    private BreadcrumbBean breadcrumbBean;
    private Simulation selectedSimulation;
    private Scenario selectedScenario;
    private String page = null;
    private String viewType = "default";

    private long modelId;
    private long scenarioId;
    private Long planId;
    private boolean embeddedCanEdit;
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private static Log _log = LogFactoryUtil.getLog(NavigationBean.class);
    private SimulationDetailsBean simulationDetailsBean = new SimulationDetailsBean();
    
    public NavigationBean() {
    }

    public BreadcrumbBean getBreadcrumbBean() {
        return breadcrumbBean;
    }

    public void setBreadcrumbBean(BreadcrumbBean breadcrumbBean){
        this.breadcrumbBean = breadcrumbBean;
        updateBreadcrumb();
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public void update(ActionEvent event) throws IOException, SystemException, PortalException {
        useModel();
    }
    
    private void useModel() throws IOException, SystemException, PortalException {
        selectedScenario = SimulationsHelper.getInstance().getScenarioById(scenarioId);
        selectedSimulation = SimulationsHelper.getInstance().getSimulationById(modelId);
        
        if (selectedScenario != null) {
            //simulationBean.setScenario(selectedScenario);
            currentPage = DETAILS_PAGE;
        }
        else if (selectedSimulation != null) {
            //simulationBean.setSimulation(selectedSimulation);
            currentPage = DETAILS_PAGE;
        }
        else {
            //simulationBean.setSimulation(null);
            currentPage = INDEX_PAGE;
        }
        updateBreadcrumb();
        
    }

    private void updateBreadcrumb() {
        breadcrumbBean.clear();
        breadcrumbBean.addItem("Model index", "#models=");

        if (selectedSimulation != null) {
            breadcrumbBean.addItem(selectedSimulation.getName(), null);
        }
    }

    public Simulation getSelectedSimulation() {
        return selectedSimulation;
    }
    
    public String getViewType() {
        return viewType;
    }
    
    public String getPage() {
        return page;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public boolean isEmbeddedCanEdit() {
        return embeddedCanEdit;
    }
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bindEvents();
    }

    private void bindEvents() {
        // unregister old handlers
        for (HandlerRegistration registration: handlerRegistrations) {
            registration.unregister();
        }
        handlerRegistrations.clear();

        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                // check if event
                final String modelsSource = "models";
                if (!event.hasSource(modelsSource)) {
                    currentPage = INDEX_PAGE;
                    breadcrumbBean.clear();
                    selectedSimulation = null;
                    updateBreadcrumb();
                }
                else {
                    Map<String, String> parameters = event.getParameters(modelsSource);
                    if (parameters.containsKey("modelId")) {
                        try {
                            Long modelId = Long.parseLong(parameters.get("modelId"));
                            selectedSimulation = CollaboratoriumModelingService.repository().getSimulation(modelId);
                            if (selectedSimulation != null) {
                                currentPage = DETAILS_PAGE;
                                updateBreadcrumb();

                                simulationDetailsBean.setModelId(modelId);
                                simulationDetailsBean.navigate(parameters);
                                
                            }
                        }
                        catch (NumberFormatException e) {
                            _log.error("Invalid modelId " + parameters.get("modelId"));
                            currentPage = INDEX_PAGE;
                            selectedSimulation = null;
                        } catch (SystemException e) {
                            _log.error("Invalid model " + parameters.get("modelId"));
                        } catch (IllegalUIConfigurationException e) {
                            _log.error("Invalid model " + parameters.get("modelId"));
                        } catch (IOException e) {
                            _log.error("Invalid model " + parameters.get("modelId"), e);
                        }
                        
                    }
                    else {
                        currentPage = INDEX_PAGE;
                        breadcrumbBean.clear();
                        selectedSimulation = null;
                        updateBreadcrumb();
                    }
                }

            }

        }));
    }
    
    public SimulationDetailsBean getSimulationDetailsBean() {
        return simulationDetailsBean;
    }

}