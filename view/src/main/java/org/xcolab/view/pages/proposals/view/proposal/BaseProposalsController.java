package org.xcolab.view.pages.proposals.view.proposal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.util.MetaKeys;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseProposalsController {

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
