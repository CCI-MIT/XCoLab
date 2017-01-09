package org.xcolab.view.pages.contestmanagement.utils;

import org.springframework.stereotype.Component;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.view.taglibs.xcolab.interfaces.TabContext;
import org.xcolab.view.taglibs.xcolab.interfaces.TabPermissions;

import javax.servlet.http.HttpServletRequest;

@Component
public class ContestsContextImpl implements TabContext {

    private static final String CONTEXT_ATTRIBUTE_PREFIX = "_proposalsPortlet_";
    private static final String CONTEXT_INITIALIZED_ATTRIBUTE =
            CONTEXT_ATTRIBUTE_PREFIX + "contextInitialized";
    private static final String PERMISSIONS_ATTRIBUTE = CONTEXT_ATTRIBUTE_PREFIX + "permissions";
    private static final String CONTEST_ATTRIBUTE = CONTEXT_ATTRIBUTE_PREFIX + "contest";
    private static final String USER_ATTRIBUTE = CONTEXT_ATTRIBUTE_PREFIX + "user";
    private static final String CONTEST_WRAPPED_ATTRIBUTE =
            CONTEXT_ATTRIBUTE_PREFIX + "contestWrapped";
    private static final String CONTEST_ID_PARAM = "contestId";
    private static final String CONTEST_MANAGEMENT_PARAM = "manager";

    public ContestsContextImpl() {
    }

    @Override
    public Contest getContest(HttpServletRequest request) {
        return getAttribute(request, CONTEST_ATTRIBUTE, Contest.class);
    }

    @Override
    public TabPermissions getPermissions(HttpServletRequest request) {
        return getAttribute(request, PERMISSIONS_ATTRIBUTE, TabPermissions.class);
    }

    private <T> T getAttribute(HttpServletRequest request, String attributeName, Class<T> clasz) {
        Object contextInitialized = request.getAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
        if (contextInitialized == null) {
            init(request);
        }
        return (T) request.getAttribute(attributeName);
    }

    private void init(HttpServletRequest request) {
        final Boolean mangerView = Boolean.valueOf(request.getParameter(CONTEST_MANAGEMENT_PARAM));
        if (mangerView) {
            request.setAttribute(PERMISSIONS_ATTRIBUTE, new ContestManagementPermissions(request));
        } else {
            final Long contestId = new Long(request.getParameter(CONTEST_ID_PARAM));
            Contest contest;
            try {
                contest = ContestClientUtil.getContest(contestId);
            } catch (ContestNotFoundException e) {
                throw new InternalException(e);
            }

            if (contest != null) {
                request.setAttribute(CONTEST_ATTRIBUTE, contest);
                request.setAttribute(CONTEST_WRAPPED_ATTRIBUTE, (contest));
                request.setAttribute(PERMISSIONS_ATTRIBUTE,
                        new ContestPermissions(request, contest));
            }
        }

        request.setAttribute(CONTEXT_INITIALIZED_ATTRIBUTE, true);
    }
}
