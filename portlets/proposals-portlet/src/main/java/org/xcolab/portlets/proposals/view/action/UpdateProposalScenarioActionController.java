package org.xcolab.portlets.proposals.view.action;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;

import com.liferay.portal.kernel.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;


@Controller
@RequestMapping("view")
public class UpdateProposalScenarioActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=updateProposalScenario"})
    public void show(ActionRequest request, Model model,
            ActionResponse response,
                     @RequestParam(required = true) long scenarioId,
                     @RequestParam(required = true) long modelId,
                     @RequestParam(required = false) String region)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {


        if (proposalsContext.getProposal(request) != null && !canEditImpactTab(request)){
            throw new ProposalsAuthorizationException("User is not allowed to edit proposal, user: " +
                    proposalsContext.getUser(request).getUserId() + ", proposal: " + proposalsContext.getProposal(request).getProposalId());
        }

        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);
        proposal.setScenarioId(scenarioId, modelId, proposalsContext.getUser(request).getUserId());
        if(!Validator.isBlank(region)){
            proposal.setModelRegion(region, proposalsContext.getUser(request).getUserId());
        }
        proposalsContext.invalidateContext(request);

    }

    private boolean canEditImpactTab(PortletRequest request) throws PortalException, SystemException{
        return ProposalTab.IMPACT.getCanEdit(proposalsContext.getPermissions(request), proposalsContext, request);
    }

}
