package org.xcolab.view.pages.proposals.tabs;

import org.xcolab.view.pages.proposals.tabs.access.AdvancingAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.EvaluationAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.ImpactAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.LegacyImpactAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.PointsAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.ScreeningAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import javax.servlet.http.HttpServletRequest;

public enum ProposalTab {
    DESCRIPTION("Description", "contests.proposal.tabs.description",
            Type.NORMAL, ProposalTabAccess.ALWAYS, ProposalTabAccess.EDIT,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    ACTIONS_IMPACTS("Model results", "contests.proposal.tabs.modelresults",
            Type.NORMAL, new LegacyImpactAccessAlgorithm(), ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    IMPACT("Impact", "contests.proposal.tabs.impact",
            Type.NORMAL, ImpactAccessAlgorithm.view(), ImpactAccessAlgorithm.edit(),
            ProposalTabActivityCountAlgorithm.alwaysZero),
    TEAM("Contributors", "contests.proposal.tabs.contributors",
            Type.NORMAL, ProposalTabAccess.ALWAYS, ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.membersCount),
    COMMENTS("Comments", "contests.proposal.tabs.comments",
            Type.NORMAL, ProposalTabAccess.ALWAYS, ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.commentsCount),
    SCREENING("Screening", "contests.proposal.tabs.screening",
            Type.HIGHLIGHT, new ScreeningAccessAlgorithm(),
            ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    ADVANCING("Advancing", "contests.proposal.tabs.advancing",
            Type.HIGHLIGHT, new AdvancingAccessAlgorithm(),
            ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    EVALUATION("Evaluation Results", "contests.proposal.tabs.evaluation",
            Type.NORMAL, new EvaluationAccessAlgorithm(),
            ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.evaluationCommentsCount),
    //TODO: check if we still need this
//    FELLOW_REVIEW("Fellow Review", Type.NORMAL, ProposalTabCanAccessAlgorithm.fellowReviewAccess,
//            ProposalTabAccess.NEVER,
//            ProposalTabActivityCountAlgorithm.fellowReviewCommentsCount),
    ADMIN("Admin", "contests.proposal.tabs.admin",
            Type.NORMAL, ProposalTabAccess.PROPOSAL_ADMIN, ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    POINTS("Points", "contests.proposal.tabs.points",
            Type.NORMAL, PointsAccessAlgorithm.view(), PointsAccessAlgorithm.edit(),
            ProposalTabActivityCountAlgorithm.alwaysZero);

    private final String displayName;
    private final Type tabType;
    private final ProposalTabCanAccessAlgorithm canAccessTabAlgorithm;
    private final ProposalTabCanAccessAlgorithm canEditAlgorithm;
    private final ProposalTabActivityCountAlgorithm activitiesCountAlgorithm;
    private final String displayNameMessageCode;

    ProposalTab(String displayName, String displayNameMessageCode,
            Type tabType, ProposalTabCanAccessAlgorithm canAccessAlgorithm,
            ProposalTabCanAccessAlgorithm canEditAlgorithm,
            ProposalTabActivityCountAlgorithm activitiesCountAlgorithm) {
        this.displayName = displayName;
        this.displayNameMessageCode = displayNameMessageCode;
        this.tabType = tabType;
        this.canAccessTabAlgorithm = canAccessAlgorithm;
        this.canEditAlgorithm = canEditAlgorithm;
        this.activitiesCountAlgorithm = activitiesCountAlgorithm;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isDefault() {
        return this.ordinal() == 0;
    }

    public boolean getCanAccess(ProposalContext proposalContext) {
        return canAccessTabAlgorithm.canAccess(proposalContext);
    }

    public boolean getCanEdit(ProposalContext proposalContext) {
        return canEditAlgorithm.canAccess(proposalContext);
    }

    public int getActivityCount(ProposalContext context, HttpServletRequest request) {
        return activitiesCountAlgorithm.getActivityCount(context, request);
    }

    public Type getTabType() {
        return tabType;
    }

    public String getDisplayNameMessageCode() {
        return displayNameMessageCode;
    }

    public enum Type {
        NORMAL(""),
        HIGHLIGHT("highlight");

        private final String cssModifier;

        Type(String cssModifier) {

            this.cssModifier = cssModifier;
        }

        public String getCssModifier() {
            return cssModifier;
        }
    }

}
