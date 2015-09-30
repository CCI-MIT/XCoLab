package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.NoSuchProposal2PhaseException;
import com.ext.portlet.PlanSectionTypeKeys;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.utils.HtmlUtil;
import org.xcolab.utils.emailnotification.ProposalCreationNotification;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("view")
public class AddUpdateProposalDetailsActionController {
	
    public final static String PROPOSAL_ANALYTICS_KEY = "CONTEST_ENTRY_UPDATE";
    public final static String PROPOSAL_ANALYTICS_CATEGORY = "User";
    public final static String PROPOSAL_ANALYTICS_ACTION = "Contest entry update";
    public final static String PROPOSAL_ANALYTICS_LABEL = "";
    private final static Set<String> attributesNotToBeCopiedFromBaseProposal = new HashSet<>();
    static {
    	attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.SECTION);
    	attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.DESCRIPTION);
    	attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.NAME);
    	attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.PITCH);
    	attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.TEAM);
    }

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
        long userId = themeDisplay.getUserId();
        
        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("action", "updateProposalDetails");
            response.setRenderParameter("edit", "true");
            request.setAttribute("ACTION_ERROR", true);
            return;
        }
        ProposalWrapper proposal;
        if (proposalsContext.getProposal(request) != null) {
            proposal = proposalsContext.getProposalWrapped(request);
            if (updateProposalSectionsBean.isMove() && updateProposalSectionsBean.getMoveToContestPhaseId() > 0) {
                // Find creation phase for contest
                List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(proposalsContext.getContestPhase(request).getContestPK());
                ContestPhase targetPhase=null;
                for (ContestPhase c : contestPhases){
                    if(ContestPhaseTypeLocalServiceUtil.getContestPhaseType(c.getContestPhaseType()).getStatus().equalsIgnoreCase("OPEN_FOR_SUBMISSION")){
                        targetPhase = c;
                        break;
                    }
                }
                if (targetPhase == null) throw new SystemException("No Proposal creation phase is associated with target contest.");

                //check if the proposal is already in the target phase
                try {
                    if (Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), targetPhase.getContestPhasePK()) != null) {
                        throw new SystemException("The proposal is already associated with the target contest.");
                    }
                } catch (NoSuchProposal2PhaseException e) {
                    //this is the expected behaviour.
                }

            	if (updateProposalSectionsBean.isHideOnMove()) {
                    // make proposal invisible in all contest phases to which it belonged to

                    for (Proposal2Phase p2p: Proposal2PhaseLocalServiceUtil.getByProposalId(proposal.getProposalId())) {
                        // only handle phases of the current contest and remove these. this allows for more advanced proposal movement
                        if (ContestPhaseLocalServiceUtil.getContestPhase(p2p.getContestPhaseId()).getContestPK() != updateProposalSectionsBean.getBaseProposalContestId()) {
                            continue;
                        }

                        // Set end version if it was not set already
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposal.getCurrentVersion());
                            Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);
                        }

                        if(updateProposalSectionsBean.getMoveFromContestPhaseId() != null){
                            if (!ContestPhaseLocalServiceUtil.getContestPhase(p2p.getContestPhaseId()).getPhaseStartDate().before(
                                    ContestPhaseLocalServiceUtil.getContestPhase(updateProposalSectionsBean.getMoveFromContestPhaseId()).getPhaseStartDate())) {
                                // remove proposal from this contest in all phases that come after the selected one
                                Proposal2PhaseLocalServiceUtil.deleteProposal2Phase(p2p);
                            }
                        }
                    }
                } else {
                    // Conclude last P2P mapping to prevent concurrent editing
                    for (Proposal2Phase p2p: Proposal2PhaseLocalServiceUtil.getByProposalId(proposal.getProposalId())) {
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposal.getCurrentVersion());
                            Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);
                        }
                    }
                }

            	// associate proposal with creation phase
            	Proposal2PhaseLocalServiceUtil.create(proposal.getProposalId(), targetPhase.getContestPhasePK(),
            			proposal.getCurrentVersion(), -1);
                ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(proposal.getProposalId(), proposalsContext.getContestPhase(request).getContestPhasePK(),
                        ProposalContestPhaseAttributeKeys.VISIBLE, 1);
            }
        } else {
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
            
            if (updateProposalSectionsBean.getBaseProposalId() > 0) {
            	// we have a base proposal
            	ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.BASE_PROPOSAL_ID, updateProposalSectionsBean.getBaseProposalId());
            	ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.BASE_PROPOSAL_CONTEST_ID, updateProposalSectionsBean.getBaseProposalContestId());
            	
            	 
            	
            	for (ProposalAttribute attribute: ProposalLocalServiceUtil.getAttributes(updateProposalSectionsBean.getBaseProposalId())) {
            		if (attributesNotToBeCopiedFromBaseProposal.contains(attribute.getName())) {
            			continue;
            		}
            		ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), attribute.getName(), attribute.getAdditionalId(), attribute.getStringValue(), attribute.getNumericValue(), attribute.getRealValue());
            	}
            }
        }
        
        boolean filledAll = true;
        
        
        if (updateProposalSectionsBean.getName() != null && (proposal.getName() == null || !updateProposalSectionsBean.getName().equals(proposal.getName()))) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.NAME, HtmlUtil.cleanMost(updateProposalSectionsBean.getName()));
        }
        else {
        	filledAll = false;
        }
        
        if (updateProposalSectionsBean.getPitch() != null && (proposal.getName() == null || !updateProposalSectionsBean.getPitch().equals(proposal.getPitch()))) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.PITCH, HtmlUtil.cleanSome(updateProposalSectionsBean.getPitch()));
        }
        else {
        	filledAll = false;
        }

        if (updateProposalSectionsBean.getDescription() != null && (proposal.getName() == null || !updateProposalSectionsBean.getDescription().equals(proposal.getDescription()))) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.DESCRIPTION, HtmlUtil.cleanSome(updateProposalSectionsBean.getDescription()));
        }
        else {
        	filledAll = false;
        }

        if (updateProposalSectionsBean.getTeam() != null && !updateProposalSectionsBean.getTeam().equals(proposal.getTeam())) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.TEAM, HtmlUtil.cleanMost(updateProposalSectionsBean.getTeam()));
        }
        else {
        	filledAll = false;
        }

        if (updateProposalSectionsBean.getImageId() > 0 && updateProposalSectionsBean.getImageId() != proposal.getImageId()) {
            ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.IMAGE_ID, updateProposalSectionsBean.getImageId()); 
        }
        else {
        	filledAll = false;
        }
        
        for (ProposalSectionWrapper section: proposal.getSections()) {
            String newSectionValue = updateProposalSectionsBean.getSectionsContent().get(section.getSectionDefinitionId()); 
            if (section.getType() == PlanSectionTypeKeys.TEXT || section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
                if (newSectionValue != null && !newSectionValue.trim().equals(section.getContent())) {
                    ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), HtmlUtil.cleanSome(newSectionValue));
                }
                else {
                	filledAll = false;
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
                else {
                	filledAll = false;
                }
            }
            if (section.getType() == PlanSectionTypeKeys.PROPOSAL_REFERENCE) {
                if (StringUtils.isNumeric(newSectionValue) && StringUtils.isNotBlank(newSectionValue)) {
                    ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), Long.parseLong(newSectionValue));
                } else if (StringUtils.isBlank(newSectionValue)) {
                    ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), 0l);
                }
            }
            if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_REFERENCE) {
                // check if all parts are numeric
                String cleanedReferences = "";
                if (StringUtils.isNotBlank(newSectionValue)){
                    String[]referencedProposals = newSectionValue.split(",");
                    for (String referencedProposal : referencedProposals) {
                        if (StringUtils.isNotBlank(referencedProposal) && StringUtils.isNumeric(referencedProposal))
                            cleanedReferences += referencedProposal + ",";
                    }
                    //if (cleanedReferences.substring(cleanedReferences.length()-2,cleanedReferences.length()-1).equalsIgnoreCase(",")) cleanedReferences = cleanedReferences.substring(0, cleanedReferences.length() - 2);
                }
                ProposalLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposal.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), cleanedReferences);
            }
        }
        


        int analyticsValue;
        
        if (filledAll) {
        	analyticsValue = 3;
        } else {
        	analyticsValue = 2;
        }
        
        AnalyticsUtil.publishEvent(request, userId, PROPOSAL_ANALYTICS_KEY + analyticsValue,
                PROPOSAL_ANALYTICS_CATEGORY,
                PROPOSAL_ANALYTICS_ACTION,
                PROPOSAL_ANALYTICS_LABEL,
                analyticsValue);

        if (createNew) {
            // Send email notification to author
            ServiceContext serviceContext = new ServiceContext();
            serviceContext.setPortalURL(themeDisplay.getPortalURL());
            Contest contest = ContestPhaseLocalServiceUtil.getContest(ContestPhaseLocalServiceUtil.getContestPhase(proposalsContext.getContestPhase(request).getContestPhasePK()));
            new ProposalCreationNotification(proposal.getWrapped(), contest, serviceContext).sendMessage();
        }
        
        
        proposalsContext.invalidateContext(request);
        
        request.setAttribute("ACTION_REDIRECTING", true);
        response.sendRedirect("/web/guest/plans/-/plans/contestId/" + proposalsContext.getContest(request).getContestPK() + "/planId/" + proposal.getProposalId());
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
