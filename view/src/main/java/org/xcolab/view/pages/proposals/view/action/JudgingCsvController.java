package org.xcolab.view.pages.proposals.view.action;

import au.com.bytecode.opencsv.CSVWriter;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.view.pages.proposals.judging.ProposalRatingWrapper;
import org.xcolab.view.pages.proposals.judging.ProposalReview;
import org.xcolab.view.pages.proposals.judging.ProposalReviewCsvExporter;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
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

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class JudgingCsvController {

    @GetMapping({"phase/{phaseId}/{proposalUrlString}/{proposalId}/tab/ADVANCING/getJudgingCsv",
            "c/{proposalUrlString}/{proposalId}/tab/ADVANCING/getJudgingCsv"})
    public void getJudgingCsv(HttpServletRequest request, HttpServletResponse response,
            ProposalContext proposalContext, Member currentMember) {

        ProposalsPermissions permissions = proposalContext.getPermissions();
        // Security handling
        if (!(permissions.getCanFellowActions() && proposalContext.getProposal().isUserAmongFellows(currentMember.getUserId())) &&
                !permissions.getCanAdminAll() && !permissions.getCanJudgeActions() && !permissions.getCanContestManagerActions()) {
            return;
        }

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String csvPayload = getProposalJudgeReviewCsv(proposalContext.getContest(),
                    proposalContext.getContestPhase(), proposalContext);

            String separatorIndicationForExcel =  "sep=" + CSVWriter.DEFAULT_SEPARATOR + CSVWriter.DEFAULT_LINE_END;
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

    private String getProposalJudgeReviewCsv(Contest contest, ContestPhase currentPhase,
            ProposalContext proposalContext)  {

        Map<Proposal,List<ProposalReview>> proposalToProposalReviewsMap = new HashMap<>();

        List<Proposal> stillActiveProposals = proposalContext.getClients().getProposalClient().getActiveProposalsInContestPhase(currentPhase.getContestPhasePK());
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

                if(fellowActionAttribute!=null) {
                    JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt(fellowActionAttribute.getNumericValue().intValue());
                    // Ignore proposals that have not been passed to judge
                    if (fellowAction != JudgingSystemActions.FellowAction.PASS_TO_JUDGES
                            && judgingPhase.getFellowScreeningActive()) {
                        continue;
                    }


                    final String proposalUrl = PlatformAttributeKey.PLATFORM_COLAB_URL.get() + proposal
                            .getProposalLinkUrl(contest, judgingPhase.getContestPhasePK());
                    final ProposalReview proposalReview =
                            new ProposalReview(proposal, judgingPhase, proposalUrl);
                    proposalReview.setReviewers(
                            ImmutableSet.copyOf(getProposalReviewingJudges(proposal, judgingPhase, proposalContext)));
                    List<ProposalRating> ratings = ProposalJudgeRatingClientUtil
                            .getJudgeRatingsForProposal(proposal.getProposalId(),
                                    judgingPhase.getContestPhasePK());
                    Map<ProposalRatingType, List<Long>> ratingsPerType = new HashMap<>();

                    for (ProposalRating rating : ratings) {
                        ProposalRatingWrapper wrapper =
                                new ProposalRatingWrapper(rating);
                        ratingsPerType.computeIfAbsent(wrapper.getRatingType(),
                                k -> new ArrayList<>());
                        ratingsPerType.get(wrapper.getRatingType())
                                .add(wrapper.getRatingValue().getValue());

                        proposalReview.addUserRating(wrapper.getUser(), wrapper.getRatingType(),
                                wrapper.getRatingValue().getValue());

                        occurringRatingTypes.add(wrapper.getRatingType());
                        if (rating.getCommentEnabled()) {
                            proposalReview.addReview(wrapper.getUser(), rating.getComment_());
                            occurringJudges.add(wrapper.getUser());
                        }
                        if (StringUtils.isNotBlank(rating.getOtherDataString())) {
                            proposalReview.addShouldAdvanceDecision(wrapper.getUser(),
                                    Boolean.parseBoolean(rating.getOtherDataString()));
                        }
                    }

                    //take the average for each type
                    for (ProposalRatingType key : ratingsPerType.keySet()) {
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

        ProposalReviewCsvExporter
                csvExporter = new ProposalReviewCsvExporter(proposalToProposalReviewsMap, new ArrayList<>(occurringRatingTypes));

        return csvExporter.getCsvString();
    }

    private List<Member> getProposalReviewingJudges(Proposal proposal, ContestPhase judgingPhase,
            ProposalContext proposalContext) {
        List<Member> selectedJudges = null;

        if (judgingPhase.getFellowScreeningActive()) {
            final String judgeIdString = proposalContext.getClients().getProposalPhaseClient().
                    getProposalContestPhaseAttribute(proposal.getProposalId(), judgingPhase.getContestPhasePK(),
                            ProposalContestPhaseAttributeKeys.SELECTED_JUDGES).getStringValue();

            selectedJudges = new ArrayList<>();

            for (String element : judgeIdString.split(";")) {

                try {
                    long userId = Long.parseLong(element);
                    Member judge = MembersClient.getMember(userId);
                    selectedJudges.add(judge);
                }catch (MemberNotFoundException |NumberFormatException ignore){

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
            } catch (MemberNotFoundException ignored) {

            }
        }

        return selectedJudges;
    }

}

