package org.xcolab.view.pages.proposals.view.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PopulateContextInterceptor extends HandlerInterceptorAdapter {

    private static final String MODEL_ATTRIBUTE_PROPOSALS_PERMISSIONS = "proposalsPermissions";
    private static final String MODEL_ATTRIBUTE_PROPOSAL_DISPLAY_PERMISSIONS =
            "proposalsDisplayPermissions";
    private static final String MODEL_ATTRIBUTE_CONTEST_PHASE = "contestPhase";
    private static final String MODEL_ATTRIBUTE_PROPOSAL = "proposal";
    private static final String MODEL_ATTRIBUTE_CONTEST = "contest";
    private static final String MODEL_ATTRIBUTE_VIEW_CONTEST_PHASE_ID = "viewContestPhaseId";
    private static final String MODEL_ATTRIBUTE_PROPOSALS_PREFERENCES = "preferences";
    private static final String MODEL_ATTRIBUTE_CONTEST_TYPE = "contestType";

    private final ProposalsContext proposalsContext;

    @Autowired
    public PopulateContextInterceptor(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) {
        String requestUrl = request.getRequestURL().toString();

        String colabPattern =
                "/(\\w+)/(?<contestYear>\\d+)/(?<contestUrlName>[a-zA-Z0-9_-]+)(((/c/)|"
                        + "(/phase/(?<phaseId>\\d+)/))(?<proposalIdentifier>\\w+)/"
                        + "(?<proposalId>\\d+)(/tab/?(?<tab>\\w+))?(/version/?"
                        + "(?<version>\\d+))?)?";
        Pattern pattern = Pattern.compile(colabPattern);
        Matcher matcher = pattern.matcher(requestUrl);

        if (matcher.find()) {
            String[] pathMatches =
                    {"contestYear", "contestUrlName", "proposalId", "phaseId", "tab", "version"};
            for (String field : pathMatches) {
                if (matcher.group(field) != null) {
                    request.setAttribute(field, matcher.group(field));
                }
            }

        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {

        if (modelAndView != null) {
            Contest contest = proposalsContext.getContest(request);
            ContestPhase contestPhase = proposalsContext.getContestPhase(request);
            Proposal proposal = proposalsContext.getProposal(request);
            ProposalsPermissions permissions = proposalsContext.getPermissions(request);
            ProposalsDisplayPermissions displayPermissions =
                    proposalsContext.getDisplayPermissions(request);

            if (contest != null) {
                modelAndView.addObject(MODEL_ATTRIBUTE_CONTEST,
                        proposalsContext.getContestWrapped(request));

                if (contestPhase != null) {
                    modelAndView.addObject(MODEL_ATTRIBUTE_CONTEST_PHASE,
                            proposalsContext.getContestPhaseWrapped(request));

                    if (proposal != null) {
                        modelAndView.addObject(MODEL_ATTRIBUTE_PROPOSAL,
                                proposalsContext.getProposalWrapped(request));
                    }
                }
            }

            modelAndView.addObject(MODEL_ATTRIBUTE_PROPOSALS_PERMISSIONS, permissions);
            modelAndView.addObject(MODEL_ATTRIBUTE_PROPOSAL_DISPLAY_PERMISSIONS,
                    displayPermissions);
            modelAndView.addObject(MODEL_ATTRIBUTE_VIEW_CONTEST_PHASE_ID,
                    proposalsContext.getViewContestPhaseId(request));
            modelAndView.addObject(MODEL_ATTRIBUTE_PROPOSALS_PREFERENCES,
                    proposalsContext.getProposalsPreferences(request));
            modelAndView.addObject(MODEL_ATTRIBUTE_CONTEST_TYPE,
                    proposalsContext.getContestType(request));
        }
    }

}
