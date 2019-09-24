package org.xcolab.view.pages.proposals.view.proposal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.IImpactClient;
import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalJudgeRatingClient;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.util.MetaKeys;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseProposalsController {

    @Autowired
    protected IContestClient contestClient;

    @Autowired
    protected IImpactClient impactClient;

    @Autowired
    protected IOntologyClient ontologyClient;

    @Autowired
    protected IProposalPhaseClient proposalPhaseClient;

    @Autowired
    protected IProposalClient proposalClient;

    @Autowired
    protected IProposalJudgeRatingClient proposalJudgeRatingClient;

    public BaseProposalsController() {
        super();
    }

    protected void setSeoTexts(HttpServletRequest request, String pageDescription) {

        if (StringUtils.isNotBlank(pageDescription)) {
            request.setAttribute(MetaKeys.DESCRIPTION.getAttributeName(), pageDescription);
        }
    }

    protected void setBasePageAttributes(ProposalContext proposalContext, Model model) {
        final ContestType contestType = proposalContext.getContestType();
        if (contestType != null) {
            setActivePageLink(model, contestType);
        }
    }

    protected void setActivePageLink(Model model, ContestType contestType) {
        model.addAttribute("_activePageLink",
                contestType.getIdentifier());
    }
}
