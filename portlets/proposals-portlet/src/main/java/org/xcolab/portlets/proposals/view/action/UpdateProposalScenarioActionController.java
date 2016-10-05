package org.xcolab.portlets.proposals.view.action;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.client.proposals.ProposalUnversionedAttributeClient;
import org.xcolab.client.proposals.pojo.ProposalUnversionedAttribute;
import org.xcolab.enums.ProposalUnversionedAttributeName;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.util.html.HtmlUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("view")
public class UpdateProposalScenarioActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=updateProposalScenario"})
    public void show(ActionRequest request, Model model,
            ActionResponse response,
                     @RequestParam long scenarioId,
                     @RequestParam long modelId,
                     @RequestParam(required = false) String region,
                     @RequestParam(required = false) String impactAuthorComment,
                     @RequestParam(required = false) String impactIAFComment,
                     @RequestParam(required = false) Boolean isConsolidatedScenario)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {


        if (proposalsContext.getProposal(request) != null && !canEditImpactTab(request)){
            throw new ProposalsAuthorizationException("User is not allowed to edit proposal, user: " +
                    proposalsContext.getUser(request).getUserId() + ", proposal: " + proposalsContext.getProposal(request).getProposalId());
        }

        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);
        Long consolidatedScenario = Validator.isNotNull(isConsolidatedScenario) && isConsolidatedScenario ? 1L : 0L;
        proposal.setScenarioId(scenarioId, consolidatedScenario, proposalsContext.getUser(request).getUserId());
        if(!Validator.isBlank(region)){
            proposal.setModelRegion(region, proposalsContext.getUser(request).getUserId());
        }

        List<ProposalUnversionedAttribute> unversionedAttributes = ProposalUnversionedAttributeClient.
                getProposalUnversionedAttributesByProposalId(proposal.getProposalId());

        if(impactAuthorComment!=null||impactIAFComment!=null) {
              if(impactAuthorComment!=null) {

                  ProposalUnversionedAttributeClient.createOrUpdateProposalUnversionedAttribute(proposalsContext.getUser(request).getUserId(),
                          HtmlUtil.cleanAll(impactAuthorComment),
                          ProposalUnversionedAttributeName.IMPACT_AUTHOR_COMMENT.toString(),
                          proposal.getProposalId());
                }
                if(impactIAFComment!=null) {
                    ProposalUnversionedAttributeClient.createOrUpdateProposalUnversionedAttribute(proposalsContext.getUser(request).getUserId(), HtmlUtil.cleanAll(impactIAFComment),
                            ProposalUnversionedAttributeName.IMPACT_IAF_COMMENT.toString(),
                            proposal.getProposalId());
                }
        }
        proposalsContext.invalidateContext(request);
        response.sendRedirect(proposal.getProposalUrl() + "/tab/IMPACT");
    }



    private boolean canEditImpactTab(PortletRequest request) throws PortalException, SystemException{
        return ProposalTab.IMPACT.getCanEdit(proposalsContext.getPermissions(request), proposalsContext, request);
    }

}
