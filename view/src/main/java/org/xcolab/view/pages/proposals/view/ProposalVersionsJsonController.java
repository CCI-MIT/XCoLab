package org.xcolab.view.pages.proposals.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

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
import java.util.Objects;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//-- @RequestMapping("view")
public class ProposalVersionsJsonController {

    private final static long MILLISECONDS_TO_GROUP_VERSIONS = 1000 * 60;

    //TODO: get contest for sharing?
    //-- @ResourceMapping("getProposalVersionFirstIndex")
    public void getProposalVersionFirstIndex(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long contestPhaseId, @RequestParam long proposalId)
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

            if (Math.abs(oldDate.getTime() - proposalVersion.getCreateDate().getTime()) > MILLISECONDS_TO_GROUP_VERSIONS){
                index++;
                oldDate = proposalVersion.getCreateDate();
            }
        }


        final JsonObject json = Json.createObjectBuilder()
                .add("index", index).build();
        response.getOutputStream().write(json.toString().getBytes());
    }

    //-- @ResourceMapping("getProposalVersionIndex")
    public void getProposalVersionIndex(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long version, @RequestParam long proposalId) throws IOException {
        int index = 0;
        Date oldDate = new Date();
        for (ProposalVersion proposalVersion: ProposalClientUtil.getAllProposalVersions(proposalId)) {
            if (proposalVersion.getVersion() == version) {
                break;
            }

            if (Math.abs(oldDate.getTime() - proposalVersion.getCreateDate().getTime()) > MILLISECONDS_TO_GROUP_VERSIONS){
                index++;
                oldDate = proposalVersion.getCreateDate();
            }
        }

        final JsonObject json = Json.createObjectBuilder()
                .add("index", index).build();
        response.getOutputStream().write(json.toString().getBytes());
    }

    //-- @ResourceMapping("getProposalVersions")
    public void getProposalVersions(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long contestId, @RequestParam long contestPhaseId,
            @RequestParam long proposalId, @RequestParam int start, @RequestParam int end)
            throws IOException {

        Proposal2Phase p2p = null;
        Contest contest = ContestClientUtil.getContest(contestId);
        ClientHelper clientHelper = new ClientHelper(contest);
        ProposalPhaseClient proposalPhaseClient = clientHelper.getProposalPhaseClient();
        ProposalClient proposalClient = clientHelper.getProposalClient();
        ContestClient contestClient = clientHelper.getContestClient();

        if (contestPhaseId > 0) {
            p2p = proposalPhaseClient.getProposal2PhaseByProposalIdContestPhaseId(proposalId,contestPhaseId);
        }

        final JsonArrayBuilder proposalVersionsArray = Json.createArrayBuilder();

        // COUNT VERSIONS
        int offset = 0;
        int counter = 0;
        int numberOfVersions = 0;
        Date oldDate = new Date();
        for (ProposalVersion proposalVersion: proposalClient.getAllProposalVersions(proposalId)) {
            long cphId = proposalVersion.getContestPhaseId();

            final ContestPhase contestPhase = contestClient.getContestPhase(cphId);
            if (contest != null){
                // Skip versions that do not belong to this contest
                Contest c2 = contestPhase.getContest();
                if (!Objects.equals(c2.getContestPK(), contest.getContestPK())) {
                    continue;
                }
            }

            if (p2p != null && (proposalVersion.getVersion() <= p2p.getVersionFrom()
                    || (proposalVersion.getVersion() > p2p.getVersionTo()
                        && p2p.getVersionTo() != -1))) {
                continue;
            }

            if (Math.abs(oldDate.getTime() - proposalVersion.getCreateDate().getTime()) > MILLISECONDS_TO_GROUP_VERSIONS) {
                numberOfVersions++;
                if (counter > (end-start)) {
                    oldDate = proposalVersion.getCreateDate();
                    continue;
                }
                if (offset < start){
                    offset++;
                    oldDate = proposalVersion.getCreateDate();
                    continue;
                }

                Member author = Member.fromId(proposalVersion.getAuthorId());
                proposalVersionsArray.add(Json.createObjectBuilder()
                        .add("version", proposalVersion.getVersion())
                        .add("date", proposalVersion.getCreateDate().getTime())
                        .add("author", Json.createObjectBuilder()
                                .add("userId", author.getId_())
                                .add("screenName", author.getScreenName())
                                .add("fullName", author.getFullName()))
                        .add("updateType", proposalVersion.getUpdateType())
                        .add("contestPhase", Json.createObjectBuilder()
                                .add("id", contestPhase.getContestPhasePK())
                                .add("name", contestPhase.getName())));

                oldDate = proposalVersion.getCreateDate();
                counter++;
            }
        }

        final JsonObject json = Json.createObjectBuilder()
                .add("proposalId", proposalId)
                .add("start", start)
                .add("end", end)
                .add("totalCount", numberOfVersions)
                .add("versions", proposalVersionsArray)
                .build();

        response.getOutputStream().write(json.toString().getBytes());
    }
}
