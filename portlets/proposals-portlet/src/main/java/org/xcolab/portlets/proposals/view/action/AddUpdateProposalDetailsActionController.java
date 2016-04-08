package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.ConfigurationAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.enums.ConfigurationAttributeKey;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.utils.edit.ProposalCreationUtil;
import org.xcolab.portlets.proposals.utils.edit.ProposalMoveUtil;
import org.xcolab.portlets.proposals.utils.edit.ProposalUpdateHelper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("view")
public class AddUpdateProposalDetailsActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=updateProposalDetails"})
    public void show(ActionRequest request, Model model,
            ActionResponse response, @Valid UpdateProposalDetailsBean updateProposalSectionsBean, BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {

        final Proposal proposal = proposalsContext.getProposal(request);
        if (proposal != null && !proposalsContext.getPermissions(request).getCanEdit()) {
            throw new ProposalsAuthorizationException("User is not allowed to edit proposal, user: " +
                    proposalsContext.getUser(request).getUserId() + ", proposal: " + proposal.getProposalId());
        }
        final Contest contest = proposalsContext.getContest(request);
        if (proposal == null && !proposalsContext.getPermissions(request).getCanCreate()) {
            throw new ProposalsAuthorizationException("User is not allowed to create proposal, user: " +
                    proposalsContext.getUser(request).getUserId() + ", contest: " + contest
                    .getContestPK());
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
        ProposalWrapper proposalWrapper;
        boolean createNew = false;
        final ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        if (proposal != null) {
            proposalWrapper = proposalsContext.getProposalWrapped(request);
            if (updateProposalSectionsBean.getIsMove() && updateProposalSectionsBean.getMoveToContestId() > 0) {
                ProposalMoveUtil.moveProposal(updateProposalSectionsBean,
                        proposalWrapper, contestPhase, contest, themeDisplay);
            }
        } else {
            createNew = true;
            proposalWrapper = ProposalCreationUtil
                    .createProposal(userId, updateProposalSectionsBean, contest, themeDisplay, contestPhase);
        }

        final Proposal2Phase p2p = proposalsContext.getProposal2Phase(request);
        ProposalUpdateHelper proposalUpdateHelper = new ProposalUpdateHelper(updateProposalSectionsBean, request,
                themeDisplay, proposalWrapper, p2p, userId);
        proposalUpdateHelper.updateProposal();

        if (createNew) {
            ProposalCreationUtil.sendAuthorNotification(themeDisplay, proposalWrapper, contestPhase);
        }

        proposalsContext.invalidateContext(request);

        request.setAttribute("ACTION_REDIRECTING", true);
        response.sendRedirect(proposalWrapper.getProposalUrl());
    }

    @RequestMapping(params = {"action=updateProposalDetails", "error=true"})
    public String reportError(PortletRequest request, Model model,
            @ModelAttribute("updateProposalSectionsBean") @Valid UpdateProposalDetailsBean updateProposalSectionsBean,
            BindingResult result) throws PortalException, SystemException {
        ProposalWrapper proposalWrapped = proposalsContext.getProposalWrapped(request);

        Proposal proposal = ProposalLocalServiceUtil.createProposal(0);
        proposal.setAuthorId(proposalsContext.getUser(request).getUserId());

        if (proposalWrapped == null) {
            proposalWrapped = new ProposalWrapper(proposal, 0, proposalsContext.getContest(request),
                    proposalsContext.getContestPhase(request), null);
            model.addAttribute("proposal", proposalWrapped);
        }

        model.addAttribute("updateProposalSectionsBean",updateProposalSectionsBean);

        request.setAttribute("imageUploadServiceUrl", ConfigurationAttributeLocalServiceUtil.getAttributeStringValue(
                ConfigurationAttributeKey.IMAGE_UPLOAD_EXTERNAL_SERVICE_URL.name(), 0L));
        request.setAttribute("imageUploadHelpText", ConfigurationAttributeLocalServiceUtil.getAttributeStringValue(
                ConfigurationAttributeKey.IMAGE_UPLOAD_HELP_TEXT.name(), 0L));
        return "proposalDetails_edit";
    }
}
