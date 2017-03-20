package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.view.proposal.BaseProposalsController;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.tabs.ProposalTabWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseProposalTabController extends BaseProposalsController {

    protected final static String ACCESS_TAB_DENIED_MESSAGE = "You don't have the permissions to access this content.";
    
    @Autowired
    private ProposalsContext proposalsContext;
    

    @ModelAttribute
    public void getTabs(Model model, HttpServletRequest request) {
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
    

    protected void setCommonModelAndPageAttributes(HttpServletRequest request, Model model, ProposalTab tab) {
       
        model.addAttribute("currentTab", tab);
        model.addAttribute("currentTabWrapped", new ProposalTabWrapper(tab, request, proposalsContext));

        final Contest contestWrapped = proposalsContext.getContestWrapped(request);
        final Proposal proposalWrapped = proposalsContext.getProposalWrapped(request);
        final ProposalClient proposalClient = proposalsContext.getClients(request).getProposalClient();

        String pageTitle = contestWrapped.getContestShortName();
        String pageSubTitle = proposalWrapped.getName();
        String pageDescription = proposalWrapped.getPitch();
        
        if (pageSubTitle == null || StringUtils.isBlank(pageSubTitle)) {
            final ContestType contestType = proposalClient.getContestTypeFromProposalId(proposalWrapped.getProposalId());
            pageSubTitle = contestType.getProposalName() + " for " + contestWrapped.getContestShortName();
        }

        if (tab != ProposalTab.DESCRIPTION) {
            pageSubTitle = tab.getDisplayName() + " - " + pageTitle;
        }
        setSeoTexts(request, pageTitle, pageSubTitle, pageDescription);
        setBasePageAttributes(request, model);
    }

    protected long createDiscussionThread(HttpServletRequest request,
            String threadTitleSuffix, boolean isQuiet) {
        final ContestType contestType = proposalsContext.getContestType(request);
        CommentThread thread = new CommentThread();
        final Proposal proposal = proposalsContext.getProposal(request);
        thread.setAuthorId(proposal.getAuthorId());
        thread.setTitle(
                contestType.getProposalName()
                        + proposalsContext.getProposalWrapped(request).getName()
                        + threadTitleSuffix);
        thread.setIsQuiet(isQuiet);
        return proposalsContext.getClients(request).getThreadClient()
                .createThread(thread).getThreadId();
    }
}
