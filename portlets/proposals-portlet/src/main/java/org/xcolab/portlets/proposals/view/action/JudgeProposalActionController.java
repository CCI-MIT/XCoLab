package org.xcolab.portlets.proposals.view.action;

import au.com.bytecode.opencsv.CSVWriter;
import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;


import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.entity.utils.portlet.PortletUtil;
import org.xcolab.entity.utils.portlet.session.SessionErrors;
import org.xcolab.entity.utils.portlet.session.SessionMessages;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.portlets.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.portlets.proposals.requests.ProposalAdvancingBean;
import org.xcolab.portlets.proposals.requests.RatingBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.entity.utils.judging.ProposalJudgingCommentHelper;
import org.xcolab.entity.utils.judging.ProposalReview;
import org.xcolab.entity.utils.judging.ProposalReviewCsvExporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("view")
public class JudgeProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=saveAdvanceDetails"})
    public void saveAdvanceDetails(ActionRequest request, Model model, ActionResponse response,
            @Valid ProposalAdvancingBean proposalAdvancingBean, BindingResult result)
            throws IOException {
        Proposal proposal = proposalsContext.getProposal(request);
        final Contest contest = proposalsContext.getContest(request);
        long proposalId = proposal.getProposalId();
        ContestPhase contestPhase = ContestClientUtil.getContestPhase(proposalAdvancingBean.getContestPhaseId());
        Member currentMember = proposalsContext.getMember(request);
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("pageToDisplay", "proposalDetails_ADVANCING");
            return;
        }

        // Security handling
        if (!(permissions.getCanFellowActions() && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentMember.getUserId())) &&
                !permissions.getCanAdminAll()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("pageToDisplay", "proposalDetails_ADVANCING");
            return;
        }

        //check if advancement was frozen
        boolean isFrozen = proposalsContext.getClients(request).getProposalPhaseClient().isProposalContestPhaseAttributeSetAndTrue(proposalId,
                contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN);

        boolean isUndecided = (proposalAdvancingBean.getAdvanceDecision()
                == JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue());

        //disallow saving when frozen for non-admins
        if (isFrozen && !permissions.getCanAdminAll()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("pageToDisplay", "proposalDetails_ADVANCING");
            return;
        }

        // save judge decision

        proposalsContext.getClients(request).getProposalPhaseClient().setProposalContestPhaseAttribute(proposalId,
                contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION,
                0L, (long) proposalAdvancingBean.getAdvanceDecision(), null);

        // save judge comment
        if (!isUndecided) {
            ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);

            commentHelper.setAdvancingComment(proposalAdvancingBean.getAdvanceComment());
        }

        //freeze the advancement
        if (!isUndecided && request.getParameter("isFreeze") != null
                && request.getParameter("isFreeze").equals("true")) {
            ProposalPhaseClientUtil
                    .setProposalContestPhaseAttribute(proposalId, contestPhase.getContestPhasePK(),
                    ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN, 0L, null, "true");
        }

        //unfreeze the advancement
        if (permissions.getCanAdminAll() && request.getParameter("isUnfreeze") != null
                && request.getParameter("isUnfreeze").equals("true")) {
            ProposalPhaseClientUtil
                    .setProposalContestPhaseAttribute(proposalId, contestPhase.getContestPhasePK(),
                    ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN, 0L, null,"false");
        }

        //forcefully promote the advancement
        if (permissions.getCanAdminAll() && !isUndecided && request.getParameter("isForcePromotion") != null && request.getParameter("isForcePromotion").equals("true")) {
            ContestClientUtil.forcePromotionOfProposalInPhase(proposal.getProposalId(), contestPhase.getContestPhasePK());
        }
        response.sendRedirect(proposal.getProposalLinkUrl(contest, contestPhase.getContestPhasePK()) + "/tab/ADVANCING");
    }

    @ResourceMapping("getJudgingCsv")
    public void getJudgingCsv(ResourceRequest request, ResourceResponse response) {

        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        Member currentMember = proposalsContext.getMember(request);
        // Security handling
        if (!(permissions.getCanFellowActions() && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentMember.getUserId())) &&
                !permissions.getCanAdminAll() && !permissions.getCanJudgeActions() && !permissions.getCanContestManagerActions()) {
            return;
        }



        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String csvPayload = getProposalJudgeReviewCsv(proposalsContext.getContest(request),
                    proposalsContext.getContestPhase(request), request);

            String separatorIndicationForExcel =  "sep=" + CSVWriter.DEFAULT_SEPARATOR + CSVWriter.DEFAULT_LINE_END;
            csvPayload = separatorIndicationForExcel + csvPayload;
            outputStream.write(csvPayload.getBytes());
            response.setContentType("application/csv");
            response.addProperty(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
            response.setProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv");

            response.setContentLength(outputStream.size());
            OutputStream out = response.getPortletOutputStream();
            outputStream.writeTo(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new InternalException(e);
        }
    }

    public String getProposalJudgeReviewCsv(Contest contest, ContestPhase currentPhase, PortletRequest request)  {
        Map<Proposal,List<ProposalReview>> proposalToProposalReviewsMap = new HashMap<>();

        List<Proposal> stillActiveProposals = ProposalsContextUtil.getClients(request).getProposalClient().getActiveProposalsInContestPhase(currentPhase.getContestPhasePK());
        Set<ProposalRatingType> occurringRatingTypes = new HashSet<>();
        Set<Member> occurringJudges = new HashSet<>();

        for (ContestPhase judgingPhase : ContestClientUtil.getAllContestPhases(contest.getContestPK())) {
            if(!judgingPhase.getFellowScreeningActive()){
                continue;
            }
            for (Proposal proposal : stillActiveProposals) {
                    ProposalContestPhaseAttribute fellowActionAttribute = ProposalPhaseClientUtil
                            .getProposalContestPhaseAttribute( proposal.getProposalId(),judgingPhase.getContestPhasePK(),
                                    ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
                    JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) fellowActionAttribute.getNumericValue().intValue());

                    // Ignore proposals that have not been passed to judge
                    if (fellowAction != JudgingSystemActions.FellowAction.PASS_TO_JUDGES && judgingPhase.getFellowScreeningActive()) {
                        continue;
                    }


                final String proposalUrl = ConfigurationAttributeKey.COLAB_URL.get() + proposal.getProposalLinkUrl(contest, judgingPhase.getContestPhasePK());
                final ProposalReview proposalReview = new ProposalReview(proposal, judgingPhase, proposalUrl);
                proposalReview.setReviewers(ImmutableSet.copyOf(getProposalReviewingJudges(proposal, judgingPhase,
                        request)));
                List<ProposalRating> ratings = ProposalJudgeRatingClientUtil
                        .getJudgeRatingsForProposal(proposal.getProposalId(), judgingPhase.getContestPhasePK());
                Map<ProposalRatingType, List<Long>> ratingsPerType = new HashMap<>();

                for (ProposalRating rating: ratings) {
                    org.xcolab.entity.utils.judging.ProposalRatingWrapper wrapper = new org.xcolab.entity.utils.judging.ProposalRatingWrapper(rating);
                    if (ratingsPerType.get(wrapper.getRatingType()) == null) {
                        ratingsPerType.put(wrapper.getRatingType(), new ArrayList<Long>());
                    }
                    ratingsPerType.get(wrapper.getRatingType()).add(wrapper.getRatingValue().getValue());

                    proposalReview.addUserRating(wrapper.getUser(),wrapper.getRatingType(),wrapper.getRatingValue().getValue());

                    occurringRatingTypes.add(wrapper.getRatingType());
                    if (rating.getCommentEnabled()) {
                        proposalReview.addReview(wrapper.getUser(), rating.getComment_());
                        occurringJudges.add(wrapper.getUser());
                    }
                }

                //take the average for each type
                for (ProposalRatingType key : ratingsPerType.keySet()) {
                    double sum = 0;
                    int count = 0;
                    for (Long val: ratingsPerType.get(key)) {
                        sum += val;
                        count++;
                    }
                    double avg = sum/count;
                    proposalReview.addRatingAverage(key, avg);
                }

                if ((proposalToProposalReviewsMap.get(proposal)==null)) {
                    proposalToProposalReviewsMap.put(proposal, new ArrayList<ProposalReview>());
                }

                proposalToProposalReviewsMap.get(proposal).add(proposalReview);
            }
        }

        ProposalReviewCsvExporter csvExporter = new ProposalReviewCsvExporter(proposalToProposalReviewsMap, new ArrayList<>(occurringRatingTypes));

        return csvExporter.getCsvString();
    }

    private List<Member> getProposalReviewingJudges(Proposal proposal, ContestPhase judgingPhase,
            PortletRequest request) {
        List<Member> selectedJudges = null;

        if (judgingPhase.getFellowScreeningActive()) {
                final String judgeIdString = proposalsContext.getClients(request).getProposalPhaseClient().
                        getProposalContestPhaseAttribute(proposal.getProposalId(), judgingPhase.getContestPhasePK(),
                                ProposalContestPhaseAttributeKeys.SELECTED_JUDGES).getStringValue();

                selectedJudges = new ArrayList<>();

                for (String element : judgeIdString.split(";")) {

                    try {
                        long userId = Long.parseLong(element);
                        Member judge = MembersClient.getMember(userId);
                        selectedJudges.add(judge);
                    }catch (MemberNotFoundException|NumberFormatException ignore){

                    }
                }
        }
        // Choose all judges
        else {
            try {
                List<Long> members = ContestTeamMemberClientUtil.getJudgesForContest(judgingPhase.getContestPK());
                selectedJudges = new ArrayList<>();
                for(Long memberId : members){
                    selectedJudges.add(MembersClient.getMember(memberId));
                }
            }catch (MemberNotFoundException ignored){

            }
        }

        return selectedJudges;
    }

    @RequestMapping(params = {"action=saveJudgingFeedback"})
    public void saveJudgingFeedback(ActionRequest request, Model model, ActionResponse response,
                                    @Valid JudgeProposalFeedbackBean judgeProposalFeedbackBean,
                                    BindingResult result)
            throws IOException {

        if (result.hasErrors()) {
            return;
        }

        final Contest contest = proposalsContext.getContest(request);
        Proposal proposal = proposalsContext.getProposalWrapped(request);
        ContestPhase contestPhase = ContestClientUtil.getContestPhase(judgeProposalFeedbackBean.getContestPhaseId());
        Member member = proposalsContext.getMember(request);
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        Boolean isPublicRating = permissions.getCanPublicRating();

        if (judgeProposalFeedbackBean.getScreeningUserId() != null) {

            member = MembersClient.getMemberUnchecked(judgeProposalFeedbackBean.getScreeningUserId());
        }

        // Security handling
        if (!(permissions.getCanJudgeActions() && proposal.isUserAmongSelectedJudge(member) || isPublicRating)) {
            return;
        }

        if(permissions.getCanJudgeActions() && proposal.isUserAmongSelectedJudge(member)){
            isPublicRating = false;
        }

        //find existing ratings
        List<ProposalRating> existingRatings = ProposalJudgeRatingClientUtil.getJudgeRatingsForProposalAndUser(
                member.getUserId(),
                proposal.getProposalId(),
                contestPhase.getContestPhasePK());

        this.saveRatings(existingRatings, judgeProposalFeedbackBean, proposal.getProposalId(),
                contestPhase.getContestPhasePK(), member.getUserId(), isPublicRating);

        response.sendRedirect(proposal.getWrapped().getProposalLinkUrl(contest,
                 contestPhase.getContestPhasePK()));
    }

    @RequestMapping(params = {"action=saveScreening"})
    public void saveScreening(ActionRequest request, Model model,
            ActionResponse response,
            @ModelAttribute("fellowProposalScreeningBean") @Valid FellowProposalScreeningBean fellowProposalScreeningBean,
            BindingResult result)
            throws ProposalsAuthorizationException, IOException {
        try {
            if (result.hasErrors()) {
                SessionErrors.clear(request);
                SessionMessages.clear(request);
                return;
            }

            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long contestPhaseId = fellowProposalScreeningBean.getContestPhaseId();
            ProposalsPermissions permissions = proposalsContext.getPermissions(request);
            Member currentMember = proposalsContext.getMember(request);

            // Security handling
            if (!(permissions.getCanFellowActions()
                    && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentMember.getUserId()))
                    && !permissions.getCanAdminAll()) {
                return;
            }

            // save selection of judges
            if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions
                    .FellowAction.PASS_TO_JUDGES.getAttributeValue()) {
                proposalsContext.getClients(request).getProposalPhaseClient().persistSelectedJudgesAttribute(
                        proposalId,
                        contestPhaseId,
                        fellowProposalScreeningBean.getSelectedJudges()
                );
            } else {
                //clear selected judges attribute since the decision is not to pass the proposal.
                proposalsContext.getClients(request).getProposalPhaseClient().persistSelectedJudgesAttribute(
                        proposalId,
                        contestPhaseId,
                        null
                );
            }

            // save fellow action
            if (fellowProposalScreeningBean.getFellowScreeningAction()!= 0) {
                proposalsContext.getClients(request).getProposalPhaseClient().setProposalContestPhaseAttribute(
                        proposalId,
                        contestPhaseId,
                        ProposalContestPhaseAttributeKeys.FELLOW_ACTION,
                        0L,
                        (long) fellowProposalScreeningBean.getFellowScreeningAction(),
                        null);

                //save fellow action comment
                ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposalsContext.getProposal(request), ContestClientUtil

                        .getContestPhase(contestPhaseId));

                if (fellowProposalScreeningBean.getFellowScreeningAction()
                        == JudgingSystemActions.FellowAction.INCOMPLETE.getAttributeValue()
                        || fellowProposalScreeningBean.getFellowScreeningAction()
                        == JudgingSystemActions.FellowAction.OFFTOPIC.getAttributeValue()
                        || fellowProposalScreeningBean.getFellowScreeningAction()
                        == JudgingSystemActions.FellowAction.NOT_ADVANCE_OTHER.getAttributeValue()) {
                    commentHelper.setScreeningComment(fellowProposalScreeningBean.getFellowScreeningActionCommentBody());
                }
            }

            // save fellow comment and rating
            //find existing ratings
            List<ProposalRating> existingRatings = ProposalJudgeRatingClientUtil.getFellowRatingForProposalAndUser(
                    currentMember.getUserId(),
                    proposalId,
                    contestPhaseId);

            this.saveRatings(existingRatings, fellowProposalScreeningBean, proposalId, contestPhaseId, currentMember.getUserId(), false);
            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request),
                     proposalsContext.getContestPhase(request).getContestPhasePK()) + "/tab/SCREENING");
        } catch (Exception e) {
            //TODO: do we still want this?
            List<Long> recipientIds = new ArrayList<>();
            recipientIds.add(1451771L); //Manuel
            recipientIds.add(1011659L); //Patrick
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            MessagingClient.sendMessage("Exception thrown when fellow rated proposal",
                    e.getMessage()+"\n\n"+exceptionAsString, 1010458L, 1010458L, recipientIds);
            throw e;
        }
    }


    private void saveRatings(List<ProposalRating> existingRatings, RatingBean ratingBean,
            long proposalId, long contestPhaseId, long currentUserId, boolean isPublicRating) {
        //initialize a map of existing ratings
        Map<Long, ProposalRating> typeToRatingMap = new HashMap<>();
        for (ProposalRating r: existingRatings) {
            typeToRatingMap.put(r.getRatingTypeId(), r);
        }

        Map<Long, String> ratingsFromForm = ratingBean.getRatingValues();
        //now update the values and save or create a new rating of not existent yet
        if (ratingsFromForm != null) {
            boolean commentAdded = false;
            for (Map.Entry<Long, String> entry : ratingsFromForm.entrySet()) {
                ProposalRating existingRating = typeToRatingMap.get(entry.getKey());
                long newRatingValueId = Long.parseLong(entry.getValue());

                if (existingRating != null) {
                    //rating exists. update and save
                    existingRating.setRatingValueId(newRatingValueId);
                    //convention: save comment in first type
                    if (!commentAdded) {
                        existingRating.setComment_(ratingBean.getComment());
                        existingRating.setCommentEnabled(true);
                        commentAdded = true;
                    } else {
                        existingRating.setComment_(null);
                        existingRating.setCommentEnabled(false);
                    }
                    ProposalJudgeRatingClientUtil.updateProposalRating(existingRating);
                } else {
                    String comment = null;
                    if (!commentAdded) {
                        comment = ratingBean.getComment();
                        commentAdded = true;
                    }
                    //create a new rating
                    ProposalRating proposalRating = new ProposalRating();
                    proposalRating.setProposalId(proposalId);
                    proposalRating.setContestPhaseId(contestPhaseId);
                    proposalRating.setUserId(currentUserId);
                    proposalRating.setRatingValueId(newRatingValueId);
                    proposalRating.setComment_(comment);
                    proposalRating.setOtherDataString("");
                    if (comment != null && !comment.isEmpty()) {
                        proposalRating.setCommentEnabled(true);
                    }else{
                        proposalRating.setCommentEnabled(false);
                    }
                    proposalRating.setOnlyForInternalUsage(isPublicRating);

                    proposalRating = ProposalJudgeRatingClientUtil.createProposalRating(proposalRating);

                }
            }
        }
    }


}
