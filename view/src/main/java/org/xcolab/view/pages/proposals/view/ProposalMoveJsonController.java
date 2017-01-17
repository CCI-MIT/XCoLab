package org.xcolab.view.pages.proposals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Role_;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
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


    @GetMapping("/api/contestsOpenForProposals")
    public void getContestsOpenForProposals(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        boolean admin = false;
        Member member = MemberAuthUtil.getMemberOrNull(request);
        if (member != null) {
            List<Role_> roles = member.getRoles();

            for (Role_ role : roles) {
                if (role.getName().equals("Administrator")) {
                    admin = true;
                    break;
                }
            }
        }

        List<ImmutableContest> returnList = new ArrayList<>();
        for (Contest contest: ContestClientUtil.findContestsByActive(true)) {
            ContestPhase cp = ContestClientUtil.getActivePhase(contest.getContestPK());
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
            final List<Contest> inactiveContests = ContestClientUtil.findContestsByActive(false);
            for (Contest inactiveContest : inactiveContests) {
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

        private ImmutableContest(Contest contest) {
            this.contestShortName = contest.getContestShortName();
            this.contestName = contest.getContestName();
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

        Contest contest = ContestClientUtil.getContest(contestId);
        ClientHelper clientHelper = new ClientHelper(contest);

        PlanTemplate planTemplate = clientHelper.getPlanTemplateClient()
                .getPlanTemplate(contest.getPlanTemplateId());

        if (planTemplate != null) {
            for (PlanSectionDefinition psd : clientHelper.getPlanTemplateClient()
                    .getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId_(), false)) {
                ProposalAttribute attribute = clientHelper.getProposalAttributeClient()
                        .getProposalAttribute(proposalId, version,
                                ProposalAttributeKeys.SECTION, psd.getId_());
                if (attribute != null && !attribute.getStringValue().trim().isEmpty()) {
                    returnList.add(new ImmutableSection(psd.getTitle(),
                            psd.getId_(), attribute.getStringValue()));
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
