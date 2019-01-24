package org.xcolab.view.pages.proposals.requests;

import org.hibernate.validator.constraints.NotBlank;

import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.view.pages.proposals.wrappers.ProposalRatingTypeWrapper;

import java.util.ArrayList;
import java.util.List;

public class ProposalAdvancingBean {

    private static final String[] EMAIL_TEMPLATES_TO_LOAD = {
            "ADVANCING_ADVANCE_TO_FINALIST",
            "ADVANCING_ADVANCE_TO_SEMIFINALIST",
            "ADVANCING_DO_NOT_ADVANCE"
    };

    private final List<ProposalRatingTypeWrapper> ratingTypes;

    private ContestEmailTemplateBean emailTemplateBean;

    private JudgingSystemActions.AdvanceDecision advanceDecision;

    @NotBlank(message = "Please enter a comment before saving the decision")
    private String advanceComment;

    public ProposalAdvancingBean() {
        this.ratingTypes = fetchRatingTypes();
    }

    public ProposalAdvancingBean(ProposalWrapper wrapper) {

        this.ratingTypes = fetchRatingTypes();

        advanceDecision = wrapper.getJudgeDecision();
        advanceComment = wrapper.getProposalReview();

        this.emailTemplateBean = new ContestEmailTemplateBean(EMAIL_TEMPLATES_TO_LOAD,
                wrapper.getName(), wrapper.getContest().getTitle());
    }

    private List<ProposalRatingTypeWrapper> fetchRatingTypes() {
        List<ProposalRatingTypeWrapper> ratingTypes = new ArrayList<>();

        //initialize ratingValues and types
        for (IProposalRatingType type : StaticProposalContext.getProposalJudgeRatingClient()
                .getRatingTypesForJudges()) {
            ratingTypes.add(new ProposalRatingTypeWrapper(type));
        }
        return ratingTypes;
    }


    public ContestEmailTemplateBean getEmailTemplateBean() {
        return this.emailTemplateBean;
    }

    public int getAdvanceDecision() {
        return advanceDecision.getAttributeValue();
    }

    public void setAdvanceDecision(int judgeDecisionValue) {
        this.advanceDecision = JudgingSystemActions.AdvanceDecision.fromInt(judgeDecisionValue);
    }

    public String getAdvanceComment() {
        return advanceComment;
    }

    public void setAdvanceComment(String advanceComment) {
        this.advanceComment = advanceComment;
    }

    public List<ProposalRatingTypeWrapper> getRatingTypes() {
        return ratingTypes;
    }
}
