package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
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
            throws PortalException, SystemException {
        
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("action", "updateProposalDetails");
            response.setRenderParameter("edit", "true");
            request.setAttribute("ACTION_ERROR", true);
            return;
        }
        
        
        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);
        if (updateProposalSectionsBean.getName() != null && !updateProposalSectionsBean.getName().equals(proposal.getName())) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.NAME, updateProposalSectionsBean.getName()); 
        }
        
        if (updateProposalSectionsBean.getPitch() != null && !updateProposalSectionsBean.getPitch().equals(proposal.getPitch())) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.PITCH, updateProposalSectionsBean.getPitch()); 
        }

        /*
         * TODO - team attribute handling
        if (updateProposalSectionsBean.getTeam() != null && !updateProposalSectionsBean.getTeam().equals(proposal.getTeam())) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys., updateProposalSectionsBean.getTeam()); 
        }*/

        if (updateProposalSectionsBean.getImageId() > 0 && updateProposalSectionsBean.getImageId() != proposal.getImageId()) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.IMAGE_ID, updateProposalSectionsBean.getImageId()); 
        }
        
        for (ProposalSectionWrapper section: proposal.getSections()) {
            String newSectionContent = updateProposalSectionsBean.getSectionsContent().get(section.getSectionDefinitionId()); 
            
            if (newSectionContent != null && !newSectionContent.equals(section.getContent())) {
                System.out.println("New section content: " + section.getSectionDefinitionId() + "\t" + newSectionContent);
                ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), newSectionContent);
            }
        }
        
        
        
        SessionMessages.add(request, "membershipRequestSent");
        
    }
    

    @RequestMapping(params = {"pageToDisplay=proposalDetails", "action=updateProposalDetails", "error=true"})
    public String reportError(PortletRequest request, Model model, 
            @ModelAttribute("updateProposalSectionsBean") @Valid UpdateProposalDetailsBean updateProposalSectionsBean, BindingResult result) {
        System.out.println("bajobongo i do przodu!");
        
        
        return "proposalDetails_edit";
    }
}
