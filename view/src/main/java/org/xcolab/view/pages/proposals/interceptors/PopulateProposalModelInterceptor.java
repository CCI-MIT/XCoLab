package org.xcolab.view.pages.proposals.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.view.pages.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalContextImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PopulateProposalModelInterceptor extends HandlerInterceptorAdapter {

    private static final String MODEL_PROPOSALS_PERMISSIONS = "proposalsPermissions";
    private static final String MODEL_PROPOSAL_DISPLAY_PERMISSIONS =
            "proposalsDisplayPermissions";
    private static final String MODEL_CONTEST_PHASE = "contestPhase";
    private static final String MODEL_PROPOSAL = "proposal";
    private static final String MODEL_CONTEST = "contest";
    private static final String MODEL_PROPOSALS_PREFERENCES = "preferences";
    private static final String MODEL_CONTEST_TYPE = "contestType";

    private final LocaleResolver localeResolver;

    @Autowired
    public PopulateProposalModelInterceptor(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {

        if (modelAndView != null) {
            ProposalContext proposalContext = new ProposalContextImpl(request, localeResolver);
            ContestWrapper contest = proposalContext.getContest();
            ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();
            ProposalWrapper proposal = proposalContext.getProposal();
            ProposalsPermissions permissions = proposalContext.getPermissions();
            ProposalsDisplayPermissions displayPermissions =
                    proposalContext.getDisplayPermissions();

            if (contest != null) {
                modelAndView.addObject(MODEL_CONTEST, proposalContext.getContest());

                if (contestPhase != null) {
                    modelAndView.addObject(MODEL_CONTEST_PHASE, proposalContext.getContestPhase());

                    if (proposal != null) {
                        modelAndView.addObject(MODEL_PROPOSAL, proposalContext.getProposal());
                    }
                }
            }

            modelAndView.addObject(MODEL_PROPOSALS_PERMISSIONS, permissions);
            modelAndView.addObject(MODEL_PROPOSAL_DISPLAY_PERMISSIONS, displayPermissions);
            modelAndView.addObject(MODEL_PROPOSALS_PREFERENCES, proposalContext.getPreferences());
            modelAndView.addObject(MODEL_CONTEST_TYPE, proposalContext.getContestType());
        }
    }

}
