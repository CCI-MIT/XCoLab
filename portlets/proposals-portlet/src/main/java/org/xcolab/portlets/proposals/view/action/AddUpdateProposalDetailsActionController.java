package org.xcolab.portlets.proposals.view.action;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.PlanSectionTypeKeys;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;


@Controller
@RequestMapping("view")
public class AddUpdateProposalDetailsActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    
    @RequestMapping(params = {"action=updateProposalDetails"})
    public void show(ActionRequest request, Model model,
            ActionResponse response, @Valid UpdateProposalDetailsBean updateProposalSectionsBean, BindingResult result) 
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        boolean createNew = false;
        
        if (proposalsContext.getProposal(request) != null && ! proposalsContext.getPermissions(request).getCanEdit()) {
            throw new ProposalsAuthorizationException("User is not allowed to edit proposal, user: " +
                    proposalsContext.getUser(request).getUserId() + ", proposal: " + proposalsContext.getProposal(request).getProposalId());
        }
        else if (proposalsContext.getProposal(request) == null && ! proposalsContext.getPermissions(request).getCanCreate()) {
            throw new ProposalsAuthorizationException("User is not allowed to create proposal, user: " +
                    proposalsContext.getUser(request).getUserId() + ", contest: " + proposalsContext.getContest(request).getContestPK());
        }
        
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("action", "updateProposalDetails");
            response.setRenderParameter("edit", "true");
            request.setAttribute("ACTION_ERROR", true);
            return;
        }
        ProposalWrapper proposal = null;
        if (proposalsContext.getProposal(request) != null) {
            proposal = proposalsContext.getProposalWrapped(request);
        }
        else {
            // create
            createNew = true;
            Proposal newProposal = ProposalLocalServiceUtil.create(proposalsContext.getUser(request).getUserId(), 
                    proposalsContext.getContestPhase(request).getContestPhasePK());
            Proposal2Phase newProposal2Phase = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(newProposal.getProposalId(), 
                    proposalsContext.getContestPhase(request).getContestPhasePK());
            
            proposal = new ProposalWrapper(
                    newProposal,  
                    0, 
                    proposalsContext.getContest(request), 
                    proposalsContext.getContestPhase(request), 
                    newProposal2Phase) ;
        }
        
        
        if (updateProposalSectionsBean.getName() != null && (proposal.getName() == null || !updateProposalSectionsBean.getName().equals(proposal.getName()))) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.NAME, removeHtml(updateProposalSectionsBean.getName()));
        }
        
        if (updateProposalSectionsBean.getPitch() != null && (proposal.getName() == null || !updateProposalSectionsBean.getPitch().equals(proposal.getPitch()))) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.PITCH, xssClean(updateProposalSectionsBean.getPitch()));
        }

        if (updateProposalSectionsBean.getTeam() != null && !updateProposalSectionsBean.getTeam().equals(proposal.getTeam())) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.TEAM, removeHtml(updateProposalSectionsBean.getTeam()));
        }

        if (updateProposalSectionsBean.getImageId() > 0 && updateProposalSectionsBean.getImageId() != proposal.getImageId()) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.IMAGE_ID, updateProposalSectionsBean.getImageId()); 
        }
        
        for (ProposalSectionWrapper section: proposal.getSections()) {
            String newSectionValue = updateProposalSectionsBean.getSectionsContent().get(section.getSectionDefinitionId()); 
            if (section.getType() == PlanSectionTypeKeys.TEXT) {
                if (newSectionValue != null && !newSectionValue.trim().equals(section.getContent())) {
                    ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), xssClean(newSectionValue));
                }
            }
            if (section.getType() == PlanSectionTypeKeys.ONTOLOGY_REFERENCE) {
                // value 
                if (StringUtils.isNumeric(newSectionValue)) {
                    long newNumericVal = Long.parseLong(newSectionValue);
                    if (newNumericVal != section.getNumericValue()) {
                        ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), 
                                proposal.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), newNumericVal);
                    }
                }
            }
        }
        
        
        proposalsContext.invalidateContext(request);
        
        if (createNew) {
            request.setAttribute("ACTION_REDIRECTING", true);
            response.sendRedirect("/web/guest/plans/-/plans/contestId/" + proposalsContext.getContest(request).getContestPK() + "/planId/" + proposal.getProposalId());
        }
    }

    private String removeHtml(String data) {
        return Jsoup.clean(data, Whitelist.none());
    }

    private String xssClean(String sectionData) {
        //http://jsoup.org/cookbook/cleaning-html/whitelist-sanitizer
        Whitelist w = Whitelist.relaxed();
        w.addEnforcedAttribute("a", "target", "_blank"); //open all links in new windows
        w.addEnforcedAttribute("a", "rel", "nofollow"); //nofollow for search engines

        String xssCleaned = Jsoup.clean(sectionData, w);

        return xssCleaned;
    }

    @RequestMapping(params = {"action=updateProposalDetails", "error=true"})
    public String reportError(PortletRequest request, Model model, 
            @ModelAttribute("updateProposalSectionsBean") @Valid UpdateProposalDetailsBean updateProposalSectionsBean, BindingResult result) throws PortalException, SystemException {
        ProposalWrapper proposalWrapped = proposalsContext.getProposalWrapped(request);

        Proposal proposal = ProposalLocalServiceUtil.createProposal(0);
        proposal.setAuthorId(proposalsContext.getUser(request).getUserId());
        
        if (proposalWrapped == null) {
            proposalWrapped = new ProposalWrapper(proposal, 0, proposalsContext.getContest(request), proposalsContext.getContestPhase(request), null);
            model.addAttribute("proposal", proposalWrapped);
        }
        
        model.addAttribute("updateProposalSectionsBean",updateProposalSectionsBean);
        return "proposalDetails_edit";
    }
}
