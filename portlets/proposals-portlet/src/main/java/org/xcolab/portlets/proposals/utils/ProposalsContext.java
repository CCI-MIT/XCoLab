package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.portlets.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.wrappers.BaseContestPhaseWrapper;

import javax.portlet.PortletRequest;

public interface ProposalsContext {

    Contest getContest(PortletRequest request) throws PortalException, SystemException;

    ContestPhase getContestPhase(PortletRequest request) throws PortalException, SystemException;

    Proposal getProposal(PortletRequest request) throws PortalException, SystemException;

    ProposalsPermissions getPermissions(PortletRequest request) throws PortalException, SystemException;

    ProposalsDisplayPermissions getDisplayPermissions(PortletRequest request) throws PortalException, SystemException;

    Proposal2Phase getProposal2Phase(PortletRequest request) throws PortalException, SystemException;

    Long getViewContestPhaseId(PortletRequest request) throws PortalException, SystemException;

    ProposalWrapper getProposalWrapped(PortletRequest request) throws PortalException, SystemException;

    ContestWrapper getContestWrapped(PortletRequest request) throws PortalException, SystemException;

    BaseContestPhaseWrapper getContestPhaseWrapped(PortletRequest request) throws PortalException, SystemException;

    ContestType getContestType(PortletRequest request) throws PortalException, SystemException;
    
    User getUser(PortletRequest request) throws PortalException, SystemException;

    void invalidateContext(PortletRequest request);

    ProposalsPreferencesWrapper getProposalsPreferences(PortletRequest request) throws PortalException, SystemException;

}