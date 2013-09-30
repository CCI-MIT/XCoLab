package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.requests.RequestMembershipBean;
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
public class UpdateProposalDetailsActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    
    @RequestMapping(params = {"pageToDisplay=proposalDetails", "action=updateProposalDetails"})
    public void show(ActionRequest request, Model model,
            ActionResponse response, @Valid UpdateProposalDetailsBean updateProposalSectionsBean, BindingResult result) 
            throws PortalException, SystemException {
        
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            return;
        }
        
        
        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);
        for (ProposalSectionWrapper section: proposal.getSections()) {
            String newSectionContent = updateProposalSectionsBean.getSectionsContent().get(section.getSectionDefinitionId()); 
            if (newSectionContent == null) {
                System.out.println("content is null " + section.getSectionDefinitionId() + "\t" + section.getTitle());
            }
            else {
                System.out.println(section.getContent().length() + "\t" + newSectionContent.length() + "\t" + section.getContent().equals(newSectionContent));
                ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), newSectionContent);
            }
            
            if (newSectionContent != null && !newSectionContent.equals(section.getContent())) {
                System.out.println("New section content: " + section.getSectionDefinitionId());   
            }
        }
        
        
        
        SessionMessages.add(request, "membershipRequestSent");
        
    }
    

    @RequestMapping(params = {"pageToDisplay=proposalDetails", "action=updateProposalDetails", "error=true"})
    public String registerError(PortletRequest request, Model model,
            @Valid RequestMembershipBean newAccountBean, BindingResult result,
            @RequestParam(required = false) String redirect) {
        return "proposalSections";
    }
}
