/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.ContestBean;
import org.climatecollaboratorium.plans.CreatePlanBean;
import org.climatecollaboratorium.plans.Helper;
import org.climatecollaboratorium.plans.PlansIndexBean;

import com.ext.portlet.NoSuchContestPhaseException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * Created by IntelliJ IDEA. User: jintrone Date: Aug 6, 2010 Time: 2:53:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestWrapper {
	private Contest contest;
	private List<ContestPhaseWrapper> phases = new ArrayList<ContestPhaseWrapper>();
	private Map<Long, ContestPhaseWrapper> index = new HashMap<Long, ContestPhaseWrapper>();
	private EditContestBean editor;
	private String debatesIdsStr = null;
	private PlansIndexBean plansIndex;
	private EventBus eventBus;
	private List<ContestPhaseWrapper> activeOrPastPhases = new ArrayList<ContestPhaseWrapper>();
	private List<ContestPhaseWrapper> pastPhases = new ArrayList<ContestPhaseWrapper>();
	ContestPhaseWrapper activePhase = null;

	private Map<String, List<User>> teamRoleUsersMap = new TreeMap<String, List<User>>();
	private CreatePlanBean createPlanBean;

	public ContestWrapper(Contest contest) throws SystemException,
			PortalException {
		ContestPhase defaultPhase = null;
		boolean addAsActiveOrPast = true;
		this.contest = contest;
		Date now = new Date();
		List<ContestPhase> contestPhases = ContestLocalServiceUtil
				.getPhases(contest);
		int phasesCount = phases.size();
		int phaseNumber = 0;
		for (ContestPhase phase : contestPhases) {
			phaseNumber++;

			ContestPhaseWrapper phaseWrapper = new ContestPhaseWrapper(this,
					phase, phaseNumber == phasesCount);
			if (addAsActiveOrPast) {
				activeOrPastPhases.add(phaseWrapper);
				if (!phaseWrapper.isActive()) {
					pastPhases.add(phaseWrapper);
				}
			}
			if (ContestPhaseLocalServiceUtil.getPhaseActive(phase)) {
				// don't add next phases as they haven't started yet
				addAsActiveOrPast = false;
			}
			phases.add(phaseWrapper);
			index.put(phase.getContestPhasePK(), phaseWrapper);
			if (ContestPhaseLocalServiceUtil.getPhaseActive(phase)) {
				activePhase = phaseWrapper;
			}
			if (phase.getPhaseStartDate().before(now))
				defaultPhase = defaultPhase == null
						|| defaultPhase.getPhaseStartDate().before(
								phase.getPhaseStartDate()) ? phase
						: defaultPhase;
		}
		if (activePhase == null) {
			activePhase = new ContestPhaseWrapper(this, defaultPhase, false);
		}
		editor = new EditContestBean();
		plansIndex = new PlansIndexBean(activePhase);

		// reverse list to have active phase as the first one
		Collections.reverse(activeOrPastPhases);
		createPlanBean = new CreatePlanBean(this);

		for (ContestTeamMember ctm : ContestLocalServiceUtil
				.getTeamMembers(contest)) {
			List<User> roleUsers = teamRoleUsersMap.get(ctm.getRole());
			if (roleUsers == null) {
				roleUsers = new ArrayList<User>();
				teamRoleUsersMap.put(ctm.getRole(), roleUsers);
			}
			roleUsers.add(ContestTeamMemberLocalServiceUtil.getUser(ctm));
		}
	}

	public String getName() {
		return contest.getContestName();

	}

	public String getDescription() {
		return contest.getContestDescription();
	}

	public String getModelDescription() {
		return contest.getContestModelDescription();
	}

	public String getPositionsDescription() {
		return contest.getContestPositionsDescription();
	}

	public List<ContestPhaseWrapper> getPhases() {
		return phases;
	}

	public ContestPhaseWrapper findPhase(Long contestPhaseId) {
		return index.get(contestPhaseId);
	}

	public Contest getContest() {
		return contest;
	}

	public EditContestBean getEditor() {
		return editor;
	}

	public List<Long> getDebatesIds() throws SystemException {
		return ContestLocalServiceUtil.getDebatesIds(contest);
	}

	public String getSponsorText() {
		return contest.getSponsorText();
	}

	public String getSponsorLogo() throws PortalException, SystemException {
		return Helper.getThemeDisplay().getPathImage()
				+ ContestLocalServiceUtil.getSponsorLogoPath(contest);
	}

	public String getShortName() {
		return contest.getContestShortName();
	}

	public String getContestModelDescription() {
		return contest.getContestModelDescription();
	}

	private String logo;
	public String getLogo() throws PortalException, SystemException {
	    if (logo == null) {
	        logo = Helper.getThemeDisplay().getPathImage() + ContestLocalServiceUtil.getLogoPath(contest);
	    }
	    return logo;
	}

	private Boolean featured;
	public boolean isFeatured() {
	    if (featured == null) {
	        featured = contest.getFlagText().toLowerCase().equals("featured");
	    }
	    return featured;
	}

	public Integer getFlag() {
		return contest.getFlag();
	}

	public String getFlagText() {
		return contest.getFlagText();
	}

	public String getFlagTextClass() {
		return contest.getFlagText().toLowerCase().replaceAll("\\s", "");
	}

	public List<ContestPhaseWrapper> getPastPhases() {
		return pastPhases;
	}

	/**
	 * Created by IntelliJ IDEA. User: jintrone Date: Aug 17, 2010 Time:
	 * 10:08:58 AM To change this template use File | Settings | File Templates.
	 */
	public class EditContestBean {

		String name;
		String description;
		String modelDescription;
		String positionsDescription;
		String shortName;
		String defaultPlanDescription;
		boolean editing = false;
		boolean editingPositions = false;
		private List<SelectItem> questionItems;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getModelDescription() {
			return modelDescription;
		}

		public void setModelDescription(String description) {
			this.modelDescription = description;
		}

		public String getPositionsDescription() {
			return positionsDescription;
		}

		public void setPositionsDescription(String description) {
			this.positionsDescription = description;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getShortName() {
			return shortName;
		}

		public void setShortName(String shortName) {
			this.shortName = shortName;
		}

		public boolean getEditing() {
			return editing;
		}

		public void edit() {
			editing = true;
			this.name = contest.getContestName();
			this.description = contest.getContestDescription();
			this.positionsDescription = contest
					.getContestPositionsDescription();
			this.modelDescription = contest.getContestModelDescription();
			this.shortName = contest.getContestShortName();
			this.defaultPlanDescription = contest.getDefaultPlanDescription();

		}

		public void save() throws SystemException {

			contest.setContestName(name);
			contest.setContestDescription(description);
			contest.setContestModelDescription(modelDescription);
			contest.setContestPositionsDescription(positionsDescription);
			contest.setContestShortName(shortName);
			contest.setDefaultPlanDescription(defaultPlanDescription);
			ContestLocalServiceUtil.updateContest(contest);
			editing = false;
		}

		public void cancel() {
			editing = false;
		}

		public boolean isEditingPositions() {
			return editingPositions;
		}

		public void editPositions() {
			editingPositions = !editingPositions;
		}

		public String getDefaultPlanDescription() {
			return defaultPlanDescription;
		}

		public void setDefaultPlanDescription(String defaultPlanDescription) {
			this.defaultPlanDescription = defaultPlanDescription;
		}

	}

	public boolean isContestActive() {
		return contest.getContestActive();
	}

	public Long getModelId() throws PortalException, SystemException {

		return ContestLocalServiceUtil.getPlanType(contest).getDefaultModelId();
	}

	public PlansIndexBean getPlansIndex() {
		return plansIndex;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
		plansIndex.setEventBus(eventBus);
		createPlanBean.setEventBus(eventBus);
	}

	public Long getContestId() {
		return contest.getContestPK();
	}

	public List<ContestPhaseWrapper> getActiveOrPastPhases() {
		return activeOrPastPhases;
	}

	public boolean getHasModel() throws PortalException, SystemException {
		Long modelId = ContestLocalServiceUtil.getPlanType(getContest())
				.getDefaultModelId();
		return modelId != null && modelId > 0;
	}

	public void setCreatePlanBean(CreatePlanBean createPlanBean) {
		this.createPlanBean = createPlanBean;
	}

	public CreatePlanBean getCreatePlanBean() {
		return createPlanBean;
	}

	public void init(ContestBean contestBean, NavigationEvent event)
			throws NoSuchContestPhaseException, PortalException,
			SystemException {
		plansIndex.init(contestBean.getCurrentPhase(), event);
	}

	public ContestPhaseWrapper getActivePhase() {
		return activePhase;
	}

	public boolean getHasFocusArea() throws PortalException, SystemException {
		return contest.getFocusAreaId() > 0;
	}

	public List<OntologyTerm> getWho() throws PortalException, SystemException {
		return getTermFromSpace("who");
	}

	public List<OntologyTerm> getWhat() throws PortalException, SystemException {
		return getTermFromSpace("what");

	}

	public List<OntologyTerm> getWhere() throws PortalException,
			SystemException {
		return getTermFromSpace("where");
	}

	public long getProposalsCount() throws SystemException, PortalException {
		return ContestLocalServiceUtil.getProposalsCount(contest);
	}

	public long getCommentsCount() throws PortalException, SystemException {
		return ContestLocalServiceUtil.getTotalComments(contest);
	}

	public String getResourcesUrl() {
		return contest.getResourcesUrl();
	}

	private final static Map<Long, FocusArea> faCache = new HashMap<Long, FocusArea>();
	private Map<String, List<OntologyTerm>> cache = new HashMap<String, List<OntologyTerm>>();
	private List<OntologyTerm> getTermFromSpace(String space)
			throws PortalException, SystemException {

	    if (!cache.containsKey(space)) {
	        if (! faCache.containsKey(contest.getFocusAreaId())) {
	            FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(contest
	                    .getFocusAreaId());
	            if (fa == null) {
	                cache.put(space, null);
	                return null;
	            }
	            faCache.put(fa.getId(), fa);
	        }
	        List<OntologyTerm> terms = new ArrayList<OntologyTerm>();
	        for (OntologyTerm t : FocusAreaLocalServiceUtil.getTerms(faCache.get(contest.getFocusAreaId()))) {
	            if (OntologyTermLocalServiceUtil.getSpace(t).getName()
	                    .equalsIgnoreCase(space)) {
	                terms.add(t);
	            }
		    }
	        cache.put(space, terms.isEmpty() ? null : terms);

        }   
	    return cache.get(space);
		
	}

	public Long getCategoryGroupId() throws PortalException, SystemException {
		if (contest.getDiscussionGroupId() <= 0) {
			ContestLocalServiceUtil.updateContestGroupsAndDiscussions();
		}
		return contest.getDiscussionGroupId();
	}

	public Long getGroupId() {
		return contest.getGroupId();
	}

	public List<ContestTeamMemberWrapper> getTeamMembers()
			throws SystemException {
		List<ContestTeamMemberWrapper> ret = new ArrayList<ContestTeamMemberWrapper>();
		for (ContestTeamMember member : ContestLocalServiceUtil
				.getTeamMembers(contest)) {
			ret.add(new ContestTeamMemberWrapper(member));
		}
		return ret;
	}

	public Map<String, List<User>> getTeamRoleUsers() {
		return teamRoleUsersMap;
	}

	public Set<String> getTeamRoles() {
		return teamRoleUsersMap.keySet();
	}

	public String getFlagTooltip() {
		return contest.getFlagTooltip();
	}
}
