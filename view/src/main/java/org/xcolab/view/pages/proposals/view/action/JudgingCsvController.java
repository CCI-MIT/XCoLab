package org.xcolab.view.pages.proposals.view.action;

import au.com.bytecode.opencsv.CSVWriter;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.IContestTeamMemberClient;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalJudgeRatingClient;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.view.pages.proposals.judging.ProposalReview;
import org.xcolab.view.pages.proposals.judging.ProposalReviewCsvExporter;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.xcolab.view.util.entity.EntityIdListUtil.MEMBERS;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class JudgingCsvController {

    @Autowired
    private IContestTeamMemberClient contestTeamMemberClient;

    @Autowired
    private IProposalJudgeRatingClient proposalJudgeRatingClient;

    @GetMapping({"phase/{phaseId}/{proposalUrlString}/{proposalId}/tab/ADVANCING/getJudgingCsv",
            "c/{proposalUrlString}/{proposalId}/tab/ADVANCING/getJudgingCsv"})
    public void getJudgingCsv(HttpServletRequest request, HttpServletResponse response,
            ProposalContext proposalContext, UserWrapper currentMember) {

        ProposalsPermissions permissions = proposalContext.getPermissions();
        // Security handling
        if (!(permissions.getCanFellowActions() && proposalContext.getProposal()
                .isUserAmongFellows(currentMember.getId())) && !permissions.getCanAdminAll()
                && !permissions.getCanJudgeActions() && !permissions
                .getCanContestManagerActions()) {
            return;
        }

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String csvPayload = getProposalJudgeReviewCsv(proposalContext.getContest(),
                    proposalContext.getContestPhase(), proposalContext);

            String separatorIndicationForExcel =
                    "sep=" + CSVWriter.DEFAULT_SEPARATOR + CSVWriter.DEFAULT_LINE_END;
            csvPayload = separatorIndicationForExcel + csvPayload;
            outputStream.write(csvPayload.getBytes());
            response.setContentType("application/csv");
            response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv");

            response.setContentLength(outputStream.size());
            OutputStream out = response.getOutputStream();
            outputStream.writeTo(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new InternalException(e);
        }
    }

    private String getProposalJudgeReviewCsv(ContestWrapper contest, ContestPhaseWrapper currentPhase,
            ProposalContext proposalContext) {

        Map<ProposalWrapper, List<ProposalReview>> proposalToProposalReviewsMap = new HashMap<>();

        List<ProposalWrapper> stillActiveProposals = proposalContext.getClients().getProposalClient()
                .getActiveProposalsInContestPhase(currentPhase.getId());
        Set<IProposalRatingType> occurringRatingTypes = new HashSet<>();

        for (ContestPhaseWrapper judgingPhase : contest.getPhases()) {
            if (!judgingPhase.getFellowScreeningActive()) {
                continue;
            }

            for (ProposalWrapper proposal : stillActiveProposals) {
                IProposalContestPhaseAttribute fellowActionAttribute =
                        StaticProposalContext.getProposalPhaseClient()
                        .getProposalContestPhaseAttribute(proposal.getId(), judgingPhase.getId(),
                                ProposalContestPhaseAttributeKeys.FELLOW_ACTION);

                if (fellowActionAttribute != null) {
                    JudgingSystemActions.FellowAction fellowAction =
                            JudgingSystemActions.FellowAction
                                    .fromInt(fellowActionAttribute.getNumericValue().intValue());
                    // Ignore proposals that have not been passed to judge
                    if (fellowAction != JudgingSystemActions.FellowAction.PASS_TO_JUDGES
                            && judgingPhase.getFellowScreeningActive()) {
                        continue;
                    }

                    final String proposalUrl = PlatformAttributeKey.COLAB_URL.get() + proposal
                            .getProposalLinkUrl(contest, judgingPhase.getId());
                    final ProposalReview proposalReview =
                            new ProposalReview(proposal, judgingPhase, proposalUrl);
                    proposalReview.setReviewers(ImmutableSet
                            .copyOf(getProposalReviewingJudges(proposal, judgingPhase,
                                    proposalContext)));
                    List<ProposalRatingWrapper> ratings = proposalJudgeRatingClient
                            .getJudgeRatingsForProposal(proposal.getId(),
                                    judgingPhase.getId());
                    Map<IProposalRatingType, List<Long>> ratingsPerType = new HashMap<>();

                    for (ProposalRatingWrapper rating : ratings) {
                        org.xcolab.view.pages.proposals.judging.ProposalRatingWrapper ratingWrapper = new org.xcolab.view.pages.proposals.judging.ProposalRatingWrapper(rating);
                        ratingsPerType.computeIfAbsent(ratingWrapper.getRatingType(),
                                k -> new ArrayList<>());
                        ratingsPerType.get(ratingWrapper.getRatingType())
                                .add(ratingWrapper.getRatingValue().getValue());

                        proposalReview.addUserRating(ratingWrapper.getUser(),
                                ratingWrapper.getRatingType(),
                                ratingWrapper.getRatingValue().getValue());

                        occurringRatingTypes.add(ratingWrapper.getRatingType());
                        if (rating.isCommentEnabled()) {
                            proposalReview.addReview(ratingWrapper.getUser(), rating.getComment());
                        }
                        if (StringUtils.isNotBlank(rating.getOtherDataString())) {
                            proposalReview.addShouldAdvanceDecision(ratingWrapper.getUser(),
                                    Boolean.parseBoolean(rating.getOtherDataString()));
                        }
                    }

                    //take the average for each type
                    for (IProposalRatingType key : ratingsPerType.keySet()) {
                        double sum = 0;
                        int count = 0;
                        for (Long val : ratingsPerType.get(key)) {
                            sum += val;
                            count++;
                        }
                        double avg = sum / count;
                        proposalReview.addRatingAverage(key, avg);
                    }

                    proposalToProposalReviewsMap.computeIfAbsent(proposal, k -> new ArrayList<>());

                    proposalToProposalReviewsMap.get(proposal).add(proposalReview);
                }
            }
        }

        ProposalReviewCsvExporter csvExporter =
                new ProposalReviewCsvExporter(contest, proposalToProposalReviewsMap,
                        new ArrayList<>(occurringRatingTypes));

        return csvExporter.getCsvString();
    }

    private List<UserWrapper> getProposalReviewingJudges(ProposalWrapper proposal, ContestPhaseWrapper judgingPhase,
            ProposalContext proposalContext) {

        final ClientHelper clients = proposalContext.getClients();
        final IProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();


        if (judgingPhase.getFellowScreeningActive()) {
            final IProposalContestPhaseAttribute selectedJudgesAttribute = proposalPhaseClient
                    .getProposalContestPhaseAttribute(proposal.getId(),
                            judgingPhase.getId(),
                            ProposalContestPhaseAttributeKeys.SELECTED_JUDGES);
            if (selectedJudgesAttribute == null) {
                throw new IllegalStateException("Fellow screening active,"
                        + " but no judges were selected for this contest.");
            }

            final String judgeIdString = selectedJudgesAttribute.getStringValue();
            return MEMBERS.fromIdString(judgeIdString);

        } else {
            // Choose all judges
            List<Long> judgeIds = contestTeamMemberClient
                    .getJudgesForContest(judgingPhase.getContestId());
            return MEMBERS.fromIdList(judgeIds);
        }
    }
}
