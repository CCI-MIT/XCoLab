package org.xcolab.view.pages.proposals.tabs;

import org.xcolab.view.pages.proposals.tabs.access.AdvancingAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.EvaluationAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.ImpactAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.LegacyImpactAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.PointsAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.ScreeningAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextWrapper;

import javax.servlet.http.HttpServletRequest;

public enum ProposalTab {
    DESCRIPTION("Description", Type.NORMAL, ProposalTabAccess.ALWAYS, ProposalTabAccess.EDIT,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    ACTIONS_IMPACTS("Model results", Type.NORMAL,
            new LegacyImpactAccessAlgorithm(), ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    IMPACT("Impact", Type.NORMAL, ImpactAccessAlgorithm.view(), ImpactAccessAlgorithm.edit(),
            ProposalTabActivityCountAlgorithm.alwaysZero),
    TEAM("Contributors", Type.NORMAL, ProposalTabAccess.ALWAYS, ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.membersCount),
    COMMENTS("Comments", Type.NORMAL, ProposalTabAccess.ALWAYS, ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.commentsCount),
    SCREENING("Screening", Type.HIGHLIGHT, new ScreeningAccessAlgorithm(),
            ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    ADVANCING("Advancing", Type.HIGHLIGHT, new AdvancingAccessAlgorithm(),
            ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    EVALUATION("Evaluation Results", Type.NORMAL, new EvaluationAccessAlgorithm(),
            ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.evaluationCommentsCount),
    //TODO: check if we still need this
//    FELLOW_REVIEW("Fellow Review", Type.NORMAL, ProposalTabCanAccessAlgorithm.fellowReviewAccess,
//            ProposalTabAccess.NEVER,
//            ProposalTabActivityCountAlgorithm.fellowReviewCommentsCount),
    ADMIN("Admin", Type.NORMAL, ProposalTabAccess.PROPOSAL_ADMIN, ProposalTabAccess.NEVER,
            ProposalTabActivityCountAlgorithm.alwaysZero),
    POINTS("Points", Type.NORMAL, PointsAccessAlgorithm.view(), PointsAccessAlgorithm.edit(),
            ProposalTabActivityCountAlgorithm.alwaysZero);

    private final String displayName;
    private final Type tabType;
    private final ProposalTabCanAccessAlgorithm canAccessTabAlgorithm;
    private final ProposalTabCanAccessAlgorithm canEditAlgorithm;
    private final ProposalTabActivityCountAlgorithm activitiesCountAlgorithm;

    ProposalTab(String displayName, Type tabType,
            ProposalTabCanAccessAlgorithm canAccessAlgorithm,
            ProposalTabCanAccessAlgorithm canEditAlgorithm,
            ProposalTabActivityCountAlgorithm activitiesCountAlgorithm) {
        this.displayName = displayName;
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

    public boolean getCanAccess(HttpServletRequest request) {
        return canAccessTabAlgorithm.canAccess(new ProposalsContextWrapper(request));
    }

    public boolean getCanEdit(HttpServletRequest request) {
        return canEditAlgorithm.canAccess(new ProposalsContextWrapper(request));
    }

    public int getActivityCount(ProposalsContext context, HttpServletRequest request) {
        return activitiesCountAlgorithm.getActivityCount(context, request);
    }

    public Type getTabType() {
        return tabType;
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