package org.xcolab.view.pages.proposals.view.proposal.json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;

import java.io.IOException;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProposalVersionsJsonController {

    private static final long MILLISECONDS_TO_GROUP_VERSIONS = 1000 * 60;

    @GetMapping("/api/phases/{phaseId}/proposals/{proposalId}/versionsFirstIndex")
    public void getProposalVersionFirstIndex(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("phaseId") long contestPhaseId, @PathVariable("proposalId") long proposalId)
            throws IOException {
        Proposal2Phase p2p = null;
        if (contestPhaseId > 0) {
            p2p = ProposalPhaseClientUtil
                    .getProposal2PhaseByProposalIdContestPhaseId(proposalId, contestPhaseId);
        }

        int index = 0;
        Date oldDate = new Date();
        for (ProposalVersion proposalVersion : ProposalClientUtil.getAllProposalVersions(proposalId)) {
            if (p2p == null || p2p.getVersionTo() == -1
                    || (proposalVersion.getVersion() >= p2p.getVersionFrom() && (proposalVersion.getVersion() < p2p.getVersionTo() ))
                    ) {
                break;
            }

            if (Math.abs(oldDate.getTime() - proposalVersion.getCreatedAt().getTime()) > MILLISECONDS_TO_GROUP_VERSIONS){
                index++;
                oldDate = proposalVersion.getCreatedAt();
            }
        }


        final JsonObject json = Json.createObjectBuilder()
                .add("index", index).build();
        response.getOutputStream().write(json.toString().getBytes());
    }


    @GetMapping("/api/proposals/{proposalId}/versions/{version}/count")
    public void getProposalVersionIndex(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("version") Integer version, @PathVariable("proposalId") Long proposalId) throws IOException {


        Contest c = ProposalClientUtil.getCurrentContestForProposal(proposalId);

        ClientHelper clientHelper = new ClientHelper();
        int index = clientHelper.getProposalClient()
                .countProposalVersionsGroupedVersionsByContest(proposalId, c.getContestPK());

        final JsonObject json = Json.createObjectBuilder()
                .add("count", index).build();
        response.getOutputStream().write(json.toString().getBytes());
    }


    @GetMapping("/api/contests/{contestId}/phases/{phaseId}/proposals/{proposalId}/versions")
    public void getProposalVersions(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("contestId") long contestId, @PathVariable("phaseId") long contestPhaseId,
            @PathVariable("proposalId") long proposalId, @RequestParam int start, @RequestParam int end)
            throws IOException {

        Proposal2Phase p2p = null;
        Contest contest = ContestClientUtil.getContest(contestId);
        ClientHelper clientHelper = new ClientHelper();
        ProposalPhaseClient proposalPhaseClient = clientHelper.getProposalPhaseClient();
        ProposalClient proposalClient = clientHelper.getProposalClient();
        ContestClient contestClient = clientHelper.getContestClient();



        final JsonArrayBuilder proposalVersionsArray = Json.createArrayBuilder();
        int counter = 0;
        for(ProposalVersion proposalVersion: clientHelper.getProposalClient().getProposalVersionsGroupedVersionsByContest(proposalId,contest.getContestPK(),start, end)){

                long cphId = proposalVersion.getContestPhaseId();
                final ContestPhase contestPhase = contestClient.getContestPhase(cphId);
                Member author = Member.fromId(proposalVersion.getauthorUserid());
                proposalVersionsArray.add(Json.createObjectBuilder()
                        .add("version", proposalVersion.getVersion())
                        .add("date", proposalVersion.getCreatedAt().getTime())
                        .add("author", Json.createObjectBuilder()
                                .add("userId", author.getId())
                                .add("screenName", author.getScreenName())
                                .add("fullName", author.getFullName()))
                        .add("updateType", proposalVersion.getUpdateType())
                        .add("contestPhase", Json.createObjectBuilder()
                                .add("id", contestPhase.getContestPhasePK())
                                .add("name", contestPhase.getName())));

                counter++;
        }


        final JsonObject json = Json.createObjectBuilder()
                .add("proposalId", proposalId)
                .add("start", start)
                .add("end", end)
                .add("totalCount", counter)
                .add("versions", proposalVersionsArray)
                .build();

        response.getOutputStream().write(json.toString().getBytes());
    }
}
