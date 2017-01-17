package org.xcolab.view.pages.proposals.view.action;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttribute;
import org.xcolab.entity.utils.enums.ProposalUnversionedAttributeName;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalTab;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UpdateProposalScenarioActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    //-- @RequestMapping(params = {"action=updateProposalScenario"})
    @PostMapping("/proposals/impact/updateProposalScenario")
    public void show(HttpServletRequest request, Model model,
            HttpServletResponse response,
                     @RequestParam long scenarioId,
                     @RequestParam long modelId,
                     @RequestParam(required = false) String region,
                     @RequestParam(required = false) String impactAuthorComment,
                     @RequestParam(required = false) String impactIAFComment,
                     @RequestParam(required = false) Boolean isConsolidatedScenario)
            throws ProposalsAuthorizationException, IOException {

        if (proposalsContext.getProposal(request) != null && !canEditImpactTab(request)){
            throw new ProposalsAuthorizationException("User is not allowed to edit proposal, user: "
                    + proposalsContext.getMember(request).getUserId() + ", proposal: "
                    + proposalsContext.getProposal(request).getProposalId());
        }

        Proposal proposal = proposalsContext.getProposalWrapped(request);
        Long consolidatedScenario = isConsolidatedScenario != null && isConsolidatedScenario ? 1L : 0L;
        proposal.setScenarioId(scenarioId, consolidatedScenario, proposalsContext.getMember(request).getUserId());

        if (StringUtils.isNotBlank(region)) {
            proposal.setModelRegion(region, proposalsContext.getMember(request).getUserId());
        }

        ProposalAttributeClient proposalAttributeClient = proposalsContext.getClients(request)
                .getProposalAttributeClient();

        List<ProposalUnversionedAttribute> unversionedAttributes = proposalAttributeClient.
                getProposalUnversionedAttributesByProposalId(proposal.getProposalId());

        if (impactAuthorComment != null || impactIAFComment != null) {
              if (impactAuthorComment != null) {

                  proposalAttributeClient.createOrUpdateProposalUnversionedAttribute(proposalsContext.getMember(request).getUserId(),
                          HtmlUtil.cleanAll(impactAuthorComment),
                          ProposalUnversionedAttributeName.IMPACT_AUTHOR_COMMENT.toString(),
                          proposal.getProposalId());
                }
                if (impactIAFComment != null) {
                    proposalAttributeClient.createOrUpdateProposalUnversionedAttribute(
                            proposalsContext.getMember(request).getUserId(),
                            HtmlUtil.cleanAll(impactIAFComment),
                            ProposalUnversionedAttributeName.IMPACT_IAF_COMMENT.toString(),
                            proposal.getProposalId());
                }
        }
        proposalsContext.invalidateContext(request);
        response.sendRedirect(proposal.getProposalUrl() + "/tab/IMPACT");
    }

    private boolean canEditImpactTab(HttpServletRequest request) {
        return ProposalTab.IMPACT.getCanEdit(
                proposalsContext.getPermissions(request), proposalsContext, request);
    }
}
