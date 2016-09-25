package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalTabWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

@Component
public class BaseProposalTabController extends BaseProposalsController {

    protected final static String ACCESS_TAB_DENIED_MESSAGE = "You don't have the permissions to access this content.";
    
    @Autowired
    private ProposalsContext proposalsContext;
    
    @ModelAttribute
    public void getTabs(Model model, PortletRequest request) throws PortalException, SystemException {
        // populate available tabs
        
        List<ProposalTabWrapper> tabs = new ArrayList<>();
        
        for (ProposalTab tab: ProposalTab.values()) {
            
            ProposalTabWrapper tabWrapper = new ProposalTabWrapper(tab, request, proposalsContext);
            if (tabWrapper.getCanAccess()) {
                tabs.add(new ProposalTabWrapper(tab, request, proposalsContext));
            }
        }
        
        model.addAttribute("tabs", tabs);
    }
    

    protected void setCommonModelAndPageAttributes(PortletRequest request, Model model, ProposalTab tab) 
            throws PortalException, SystemException {
       
        model.addAttribute("currentTab", tab);
        model.addAttribute("currentTabWrapped", new ProposalTabWrapper(tab, request, proposalsContext));

        final ContestWrapper contestWrapped = proposalsContext.getContestWrapped(request);
        final ProposalWrapper proposalWrapped = proposalsContext.getProposalWrapped(request);

        String pageTitle = contestWrapped.getContestShortName();
        String pageSubTitle = proposalWrapped.getName();
        String pageDescription = proposalWrapped.getPitch();
        
        if (pageSubTitle == null || StringUtils.isBlank(pageSubTitle)) {
            final ContestType contestType = ContestTypeLocalServiceUtil.getContestTypeFromProposalId(proposalWrapped.getProposalId());
            pageSubTitle = contestType.getProposalName() + " for " + contestWrapped.getContestShortName();
        }

        if (tab != ProposalTab.DESCRIPTION) {
            pageSubTitle = tab.getDisplayName() + " - " + pageTitle;
        }
        setSeoTexts(request, pageTitle, pageSubTitle, pageDescription);
        
    }

    protected long createDiscussionThread(PortletRequest request,
            String threadTitleSuffix, boolean isQuiet)
            throws SystemException, PortalException {
        final ContestType contestType = proposalsContext.getContestType(request);
        CommentThread thread = new CommentThread();
        final Proposal proposal = proposalsContext.getProposal(request);
        thread.setAuthorId(proposal.getAuthorId());
        thread.setTitle(
                contestType.getProposalName()
                        + proposalsContext.getProposalWrapped(request).getName()
                        + threadTitleSuffix);
        thread.setIsQuiet(isQuiet);
        return ThreadClientUtil.createThread(thread).getThreadId();
    }
}
