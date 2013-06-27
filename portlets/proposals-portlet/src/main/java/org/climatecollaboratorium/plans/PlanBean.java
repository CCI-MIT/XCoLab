package org.climatecollaboratorium.plans;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.events.PlanDeletedEvent;
import org.climatecollaboratorium.plans.events.PlanUpdatedEvent;
import org.climatecollaboratorium.plans.exceptions.BeanInitializationException;
import org.climatecollaboratorium.plans.wrappers.PlanItemWrapper;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanTypeAttribute;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class PlanBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanItemWrapper plan;
	private PlanItem planItem;
	private SimulationBean simulationBean = new SimulationBean();
	private PlanModelBean modelBean;
	private boolean editingDescription;
	private boolean editingName;
	private boolean editing = false;
	private CreatePlanBean createPlanBean;
	private PlanMembershipBean membershipBean;
	private PlansPermissionsBean permissions;
	private EventBus eventBus;
	private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
	private int selectedTabIndex = 0;
	private Long planId = -1L;
	private DiscussionBean commentsBean = new DiscussionBean();

	private static final Map<String, Integer> tabNameIndexMap = new HashMap<String, Integer>();
	public final static String PLANS_SOURCE = "plans";
	private final static String NEW_PLAN_PARAM = "newPlan";

	private final static String DEFAULT_TAB = "actionsimpacts";
	private boolean planOpenForEditing = false;
	// this is ver bad solution, we should use event bus for communication
	// between beans instead of direct references
	private PlansIndexBean plansIndexBean;
	private transient org.climatecollaboratorium.facelets.simulations.SimulationBean externalSimulationBean;
	private NavigationEvent lastNavEvent;
	private List<PlanTabWrapper> availableTabs;
	private PlanTab currentTab = PlanTab.DESCRIPTION;
	private boolean leaveThisPlan = false;

	static {

		tabNameIndexMap.put("admin", tabNameIndexMap.size());
		tabNameIndexMap.put("description", tabNameIndexMap.size());
		tabNameIndexMap.put("actionsimpacts", tabNameIndexMap.size());
		tabNameIndexMap.put("sections", tabNameIndexMap.size());
		tabNameIndexMap.put("comments", tabNameIndexMap.size());
		tabNameIndexMap.put("team", tabNameIndexMap.size());
		tabNameIndexMap.put("discussion", tabNameIndexMap.size());
	}

	private static Log _log = LogFactoryUtil.getLog(PlanBean.class);

	public PlanBean(Map<String, String> params, PlansPermissionsBean permissions)
			throws SystemException, PortalException,
			BeanInitializationException {

		this.permissions = permissions;

		String planIdStr = params.get("planId");

		if (planIdStr != null && planIdStr.trim().length() > 0) {
			try {
				planId = Long.parseLong(planIdStr);
				refresh();
				setTabForName(params.get("tab"));
				// selectedTabIndex = getDefaultTab();
			} catch (NumberFormatException e) {
				throw new BeanInitializationException("Can't parse plan id", e);
			}
		}

	}
	
	public long getContestPhase() {
		try {
			return PlanItemLocalServiceUtil.getPlanMeta(planItem).getContestPhase();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		return 0L;
	}

	public void init(NavigationEvent event) throws SystemException,
			PortalException, IllegalUIConfigurationException, IOException {
		lastNavEvent = event;
		Map<String, String> parameters = event.getParameters(PLANS_SOURCE);
		editing = false;
		if (parameters == null) {
			selectedTabIndex = getDefaultTab();
			return;
		}

		if (parameters.containsKey("tab")) {
			setTabForName(parameters.get("tab"));
		} else {
			// default tab if no tab is set
			selectedTabIndex = getDefaultTab();
		}

		if (parameters.containsKey(NEW_PLAN_PARAM) && permissions.getCanEdit()) {
			//
			if (PlanItemLocalServiceUtil.getPlanDescriptions(planItem).get(0)
					.getVersion() == 1) {
				editingDescription = true;
				editingName = true;
				editing = true;
			}
			if (PlanItemLocalServiceUtil.getAllPlanModelRuns(planItem).get(0)
					.getVersion() == 0) {
				if (!simulationBean.isEditing()) {
					simulationBean.edit(null);
					externalSimulationBean.editActions(null);
				}
				planOpenForEditing = true;
			}

		} else {
			planOpenForEditing = false;
			editingDescription = false;
			editingName = false;
			if (simulationBean != null && simulationBean.isEditing()) {
				simulationBean.edit(null);
			}
		}
	}

	private void setTabForName(String name) {

		try {
			Integer tmp = tabNameIndexMap.get(name);
			selectedTabIndex = tmp != null ? tmp : getDefaultTab();
			currentTab = PlanTab.valueOf(name.toUpperCase());
			if (currentTab == PlanTab.ADMIN) {
				if (!permissions.getCanAdmin()) {
					currentTab = PlanTab.DESCRIPTION;
				}
			}
		} catch (Exception e) {
			_log.error("Can't parse tab number: " + name);
		}
	}

	public int getDefaultTab() throws SystemException, PortalException {
		if (planItem != null
				&& PlanItemLocalServiceUtil.getAllPlanModelRuns(planItem)
						.get(0).getVersion() == 0
				&& permissions.getCanEdit()
				&& PlanItemLocalServiceUtil.getPlanType(planItem).getModelId() > 0) {
			return tabNameIndexMap.get("actionsimpacts");
		} else
			return tabNameIndexMap.get("description");
	}

	public void clear() {
		planId = -1L;
	}

	public Long getPlanId() {
		return planId;
	}

	public void refreshFull() throws SystemException, PortalException,
			IllegalUIConfigurationException, IOException {
		init(lastNavEvent);
	}

	public void refresh() throws SystemException, PortalException {
		/*
		 * if (simulationBean != null) { simulationBean.cleanup(); }
		 */
		if (planId != null && planId > 0) {

			planItem = PlanItemLocalServiceUtil.getPlan(planId);
			if (planItem == null)
				return;
			modelBean = new PlanModelBean(planItem, this);
			plan = new PlanItemWrapper(planItem, this, permissions);
			plan.setEventBus(eventBus);
			permissions.setPlan(planItem);
			if (simulationBean != null) {
				if (simulationBean.isSaved()) {
					planOpenForEditing = false;
				}

				simulationBean.setPlan(planItem, this);
			}
			// simulationBean = new SimulationBean(planItem, this, eventBus);
			membershipBean = new PlanMembershipBean(planItem, this, permissions);
			editing = false;

		}
		editing = false;
	}

	public PlanItemWrapper getPlan() {
		if (plan == null)
			try {
				refresh();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return plan;
	}

	public void setEditingDescription(boolean editingDescription)
			throws SystemException, PortalException {
		this.editingDescription = editingDescription;
		refresh();
	}

	public boolean isEditingDescription() {
		return editingDescription;
	}

	public void editDescription(ActionEvent e) throws SystemException {
		editingDescription = !editingDescription;
		editingName = !editingName;

		plan.setName(PlanItemLocalServiceUtil.getName(plan.getWrapped()));
		plan.setDescription(PlanItemLocalServiceUtil.getDescription(plan
				.getWrapped()));

		plan.refresh();
	}

	public void setEditingName(boolean editingName) throws SystemException,
			PortalException {
		this.editingName = editingName;
		refresh();
	}

	public boolean isEditingName() {
		return editingName;
	}

	public void editName(ActionEvent e) throws SystemException {
		plan.setName(PlanItemLocalServiceUtil.getName(plan.getWrapped()));
		plan.setDescription(PlanItemLocalServiceUtil.getDescription(plan
				.getWrapped()));
		editingName = !editingName;
	}

	public SimulationBean getSimulationBean() {
		return simulationBean;
	}

	public PlanModelBean getModelBean() {

		return modelBean;
	}

	public int getVotesPercent() throws SystemException, PortalException {
		int votes = plan.getVotes();
		int votesTotal = PlanVoteLocalServiceUtil.getPlanVotesCount();
		if (votes == 0 || votesTotal == 0) {
			return 0;
		}

		return (100 * votes) / votesTotal;
	}

	public CreatePlanBean getCreatePlanBean() throws SystemException {
		if (createPlanBean == null) {
			createPlanBean = new CreatePlanBean(this);
			if (eventBus != null) {
				createPlanBean.setEventBus(eventBus);
			}
		}
		return createPlanBean;
	}

	public PlanMembershipBean getMembershipBean() throws PortalException,
			SystemException {
		if (membershipBean == null) {
			membershipBean = new PlanMembershipBean(plan.getWrapped(), this,
					permissions);
		}
		return membershipBean;
	}

	public void cleanup() {
		simulationBean.cleanup();
	}

	public int getSelectedTab() {
		return selectedTabIndex;
	}

	public void setSelectedTab(int selectedTab) {
		selectedTabIndex = selectedTab;
	}

	public void planDeleted() {
		eventBus.fireEvent(new PlanDeletedEvent(planItem));
	}

	public void uploadFile(ActionEvent evt) {
		_log.debug("Should upload file");
	}

	public void setPermissions(PlansPermissionsBean permissions) {
		this.permissions = permissions;

	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
		simulationBean.setEventBus(eventBus);
		if (plan != null) {
			plan.setEventBus(eventBus);
		}
		if (createPlanBean != null) {
			createPlanBean.setEventBus(eventBus);
		}
		bind();
	}

	public void setPlansIndexBean(PlansIndexBean plansIndexBean) {
		this.plansIndexBean = plansIndexBean;
	}

	public void refreshIndex() throws PortalException, SystemException {
		plansIndexBean.refresh();
	}

	public void modelChanged() {
		plan.modelChanged();

	}

	public boolean isPlanOpenForEditing() {
		return planOpenForEditing;
	}

	public String getRelativeUrl() throws SystemException, PortalException,
			UnsupportedEncodingException {
		String result;
		if (plan != null) {
			result = "http://climatecolab.org/web/guest/plans/-/plans/contestId/"
					+ PlanItemLocalServiceUtil.getContest(plan.getWrapped())
							.getContestPK() + "/planId/" + getPlanId();
		} else {
			result = "http://climatecolab.org/web/guest/plans/-/plans/";
		}
		return URLEncoder.encode(result, "UTF-8");
	}

	private void bind() {
		for (HandlerRegistration reg : handlerRegistrations) {
			reg.unregister();
		}

		handlerRegistrations.clear();

		handlerRegistrations.add(eventBus.registerHandler(
				PlanUpdatedEvent.class, new EventHandler<PlanUpdatedEvent>() {

					@Override
					public void onEvent(PlanUpdatedEvent event) {
						// refresh plan item wrapper
						try {
							refresh();
						} catch (SystemException e) {
							_log.error(
									"Can't refresh plan item wrapper after plan update: "
											+ event.getPlan().getPlanId(), e);
						} catch (PortalException e) {
							_log.error(
									"Can't refresh plan item wrapper after plan update: "
											+ event.getPlan().getPlanId(), e);
						}

					}
				}));
	}

	public DiscussionBean getCommentsBean() {
		return commentsBean;
	}

	public void setExternalSimulationBean(
			org.climatecollaboratorium.facelets.simulations.SimulationBean externalSimulationBean) {
		this.externalSimulationBean = externalSimulationBean;

	}

	public void cancelSimulationEdit(ActionEvent e) throws SystemException,
			IllegalUIConfigurationException, PortalException, IOException {
		externalSimulationBean.editActions(e);
		refresh();
	}

	private Boolean hasAbstract;

	public boolean getHasAbstract() throws SystemException, PortalException {
		if (hasAbstract == null) {
			hasAbstract = false;
			for (PlanTypeAttribute a : PlanTypeLocalServiceUtil
					.getAttributes(PlanItemLocalServiceUtil
							.getPlanType(planItem))) {
				if (a.getAttributeName().equals(
						PlanConstants.Columns.ABSTRACT.name())) {
					hasAbstract = true;
					break;
				}

			}

		}

		return hasAbstract;
	}

	public List<PlanTabWrapper> getAvailableTabs() throws PortalException,
			SystemException {

		if (availableTabs == null) {
			if (getPlan() == null)
				return null;
			availableTabs = new ArrayList<PlanTabWrapper>();

			availableTabs
					.add(new PlanTabWrapper(planItem, PlanTab.DESCRIPTION));
			if (getPlan().getHasModel()) {
				availableTabs.add(new PlanTabWrapper(planItem,
						PlanTab.ACTIONSIMPACTS));
			}
			availableTabs.add(new PlanTabWrapper(planItem, PlanTab.TEAM));
			availableTabs.add(new PlanTabWrapper(planItem, PlanTab.COMMENTS));
			if (permissions.getCanAdmin()
					&& !planItem.getState().equals("DELETED")) {
				availableTabs.add(new PlanTabWrapper(planItem, PlanTab.ADMIN));
			}
		}

		return availableTabs;
	}

	public PlanTab getCurrentTab() {
		return currentTab;
	}

	public void changeTab(ActionEvent e) {
		currentTab = PlanTab.valueOf(e.getComponent().getAttributes()
				.get("tab").toString());
	}

	public boolean isEditing() {
		return editing;
	}

	public void toggleEditing(ActionEvent e) {
		try {
			boolean oldEditing = editing;
			if (oldEditing) {
				this.refresh();
				if (planItem.getVersion() == 1) {
					PlanItemLocalServiceUtil.delete(planItem, Helper
							.getLiferayUser().getUserId());
					leaveThisPlan = true;
				} else {
					editing = !oldEditing;
				}
			} else {
				editing = !oldEditing;
			}

		} catch (SystemException e1) {
		} catch (PortalException e1) {
		}
	}

	public boolean getLeaveThisPlan() {
		return leaveThisPlan;
	}

	public static enum PlanTab {
		ADMIN("ADMINISTRATION", false, false), DESCRIPTION("DESCRIPTION", true,
				true), ACTIONSIMPACTS("MODEL RESULTS", true, false), COMMENTS(
				"COMMENTS", false, false), TEAM("CONTRIBUTORS", false, false);

		private final String displayName;
		private final boolean editable;
		private final boolean hasHistory;

		PlanTab(String displayName, boolean editable, boolean hasHistory) {
			this.displayName = displayName;
			this.editable = editable;
			this.hasHistory = hasHistory;
		}

		public String getDisplayName() {
			return displayName;
		}

		public boolean isEditable() {
			return editable;
		}

		public boolean getHasHistory() {
			return hasHistory;
		}

	}

	public class PlanTabWrapper implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final PlanItem plan;
		private final PlanTab tab;

		public PlanTabWrapper(PlanItem plan, PlanTab tab) {
			this.tab = tab;
			this.plan = plan;
		}

		public String getDisplayName() {
			return tab.getDisplayName();
		}

		public boolean getHasActivityCount() {
			return tab == PlanTab.COMMENTS || tab == PlanTab.TEAM;
		}

		public int getActivityCount() throws SystemException, PortalException {
			if (tab == PlanTab.COMMENTS)
				return PlanItemLocalServiceUtil.getCommentsCount(plan);
			else if (tab == PlanTab.TEAM)
				return PlanItemLocalServiceUtil.getMembers(plan).size();
			return 0;
		}

		public PlanTab getTab() {
			return tab;
		}
	}
}
