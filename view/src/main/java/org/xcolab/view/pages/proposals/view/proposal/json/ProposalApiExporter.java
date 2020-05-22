package org.xcolab.view.pages.proposals.view.proposal.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.view.pages.contestmanagement.beans.ProposalReportBean;
import org.xcolab.view.pages.contestmanagement.utils.FullProposalCsvWriter;
import org.xcolab.view.pages.contestmanagement.utils.ProposalCommentsCsvWriter;
import org.xcolab.view.pages.contestmanagement.utils.ProposalExportType;
import org.xcolab.view.pages.contestmanagement.utils.ProposalSupportersCsvWriter;
import org.xcolab.view.util.entity.EntityIdListUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProposalApiExporter {

    @Autowired
    private IContestClient contestClient;

    @GetMapping("/api/contests/{contestId}/comments")
    public void generateProposalCommentReport(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long contestId) throws IOException {

        getCommentsForContestIdList(contestId, response, ProposalExportType.ALL_COMMENTS);

    }

    @GetMapping("/api/contests/comments")
    public void generateProposalCommentReport(HttpServletRequest request, HttpServletResponse response) throws IOException {

        getCommentsForContestIdList(null, response, ProposalExportType.ALL_COMMENTS);

    }

    @GetMapping("/api/contests/{contestId}/supporters")
    public void generateProposalSupportReport(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long contestId) throws IOException {

        getCommentsForContestIdList(contestId, response, ProposalExportType.ALL_SUPPORTERS);

    }

    @GetMapping("/api/contests/supporters")
    public void generateSupportCommentReport(HttpServletRequest request, HttpServletResponse response) throws IOException {

        getCommentsForContestIdList(null, response, ProposalExportType.ALL_SUPPORTERS);

    }

    @GetMapping("/api/contests/{contestId}/proposals")
    public void generateProposalReport(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long contestId) throws IOException {

        getCommentsForContestIdList(contestId, response, ProposalExportType.ALL_ESCAPED);

    }

    @GetMapping("/api/contests/proposals")
    public void generateProposalReport(HttpServletRequest request, HttpServletResponse response) throws IOException {

        getCommentsForContestIdList(null, response, ProposalExportType.ALL_ESCAPED);

    }

    private void getCommentsForContestIdList(Long contestId, HttpServletResponse response , ProposalExportType proposalExportType)
            throws IOException {


        List<ContestWrapper> contests = null;

        if(contestId!=null) {
            contests = new ArrayList<>();
            contests.add(contestClient.getContest(contestId));
        } else {

            contests = contestClient.getContests(true, false,
                    ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        }

        switch (proposalExportType) {
            case ALL_ESCAPED: {
                if (contests.size() >= 1) {
                    List<ProposalWrapper> proposals = StaticProposalContext.getProposalClient()
                            .listProposals(contests.get(0).getId());
                    if (proposals.size() >= 1) {
                        ProposalWrapper proposal = new ProposalWrapper(proposals.get(0));

                        List<String> headers = new ArrayList<>();
                        headers.addAll(FullProposalCsvWriter.COLUMN_NAMES);

                        for (ProposalTemplateSectionDefinitionWrapper section : proposal
                                .getSections()) {
                            headers.add(section.getTitle());
                        }

                        try (FullProposalCsvWriter csvWriter = new FullProposalCsvWriter(response,
                                headers)) {
                            contests.forEach(csvWriter::writeProposalsInContest);
                        }
                    }
                }
                break;
            }
            case ALL_SUPPORTERS : {

                try (ProposalSupportersCsvWriter csvWriter = new ProposalSupportersCsvWriter(response)) {
                    contests.forEach(csvWriter::writeProposalSupportersInContest);
                }
                break;
            }
            case ALL_COMMENTS: {
                try (ProposalCommentsCsvWriter csvWriter = new ProposalCommentsCsvWriter(
                        response)) {
                    contests.forEach(csvWriter::writeProposalCommentsInContest);
                }
                break;
            }
        }
    }
}

