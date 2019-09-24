package org.xcolab.view.pages.proposals.view.proposal.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.user.pojo.wrapper.RoleWrapper;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class ProposalMoveJsonController {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private IContestClient contestClient;

    @GetMapping("/api/contestsOpenForProposals")
    public void getContestsOpenForProposals(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        boolean admin = false;
        UserWrapper member = MemberAuthUtil.getMemberOrNull();
        if (member != null) {
            List<RoleWrapper> roles = member.getRoles();

            for (RoleWrapper role : roles) {
                if (role.getName().equals("Administrator")) {
                    admin = true;
                    break;
                }
            }
        }

        List<ImmutableContest> returnList = new ArrayList<>();
        for (ContestWrapper contest: contestClient.findContestsByActive(true)) {
            ContestPhaseWrapper cp = contestClient.getActivePhase(contest.getId());
            if (cp.getPhaseActive()) {
                String statusStr = cp.getContestStatusStr();
                ContestStatus status = null;
                if (statusStr != null) {
                    status = ContestStatus.valueOf(statusStr);
                }
                if (admin || (status != null && status.isCanCreate())) {
                    returnList.add(new ImmutableContest(contest));
                }
            }
        }

        // Add non active contests
        if (admin) {
            final List<ContestWrapper> inactiveContests = contestClient.findContestsByActive(false);
            for (ContestWrapper inactiveContest : inactiveContests) {
                returnList.add(new ImmutableContest(inactiveContest));
            }
        }

        response.flushBuffer();
        mapper.writeValue(response.getOutputStream(), returnList);
    }

    private static class ImmutableContest {

        private final String contestShortName;
        private final String contestName;
        private final long contestYear;
        private final String contestUrlName;

        private ImmutableContest(ContestWrapper contest) {
            this.contestShortName = contest.getTitleWithEndYear();
            this.contestName = contest.getQuestion();
            this.contestYear = contest.getContestYear();
            this.contestUrlName = contest.getContestUrlName();
        }

        public String getContestShortName() {
            return contestShortName;
        }

        public String getContestName() {
            return contestName;
        }

        public long getContestYear() {
            return contestYear;
        }

        public String getContestUrlName() {
            return contestUrlName;
        }
    }


    @GetMapping("/api/contests/{contestId}/proposals/{proposalId}/versions/{version}/sections")
    public void getProposalContestSections(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long proposalId, @PathVariable int version, @PathVariable long contestId)
            throws IOException {
        List<ImmutableSection> returnList = new ArrayList<>();

        ContestWrapper contest = contestClient.getContest(contestId);
        ClientHelper clientHelper = new ClientHelper();

        IProposalTemplate proposalTemplate = clientHelper.getProposalTemplateClient()
                .getProposalTemplate(contest.getProposalTemplateId());

        if (proposalTemplate != null) {
            for (ProposalTemplateSectionDefinitionWrapper psd : clientHelper.getProposalTemplateClient()
                    .getProposalTemplateSectionDefinitionByProposalTemplateId(proposalTemplate.getId(), false)) {
                ProposalAttribute attribute = clientHelper.getProposalAttributeClient()
                        .getProposalAttribute(proposalId, version,
                                ProposalAttributeKeys.SECTION, psd.getId());
                if (attribute != null && !attribute.getStringValue().trim().isEmpty()) {
                    returnList.add(new ImmutableSection(psd.getTitle(),
                            psd.getId(), attribute.getStringValue()));
                }
            }
        }
        response.flushBuffer();
        mapper.writeValue(response.getOutputStream(), returnList);
    }

    private static class ImmutableSection {

        private final String title;
        private final long sectionId;
        private final String content;

        ImmutableSection(String title, long sectionId, String content) {
            this.title = title;
            this.sectionId = sectionId;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public long getSectionId() {
            return sectionId;
        }

        public String getContent() {
            return content;
        }
    }
}
