package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.events.PlanCreatedEvent;
import org.climatecollaboratorium.plans.events.PlanDeletedEvent;
import org.climatecollaboratorium.plans.events.PlanUpdatedEvent;
import org.climatecollaboratorium.plans.utils.PagedListDataModel;
import org.climatecollaboratorium.plans.wrappers.ContestPhaseWrapper;
import org.climatecollaboratorium.plans.wrappers.PlanIndexItemWrapper;

import com.ext.portlet.NoSuchPlanVoteException;
import com.ext.portlet.contests.ContestPhaseHelper;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanVote;
import com.ext.portlet.model.PlansUserSettings;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.icesoft.faces.component.datapaginator.DataPaginator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class PlansIndexBean {

    private List<Columns> columns;

    private String sortColumn = "SUPPORTERS";
    private Columns defaultSortColumn;
    private boolean sortAscending = false;
    private boolean updatePlansList = true;
    private boolean defaultSort = true;
    private int pageSize = 50;
    private static int MAX_POPULAR_RESULTS = 10;
    // Current items in ui
    private List uiCustomerBeans = new ArrayList(pageSize);

    private PagedListDataModel plansDataModel;
    


    private ContestPhaseWrapper contestPhase;
    private List<ContestPhase> queryPhases = new ArrayList<ContestPhase>();

    private ConfigureColumnsBean columnsConfiguration;

    private PlansUserSettings plansUserSettings;

    private Long userVote;

    private boolean findUserVote;
    private boolean updateErrorNotes;

    private List<PlanItem> notFilteredPlans = new ArrayList<PlanItem>();
    private List<PlanIndexItemWrapper> plans = new ArrayList<PlanIndexItemWrapper>();
    private List<PlanIndexItemWrapper> plansWithRibbons = new ArrayList<PlanIndexItemWrapper>();
    private List<PlanIndexItemWrapper> plansFeatured = new ArrayList<PlanIndexItemWrapper>();
    private List<PlanIndexItemWrapper> plansWithProperName = new ArrayList<PlanIndexItemWrapper>();
    private List<PlanIndexItemWrapper> plansUntitled = new ArrayList<PlanIndexItemWrapper>();

    private DataPaginator dataPaginator;
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();

    private PlanTypeIndexBean contestIndexBean;

    private List<TestTab> testTabs = Arrays.asList(new TestTab("one"), new TestTab("two"), new TestTab("three"));

    private int tabindex = 0;

    private static final String CONTEST_PHASE_SESSION_PARAM = "CONTEST_PHASE_SESSION_PARAM";
    private final static String PLANS_SOURCE = "plans";
    private final static String VIEW_PROPOSALS_PARAM = "viewProposals";
    private final static String ACTIVE_PROPOSALS = "active";
    private final static String PREVIOUS_PROPOSALS = "previous";

    private final static String SHOW_OPEN_PARAM = "showOpen";
    private final static String SHOW_NEEDING_ASSISTANCE_PARAM = "showNeedingAssistance";

    private static Log _log = LogFactoryUtil.getLog(PlansIndexBean.class);
    private boolean showPreviousProposals = false;
    private int plansUpdateToken = 0;

//    private boolean showProposalsThatUserOwns;
//
//    private boolean showProposalsThatUserSupports;
//
//    private boolean showProposalsThatAreOpen;
//
//    private boolean showProposalsThatNeedSupporters;

    private enum Mode {
        PROPOSALS_ALL("All proposals"), PROPOSALS_USER_OWNS("My proposals",true), PROPSALS_USER_SUPPORTS("Proposals I support",true),
        PROPOSALS_OPEN("Open proposals"), PROPOSALS_NEED_ASSISTANCE("Proposals seeking help");


        private String userString;
        private SelectItem item;
        private boolean requiresLogin;

        Mode(String userString) {
            this(userString,false);
        }

        Mode(String userString, boolean requiresLogin) {
            this.userString = userString;
            this.requiresLogin = requiresLogin;
        }

        public SelectItem getSelectItem() {
            if (item == null) {
                item = new SelectItem(name(), userString);
            }
            return item;
        }

    }

    private List<SelectItem> availableModes = null;

    private Mode current_mode = Mode.PROPOSALS_ALL;

    public PlansIndexBean(ContestPhaseWrapper contestPhaseWrapper) throws PortalException, SystemException {
        this.contestPhase = contestPhaseWrapper;
        /*
        if (contestPhase.getStatus() == ContestStatus.VOTING) {
            defaultSortColumn = Columns.VOTES;
        }
        else {
            defaultSortColumn = Columns.SUPPORTERS;
        }*/
        defaultSortColumn = ContestPhaseHelper.getDefaultSortPhaseColumn(contestPhase.getPhase());
        sortColumn = defaultSortColumn.name();
        //dataPaginator = new DataPaginator();

        updatePlansList = true;
        columnsConfiguration = null;
        initSortColun();
        columnsUpdate();
        
        queryPhases.add(contestPhase.getPhase());
        /*if (contestPhaseWrapper != null) {
            getPlans();
        }*/
        
    }


    public void init(ContestPhaseWrapper currentPhase, NavigationEvent event) throws PortalException, SystemException {
        boolean isDirty = false;
        
        

        if (currentPhase == null) {
            contestPhase = null;
            queryPhases.clear();
            return;
        }

        if (contestPhase != null && contestPhase.getPhaseId().equals(currentPhase.getPhaseId())) {
            // ignore
        } else {
            contestPhase = currentPhase;
            queryPhases.add(contestPhase.getPhase());
            isDirty = true;
            defaultSortColumn = ContestPhaseHelper.getDefaultSortPhaseColumn(contestPhase.getPhase());
        }

        Map<String, String> params = event.getParameters(PLANS_SOURCE);
        if (params != null && params.containsKey(VIEW_PROPOSALS_PARAM)) {
            boolean tmp = params.get(VIEW_PROPOSALS_PARAM).equals(PREVIOUS_PROPOSALS);
            if (tmp != showPreviousProposals) {
                isDirty = true;
                showPreviousProposals = tmp;

                if (showPreviousProposals) {
                    queryPhases.clear();
                    queryPhases.addAll(ContestPhaseLocalServiceUtil.getPreviousPhases(contestPhase.getPhase()));
                }
            }
        } else {
            showPreviousProposals = false;
        }

        if (params != null) {
            if (params.containsKey(SHOW_NEEDING_ASSISTANCE_PARAM)) {
                if (getCurrentMode() != Mode.PROPOSALS_NEED_ASSISTANCE) {
                    setCurrentMode(Mode.PROPOSALS_NEED_ASSISTANCE);
                    isDirty = true;
                }
            } else if (params.containsKey(SHOW_OPEN_PARAM)) {
                if (getCurrentMode() != Mode.PROPOSALS_OPEN) {
                    setCurrentMode(Mode.PROPOSALS_OPEN);
                    isDirty = true;
                }
            }


        }
        initSortColun();

        if (isDirty) {
            refresh();
            sortColumn = defaultSortColumn.name();
            sortAscending = false;
        }

    }
    private void initSortColun() {
        sortColumn = defaultSortColumn.name();
        // if contest isn't active then we should sort by votes
        /*
        if (!contestPhase.getContest().isContestActive() || contestPhase.getStatus() == ContestStatus.VOTING) {
            sortColumn = Columns.VOTES.name();
        }
        else {
            sortColumn = Columns.SUPPORTERS.name();
        }*/
        sortAscending = false;
    }

    public ContestPhaseWrapper getContestPhase() {
        return contestPhase;
    }

    public List<ContestPhaseWrapper> getContestPhases() {
        List<ContestPhaseWrapper> result = contestPhase == null ? Collections.<ContestPhaseWrapper>emptyList() : contestPhase.getContest().getPhases();
        return result;
    }

    public int getContestPhaseIndex() {
        int result = contestPhase == null ? -1 : contestPhase.getContest().getPhases().indexOf(contestPhase);
        return result;
    }


    /*public void setContestPhaseIndex(int i) throws SystemException, PortalException {
        if (contestPhase!=null) init(contestPhase.getContest().getPhases().get(i).getPhaseId(), Collections.<String, String>emptyMap());
    }
    */

    public void setEventBus(EventBus eventBus) {
        if (this.eventBus != eventBus) {
            this.eventBus = eventBus;
            bindEvents();
        }
    }

    public List<TestTab> getTabs() {
        return testTabs;
    }

    public int getTabIndex() {
        return tabindex;
    }

    public void setTabIndex(int idx) {
        this.tabindex = idx;
    }


    private void bindEvents() {
        for (HandlerRegistration registration : handlerRegistrations) {
            registration.unregister();
        }

        handlerRegistrations.clear();

        handlerRegistrations.add(eventBus.registerHandler(PlanDeletedEvent.class, new EventHandler<PlanDeletedEvent>() {

            @Override
            public void onEvent(PlanDeletedEvent event) {
                try {
                    PlansIndexBean.this.refresh();
                } catch (Exception e) {
                    // ignore
                }
            }

        }));
        

        handlerRegistrations.add(eventBus.registerHandler(PlanUpdatedEvent.class, new EventHandler<PlanUpdatedEvent>() {

            @Override
            public void onEvent(PlanUpdatedEvent event) {
                try {
                    PlansIndexBean.this.refresh();
                } catch (Exception e) {
                    // ignore
                }
            }

        }));
        
        handlerRegistrations.add(eventBus.registerHandler(PlanCreatedEvent.class, new EventHandler<PlanCreatedEvent>() {

            @Override
            public void onEvent(PlanCreatedEvent event) {
                try {
                    PlansIndexBean.this.refresh();
                } catch (Exception e) {
                    // ignore
                }
            }

        }));
    }
    
    public List<PlanIndexItemWrapper> getPlansMultiContestPage() throws SystemException, PortalException {
        if (contestPhase.getContest().isContestActive()) {
            return getPlans();
        }
        return null;
    }

    public List<PlanIndexItemWrapper> getPlans() throws SystemException, PortalException {
        if (contestPhase == null) {
            return null;
        }
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        if (updatePlansList) {
            plansUpdateToken++;
            
            plans.clear();
            plansWithRibbons.clear();
            plansFeatured.clear();
            plansWithProperName.clear();
            plansUntitled.clear();
            
            updatePlansList = false;
            Columns sortCol = Columns.valueOf(sortColumn);

            
            String sortAttribute = Attribute.LAST_MOD_DATE.name();
            // if contest isn't active then we should sort by votes
            /*if (!contestPhase.getContest().isContestActive()) {
                sortAttribute = Attribute.VOTES.name();
            }*/
            if (sortCol.isSortable()) {
                sortAttribute = sortCol.getSortAttribute().name();
            }
            if (showPreviousProposals) {
                notFilteredPlans = PlanItemLocalServiceUtil.getPlans(ectx.getSessionMap(), ectx.getRequestMap(), null, 
                        queryPhases, 0, 1000, sortAttribute, sortAscending ? "ASC" : "DESC", false);
            } else {
                notFilteredPlans = PlanItemLocalServiceUtil.getPlans(ectx.getSessionMap(), ectx.getRequestMap(), 0L, contestPhase.getPhase().getContestPhasePK(), 0, 1000, sortAttribute, sortAscending ? "ASC" : "DESC", false);                
            }
            
            Attribute sortAttr = Attribute.valueOf(sortAttribute);

            List<PlanItem> popularPlans = Collections.emptyList();
            List<PlanIndexItemWrapper> untitledPlans = new ArrayList<PlanIndexItemWrapper>();
            
            boolean hasScrapBook = false;


            final Long userId = Helper.isUserLoggedIn() ? Helper.getLiferayUser().getUserId() : -1;
            for (Iterator<PlanItem> i = notFilteredPlans.iterator();i.hasNext();) {
                PlanItem plan = i.next();
                if (plan.getVersion() < 2) {
                    i.remove();
                    continue;
                }
                PlanIndexItemWrapper piiw = new PlanIndexItemWrapper(plan, this);
                
                if (PlanItemLocalServiceUtil.getRibbon(plan) != null && PlanItemLocalServiceUtil.getRibbon(plan) > 0) {
                    plansWithRibbons.add(piiw);
                }
                else if (PlanItemLocalServiceUtil.getTags(plan) != null) {
                    plansFeatured.add(piiw);
                }
                else if (PlanItemLocalServiceUtil.getName(plan).startsWith("Untitled")) {
                    plansUntitled.add(piiw);
                }
                else {
                    plansWithProperName.add(piiw);
                }
                plans.add(piiw);
            }
            
            if (defaultSort) {
                // update sorting for sections
                Collections.sort(plansWithRibbons, new Comparator<PlanIndexItemWrapper>() {

                    @Override
                    public int compare(PlanIndexItemWrapper o1, PlanIndexItemWrapper o2) {
                        try {
                            return o1.getRibbon() - o2.getRibbon();
                        } catch (SystemException e) {
                            // ignore
                        }
                        return 0;
                    }
                
                });
            
                // update sorting for sections
                Collections.sort(plansFeatured, new Comparator<PlanIndexItemWrapper>() {

                    @Override
                    public int compare(PlanIndexItemWrapper o1, PlanIndexItemWrapper o2) {
                        try {
                            return o1.getTagsOrder() - o2.getTagsOrder();
                        } catch (SystemException e) {
                            // ignore
                        }
                        return 0;
                    }
                
                });
            }
            
            
            
            
            updateErrorNotes = true;
        }
        return plans;
    }

    public List<Columns> getColumns() {
        return columns;
    }

    public DataPaginator getDataPaginator() {
        return dataPaginator;
    }

    public void setDataPaginator(DataPaginator dataPaginator) {
        this.dataPaginator = dataPaginator;
    }


    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
        updatePlansList = true;
    }
    
    public void updateSortColumn(ActionEvent e) {
        defaultSort = false;
        String newSortColumn = (String) e.getComponent().getAttributes().get("sortColumn"); 
        //this.sortColumn =
        if (newSortColumn.equals(sortColumn)) {
            sortAscending = !sortAscending;
        }
        else {
            sortAscending = true;
        }
        sortColumn = newSortColumn;
        updatePlansList = true;
        
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
        updatePlansList = true;
    }


    public void filtersUpdate() {
        updatePlansList = true;
    }


    public ConfigureColumnsBean getColumnsConfiguration() throws SystemException, PortalException {
        if (columnsConfiguration == null) {
            columnsConfiguration = new ConfigureColumnsBean(this);
        }
        return columnsConfiguration;
    }


    private void columnsUpdate() throws PortalException, SystemException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        //plansUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), contestPhase.getPlanType());
        columns = new ArrayList<Columns>();
        for (Columns col : ContestPhaseHelper.getPhaseColumns(contestPhase.getPhase())) {
            columns.add(col);
        }
        updatePlansList = true;
    }


    public void refresh() throws PortalException, SystemException {
        updatePlansList = true;
        columnsConfiguration = null;
        columnsUpdate();
        getPlans();
    }

    public int getVotesCount() throws SystemException, PortalException {
        return PlanVoteLocalServiceUtil.countPlanVotes(contestPhase.getPlanType());
    }

    public int getPlansCount() throws SystemException, PortalException {
        return getPlans().size();
    }

    public void findUserVote(ActionEvent e) throws PortalException, SystemException {
        findUserVote = true;
        refresh();
    }

    public boolean isFindUserVote() {
        if (findUserVote) {
            findUserVote = false;
            return true;
        }
        return false;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean getHasUserVoted() throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            try {
                PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(Helper.getLiferayUser().getUserId(), contestPhase.getContest().getContestId());
                return vote != null;
            }
            catch (NoSuchPlanVoteException e) {
            }
        }
        return false;
    }

    public boolean isUpdateErrorNotes() {
        if (updateErrorNotes) {
            updateErrorNotes = false;
            return true;
        }
        return updateErrorNotes;
    }

    public static class TestTab {

        String label;

        public TestTab(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public void clear() {
        contestPhase = null;
    }

    public void setSelectedMode(String name) throws SystemException, PortalException {
        setCurrentMode(Mode.valueOf(name));
    }

    public String getSelectedMode() {
        return current_mode.name();
    }

    public Mode getCurrentMode() {
        return current_mode;
    }

    public void setCurrentMode(Mode mode) throws SystemException, PortalException {
        if (mode!=current_mode) {
            current_mode = mode;
            refresh();
        }

    }

    public List<SelectItem> getAvailableModes() {
        if (availableModes == null) {
            availableModes = new ArrayList<SelectItem>();
            for (Mode m:Mode.values()) {
                if (m.requiresLogin && !Helper.isUserLoggedIn()) continue;
                availableModes.add(m.getSelectItem());
            }
        }
        return availableModes;
    }

    public boolean isShowPreviousProposals() {
        return showPreviousProposals;
    }

//     public boolean isShowProposalsThatUserOwns() {
//         return showProposalsThatUserOwns;
//     }
//
//     public boolean isShowProposalsThatUserSupports() {
//         return showProposalsThatUserSupports;
//     }
//
//     public void setShowProposalsThatUserOwns(boolean show) throws PortalException, SystemException {
//         showProposalsThatUserOwns = show;
//     }
//
//     public void setShowProposalsThatUserSupports(boolean show) throws PortalException, SystemException {
//         showProposalsThatUserSupports = show;
//     }
//
//     public void setShowProposalsThatNeedSupporters(boolean show) throws PortalException, SystemException {
//         showProposalsThatNeedSupporters = show;
//     }
//
//     public void setShowProposalsThatAreOpen(boolean show) throws PortalException, SystemException {
//         showProposalsThatAreOpen = show;
//         refresh();
//     }
//
//     public boolean isShowProposalsThatAreOpen() {
//         return showProposalsThatAreOpen;
//     }
//
//     public boolean isShowProposalsThatNeedSupporters() {
//         return showProposalsThatNeedSupporters;
//     }
    
    public Columns getNameColumn() {
        return PlanConstants.Columns.NAME;
    }
    
    public int getPlansUpdateToken() {
        return plansUpdateToken;
    }


    public List<PlanIndexItemWrapper> getPlansWithRibbons() throws SystemException, PortalException {
        getPlans();
        return plansWithRibbons;
    }


    public List<PlanIndexItemWrapper> getPlansFeatured() throws SystemException, PortalException {
        getPlans();
        return plansFeatured;
    }


    public List<PlanIndexItemWrapper> getPlansWithProperName() throws SystemException, PortalException {
        getPlans();
        return plansWithProperName;
    }


    public List<PlanIndexItemWrapper> getPlansUntitled() throws SystemException, PortalException {
        getPlans();
        return plansUntitled;
    }
}
