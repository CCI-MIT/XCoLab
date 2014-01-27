package org.xcolab.portlets.proposals.wrappers;

import javax.portlet.PortletRequest;

import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

interface ProposalTabCanAccessAlgorithm {
    boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request);
    
    
    public final static ProposalTabCanAccessAlgorithm alwaysTrue = new ProposalTabCanAccessAlgorithm() {
        
        @Override
        public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
            return true;
        }
    };
    
    public final static ProposalTabCanAccessAlgorithm alwaysFalse = new ProposalTabCanAccessAlgorithm() {
        
        @Override
        public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
            return false;
        }
    };
    
    public final static ProposalTabCanAccessAlgorithm adminOnlyAccess = new ProposalTabCanAccessAlgorithm() {
        
        @Override
        public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
            return permissions.getCanAdmin();
        }
    };
    
    public final static ProposalTabCanAccessAlgorithm judgeAccess = new ProposalTabCanAccessAlgorithm() {
        
        @Override
        public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
            if(!fellowAccess.canAccess(permissions, context, request)) return false;
            try {
                ProposalJudgeWrapper wrapper = new ProposalJudgeWrapper(context.getProposal(request), context.getUser(request));
                return wrapper.shouldShowJudgingTab(context.getContestPhase(request).getContestPhasePK());
            } catch (Throwable e) {

            }
            return false;
        }
    };
    
    public final static ProposalTabCanAccessAlgorithm fellowAccess = new ProposalTabCanAccessAlgorithm() {
        
        @Override
        public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
            return permissions.getCanFellowActions();
        }
    };
    
    public final static ProposalTabCanAccessAlgorithm canEditAccess = new ProposalTabCanAccessAlgorithm() {
        
        @Override
        public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
            try {
                return permissions.getCanEdit();
            } catch (SystemException e) {
                _log.error("can't check if user is allowed to edit proposal", e);
            } catch (PortalException e) {
                _log.error("can't check if user is allowed to edit proposal", e);
            }
            return false;
        }
        private final Log _log = LogFactoryUtil.getLog(ProposalTabCanAccessAlgorithm.class);
    };
    
}