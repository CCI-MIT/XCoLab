package org.xcolab.portlets.proposals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
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
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.entity.utils.MemberUtil;
import org.xcolab.portlets.proposals.utils.context.ClientHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class ProposalMoveJsonController {

    private final ObjectMapper mapper = new ObjectMapper();

    @ResourceMapping("getContestsOpenForProposals")
    public void getContestsOpenForProposals(ResourceRequest request, ResourceResponse response)
            throws IOException {

        boolean admin = false;
        Member member = MemberUtil.getMember(request);
        if (member != null) {
            List<Role_> roles = member.getRoles();

            for (Role_ role : roles) {
                if (role.getName().equals("Administrator")) {
                    admin = true;
                    break;
                }
            }
        }

        List<Contest> returnList = new ArrayList<>();
        for (Contest contest: ContestClientUtil.findContestsByActive(true)) {
            ContestPhase cp = ContestClientUtil.getActivePhase(contest.getContestPK());
            if (cp.getPhaseActive()) {
                String statusStr = cp.getContestStatusStr();
                ContestStatus status = null;
                if (statusStr != null) {
                    status = ContestStatus.valueOf(statusStr);
                }
                if (admin || (status != null && status.isCanCreate())) {
                    returnList.add(contest);
                }
            }
        }

        // Add non active contests
        if (admin) {
            returnList.addAll(ContestClientUtil.findContestsByActive(false));
        }

        mapper.writeValue(response.getPortletOutputStream(), returnList);
    }

    @ResourceMapping("getProposalContestSections")
    public void getProposalContestSections(ResourceRequest request, ResourceResponse response,
            @RequestParam long proposalId, @RequestParam int version, @RequestParam long contestId)
            throws IOException {
        List<ImmutableSection> returnList = new ArrayList<>();

        Contest contest = ContestClientUtil.getContest(contestId);
        ClientHelper clientHelper = new ClientHelper(contest);

        Proposal proposal = clientHelper.getProposalClient().getProposal(proposalId);

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
        mapper.writeValue(response.getPortletOutputStream(), returnList);
    }

    private static class ImmutableSection {

        private final String title;
        private final long id;
        private final String content;

        ImmutableSection(String title, long id, String content) {
            this.title = title;
            this.id = id;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }
    }
}
