package org.climatecollaboratorium.plans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.facelets.simulations.SimulationBean;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.exceptions.BeanInitializationException;

import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.NoSuchContestPhaseException;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;

public class NavigationBean {
    private PlanPageType pageType = PlanPageType.getDefaultPageType();
    public final static String DEFERED_PLAN_VOTE_ID_PARAM = "deferrPlanVoteIDSessionParam";
    private PlanBean planBean;
    private PlansIndexBean plansIndexBean;
    private ContestBean contestBean;
    private PlansPermissionsBean plansPermissionsBean;
    private IssuesBean issuesBean;
    private CreatePlanBean createPlanBean;
    private ContestsBean contestsBean;
    private SimulationBean externalSimulationBean;
    
    private EventBus eventBus;
    
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    
    private static Log _log = LogFactoryUtil.getLog(NavigationBean.class);
    
    public NavigationBean() throws SystemException, PortalException, BeanInitializationException, NumberFormatException, IOException {

        Map<String, String> params = Helper.getUrlParametersMap();
        try {
            plansPermissionsBean = new PlansPermissionsBean();
            contestBean = new ContestBean(params);
            plansIndexBean = contestBean.getPlansIndex();
            
            planBean = new PlanBean(params, plansPermissionsBean);
            issuesBean = new IssuesBean();
            createPlanBean = new CreatePlanBean(plansIndexBean);
            
            contestsBean = new ContestsBean(params);
            
            
            planBean.setPermissions(plansPermissionsBean);
            planBean.setPlansIndexBean(plansIndexBean);
        }
        catch (BeanInitializationException e) {
            _log.error("Exception thrown when initializing plans beans", e);
            throw e;
        }
        pageType = PlanPageType.getPageTypeForParams(params);
        
        updatePageTitle();
        
        
    }
    public void setDeferPlanIdVote(Long planId) {
        if (planId < 0 || planId == null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(DEFERED_PLAN_VOTE_ID_PARAM);
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(DEFERED_PLAN_VOTE_ID_PARAM, planId);
        }
            
    }
    
    public void deferVotingTo(ActionEvent e) {
    }
    
    public boolean getHasUserVoted() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(DEFERED_PLAN_VOTE_ID_PARAM) != null;
    }
    
    public Long getDeferPlanIdVote() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(DEFERED_PLAN_VOTE_ID_PARAM);
    }
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        planBean.setEventBus(eventBus);
        plansIndexBean.setEventBus(eventBus);
        contestsBean.setEventBus(eventBus);
        createPlanBean.setEventBus(eventBus);
        bindEvents();
    }

    public EventBus getEventBus() {
        return eventBus;
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
                pageType = PlanPageType.getPageTypeForNavEvent(event, pageType);
                // init all beans and update plan type
                try {
                    switch (pageType) {
                        case PLAN_DETAILS:
                            //vent.setResultName("contest");
                            planBean.init(event);
                            break;
                        case CONTEST_PROPOSALS:
                        case CONTEST_MODEL:
                            contestBean.init(event);
                            plansIndexBean.init(contestBean.getCurrentPhase(), event);
                            break;
                        case CONTEST_ISSUES:
                            contestBean.init(event);
                            plansIndexBean.init(contestBean.getCurrentPhase(), event);
                            break;
                    }
                    contestsBean.init(event);
                    issuesBean.init(event);
                    createPlanBean.init(event);
                    updatePageTitle();
                } catch (NoSuchContestPhaseException e) {
                    _log.error("Can't init plan related beans", e);
                } catch (PortalException e) {
                    _log.error("Can't init plan related beans", e);
                } catch (SystemException e) {
                    _log.error("Can't init plan related beans", e);
                } catch (IllegalUIConfigurationException e) {
                    _log.error("Can't init plan related beans", e);
                } catch (IOException e) {
                    _log.error("Can't init plan related beans", e);
                }
                
            }


        }));
    }
    

    private void updatePageTitle() {
        String pageTitle = pageType.getPageTitle(this);
        if (pageTitle == null) {
            pageTitle = "Climatecolab Proposals";
        }
        try {
            HttpServletRequest request = Helper.getRequest();
            if (request != null) {
                PortalUtil.setPageTitle(pageTitle, Helper.getRequest());
            }
        }
        catch (Throwable e) {
            // ignore as icefaces throws NPE on some phases because requests haven't been populated  
        }
        
    }
    
    public PlansIndexBean getPlansIndexBean() {
        return plansIndexBean;
    }

    public PlanBean getPlanBean() {
        return planBean;
    }

    public CreatePlanBean getCreatePlanBean() {
        return createPlanBean;
    }

    public PlanPageType getPageType() {
        return pageType;
    }

    public ContestBean getContestBean() {
        return contestBean;
    }

    public PlansPermissionsBean getPlansPermissionsBean() {
        return plansPermissionsBean;
    }

    public IssuesBean getIssuesBean() {
        return issuesBean;
    }

    public ContestsBean getContestsBean() {
        return contestsBean;
    }

    public void setExternalSimulationBean(SimulationBean externalSimulationBean) {
        this.externalSimulationBean = externalSimulationBean;
        planBean.setExternalSimulationBean(externalSimulationBean);
    }

    public SimulationBean getExternalSimulationBean() {
        return externalSimulationBean;
    }
    
    public Date getForceRerender() {
        return new Date();
    }
}
