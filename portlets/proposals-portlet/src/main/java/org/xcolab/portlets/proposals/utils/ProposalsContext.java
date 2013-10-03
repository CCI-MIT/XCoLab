package org.xcolab.portlets.proposals.utils;

import javax.portlet.PortletRequest;

import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.wrappers.ContestPhaseWrapper;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

public interface ProposalsContext {

    Contest getContest(PortletRequest request) throws PortalException, SystemException;

    ContestPhase getContestPhase(PortletRequest request) throws PortalException, SystemException;

    Proposal getProposal(PortletRequest request) throws PortalException, SystemException;

    ProposalsPermissions getPermissions(PortletRequest request) throws PortalException, SystemException;

    Proposal2Phase getProposal2Phase(PortletRequest request) throws PortalException, SystemException;

    Long getViewContestPhaseId(PortletRequest request) throws PortalException, SystemException;

    ProposalWrapper getProposalWrapped(PortletRequest request) throws PortalException, SystemException;

    ContestWrapper getContestWrapped(PortletRequest request) throws PortalException, SystemException;

    ContestPhaseWrapper getContestPhaseWrapped(PortletRequest request) throws PortalException, SystemException;
    
    User getUser(PortletRequest request) throws PortalException, SystemException;

}